package com.example.william.moneycontrol;

import android.content.ContentValues;
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

/**
 * Created by william on 1/25/15.
 */
public class CreateAccountActivity extends ActionBarActivity {

    private EditText etNumero,etSaldoInicial,etDescripcion;
    String banco,moneda,tipo;
    Spinner spinner, spinner2,spinner3;
    ActionBar actionBar;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_cuenta);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Cuentas");
        spinner = (Spinner) findViewById(R.id.spinnerBanks);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.banks_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner2 = (Spinner) findViewById(R.id.spinnerCurrency);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.currency_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        spinner3 = (Spinner) findViewById(R.id.spinnerType);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,
                R.array.type_array, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter3);

        Button btnCancel = (Button) findViewById((R.id.buttonCancel));

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            finish();
            }
        });

        etNumero=(EditText)findViewById(R.id.editTextNumero);
        etSaldoInicial=(EditText)findViewById(R.id.editTextSaldoInicial);
        etDescripcion=(EditText)findViewById(R.id.editTextDescripcion);


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
        String num = etNumero.getText().toString();
        String saldo = etSaldoInicial.getText().toString();
        String descripcion = etDescripcion.getText().toString();
        banco=spinner.getSelectedItem().toString();
        moneda=spinner2.getSelectedItem().toString();
        tipo=spinner3.getSelectedItem().toString();



        ContentValues registro = new ContentValues();
        registro.put("NumeroCuenta", num);
        registro.put("Banco",banco);
        registro.put("Moneda",moneda);
        registro.put("SaldoInicial",saldo);
        registro.put("TipoCuenta",tipo);
        registro.put("Descripcion", descripcion);
        bd.insert("Cuenta", null, registro);
        bd.close();
        Toast.makeText(this, "Se Agrego la cuenta Correctamente",
                Toast.LENGTH_SHORT).show();

        finish();
    }

        public void consultaporNumero(View v) {


        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"MoneyControl", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();


        String cod = etNumero.getText().toString();
        Cursor fila = bd.rawQuery(
                "select SaldoInicial,Descripcion from Cuenta where NumeroCuenta=" + cod, null);


        if (fila.moveToFirst()) {
            etSaldoInicial.setText(fila.getString(0));
            etDescripcion.setText(fila.getString(1));
        } else
            Toast.makeText(this, "No existe cuenta",
                    Toast.LENGTH_SHORT).show();
        bd.close();
    }

}