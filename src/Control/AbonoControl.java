/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import DAO.AbonoDAO;
import DAO.ContratoDAO;
import Entidades.Abono;
import Entidades.Contrato;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CHRISTOPHERURIELTAFO
 */
public class AbonoControl {
    private final AbonoDAO DATOS;
    private Abono obj;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;
    
    public AbonoControl() {
        this.DATOS = new AbonoDAO();
        this.obj = new Abono();
        this.registrosMostrados=0;
    }
    
    
    public DefaultTableModel listar1 (String texto,int totalPorPagina,int numPagina){
        List<Abono> lista=new ArrayList();
        lista.addAll(DATOS.listar1(texto,totalPorPagina,numPagina));
        
        String[] titulos = {"ID","N° de Cuota","Fecha Pago","Monto Cuota","Estado","ID Cliente","ID Contrato"};
        this.modeloTabla = new DefaultTableModel(null,titulos);
        
        String estado;
        String[] registro = new String[7];
        this.registrosMostrados=0;
        for(Abono item:lista){
            if(item.isEstado()){
                estado="Pagado";
            }else{
                estado="Pendiente";
            }
            registro[0]=Integer.toString(item.getId());
            registro[1]=Integer.toString(item.getCuota());
            registro[2]=item.getFecha();
            registro[3]=Double.toString(item.getMonto());
            registro[4]=estado;
            registro[5]=Integer.toString(item.getCliente_id());
            registro[6]=Integer.toString(item.getContrato_id());
            this.modeloTabla.addRow(registro);
            this.registrosMostrados=this.registrosMostrados+1;
        }
        return this.modeloTabla;
        
    }
    
    public String Activar(int Id){
        if(DATOS.Activar(Id)){
            return "OK";
        }else{
            return "No se pudo realizar el Abono";
        }
    }
    
    public String Insertar(int Cuota, String Fecha, double Monto, int Cliente_id, int Contrato_id) {
            obj.setCuota(Cuota);
            obj.setFecha(Fecha);
            obj.setMonto(Monto);
            obj.setCliente_id(Cliente_id);
            obj.setContrato_id(Contrato_id);
            if (DATOS.Insertar(obj)) {
                return "OK";
            } else {
                return "Error en el registro.";
            }
    }
    
    public int Total(){
        return DATOS.Total();
    }
    
    public int TotalMostrados(){
        return this.registrosMostrados;
    }

}
