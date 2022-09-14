package com.weekclone.marketkurlyclone.service;


import com.weekclone.marketkurlyclone.dto.MemberResponseDto;
import com.weekclone.marketkurlyclone.dto.ResponseDto;
import com.weekclone.marketkurlyclone.jwt.TokenProvider;
import com.weekclone.marketkurlyclone.model.Authority;
import com.weekclone.marketkurlyclone.model.Member;
import com.weekclone.marketkurlyclone.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;



    public ResponseDto<?> getMemberInfo(String memberId, HttpServletRequest request)
    {
        Member member= getMemberfromContext();
        if(member==null)
        {
            //여기 예외처리 global로 만들 생각중
        }
        //권한 확인을 어떻게 할지, 이 부분 확인 필요, 아마 SECURED로 처리하거나 security config에 바로 박아버릴지도
        Member findmember= findPresentMember(memberId);


        MemberResponseDto memberResponseDto= new MemberResponseDto(findmember);
        return ResponseDto.is_Success(memberResponseDto);
    }

    ////////////////이거 수정 필요
    public ResponseDto<?> changeMemberAuthority(String memberId,Long authority,HttpServletRequest request)
    {
        Member member= getMemberfromContext();
        if(member==null)
        {
            //여기 예외처리
        }
        Member findmember=findPresentMember(memberId);
        if(authority==1)
        {
            findmember.updateAuthor(Authority.ROLE_USER);
        } else if (authority==2) {
            findmember.updateAuthor(Authority.ROLE_MANAGER);
        }

        return ResponseDto.is_Success("권한 변경 완료");
    }



    /*@Transactional
    public Member validateMember(HttpServletRequest request) {
        System.out.println("2-1");
        if (!tokenProvider.validateToken(request.getHeader("Refresh-Token"))) {
            System.out.println("2-2");
            return null;
        }
        System.out.println("2-3");
        return tokenProvider.getMemberFromAuthentication();
    }*/

    public Member findPresentMember(String memberId)
    {
        Optional<Member> optionalMember=memberRepository.findByMemberId(memberId);
        return optionalMember.orElse(null);
    }

    //여기서 컨텍스트 홀더에 저장된 username을 가져오는데
    //난 아까 member.getId()를 User객체로 만들어서 authentication으로 넣었기 때문에
    //컨텍스트 홀더에는 member의 id가 저장되어있고 우린 그걸 이용해서 멤버 객체를 찾는 것이다.
    @Transactional
    public Member getMemberfromContext() {
        String userId = SecurityContextHolder.getContext().getAuthentication().getName();

        Optional<Member> member = memberRepository.findById(Long.valueOf(userId));  //Long.valueOf(userId)
        return member.get();
    }


}
