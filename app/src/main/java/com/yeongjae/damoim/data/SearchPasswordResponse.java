package com.yeongjae.damoim.data;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SearchPasswordResponse {

    private String password;

    @Builder
    public SearchPasswordResponse(String password) {
        this.password = password;
    }
}
