package com.example.william.moneycontrol.Prestamos;

import android.app.DialogFragment;
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
import android.widget.Spinner;
import android.widget.TextView;

import com.example.william.moneycontrol.Helpers.AdminSQLiteOpenHelper;
import com.example.william.moneycontrol.R;
import com.example.william.moneycontrol.Transacciones.CreateGastoIngresoActivity;
import com.example.william.moneycontrol.Transacciones.CreateTransferActivity;

import java.util.ArrayList;

/**
 * Created by Affisa-Jimmy on 18/03/2015.
 */
public class PagoCuotasFragment extends DialogFragment {
    int numeroCuenta;
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
        numeroCuenta =  b.getInt("SaldoCuota");

        getDialog().setTitle("Pago de cuota");

        spinnerOrigen = (Spinner) v.findViewById(R.id.spinnerCuentas);

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

}