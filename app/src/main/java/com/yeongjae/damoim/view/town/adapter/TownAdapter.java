package com.yeongjae.damoim.view.town.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.yeongjae.damoim.data.Town;
import com.yeongjae.damoim.listener.OnBasicItemClickListener;
import com.yeongjae.damoim.listener.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TownAdapter extends RecyclerView.Adapter<TownViewHolder> implements TownAdapterContract.Model, TownAdapterContract.View {

    private Context context;
    private List<Town> towns = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private OnLoadMoreListener onLoadMoreListener;
    private OnBasicItemClickListener onBasicItemClickListener;

    private boolean isMoreLoading = false;
    private boolean isLast = false;

    public TownAdapter(Context context, OnLoadMoreListener onLoadMoreListener) {
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

    @NonNull
    @Override
    public TownViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TownViewHolder(context, parent, onBasicItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TownViewHolder holder, int position) {
        if(holder == null) return;
    }

    @Override
    public int getItemCount() {
        return towns != null ? towns.size() : 0;
    }

    @Override
    public void notifyAdapter() {
        notifyDataSetChanged();
    }

    @Override
    public Town getItem(int position) {
        return towns.get(position);
    }

    @Override
    public void addItems(List<Town> towns) {
        this.towns.addAll(towns);
    }

    @Override
    public void setIsLast(boolean isLast) {
        this.isLast = isLast;
    }

    @Override
    public void setMoreLoading(boolean isMoreLoading) {
        this.isMoreLoading = isMoreLoading;
    }
}
