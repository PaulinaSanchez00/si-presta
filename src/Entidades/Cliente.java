/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

public class Cliente {
    private int id;
    private String nombre_cliente, apellido_cliente, fecha_nac;
    private boolean Deuda;
    private String calle, Num_Ext, Colonia, Ciudad, CodPostal, Municipio, Estado, Telefono;
    private String calle_tra, Num_Ext_tra, Colonia_tra, Ciudad_tra, CodPostal_tra, Municipio_tra, Estado_tra, Nombre_Jefe, Apellido_Jefe, Telefono_Jefe, Puesto, Correo_Elect;
    private int documentos_id;

    public Cliente(int id) {
        this.id = id;
    }
    
    public Cliente(int id, String nombre_cliente, String apellido_cliente, String fecha_nac, boolean Deuda, String calle, String Num_Ext, String Colonia, String Ciudad, String CodPostal, String Municipio, String Estado, String Telefono, String calle_tra, String Num_Ext_tra, String Colonia_tra, String Ciudad_tra, String CodPostal_tra, String Municipio_tra, String Estado_tra, String Nombre_Jefe, String Apellido_Jefe, String Telefono_Jefe, String Puesto, String Correo_Elect, int documentos_id) {
        this.id = id;
        this.nombre_cliente = nombre_cliente;
        this.apellido_cliente = apellido_cliente;
        this.fecha_nac = fecha_nac;
        this.Deuda = Deuda;
        this.calle = calle;
        this.Num_Ext = Num_Ext;
        this.Colonia = Colonia;
        this.Ciudad = Ciudad;
        this.CodPostal = CodPostal;
        this.Municipio = Municipio;
        this.Estado = Estado;
        this.Telefono = Telefono;
        this.calle_tra = calle_tra;
        this.Num_Ext_tra = Num_Ext_tra;
        this.Colonia_tra = Colonia_tra;
        this.Ciudad_tra = Ciudad_tra;
        this.CodPostal_tra = CodPostal_tra;
        this.Municipio_tra = Municipio_tra;
        this.Estado_tra = Estado_tra;
        this.Nombre_Jefe = Nombre_Jefe;
        this.Apellido_Jefe = Apellido_Jefe;
        this.Telefono_Jefe = Telefono_Jefe;
        this.Puesto = Puesto;
        this.Correo_Elect = Correo_Elect;
        this.documentos_id = documentos_id;
    }

    public Cliente() {
    }

    public int getId() {
        return id;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public String getApellido_cliente() {
        return apellido_cliente;
    }

    public String getFecha_nac() {
        return fecha_nac;
    }

    public boolean isDeuda() {
        return Deuda;
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

    public String getTelefono() {
        return Telefono;
    }

    public String getCalle_tra() {
        return calle_tra;
    }

    public String getNum_Ext_tra() {
        return Num_Ext_tra;
    }

    public String getColonia_tra() {
        return Colonia_tra;
    }

    public String getCiudad_tra() {
        return Ciudad_tra;
    }

    public String getCodPostal_tra() {
        return CodPostal_tra;
    }

    public String getMunicipio_tra() {
        return Municipio_tra;
    }

    public String getEstado_tra() {
        return Estado_tra;
    }

    public String getNombre_Jefe() {
        return Nombre_Jefe;
    }

    public String getApellido_Jefe() {
        return Apellido_Jefe;
    }

    public String getTelefono_Jefe() {
        return Telefono_Jefe;
    }

    public String getPuesto() {
        return Puesto;
    }

    public String getCorreo_Elect() {
        return Correo_Elect;
    }

    public int getDocumentos_id() {
        return documentos_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public void setApellido_cliente(String apellido_cliente) {
        this.apellido_cliente = apellido_cliente;
    }

    public void setFecha_nac(String fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public void setDeuda(boolean Deuda) {
        this.Deuda = Deuda;
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

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public void setCalle_tra(String calle_tra) {
        this.calle_tra = calle_tra;
    }

    public void setNum_Ext_tra(String Num_Ext_tra) {
        this.Num_Ext_tra = Num_Ext_tra;
    }

    public void setColonia_tra(String Colonia_tra) {
        this.Colonia_tra = Colonia_tra;
    }

    public void setCiudad_tra(String Ciudad_tra) {
        this.Ciudad_tra = Ciudad_tra;
    }

    public void setCodPostal_tra(String CodPostal_tra) {
        this.CodPostal_tra = CodPostal_tra;
    }

    public void setMunicipio_tra(String Municipio_tra) {
        this.Municipio_tra = Municipio_tra;
    }

    public void setEstado_tra(String Estado_tra) {
        this.Estado_tra = Estado_tra;
    }

    public void setNombre_Jefe(String Nombre_Jefe) {
        this.Nombre_Jefe = Nombre_Jefe;
    }

    public void setApellido_Jefe(String Apellido_Jefe) {
        this.Apellido_Jefe = Apellido_Jefe;
    }

    public void setTelefono_Jefe(String Telefono_Jefe) {
        this.Telefono_Jefe = Telefono_Jefe;
    }

    public void setPuesto(String Puesto) {
        this.Puesto = Puesto;
    }

    public void setCorreo_Elect(String Correo_Elect) {
        this.Correo_Elect = Correo_Elect;
    }

    public void setDocumentos_id(int documentos_id) {
        this.documentos_id = documentos_id;
    }
}


