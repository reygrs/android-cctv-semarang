package com.gdtech.smarttransportationcity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private WebView view; //membuat variabel view agar bisa akses metho
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView marque = (TextView) this.findViewById(R.id.marque_scrolling_text);
        marque.setSelected(true);

        view = (WebView) this.findViewById(R.id.webViewMain);

        view.getSettings().setAppCacheMaxSize( 10 * 1024 * 1024 );
        view.getSettings().setJavaScriptEnabled(true);

        view.setWebViewClient(new MyBrowser(){

            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                dialog = ProgressDialog.show(MainActivity.this, null,
                        "Please Wait...Page is Loading...");
                dialog.setCancelable(true);
                dialog.dismiss();

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
                Toast.makeText(MainActivity.this,
                        "The Requested Page Does Not Exist", Toast.LENGTH_LONG).show();
                String dataerrornih = "<center>Data tidak dapat diunduh<br>Silahkan cek koneksi atau kuota internet anda";
                view.loadData(dataerrornih,"","");
                //view.loadUrl("http://www.lalinsemarang.info");
                //super.onReceivedError(view, errorCode, description, failingUrl);
            }
        });
        view.loadUrl("http://dishub.semarangkota.go.id/slide/");

        Button buttonAtcs = (Button) findViewById(R.id.btnAtcs);
        buttonAtcs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // This Perform action on click
                Intent intent = new Intent(view.getContext(), KotlinMainActivity.class);
                startActivity(intent);
            }
        });

        Button buttonbus = (Button) findViewById(R.id.btnbus);
        buttonbus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // This Perform action on click
                Intent intent = new Intent(view.getContext(), BrtActivity.class);
                startActivity(intent);
            }
        });

        Button buttonekir = (Button) findViewById(R.id.btnEkir);
        buttonekir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // This Perform action on click
                Intent intent = new Intent(view.getContext(), PkbActivity.class);
                startActivity(intent);
            }
        });

        Button buttonbuswisata = (Button) findViewById(R.id.btnbuswisata);
        buttonbuswisata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // This Perform action on click
                Intent intent = new Intent(view.getContext(), BusWisataActivity.class);
                startActivity(intent);
            }
        });

        Button buttonsiabang = (Button) findViewById(R.id.btnsiabang);
        buttonsiabang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // This Perform action on click
                Intent intent = new Intent(view.getContext(), SiabangActivity.class);
                startActivity(intent);
            }
        });

        Button buttonmoovit = (Button) findViewById(R.id.btnImoovit);
        buttonmoovit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // This Perform action on click
                //String value="www.dishub.semarangkota.go.id";
                Intent intent = new Intent(view.getContext(), MoovitActivity.class);
                //EreportActivity.class
                startActivity(intent);
            }
        });

        Button buttonwebsite = (Button) findViewById(R.id.btnweb);
        buttonwebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://dishub.semarangkota.go.id"));
                startActivity(intent);
            }
        } );

        Button buttonmaps = (Button) findViewById(R.id.btnmaps);
        buttonmaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://goo.gl/maps/nuiDoP9UZCgSzcis7"));
                startActivity(intent);
            }
        } );

        Button buttoncall = (Button) findViewById(R.id.btncall);
        buttoncall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:082281747794"));
                startActivity(intent);
            }
        } );

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
}
