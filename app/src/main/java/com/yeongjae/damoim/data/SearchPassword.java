package com.yeongjae.damoim.data;

import java.util.function.Predicate;
import java.util.stream.Stream;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SearchPassword {

    private String phone;
    private String email;

    @Builder
    public SearchPassword(String phone, String email) {
            this.phone = phone;
            this.email = email;
    }

    /**
     * phone, email 이 빈 문자열인지 검사
     */
    public boolean isAllNonNull() {
        long count =  Stream.of(phone, email)
                .filter(data -> !data.isEmpty())
                .count();
        Predicate<Long> isAllNonNull = cnt -> cnt == 2;
        return isAllNonNull.test(count);

    }

    /**
     * phone 이 빈 문자열인지 검사
     */
    public static boolean isSearchPasswordDataNull(String phone) {
        long count =  Stream.of(phone)
                .filter(data -> !data.isEmpty())
                .count();
        Predicate<Long> isAllNonNull = cnt -> cnt == 1;
        return isAllNonNull.test(count);
    }

}
