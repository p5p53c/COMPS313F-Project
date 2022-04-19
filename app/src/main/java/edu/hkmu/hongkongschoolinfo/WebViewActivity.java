package edu.hkmu.hongkongschoolinfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends AppCompatActivity {
    private WebView webview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webview = findViewById(R.id.webview);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String urlStr = intent.getStringExtra(ContactDialog.EXTRA_MESSAGE);

        // Enable JavaScript
        webview.getSettings().setJavaScriptEnabled(true);

        // Enable clicking on links and redirection
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        // Load initial page
        webview.loadUrl(urlStr);
    }
}