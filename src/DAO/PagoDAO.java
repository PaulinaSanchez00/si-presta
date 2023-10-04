/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import BD.Conexion;
import CRUD.CrudPago;
import Entidades.Pago;
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
public class PagoDAO implements CrudPago<Pago> {
    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;

    public PagoDAO() { 
        CON = Conexion.getInstancia();
    }
    
    @Override
    public List<Pago> listar(String texto,int totalPorPagina,int numPagina) {
        List<Pago> registros=new ArrayList();
        try {
            ps=CON.conectar().prepareStatement("SELECT * FROM pago WHERE contrato_id LIKE ? ORDER BY id ASC LIMIT ?,?");
            ps.setString(1,"%" + texto +"%");
            ps.setInt(2, (numPagina-1)*totalPorPagina);
            ps.setInt(3, totalPorPagina);
            rs=ps.executeQuery();
            while(rs.next()){
                registros.add(new Pago(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getDouble(7), rs.getDouble(8), rs.getInt(9)));
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
    public boolean Insertar(Pago obj) {
        resp=false;
        try {
            ps = CON.conectar().prepareStatement("INSERT INTO pago SET cliente_id=?, contrato_id=?, fecha_reg=?, fecha_lim=?, monto=?, dinero=?, feria=?, empleado_id=?");
            ps.setInt(1, obj.getCliente_id());
            ps.setInt(2, obj.getContrato_id());
            ps.setString(3, obj.getFecha_reg());
            ps.setString(4, obj.getFecha_lim());
            ps.setDouble(5,obj.getMonto());
            ps.setDouble(6,obj.getDinero());
            ps.setDouble(7,obj.getFeria());
            ps.setInt(8,obj.getEmpleado_id());
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
    public int Total() {
        int TotalRegistros=0;
        try {
            ps = CON.conectar().prepareStatement("SELECT COUNT(id) FROM pago");
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

}
