package com.yeongjae.damoim.view.trade.adapter;

import com.yeongjae.damoim.data.People;
import com.yeongjae.damoim.data.Trade;

import java.util.List;

public interface TradeAdapterContract {

    interface View {
        void notifyAdapter();
    }

    interface Model {
        Trade getItem(int position);
        void addItems(List<Trade> trades);
        void setIsLast(boolean isLast);
        void setMoreLoading(boolean isMoreLoading);
    }

}
