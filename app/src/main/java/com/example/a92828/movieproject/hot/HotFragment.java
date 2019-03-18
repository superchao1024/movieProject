package com.example.a92828.movieproject.hot;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.example.a92828.movieproject.R;
import com.example.a92828.movieproject.BaseFragment;
import com.example.a92828.movieproject.SearchActivity;
import com.example.a92828.movieproject.hot.adapter.ViewPagerAdapter;
import com.example.a92828.movieproject.hot.fragment.ShowingFragment;
import com.example.a92828.movieproject.hot.fragment.ComingFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 发现的fragment
 */

public class HotFragment extends BaseFragment {
    private TabLayout tablayout;
    private ViewPager viewPager;
    ViewPagerAdapter adapter;
    //数据源

    private List<Fragment> listfragment;
    private ShowingFragment f1;
    private ComingFragment f2;
    private TextView tv_search_home;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_hot, null);
        tablayout = (TabLayout) view.findViewById(R.id.tablayout);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        tv_search_home = view.findViewById(R.id.tv_search_home);
        f1 = new ShowingFragment();
        f2 = new ComingFragment();

        tv_search_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),SearchActivity.class);
                startActivity(intent);

            }
        });

        return view;
    }
    @Override
    public void initData() {
        super.initData();
        listfragment = new ArrayList<>();

        listfragment.add(f1);
        listfragment.add(f2);
        //初始化数据

        //设置Viewpager的适配器
        adapter = new ViewPagerAdapter(getFragmentManager(),listfragment);
        viewPager.setAdapter(adapter);
        //关联viewPager
        tablayout.setupWithViewPager(viewPager);
       // tablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tablayout.setTabMode(TabLayout.MODE_FIXED);
    }

    }

