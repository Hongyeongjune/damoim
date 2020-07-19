package com.yeongjae.damoim.data;

import android.net.Uri;

import java.net.URI;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Trade {

    private Long id;
    private Uri image;
    private String title;
    private String content;
    private String price;
    private String writer;
    private String regTime;

    @Builder
    public Trade(Long id, Uri image, String title, String content, String price, String writer, String regTime) {
        this.image = image;
        this.id = id;
        this.title = title;
        this.content = content;
        this.price = price;
        this.writer = writer;
        this.regTime = regTime;
    }
}
