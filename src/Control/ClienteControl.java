/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import DAO.ClienteDAO;
import Entidades.Cliente;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CHRISTOPHERURIELTAFO
 */
public class ClienteControl {
    private final ClienteDAO DATOS;
    private Cliente obj;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;
    
    public ClienteControl() {
        this.DATOS = new ClienteDAO();
        this.obj = new Cliente();
        this.registrosMostrados=0;
    }
    
    
    public DefaultTableModel listar (String texto,int totalPorPagina,int numPagina){
        List<Cliente> lista=new ArrayList();
        lista.addAll(DATOS.listar(texto,totalPorPagina,numPagina));
        
        String[] titulos = {"ID","Nombre","Apellido","Fecha de Nacimiento","Deuda","Calle","Num. Ext.","Colonia","Ciudad","Cod. Postal","Municipio","Estado","Telefono","Calle Trabajo","Num. Ext.","Colonia","Ciudad","Cod. Postal","Municipio","Estado","Nombre Jefe","Apellido Jefe","Telefono","Puesto","Correo","ID Documentos"};
        this.modeloTabla = new DefaultTableModel(null,titulos);
        
        String estado;
        String[] registro = new String[26];
        this.registrosMostrados=0;
        for(Cliente item:lista){
            if(item.isDeuda()){
                estado="Pagado";
            }else{
                estado="Pendiente";
            }
            registro[0]=Integer.toString(item.getId());
            registro[1]=item.getNombre_cliente();
            registro[2]=item.getApellido_cliente();
            registro[3]=item.getFecha_nac();
            registro[4]=estado;
            registro[5]=item.getCalle();
            registro[6]=item.getNum_Ext();
            registro[7]=item.getColonia();
            registro[8]=item.getCiudad();
            registro[9]=item.getCodPostal();
            registro[10]=item.getMunicipio();
            registro[11]=item.getEstado();
            registro[12]=item.getTelefono();
            registro[13]=item.getCalle_tra();
            registro[14]=item.getNum_Ext_tra();
            registro[15]=item.getColonia_tra();
            registro[16]=item.getCiudad_tra();
            registro[17]=item.getCodPostal_tra();
            registro[18]=item.getMunicipio_tra();
            registro[19]=item.getEstado_tra();
            registro[20]=item.getNombre_Jefe();
            registro[21]=item.getApellido_Jefe();
            registro[22]=item.getTelefono_Jefe();
            registro[23]=item.getPuesto();
            registro[24]=item.getCorreo_Elect();
            registro[25]=Integer.toString(item.getDocumentos_id());
            this.modeloTabla.addRow(registro);
            this.registrosMostrados=this.registrosMostrados+1;
        }
        return this.modeloTabla;
        
    }
    
    public String Insertar(String nombre_cliente, String apellido_cliente, String fecha_nac, String calle, String Num_Ext, String Colonia, String Ciudad, String CodPostal, String Municipio, String Estado, String Telefono, String calle_tra, String Num_Ext_tra, String Colonia_tra, String Ciudad_tra, String CodPostal_tra, String Municipio_tra, String Estado_tra, String Nombre_Jefe, String Apellido_Jefe, String Telefono_Jefe, String Puesto, String Correo_Elect, int documentos_id){
        if (DATOS.existe(nombre_cliente, apellido_cliente)){
            return "El registro ya existe.";  
        }else{
            obj.setNombre_cliente(nombre_cliente);
            obj.setApellido_cliente(apellido_cliente);
            obj.setFecha_nac(fecha_nac);
            obj.setCalle(calle);
            obj.setNum_Ext(Num_Ext);
            obj.setColonia(Colonia);
            obj.setCiudad(Ciudad);
            obj.setCodPostal(CodPostal);
            obj.setMunicipio(Municipio);
            obj.setEstado(Estado);
            obj.setTelefono(Telefono);
            obj.setCalle_tra(calle_tra);
            obj.setNum_Ext_tra(Num_Ext_tra);
            obj.setColonia_tra(Colonia_tra);
            obj.setCiudad_tra(Ciudad_tra);
            obj.setCodPostal_tra(CodPostal_tra);
            obj.setMunicipio_tra(Municipio_tra);
            obj.setEstado_tra(Estado_tra);
            obj.setNombre_Jefe(Nombre_Jefe);
            obj.setApellido_Jefe(Apellido_Jefe);
            obj.setTelefono_Jefe(Telefono_Jefe);
            obj.setPuesto(Puesto);
            obj.setCorreo_Elect(Correo_Elect);
            obj.setDocumentos_id(documentos_id);
            if(DATOS.Insertar(obj)){
                return "OK";
            }else{
                return "Error en el registro.";
            }
        }
    }
    
