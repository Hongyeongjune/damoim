package com.yeongjae.damoim.view.tradecreate.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.yeongjae.damoim.data.Trade;
import com.yeongjae.damoim.data.TradeImage;
import com.yeongjae.damoim.listener.OnBasicItemClickListener;
import com.yeongjae.damoim.listener.OnLoadMoreListener;
import com.yeongjae.damoim.view.trade.adapter.TradeViewHolder;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TradeCreateAdapter extends RecyclerView.Adapter<TradeCreateViewHolder> implements TradeCreateAdapterContract.Model, TradeCreateAdapterContract.View {
    private Context context;
    private List<TradeImage> trades = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private OnLoadMoreListener onLoadMoreListener;
    private OnBasicItemClickListener onBasicItemClickListener;

    private boolean isMoreLoading = false;
    private boolean isLast = false;

    public TradeCreateAdapter(Context context, OnLoadMoreListener onLoadMoreListener) {
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
    public TradeCreateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TradeCreateViewHolder(context, parent, onBasicItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TradeCreateViewHolder holder, int position) {
        if(holder == null) return;
    }

    @Override
    public int getItemCount() {
        return trades != null ? trades.size() : 0;
    }

    @Override
    public void notifyAdapter() {
        notifyDataSetChanged();
    }

    @Override
    public TradeImage getItem(int position) {
        return trades.get(position);
    }

    @Override
    public void addItems(List<TradeImage> trades) {
        this.trades.addAll(trades);
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
