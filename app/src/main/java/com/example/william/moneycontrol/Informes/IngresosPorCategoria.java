package com.example.william.moneycontrol.Informes;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.william.moneycontrol.Helpers.AdminSQLiteOpenHelper;
import com.example.william.moneycontrol.Helpers.ShowWebChartActivity;
import com.example.william.moneycontrol.R;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Jimmy Banegas on 23/02/2015.
 */
public class IngresosPorCategoria extends ActionBarActivity implements View.OnClickListener{
    Button btnCalendar, btnTimePicker;
    private EditText ettxtDate;
    Button btnCalendar2, btnTimePicker2;
    private EditText ettxtDate2;
    Button btnShow;
    // Variable for storing current date and time
    private int mYear, mMonth, mDay, mHour, mMinute;


    ActionBar actionBar;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingresos_por_categoria);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Ingresos por categoría");

        ettxtDate=(EditText)findViewById(R.id.editText);
        btnCalendar = (Button) findViewById(R.id.btnDesde);

        btnCalendar.setOnClickListener((View.OnClickListener) this);

        ettxtDate2=(EditText)findViewById(R.id.editText2);
        btnCalendar2 = (Button) findViewById(R.id.btnHasta);

        btnCalendar2.setOnClickListener((View.OnClickListener) this);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        btnShow = (Button)findViewById(R.id.buttonShowChart);

        btnShow.setOnClickListener(btnShowOnClickListener);
    }

    View.OnClickListener btnShowOnClickListener =
            new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    SendDataToChart();
                }

            };


    public void SendDataToChart(){

        ArrayList<String> Montos = new ArrayList<String>();

        ArrayList<String> Categorias = new ArrayList<String>();

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(),"MoneyControl", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();


        String fecha1 = ettxtDate.getText().toString();
        if (fecha1.matches("")) {
            Toast.makeText(this, "Fecha inicio vacía", Toast.LENGTH_SHORT).show();
            return;
        }
        String fecha2 = ettxtDate2.getText().toString();

        if (fecha2.matches("")) {
            Toast.makeText(this, "Fecha final vacía", Toast.LENGTH_SHORT).show();
            return;
        }

        String Monto = "";
        String Categoria = "";




        Cursor fila = bd.rawQuery(
                "select Categoria,Monto from Transaccion where Tipo = \"Ingreso\" and Fecha between '" + fecha1+"' and '" + fecha2 + "'", null);

        while(fila.moveToNext()){
            Categoria=fila.getString(0);
            Categorias.add(Categoria);

            Monto=fila.getString(1);
            Montos.add(Monto);


        }
        bd.close();

        Intent intent = new Intent(this, ShowWebChartActivity.class);
        intent.putStringArrayListExtra("Montos", Montos);

        intent.putStringArrayListExtra("Categorias", Categorias);


        startActivity(intent);


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
                            ettxtDate.setText(dayOfMonth + "-"
                                    + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            dpd.show();
        }else if (v == btnCalendar2){
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
                            ettxtDate2.setText(dayOfMonth + "-"
                                    + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            dpd.show();
        }

    }
}
