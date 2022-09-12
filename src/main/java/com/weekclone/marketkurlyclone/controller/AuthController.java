package com.weekclone.marketkurlyclone.controller;

import com.weekclone.marketkurlyclone.dto.*;
import com.weekclone.marketkurlyclone.model.Member;
import com.weekclone.marketkurlyclone.repository.MemberRepository;
import com.weekclone.marketkurlyclone.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

//회원가입 로그인 로그아웃
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final MemberRepository memberRepository;

    // 회원가입
    @PostMapping("/api/member/signup")
    public ResponseDto<?> signup(@RequestBody MemberRequestDto memberRequestDto) {
        return authService.signup(memberRequestDto);
    }

    // 로그인
    @PostMapping("/api/member/login")
    public ResponseDto<?> login(@RequestBody MemberRequestDto memberRequestDto, HttpServletResponse response) {
        TokenDto tokenDto = authService.login(memberRequestDto);
        response.setHeader("Authorization", "Bearer " + tokenDto.getAccessToken());
        response.setHeader("Refresh-Token", tokenDto.getRefreshToken());
        response.setHeader("Access-Token-Expire-Time", String.valueOf(tokenDto.getAccessTokenExpiresIn()));
        ResponseDto responseDto = ResponseDto.is_Success(memberRepository.findByMemberId(memberRequestDto.getMemberId()));
        return responseDto;
    }

    // 로그인 테스트
    @GetMapping(value = "/api/member", produces = "application/json; charset=UTF-8")
    public Object loginTest() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Member> member = memberRepository.findByMemberId(userId);
        if(!member.isPresent()) {
            return "유효하지 않은 토큰이거나 로그인 하지 않았습니다!";
        }
        return member;
    }

    // 로그아웃
    @PostMapping(value = "/api/member/logout", produces = "application/json; charset=UTF-8")
    public String logout(TokenRequestDto tokenRequestDto, HttpServletRequest request){
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Member> member = memberRepository.findByMemberId(userId);
        if(!member.isPresent()) {
            return "유효하지 않은 토큰이거나 로그인 하지 않았습니다!";
        }
        authService.logout(tokenRequestDto,request);
        return userId+"계정 로그아웃!";
    }

    @PostMapping("/api/member/reissue")  //재발급을 위한 로직
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
    }

}