package com.weekclone.marketkurlyclone.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.weekclone.marketkurlyclone.oauth.dto.GoogleUser;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Entity
@Builder
@AllArgsConstructor
public class Member extends Timestamped {

    /*@Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(nullable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    private UUID uuid;*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String memberId;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String phone_number;

    @Column
    private String address;

    @Column
    private String address_detail;

    @Column
    private String gender;

    @Column
    private String birth;

    @Column
    private boolean policy;

    @Column
    private String invite_user;

    @Enumerated(EnumType.STRING)
    private Authority authority;


    public void updateAuthor(Authority authority)
    {
        this.authority=authority;

    }

    public Member(String memberId, String password, String name, String email, String phone_number, String address,
                  String address_detail, String gender, String birth, boolean policy, String invite_user, Authority authority) {

        this.memberId = memberId;

        this.password = password;
        this.name = name;
        this.email = email;
        this.phone_number = phone_number;
        this.address = address;
        this.address_detail = address_detail;
        this.gender = gender;
        this.birth = birth;
        this.policy = policy;
        this.invite_user = invite_user;
        this.authority = authority;
    }


    private String provider;// oauth2??? ????????? ?????? ?????? ???????????? ???????????????
    private String providerId;// oauth2??? ????????? ?????? ????????????

    public Member(GoogleUser googleUser)
    {
        this.memberId=googleUser.getName();
        this.email=googleUser.getEmail();
        this.password="googlelogin";
        this.provider="Google";
    }

    @Builder(builderClassName = "OAuth2Register", builderMethodName = "oauth2Register")
    public Member(String memberId, String password, String email, Authority authority, String provider, String providerId) {
        this.memberId = memberId;
        this.password = password;
        this.email = email;
        this.authority = authority;
        this.provider = provider;
        this.providerId = providerId;
    }
}