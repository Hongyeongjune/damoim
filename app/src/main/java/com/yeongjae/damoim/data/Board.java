package com.yeongjae.damoim.data;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Board {

    private Long id;
    private String title;
    private String content;
    private String writer;
    private String regTime;

    @Builder
    public Board(Long id, String title, String content, String writer, String regTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.regTime = regTime;
    }
}
