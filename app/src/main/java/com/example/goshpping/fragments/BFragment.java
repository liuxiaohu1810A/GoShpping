package com.example.goshpping.fragments;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.goshpping.DBUtils.DBUtils;
import com.example.goshpping.R;
import com.example.goshpping.bean.DataBean;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BFragment extends Fragment {


    private View view;
    private RecyclerView mRlv;
    private ArrayList<DataBean> beans;
    private MyAdapter2 adapter;
    /**
     * 123
     */

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && adapter != null) {
            beans.clear();
            initData();
        }
    }

    private void initData() {
        List<DataBean> select = DBUtils.select();
        beans.addAll(select);
        adapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_b, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        mRlv = (RecyclerView) view.findViewById(R.id.rlv);
        mRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        mRlv.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        beans = new ArrayList<>();
        adapter = new MyAdapter2(getContext(), beans);
        mRlv.setAdapter(adapter);

    }
}
