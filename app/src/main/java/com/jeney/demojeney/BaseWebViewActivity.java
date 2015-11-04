package com.jeney.demojeney;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.jeney.demojeney.comm.actvity.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * desc: H5页面
 * author: Jeney
 * email: jeneylu@anjuke.com
 * date: 2015/11/4
 * WebView緩存 http://blog.csdn.net/moubenmao_jun/article/details/9076917
 */
public class BaseWebViewActivity extends BaseActivity {
    private static final String URL = "BaseWebViewActivity.url";

    @Bind(R.id.webView)
    WebView webView;

    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    private String mUrl;

    public static Intent newIntent(Context context, String url) {
        Intent intent = new Intent(context, BaseWebViewActivity.class);
        intent.putExtra(URL, url);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUrl = getIntent().getStringExtra(URL);
        setContentView(R.layout.activity_base_webview);
        ButterKnife.bind(this);
        webView.setWebViewClient(new MyWebViewClient());
        initWebSettings();
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebChromeClient(new MyWebChromeClient());
        webView.loadUrl(mUrl);
        ButterKnife.bind(this);
    }

    private void initWebSettings() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            webView.destroy();
            super.onBackPressed();
        }
    }

    private class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
                //noinspection ConstantConditions
                getSupportActionBar().setTitle(title);
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            if (null != progressBar)
                if (newProgress == 100) {
                    progressBar.setVisibility(View.GONE);
                } else {
                    progressBar.setProgress(newProgress);
                    progressBar.setVisibility(View.VISIBLE);
                }
        }
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }
    }
}
