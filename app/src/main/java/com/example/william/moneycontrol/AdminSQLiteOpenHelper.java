package com.example.william.moneycontrol;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by william on 2/5/15.
 */
public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {
    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Cuenta(IdCuenta integer primary key autoincrement,NumeroCuenta int" +
                ",Banco text, Moneda text,SaldoInicial real ,TipoCuenta text,Descripcion text)");

        db.execSQL("create table Categoria(IdCategoria integer primary key autoincrement," +
                "Nombre text ,TipoCategoria text,Descripcion text)");

        db.execSQL("create table Prestamo(IdPrestamo integer primary key autoincrement," +
                "Banco text ,Descripcion text,Monto real,Tasa real, Plazo real)");

        db.execSQL("create table Transaccion(IdTransaccion integer primary key autoincrement," +
                "Tipo text ,Categoria text,Monto real, Destino text, Fuente text,Fecha date, DComentario text)");

        db.execSQL("create table Prestamo(IdPrestamo integer primary key autoincrement," +
                "Banco text ,Descripcion text,Monto real,Tasa real, Plazo real)");


        db.execSQL("create table Banco(IdBnaco integer primary key autoincrement," +
                "Nombre text ,Descripcion text)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}