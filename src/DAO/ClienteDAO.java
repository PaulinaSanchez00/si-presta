/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import BD.Conexion;
import CRUD.CrudClienteInterface;
import Entidades.Cliente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *E
 * @author CHRISTOPHERURIELTAFO
 */
public class ClienteDAO implements CrudClienteInterface<Cliente> {
    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;

    public ClienteDAO() {
        CON = Conexion.getInstancia();
    }
    
    @Override
    public List<Cliente> listar(String texto,int totalPorPagina,int numPagina) {
        List<Cliente> registros=new ArrayList();
        try {
            ps=CON.conectar().prepareStatement("SELECT * FROM cliente WHERE nombre_cliente LIKE ? ORDER BY id ASC LIMIT ?,?");
            ps.setString(1,"%" + texto +"%");
            ps.setInt(2, (numPagina-1)*totalPorPagina);
            ps.setInt(3, totalPorPagina);
            rs=ps.executeQuery();
            while(rs.next()){
                registros.add(new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getBoolean(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16), rs.getString(17), rs.getString(18), rs.getString(19), rs.getString(20), rs.getString(21), rs.getString(22), rs.getString(23), rs.getString(24), rs.getString(25), rs.getInt(26)));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally{
            ps=null;
            rs=null;
            CON.desconectar();
        }
        return registros;
    }

    @Override
    public boolean Insertar(Cliente obj) {
        resp=false;
        try {
            ps = CON.conectar().prepareStatement("INSERT INTO cliente SET nombre_cliente=?, apellido_cliente=?, fecha_nac=?,Deuda=?,calle=?,Num_Ext=?,Colonia=?,Ciudad=?,CodPostal=?,Municipio=?,Estado=?,Telefono=?,calle_tra=?,Num_Ext_tra=?,Colonia_tra=?,Ciudad_tra=?,CodPostal_tra=?,Municipio_tra=?,Estado_tra=?,Nombre_Jefe=?,Apellido_Jefe=?,Telefono_Jefe=?,Puesto=?,Correo_Elect=?,documentos_id=? ");
            ps.setString(1, obj.getNombre_cliente());
            ps.setString(2, obj.getApellido_cliente());
            ps.setString(3, obj.getFecha_nac());
            ps.setBoolean(4,obj.isDeuda());
            ps.setString(5,obj.getCalle());
            ps.setString(6,obj.getNum_Ext());
            ps.setString(7,obj.getColonia());
            ps.setString(8,obj.getCiudad());
            ps.setString(9,obj.getCodPostal());
            ps.setString(10,obj.getMunicipio());
            ps.setString(11,obj.getEstado());
            ps.setString(12,obj.getTelefono());
            ps.setString(13,obj.getCalle_tra());
            ps.setString(14,obj.getNum_Ext_tra());
            ps.setString(15,obj.getColonia_tra());
            ps.setString(16,obj.getCiudad_tra());
            ps.setString(17,obj.getCodPostal_tra());
            ps.setString(18,obj.getMunicipio_tra());
            ps.setString(19,obj.getEstado_tra());
            ps.setString(20,obj.getNombre_Jefe());
            ps.setString(21,obj.getApellido_Jefe());
            ps.setString(22,obj.getTelefono_Jefe());
            ps.setString(23,obj.getPuesto());
            ps.setString(24,obj.getCorreo_Elect());
            ps.setInt(25, obj.getDocumentos_id());
            if(ps.executeUpdate()>0){
                resp=true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally{
            ps=null;
            CON.desconectar();
        }
        return resp;
    }

    @Override
    public boolean Actualizar(Cliente obj) {
        resp=false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE cliente SET nombre_cliente=?, apellido_cliente=?, fecha_nac=?,Deuda=?,calle=?,Num_Ext=?,Colonia=?,Ciudad=?,CodPostal=?,Municipio=?,Estado=?,Telefono=?,calle_tra=?,Num_Ext_tra=?,Colonia_tra=?,Ciudad_tra=?,CodPostal_tra=?,Municipio_tra=?,Estado_tra=?,Nombre_Jefe=?,Apellido_Jefe=?,Telefono_Jefe=?,Puesto=?,Correo_Elect=?,documentos_id=? WHERE id=? ");
            ps.setString(1, obj.getNombre_cliente());
            ps.setString(2, obj.getApellido_cliente());
            ps.setString(3, obj.getFecha_nac());
            ps.setBoolean(4,obj.isDeuda());
            ps.setString(5,obj.getCalle());
            ps.setString(6,obj.getNum_Ext());
            ps.setString(7,obj.getColonia());
            ps.setString(8,obj.getCiudad());
            ps.setString(9,obj.getCodPostal());
            ps.setString(10,obj.getMunicipio());
            ps.setString(11,obj.getEstado());
            ps.setString(12,obj.getTelefono());
            ps.setString(13,obj.getCalle_tra());
            ps.setString(14,obj.getNum_Ext_tra());
            ps.setString(15,obj.getColonia_tra());
            ps.setString(16,obj.getCiudad_tra());
            ps.setString(17,obj.getCodPostal_tra());
            ps.setString(18,obj.getMunicipio_tra());
            ps.setString(19,obj.getEstado_tra());
            ps.setString(20,obj.getNombre_Jefe());
            ps.setString(21,obj.getApellido_Jefe());
            ps.setString(22,obj.getTelefono_Jefe());
            ps.setString(23,obj.getPuesto());
            ps.setString(24,obj.getCorreo_Elect());
            ps.setInt(25, obj.getDocumentos_id());
            ps.setInt(26, obj.getId());
            if(ps.executeUpdate()>0){
                resp=true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally{
            ps=null;
            CON.desconectar();
        }
        return resp;
    }

    @Override
    public boolean Desactivar(int id) {
        resp=false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE cliente SET Deuda=0 WHERE id=? ");
            ps.setInt(1, id);
            if(ps.executeUpdate()>0){
                resp=true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally{
            ps=null;
            CON.desconectar();
        }
        return resp;
    }

    @Override
    public boolean Activar(int id) {
        resp=false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE cliente SET Deuda=1 WHERE id=? ");
            ps.setInt(1,id);
            if(ps.executeUpdate()>0){
                resp=true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally{
            ps=null;
            CON.desconectar();
        }
        return resp;
    }

    @Override
    public int Total() {
        int TotalRegistros=0;
        try {
            ps = CON.conectar().prepareStatement("SELECT COUNT(id) FROM cliente");
            rs = ps.executeQuery();
            
            while(rs.next()){
                TotalRegistros=rs.getInt("COUNT(id)");
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }finally{
            ps=null;
            rs=null;
            CON.desconectar();
        }
        return TotalRegistros;
    }
    
    @Override
    public List<Cliente> listarid2(String texto) {
        List<Cliente> registros= new ArrayList();
       try {
            ps=CON.conectar().prepareStatement("SELECT Max(C.id)+1 FROM Cliente C");
            rs=ps.executeQuery();
            while(rs.next()){
                registros.add(new Cliente(rs.getInt(1)));              
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally{
            ps=null;
            rs=null;
            CON.desconectar();
        }
        return registros;
    }

    @Override
    public boolean existe(String texto1,String texto2) {
        resp=false;
        try {
            ps=CON.conectar().prepareStatement("SELECT nombre_cliente, apellido_cliente FROM cliente WHERE nombre_cliente=? AND apellido_cliente=?" );
            ps.setString(1, texto1);
            ps.setString(2, texto2);
            rs=ps.executeQuery();
            if(rs.next()){
                resp=true;
            }           
            ps.close();
            rs.close();
        }  catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally{
            ps=null;
            rs=null;
            CON.desconectar();
        }
        return resp;
    }
}

    
    

