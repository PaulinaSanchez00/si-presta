/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import DAO.PagoDAO;
import Entidades.Pago;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author CHRISTOPHERURIELTAFO
 */
public class PagoControl {
    private final PagoDAO DATOS;
    private Pago obj;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;
    
    public PagoControl() {
        this.DATOS = new PagoDAO();
        this.obj = new Pago();
        this.registrosMostrados=0;
    }
    
    
    public DefaultTableModel listar (String texto,int totalPorPagina,int numPagina){
        List<Pago> lista=new ArrayList();
        lista.addAll(DATOS.listar(texto,totalPorPagina,numPagina));
        
        String[] titulos = {"ID","ID Cliente","ID Contrato","Fecha Reg.","Fecha Lim.","Monto","Dinero","Feria","ID Empleado"};
        this.modeloTabla = new DefaultTableModel(null,titulos);
        
        String[] registro = new String[9];
        this.registrosMostrados=0;
        for(Pago item:lista){
            registro[0]=Integer.toString(item.getId());
            registro[1]=Integer.toString(item.getCliente_id());
            registro[2]=Integer.toString(item.getContrato_id());
            registro[3]=item.getFecha_reg();
            registro[4]=item.getFecha_lim();
            registro[5]=Double.toString(item.getMonto());
            registro[6]=Double.toString(item.getDinero());
            registro[7]=Double.toString(item.getFeria());
            registro[8]=Integer.toString(item.getEmpleado_id());
            this.modeloTabla.addRow(registro);
            this.registrosMostrados=this.registrosMostrados+1;
        }
        return this.modeloTabla;
        
    }
    
    public String Insertar(int cliente_id, int contrato_id, String fecha_reg, String fecha_lim, double monto, double dinero, double feria, int empleado_id) {
            obj.setCliente_id(cliente_id);
            obj.setContrato_id(contrato_id);
            obj.setFecha_reg(fecha_reg);
            obj.setFecha_lim(fecha_lim);
            obj.setMonto(monto);
            obj.setDinero(dinero);
            obj.setFeria(feria);
            obj.setEmpleado_id(empleado_id);
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
