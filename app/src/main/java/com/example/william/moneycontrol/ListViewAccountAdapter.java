package com.example.william.moneycontrol;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by william on 2/18/15.
 */
public class ListViewAccountAdapter extends BaseAdapter {
    private  static ArrayList<AccountItem> listCuentas;
    private LayoutInflater mInflater;

    public ListViewAccountAdapter(Context CuentasFragment, ArrayList<AccountItem> results){
        listCuentas = results;
        mInflater = LayoutInflater.from(CuentasFragment);
    }


    @Override
    public int getCount() {
        return listCuentas.size();
    }

    @Override
    public Object getItem(int position) {
        return listCuentas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.drawer_account_item, null);
            holder = new ViewHolder();
            holder.txtBanco = (TextView) convertView.findViewById(R.id.txtViewBanco);
            holder.txtTipo = (TextView) convertView.findViewById(R.id.txtViewTipo);
            holder.txtNumeroCuenta = (TextView) convertView.findViewById((R.id.txtViewNumeroCuenta));

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtBanco.setText(listCuentas.get(position).getBanco());
        holder.txtTipo.setText(listCuentas.get(position).getMoneda());
        holder.txtNumeroCuenta.setText(String.valueOf(listCuentas.get(position).getNumeroCuenta()));

        return convertView;
    }

    static class ViewHolder{
        TextView txtBanco, txtTipo, txtNumeroCuenta;
    }
}
