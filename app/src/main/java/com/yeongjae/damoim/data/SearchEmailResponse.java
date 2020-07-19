package com.yeongjae.damoim.data;

import java.util.function.Predicate;
import java.util.stream.Stream;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SearchEmailResponse {

    private String email;

    @Builder
    public SearchEmailResponse(String email) {
        this.email = email;
    }
}
