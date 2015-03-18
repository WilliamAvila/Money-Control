package com.example.william.moneycontrol.Cuentas;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        rootView = inflater.inflate(R.layout.fragment_cuentas, container, false);


       ImageButton btnNextScreen = (ImageButton) rootView.findViewById((R.id.btnNextScreen));

       btnNextScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getActivity().getApplicationContext(),CreateAccountActivity.class);

                startActivity(nextScreen);

            }
        });

        final ArrayList<AccountItem> cuentas = GetlistAccounts();
        final ListView lv = (ListView)rootView.findViewById(R.id.listViewLpsAccounts);

        lv.setAdapter(new ListViewAccountAdapter(getActivity().getApplicationContext(), cuentas));

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                TimeDialogFragment tFragment = new TimeDialogFragment();

                /** Creating a bundle object to store the position of the selected country */
                Bundle b = new Bundle();

                /** Storing the position in the bundle object */
                b.putInt("position", position);
                b.putInt("NumCuenta", Integer.parseInt(cuentas.get(position).getNumeroCuenta()));

                /** Setting the bundle object as an argument to the DialogFragment object */
                tFragment.setArguments(b);

                /** Getting FragmentManager object */
                FragmentManager fragmentManager = getFragmentManager();

                /** Starting a FragmentTransaction */
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                /** Getting the previously created fragment object from the fragment manager */
                TimeDialogFragment tPrev =  ( TimeDialogFragment ) fragmentManager.findFragmentByTag("time_dialog");

                /** If the previously created fragment object still exists, then that has to be removed */
                if(tPrev!=null)
                    fragmentTransaction.remove(tPrev);

                /** Opening the fragment object */
                tFragment.show(fragmentTransaction, "time_dialog");

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
        String numeroCuenta="";
        double saldo;
        Cursor fila = bd.rawQuery(
                "select Banco, Moneda,numeroCuenta,Saldo from Cuenta" , null);

       while(fila.moveToNext()){
           Banco=fila.getString(0);
           Moneda=fila.getString(1);
           numeroCuenta=fila.getString(2);
           saldo=fila.getDouble(3);
           cuentas.add(new AccountItem(numeroCuenta,Banco,Moneda,saldo));

       }
        bd.close();

        return cuentas;
    }


}
