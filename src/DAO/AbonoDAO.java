/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import BD.Conexion;
import CRUD.CrudAbono;
import CRUD.CrudContrato;
import Entidades.Abono;
import Entidades.Contrato;
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
public class AbonoDAO implements CrudAbono<Abono> {
    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;

    public AbonoDAO() { 
        CON = Conexion.getInstancia();
    }
    
    @Override
    public List<Abono> listar1(String texto,int totalPorPagina,int numPagina) {
        List<Abono> registros=new ArrayList();
        try {
            ps=CON.conectar().prepareStatement("SELECT * FROM abono WHERE contrato_id LIKE ? ORDER BY id ASC LIMIT ?,?");
            ps.setString(1,"%" + texto +"%");
            ps.setInt(2, (numPagina-1)*totalPorPagina);
            ps.setInt(3, totalPorPagina);
            rs=ps.executeQuery();
            while(rs.next()){
                registros.add(new Abono(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getDouble(4), rs.getBoolean(5), rs.getInt(6), rs.getInt(7)));
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
    public boolean Insertar(Abono obj) {
        resp=false;
        try {
            ps = CON.conectar().prepareStatement("INSERT INTO abono SET Cuota=?, Fecha=?, Monto=?, Estado=?, Cliente_id=?, Contrato_id=?");
            ps.setInt(1, obj.getCuota());
            ps.setString(2, obj.getFecha());
            ps.setDouble(3,obj.getMonto());
            ps.setBoolean(4, obj.isEstado());
            ps.setInt(5,obj.getCliente_id());
            ps.setInt(6,obj.getContrato_id());
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
            ps = CON.conectar().prepareStatement("SELECT COUNT(id) FROM abono");
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
    public boolean Desactivar(int id) {
        resp=false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE Abono SET Deuda=0 WHERE id=? ");
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
            ps = CON.conectar().prepareStatement("UPDATE abono SET Estado=1 WHERE id=? ");
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
    public List<Abono> listarid3(String texto) {
        List<Abono> registros= new ArrayList();
       try {
            ps=CON.conectar().prepareStatement("SELECT Max(A.id)+1 FROM abono AS A");
            rs=ps.executeQuery();
            while(rs.next()){
                registros.add(new Abono(rs.getInt(1)));              
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

    
}
