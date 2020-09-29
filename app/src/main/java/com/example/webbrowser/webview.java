package com.example.webbrowser;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.webkit.URLUtil;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.SearchView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Pattern;

public class webview extends AppCompatActivity {
    WebView webView;
    SearchView searchView;

    private final String WEBSITE_REGEX = "^(http:\\/\\/|https:\\/\\/)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[\u200C\u200Ba-z]{3}\\.([a-z]+)?$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        searchView = (SearchView) findViewById(R.id.search_view);
        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        Intent intent = getIntent();
        final String str = intent.getStringExtra("message");
        if (null != str && Pattern.matches(WEBSITE_REGEX, str)) {
            System.out.println(URLUtil.guessUrl(str).replace("http", "https"));
            webView.loadUrl(URLUtil.guessUrl(str).replace("http", "https"));
        } else {
            webView.loadUrl("https://www.google.com/search?q="+str);
        }

        webView.setWebViewClient(new WebViewClient() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return false;
            }
        });


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (null != s && Pattern.matches(WEBSITE_REGEX, s)) {
                    webView.loadUrl(URLUtil.guessUrl(str).replace("http", "https"));
                } else {
                    webView.loadUrl("https://www.google.com/search?q="+s);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

}