    public String Actualizar(int id, String nombre_cliente, String Nombre_Ant, String apellido_cliente, String fecha_nac, String calle, String Num_Ext, String Colonia, String Ciudad, String CodPostal, String Municipio, String Estado, String Telefono, String calle_tra, String Num_Ext_tra, String Colonia_tra, String Ciudad_tra, String CodPostal_tra, String Municipio_tra, String Estado_tra, String Nombre_Jefe, String Apellido_Jefe, String Telefono_Jefe, String Puesto, String Correo_Elect, int documentos_id){
        if(nombre_cliente.equals(Nombre_Ant)){
            obj.setId(id);
            obj.setNombre_cliente(nombre_cliente);
            obj.setApellido_cliente(apellido_cliente);
            obj.setFecha_nac(fecha_nac);
            obj.setCalle(calle);
            obj.setNum_Ext(Num_Ext);
            obj.setColonia(Colonia);
            obj.setCiudad(Ciudad);
            obj.setCodPostal(CodPostal);
            obj.setMunicipio(Municipio);
            obj.setEstado(Estado);
            obj.setTelefono(Telefono);
            obj.setCalle_tra(calle_tra);
            obj.setNum_Ext_tra(Num_Ext_tra);
            obj.setColonia_tra(Colonia_tra);
            obj.setCiudad_tra(Ciudad_tra);
            obj.setCodPostal_tra(CodPostal_tra);
            obj.setMunicipio_tra(Municipio_tra);
            obj.setEstado_tra(Estado_tra);
            obj.setNombre_Jefe(Nombre_Jefe);
            obj.setApellido_Jefe(Apellido_Jefe);
            obj.setTelefono_Jefe(Telefono_Jefe);
            obj.setPuesto(Puesto);
            obj.setCorreo_Elect(Correo_Elect);
            obj.setDocumentos_id(documentos_id);
            if(DATOS.Actualizar(obj)){
                return "OK";
            }else{
                return "Error en la acctualización.";
            }
        }else{
            if(DATOS.existe(nombre_cliente,apellido_cliente)){
                return "El registro ya existe";
            }else{
            obj.setId(id);
            obj.setNombre_cliente(nombre_cliente);
            obj.setApellido_cliente(apellido_cliente);
            obj.setFecha_nac(fecha_nac);
            obj.setCalle(calle);
            obj.setNum_Ext(Num_Ext);
            obj.setColonia(Colonia);
            obj.setCiudad(Ciudad);
            obj.setCodPostal(CodPostal);
            obj.setMunicipio(Municipio);
            obj.setEstado(Estado);
            obj.setTelefono(Telefono);
            obj.setCalle_tra(calle_tra);
            obj.setNum_Ext_tra(Num_Ext_tra);
            obj.setColonia_tra(Colonia_tra);
            obj.setCiudad_tra(Ciudad_tra);
            obj.setCodPostal_tra(CodPostal_tra);
            obj.setMunicipio_tra(Municipio_tra);
            obj.setEstado_tra(Estado_tra);
            obj.setNombre_Jefe(Nombre_Jefe);
            obj.setApellido_Jefe(Apellido_Jefe);
            obj.setTelefono_Jefe(Telefono_Jefe);
            obj.setPuesto(Puesto);
            obj.setCorreo_Elect(Correo_Elect);
            obj.setDocumentos_id(documentos_id);
                if(DATOS.Actualizar(obj)){
                    return "OK";
                }else{
                    return "Error en la acctualización.";
                }
            }
        }
    }
    
    public String Desactivar (int Id){
        if(DATOS.Desactivar(Id)){
            return "OK";
        }else{
            return "No se pudo activar el registro";
        }
    }
    
    public String Activar(int Id){
        if(DATOS.Activar(Id)){
            return "OK";
        }else{
            return "No se pudo desactivar el registro";
        }
    }
    
    public int Total(){
        return DATOS.Total();
    }
    
    public int TotalMostrados(){
        return this.registrosMostrados;
    }
    
    public DefaultTableModel listarid2(String texto){
        List<Cliente> lista=new ArrayList();
        lista.addAll(DATOS.listarid2(texto));
        
        String[] titulos={"Id Cliente"};
        this.modeloTabla=new DefaultTableModel(null,titulos);        

        String[] registro = new String[1];
        this.registrosMostrados=0;
        for (Cliente item:lista){
            registro[0]=Integer.toString(item.getId());

            this.modeloTabla.addRow(registro);
            this.registrosMostrados=this.registrosMostrados+1;
        }
        return this.modeloTabla;
    }
}
