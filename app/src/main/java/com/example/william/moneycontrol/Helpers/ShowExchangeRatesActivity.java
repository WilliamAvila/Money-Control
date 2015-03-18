package com.example.william.moneycontrol.Helpers;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.example.william.moneycontrol.R;

import java.util.ArrayList;

/**
 * Created by william on 3/18/15.
 */
@SuppressLint("SetJavaScriptEnabled")
public class ShowExchangeRatesActivity extends ActionBarActivity {

    WebView webView;
    int num1, num2, num3, num4, num5;
    ArrayList Montos,Categorias;
    int ArraySize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exchange_rates);

        webView = (WebView)findViewById(R.id.webView);
        webView.addJavascriptInterface(new WebAppInterface(), "Android");

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/currency.html");
    }

    public class WebAppInterface {

        @JavascriptInterface
        public int getNum1() {
            return num1;
        }




    }

}


