package com.example.a92828.movieproject;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.a92828.movieproject.hot.HotFragment;
import com.example.a92828.movieproject.find.FindFragment;
import com.example.a92828.movieproject.center.UserFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jzvd.Jzvd;

public class MainActivity extends FragmentActivity {
    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;
    @BindView(R.id.rb_hot)
    RadioButton rbHot;
    @BindView(R.id.rb_find)
    RadioButton rbFind;
    @BindView(R.id.rb_video)
    RadioButton rbVideo;
    @BindView(R.id.rg_main)
    RadioGroup rgMain;
    /**
     * 装多个fragment的一个实例集合
     */
    private ArrayList<BaseFragment> fragments;

    private UserFragment homeFragment;
    private FindFragment dashboardFragment;
    private HotFragment noticeFragment;


    private static final String HOME_FRAGMENT_KEY = "homeFragment";
    private static final String DASHBOARD_FRAGMENT_KEY = "DashboardFragment";
    private static final String NOTICE_FRAGMENT_KEY = "NoticeFragment";


    private int position = 0;
    /**
     * 上次显示的fragment
     */
    private static Fragment tempFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ButtonKnife和当前Activity绑定
        ButterKnife.bind(this);
        /**
         * 初始化fragment
         */
        initFragment();

        //设置RadioGroup监听
        initListener();


    }


    @SuppressLint("MissingSuperCall")
    @Override
    protected void onSaveInstanceState(Bundle outState) {
       // super.onSaveInstanceState(outState);
    }



    private void initListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_hot:  //发现
                        position = 0;
                        break;
                    case R.id.rb_find:   //购物车
                        position = 1;
                        break;
                    case R.id.rb_video:   //用户中心
                        position = 2;
                        break;
                    default:
                        position = 0;
                        break;
                }
                //根据位置去取不同的fragment
                BaseFragment baseFragment = getFragment(position);
                /**
                 * 第一个参数：上次显示的fragment
                 * 第二个参数：当前正要显示的Fragment
                 */
                switchFragment(tempFragment, baseFragment);
            }
        });
        rgMain.check(R.id.rb_hot);
    }

    /**
     * 添加的时候要按照顺序
     */
    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new HotFragment());
        fragments.add(new FindFragment());
        fragments.add(new UserFragment());
    }

    private BaseFragment getFragment(int position) {
        if (fragments != null && fragments.size() > 0) {
            BaseFragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;
    }
    /**
     * 切换fragment
     *
     * @param fromFragment
     * @param nextFragment
     */
    public void switchFragment(Fragment fromFragment, BaseFragment nextFragment) {
        if (tempFragment != nextFragment) {
            tempFragment = nextFragment;
            if (nextFragment != null) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                // 判断 nextFragment 是否添加
                if (!nextFragment.isAdded()) {                 // 隐藏当前 Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    //添加fragment
                    transaction.add(R.id.frameLayout, nextFragment,"home").commit();
                } else {
                    // 隐藏当前 Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }
    }
}