package com.example.william.moneycontrol.Cuentas;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
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
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;

import com.example.william.moneycontrol.Helpers.AdminSQLiteOpenHelper;
import com.example.william.moneycontrol.R;

import java.util.ArrayList;

/**
 * Created by william on 1/31/15.
 */
public class CuentasFragment extends Fragment {


    private View rootView;
    private final ArrayList<AccountItem> cuentas = new ArrayList<AccountItem>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_cuentas, container, false);


        ImageButton btnNextScreen = (ImageButton) rootView.findViewById((R.id.btnNextScreen));

        btnNextScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getActivity().getApplicationContext(), CreateAccountActivity.class);

                startActivity(nextScreen);

            }
        });


        final ArrayList<AccountItem> cuentas = GetlistAccounts();
        final ListView lv = (ListView) rootView.findViewById(R.id.listViewLpsAccounts);
        lv.setAdapter(new ListViewAccountAdapter(getActivity().getApplicationContext(), cuentas));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent nextScreen = new Intent(getActivity().getApplicationContext(), AccountDetailsActivity.class);
                Log.e("Item at Position", parent.getAdapter().getItem(position).toString());

                registerForContextMenu(lv);
                lv.showContextMenu();

                nextScreen.putExtra("NumeroCuenta", cuentas.get(position).getNumeroCuenta());
                startActivity(nextScreen);
            }

        });


        return rootView;

    }

    private ArrayList<AccountItem> GetlistAccounts() {


        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(rootView.getContext(), "MoneyControl", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String Banco = "";
        String Moneda = "";
        Double Saldo;
        String NumeroCuenta = "";

        Cursor fila = bd.rawQuery(
                "select Banco, Moneda,Saldo,NumeroCuenta from Cuenta", null);

        while (fila.moveToNext()) {
            Banco = fila.getString(0);
            Moneda = fila.getString(1);
            Saldo = fila.getDouble(2);
            NumeroCuenta = fila.getString(3);
            cuentas.add(new AccountItem(NumeroCuenta, Banco, Moneda, Saldo));
        }

        bd.close();

        return cuentas;
    }

}