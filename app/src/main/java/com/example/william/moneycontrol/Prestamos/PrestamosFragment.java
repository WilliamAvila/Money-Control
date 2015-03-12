package com.example.william.moneycontrol.Prestamos;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.william.moneycontrol.Helpers.AdminSQLiteOpenHelper;
import com.example.william.moneycontrol.R;

import java.util.ArrayList;

public class PrestamosFragment extends Fragment {

    private View rootView;
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

        ArrayList<LoanItem> prestamos = GetlistPrestamos();
        ListView lv = (ListView)rootView.findViewById(R.id.listViewLoans);

        lv.setAdapter(new ListViewLoanAdapter(getActivity().getApplicationContext(), prestamos));

        return rootView;

    }
    private ArrayList<LoanItem> GetlistPrestamos(){
        ArrayList<LoanItem> prestamos = new ArrayList<LoanItem>();

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(rootView.getContext(),"MoneyControl", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String descripcion="";
        int idPrestamo=0;
        int idBanco;
        float monto;
        float tasa_interés;
        float plazo_meses;

        Cursor fila = bd.rawQuery(
                "select Banco,Descripcion,Monto,Plazo,Tasa from Prestamo" , null);

        while(fila.moveToNext()){
            idPrestamo=fila.getInt(0);
            idBanco=fila.getInt(1);
            descripcion=fila.getString(2);
            monto=fila.getFloat(3);
            plazo_meses=fila.getFloat(4);
            tasa_interés=fila.getFloat(5);
            prestamos.add(new LoanItem(idPrestamo,idBanco,descripcion,monto,tasa_interés,plazo_meses));
        }

        bd.close();

        return prestamos;
    }

}
