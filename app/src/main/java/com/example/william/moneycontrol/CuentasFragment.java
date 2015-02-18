package com.example.william.moneycontrol;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.res.TypedArray;

import java.util.ArrayList;

/**
 * Created by william on 1/31/15.
 */
public class CuentasFragment extends Fragment {


    private View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_cuentas, container, false);


        ImageButton btnNextScreen = (ImageButton) rootView.findViewById((R.id.btnNextScreen));



       btnNextScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getActivity().getApplicationContext(),SecondScreenActivity.class);

                startActivity(nextScreen);

            }
        });


        Button btn = (Button) rootView.findViewById((R.id.button));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(rootView.getContext(),"MoneyControl", null, 1);
                SQLiteDatabase bd = admin.getWritableDatabase();
                EditText et1 = (EditText)rootView.findViewById(R.id.editText);
                TextView tv1 =(TextView)rootView.findViewById(R.id.textViewBanco);
                TextView tv2 =(TextView)rootView.findViewById(R.id.textViewSaldo);
                String cod = et1.getText().toString();
                Cursor fila = bd.rawQuery(
                        "select Banco,SaldoInicial from Cuenta where IdCuenta=" + cod, null);


                if (fila.moveToFirst()) {
                    tv1.setText(fila.getString(0));
                    tv2.setText(fila.getString(1));
                } else
                    Toast.makeText(rootView.getContext(), "No existe cuenta",
                            Toast.LENGTH_SHORT).show();
                bd.close();

            }
        });



        ArrayList<AccountItem> cuentas = GetlistContact();
        ListView lv = (ListView)rootView.findViewById(R.id.listViewLpsAccounts);
        lv.setAdapter(new ListViewAccountAdapter(getActivity().getApplicationContext(), cuentas));

        return rootView;


    }
    private ArrayList<AccountItem> GetlistContact(){
        ArrayList<AccountItem> cuentas = new ArrayList<AccountItem>();



        cuentas.add(new AccountItem("Fichosa","dolar",001));

        return cuentas;
    }


//    public void consultaporNumero(View v) {
//
//
//        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(rootView.getContext(),"MoneyControl", null, 1);
//        SQLiteDatabase bd = admin.getWritableDatabase();
//        EditText et1 = (EditText)rootView.findViewById(R.id.editText);
//        TextView tv1 =(TextView)rootView.findViewById(R.id.textViewBanco);
//        TextView tv2 =(TextView)rootView.findViewById(R.id.textViewSaldo);
//        String cod = et1.getText().toString();
//        Cursor fila = bd.rawQuery(
//                "select Banco,SaldoInicial from Cuenta where IdCuenta=" + cod, null);
//
//
//        if (fila.moveToFirst()) {
//            tv1.setText(fila.getString(0));
//            tv2.setText(fila.getString(1));
//        } else
//            Toast.makeText(rootView.getContext(), "No existe cuenta",
//                    Toast.LENGTH_SHORT).show();
//        bd.close();
//    }



}
