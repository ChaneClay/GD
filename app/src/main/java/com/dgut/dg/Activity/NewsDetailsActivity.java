package com.dgut.dg.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.dgut.dg.R;

public class NewsDetailsActivity extends AppCompatActivity {

    private WebView mWvDetail;
    public static final String TAG = "NewsDetailsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        ActionBar bar = getSupportActionBar();
        bar.setTitle("返回");
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setDisplayShowHomeEnabled(false);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String detailUrl = bundle.getString("url");

        mWvDetail = findViewById(R.id.wv_detail);

        mWvDetail.loadUrl(detailUrl);

        mWvDetail.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(detailUrl);
                return true;
            }
        });

    }

    // 页面返回键
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}