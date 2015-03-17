package com.example.william.moneycontrol.Prestamos;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.william.moneycontrol.Cuentas.AccountItem;
import com.example.william.moneycontrol.Helpers.AdminSQLiteOpenHelper;
import com.example.william.moneycontrol.MainActivity;
import com.example.william.moneycontrol.R;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Jimmy Banegas on 18/02/2015.
 */
public class CreateLoanActivity extends ActionBarActivity implements View.OnClickListener {
    private EditText etDescripcion;
    String banco;
    private EditText etMonto;
    private EditText etTasa_interés;
    private EditText etPlazo_meses;
    Spinner spinner;
    Button btnCalendar, btnTimePicker;
    private EditText ettxtDate;
    ActionBar actionBar;

    // Variable for storing current date and time
    private int mYear, mMonth, mDay, mHour, mMinute;

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

        ettxtDate=(EditText)findViewById(R.id.editText3);
        btnCalendar = (Button) findViewById(R.id.button);

        btnCalendar.setOnClickListener((View.OnClickListener) this);

       etPlazo_meses.requestFocus();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android
.R.id.home:
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
        if (descripcion.matches("")) {
            descripcion="";
        }

        banco=spinner.getSelectedItem().toString();
        if (banco.matches("")) {
            Toast.makeText(this, "Banco vacío", Toast.LENGTH_SHORT).show();
            return;
        }

         if (etMonto.getText().toString().matches("")) {
            Toast.makeText(this, "Monto vacío", Toast.LENGTH_SHORT).show();
            return;
         }

        float monto =  Float.parseFloat(etMonto.getText().toString());

         if (etTasa_interés.getText().toString().matches("")) {
            Toast.makeText(this, "Tasa vacía", Toast.LENGTH_SHORT).show();
            return;
        }

        float tasa = Float.parseFloat(etTasa_interés.getText().toString());


        String fecha = ettxtDate.getText().toString();
       if (fecha.matches("")) {
            Toast.makeText(this, "Fecha vacía", Toast.LENGTH_SHORT).show();
            return;
        }


         if (etPlazo_meses.getText().toString().matches("")) {
            Toast.makeText(this, "Plazo vacío", Toast.LENGTH_SHORT).show();
            return;
        }
        float plazo = Float.parseFloat(etPlazo_meses.getText().toString());



        ContentValues registro = new ContentValues();
        registro.put("Banco",banco);
        registro.put("Descripcion", descripcion);
        registro.put("Monto", monto);
        registro.put("Tasa", tasa);
        registro.put("Plazo", plazo);
        registro.put("Fecha",fecha);
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

    @Override
    public void onClick(View v) {
        if (v == btnCalendar) {
            // Process to get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);

            // Launch Date Picker Dialog
            DatePickerDialog dpd = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            // Display Selected date in textbox
                            ettxtDate.setText(year + "-"
                                    + (monthOfYear + 1) + "-" + dayOfMonth);


                        }
                    }, mYear, mMonth, mDay);
            dpd.show();
        }

    }

}
