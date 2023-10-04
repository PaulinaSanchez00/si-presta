
package Entidades;


public class Empleado {
    int Empleado_id, Rol_id;
    String Nombre, Apellido,Contrasena, Fecha_nac, calle,Num_Ext, Colonia, Ciudad, CodPostal,Municipio, Estado, Email, Telefono;
    boolean Estatus; 

    public Empleado() {
    }
    public Empleado(int Empleado_id) {
        this.Empleado_id = Empleado_id;
    }
    public Empleado(int Empleado_id, String Nombre, String Apellido, String Contrasena,boolean Estatus, String Fecha_nac, String calle, String Num_Ext, String Colonia, String Ciudad, String CodPostal, String Municipio, String Estado, String Email, String Telefono, int Rol_id) {
        this.Empleado_id = Empleado_id;
        this.Rol_id = Rol_id;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Contrasena = Contrasena;
        this.Fecha_nac = Fecha_nac;
        this.calle = calle;
        this.Num_Ext = Num_Ext;
        this.Colonia = Colonia;
        this.Ciudad = Ciudad;
        this.CodPostal = CodPostal;
        this.Municipio = Municipio;
        this.Estado = Estado;
        this.Email = Email;
        this.Telefono = Telefono;
        this.Estatus=Estatus;
    }

    public int getEmpleado_id() {
        return Empleado_id;
    }

    public int getRol_id() {
        return Rol_id;
    }

    public String getNombre() {
        return Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public String getContrasena() {
        return Contrasena;
    }

    public void setEstatus(boolean Estatus) {
        this.Estatus = Estatus;
    }

    public boolean isEstatus() {
        return Estatus;
    }

    public String getFecha_nac() {
        return Fecha_nac;
    }

    public String getCalle() {
        return calle;
    }

    public String getNum_Ext() {
        return Num_Ext;
    }

    public String getColonia() {
        return Colonia;
    }

    public String getCiudad() {
        return Ciudad;
    }

    public String getCodPostal() {
        return CodPostal;
    }

    public String getMunicipio() {
        return Municipio;
    }

    public String getEstado() {
        return Estado;
    }

    public String getEmail() {
        return Email;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setEmpleado_id(int Empleado_id) {
        this.Empleado_id = Empleado_id;
    }

    public void setRol_id(int Rol_id) {
        this.Rol_id = Rol_id;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public void setContrasena(String Contrasena) {
        this.Contrasena = Contrasena;
    }

    public void setFecha_nac(String Fecha_nac) {
        this.Fecha_nac = Fecha_nac;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setNum_Ext(String Num_Ext) {
        this.Num_Ext = Num_Ext;
    }

    public void setColonia(String Colonia) {
        this.Colonia = Colonia;
    }

    public void setCiudad(String Ciudad) {
        this.Ciudad = Ciudad;
    }

    public void setCodPostal(String CodPostal) {
        this.CodPostal = CodPostal;
    }

    public void setMunicipio(String Municipio) {
        this.Municipio = Municipio;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }
    
}
