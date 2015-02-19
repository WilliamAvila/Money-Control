package com.example.william.moneycontrol;

import android.content.ContentValues;
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

import java.util.Date;

/**
 * Created by Jimmy Banegas on 19/02/2015.
 */
public class CreateGastoIngresoActivity extends ActionBarActivity {
    private EditText etDescripcion;
    String tipo;
    String categoria;
    Date fecha;
    private EditText etMonto;
    Spinner spinner;
    Spinner spinnerCategoria;

    ActionBar actionBar;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_gasto_ingreso);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Transacciones");
        spinner = (Spinner) findViewById(R.id.spinnerTipo);
        spinnerCategoria = (Spinner) findViewById(R.id.spinnerCategorias);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.tipo_transaccion_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.tipo_categoria_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Button btnCancel = (Button) findViewById((R.id.buttonCancel));

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        spinner.setAdapter(adapter);
        spinnerCategoria.setAdapter(adapter2);
        etDescripcion=(EditText)findViewById(R.id.descripcion_gasto);

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
        tipo=spinner.getSelectedItem().toString();

        ContentValues registro = new ContentValues();
        registro.put("TipoCategoria",tipo);
        registro.put("Descripcion", descripcion);
        bd.insert("Categoria", null, registro);
        bd.close();
        Toast.makeText(this, "Se Agregó Correctamente",
                Toast.LENGTH_SHORT).show();

        finish();
    }

}
