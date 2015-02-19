package com.example.william.moneycontrol;

/**
 * Created by Jimmy Banegas on 18/02/2015.
 */
public class CategoryItem {
    int idCategoria;
    String nombre;
    String descripcion;
    String tipo;


    public CategoryItem(int idCategoria, String descripcion,String nombre,String tipo) {
        this.idCategoria = idCategoria;
        this.nombre= nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
