package com.example.william.moneycontrol;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.william.moneycontrol.R;

/**
 * Created by william on 1/31/15.
 */
public class CuentasFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_cuentas, container, false);


        Button btnNextScreen = (Button) rootView.findViewById((R.id.btnNextScreen));



        btnNextScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getActivity().getApplicationContext(),SecondScreenActivity.class);

                startActivity(nextScreen);

            }
        });






        return rootView;


    }



}
