package com.yeongjae.damoim.data;

import android.net.Uri;

import java.net.URI;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TradeImage {

    private Uri tradeImagePath;

    @Builder
    public TradeImage(Uri tradeImagePath) {
        this.tradeImagePath = tradeImagePath;
    }

}
