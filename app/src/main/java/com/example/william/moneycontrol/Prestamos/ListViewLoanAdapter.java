package com.example.william.moneycontrol.Prestamos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.william.moneycontrol.Prestamos.LoanItem;
import com.example.william.moneycontrol.R;

import java.util.ArrayList;

/**
 * Created by Jimmy Banegas on 06/03/2015.
 */
public class ListViewLoanAdapter extends BaseAdapter {
    private  static ArrayList<LoanItem> listPrestamos;
    private LayoutInflater mInflater;

    public ListViewLoanAdapter(Context PrestamosFragment, ArrayList<LoanItem> results){
        listPrestamos = results;
        mInflater = LayoutInflater.from(PrestamosFragment);
    }


    @Override
    public int getCount() {
        return listPrestamos.size();
    }

    @Override
    public Object getItem(int position) {
        return listPrestamos.get(position);
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
            holder.txtIdPrestamo = (TextView) convertView.findViewById(R.id.txtViewBanco);
            holder.txtBanco = (TextView) convertView.findViewById(R.id.txtViewTipo);
            holder.txtMonto = (TextView) convertView.findViewById((R.id.txtViewNumeroCuenta));

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.txtIdPrestamo.setText(String.valueOf(listPrestamos.get(position).getIdPrestamo()));
        holder.txtBanco.setText(String.valueOf(listPrestamos.get(position).getMonto()));
        holder.txtMonto.setText(String.valueOf(listPrestamos.get(position).getBanco()));

        return convertView;
    }

    static class ViewHolder{
        TextView txtIdPrestamo, txtBanco,txtMonto;
    }
}
