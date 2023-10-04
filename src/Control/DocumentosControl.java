/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import DAO.DocumentosDAO;
import Entidades.Documentos;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CHRISTOPHERURIELTAFO
 */
public class DocumentosControl {
    private final DocumentosDAO DATOS;
    private Documentos obj;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;
    
    
    public DocumentosControl() {
        this.DATOS = new DocumentosDAO();
        this.obj = new Documentos();
        this.registrosMostrados=0;
    }
    
    
    public DefaultTableModel listar1 (String texto,int totalPorPagina,int numPagina){
        List<Documentos> lista=new ArrayList();
        lista.addAll(DATOS.listar1(texto,totalPorPagina,numPagina));
        
        String[] titulos = {"ID","INE","Acta","Comprobante","Contrato"};
        this.modeloTabla = new DefaultTableModel(null,titulos);
        
        String[] registro = new String[4];
        this.registrosMostrados=0;
        for(Documentos item:lista){
            registro[0]=Integer.toString(item.getId());
            registro[1]=item.getIne();
            registro[2]=item.getActa_nac();
            registro[3]=item.getComprobante_dom();
            this.modeloTabla.addRow(registro);
            this.registrosMostrados=this.registrosMostrados+1;
        }
        return this.modeloTabla;
        
    }
    
    public String Insertar(String ine, String acta_nac, String comprobante_dom){
        if (DATOS.existe(ine)){
            return "El registro ya existe.";
            
        }else{
            obj.setIne(ine);
            obj.setActa_nac(acta_nac);
            obj.setComprobante_dom(comprobante_dom);
            if(DATOS.Insertar(obj)){
                return "OK";
            }else{
                return "Error en el registro.";
            }
        }
    }
    
     public String Actualizar(int id, String ine, String ine_Ant, String acta_nac, String comprobante_dom){
        if(ine.equals(ine_Ant)){
            obj.setId(id);
            obj.setIne(ine);
            obj.setActa_nac(acta_nac);
            obj.setComprobante_dom(comprobante_dom);
            if(DATOS.Actualizar(obj)){
                return "OK";
            }else{
                return "Error en la acctualización.";
            }
        }else{
            if(DATOS.existe(ine)){
                return "El registro ya existe";
            }else{
            obj.setId(id);
            obj.setIne(ine);
            obj.setActa_nac(acta_nac);
            obj.setComprobante_dom(comprobante_dom);
                if(DATOS.Actualizar(obj)){
                    return "OK";
                }else{
                    return "Error en la acctualización.";
                }
            }
        }
    }
    
    public int Total(){
        return DATOS.Total();
    }
    
    public int TotalMostrados(){
        return this.registrosMostrados;
    }
    
    public DefaultTableModel listarid(String texto){
        List<Documentos> lista=new ArrayList();
        lista.addAll(DATOS.listarid(texto));
        
        String[] titulos={"Id Documento"};
        this.modeloTabla=new DefaultTableModel(null,titulos);        

        String[] registro = new String[1];
        this.registrosMostrados=0;
        for (Documentos item:lista){
            registro[0]=Integer.toString(item.getId());

            this.modeloTabla.addRow(registro);
            this.registrosMostrados=this.registrosMostrados+1;
        }
        return this.modeloTabla;
    }
    
}
