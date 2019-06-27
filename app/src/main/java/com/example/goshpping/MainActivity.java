package com.example.goshpping;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.goshpping.api.ApiServer;
import com.example.goshpping.bean.User;
import com.example.goshpping.fragments.AFragment;
import com.example.goshpping.fragments.BFragment;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    /**
     * 首页
     */
    private TextView mTv;
    private ViewPager mVp;
    private TabLayout mTab;
    private Toolbar mTool;
    private int page=1;
    private ArrayList<Fragment> fm;
    private VpAdapter vpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initVape();
        initTab();
    }

    private void initVape() {
        fm = new ArrayList<>();
        fm.add(new AFragment());
        fm.add(new BFragment());
        vpAdapter = new VpAdapter(getSupportFragmentManager(), fm);
        mVp.setAdapter(vpAdapter);
    }

    private void initTab() {
        mTab.setupWithViewPager(mVp);
        mTab.getTabAt(0).setIcon(R.drawable.tab1).setText("首页");
        mTab.getTabAt(1).setIcon(R.drawable.tab2).setText("购物车");
        mTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mVp.setCurrentItem(tab.getPosition());
                mTv.setText(tab.getText());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    private void initView() {
        mTv = (TextView) findViewById(R.id.tv);
        mVp = (ViewPager) findViewById(R.id.vp);
        mTab = (TabLayout) findViewById(R.id.tab);
        mTool = (Toolbar) findViewById(R.id.tool);
        mTool.setTitle("");
        setSupportActionBar(mTool);
    }
}
