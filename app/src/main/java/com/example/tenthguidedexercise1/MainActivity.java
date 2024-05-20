package com.example.tenthguidedexercise1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    WebView browser;
    AutoCompleteTextView suggestedURL;
    ArrayAdapter adapter;
    Button submit;
    String[] urls = {"google.com", "yahoo.com", "facebook.com", "youtube.com"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        browser = findViewById(R.id.webView);
        suggestedURL = findViewById(R.id.actvURLGE10);
        submit = findViewById(R.id.btnOpenURLGE10);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,urls);
        suggestedURL.setThreshold(2);
        suggestedURL.setAdapter(adapter);

        initializeWebView();
        loadWebURL();
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void initializeWebView() {
        browser.getSettings().setLoadsImagesAutomatically(true);
        // Enable JavaScript (careful with XSS vulnerabilities)
        browser.getSettings().setJavaScriptEnabled(true);
        // WebView should handle the URL loading
        browser.setWebViewClient(new WebViewClient());
        // Enable Scrollbar
        browser.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
    }

    public void loadWebURL() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = suggestedURL.getText().toString();

                if (!url.startsWith("www.") && !url.startsWith("https://")) {
                    url = "www." + url;
                }
                if (!url.startsWith("https://")) {
                    url = "https://" + url;
                }
                browser.loadUrl(url);
            }
        });
    }
}
