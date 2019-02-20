package com.example.a92828.movieproject.home;


import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.a92828.movieproject.BaseFragment;


/**
 * 主页面的fragment
 */


public class HomeFragment extends BaseFragment {

    private TextView textView;

    @Override
    public View initView() {
        Log.e("HomeFragment", "主页Fragment的UI被初始化了");
        textView = new TextView(mContext);
        textView.setText("我是主页内容");
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e("HomeFragment","主页Fragment的数据被初始化了");

    }
}

