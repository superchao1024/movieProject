package com.example.a92828.movieproject.itemDetail;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.a92828.movieproject.R;

public class LongCommentsActivity extends Activity {

    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_long_comments);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        Toast.makeText(this, url, Toast.LENGTH_SHORT).show();
        webview = findViewById(R.id.webview);
        //webview.loadUrl(url);

//        Uri uri = Uri.parse(url);  //url为你要链接的地址
//        Intent intenttwo = new Intent(Intent.ACTION_VIEW,uri);
//        startActivity(intenttwo);

        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient());    //当需要一个网页跳转到另一个网页时，目标网页仍然在当前WebView中显示，而不是打开系统浏览器
        webview.loadUrl(url);


    }
}
