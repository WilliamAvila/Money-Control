package com.example.william.moneycontrol;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.william.moneycontrol.Cuentas.AccountInfoActivity;
import com.example.william.moneycontrol.Cuentas.AccountItem;
import com.example.william.moneycontrol.Cuentas.ListViewAccountAdapter;
import com.example.william.moneycontrol.Cuentas.TimeDialogFragment;
import com.example.william.moneycontrol.Helpers.AdminSQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by william on 1/31/15.
 */
public class HomeFragment extends Fragment {

    private View rootView;
    private final  ArrayList<AccountItem> cuentas=new ArrayList<AccountItem>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_home, container, false);


        final ArrayList<AccountItem> cuentas = GetlistAccounts();
        final ListView lv = (ListView)rootView.findViewById(R.id.listViewResumen);
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


        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(rootView.getContext(),"MoneyControl", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String Banco="";
        String Moneda="";
        Double Saldo;
        String NumeroCuenta="";

        Cursor fila = bd.rawQuery(
                "select Banco, Moneda,Saldo,NumeroCuenta from Cuenta" , null);

        while(fila.moveToNext()){
            Banco=fila.getString(0);
            Moneda=fila.getString(1);
            Saldo=fila.getDouble(2);
            NumeroCuenta=fila.getString(3);
            cuentas.add(new AccountItem(NumeroCuenta,Banco,Moneda,Saldo));
        }

        bd.close();

        return cuentas;
    }

   @Override
    public void onResume() {
       super.onResume();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){

                    new AlertDialog.Builder(getActivity())
                            .setMessage("¿Desea salir?")
                            .setCancelable(false)
                            .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    getActivity().finish();
                                }
                            })
                            .setNegativeButton("No", null)
                            .show();

                    return true;
                }
                return false;
            }
        });
    }
}

