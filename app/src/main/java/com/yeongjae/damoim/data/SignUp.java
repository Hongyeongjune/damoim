package com.yeongjae.damoim.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.function.Predicate;
import java.util.stream.Stream;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUp implements Serializable {

    private String email;
    private String password;
    private String location;
    private String nickName;
    private String phone;
    private String sex;
    private String birth;
    private Boolean isVerified;
    private String role;
//    private String fcmToken;

    @Builder
    public SignUp(String email, String password, String location,
                  String nickName, String phone, String sex, String birth, Boolean isVerified, String role) {
        this.email = email;
        this.password = password;
        this.location = location;
        this.nickName = nickName;
        this.phone = phone;
        this.sex = sex;
        this.birth = birth;
        this.isVerified = isVerified;
        this.role = role;
//        this.fcmToken = fcmToken;
    }

    public boolean isAllNonNull() {
        long count =  Stream.of(email, password, nickName, phone, sex, birth, isVerified.toString(), role)
                .filter(data -> !data.isEmpty())
                .count();
        Predicate<Long> isAllNonNull = cnt -> cnt == 8;
        return isAllNonNull.test(count);
    }

    public static boolean isSignUpDataNonNull(String signUpData) {
        long count =  Stream.of(signUpData)
                .filter(data -> !data.isEmpty())
                .count();
        Predicate<Long> isAllNonNull = cnt -> cnt == 1;
        return isAllNonNull.test(count);
    }


}
