package com.gdtech.smarttransportationcity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class BusWisataActivity extends AppCompatActivity {

    private WebView buswisata;


    //URL web, yang akan kita gunakan pada Webview
    private String URL = "http://buswisata.dishub.semarangkota.go.id/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        buswisata = findViewById(R.id.my_web);
        settings();
        load_website();

        buswisata.getSettings().setBuiltInZoomControls(true);

    }

    //Method Ini Digunakan sebagai Setelan/Pengaturan Web
    private void settings(){
        WebSettings web_settings = buswisata.getSettings();
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
            buswisata.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }else{
            buswisata.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }

        buswisata.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.toString());
                return true;
            }
        });

        buswisata.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        buswisata.loadUrl(URL);
    }

    public void onBackPressed() {
        if(buswisata.canGoBack()){
            buswisata.goBack();
        }else{
            super.onBackPressed();
        }
    }


}