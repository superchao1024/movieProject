package com.example.a92828.movieproject.find;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.a92828.movieproject.BaseFragment;
import com.example.a92828.movieproject.R;
import com.example.a92828.movieproject.YuGaoActivity;
import com.example.a92828.movieproject.bean.KouBeiBean;
import com.example.a92828.movieproject.find.Adapter.FindFragmentAdapter;
import com.example.a92828.movieproject.find.Adapter.MyRecyclerViewkoubeiOneAdapter;
import com.example.a92828.movieproject.find.Adapter.MyRecyclerViewkoubeiTwoAdapter;
import com.example.a92828.movieproject.utils.Constants;
import com.example.a92828.movieproject.utils.LinearLayoutManagerWrapper;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import java.util.List;
import okhttp3.Call;

/**
 * 购物车的fragment
 */

public class FindFragment extends BaseFragment {

    protected static final int WHAT_REQUEST_SUCCESS_KOUBEI_V1 =0;
    protected static final int WHAT_REQUEST_ERROR = 1;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case WHAT_REQUEST_SUCCESS_KOUBEI_V1:

                    recyclerview_find.setAdapter(findadapter);
                    break;
                case WHAT_REQUEST_ERROR:
                    Toast.makeText(getActivity(), "加载数据失败", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };
    private KouBeiBean testJson;
    private List<KouBeiBean.SubjectsBean> subjects;
    private Button bt;
    private RecyclerView recyclerview_find;
    private FindFragmentAdapter findadapter;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_find, null);
        recyclerview_find = view.findViewById(R.id.recyclerview_find);
        //initData();
        return view;
    }


    @Override
    public void initData() {
        new Thread() {
            @Override
            public void run() {
                //联网请求得到json字符串
                try {
                    getOkhttpGet();

                } catch (Exception e) {
                    e.printStackTrace();
                    handler.sendEmptyMessage(WHAT_REQUEST_ERROR);//发送请求失败的消息
                }
            }
        }.start();
    }


    /**
     * 得到json数据（Top250）
     */

    private void getOkhttpGet() {
        String url = Constants.KOUBEI;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback()
                {
                    /**
                     * 当请求失败的时候回调
                     * @param call
                     * @param e
                     * @param id
                     */
                    @Override
                    public void onError(Call call, Exception e, int id) {

                        Log.e("TAG","首页请求失败=="+e.getMessage());
                    }

                    /**
                     * 当联网成功的时候回调
                     * @param response  请求成功的数据
                     * @param id
                     */
                    @Override
                    public void onResponse(String response, int id) {

                        Log.d("TAB","首页请求成功=="+response);
                        Gson gson = new Gson();

                        testJson = gson.fromJson(response, KouBeiBean.class);
                        subjects = testJson.getSubjects();
                        if (testJson!=null){
                            //有数据
                            findadapter = new FindFragmentAdapter(mContext,subjects);
                            handler.sendEmptyMessage(WHAT_REQUEST_SUCCESS_KOUBEI_V1);//发送请求成功的消息
                            recyclerview_find.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
                        }else {
                            //没有数据
                            Log.d("TAB","首页请求失败=="+response);
                        }

                   }
                });
    }
}
