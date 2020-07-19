package com.yeongjae.damoim.view.board;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.yeongjae.damoim.R;
import com.yeongjae.damoim.listener.OnLoadMoreListener;
import com.yeongjae.damoim.otto.BusProvider;
import com.yeongjae.damoim.view.board.adapter.BoardAdapter;
import com.yeongjae.damoim.view.board.presenter.BoardContract;
import com.yeongjae.damoim.view.boardcreate.BoardCreateActivity;

public class BoardFragment extends Fragment implements OnLoadMoreListener, BoardContract.View {

    private Context context;
    private RecyclerView recyclerView;
    private BoardAdapter boardAdapter;
    private FloatingActionButton fap;

    public static BoardFragment createFragment() {
        BoardFragment boardFragment = new BoardFragment();
        return boardFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = (ViewGroup) inflater.inflate(R.layout.fragment_board, container, false);
        init(view);

        return view;
    }

    private void init(View view) {
        context = view.getContext();
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_boards);
        fap = (FloatingActionButton) view.findViewById(R.id.fap_board);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        boardAdapter = new BoardAdapter(context, this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(
                new DividerItemDecoration(
                        context, linearLayoutManager.getOrientation()
        ));
        boardAdapter.setLinearLayoutManager(linearLayoutManager);
        boardAdapter.setRecyclerView(recyclerView);
        recyclerView.setAdapter(boardAdapter);

        fap.setOnClickListener(v -> startBoardCreateActivity());
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BusProvider.getInstance().register(this);
    }

    @Override
    public void onDestroyView() {
        BusProvider.getInstance().unregister(this);
        super.onDestroyView();
    }

    @Override
    public void startBoardCreateActivity() {
        Intent intent = new Intent(getActivity(), BoardCreateActivity.class);
        getActivity().startActivity(intent);
    }
}
