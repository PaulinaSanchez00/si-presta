 
package DAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import BD.Conexion;
import CRUD.CrudListarInterface;
import Entidades.Fondo;
import Entidades.Reporte;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class FondoDAO implements CrudListarInterface<Fondo> {

    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;
    
    public FondoDAO() {
        CON = Conexion.getInstancia();
    }

    
    
    @Override
    public List<Fondo> SelectNom(String Texto) {
        List<Fondo>registros=new ArrayList();
        try {
            ps = CON.conectar().prepareStatement("SELECT * FROM PAGOS WHERE fecha_reg LIKE ?;");
            ps.setString(1,"%"+Texto+"%");
            rs = ps.executeQuery();
            while (rs.next()){
                registros.add(new Fondo(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getInt(4)));
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
    
    @Override
    public List<Fondo> SelectNom1(String Texto) {
        List<Fondo>registros=new ArrayList();
        try {
            ps = CON.conectar().prepareStatement("select * from retiro where Fecha Like ?;");
            ps.setString(1,"%"+Texto+"%");
            rs = ps.executeQuery();
            while (rs.next()){
                registros.add(new Fondo(rs.getInt(1),rs.getDouble(2),rs.getString(3)));
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

    @Override
    public boolean Insertar(Fondo obj) {
        resp=false;
        try {
            ps = CON.conectar().prepareStatement("INSERT INTO retiro SET Fecha=?, Retiro=?, Empleado_id=?");
            ps.setString(1, obj.getFecha());
            ps.setDouble(2, obj.getRetiro());
            ps.setInt(3,obj.getId());
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
}
