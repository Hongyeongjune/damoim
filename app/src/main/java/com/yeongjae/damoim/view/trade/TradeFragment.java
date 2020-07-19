package com.yeongjae.damoim.view.trade;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.yeongjae.damoim.R;
import com.yeongjae.damoim.listener.OnLoadMoreListener;
import com.yeongjae.damoim.otto.BusProvider;
import com.yeongjae.damoim.view.trade.adapter.TradeAdapter;
import com.yeongjae.damoim.view.trade.presenter.TradeContract;
import com.yeongjae.damoim.view.tradecreate.TradeCreateActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class TradeFragment extends Fragment implements OnLoadMoreListener, TradeContract.View {

    private Context context;
    private RecyclerView recyclerView;
    private TradeAdapter tradeAdapter;
    private FloatingActionButton fap;
    
    public static TradeFragment createFragment() {
        TradeFragment tradeFragment = new TradeFragment();
        return tradeFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = (ViewGroup) inflater.inflate(R.layout.fragment_trade, container, false);
        init(view);

        return view;
    }

    private void init(View view) {
        context = view.getContext();
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_trade);
        fap = (FloatingActionButton) view.findViewById(R.id.fap_trade);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        tradeAdapter = new TradeAdapter(context, this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(
                context, linearLayoutManager.getOrientation()
        ));
        tradeAdapter.setLinearLayoutManager(linearLayoutManager);
        tradeAdapter.setRecyclerView(recyclerView);
        recyclerView.setAdapter(tradeAdapter);

        fap.setOnClickListener(v -> startTradeCreateActivity());
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
    public void startTradeCreateActivity() {
        Intent intent = new Intent(getActivity(), TradeCreateActivity.class);
        getActivity().startActivity(intent);
    }
}
