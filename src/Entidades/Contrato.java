/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author CHRISTOPHERURIELTAFO
 */
public class Contrato {
    private int id,cliente_id,documentos_id;
    private String fecha_reg,fecha_soli;
    private boolean Estado;
    private double monto;
    private int interes, cuotas;
    private String forma_pago;
    private double abono_min;
    private double interes_total;
    private double total_pagar;
    private String clausulas;
    private int empleado_id;

    public Contrato(int id) {
        this.id = id;
    }

    public Contrato(int id, int cliente_id, int documentos_id, String fecha_reg, String fecha_soli, boolean Estado, double monto, int interes, int cuotas, String forma_pago, double abono_min, double interes_total, double total_pagar, String clausulas, int empleado_id) {
        this.id = id;
        this.cliente_id = cliente_id;
        this.documentos_id = documentos_id;
        this.fecha_reg = fecha_reg;
        this.fecha_soli = fecha_soli;
        this.Estado = Estado;
        this.monto = monto;
        this.interes = interes;
        this.cuotas = cuotas;
        this.forma_pago = forma_pago;
        this.abono_min = abono_min;
        this.interes_total = interes_total;
        this.total_pagar = total_pagar;
        this.clausulas = clausulas;
        this.empleado_id = empleado_id;
    }

    public Contrato() {
    }

    public int getId() {
        return id;
    }

    public int getCliente_id() {
        return cliente_id;
    }

    public int getDocumentos_id() {
        return documentos_id;
    }

    public String getFecha_reg() {
        return fecha_reg;
    }

    public String getFecha_soli() {
        return fecha_soli;
    }

    public double getMonto() {
        return monto;
    }

    public int getInteres() {
        return interes;
    }

    public int getCuotas() {
        return cuotas;
    }

    public String getForma_pago() {
        return forma_pago;
    }

    public double getAbono_min() {
        return abono_min;
    }

    public double getInteres_total() {
        return interes_total;
    }

    public double getTotal_pagar() {
        return total_pagar;
    }

    public String getClausulas() {
        return clausulas;
    }

    public int getEmpleado_id() {
        return empleado_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public void setDocumentos_id(int documentos_id) {
        this.documentos_id = documentos_id;
    }

    public void setFecha_reg(String fecha_reg) {
        this.fecha_reg = fecha_reg;
    }

    public void setFecha_soli(String fecha_soli) {
        this.fecha_soli = fecha_soli;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public void setInteres(int interes) {
        this.interes = interes;
    }

    public void setCuotas(int cuotas) {
        this.cuotas = cuotas;
    }

    public void setForma_pago(String forma_pago) {
        this.forma_pago = forma_pago;
    }

    public void setAbono_min(double abono_min) {
        this.abono_min = abono_min;
    }

    public void setInteres_total(double interes_total) {
        this.interes_total = interes_total;
    }

    public void setTotal_pagar(double total_pagar) {
        this.total_pagar = total_pagar;
    }

    public void setClausulas(String clausulas) {
        this.clausulas = clausulas;
    }

    public void setEmpleado_id(int empleado_id) {
        this.empleado_id = empleado_id;
    }

    public boolean isEstado() {
        return Estado;
    }

    public void setEstado(boolean Estado) {
        this.Estado = Estado;
    }

    @Override
    public String toString() {
        return "Contrato{" + "id=" + id + ", cliente_id=" + cliente_id + ", documentos_id=" + documentos_id + ", fecha_reg=" + fecha_reg + ", fecha_soli=" + fecha_soli + ", Estado=" + Estado + ", monto=" + monto + ", interes=" + interes + ", cuotas=" + cuotas + ", forma_pago=" + forma_pago + ", abono_min=" + abono_min + ", interes_total=" + interes_total + ", total_pagar=" + total_pagar + ", clausulas=" + clausulas + ", empleado_id=" + empleado_id + '}';
    }   
}