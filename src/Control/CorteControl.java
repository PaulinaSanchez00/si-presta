/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import DAO.AbonoDAO;
import DAO.ContratoDAO;
import DAO.CorteDAO;
import Entidades.Abono;
import Entidades.Contrato;
import Entidades.Corte;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CHRISTOPHERURIELTAFO
 */
public class CorteControl {
    private final CorteDAO DATOS;
    private Corte obj;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;
    
    public CorteControl() {
        this.DATOS = new CorteDAO();
        this.obj = new Corte();
        this.registrosMostrados=0;
    }
    
    
    public DefaultTableModel listar2 (String texto,int totalPorPagina,int numPagina){
        List<Corte> lista=new ArrayList();
        lista.addAll(DATOS.listar2(texto,totalPorPagina,numPagina));
        
        String[] titulos = {"ID","Corte ID","Fecha","Saldo","Retiro","Fondo","Total Caja","Diferencia","Empleado_id"};
        this.modeloTabla = new DefaultTableModel(null,titulos);
        
        String[] registro = new String[8];
        this.registrosMostrados=0;
        for(Corte item:lista){
            registro[0]=Integer.toString(item.getCorte_id());
            registro[1]=item.getFecha_corte();
            registro[2]=Double.toString(item.getSaldo());
            registro[3]=Double.toString(item.getRetirado());
            registro[4]=Double.toString(item.getFondo());
            registro[5]=Double.toString(item.getTotal_Caja());
            registro[6]=Double.toString(item.getDiferencia());
            registro[7]=Integer.toString(item.getEmpleado_id());
            
            this.modeloTabla.addRow(registro);
            this.registrosMostrados=this.registrosMostrados+1;
        }
        return this.modeloTabla;
    }
    
    public String Insertar(String Fecha_corte, double Saldo, double Retirado, double Fondo, double Total_Caja, double Diferencia, int Empleado_id) {
            obj.setFecha_corte(Fecha_corte);
            obj.setSaldo(Saldo);
            obj.setRetirado(Retirado);
            obj.setFondo(Fondo);
            obj.setTotal_Caja(Total_Caja);
            obj.setDiferencia(Diferencia);
            obj.setEmpleado_id(Empleado_id);
            if (DATOS.Insertar(obj)) {
                return "OK";
            } else {
                return "Error en el registro.";
            }
    }
}
