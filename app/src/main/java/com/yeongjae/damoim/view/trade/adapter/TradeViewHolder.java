package com.yeongjae.damoim.view.trade.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yeongjae.damoim.R;
import com.yeongjae.damoim.data.Trade;
import com.yeongjae.damoim.listener.OnBasicItemClickListener;

import androidx.recyclerview.widget.RecyclerView;

public class TradeViewHolder extends RecyclerView.ViewHolder {
    
    private Context context;
    private ImageView ivTradeImage;
    private TextView tvTradeTitle;
    private TextView tvTradePrice;
    private TextView tvTradeDate;
    private OnBasicItemClickListener onBasicItemClickListener;

    public TradeViewHolder(Context context, ViewGroup parent, OnBasicItemClickListener onBasicItemClickListener) {
        super(LayoutInflater.from(context).inflate(R.layout.item_trade, parent, false));
        this.context = context;
        this.ivTradeImage = itemView.findViewById(R.id.iv_trade_image);
        this.tvTradeTitle = itemView.findViewById(R.id.tv_trade_title);
        this.tvTradePrice = itemView.findViewById(R.id.tv_trade_price);
        this.tvTradeDate = itemView.findViewById(R.id.tv_trade_date);
        this.onBasicItemClickListener = onBasicItemClickListener;
    }

    public void onBind(Trade trade, int position) {
        ivTradeImage.setImageURI(trade.getImage());
        tvTradeTitle.setText(trade.getTitle());
        tvTradePrice.setText(trade.getPrice());
        tvTradeDate.setText(trade.getRegTime());

        itemView.setOnClickListener(view -> {
            onBasicItemClickListener.onStartItemClick(position);
        });
    }
}
