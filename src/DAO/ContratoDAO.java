/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import BD.Conexion;
import CRUD.CrudContrato;
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
public class ContratoDAO implements CrudContrato<Contrato> {
    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;

    public ContratoDAO() {
        CON = Conexion.getInstancia();
    }
    
    @Override
    public List<Contrato> listar2(String texto,int totalPorPagina,int numPagina) {
        List<Contrato> registros=new ArrayList();
        try {
            ps=CON.conectar().prepareStatement("SELECT * FROM contrato WHERE cliente_id LIKE ? ORDER BY id ASC LIMIT ?,?");
            ps.setString(1,"%" + texto +"%");
            ps.setInt(2, (numPagina-1)*totalPorPagina);
            ps.setInt(3, totalPorPagina);
            rs=ps.executeQuery();
            while(rs.next()){
                registros.add(new Contrato(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getBoolean(6),rs.getDouble(7),rs.getInt(8),rs.getInt(9),rs.getString(10),rs.getDouble(11),rs.getDouble(12),rs.getDouble(13),rs.getString(14),rs.getInt(15)));
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
    public boolean Insertar(Contrato obj) {
        resp=false;
        try {
            ps = CON.conectar().prepareStatement("INSERT INTO contrato SET cliente_id=?, documentos_id=?, fecha_reg=?,fecha_soli=?,monto=?,interes=?,cuotas=?,forma_pago=?,abono_min=?,interes_total=?,total_pagar=?,clausulas=?,empleado_id=? ");
            ps.setInt(1, obj.getCliente_id());
            ps.setInt(2, obj.getDocumentos_id());
            ps.setString(3,obj.getFecha_reg());
            ps.setString(4, obj.getFecha_soli());
            ps.setDouble(5,obj.getMonto());
            ps.setInt(6,obj.getInteres());
            ps.setInt(7,obj.getCuotas());
            ps.setString(8,obj.getForma_pago());
            ps.setDouble(9,obj.getAbono_min());
            ps.setDouble(10,obj.getInteres_total());
            ps.setDouble(11,obj.getTotal_pagar());
            ps.setString(12,obj.getClausulas());
            ps.setInt(13,obj.getEmpleado_id());
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
            ps = CON.conectar().prepareStatement("SELECT COUNT(id) FROM contrato");
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
    public boolean existe(int texto) {
        resp=false;
        try {
            ps = CON.conectar().prepareStatement("SELECT cliente_id FROM contrato where cliente_id=?");
            ps.setString(1,Integer.toString(texto));
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

    @Override
    public List<Contrato> listarid3(String texto) {
        List<Contrato> registros= new ArrayList();
       try {
            ps=CON.conectar().prepareStatement("SELECT Max(C.id)+1 FROM Contrato AS C");
            rs=ps.executeQuery();
            while(rs.next()){
                registros.add(new Contrato(rs.getInt(1)));              
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
