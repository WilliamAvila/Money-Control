package com.example.william.moneycontrol.Prestamos;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.william.moneycontrol.Cuentas.AccountInfoActivity;
import com.example.william.moneycontrol.Cuentas.AccountItem;
import com.example.william.moneycontrol.Helpers.AdminSQLiteOpenHelper;
import com.example.william.moneycontrol.R;

import java.util.ArrayList;

public class PrestamosFragment extends Fragment {

    private View rootView;
    private final  ArrayList<LoanItem> prestamos=new ArrayList<LoanItem>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_prestamos, container, false);


        ImageButton btnNextScreen = (ImageButton) rootView.findViewById((R.id.btnNuevoPrestamo));

        btnNextScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getActivity().getApplicationContext(),CreateLoanActivity.class);

                startActivity(nextScreen);

            }
        });

        final ArrayList<LoanItem> prestamos = GetlistPrestamos();
        final ListView lv = (ListView)rootView.findViewById(R.id.listViewLoans);

        lv.setAdapter(new ListViewLoanAdapter(getActivity().getApplicationContext(), prestamos));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent nextScreen = new Intent(getActivity().getApplicationContext(), PagoPrestamos.class);

                registerForContextMenu(lv);
                lv.showContextMenu();


                nextScreen.putExtra("NumeroPrestamo",prestamos.get(position).getIdPrestamo());

                Log.d("Num Prestamo On Click", String.valueOf(prestamos.get(position).getIdPrestamo()));

                startActivity(nextScreen);
            }

        });

        return rootView;

    }
    private ArrayList<LoanItem> GetlistPrestamos(){

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(rootView.getContext(),"MoneyControl", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String descripcion="";
        int idPrestamo=0;
        String idBanco;
        float monto;
        float tasa_interes;
        float plazo_meses;

        Cursor fila = bd.rawQuery(
                "select IdPrestamo, Banco,Descripcion,Monto,Plazo,Tasa,Fecha from Prestamo" , null);

        while(fila.moveToNext()){
            idPrestamo=fila.getInt(0);
            idBanco=fila.getString(1);
            descripcion=fila.getString(2);
            monto=fila.getFloat(3);
            plazo_meses=fila.getFloat(4);
            tasa_interes=fila.getFloat(5);
            Log.d("Valor idPrestamo",String.valueOf(idPrestamo));
            prestamos.add(new LoanItem(String.valueOf(idPrestamo),idBanco,descripcion,monto,tasa_interes,plazo_meses));
        }

        bd.close();

        return prestamos;
    }

}
