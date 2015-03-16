package com.example.william.moneycontrol.Helpers;

/**
 * Created by william on 3/9/15.
 */
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.example.william.moneycontrol.R;

import java.util.ArrayList;

@SuppressLint("SetJavaScriptEnabled")
public class ShowWebChartActivity extends ActionBarActivity {

    WebView webView;
    int num1, num2, num3, num4, num5;
    ArrayList Montos,Categorias;
    int ArraySize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_webchart);

        Intent intent= getIntent();
        Montos = new ArrayList<String>();
        Categorias = new ArrayList<String>();

        Montos = intent.getStringArrayListExtra("Montos");
        Categorias = intent.getStringArrayListExtra("Categorias");



        for(int i = 0;i<Montos.size();i++){
            Log.d("Montos", Montos.get(i).toString());

            Log.d("Categorias", Categorias.get(i).toString());
        }
        ArraySize = Montos.size();

        webView = (WebView)findViewById(R.id.web);
        webView.addJavascriptInterface(new WebAppInterface(), "Android");

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/chart.html");
    }

    public class WebAppInterface {



        @JavascriptInterface
        public ArrayList getArrayMontos() {
            return Montos;
        }


        @JavascriptInterface
        public ArrayList getArrayCategorias() {
            return Categorias;
        }

        @JavascriptInterface
        public String getCategoria(int index) {
            if(index <= ArraySize)
                return Categorias.get(index).toString();
            else
                return "";
        }

        @JavascriptInterface
        public int getMonto(int index) {
            if(index <= ArraySize)
                return Integer.parseInt(Montos.get(index).toString());
            else
                return 0;
        }


        @JavascriptInterface
        public int getArraySize() {
            return ArraySize;
        }

        @JavascriptInterface
        public int getNum1() {
            return num1;
        }

        @JavascriptInterface
        public int getNum2() {
            return num2;
        }

        @JavascriptInterface
        public int getNum3() {
            return num3;
        }

        @JavascriptInterface
        public int getNum4() {
            return num4;
        }

        @JavascriptInterface
        public int getNum5() {
            return num5;
        }
    }

}
