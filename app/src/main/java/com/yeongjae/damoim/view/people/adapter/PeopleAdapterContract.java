package com.yeongjae.damoim.view.people.adapter;

import com.yeongjae.damoim.data.Board;
import com.yeongjae.damoim.data.People;

import java.util.List;

public interface PeopleAdapterContract {

    interface View {
        void notifyAdapter();
    }

    interface Model {
        People getItem(int position);
        void addItems(List<People> peopleList);
        void setIsLast(boolean isLast);
        void setMoreLoading(boolean isMoreLoading);
    }

}
