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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_home, container, false);


        ArrayList<AccountItem> cuentas = GetlistAccounts();
        final ListView lv = (ListView)rootView.findViewById(R.id.listViewResumen);
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
                "select Banco, Moneda,SaldoInicial from Cuenta" , null);

        while(fila.moveToNext()){
            Banco=fila.getString(0);
            Moneda=fila.getString(1);
            numeroCuenta=fila.getInt(2);
            cuentas.add(new AccountItem(Banco,Moneda,numeroCuenta));
        }

        bd.close();

        return cuentas;
    }

}

