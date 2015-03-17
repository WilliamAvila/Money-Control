package com.example.william.moneycontrol.Prestamos;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.william.moneycontrol.Helpers.AdminSQLiteOpenHelper;
import com.example.william.moneycontrol.MainActivity;
import com.example.william.moneycontrol.R;
import com.example.william.moneycontrol.Transacciones.CreateGastoIngresoActivity;
import com.example.william.moneycontrol.Transacciones.CreateTransferActivity;

/**
 * Created by william on 3/16/15.
 */
public class PagoPrestamos  extends ActionBarActivity {


        ActionBar actionBar;
        String numeroPrestamo;

        public void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            actionBar = getSupportActionBar();
            actionBar.setTitle("Opciones de Cuenta");

            setContentView(R.layout.account_info);

            Intent i = getIntent();
            // Receiving the Data
            numeroPrestamo = i.getStringExtra("NumeroPrestamo");
            Log.d("Numero Prestamo", numeroPrestamo);

            TextView tv = (TextView)findViewById(R.id.txtViewNumCuenta);
            tv.setText(numeroPrestamo);
            ViewAccountData(numeroPrestamo);

            ActionBar actionBar = getSupportActionBar();
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


        public void AgregarGasto(View v) {
            Intent nextScreen = new Intent(getApplicationContext(),CreateGastoIngresoActivity.class);

            nextScreen.putExtra("NumeroPrestamo",numeroPrestamo.toString());

            startActivity(nextScreen);

        }


        public void ViewAccountData(String IdPrestamo){

            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(),"MoneyControl", null, 1);
            SQLiteDatabase bd = admin.getWritableDatabase();

            TextView tv1 =(TextView)findViewById(R.id.textViewBank);
            TextView tv2 =(TextView)findViewById(R.id.textViewBalance);
            TextView tv3 =(TextView)findViewById(R.id.textViewType);

            Cursor fila = bd.rawQuery(
                    "select Banco,Monto,Plazo,Tasa from Prestamo where IdPrestamo = " + IdPrestamo, null);


            if (fila.moveToFirst()) {
                tv1.setText(fila.getString(0));
                tv2.setText(fila.getString(1));
                tv3.setText(fila.getString(2));
                String st= fila.getString(3);
            } else
                Toast.makeText(getApplicationContext(), "No Info",
                        Toast.LENGTH_SHORT).show();
            bd.close();

        }



    }


