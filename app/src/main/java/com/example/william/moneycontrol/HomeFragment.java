package com.example.william.moneycontrol;

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
import android.widget.ListView;
import android.widget.TextView;

import com.example.william.moneycontrol.Cuentas.AccountInfoActivity;
import com.example.william.moneycontrol.Cuentas.AccountItem;
import com.example.william.moneycontrol.Cuentas.ListViewAccountAdapter;
import com.example.william.moneycontrol.Helpers.AdminSQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by william on 1/31/15.
 */
public class HomeFragment extends Fragment {

    private View rootView;
    private final  ArrayList<AccountItem> cuentas=new ArrayList<AccountItem>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_home, container, false);


        final ArrayList<AccountItem> cuentas = GetlistAccounts();
        final ListView lv = (ListView)rootView.findViewById(R.id.listViewResumen);
        lv.setAdapter(new ListViewAccountAdapter(getActivity().getApplicationContext(), cuentas));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent nextScreen = new Intent(getActivity().getApplicationContext(), AccountInfoActivity.class);
                Log.e("Item at Position", parent.getAdapter().getItem(position).toString());

                registerForContextMenu(lv);
                lv.showContextMenu();

                nextScreen.putExtra("NumeroCuenta",cuentas.get(position).getNumeroCuenta());
                startActivity(nextScreen);
            }

        });


        return rootView;

    }
    private ArrayList<AccountItem> GetlistAccounts(){


        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(rootView.getContext(),"MoneyControl", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String Banco="";
        String Moneda="";
        Double Saldo;
        String NumeroCuenta="";

        Cursor fila = bd.rawQuery(
                "select Banco, Moneda,Saldo,NumeroCuenta from Cuenta" , null);

        while(fila.moveToNext()){
            Banco=fila.getString(0);
            Moneda=fila.getString(1);
            Saldo=fila.getDouble(2);
            NumeroCuenta=fila.getString(3);
            cuentas.add(new AccountItem(NumeroCuenta,Banco,Moneda,Saldo));
        }

        bd.close();

        return cuentas;
    }

}

