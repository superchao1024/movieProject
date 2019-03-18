package com.example.a92828.movieproject.center;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.a92828.movieproject.BaseFragment;
import com.example.a92828.movieproject.R;
import com.example.a92828.movieproject.bean.PiaoFangBean;
import com.example.a92828.movieproject.utils.Constants;
import com.example.a92828.movieproject.utils.DividerListItemDecoration;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;


/**
 * 用户中心的fragment
 */

public class UserFragment extends BaseFragment {

    private RecyclerView rv_user;
    protected static final int WHAT_REQUEST_SUCCESS_KOUBEI_V1 =0;
    protected static final int WHAT_REQUEST_ERROR = 1;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case WHAT_REQUEST_SUCCESS_KOUBEI_V1:
                    //显示列表
                  rv_user.setAdapter(userFragmentAdapter);
                    break;
                case WHAT_REQUEST_ERROR:
                    Toast.makeText(mContext, "加载数据失败", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };
    private PiaoFangBean piaoFangBean;
    private List<PiaoFangBean.DataBean> data;
    private UserFragmentAdapter userFragmentAdapter;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_user, null);
        rv_user = view.findViewById(R.id.rv_user);
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
        String url = Constants.PIAOFANG;
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

                        piaoFangBean = gson.fromJson(response, PiaoFangBean.class);
                        data = piaoFangBean.getData();
                            //有数据
                            userFragmentAdapter = new UserFragmentAdapter(mContext, data);

                            handler.sendEmptyMessage(WHAT_REQUEST_SUCCESS_KOUBEI_V1);//发送请求成功的消息
                            rv_user.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
                        //    rv_user.addItemDecoration(new DividerListItemDecoration(getActivity(),DividerListItemDecoration.VERTICAL_LIST));

                    }
                });
    }
}