package com.example.william.moneycontrol;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

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

        /*ArrayList<AccountItem> bancos = GetlistBancos();
        ListView lv = (ListView) rootView.findViewById(R.id.listViewBancos);

        lv.setAdapter(new ListViewAccountAdapter(getActivity().getApplicationContext(), bancos));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                Intent nextScreen = new Intent(getActivity().getApplicationContext(), CreateAccountActivity.class);

                startActivity(nextScreen);
            }
        });

*/
        return rootView;
    }

    private ArrayList<AccountItem> GetlistBancos() {
        return null;
    }

}