package com.example.william.moneycontrol.Configuraciones;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.william.moneycontrol.Helpers.ShowExchangeRatesActivity;
import com.example.william.moneycontrol.R;


public class ConfigFragment extends Fragment {

    private View rootView;
    public ConfigFragment() {
        // Required empty public constructor
    }

    static final int REQUEST_LINK_TO_DBX = 0;  // This value is up to you

    private Button buttonExchange;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_config, container, false);


        buttonExchange = (Button) rootView.findViewById(R.id.buttonExchange);
        buttonExchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getActivity().getApplicationContext(), ShowExchangeRatesActivity.class);
                startActivity(nextScreen);
            }
        });






        return rootView;
    }




}
