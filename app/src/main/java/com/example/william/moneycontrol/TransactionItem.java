package com.example.william.moneycontrol;

/**
 * Created by Jimmy Banegas on 18/02/2015.
 */
public class TransactionItem {
    int idTransaccion;
    String tipoTransaccion;
    int idCategoria;
    int cuentaFuente;
    int cuentaDestino;
    float montoTransaccion;
    String comentario;


    public TransactionItem(int idTransaccion,String tipoTransaccion,int idCategoria,int cuentaFuente,float montoTransaccion,String comentario){
        this.idTransaccion=idTransaccion;
        this.tipoTransaccion=tipoTransaccion;
        this.idCategoria=idCategoria;
        this.cuentaDestino=0;
        this.cuentaFuente=cuentaFuente;
        this.montoTransaccion=montoTransaccion;
        this.comentario=comentario;
    }

    public TransactionItem(int idTransaccion,String tipoTransaccion,int idCategoria,int cuentaFuente,int cuentaDestino,float montoTransaccion,String comentario){
        this.idTransaccion=idTransaccion;
        this.tipoTransaccion=tipoTransaccion;
        this.idCategoria=idCategoria;
        this.cuentaDestino=cuentaDestino;
        this.cuentaFuente=cuentaFuente;
        this.montoTransaccion=montoTransaccion;
        this.comentario=comentario;
    }

    public int getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getCuentaFuente() {
        return cuentaFuente;
    }

    public void setCuentaFuente(int cuentaFuente) {
        this.cuentaFuente = cuentaFuente;
    }

    public int getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(int cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    public float getMontoTransaccion() {
        return montoTransaccion;
    }

    public void setMontoTransaccion(float montoTransaccion) {
        this.montoTransaccion = montoTransaccion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

}
