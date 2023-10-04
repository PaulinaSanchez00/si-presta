/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author CHRISTOPHERURIELTAFO
 */
public class Pago {
    private int id ,cliente_id, contrato_id;
    private String fecha_reg,fecha_lim;
    private double monto, dinero,feria;
    private int empleado_id;

    public Pago() {
    }

    public Pago(int id, int cliente_id, int contrato_id, String fecha_reg, String fecha_lim, double monto, double dinero, double feria, int empleado_id) {
        this.id = id;
        this.cliente_id = cliente_id;
        this.contrato_id = contrato_id;
        this.fecha_reg = fecha_reg;
        this.fecha_lim = fecha_lim;
        this.monto = monto;
        this.dinero = dinero;
        this.feria = feria;
        this.empleado_id = empleado_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public int getContrato_id() {
        return contrato_id;
    }

    public void setContrato_id(int contrato_id) {
        this.contrato_id = contrato_id;
    }

    public String getFecha_reg() {
        return fecha_reg;
    }

    public void setFecha_reg(String fecha_reg) {
        this.fecha_reg = fecha_reg;
    }

    public String getFecha_lim() {
        return fecha_lim;
    }

    public void setFecha_lim(String fecha_lim) {
        this.fecha_lim = fecha_lim;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public double getDinero() {
        return dinero;
    }

    public void setDinero(double dinero) {
        this.dinero = dinero;
    }

    public double getFeria() {
        return feria;
    }

    public void setFeria(double feria) {
        this.feria = feria;
    }

    public int getEmpleado_id() {
        return empleado_id;
    }

    public void setEmpleado_id(int empleado_id) {
        this.empleado_id = empleado_id;
    }
    
    
}
