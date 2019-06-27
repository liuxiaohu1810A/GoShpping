package com.example.app2.goshpping.fragments;


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


import com.example.app2.R;
import com.example.app2.goshpping.api.ApiServer;
import com.example.app2.goshpping.bean.DataBean;
import com.example.app2.goshpping.bean.User;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class AFragment extends Fragment {


    private View view;
    private RecyclerView mRlv;
    private ArrayList<DataBean> beans;
    private MyAdapter adapter;
    private int page=1;
    private TextView mTvPrice;
    private int money;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a, container, false);
        initView(view);
        initData();
        return view;
    }

    private static final String TAG = "AFragment";
    private void initData() {
        Retrofit retrofit = new Retrofit.Builder()
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(ApiServer.url)
                        .build();
                ApiServer apiServer = retrofit.create(ApiServer.class);
                Observable<User> java = apiServer.getRxJava(page);
                java.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<User>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(User user) {
                                List<DataBean> data = user.getData();
                                beans.addAll(data);
                                adapter.notifyDataSetChanged();
                                Log.d(TAG, "onNext: "+data);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
    }

    private void initView(View view) {
        mRlv = (RecyclerView) view.findViewById(R.id.rlv);
        mTvPrice = (TextView) view.findViewById(R.id.tv_price);
        mRlv.setLayoutManager(new LinearLayoutManager(getContext()));
        mRlv.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        beans = new ArrayList<>();
        mTvPrice.setText(0+"$");
        adapter = new MyAdapter(getContext(), beans);
        mRlv.setAdapter(adapter);
        adapter.setA(new MyAdapter.Lxh() {


            @Override
            public void onClick(View v, int position) {
                DataBean dataBean = beans.get(position);
                int num = dataBean.getNum();
                Log.d("价钱", "onClick: "+num);
                CheckBox cb = v.findViewById(R.id.cb);
                if (cb.isChecked()){
                    money+=num;
                    Log.d("总价", "onClick: "+money);
                }else{
                    money-=num;
                }
                if (money>=0){
                    mTvPrice.setText(money+"$");
                }
            }
        });
    }
}
