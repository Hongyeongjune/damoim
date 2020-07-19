package com.yeongjae.damoim.view.town.adapter;

import com.yeongjae.damoim.data.Town;
import com.yeongjae.damoim.data.Trade;

import java.util.List;

public interface TownAdapterContract {

    interface View {
        void notifyAdapter();
    }

    interface Model {
        Town getItem(int position);
        void addItems(List<Town> trades);
        void setIsLast(boolean isLast);
        void setMoreLoading(boolean isMoreLoading);
    }

}
