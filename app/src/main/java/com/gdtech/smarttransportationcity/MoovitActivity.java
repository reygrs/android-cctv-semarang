package com.gdtech.smarttransportationcity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MoovitActivity extends Activity {

    private WebView view; //membuat variabel view agar bisa akses method

    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        view = (WebView) this.findViewById(R.id.my_web);
        view.getSettings().setJavaScriptEnabled(true);

        view.setWebViewClient(new MyBrowser(){

            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                dialog = ProgressDialog.show(MoovitActivity.this, "Page is Loading",
                        "Please Wait...");
                dialog.setCancelable(true);
                super.onPageStarted(view, url, favicon);
            }

            // This method will be triggered when the Page loading is completed

            public void onPageFinished(WebView view, String url) {
                dialog.dismiss();
                super.onPageFinished(view, url);
            }

            // This method will be triggered when error page appear

            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                dialog.dismiss();
                // You can redirect to your own page instead getting the default
                // error page
                Toast.makeText(MoovitActivity.this,
                        "The Requested Page Does Not Exist", Toast.LENGTH_LONG).show();
                view.loadUrl("https://moovit.com/lines?customerId=4624&metroId=4425&lang=en");
                super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });
        view.loadUrl("https://moovitapp.com/index/in/Tranportasi_Umum-Semarang-4425");
        //https://moovitapp.com/index/in/Tranportasi_Umum-Semarang-4425

    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.endsWith(".stream")) {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(url));
                view.getContext().startActivity(intent);
                return true;
            } else {
                return super.shouldOverrideUrlLoading(view, url);
            }
        }
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //ketika disentuh tombol back
        if ((keyCode == KeyEvent.KEYCODE_BACK) && view.canGoBack()) {
            view.goBack(); //method goback(),untuk kembali ke halaman sebelumnya
            return true;
        }
        // Jika tidak ada halaman yang pernah dibuka
        // maka akan keluar dari activity (tutup aplikasi)
        return super.onKeyDown(keyCode, event);
    }
}
