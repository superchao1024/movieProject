package com.example.a92828.movieproject.hot.fragment;


import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.a92828.movieproject.R;
import com.example.a92828.movieproject.bean.ShowingBean;
import com.example.a92828.movieproject.hot.adapter.MyShowingRecyclerViewAdapter;
import com.example.a92828.movieproject.utils.Constants;
import com.example.a92828.movieproject.utils.DividerListItemDecoration;
import com.example.a92828.movieproject.utils.LinearLayoutManagerWrapper;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import java.util.List;
import okhttp3.Call;

public class ShowingFragment extends Fragment {

    Context context;
    private RecyclerView rv_showing;
    protected static final int WHAT_REQUEST_SUCCESS =0;
    protected static final int WHAT_REQUEST_ERROR = 1;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case WHAT_REQUEST_SUCCESS:
                    //显示列表
                    rv_showing.setAdapter(adapter);

                    break;
                case WHAT_REQUEST_ERROR:
                    Toast.makeText(getActivity(), "加载数据失败", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };
    private List<ShowingBean.SubjectsBean> subjects;
    private MyShowingRecyclerViewAdapter adapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //上下文
        context = getActivity();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(context, R.layout.fragment_showing, null);
        rv_showing = view.findViewById(R.id.rv_showing);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
 * 获取json数据（最近热映）
 */
private void getOkhttpGet() {
    String url = Constants.SHOWING;
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

                    Log.d("TAG","首页请求成功=="+response);
                    Gson gson = new Gson();
                    ShowingBean showingBean = gson.fromJson(response, ShowingBean.class);
                    subjects = showingBean.getSubjects();


                    // directorsBean1 = gson.fromJson(jsonString, DirectorsBean.class);
                    adapter = new MyShowingRecyclerViewAdapter(getActivity(), subjects);

                    handler.sendEmptyMessage(WHAT_REQUEST_SUCCESS);//发送请求成功的消息
                    //LayoutManager
                    rv_showing.setLayoutManager((new LinearLayoutManagerWrapper(getActivity(),LinearLayoutManager.VERTICAL,false)));

                    //添加Recyclerview的分隔线
                    rv_showing.addItemDecoration(new DividerListItemDecoration(getActivity(),DividerListItemDecoration.VERTICAL_LIST));
                }
            });
}
}


