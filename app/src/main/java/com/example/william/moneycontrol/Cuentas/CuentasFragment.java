package com.example.william.moneycontrol.Cuentas;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.william.moneycontrol.AdminSQLiteOpenHelper;
import com.example.william.moneycontrol.R;

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
                Intent nextScreen = new Intent(getActivity().getApplicationContext(),CreateAccountActivity.class);

                startActivity(nextScreen);

            }
        });

        ArrayList<AccountItem> cuentas = GetlistAccounts();
        final ListView lv = (ListView)rootView.findViewById(R.id.listViewLpsAccounts);

        lv.setAdapter(new ListViewAccountAdapter(getActivity().getApplicationContext(), cuentas));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent nextScreen = new Intent(getActivity().getApplicationContext(), AccountInfoActivity.class);
                Log.e("hello", parent.getAdapter().getItem(position).toString());

                registerForContextMenu(lv);
                lv.showContextMenu();

                TextView tv = (TextView) view.findViewById(R.id.txtViewNumeroCuenta);
                nextScreen.putExtra("NumeroCuenta",tv.getText().toString());
                startActivity(nextScreen);
            }

        });



        return rootView;

    }
    private ArrayList<AccountItem> GetlistAccounts(){
        ArrayList<AccountItem> cuentas = new ArrayList<AccountItem>();

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(rootView.getContext(),"MoneyControl", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String Banco="";
        String Moneda="";
        int numeroCuenta=0;

        Cursor fila = bd.rawQuery(
                "select Banco, Moneda,numeroCuenta from Cuenta" , null);

       while(fila.moveToNext()){
           Banco=fila.getString(0);
           Moneda=fila.getString(1);
           numeroCuenta=fila.getInt(2);
           cuentas.add(new AccountItem(Banco,Moneda,numeroCuenta));

       }

        bd.close();

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
