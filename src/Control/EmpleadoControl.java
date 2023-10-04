/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import DAO.EmpleadoDAO;
import Entidades.Empleado;
import Entidades.Variables;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;


public class EmpleadoControl {
    private final EmpleadoDAO DATOS;
    private Empleado obj;
    private DefaultTableModel modeloTabla;
    public int RegMost;

    public EmpleadoControl() {
        this.DATOS = new EmpleadoDAO();
        this.obj = new Empleado();
        this.RegMost = 0;
    }
    public DefaultTableModel listar (String Texto, int TotalPorpagina,int numPagina){
    List<Empleado> lista = new ArrayList();
        lista.addAll(DATOS.listar(Texto, TotalPorpagina, numPagina));
        
        String[] titulos = {"Empleado id","Nombre","Apellido","Contraseña","Estatus","Fecha nacimiento","Calle", "NumExt","Colonia", "Ciudad","Codigo Postal","Municipio","Estado","Email","Telefono","Rol id", "Puesto","Descripcion"};
        this.modeloTabla = new DefaultTableModel(null,titulos);
        
        String estado;
        String[] registro = new String[18];
        this.RegMost=0;
        
        for(Empleado item:lista){
            if(item.isEstatus()){
                estado="Laborando";
            }else{
                estado="Descanso";
            }
            registro[0]=Integer.toString(item.getEmpleado_id());
            registro[1]=item.getNombre();
            registro[2]=item.getApellido();
            registro[3]=item.getContrasena();
            registro[4]=estado;
            registro[5]=item.getFecha_nac();
            registro[6]=item.getCalle();
            registro[7]=item.getNum_Ext();
            registro[8]=item.getColonia();
            registro[9]=item.getCiudad();
            registro[10]=item.getCodPostal();
            registro[11]=item.getMunicipio();
            registro[12]=item.getEstado();
            registro[13]=item.getEmail();
            registro[14]=item.getTelefono();
            registro[15]=Integer.toString(item.getRol_id());
            
            this.modeloTabla.addRow(registro);
            this.RegMost++;
        }
        return this.modeloTabla;
    }
    public String login(int id, String contrasena){
        String resp="0";
        Empleado ver=this.DATOS.login(id, contrasena);
        if(ver!=null){
            if(ver.isEstatus()){
                Variables.EmpleadoId = ver.getEmpleado_id();
                Variables.EmpleadoNombre = ver.getNombre();
                Variables.rolId=ver.getRol_id();
                resp="1";
                
            }else{
                resp="2";
            }
        }
        return resp;
    }
    public String Insertar(String Nombre, String Apellido, String Contrasena,boolean Estatus,String Fecha_nac, String calle, String Num_Ext, String Colonia, String Ciudad, String CodPostal, String Municipio, String Estado, String Email, String Telefono,int Rol_id){
        if (DATOS.existe(Nombre)){
            return "El registro ya existe.";
            
        }else{
            obj.setNombre(Nombre);
            obj.setApellido(Apellido);
            obj.setContrasena(Contrasena);
            obj.setEstatus(Estatus);
            obj.setFecha_nac(Fecha_nac);
            obj.setCalle(calle);
            obj.setNum_Ext(Num_Ext);
            obj.setColonia(Colonia);
            obj.setCiudad(Ciudad);
            obj.setCodPostal(CodPostal);
            obj.setMunicipio(Municipio);
            obj.setEstado(Estado);
            obj.setEmail(Email);
            obj.setTelefono(Telefono);
            obj.setRol_id(Rol_id);
            if(DATOS.Insertar(obj)){
                return "OK";
            }else{
                return "Error en el registro.";
            }
        }
    }
    public String Actualizar(String Nombre, String Apellido, String Contrasena, String Fecha_nac,String NombreAnt, String calle, String Num_Ext, String Colonia, String Ciudad, String CodPostal, String Municipio, String Estado, String Email, String Telefono,int Rol_id,int Empleado_id){
        if(Nombre.equals(NombreAnt)){
            obj.setNombre(Nombre);
            obj.setApellido(Apellido);
            obj.setContrasena(Contrasena);
            obj.setFecha_nac(Fecha_nac);
            obj.setCalle(calle);
            obj.setNum_Ext(Num_Ext);
            obj.setColonia(Colonia);
            obj.setCiudad(Ciudad);
            obj.setCodPostal(CodPostal);
            obj.setMunicipio(Municipio);
            obj.setEstado(Estado);
            obj.setEmail(Email);
            obj.setTelefono(Telefono);
            obj.setRol_id(Rol_id);
            obj.setEmpleado_id(Empleado_id);
            if(DATOS.Actualizar(obj)){
                return "OK";
            }else{
                return "Error en la actualizacion.";
            }
            
        }else{
            if(DATOS.existe(Nombre)){
            return "El registro ya existe";
            }else{
                obj.setNombre(Nombre);
                obj.setApellido(Apellido);
                obj.setContrasena(Contrasena);
                obj.setFecha_nac(Fecha_nac);
                obj.setCalle(calle);
                obj.setNum_Ext(Num_Ext);
                obj.setColonia(Colonia);
                obj.setCiudad(Ciudad);
                obj.setCodPostal(CodPostal);
                obj.setMunicipio(Municipio);
                obj.setEstado(Estado);
                obj.setEmail(Email);
                obj.setTelefono(Telefono);
                obj.setRol_id(Rol_id);
                if (DATOS.Insertar(obj)) {
                    return "OK";
                } else {
                    return "Error en la actualizacion.";
                }
            }
        }
    }
    public String Desactivar (int Id){
        if(DATOS.Desactivar(Id)){
            return "OK";
        }else{
            return "No se pudo desactivar el registro";
        }
    }
    public String Activar(int Id){
        if(DATOS.Activar(Id)){
            return "OK";
        }else{
            return "No se pudo activar el registro";
        }
    }
    public int Total(){
        return DATOS.Total();
    }
    
    public int TotalMostrados(){
        return this.RegMost;
    }
    public DefaultTableModel listarid2(String texto){
        List<Empleado> lista=new ArrayList();
        lista.addAll(DATOS.listarid2(texto));
        
        String[] titulos={"ID Empleado"};
        this.modeloTabla=new DefaultTableModel(null,titulos);        

        String[] registro = new String[1];
        this.RegMost=0;
        for (Empleado item:lista){
            registro[0]=Integer.toString(item.getEmpleado_id());

            this.modeloTabla.addRow(registro);
            this.RegMost=this.RegMost+1;
        }
        return this.modeloTabla;
    }
}
