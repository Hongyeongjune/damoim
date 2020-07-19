package com.yeongjae.damoim.view.people;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.yeongjae.damoim.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class PeopleFragment extends Fragment implements OnMapReadyCallback {

    private Context context;
    private GoogleMap googleMap;
    private TextView tvPeopleMap;
    private TextView tvPeopleBoard;
    private LinearLayout llPeopleMap;
    private LinearLayout llPeopleBoard;

    public static PeopleFragment createFragment() {
        PeopleFragment peopleFragment = new PeopleFragment();
        return peopleFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = (ViewGroup) inflater.inflate(R.layout.fragment_people, container, false);
        init(view);

        return view;
    }

    private void init(View view) {
        context = view.getContext();
        tvPeopleMap = (TextView) view.findViewById(R.id.tv_people_map);
        tvPeopleBoard = (TextView) view.findViewById(R.id.tv_people_board);
        llPeopleMap = (LinearLayout) view.findViewById(R.id.ll_people_map);
        llPeopleBoard = (LinearLayout) view.findViewById(R.id.ll_people_board);

        tvPeopleMap.setOnClickListener(v -> {
            llPeopleMap.setVisibility(View.VISIBLE);
            llPeopleBoard.setVisibility(View.INVISIBLE);
        });

        tvPeopleBoard.setOnClickListener(v -> {
            llPeopleMap.setVisibility(View.INVISIBLE);
            llPeopleBoard.setVisibility(View.VISIBLE);
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
    }
}
