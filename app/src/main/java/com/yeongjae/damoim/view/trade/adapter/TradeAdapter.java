package com.yeongjae.damoim.view.trade.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.yeongjae.damoim.data.Trade;
import com.yeongjae.damoim.listener.OnBasicItemClickListener;
import com.yeongjae.damoim.listener.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TradeAdapter extends RecyclerView.Adapter<TradeViewHolder> implements TradeAdapterContract.Model, TradeAdapterContract.View {

    private Context context;
    private List<Trade> trades = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private OnLoadMoreListener onLoadMoreListener;
    private OnBasicItemClickListener onBasicItemClickListener;

    private boolean isMoreLoading = false;
    private boolean isLast = false;

    public TradeAdapter(Context context, OnLoadMoreListener onLoadMoreListener) {
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
    public TradeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TradeViewHolder(context, parent, onBasicItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TradeViewHolder holder, int position) {
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
    public Trade getItem(int position) {
        return trades.get(position);
    }

    @Override
    public void addItems(List<Trade> trades) {
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
