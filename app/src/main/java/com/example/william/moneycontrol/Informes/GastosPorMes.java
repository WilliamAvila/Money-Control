package com.example.william.moneycontrol.Informes;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

import com.example.william.moneycontrol.R;

/**
 * Created by Jimmy Banegas on 23/02/2015.
 */
public class GastosPorMes extends ActionBarActivity {
    android.support.v7.app.ActionBar actionBar;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gastos_por_mes);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Gastos por mes");


        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
