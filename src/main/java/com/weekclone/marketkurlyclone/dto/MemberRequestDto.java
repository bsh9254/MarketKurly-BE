package com.weekclone.marketkurlyclone.dto;

import com.weekclone.marketkurlyclone.model.Authority;
import com.weekclone.marketkurlyclone.model.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberRequestDto {

    private String memberId;

    private String password;

    private String passwordConfirm;

    private String name;

    private String email;

    private String phone_number;

    private String address;

    private String address_detail;

    private String gender;

    private String birth;

    private Boolean policy;

    private String invite_user;

    public Member toMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .memberId(memberId)
                .password(passwordEncoder.encode(password))
                .name(name)
                .email(email)
                .phone_number(phone_number)
                .address(address)
                .address_detail(address_detail)
                .gender(gender)
                .birth(birth)
                .policy(policy)
                .invite_user(invite_user)
                .authority(Authority.ROLE_USER)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(memberId, password);
    }
}