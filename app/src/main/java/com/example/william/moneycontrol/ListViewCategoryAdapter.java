package com.example.william.moneycontrol;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jimmy Banegas on 05/03/2015.
 */
public class ListViewCategoryAdapter extends BaseAdapter {
    private  static ArrayList<CategoryItem> listCategorias  ;
    private LayoutInflater mInflater;

    public ListViewCategoryAdapter(Context CategoriasFragment, ArrayList<CategoryItem> results){
        listCategorias = results;
        mInflater = LayoutInflater.from(CategoriasFragment);
    }


    @Override
    public int getCount() {
        return listCategorias.size();
    }

    @Override
    public Object getItem(int position) {
        return listCategorias.get(position);
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

        holder.txtNombre.setText(listCategorias.get(position).getNombre());
        holder.txtTipo.setText(listCategorias.get(position).getTipo());
     //   holder.txtNumeroCuenta.setText(String.valueOf(listCategorias.get(position)()));

        return convertView;
    }

    static class ViewHolder{
        TextView txtNombre, txtTipo;
    }
}
