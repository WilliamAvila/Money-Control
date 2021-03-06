package com.example.william.moneycontrol.Bancos;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.william.moneycontrol.Helpers.AdminSQLiteOpenHelper;
import com.example.william.moneycontrol.MainActivity;
import com.example.william.moneycontrol.R;

/**
 * Created by william on 2/12/15.
 */
public class CreateBankActivity extends ActionBarActivity {

    private EditText etDescripcion;
    private EditText etNombre;
    ActionBar actionBar;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_banco);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Bancos");


        Button btnCancel = (Button) findViewById((R.id.buttonCancel));

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        etNombre=(EditText)findViewById(R.id.textEditNombreBanco);
        etDescripcion=(EditText)findViewById(R.id.DescripcionBanco);

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
        String nombre = etNombre.getText().toString();
        String descripcion = etDescripcion.getText().toString();

        if (nombre.matches("")) {
            Toast.makeText(this, "Nombre vacío", Toast.LENGTH_SHORT).show();
            return;
        }

        if (descripcion.matches("")) {
            descripcion="";
        }

        ContentValues registro = new ContentValues();

        registro.put("Nombre", nombre);
        registro.put("Descripcion", descripcion);
        bd.insert("Banco", null, registro);
        bd.close();
        Toast.makeText(this, "Se Agregó Correctamente",
                Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(CreateBankActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("Index",3);
        startActivity(intent);
        finish();
    }

}
