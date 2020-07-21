package com.yeongjae.damoim.view.tradecreate.adapter;

import com.yeongjae.damoim.data.TradeImage;

import java.util.List;

public interface TradeCreateAdapterContract {

    interface View {
        void notifyAdapter();
    }

    interface Model {
        TradeImage getItem(int position);
        void addItems(List<TradeImage> tradeImages);
        void setIsLast(boolean isLast);
        void setMoreLoading(boolean isMoreLoading);
    }
}
