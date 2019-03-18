package com.example.a92828.movieproject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;

public class SearchActivity extends Activity implements SearchView.OnQueryTextListener{
    private SearchView mSearchView;
    private TextView finish_search;
    private Intent intent;
    private String result;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mSearchView = (SearchView) findViewById(R.id.searchview);
        finish_search = findViewById(R.id.finish_search);
        mSearchView.setOnQueryTextListener(this);
        finish_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override
    public boolean onQueryTextSubmit(String s) {
        intent = new Intent(this,QueryResultActivity.class);
        intent.putExtra("string",result);
        startActivity(intent);

        return false;
    }
    @Override
    public boolean onQueryTextChange(String s) {
        if (!TextUtils.isEmpty(s)){
            result = s;
        }
        return false;
    }
}