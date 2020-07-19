package com.yeongjae.damoim.view.people.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yeongjae.damoim.R;
import com.yeongjae.damoim.data.People;
import com.yeongjae.damoim.listener.OnBasicItemClickListener;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PeopleViewHolder extends RecyclerView.ViewHolder {
    
    private Context context;
    private TextView tvPeopleType;
    private TextView tvPeopleTitle;
    private TextView tvPeopleWriter;
    private TextView tvPeopleDate;
    private OnBasicItemClickListener onBasicItemClickListener;

    public PeopleViewHolder(Context context, ViewGroup parent, OnBasicItemClickListener onBasicItemClickListener) {
        super(LayoutInflater.from(context).inflate(R.layout.item_people, parent, false));
        this.context = context;
        this.tvPeopleType = itemView.findViewById(R.id.tv_people_type);
        this.tvPeopleTitle = itemView.findViewById(R.id.tv_people_title);
        this.tvPeopleWriter = itemView.findViewById(R.id.tv_people_writer);
        this.tvPeopleDate = itemView.findViewById(R.id.tv_people_date);
        this.onBasicItemClickListener = onBasicItemClickListener;
    }

    public void onBind(People people, int position) {
        tvPeopleType.setText(people.getId() + "");
        tvPeopleTitle.setText(people.getTitle());
        tvPeopleWriter.setText(people.getWriter());
        tvPeopleDate.setText(people.getRegTime());

        itemView.setOnClickListener(view -> {
            onBasicItemClickListener.onStartItemClick(position);
        });
    }
}
