package com.yeongjae.damoim.view.board.adapter;

import com.yeongjae.damoim.data.Board;

import java.util.List;

public interface BoardAdapterContract {

    interface View {
        void notifyAdapter();
    }

    interface Model {
        Board getItem(int position);
        void addItems(List<Board> boardList);
        void setIsLast(boolean isLast);
        void setMoreLoading(boolean isMoreLoading);
    }
}
