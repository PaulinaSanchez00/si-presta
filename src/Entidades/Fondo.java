
package Entidades;


public class Fondo {
    //Pagos
    private int Empleado;
    private String fecha_reg;
    private double Total;
    private int Pagos;
    
    //Retiros
    private int empleado_id;
    private double Retirado;
    private String fecha1;
    
    //Retiro Tabla
    private int Retiro_id;
    private String Fecha;
    private double retiro;
    private int id;
    

    public Fondo() {
    }

    public Fondo(int empleado_id, double Retirado, String fecha1) {
        this.Retirado = Retirado;
        this.empleado_id = empleado_id;
        this.fecha1 = fecha1;
    }
    

    public Fondo(int Empleado, String fecha_reg, double Total, int Pagos) {
        this.Empleado = Empleado;
        this.fecha_reg = fecha_reg;
        this.Total = Total;
        this.Pagos = Pagos;
    }

    public String getFecha1() {
        return fecha1;
    }

    public void setFecha1(String fecha1) {
        this.fecha1 = fecha1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRetiro_id() {
        return Retiro_id;
    }

    public void setRetiro_id(int Retiro_id) {
        this.Retiro_id = Retiro_id;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public double getRetiro() {
        return retiro;
    }

    public void setRetiro(double retiro) {
        this.retiro = retiro;
    }

    public int getEmpleado_id() {
        return empleado_id;
    }

    public void setEmpleado_id(int empleado_id) {
        this.empleado_id = empleado_id;
    }
    

    public double getRetirado() {
        return Retirado;
    }

    public void setRetirado(double Retirado) {
        this.Retirado = Retirado;
    }

    public int getEmpleado() {
        return Empleado;
    }

    public void setEmpleado(int Empleado) {
        this.Empleado = Empleado;
    }

    public String getFecha_reg() {
        return fecha_reg;
    }

    public void setFecha_reg(String fecha_reg) {
        this.fecha_reg = fecha_reg;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }

    public int getPagos() {
        return Pagos;
    }

    public void setPagos(int Pagos) {
        this.Pagos = Pagos;
    }

}
