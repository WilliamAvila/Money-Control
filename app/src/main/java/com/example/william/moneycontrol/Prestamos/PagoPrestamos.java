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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.william.moneycontrol.Helpers.AdminSQLiteOpenHelper;
import com.example.william.moneycontrol.R;
import com.example.william.moneycontrol.Transacciones.CreateGastoIngresoActivity;

import java.util.ArrayList;

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

            setContentView(R.layout.plazo_prestamos);

            Intent i = getIntent();
            // Receiving the Data
            numeroPrestamo = i.getStringExtra("NumeroPrestamo");
            Log.d("Numero Prestamo", numeroPrestamo);


            ArrayList<LoanItem> prestamos =GetlistPlazos(numeroPrestamo);
            final ListView lv = (ListView)findViewById(R.id.listViewPlazos);

            lv.setAdapter(new ListViewLoanAdapter(getApplicationContext(), prestamos));


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




    private ArrayList<LoanItem> GetlistPlazos(String IdPrestamo){
        ArrayList<LoanItem> prestamos = new ArrayList<LoanItem>();

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(),"MoneyControl", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String fecha="";

        float pago_mensual=0;
        String Banco="";
        float monto=0;
        float tasa_interes=0;
        int plazo_meses=0;

        Cursor fila = bd.rawQuery(
                "select Fecha,Monto,Plazo,Tasa,Banco from Prestamo where IdPrestamo = "+ IdPrestamo , null);

        if(fila.moveToFirst()){
            fecha=fila.getString(0);
            Log.d("Fecha", fecha);
            Log.d("Fecha", String.valueOf(fila.getType(0)));

            monto=fila.getFloat(1);
            plazo_meses=fila.getInt(2);
            tasa_interes =fila.getFloat(3);

            Banco = fila.getString(4);




        }
        pago_mensual=((monto*tasa_interes)+monto)/plazo_meses;
        for(int i=0;i<plazo_meses;i++)
            prestamos.add(new LoanItem(Integer.valueOf(IdPrestamo),fecha,pago_mensual));

        bd.close();

        return prestamos;
    }



    }


