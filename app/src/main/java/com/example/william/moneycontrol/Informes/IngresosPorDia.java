package com.example.william.moneycontrol.Informes;

import android.app.DatePickerDialog;
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
import android.widget.ListView;
import android.widget.Spinner;

import com.example.william.moneycontrol.Cuentas.AccountItem;
import com.example.william.moneycontrol.Cuentas.ListViewAccountAdapter;
import com.example.william.moneycontrol.Helpers.AdminSQLiteOpenHelper;
import com.example.william.moneycontrol.R;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Jimmy Banegas on 23/02/2015.
 */
public class IngresosPorDia extends ActionBarActivity implements View.OnClickListener{
    Button btnCalendar, btnTimePicker;
    private EditText ettxtDate;
    Button btnCalendar2, btnTimePicker2;
    private EditText ettxtDate2;
    Spinner spinnerCuentas;
    Button btnShow;
    private final ArrayList<AccountItem> ingresos = new ArrayList<AccountItem>();
    String AccountNumber;
    // Variable for storing current date and time
    private int mYear, mMonth, mDay, mHour, mMinute;


    ActionBar actionBar;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingresos_por_dia);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Ingresos por día");
        AddAccounts();
        ettxtDate=(EditText)findViewById(R.id.editText);
        btnCalendar = (Button) findViewById(R.id.btnDesde);

        btnCalendar.setOnClickListener((View.OnClickListener) this);

        ettxtDate2=(EditText)findViewById(R.id.editText2);
        btnCalendar2 = (Button) findViewById(R.id.btnHasta);

        btnCalendar2.setOnClickListener((View.OnClickListener) this);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        btnShow = (Button) findViewById(R.id.buttonShowIngresos);

        btnShow.setOnClickListener(btnShowOnClickListener);
    }

    View.OnClickListener btnShowOnClickListener =
            new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    AddItemsToListView();
                }

            };


    public void AddItemsToListView(){

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(),"MoneyControl", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();


        String fecha1 = ettxtDate.getText().toString();
        String fecha2 = ettxtDate2.getText().toString();

        String Fecha = "";
        String Categoria = "";
        String Monto = "";

        AccountNumber = spinnerCuentas.getSelectedItem().toString().split(" ")[0];
        Log.e("Numero Cuenta", AccountNumber);


        Cursor fila = bd.rawQuery(
                "select Fecha,Categoria,Monto from Transaccion where Tipo = \"Ingreso\" and Fecha between '" + fecha1+"' and '" + fecha2 + "' and Fuente = \""+AccountNumber+"\"", null);

        ingresos.clear();
        while(fila.moveToNext()){
            Fecha = fila.getString(0);
            Categoria=fila.getString(1);
            Monto = fila.getString(2);


            ingresos.add(new AccountItem("",Fecha,Categoria,Double.parseDouble(Monto)));

        }
        bd.close();

        final ListView lv = (ListView)findViewById(R.id.listView2);
        lv.setAdapter(new ListViewAccountAdapter(getApplicationContext(), ingresos));


    }

    public void AddAccounts(){
        ArrayList<String> cuentas = new ArrayList<String>();

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getApplicationContext(),"MoneyControl", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String Nombre="";
        String Banco = "";

        Cursor fila = bd.rawQuery(
                "select NumeroCuenta, Banco from Cuenta" , null);

        while(fila.moveToNext()){
            Nombre=fila.getString(0);
            Banco=fila.getString(1);
            cuentas.add(Nombre+ "      "+ Banco);
        }

        bd.close();

        spinnerCuentas = (Spinner) findViewById(R.id.spinnerCuentas2);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, cuentas); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCuentas.setAdapter(spinnerArrayAdapter);

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
                            ettxtDate.setText(year + "-"
                                    + (monthOfYear + 1) + "-" + dayOfMonth);

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
                            ettxtDate2.setText(year + "-"
                                    + (monthOfYear + 1) + "-" + dayOfMonth);

                        }
                    }, mYear, mMonth, mDay);
            dpd.show();
        }

    }
}
