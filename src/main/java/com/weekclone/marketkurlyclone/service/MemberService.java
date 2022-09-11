package com.weekclone.marketkurlyclone.service;


import com.weekclone.marketkurlyclone.dto.MemberResponseDto;
import com.weekclone.marketkurlyclone.dto.ResponseDto;
import com.weekclone.marketkurlyclone.jwt.TokenProvider;
import com.weekclone.marketkurlyclone.model.Authority;
import com.weekclone.marketkurlyclone.model.Member;
import com.weekclone.marketkurlyclone.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final TokenProvider tokenProvider;
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    public ResponseDto<?> getMemberInfo(String memberId, HttpServletRequest request)
    {
        Member member=memberService.validateMember(request);
        if(member==null)
        {
            //여기 예외처리 global로 만들 생각중
        }
        //권한 확인을 어떻게 할지, 이 부분 확인 필요, 아마 SECURED로 처리하거나 security config에 바로 박아버릴지도

        Member findmember=memberRepository.findByMemberId(memberId);
        MemberResponseDto memberResponseDto= new MemberResponseDto(findmember);
        return ResponseDto.is_Success(memberResponseDto);
    }

    public ResponseDto<?> changeMemberAuthority(String memberId,Long authority,HttpServletRequest request)
    {
        Member member=memberService.validateMember(request);
        if(member==null)
        {
            //여기 예외처리
        }
        Member findmember=memberRepository.findByMemberId(memberId);
        if(authority==1)
        {
            findmember.update(Authority.ROLE_USER);
        } else if (authority==2) {
            findmember.update(Authority.ROLE_MANAGER);
        }

        return ResponseDto.is_Success("권한 변경 완료");
    }



    @Transactional
    public Member validateMember(HttpServletRequest request) {
        if (!tokenProvider.validateToken(request.getHeader("Refresh-Token"))) {
            return null;
        }
        return tokenProvider.getMemberFromAuthentication();
    }


}
