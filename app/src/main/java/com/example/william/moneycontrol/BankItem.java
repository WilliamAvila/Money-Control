package com.example.william.moneycontrol;

/**
 * Created by Jimmy Banegas on 18/02/2015.
 */
public class BankItem {

    int idBanco;
    String nombre;

    public BankItem(int idBanco, String nombre) {
        this.idBanco = idBanco;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(int idBanco) {
        this.idBanco = idBanco;
    }

}
