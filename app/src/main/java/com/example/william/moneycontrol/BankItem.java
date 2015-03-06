package com.example.william.moneycontrol;

/**
 * Created by Jimmy Banegas on 18/02/2015.
 */
public class BankItem {
    int idBanco;
    String nombre;
    String descripcion;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BankItem(int idBanco, String nombre, String descripcion) {
        this.idBanco = idBanco;
        this.nombre = nombre;
        this.descripcion = descripcion;
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
