package com.example.william.moneycontrol;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

/**
 * Created by william on 2/25/15.
 */
public class AccountInfoActivity extends ActionBarActivity {


        ActionBar actionBar;

        public void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.account_info);

            Intent i = getIntent();
            // Receiving the Data
            String name = i.getStringExtra("Id");

            TextView tv = (TextView)findViewById(R.id.txtViewIdCuenta);
            tv.setText(name);
        }



}
