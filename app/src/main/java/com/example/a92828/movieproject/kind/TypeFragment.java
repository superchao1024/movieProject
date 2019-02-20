package com.example.a92828.movieproject.kind;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.a92828.movieproject.BaseFragment;

/**
 * 分类的fragment
 *
 *
 */

public class TypeFragment extends BaseFragment {

    private TextView textView;

    @Override
    public View initView() {
        Log.e("TypeFragment","分类Fragment的UI被初始化了");
        textView = new TextView(mContext);
        textView.setText("我是分类内容");
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        Log.e("TypeFragment","分类Fragment的数据被初始化了");
        textView.setText("我是分类内容");
    }
}
