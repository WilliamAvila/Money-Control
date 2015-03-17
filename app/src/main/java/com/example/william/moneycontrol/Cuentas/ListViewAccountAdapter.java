package com.example.william.moneycontrol.Cuentas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.william.moneycontrol.Cuentas.AccountItem;
import com.example.william.moneycontrol.R;

import java.util.ArrayList;

/**
 * Created by william on 2/18/15.
 */
public class ListViewAccountAdapter extends BaseAdapter {
    private  static ArrayList<AccountItem> listCuentas;
    private LayoutInflater mInflater;
    private int[] colors = new int[] {0xFF33B5E5,
            0xFFAA66CC,
            0xFF99CC00,
            0xFFFFBB33,
            0xFFFF4444,
            0xFF0099CC,
            0xFF9933CC,
            0xFF669900,
            0xFFFF8800,
            0xFFCC0000,0xFFBB3377,0x99CC0077,0xFFBB33AA,0xFFBB3300,0xFF4444AA,0x30FF0000, 0x300000FF,0xFFEE3333 };

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

            int colorPos = position % colors.length;
            convertView.setBackgroundColor(colors[colorPos]);


            holder.txtBanco = (TextView) convertView.findViewById(R.id.txtViewBanco);
            holder.txtTipo = (TextView) convertView.findViewById(R.id.txtViewTipo);
            holder.txtNumeroCuenta = (TextView) convertView.findViewById((R.id.txtViewNumeroCuenta));

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtBanco.setText(listCuentas.get(position).getBanco());
        holder.txtTipo.setText(listCuentas.get(position).getMoneda());
        holder.txtNumeroCuenta.setText(String.valueOf(listCuentas.get(position).getSaldo()));

        return convertView;
    }

    static class ViewHolder{
        TextView txtBanco, txtTipo, txtNumeroCuenta;
    }
}
