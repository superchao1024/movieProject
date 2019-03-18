package com.example.a92828.movieproject.itemDetail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a92828.movieproject.QueryResultActivity;
import com.example.a92828.movieproject.R;
import com.example.a92828.movieproject.bean.KouBeiBean;
import com.example.a92828.movieproject.bean.QueryItem;
import com.example.a92828.movieproject.find.Adapter.FindFragmentAdapter;
import com.example.a92828.movieproject.utils.Constants;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class QueryItemActivity extends Activity {

    private String id;
    protected static final int WHAT_REQUEST_SUCCESS_KOUBEI_V1 = 0;
    protected static final int WHAT_REQUEST_ERROR = 1;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case WHAT_REQUEST_SUCCESS_KOUBEI_V1:
                    rv_queryItem.setAdapter(queryItemAdapter);
                    break;
                case WHAT_REQUEST_ERROR:
                    Toast.makeText(QueryItemActivity.this, "加载数据失败", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };
    private QueryItemAdapter queryItemAdapter;
    private RecyclerView rv_queryItem;
    private ImageView iv_item_back;
    private TextView tv_item_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_item);
        //   tv_test = findViewById(R.id.tv_test);
        rv_queryItem = findViewById(R.id.rv_queryItem);
        iv_item_back = findViewById(R.id.iv_item_back);
        tv_item_type = findViewById(R.id.tv_item_type);

        iv_item_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        // tv_test.setText(id);
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


    private void getOkhttpGet() {
        String url = "http://api.douban.com/v2/movie/subject/" + id + "?apikey=0b2bdeda43b5688921839c8ecb20399b";
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

                        Log.e("TAA", "首页请求失败==" + e.getMessage());
                    }

                    /**
                     * 当联网成功的时候回调
                     * @param response  请求成功的数据
                     * @param id
                     */
                    @Override
                    public void onResponse(String response, int id) {
                        Log.d("TAAA", "首页请求成功==" + response);
                        //  Toast.makeText(QueryItemActivity.this, response, Toast.LENGTH_SHORT).show();
                        Gson gson = new Gson();
                        QueryItem queryItem = gson.fromJson(response, QueryItem.class);
                        if (queryItem != null) {
                            //有数据
                            queryItemAdapter = new QueryItemAdapter(QueryItemActivity.this, queryItem);
                            handler.sendEmptyMessage(WHAT_REQUEST_SUCCESS_KOUBEI_V1);//发送请求成功的消息
                            rv_queryItem.setLayoutManager(new LinearLayoutManager(QueryItemActivity.this, LinearLayoutManager.VERTICAL, false));
                            if (queryItem.getSubtype().equals("movie")){
                                tv_item_type.setText("电影");
                            }else if (queryItem.getSubtype().equals("tv")){
                                tv_item_type.setText("电视剧");
                            }else {
                                tv_item_type.setText("  ");
                            }
                        } else {
                            // 没有数据
                            Log.d("TAB", "首页请求失败==" + response);
                        }
                    }
                });
        }
}

