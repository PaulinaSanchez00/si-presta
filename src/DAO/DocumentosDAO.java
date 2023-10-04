/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import BD.Conexion;
import CRUD.CrudDocumentosInterface;
import Entidades.Documentos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author CHRISTOPHERURIELTAFO
 */
public class DocumentosDAO implements CrudDocumentosInterface<Documentos> {
    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp1;

    public DocumentosDAO() {
        CON = Conexion.getInstancia();
    }
    
    @Override
    public List<Documentos> listar1(String texto,int totalPorPagina,int numPagina) {
        List<Documentos> registros=new ArrayList();
        try {
            ps=CON.conectar().prepareStatement("SELECT * FROM documentos WHERE id LIKE ? ORDER BY id ASC LIMIT ?,?");
            ps.setString(1,"%" + texto +"%");ps.setInt(2, (numPagina-1)*totalPorPagina);
            ps.setInt(2, (numPagina-1)*totalPorPagina);
            ps.setInt(3, totalPorPagina);
            rs=ps.executeQuery();
            while(rs.next()){
                registros.add(new Documentos(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
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
    public boolean Insertar(Documentos obj) {
        resp1=false;
        try {
            ps=CON.conectar().prepareStatement("INSERT INTO documentos (ine, acta_nac, comprobante_dom) VALUES (?,?,?)");
            ps.setString(1, obj.getIne());
            ps.setString(2, obj.getActa_nac());
            ps.setString(3, obj.getComprobante_dom());
            if(ps.executeUpdate()>0){
                resp1=true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally{
            ps=null;
            CON.desconectar();
        }
        return resp1;
    }

    @Override
    public boolean Actualizar(Documentos obj) {
        resp1=false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE documentos SET ine=?, acta_nac=?, comprobante_dom=? WHERE id=?");
            ps.setString(1, obj.getIne());
            ps.setString(2, obj.getActa_nac());
            ps.setString(3, obj.getComprobante_dom());
            ps.setInt(4, obj.getId());
            if(ps.executeUpdate()>0){
                resp1=true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally{
            ps=null;
            CON.desconectar();
        }
        return resp1;
    }

    
    @Override
    public int Total() {
        int TotalRegistros=0;
        try {
            ps = CON.conectar().prepareStatement("SELECT COUNT(id) FROM documentos");
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
    public boolean existe(String texto) {
        resp1=false;
        try {
            ps=CON.conectar().prepareStatement("SELECT ine FROM documentos WHERE ine=?");
            ps.setString(1,texto);
            rs=ps.executeQuery();
            if(rs.next()){
                resp1=true;
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
        return resp1;
    }

    @Override
    public List<Documentos> listarid(String texto) {
        List<Documentos> registros= new ArrayList();
       try {
            ps=CON.conectar().prepareStatement("SELECT Max(D.id)+1 From documentos D");
            rs=ps.executeQuery();
            while(rs.next()){
                registros.add(new Documentos(rs.getInt(1)));              
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
