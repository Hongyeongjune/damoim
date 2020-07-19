package com.yeongjae.damoim.view.board.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yeongjae.damoim.R;
import com.yeongjae.damoim.data.Board;
import com.yeongjae.damoim.listener.OnBasicItemClickListener;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BoardViewHolder extends RecyclerView.ViewHolder {

    private Context context;
    private TextView tvBoardType;
    private TextView tvBoardTitle;
    private TextView tvBoardWriter;
    private TextView tvBoardDate;
    private OnBasicItemClickListener onBasicItemClickListener;

    public BoardViewHolder(Context context, ViewGroup parent, OnBasicItemClickListener onBasicItemClickListener) {
        super(LayoutInflater.from(context).inflate(R.layout.item_boards, parent, false));
        this.context = context;
        this.tvBoardType = itemView.findViewById(R.id.tv_board_type);
        this.tvBoardTitle = itemView.findViewById(R.id.tv_board_title);
        this.tvBoardWriter = itemView.findViewById(R.id.tv_board_writer);
        this.tvBoardDate = itemView.findViewById(R.id.tv_board_date);
        this.onBasicItemClickListener = onBasicItemClickListener;
    }

    public void onBind(Board board, int position) {
        tvBoardType.setText(board.getId() + "");
        tvBoardTitle.setText(board.getTitle());
        tvBoardWriter.setText(board.getWriter());
        tvBoardDate.setText(board.getRegTime());

        itemView.setOnClickListener(view -> {
            onBasicItemClickListener.onStartItemClick(position);
        });
    }
}
