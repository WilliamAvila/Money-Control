package com.example.william.moneycontrol;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

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
            String numeroCuenta = i.getStringExtra("NumeroCuenta");

            TextView tv = (TextView)findViewById(R.id.txtViewNumCuenta);
            tv.setText(numeroCuenta);
            ViewAccountData(numeroCuenta);
        }



        public void ViewAccountData(String accountNumber){

          AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(),"MoneyControl", null, 1);
          SQLiteDatabase bd = admin.getWritableDatabase();

          TextView tv1 =(TextView)findViewById(R.id.textViewBank);
          TextView tv2 =(TextView)findViewById(R.id.textViewBalance);
          TextView tv3 =(TextView)findViewById(R.id.textViewType);
          TextView tv4 =(TextView)findViewById(R.id.textViewDescription);

          Cursor fila = bd.rawQuery(
                "select Banco,Moneda,SaldoInicial,TipoCuenta,Descripcion from Cuenta where NumeroCuenta=" + accountNumber, null);


          if (fila.moveToFirst()) {
                tv1.setText(fila.getString(0));
                tv2.setText(fila.getString(1)+" "+fila.getString(2));
                tv3.setText(fila.getString(3));
                String st= fila.getString(4);
               // tv4.setText(fila.getString(4));
          } else
            Toast.makeText(getApplicationContext(), "No Info",
                    Toast.LENGTH_SHORT).show();
            bd.close();

        }



}
