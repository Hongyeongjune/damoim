package com.yeongjae.damoim.view.tradecreate.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yeongjae.damoim.R;
import com.yeongjae.damoim.data.TradeImage;
import com.yeongjae.damoim.listener.OnBasicItemClickListener;

import androidx.recyclerview.widget.RecyclerView;

public class TradeCreateViewHolder extends RecyclerView.ViewHolder {

    private Context context;
    private ImageView tvTradeImage;
    private OnBasicItemClickListener onBasicItemClickListener;

    public TradeCreateViewHolder(Context context, ViewGroup parent, OnBasicItemClickListener onBasicItemClickListener) {
        super(LayoutInflater.from(context).inflate(R.layout.item_trade_image, parent, false));
        this.context = context;
        this.tvTradeImage = itemView.findViewById(R.id.iv_trade_create_image);
        this.onBasicItemClickListener = onBasicItemClickListener;
    }

    public void onBind(TradeImage tradeImage, int position) {
        tvTradeImage.setImageURI(tradeImage.getTradeImagePath());

        itemView.setOnClickListener(view -> {
            onBasicItemClickListener.onStartItemClick(position);
        });
    }
    
}
