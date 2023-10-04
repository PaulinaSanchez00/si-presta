/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import DAO.ContratoDAO;
import Entidades.Contrato;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CHRISTOPHERURIELTAFO
 */
public class ContratoControl {
    private final ContratoDAO DATOS;
    private Contrato obj;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;
    
    public ContratoControl() {
        this.DATOS = new ContratoDAO();
        this.obj = new Contrato();
        this.registrosMostrados=0;
    }
    
    
    public DefaultTableModel listar2 (String texto,int totalPorPagina,int numPagina){
        List<Contrato> lista=new ArrayList();
        lista.addAll(DATOS.listar2(texto,totalPorPagina,numPagina));
        
        String[] titulos = {"ID","ID Cliente","ID Documentos","Fecha Registro","Fecha Limite","Estado","Prestado","Interes %","Num. Cuotas","Forma de Pago","Abono Min.","Interes Total","Total Pagar","Clausulas","Empleado_id"};
        this.modeloTabla = new DefaultTableModel(null,titulos);
        
        String estado;
        String[] registro = new String[15];
        this.registrosMostrados=0;
        for(Contrato item:lista){
            if(item.isEstado()){
                estado="Pendiente";
            }else{
                estado="Aprovado";
            }
            registro[0]=Integer.toString(item.getId());
            registro[1]=Integer.toString(item.getCliente_id());
            registro[2]=Integer.toString(item.getDocumentos_id());
            registro[3]=item.getFecha_reg();
            registro[4]=item.getFecha_soli();
            registro[5]=estado;
            registro[6]=Double.toString(item.getMonto());
            registro[7]=Integer.toString(item.getInteres());
            registro[8]=Integer.toString(item.getCuotas());
            registro[9]=item.getForma_pago();
            registro[10]=Double.toString(item.getAbono_min());
            registro[11]=Double.toString(item.getInteres_total());
            registro[12]=Double.toString(item.getTotal_pagar());
            registro[13]=item.getClausulas();
            registro[14]=Integer.toString(item.getEmpleado_id());
            this.modeloTabla.addRow(registro);
            this.registrosMostrados=this.registrosMostrados+1;
        }
        return this.modeloTabla;
        
    }
    
    public String Insertar(int cliente_id, int documentos_id, String fecha_reg, String fecha_soli, double monto, int interes, int cuotas, String forma_pago, double abono_min, double interes_total, double total_pagar, String clausulas, int empleado_id) {
        if (DATOS.existe(cliente_id)) {
            return "El registro ya existe.";

        } else {
            obj.setCliente_id(cliente_id);
            obj.setDocumentos_id(documentos_id);
            obj.setFecha_reg(fecha_reg);
            obj.setFecha_soli(fecha_soli);
            obj.setMonto(monto);
            obj.setInteres(interes);
            obj.setCuotas(cuotas);
            obj.setForma_pago(forma_pago);
            obj.setAbono_min(abono_min);
            obj.setInteres_total(interes_total);
            obj.setTotal_pagar(total_pagar);
            obj.setClausulas(clausulas);
            obj.setEmpleado_id(empleado_id);
            if (DATOS.Insertar(obj)) {
                return "OK";
            } else {
                return "Error en el registro.";
            }
        }
    }
    
    public int Total(){
        return DATOS.Total();
    }
    
    public int TotalMostrados(){
        return this.registrosMostrados;
    }
    
    public DefaultTableModel listarid3(String texto){
        List<Contrato> lista=new ArrayList();
        lista.addAll(DATOS.listarid3(texto));
        
        String[] titulos={"Id Contrato"};
        this.modeloTabla=new DefaultTableModel(null,titulos);        

        String[] registro = new String[1];
        this.registrosMostrados=0;
        for (Contrato item:lista){
            registro[0]=Integer.toString(item.getId());

            this.modeloTabla.addRow(registro);
            this.registrosMostrados=this.registrosMostrados+1;
        }
        return this.modeloTabla;
    }
}
