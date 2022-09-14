package com.weekclone.marketkurlyclone.service;

import com.weekclone.marketkurlyclone.dto.MemberRequestDto;
import com.weekclone.marketkurlyclone.dto.ResponseDto;
import com.weekclone.marketkurlyclone.dto.TokenDto;
import com.weekclone.marketkurlyclone.dto.TokenRequestDto;
import com.weekclone.marketkurlyclone.jwt.TokenProvider;
import com.weekclone.marketkurlyclone.model.Member;
import com.weekclone.marketkurlyclone.model.RefreshToken;
import com.weekclone.marketkurlyclone.repository.MemberRepository;
import com.weekclone.marketkurlyclone.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final RedisTemplate redisTemplate;



    public boolean idcheck(@RequestBody MemberRequestDto memberRequestDto)
    {
        if(memberRepository.existsByMemberId(memberRequestDto.getMemberId()))
        {
            return false;
        }
        return true;
    }

    public boolean emailCheck(MemberRequestDto memberRequestDto)
    {
        if(memberRepository.existsByEmail(memberRequestDto.getEmail()))
        {
            return false;
        }
        return true;
    }

    @Transactional
    public ResponseDto signup(MemberRequestDto memberRequestDto) {
        if(!(Pattern.matches("[a-zA-Z0-9]*$",memberRequestDto.getMemberId()) && (memberRequestDto.getMemberId().length() > 5 && memberRequestDto.getMemberId().length() <17)
                && Pattern.matches("[a-zA-Z0-9]*$",memberRequestDto.getPassword()) && (memberRequestDto.getPassword().length() > 9 && memberRequestDto.getPassword().length() <33))){
            throw new IllegalArgumentException("닉네임 혹은 비밀번호 조건을 확인해주세요.");
        }
        Member member = memberRequestDto.toMember(passwordEncoder);
        memberRepository.save(member);

        return ResponseDto.is_Success(member);
    }


    @Transactional
    public TokenDto login(MemberRequestDto memberRequestDto) {
//        if (!memberRepository.existsByNickname(memberRequestDto.getNickname()) ||
//                !memberRepository.existsByPassword(passwordEncoder.encode(memberRequestDto.getPassword()))) {
//            throw new RuntimeException("사용자를 찾을 수 없습니다");
//        }
        // 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = memberRequestDto.toAuthentication();

        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        //    authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        /*    AuthService에서 AuthenticationManagerBuilder 주입 받음
              AuthenticationManagerBuilder 에서 AuthenticationManager 를 구현한 ProviderManager 생성
              org.springframework.security.authentication.ProviderManager 는 AbstractUserDetailsAuthenticationProvider 의 자식 클래스인 DaoAuthenticationProvider 를 주입받아서 호출
              DaoAuthenticationProvider 의 authenticate 에서는 retrieveUser 로 DB 에 있는 사용자 정보를 가져오고 additionalAuthenticationChecks 로 비밀번호 비교
              retrieveUser 내부에서 UserDetailsService 인터페이스를 직접 구현한 CustomUserDetailsService 클래스의 오버라이드 메소드인 loadUserByUsername 가 호출됨*/

        try{
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);


            // 3. 인증 정보를 기반으로 JWT 토큰 생성
            TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

            // 4. RefreshToken 저장
            RefreshToken refreshToken = RefreshToken.builder()
                    .key(authentication.getName())
                    .value(tokenDto.getRefreshToken())
                    .build();

            refreshTokenRepository.save(refreshToken);

            // 5. 토큰 발급
            return tokenDto;
        } catch (Exception e){
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다.");
        }
    }

    public void logout(TokenRequestDto tokenRequestDto, HttpServletRequest request) {
        String accessToken = request.getHeader("Authorization").substring(7);
        Long expiration = tokenProvider.getExpiration(accessToken);
        System.out.println(expiration);
        redisTemplate.opsForValue()
                .set(accessToken,"logout",expiration, TimeUnit.MILLISECONDS);

    }

    @Transactional
    public TokenDto reissue(TokenRequestDto tokenRequestDto) {
        // 1. Refresh Token 검증
        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("Refresh Token 이 유효하지 않습니다.");
        }

        // 2. Access Token 에서 Member ID 가져오기
        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        // 3. 저장소에서 Member ID 를 기반으로 Refresh Token 값 가져옴
        RefreshToken refreshToken = refreshTokenRepository.findByKkey(authentication.getName())
                .orElseThrow(() -> new RuntimeException("로그아웃 된 사용자입니다."));

        // 4. Refresh Token 일치하는지 검사
        if (!refreshToken.getVvalue().equals(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        // 5. 새로운 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 6. 저장소 정보 업데이트
        RefreshToken newRefreshToken = refreshToken.updateValue(tokenDto.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);

        // 토큰 발급
        return tokenDto;
    }
}