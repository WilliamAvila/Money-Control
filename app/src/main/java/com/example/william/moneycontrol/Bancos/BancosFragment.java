package com.example.william.moneycontrol.Bancos;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.william.moneycontrol.Helpers.AdminSQLiteOpenHelper;
import com.example.william.moneycontrol.Cuentas.CreateAccountActivity;
import com.example.william.moneycontrol.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class BancosFragment extends Fragment {


    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_bancos, container, false);


        ImageButton btnNextScreen = (ImageButton) rootView.findViewById((R.id.btnNextScreen));

        btnNextScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getActivity().getApplicationContext(), CreateBankActivity.class);

                startActivity(nextScreen);

            }
        });

        ArrayList<BankItem> bancos = GetlistBancos();

        ListView lv = (ListView) rootView.findViewById(R.id.listViewBancos);

        lv.setAdapter(new ListViewBankAdapter(getActivity().getApplicationContext(), bancos));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent nextScreen = new Intent(getActivity().getApplicationContext(), CreateAccountActivity.class);

                startActivity(nextScreen);
            }
        });

        return rootView;
    }

    private ArrayList<BankItem> GetlistBancos() {

        ArrayList<BankItem> bancos = new ArrayList<BankItem>();

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(rootView.getContext(),"MoneyControl", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String descripcion="";
        String nombre="";
        int idBanco=0;

        Cursor fila = bd.rawQuery(
                "select idBanco, Nombre,Descripcion from Banco" , null);

        while(fila.moveToNext()){
            idBanco=fila.getInt(0);
            nombre=fila.getString(1);
            descripcion=fila.getString(2);
            bancos.add(new BankItem(idBanco, descripcion, nombre));
        }

        bd.close();

        return bancos;
    }

}