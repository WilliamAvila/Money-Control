package com.example.william.moneycontrol.Prestamos;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.william.moneycontrol.Cuentas.TimeDialogFragment;
import com.example.william.moneycontrol.Helpers.AdminSQLiteOpenHelper;
import com.example.william.moneycontrol.R;
import com.example.william.moneycontrol.Transacciones.CreateGastoIngresoActivity;
import com.example.william.moneycontrol.Transacciones.CreateTransferActivity;

import java.util.ArrayList;

/**
 * Created by william on 3/16/15.
 */
public class PagoPrestamos  extends ActionBarActivity {

        private final ArrayList<LoanItem> prestamos = new ArrayList<LoanItem>();
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

            Button btnPago = (Button) findViewById((R.id.btnPagoCuota));

            btnPago.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*Intent nextScreen = new Intent(getApplicationContext(),CreateTransferActivity.class);
                    startActivity(nextScreen);
                           return;*/
                    PagoCuotasFragment tFragment = new PagoCuotasFragment();

                    /** Creating a bundle object to store the position of the selected country */
                    Bundle b = new Bundle();

                    /** Storing the position in the bundle object */
                  /*  b.putInt("position", position);
                    b.putInt("NumCuenta", Integer.parseInt(cuentas.get(position).getNumeroCuenta()));*/

                    /** Setting the bundle object as an argument to the DialogFragment object */
                    tFragment.setArguments(b);

                    /** Getting FragmentManager object */
                    FragmentManager fragmentManager = getFragmentManager();

                    /** Starting a FragmentTransaction */
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    /** Getting the previously created fragment object from the fragment manager */
                    PagoCuotasFragment tPrev =  ( PagoCuotasFragment ) fragmentManager.findFragmentByTag("payment_dialog");

                    /** If the previously created fragment object still exists, then that has to be removed */
                    if(tPrev!=null)
                        fragmentTransaction.remove(tPrev);

                    /** Opening the fragment object */
                    tFragment.show(fragmentTransaction, "payment_dialog");
                }
            });


            GetlistPlazos(numeroPrestamo);

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

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        finish();
    }


    private void GetlistPlazos(String IdPrestamo){


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

            monto=fila.getFloat(1);
            plazo_meses=fila.getInt(2);
            tasa_interes =fila.getFloat(3);

            Banco = fila.getString(4);

        }

        float pago_total =((monto*tasa_interes)+monto);

        pago_mensual=pago_total/plazo_meses;

        float interes_total= pago_total-monto;

        float interes_mensual = interes_total/plazo_meses;


        TextView tv1 =(TextView)findViewById(R.id.textViewTotalIntereses);
        TextView tv2 =(TextView)findViewById(R.id.textViewInteresesporMes);
        TextView tv3 =(TextView)findViewById(R.id.textViewTotalPrestamo);

        tv1.setText(String.valueOf(interes_total));
        tv2.setText(String.valueOf(interes_mensual));
        tv3.setText(String.valueOf(pago_total));

        for(int i=0;i<plazo_meses;i++)
            prestamos.add(new LoanItem(String.valueOf(fecha),Banco,pago_mensual));

        bd.close();

        final ListView lv = (ListView)findViewById(R.id.listViewPlazos);
        //lv.setAdapter(new ListViewLoanAdapter(getApplicationContext(), prestamos));


    }
 }


