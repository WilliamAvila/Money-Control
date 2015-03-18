package com.example.william.moneycontrol.Cuentas;

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

/**
 * Created by william on 3/18/15.
 */
public class AccountDetailsActivity extends ActionBarActivity {


    ActionBar actionBar;
    String numeroCuenta;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Opciones de Cuenta");

        setContentView(R.layout.account_details);

        Intent i = getIntent();
        // Receiving the Data
        numeroCuenta = i.getStringExtra("NumeroCuenta");

        Log.d("Numero Cuenta", numeroCuenta);

        TextView tv = (TextView)findViewById(R.id.textViewAccountNumber);
        tv.setText(numeroCuenta);
        ViewAccountData(numeroCuenta);

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

    public void DeleteAccount(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "MoneyControl", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        bd.delete("Cuenta", "NumeroCuenta=" + numeroCuenta, null);
        bd.close();

        Intent intent = new Intent(AccountDetailsActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();

    }



    public void ViewAccountData(String accountNumber){

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(),"MoneyControl", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        TextView tv1 =(TextView)findViewById(R.id.textViewBank);
        TextView tv2 =(TextView)findViewById(R.id.textViewInitialBalance);
        TextView tv3 =(TextView)findViewById(R.id.textViewBalance);
        TextView tv4 =(TextView)findViewById(R.id.textViewType);
        TextView tv5 =(TextView)findViewById(R.id.textViewDescription);

        Cursor fila = bd.rawQuery(
                "select Banco,Moneda,SaldoInicial,Saldo,TipoCuenta,Descripcion from Cuenta where NumeroCuenta=" + accountNumber, null);

        if (fila.moveToFirst()) {
            tv1.setText(fila.getString(0));
            tv2.setText(fila.getString(1)+" "+fila.getString(2));
            tv3.setText(fila.getString(3));
            tv4.setText(fila.getString(4));
            tv5.setText(fila.getString(5));
        } else
            Toast.makeText(getApplicationContext(), "No Info",
                    Toast.LENGTH_SHORT).show();
        bd.close();

    }

}
