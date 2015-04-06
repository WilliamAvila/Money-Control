package com.example.william.moneycontrol.Prestamos;

import android.accounts.Account;
import android.app.DialogFragment;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.william.moneycontrol.Helpers.AdminSQLiteOpenHelper;
import com.example.william.moneycontrol.R;
import com.example.william.moneycontrol.Transacciones.CreateGastoIngresoActivity;
import com.example.william.moneycontrol.Transacciones.CreateTransferActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Affisa-Jimmy on 18/03/2015.
 */
public class PagoCuotasFragment extends DialogFragment {
    int saldoCuota;
    private EditText Descripcion;
    Float Cantidad;
    Spinner spinnerOrigen;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        /** Inflating layout for the dialog */
        View v = inflater.inflate(R.layout.dialog_pago_cuota, null);
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getActivity().getApplicationContext(),"MoneyControl", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        /** Getting the arguments passed to this fragment. Here we expects the selected item's position as argument */
        final Bundle b = getArguments();
        /** Setting the title for the dialog window */
        saldoCuota =  b.getInt("SaldoCuota");
        Cantidad =  b.getFloat("Cantidad");
        getDialog().setTitle("Pago de cuota");

        spinnerOrigen = (Spinner) v.findViewById(R.id.spinnerCuentas);
        TextView tv1 =(TextView) v.findViewById(R.id.txtCantidad);
        tv1.setText(String.valueOf(Cantidad));
        Descripcion =(EditText) v.findViewById(R.id.txtDescripcion);

                //AccountNumber = spinnerOrigen.getSelectedItem().toString().split(" ")[0];
        AddAccounts();

        Button btnCancelar = (Button) v.findViewById((R.id.btnCancelar));

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        Button btnGuardar = (Button) v.findViewById((R.id.btnPagar));

        btnGuardar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String tipo;
                String categoria;
                String numeroCuenta;

                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getActivity().getApplicationContext(),
                        "MoneyControl", null, 1);
                SQLiteDatabase bd = admin.getWritableDatabase();

                String descripcion = Descripcion.getText().toString();
                if (descripcion.matches("")) {
                    descripcion="";
                }

                String monto = Cantidad.toString();
                if (monto.toString().matches("")) {
                    return;
                }

                tipo="Gasto";
                categoria="Cuota Prestamo";
                ContentValues registro = new ContentValues();

                numeroCuenta=spinnerOrigen.getSelectedItem().toString().split(" ")[0];;

                registro.put("Tipo",tipo);
                registro.put("Categoria",categoria);
                registro.put("Monto", monto);
                registro.put("Destino","");
                registro.put("Fuente",numeroCuenta);//Cuenta en la que se hace la transaccion
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH)+1;
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                registro.put("Fecha", mYear+"-"+mMonth+"-"+mDay);
                registro.put("Comentario", descripcion);

                Log.e("PAGO CUOTA", registro.toString());

                bd.insert("Transaccion", null, registro);
                bd.close();


                if(tipo.equals("Gasto"))
                    UpdateAccountData(numeroCuenta,monto);
                else
                    AddAccountData(numeroCuenta,monto);

                dismiss();
            }
        });

        /** Returns the View object */
        return v;
    }

    public void AddAccounts(){
        ArrayList<String> cuentas = new ArrayList<String>();

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getActivity().getApplicationContext(),"MoneyControl", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String Nombre="";
        String Banco = "";

        Cursor fila = bd.rawQuery(
                "select NumeroCuenta, Banco from Cuenta" , null);

        while(fila.moveToNext()){
            Nombre=fila.getString(0);
            Banco=fila.getString(1);
            cuentas.add(Nombre+ " "+ Banco);
        }

        bd.close();

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, cuentas); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOrigen.setAdapter(spinnerArrayAdapter);

    }


    public void UpdateAccountData(String numCuenta,String monto){

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getActivity().getApplicationContext(),"MoneyControl", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        Cursor fila = bd.rawQuery(
                "select Saldo from Cuenta where NumeroCuenta="+numCuenta , null);

        double saldo=0;
        if(fila.moveToFirst())
            saldo = fila.getDouble(0);

        ContentValues registro = new ContentValues();

        saldo -= Double.parseDouble(monto);

        registro.put("Saldo", String.valueOf(saldo));
        int cant = bd.update("Cuenta", registro, "NumeroCuenta=" + numCuenta, null);
        bd.close();

      /*  if (cant == 1)
            Toast.makeText(this, "Se Actualizaron los datos", Toast.LENGTH_SHORT)
                    .show();
        else
            Toast.makeText(this, "No existe NumeroCuenta",
                    Toast.LENGTH_SHORT).show();
        */

    }


    public void AddAccountData(String numCuenta,String monto){

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getActivity().getApplicationContext(),"MoneyControl", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        Cursor fila = bd.rawQuery(
                "select Saldo from Cuenta where NumeroCuenta="+numCuenta , null);

        double saldo=0;
        if(fila.moveToFirst())
            saldo = fila.getDouble(0);

        ContentValues registro = new ContentValues();

        saldo += Double.parseDouble(monto);

        registro.put("Saldo", String.valueOf(saldo));
        int cant = bd.update("Cuenta", registro, "NumeroCuenta=" + numCuenta, null);
        bd.close();

      /*  if (cant == 1)
            Toast.makeText(this, "Se Actualizaron los datos", Toast.LENGTH_SHORT)
                    .show();
        else
            Toast.makeText(this, "No existe NumeroCuenta",
                    Toast.LENGTH_SHORT).show();*/

    }

}