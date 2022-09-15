package com.weekclone.marketkurlyclone.controller;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.weekclone.marketkurlyclone.dto.*;
import com.weekclone.marketkurlyclone.model.Member;
import com.weekclone.marketkurlyclone.oauth.model.OauthResponseModel;
import com.weekclone.marketkurlyclone.oauth.service.GoogleService;
import com.weekclone.marketkurlyclone.repository.MemberRepository;
import com.weekclone.marketkurlyclone.service.AuthService;
import com.weekclone.marketkurlyclone.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    private final MemberService memberService;
    private final GoogleService googleService;

    // 회원가입
    @PostMapping("/member/signup")
    public ResponseDto<?> signup(@RequestBody MemberRequestDto memberRequestDto) {
        return authService.signup(memberRequestDto);
    }

    //아이디 중복검사
    @PostMapping("/member/idcheck")
    public boolean idcheck(@RequestBody MemberCheckRequestDto checkRequestDto)
    {
        return authService.idCheck(checkRequestDto);
    }

    // 이메일 중복검사
    @PostMapping("/member/emailcheck")
    public boolean emailCheck(@RequestBody EmailCheckRequestDto checkRequestDto)
    {
        return authService.emailCheck(checkRequestDto);
    }

    // 로그인
    @PostMapping("/member/login")
    public ResponseDto<?> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        TokenDto tokenDto = authService.login(loginRequestDto);
        response.setHeader("Authorization", "Bearer " + tokenDto.getAccessToken());
        response.setHeader("Refresh-Token", tokenDto.getRefreshToken());
        response.setHeader("Access-Token-Expire-Time", String.valueOf(tokenDto.getAccessTokenExpiresIn()));
        ResponseDto responseDto = ResponseDto.is_Success(memberRepository.findByMemberId(loginRequestDto.getMemberId()));
        return responseDto;
    }

    /*// 로그인 테스트
    @GetMapping(value = "/api/member", produces = "application/json; charset=UTF-8")
    public Object loginTest() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Member> member = memberRepository.findByMemberId(userId);
        if(!member.isPresent()) {
            return "유효하지 않은 토큰이거나 로그인 하지 않았습니다!";
        }
        return member;
    }*/

    // 로그아웃
    @PostMapping(value = "member/logout", produces = "application/json; charset=UTF-8")
    public String logout(TokenRequestDto tokenRequestDto, HttpServletRequest request){
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Member> member = memberRepository.findByMemberId(userId);
        if(!member.isPresent()) {
            return "유효하지 않은 토큰이거나 로그인 하지 않았습니다!";
        }
        authService.logout(tokenRequestDto,request);
        return userId+"계정 로그아웃!";
    }

//    @PostMapping("/member/reissue")  //재발급을 위한 로직
//    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
//        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
//    }

    @GetMapping("/cms/member/{memberId}")
    public ResponseDto<?> getMemberInfo(@PathVariable String memberId, HttpServletRequest request)
    {
        return memberService.getMemberInfo(memberId,request);
    }
    @PutMapping("/cms/member/{memberId}/{authority}")
    public ResponseDto<?> changeMemberAuthority(@PathVariable String memberId, @PathVariable Long authority,HttpServletRequest request)
    {
        return memberService.changeMemberAuthority(memberId,authority,request);
    }

    @GetMapping("/api/member/{oauth}/callback")
    public ResponseEntity<OauthResponseModel> OauthLogin(@RequestParam(name = "code") String code, HttpServletResponse response,
                                                         @RequestParam(value = "state", required = false) String state , @PathVariable String oauth) throws JsonProcessingException {
        if(oauth.equals("google"))
            return googleService.oauthLogin(code, response);
//        else if (oauth.equals("kakao")) {
//            return googleService.kakaologin(code, response);
//        }

        OauthResponseModel oauthResponseModel = OauthResponseModel.builder()
                .code(HttpStatus.OK.value())
                .httpStatus(HttpStatus.OK)
                .message("해당하는 소셜 로그인 정보가 없음").build();
        //////수정
        return new ResponseEntity<>(oauthResponseModel, oauthResponseModel.getHttpStatus());
    }



}