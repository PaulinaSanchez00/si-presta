
package Entidades;


public class Corte {
    private int Corte_id;
    private String Fecha_corte;
    private double Saldo, Retirado, Fondo, Total_Caja,Diferencia;
    private int Empleado_id;

    public Corte(int Corte_id, String Fecha_corte, double Saldo, double Retirado, double Fondo, double Total_Caja, double Diferencia, int Empleado_id) {
        this.Corte_id = Corte_id;
        this.Fecha_corte = Fecha_corte;
        this.Saldo = Saldo;
        this.Retirado = Retirado;
        this.Fondo = Fondo;
        this.Total_Caja = Total_Caja;
        this.Diferencia = Diferencia;
        this.Empleado_id = Empleado_id;
    }

    public Corte() {
    }

    public int getCorte_id() {
        return Corte_id;
    }

    public void setCorte_id(int Corte_id) {
        this.Corte_id = Corte_id;
    }

    public String getFecha_corte() {
        return Fecha_corte;
    }

    public void setFecha_corte(String Fecha_corte) {
        this.Fecha_corte = Fecha_corte;
    }

    public double getSaldo() {
        return Saldo;
    }

    public void setSaldo(double Saldo) {
        this.Saldo = Saldo;
    }

    public double getRetirado() {
        return Retirado;
    }

    public void setRetirado(double Retirado) {
        this.Retirado = Retirado;
    }

    public double getFondo() {
        return Fondo;
    }

    public void setFondo(double Fondo) {
        this.Fondo = Fondo;
    }

    public double getTotal_Caja() {
        return Total_Caja;
    }

    public void setTotal_Caja(double Total_Caja) {
        this.Total_Caja = Total_Caja;
    }

    public double getDiferencia() {
        return Diferencia;
    }

    public void setDiferencia(double Diferencia) {
        this.Diferencia = Diferencia;
    }

    public int getEmpleado_id() {
        return Empleado_id;
    }

    public void setEmpleado_id(int Empleado_id) {
        this.Empleado_id = Empleado_id;
    }

}
    
   
