package com.example.a92828.movieproject.hot.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {


    private ArrayList<String> title= new ArrayList<>();

    private List<Fragment> listfragment;

    public ViewPagerAdapter(FragmentManager fragmentManager, List<Fragment> listfragment) {
        super(fragmentManager);
        this.listfragment = listfragment;
    }

    /**
     * 根据位置返回对应的fragment
     * @param position
     * @return
     */

    @Override
    public Fragment getItem(int position) {
        return listfragment.get(position);
    }

    @Override
    public int getCount() {
        return listfragment.size();
    }

    /**
     * 得到页面的标题
     * @param position
     * @return
     */
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        title.add("近期热映");
        title.add("即将上映");
        return title.get(position);
    }
}
