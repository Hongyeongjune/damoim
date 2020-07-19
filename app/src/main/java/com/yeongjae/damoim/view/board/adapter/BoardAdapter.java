package com.yeongjae.damoim.view.board.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.yeongjae.damoim.data.Board;
import com.yeongjae.damoim.listener.OnBasicItemClickListener;
import com.yeongjae.damoim.listener.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class BoardAdapter extends RecyclerView.Adapter<BoardViewHolder> implements BoardAdapterContract.Model, BoardAdapterContract.View{

    private Context context;
    private List<Board> boards = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private OnLoadMoreListener onLoadMoreListener;
    private OnBasicItemClickListener onBasicItemClickListener;

    private boolean isMoreLoading = false;
    private boolean isLast = false;

    public BoardAdapter(Context context, OnLoadMoreListener onLoadMoreListener) {
        this.context = context;
        this.onLoadMoreListener = onLoadMoreListener;
    }

    public void setLinearLayoutManager(LinearLayoutManager linearLayoutManager) {
        this.linearLayoutManager = linearLayoutManager;
    }

    public void setOnBasicItemClickListener(OnBasicItemClickListener onBasicItemClickListener) {
        this.onBasicItemClickListener = onBasicItemClickListener;
    }


    public void setRecyclerView(RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if(isVisibleLastItem(recyclerView)) {
                    if(onLoadMoreListener != null) {
                        onLoadMoreListener.onLoadMore();
                    }
                    isMoreLoading = true;
                }
            }
        });
    }

    private boolean isVisibleLastItem(RecyclerView recyclerView) {
        return (!isLast && !isMoreLoading && (
                linearLayoutManager.getItemCount() - recyclerView.getChildCount())
                <= (linearLayoutManager.findFirstVisibleItemPosition() + 1));
    }

    @Override
    public Board getItem(int position) {
        return boards.get(position);
    }

    @Override
    public void addItems(List<Board> boards) {
        this.boards.addAll(boards);
    }

    @Override
    public int getItemCount() {
        return boards != null ? boards.size() : 0;
    }

    @Override
    public void setIsLast(boolean isLast) {
        this.isLast = isLast;
    }

    @Override
    public void setMoreLoading(boolean isMoreLoading) {
        this.isMoreLoading = isMoreLoading;
    }

    @NonNull
    @Override
    public BoardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BoardViewHolder(context, parent, onBasicItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull BoardViewHolder holder, int position) {
        if(holder == null) return;
    }


    @Override
    public void notifyAdapter() {
        notifyDataSetChanged();
    }
}
