package com.weekclone.marketkurlyclone.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Getter
@NoArgsConstructor

@Entity
public class Member extends Timestamped {

    @Id @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")  //컬럼타입을 BINARY(16)으로 하지 않으면 공백이 들어가며 원하는 동작이 이루어지지 않는다고 한다.
    private UUID uuid;                      //@Column 어노테이션을 꼭 사용할 필요는 없으며, DB의 컬럼 타입이 BINARY(16) 이면 된다.

    @Column(nullable = false, unique = true)
    private String user_id;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Column(nullable = false)
    private String phone_number;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String address_detail;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String birth;

    @Column(nullable = false)
    private boolean policy;

    @Column(nullable = false)
    private String invite_user;






    @Builder
    public Member(String username, String password, Authority authority) {
        this.user_id = username;
        this.password = password;
        this.authority = authority;

    }

    public void update(Authority authority)
    {
        this.authority=authority;

    }

    //private String provider;// oauth2를 이용할 경우 어떤 플랫폼을 이용하는지
    //private String providerId;// oauth2를 이용할 경우 아이디값
    /*public Member(GoogleUser googleUser)
    {
        this.username=googleUser.getName();
        this.email=googleUser.getEmail();
        this.password="googlelogin";
        this.provider="Google";
    }*/

    /*@Builder(builderClassName = "OAuth2Register", builderMethodName = "oauth2Register")
    public Member(String username, String password, String email, Authority authority, String provider, String providerId) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.authority = authority;
        this.provider = provider;
        this.providerId = providerId;
    }*/
}