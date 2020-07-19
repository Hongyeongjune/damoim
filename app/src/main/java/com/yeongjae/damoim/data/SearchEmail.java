package com.yeongjae.damoim.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.function.Predicate;
import java.util.stream.Stream;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SearchEmail implements Serializable {

    private String phone;

    @Builder
    public SearchEmail(String phone) {
        this.phone = phone;
    }

    public boolean isAllNonNull() {
        long count =  Stream.of(phone)
                .filter(data -> !data.isEmpty())
                .count();
        Predicate<Long> isAllNonNull = cnt -> cnt == 1;
        return isAllNonNull.test(count);
    }

    /**
     * phone 이 빈 문자열인지 검사
     */
    public static boolean isSearchEmailDataNull(String phone) {
        long count =  Stream.of(phone)
                .filter(data -> !data.isEmpty())
                .count();
        Predicate<Long> isAllNonNull = cnt -> cnt == 1;
        return isAllNonNull.test(count);
    }
}
