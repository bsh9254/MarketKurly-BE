package com.weekclone.marketkurlyclone.controller;
//회원가입 로그인 로그아웃

import com.weekclone.marketkurlyclone.dto.ResponseDto;
import com.weekclone.marketkurlyclone.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final MemberService memberService;


    @GetMapping("/cms/member/{memberId}")
    public ResponseDto<?> getMemberInfo(@PathVariable String memberId, HttpServletRequest request)
    {
        return memberService.getMemberInfo(memberId,request);
    }
    @PostMapping("/cms/member/{memberId}/{authority}")
    public ResponseDto<?> changeMemberAuthority(@PathVariable String memberId, @PathVariable Long authority,HttpServletRequest request)
    {
        return memberService.changeMemberAuthority(memberId,authority,request);
    }




}
