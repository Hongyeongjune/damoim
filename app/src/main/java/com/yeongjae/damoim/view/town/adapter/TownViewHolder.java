package com.yeongjae.damoim.view.town.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yeongjae.damoim.R;
import com.yeongjae.damoim.data.Town;
import com.yeongjae.damoim.listener.OnBasicItemClickListener;

import androidx.recyclerview.widget.RecyclerView;

public class TownViewHolder extends RecyclerView.ViewHolder {

    private Context context;
    private TextView tvTownType;
    private TextView tvTownTitle;
    private TextView tvTownWriter;
    private TextView tvTownDate;
    private OnBasicItemClickListener onBasicItemClickListener;

    public TownViewHolder(Context context, ViewGroup parent, OnBasicItemClickListener onBasicItemClickListener) {
        super(LayoutInflater.from(context).inflate(R.layout.item_towns, parent, false));
        this.context = context;
        this.tvTownType = itemView.findViewById(R.id.tv_town_type);
        this.tvTownTitle = itemView.findViewById(R.id.tv_town_title);
        this.tvTownWriter = itemView.findViewById(R.id.tv_town_writer);
        this.tvTownDate = itemView.findViewById(R.id.tv_town_date);
        this.onBasicItemClickListener = onBasicItemClickListener;
    }

    public void onBind(Town town, int position) {
        tvTownType.setText(town.getId() + "");
        tvTownTitle.setText(town.getTitle());
        tvTownWriter.setText(town.getWriter());
        tvTownDate.setText(town.getRegTime());

        itemView.setOnClickListener(view -> {
            onBasicItemClickListener.onStartItemClick(position);
        });
    }
}
