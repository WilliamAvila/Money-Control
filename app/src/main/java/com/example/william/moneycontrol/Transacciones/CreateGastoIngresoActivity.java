package com.example.william.moneycontrol.Transacciones;

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
import com.example.william.moneycontrol.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Jimmy Banegas on 19/02/2015.
 */
public class CreateGastoIngresoActivity extends ActionBarActivity implements View.OnClickListener {
    private EditText etDescripcion;
    String tipo;
    String categoria;
    Date fecha;
    private EditText etMonto;
    Spinner spinnerTipo;
    Spinner spinnerCategoria;
    Button btnCalendar, btnTimePicker;
    private EditText ettxtDate;

    String numeroCuenta;

    // Variable for storing current date and time
    private int mYear, mMonth, mDay, mHour, mMinute;


    ActionBar actionBar;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_gasto_ingreso);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Transacciones");
        spinnerTipo = (Spinner) findViewById(R.id.spinnerTipo);

        spinnerCategoria = (Spinner) findViewById(R.id.spinnerCategorias);
        AddCategories();

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.tipo_transaccion_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner

        Button btnCancel = (Button) findViewById((R.id.buttonCancel));

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        spinnerTipo.setAdapter(adapter);
        etDescripcion=(EditText)findViewById(R.id.descripcion_gasto);
        ettxtDate=(EditText)findViewById(R.id.txtDate);
        etMonto = (EditText)findViewById(R.id.monto);
        btnCalendar = (Button) findViewById(R.id.btnCalendar);

        btnCalendar.setOnClickListener((View.OnClickListener) this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent i = getIntent();
        // Receiving the Data
        numeroCuenta = i.getStringExtra("NumeroCuenta");

        Log.e("hello", numeroCuenta);

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


    public void AddCategories(){
        ArrayList<AccountItem> cuentas = new ArrayList<AccountItem>();
        ArrayList<String> categorias = new ArrayList<String>();
        categorias.add("University");
        categorias.add("Food");


        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(),"MoneyControl", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String Nombre="";

        Cursor fila = bd.rawQuery(
                "select Nombre from Categoria" , null);

        while(fila.moveToNext()){
            Nombre=fila.getString(0);
            categorias.add(Nombre);

        }

        bd.close();

        spinnerCategoria = (Spinner) findViewById(R.id.spinnerCategorias);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categorias); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategoria.setAdapter(spinnerArrayAdapter);
    }


    public void IngresarDatos(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "MoneyControl", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String descripcion = etDescripcion.getText().toString();
        String monto = etMonto.getText().toString();
        String fecha = ettxtDate.getText().toString();
        tipo=spinnerTipo.getSelectedItem().toString();
        categoria=spinnerCategoria.getSelectedItem().toString();
        ContentValues registro = new ContentValues();

        registro.put("Tipo",tipo);
        registro.put("Categoria",categoria);
        registro.put("Monto", monto);
        registro.put("Destino","");
        registro.put("Fuente",numeroCuenta);//Cuenta en la que se hace la transaccion
        registro.put("Fecha", fecha);
        registro.put("Comentario", descripcion);

        bd.insert("Transaccion", null, registro);
        bd.close();
        Toast.makeText(this, "Se Agregó Correctamente",
                Toast.LENGTH_SHORT).show();

        finish();
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
