package com.example.a92828.movieproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a92828.movieproject.bean.MovieBean;
import com.example.a92828.movieproject.hot.adapter.MyShowingRecyclerViewAdapter;
import com.example.a92828.movieproject.itemDetail.QueryItemActivity;
import com.example.a92828.movieproject.utils.Constants;
import com.example.a92828.movieproject.utils.DividerListItemDecoration;
import com.example.a92828.movieproject.utils.LinearLayoutManagerWrapper;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import okhttp3.Call;

public class QueryResultActivity extends Activity {

    private String data;
    private List<MovieBean.SubjectsBean> subjects;
    protected static final int WHAT_REQUEST_SUCCESS = 0;
    protected static final int WHAT_REQUEST_ERROR = 1;
    private SearchView searchview_query;
    private TextView finfish_query_search;
    private RecyclerView rv_query_result;
    private MySearchResultRecyclerViewAdapter mySearchResultRecyclerViewAdapter;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case WHAT_REQUEST_SUCCESS:
                    //显示列表
                    tv_query_result.setText("共" + total + "部电影");
                    rv_query_result.setAdapter(mySearchResultRecyclerViewAdapter);

                    break;
                case WHAT_REQUEST_ERROR:
                    Toast.makeText(QueryResultActivity.this, "加载数据失败", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };
    private TextView tv_query_result;
    private int total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_result);
        searchview_query = findViewById(R.id.searchview_query);
        tv_query_result = findViewById(R.id.tv_query_result);
        rv_query_result = findViewById(R.id.rv_query_result);

        finfish_query_search = findViewById(R.id.finish_query_search);
        finfish_query_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        data = intent.getStringExtra("string");
        searchview_query.setQuery(data, false);
        getOkhttpGet();
    }

    /**
     * 获取json数据（查询）
     */
    private void getOkhttpGet() {
        String url = Constants.SEARCH + data;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    /**
                     * 当请求失败的时候回调
                     * @param call
                     * @param e
                     * @param id
                     */
                    @Override
                    public void onError(Call call, Exception e, int id) {

                        Log.e("TAG", "首页请求失败==" + e.getMessage());
                    }

                    /**
                     * 当联网成功的时候回调
                     * @param response  请求成功的数据
                     * @param id
                     */
                    @Override
                    public void onResponse(String response, int id) {

                        Log.d("TAG", "首页请求成功==" + response);
                        Gson gson = new Gson();
                        MovieBean searchBean = gson.fromJson(response, MovieBean.class);
                        subjects = searchBean.getSubjects();
                        total = searchBean.getTotal();


                        mySearchResultRecyclerViewAdapter = new MySearchResultRecyclerViewAdapter(QueryResultActivity.this, subjects);
                        handler.sendEmptyMessage(WHAT_REQUEST_SUCCESS);//发送请求成功的消息
                        //LayoutManager
                        rv_query_result.setLayoutManager((new LinearLayoutManagerWrapper(QueryResultActivity.this, LinearLayoutManager.VERTICAL, false)));

                        //添加Recyclerview的分隔线
                        rv_query_result.addItemDecoration(new DividerListItemDecoration(QueryResultActivity.this, DividerListItemDecoration.VERTICAL_LIST));
                        mySearchResultRecyclerViewAdapter.setOnItemClickListener(new MyShowingRecyclerViewAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view,String data) {
                                Intent intent = new Intent(QueryResultActivity.this,QueryItemActivity.class);
                                intent.putExtra("id",data);
                                startActivity(intent);
                               // Toast.makeText(QueryResultActivity.this, data, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
    }

}
