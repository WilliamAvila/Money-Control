package com.example.william.moneycontrol;


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

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoriasFragment extends Fragment {

   private View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_categorias, container, false);


        ImageButton btnNextScreen = (ImageButton) rootView.findViewById((R.id.btnNuevaCategoria));

        btnNextScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getActivity().getApplicationContext(),CreateCategoryActivity.class);

                startActivity(nextScreen);

            }
        });

      /*  ArrayList<CategoryItem> categorias = GetlistCategorias();
        ListView lv = (ListView)rootView.findViewById(R.id.listViewLpsAccounts);

        lv.setAdapter(new ListViewAccountAdapter(getActivity().getApplicationContext(), categorias));*/

        return rootView;

    }
    private ArrayList<CategoryItem> GetlistCategorias(){
        ArrayList<CategoryItem> categorias = new ArrayList<CategoryItem>();

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(rootView.getContext(),"MoneyControl", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String tipo="";
        String descripcion="";
        String nombre="";
        int idCategoria=0;

        Cursor fila = bd.rawQuery(
                "select TipoCategoria, Nombre,Descripcion from Categoria" , null);

        while(fila.moveToNext()){
            idCategoria=fila.getInt(0);
            tipo=fila.getString(1);
            nombre=fila.getString(2);
            descripcion=fila.getString(3);
            categorias.add(new CategoryItem(idCategoria,descripcion,nombre,tipo));
        }

        bd.close();

        return categorias;
    }

}
