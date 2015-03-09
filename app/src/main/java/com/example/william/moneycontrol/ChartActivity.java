package com.example.william.moneycontrol;

/**
 * Created by william on 3/9/15.
 */

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class ChartActivity extends ActionBarActivity {

    EditText num1, num2, num3, num4, num5;
    Button btnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chart_view);

        num1 = (EditText)findViewById(R.id.num1);
        num2 = (EditText)findViewById(R.id.num2);
        num3 = (EditText)findViewById(R.id.num3);
        num4 = (EditText)findViewById(R.id.num4);
        num5 = (EditText)findViewById(R.id.num5);
        btnShow = (Button)findViewById(R.id.show);

        btnShow.setOnClickListener(btnShowOnClickListener);
    }

    OnClickListener btnShowOnClickListener =
            new OnClickListener(){

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(
                            ChartActivity.this,
                            ShowWebChartActivity.class);

                    intent.putExtra("NUM1", getNum(num1));
                    intent.putExtra("NUM2", getNum(num2));
                    intent.putExtra("NUM3", getNum(num3));
                    intent.putExtra("NUM4", getNum(num4));
                    intent.putExtra("NUM5", getNum(num5));

                    startActivity(intent);
                }

            };

    private int getNum(EditText editText){

        int num = 0;

        String stringNum = editText.getText().toString();
        if(!stringNum.equals("")){
            num = Integer.valueOf(stringNum);
        }

        return (num);
    }

}