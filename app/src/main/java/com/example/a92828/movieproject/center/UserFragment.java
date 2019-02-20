package com.example.a92828.movieproject.center;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.a92828.movieproject.BaseFragment;

/**
 * 用户中心的fragment
 */

public class UserFragment extends BaseFragment {

    private TextView textView;

    @Override
    public View initView() {
        Log.e("UserFragment", "用户中心Fragment的UI被初始化了");
        textView = new TextView(mContext);
        textView.setText("我是用户中心内容");
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e("UserFragment", "用户中心Fragment的数据被初始化了");
        textView.setText("我是用户中心内容");
    }
}
