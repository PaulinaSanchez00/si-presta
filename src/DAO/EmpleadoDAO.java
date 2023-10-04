
package DAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import BD.Conexion;
import CRUD.CrudPersonaInterface;
import Entidades.Empleado;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class EmpleadoDAO implements CrudPersonaInterface<Empleado> {
    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;

    public EmpleadoDAO() {
        CON = Conexion.getInstancia();
    }

    @Override
    public List<Empleado> listar(String Texto, int TotalPorpagina, int numPagina) {
        List<Empleado>registros=new ArrayList();
        try {
            ps = CON.conectar().prepareStatement("Select*from Empleado where nombre like ?  ORDER by nombre asc limit ?,?; ");
            ps.setString(1,"%"+Texto+"%");
            ps.setInt(2,(numPagina-1)*TotalPorpagina);
            ps.setInt(3, TotalPorpagina);
            rs = ps.executeQuery();
            while (rs.next()){
                registros.add(new Empleado(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getBoolean(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15),rs.getInt(16)));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),"Listar",JOptionPane.ERROR_MESSAGE);
        }finally{
            ps=null;
            rs=null;
            CON.desconectar();
        }
        return registros;
    }
    public Empleado login(int Id, String Contrasena ){
        Empleado ver= null;
        try {
            ps = CON.conectar().prepareStatement("Select * from empleado where Empleado_id=? AND Contrasena=?");
            ps.setInt(1,Id);
            ps.setString(2,Contrasena);
            rs = ps.executeQuery();
            if(rs.next()){
                ver = new Empleado(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getBoolean(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15),rs.getInt(16));
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
        return ver;
    }
    @Override
    public boolean Insertar(Empleado Obj) {
         resp=false;
        try {
            int Estatus;
            if(Obj.isEstatus()){
                Estatus=1;
            }else{
                Estatus=0;
            }
            ps = CON.conectar().prepareStatement("INSERT INTO empleado SET nombre=?, apellido=?,Contrasena=?,Estatus=?,Fecha_nac=?,calle=?,Num_Ext=?,Colonia=?,Ciudad=?, CodPostal=?,Municipio=?, Estado=?,Email=?,Telefono=?,Rol_id=?");
            ps.setString(1, Obj.getNombre());
            ps.setString(2, Obj.getApellido());
            ps.setString(3, Obj.getContrasena());
            ps.setInt(4,Estatus);
            ps.setString(5,Obj.getFecha_nac());
            ps.setString(6,Obj.getCalle());
            ps.setString(7,Obj.getNum_Ext());
            ps.setString(8,Obj.getColonia());
            ps.setString(9,Obj.getCiudad());
            ps.setString(10,Obj.getCodPostal());
            ps.setString(11,Obj.getMunicipio());
            ps.setString(12,Obj.getEstado());
            ps.setString(13,Obj.getEmail());
            ps.setString(14,Obj.getTelefono());
            ps.setInt(15, Obj.getRol_id());
            if(ps.executeUpdate()>0){
                resp=true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),"Incertar",JOptionPane.ERROR_MESSAGE);
        }finally{
            CON.desconectar();
        }
        return resp;
    }

    @Override
    public boolean Actualizar(Empleado Obj) {
        resp=false;
        try {
            int Estatus;
            if(Obj.isEstatus()){
                Estatus=1;
            }else{
                Estatus=0;
            }
            ps = CON.conectar().prepareStatement("UPDATE empleado SET Nombre=?, Apellido=?,Contrasena=?,Estatus=?,Fecha_nac=?,calle=?,Num_Ext=?,Colonia=?,Ciudad=?, CodPostal=?,Municipio=?, Estado=?,Email=?,Telefono=?,Rol_id=? WHERE Empleado_id=?");
            ps.setString(1, Obj.getNombre());
            ps.setString(2, Obj.getApellido());
            ps.setString(3, Obj.getContrasena());
            ps.setInt(4,Estatus);
            ps.setString(5,Obj.getFecha_nac());
            ps.setString(6,Obj.getCalle());
            ps.setString(7,Obj.getNum_Ext());
            ps.setString(8,Obj.getColonia());
            ps.setString(9,Obj.getCiudad());
            ps.setString(10,Obj.getCodPostal());
            ps.setString(11,Obj.getMunicipio());
            ps.setString(12,Obj.getEstado());
            ps.setString(13,Obj.getEmail());
            ps.setString(14,Obj.getTelefono());
            ps.setInt(15, Obj.getRol_id());
            ps.setInt(16, Obj.getEmpleado_id());
            if(ps.executeUpdate()>0){
                resp=true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),"Actualizar",JOptionPane.ERROR_MESSAGE);
        }finally{
            CON.desconectar();
        }
        return resp;
    }

    @Override
    public boolean Desactivar(int id) {
        resp=false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE empleado SET Estatus=0 WHERE Empleado_id=? ");
            ps.setInt(1, id);
            if(ps.executeUpdate()>0){
                resp=true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),"Desactivar",JOptionPane.ERROR_MESSAGE);
        }finally{
            CON.desconectar();
        }
        return resp;
    }

    @Override
    public boolean Activar(int id) {
        resp=false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE empleado SET Estatus=1 WHERE Empleado_id=? ");
            ps.setInt(1, id);
            if(ps.executeUpdate()>0){
                resp=true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),"Activar",JOptionPane.ERROR_MESSAGE);
        }finally{
            CON.desconectar();
        }
        return resp;
    }

    @Override
    public int Total() {
        int TotalRegistros=0;
        try {
            ps = CON.conectar().prepareStatement("SELECT COUNT(Empleado_id) FROM empleado;");
            rs = ps.executeQuery();
            
            while(rs.next()){
                TotalRegistros=rs.getInt("COUNT(Empleado_id)");
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage(),"Total",JOptionPane.ERROR_MESSAGE);
        }finally{
            ps=null;
            rs=null;
            CON.desconectar();
        }
        return TotalRegistros;
    }

    @Override
    public boolean existe(String texto) {
        resp=false;
        try {
            ps = CON.conectar().prepareStatement("SELECT NOMBRE FROM Empleado where NOMBRE=?");
            ps.setString(1,texto);
            rs = ps.executeQuery();
            if(rs.next()){
                resp=true;
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage(),"Existe",JOptionPane.ERROR_MESSAGE);
        }finally{
            ps=null;
            rs=null;
            CON.desconectar();
        }
        return resp;
    }
    
    public List<Empleado> listarid2(String texto) {
        List<Empleado> registros= new ArrayList();
       try {
            ps=CON.conectar().prepareStatement("SELECT Max(E.Empleado_id)+1 FROM empleado E");
            rs=ps.executeQuery();
            while(rs.next()){
                registros.add(new Empleado(rs.getInt(1)));              
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),"Listar2",JOptionPane.ERROR_MESSAGE);
        }finally{
            ps=null;
            rs=null;
            CON.desconectar();
        }
        return registros;
    }
}
