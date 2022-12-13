package com.example.bangladeshrailway;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.URLEncoder;

public class TrainRoutes extends AppCompatActivity {
    WebView pdfview;
    String fileurl;
    String url="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_routes);
        getSupportActionBar().setTitle("Train Routes");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        fileurl="https://firebasestorage.googleapis.com/v0/b/bangladesh-railway-56f4f.appspot.com/o/map%2FBangladesh%20Railway%20Map.pdf?alt=media&token=69dbd84b-f4d1-4f58-b93f-87b9d09938c9";

        pdfview=findViewById(R.id.pdfview);
        pdfview.getSettings().setJavaScriptEnabled(true);
        pdfview.getSettings().setSupportZoom(true);
        pdfview.getSettings().setBuiltInZoomControls(true);

        final ProgressDialog pd=new ProgressDialog(this);
        pd.setMessage("Opening....!!!");



        pdfview.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                pd.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                pd.dismiss();
            }
        });


        try {
            url= URLEncoder.encode(fileurl,"UTF-8");
        }catch (Exception ignored)
        {

        }
        pdfview.loadUrl("http://docs.google.com/gview?embedded=true&url=" + url);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}