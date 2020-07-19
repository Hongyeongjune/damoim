package com.yeongjae.damoim.view.town;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
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

import com.yeongjae.damoim.R;
import com.yeongjae.damoim.listener.OnLoadMoreListener;
import com.yeongjae.damoim.otto.BusProvider;
import com.yeongjae.damoim.view.town.TownFragment;
import com.yeongjae.damoim.view.town.adapter.TownAdapter;

public class TownFragment  extends Fragment implements OnLoadMoreListener {

    private Context context;
    private RecyclerView recyclerView;
    private TownAdapter townAdapter;

    public static TownFragment createFragment() {
        TownFragment townFragment = new TownFragment();
        return townFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = (ViewGroup) inflater.inflate(R.layout.fragment_town, container, false);
        init(view);

        return view;
    }

    private void init(View view) {
        context = view.getContext();
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_towns);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        townAdapter = new TownAdapter(context, this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(
                context, linearLayoutManager.getOrientation()
        ));
        townAdapter.setLinearLayoutManager(linearLayoutManager);
        townAdapter.setRecyclerView(recyclerView);
        recyclerView.setAdapter(townAdapter);
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
}
