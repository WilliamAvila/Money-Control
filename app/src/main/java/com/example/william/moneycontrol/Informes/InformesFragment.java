package com.example.william.moneycontrol.Informes;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.william.moneycontrol.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class InformesFragment extends Fragment {


    private View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_informes, container, false);


        ListView listViewGastos;
        listViewGastos = (ListView)rootView.findViewById(R.id.listViewGastos);

        listViewGastos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                if (position == 0) {
                    Intent nextScreen = new Intent(getActivity().getApplicationContext(), GastosPorCategoria.class);
                    startActivity(nextScreen);
                } else if (position == 1) {
                    Intent nextScreen = new Intent(getActivity().getApplicationContext(), GastosPorDia.class);
                    startActivity(nextScreen);
                } else {
                    Intent nextScreen = new Intent(getActivity().getApplicationContext(), GastosPorMes.class);
                    startActivity(nextScreen);
                }

            }
        });

        ListView listViewIngresos;
        listViewIngresos = (ListView)rootView.findViewById(R.id.listViewIngresos);

        listViewIngresos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                if(position==0){
                    Intent nextScreen = new Intent(getActivity().getApplicationContext(),IngresosPorCategoria.class);
                    startActivity(nextScreen);
                }else if(position==1){
                    Intent nextScreen = new Intent(getActivity().getApplicationContext(),IngresosPorDia.class);
                    startActivity(nextScreen);
                }
                else{
                    Intent nextScreen = new Intent(getActivity().getApplicationContext(),IngresosPorMes.class);
                    startActivity(nextScreen);
                }

            }
        });

        return rootView;
    }
}
