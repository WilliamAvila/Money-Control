package com.example.william.moneycontrol.Prestamos;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.william.moneycontrol.Cuentas.AccountItem;
import com.example.william.moneycontrol.Helpers.AdminSQLiteOpenHelper;
import com.example.william.moneycontrol.MainActivity;
import com.example.william.moneycontrol.R;

import java.util.ArrayList;

/**
 * Created by Jimmy Banegas on 18/02/2015.
 */
public class CreateLoanActivity extends ActionBarActivity {
    private EditText etDescripcion;
    String banco;
    private EditText etMonto;
    private EditText etTasa_interés;
    private EditText etPlazo_meses;
    Spinner spinner;
    ActionBar actionBar;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_prestamo);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Préstamos");



        Button btnCancel = (Button) findViewById((R.id.buttonCancel));

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        AddBanks();

        etDescripcion=(EditText)findViewById(R.id.descripcion_loan);
        etMonto =(EditText) findViewById(R.id.monto);
        etTasa_interés =(EditText) findViewById(R.id.tasa);
        etPlazo_meses =(EditText) findViewById(R.id.plazo);

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


    public void IngresarDatos(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "MoneyControl", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String descripcion = etDescripcion.getText().toString();
        banco=spinner.getSelectedItem().toString();
        float monto =  Float.parseFloat(etMonto.getText().toString());
        float tasa = Float.parseFloat(etTasa_interés.getText().toString());

        float plazo = Float.parseFloat(etPlazo_meses.getText().toString());

        ContentValues registro = new ContentValues();
        registro.put("Banco",banco);
        registro.put("Descripcion", descripcion);
        registro.put("Monto", monto);
        registro.put("Tasa", tasa);
        registro.put("Plazo", plazo);
        bd.insert("Prestamo", null, registro);
        bd.close();
        Toast.makeText(this, "Se Agregó Correctamente",
                Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(CreateLoanActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("Index",4);
        startActivity(intent);
        finish();
    }

    public void AddBanks(){
        ArrayList<AccountItem> cuentas = new ArrayList<AccountItem>();
        ArrayList<String> bancos = new ArrayList<String>();


        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(),"MoneyControl", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String Nombre="";


        Cursor fila = bd.rawQuery(
                "select Nombre from Banco" , null);

        while(fila.moveToNext()){
            Nombre=fila.getString(0);
            bancos.add(Nombre);

        }

        bd.close();

        spinner = (Spinner) findViewById(R.id.spinnerBanco);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, bancos); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);
    }



}
