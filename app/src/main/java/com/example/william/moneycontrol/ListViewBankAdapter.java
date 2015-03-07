package com.example.william.moneycontrol;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jimmy Banegas on 06/03/2015.
 */
public class ListViewBankAdapter extends BaseAdapter {

    private  static ArrayList<BankItem> listBancos;
    private LayoutInflater mInflater;

    public ListViewBankAdapter (Context BancosFragment, ArrayList<BankItem> results){
        listBancos = results;
        mInflater = LayoutInflater.from(BancosFragment);
    }

    @Override
    public int getCount() {
        return listBancos.size();
    }

    @Override
    public Object getItem(int position) {
        return listBancos.get(position);
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

            holder.txtNombre = (TextView) convertView.findViewById(R.id.txtViewBanco);
            holder.txtTipo = (TextView) convertView.findViewById(R.id.txtViewTipo);
            // holder.txtNumeroCuenta = (TextView) convertView.findViewById((R.id.txtViewNumeroCuenta));

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtNombre.setText(listBancos.get(position).getNombre());
        holder.txtTipo.setText(listBancos.get(position).getDescripcion());
        //   holder.txtNumeroCuenta.setText(String.valueOf(listBancos.get(position)()));

        return convertView;
    }

    static class ViewHolder{
        TextView txtNombre, txtTipo;
    }
}
