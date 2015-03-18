package com.example.william.moneycontrol.Prestamos;

/**
 * Created by Jimmy Banegas on 18/02/2015.
 */
public class LoanItem {
    int idPrestamo;
    String Banco;
    String descripcion;
    float monto;
    float tasa_interes;
    float plazo_meses;


    public  LoanItem(int idPrestamo,String Banco,String descripcion,float monto,float tasa_interes,float plazo_meses){
        this.idPrestamo=idPrestamo;
        this.Banco=Banco;
        this.descripcion=descripcion;
        this.monto=monto;
        this.tasa_interes=tasa_interes;
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
        return tasa_interes;
    }

    public void setTasa_interés(float tasa_interés) {
        this.tasa_interes = tasa_interés;
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

    public String getBanco() {
        return Banco;
    }

    public void setIdBanco(String idBanco) {
        this.Banco = idBanco;
    }
}
