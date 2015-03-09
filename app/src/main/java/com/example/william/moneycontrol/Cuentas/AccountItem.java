package com.example.william.moneycontrol.Cuentas;

/**
 * Created by william on 2/18/15.
 */
public class AccountItem {

    int numeroCuenta;
    String moneda;
    double saldo;
    String tipo;
    String banco;
    String Descripcion;

    public AccountItem(int numeroCuenta, String moneda, double saldo, String tipo, String banco, String descripcion) {
        this.numeroCuenta = numeroCuenta;
        this.moneda = moneda;
        this.saldo = saldo;
        this.tipo = tipo;
        this.banco = banco;
        Descripcion = descripcion;
    }

    public AccountItem(String banco, String moneda, int numeroCuenta) {
        this.banco = banco;
        this.moneda = moneda;
        this.numeroCuenta = numeroCuenta;
    }

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public String getMoneda() {
        return moneda;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getTipo() {
        return tipo;
    }

    public String getBanco() {
        return banco;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setNumeroCuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
