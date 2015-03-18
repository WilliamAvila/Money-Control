package com.example.william.moneycontrol.Cuentas;

import java.text.DateFormat;
import java.util.Date;
import java.util.TimeZone;

import android.app.DialogFragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.william.moneycontrol.Helpers.AdminSQLiteOpenHelper;
import com.example.william.moneycontrol.R;
import com.example.william.moneycontrol.Transacciones.CreateGastoIngresoActivity;
import com.example.william.moneycontrol.Transacciones.CreateTransferActivity;

import java.text.DateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Affisa-Jimmy on 18/03/2015.
 */
public class TimeDialogFragment extends DialogFragment {
    int numeroCuenta;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        /** Inflating layout for the dialog */
        View v = inflater.inflate(R.layout.dialog_layout, null);
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getActivity().getApplicationContext(),"MoneyControl", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        /** Getting the arguments passed to this fragment. Here we expects the selected item's position as argument */
        final Bundle b = getArguments();
        /** Setting the title for the dialog window */
        numeroCuenta =  b.getInt("NumCuenta");

        getDialog().setTitle("Cuenta # " + b.getInt("NumCuenta"));

        Cursor fila = bd.rawQuery(
                "select Banco,Moneda,SaldoInicial,TipoCuenta,Descripcion from Cuenta where NumeroCuenta=" + b.getInt("NumCuenta"), null);

        /** Getting the reference to the TextView object of the layout */
        TextView tv1 =(TextView) v.findViewById(R.id.txtBanco);
        TextView tv2 =(TextView) v.findViewById(R.id.txtSaldo);

        Button btnTransferencia = (Button) v.findViewById((R.id.btnTransfer));

        btnTransferencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getActivity().getApplicationContext(),CreateTransferActivity.class);
                startActivity(nextScreen);
                return;
            }
        });

        Button btnTransaccion = (Button) v.findViewById((R.id.btnTransact));

        btnTransaccion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getActivity().getApplicationContext(),CreateGastoIngresoActivity.class);

                Log.e("Numero cuenta :", String.valueOf(numeroCuenta));
                nextScreen.putExtra("NumeroCuenta",String.valueOf(numeroCuenta));
                startActivity(nextScreen);
                return;
            }
        });


        if (fila.moveToFirst()) {
            tv1.setText(fila.getString(0));
            tv2.setText(fila.getString(1)+" "+fila.getString(2));
        }

        /** Returns the View object */
        return v;
    }

}