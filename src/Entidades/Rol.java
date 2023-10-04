
package Entidades;


public class Rol {
    int Rol_id;
    String Pto_Empleado, Descripcion;

    public Rol() {
    }

    public Rol(int Rol_id, String Pto_Empleado, String Descripcion) {
        this.Rol_id = Rol_id;
        this.Pto_Empleado = Pto_Empleado;
        this.Descripcion = Descripcion;
    }

    public int getRol_id() {
        return Rol_id;
    }

    public String getPto_Empleado() {
        return Pto_Empleado;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setRol_id(int Rol_id) {
        this.Rol_id = Rol_id;
    }

    public void setPto_Empleado(String Pto_Empleado) {
        this.Pto_Empleado = Pto_Empleado;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
    
    
}
