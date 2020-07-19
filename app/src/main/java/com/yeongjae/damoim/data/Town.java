package com.yeongjae.damoim.data;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Town {

    private Long id;
    private String title;
    private String content;
    private String writer;
    private String regTime;

    @Builder
    public Town(Long id, String title, String content, String writer, String regTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.regTime = regTime;
    }
}