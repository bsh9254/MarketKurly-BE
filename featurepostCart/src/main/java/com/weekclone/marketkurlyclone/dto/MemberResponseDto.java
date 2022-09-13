package com.weekclone.marketkurlyclone.dto;

import com.weekclone.marketkurlyclone.model.Authority;
import com.weekclone.marketkurlyclone.model.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberResponseDto {

        private Long id;
        private String memberId;
        private String password;
        private String name;
        private String email;
        private String phone_number;
        private String address;
        private String address_detail;
        private String gender;
        private String birth;

        private Authority authority;
        private boolean policy;
        private String invite_user;

        public MemberResponseDto(Member member)
        {
            this.id=member.getId();
            this.memberId=member.getMemberId();
            this.name=member.getName();
            this.email=member.getEmail();
            this.phone_number=member.getPhone_number();
            this.address=member.getAddress();
            this.address_detail= member.getAddress_detail();;
            this.gender=member.getGender();
            this.birth=member.getBirth();
            this.authority=member.getAuthority();
            this.policy= member.isPolicy();
            this.invite_user= member.getInvite_user();
        }
        public MemberResponseDto(String memberId){
            this.memberId=memberId;
        }


        public static MemberResponseDto of(Member member) {
            return new MemberResponseDto(member.getMemberId());
        }
}
