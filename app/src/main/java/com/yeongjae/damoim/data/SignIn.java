package com.yeongjae.damoim.data;

import java.util.function.Predicate;
import java.util.stream.Stream;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SignIn {

    private String email;
    private String password;
    private String fcmToken;

    @Builder
    public SignIn(String email, String password, String fcmToken) {
        this.email = email;
        this.fcmToken = fcmToken;
        this.password = password;
    }

    /**
     * email, password 가 빈 문자열인지 검사
     */
    public boolean isAllNonNull() {
        long count =  Stream.of(email, password, fcmToken)
                .filter(data -> !data.isEmpty())
                .count();
        Predicate<Long> isAllNonNull = cnt -> cnt == 3;
        return isAllNonNull.test(count);
    }

}
