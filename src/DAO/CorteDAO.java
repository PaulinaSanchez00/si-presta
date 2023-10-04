/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import BD.Conexion;
import CRUD.CrudCorteInterface;
import Entidades.Corte;
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
public class CorteDAO implements CrudCorteInterface<Corte> {
    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;

    public CorteDAO() { 
        CON = Conexion.getInstancia();
    }
    
    @Override
    public List<Corte> listar2(String texto,int totalPorPagina,int numPagina) {
        List<Corte> registros=new ArrayList();
        try {
            ps=CON.conectar().prepareStatement("SELECT * FROM corte WHERE Fecha_corte LIKE ? ORDER BY id ASC LIMIT ?,?");
            ps.setString(1,"%" + texto +"%");
            ps.setInt(2, (numPagina-1)*totalPorPagina);
            ps.setInt(3, totalPorPagina);
            rs=ps.executeQuery();
            while(rs.next()){
                registros.add(new Corte(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4), rs.getDouble(5), rs.getDouble(6), rs.getDouble(7), rs.getInt(8)));
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
    public boolean Insertar(Corte obj) {
        resp=false;
        try {
            ps = CON.conectar().prepareStatement("INSERT INTO corte SET Fecha_corte=?, Saldo=?, Retirado=?, Fondo=?, Total_Caja=?, Diferencia=?, Empleado_id=? ");
            ps.setString(1, obj.getFecha_corte());
            ps.setDouble(2, obj.getSaldo());
            ps.setDouble(3, obj.getRetirado());
            ps.setDouble(4, obj.getFondo());
            ps.setDouble(5, obj.getTotal_Caja());
            ps.setDouble(6, obj.getDiferencia());
            ps.setDouble(7, obj.getEmpleado_id());
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
