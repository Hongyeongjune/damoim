package com.yeongjae.damoim.data;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VerificationCode implements Serializable {

    private String phone;
    private String verificationCode;
    private String email;
    private String type;

    @Builder
    public VerificationCode(String phone, String verificationCode, String email, String type) {
        this.phone = phone;
        this.verificationCode = verificationCode;
        this.type = type;
        this.email = email;
    }

}
