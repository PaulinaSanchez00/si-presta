
package DAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import BD.Conexion;
import CRUD.CrudListarInterface;
import CRUD.CrudListarInterface1;
import Entidades.Reporte;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class ReporteDAO implements CrudListarInterface1<Reporte> {

    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;
    
    public ReporteDAO() {
        CON = Conexion.getInstancia();
    }

    
    
    @Override
    public List<Reporte> SelectNom(String Texto) {
        List<Reporte>registros=new ArrayList();
        try {
            ps = CON.conectar().prepareStatement("SELECT *FROM REPORTE WHERE NOMBRECOMP LIKE ?;");
            ps.setString(1,"%"+Texto+"%");
            rs = ps.executeQuery();
            while (rs.next()){
                registros.add(new Reporte(rs.getInt(1),rs.getString(2),rs.getInt(3)));
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

    
}
