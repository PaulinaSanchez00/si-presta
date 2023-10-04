
package DAO;

import BD.Conexion;
import CRUD.CrudCambiosInterface;
import Entidades.Rol;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class RolDAO implements CrudCambiosInterface<Rol>{
    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;

    public RolDAO() {
        CON = Conexion.getInstancia();
    }
    
    
    @Override
    public boolean Insertar(Rol Obj) {
        resp=false;
        try {
            
            ps = CON.conectar().prepareStatement("INSERT INTO rol SET Pto_Empleado=?, Descripcion=?");
            ps.setString(1, Obj.getPto_Empleado());
            ps.setString(2, Obj.getDescripcion());
            
            if(ps.executeUpdate()>0){
                resp=true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally{
            CON.desconectar();
        }
        return resp;
    }

    @Override
    public boolean Actualizar(Rol Obj) {
        resp=false;
        try {
            
            ps = CON.conectar().prepareStatement("UPDATE rol SET Pto_Empleado=?, Descripcion=?");
            ps.setString(1, Obj.getPto_Empleado());
            ps.setString(2, Obj.getDescripcion());
            
            if(ps.executeUpdate()>0){
                resp=true;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally{
            CON.desconectar();
        }
        return resp;
    }

    @Override
    public boolean existe(int Rol_id) {
        resp=false;
        try {
            ps = CON.conectar().prepareStatement("SELECT Rol_id FROM Rol where Rol_id=?");
            ps.setInt(1,Rol_id);
            rs = ps.executeQuery();
            if(rs.next()){
                resp=true;
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
        return resp;
    }
    
    
}
