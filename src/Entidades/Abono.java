/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author CHRISTOPHERURIELTAFO
 */
public class Abono {
    private int id;
    private int Cuota;
     private String Fecha;
    private double Monto;
    private boolean Estado;
    private int Cliente_id,Contrato_id;

    public Abono(int id) {
        this.id = id;
    }

    public Abono() {
    }

    public Abono(int id, int Cuota, String Fecha, double Monto, boolean Estado, int Cliente_id, int Contrato_id) {
        this.id = id;
        this.Cuota = Cuota;
        this.Fecha = Fecha;
        this.Monto = Monto;
        this.Estado = Estado;
        this.Cliente_id = Cliente_id;
        this.Contrato_id = Contrato_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCuota() {
        return Cuota;
    }

    public void setCuota(int Cuota) {
        this.Cuota = Cuota;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public double getMonto() {
        return Monto;
    }

    public void setMonto(double Monto) {
        this.Monto = Monto;
    }

    public boolean isEstado() {
        return Estado;
    }

    public void setEstado(boolean Estado) {
        this.Estado = Estado;
    }

    public int getCliente_id() {
        return Cliente_id;
    }

    public void setCliente_id(int Cliente_id) {
        this.Cliente_id = Cliente_id;
    }

    public int getContrato_id() {
        return Contrato_id;
    }

    public void setContrato_id(int Contrato_id) {
        this.Contrato_id = Contrato_id;
    }

    @Override
    public String toString() {
        return "Abono{" + "id=" + id + ", Cuota=" + Cuota + ", Fecha=" + Fecha + ", Monto=" + Monto + ", Estado=" + Estado + ", Cliente_id=" + Cliente_id + ", Contrato_id=" + Contrato_id + '}';
    }

}