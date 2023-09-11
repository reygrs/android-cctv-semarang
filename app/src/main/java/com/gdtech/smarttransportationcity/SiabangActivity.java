package com.gdtech.smarttransportationcity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class SiabangActivity extends AppCompatActivity {

    private WebView siabang;

    //URL web, yang akan kita gunakan pada Webview
    private String URL = "http://siabang.dishub.semarangkota.go.id/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        siabang = findViewById(R.id.my_web);
        settings();
        load_website();

        siabang.getSettings().setBuiltInZoomControls(true); //zoomout zoomin webview
    }

    //Method Ini Digunakan sebagai Setelan/Pengaturan Web
    private void settings(){
        WebSettings web_settings = siabang.getSettings();
        web_settings.setJavaScriptEnabled(true);
        web_settings.setAllowContentAccess(true);
        web_settings.setUseWideViewPort(true);
        web_settings.setLoadsImagesAutomatically(true);
        web_settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        web_settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        web_settings.setEnableSmoothTransition(true);
        web_settings.setDomStorageEnabled(true);
    }

    //Untuk Mengatur, dan menampilan Halaman Web pada Aplikasi
    private void load_website(){
        if(Build.VERSION.SDK_INT >= 19){
            siabang.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }else{
            siabang.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        siabang.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.toString());
                return true;
            }
        });

        siabang.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        siabang.loadUrl(URL);
    }

    public void onBackPressed() {
        if(siabang.canGoBack()){
            siabang.goBack();
        }else{
            super.onBackPressed();
        }
    }
}