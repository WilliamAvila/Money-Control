package com.example.william.moneycontrol;

/**
 * Created by Jimmy Banegas on 18/02/2015.
 */
public class LoanItem {
    int idPrestamo;
    int idBanco;
    String descripcion;
    float monto;
    float tasa_interés;
    float plazo_meses;


    public  LoanItem(int idPrestamo,int idBanco,String descripcion,float monto,float tasa_interés,float plazo_meses){
        this.idPrestamo=idPrestamo;
        this.idBanco=idBanco;
        this.descripcion=descripcion;
        this.monto=monto;
        this.tasa_interés=tasa_interés;
        this.plazo_meses=plazo_meses;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public float getTasa_interés() {
        return tasa_interés;
    }

    public void setTasa_interés(float tasa_interés) {
        this.tasa_interés = tasa_interés;
    }

    public float getPlazo_meses() {
        return plazo_meses;
    }

    public void setPlazo_meses(float plazo_meses) {
        this.plazo_meses = plazo_meses;
    }

      public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public int getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(int idBanco) {
        this.idBanco = idBanco;
    }
}
