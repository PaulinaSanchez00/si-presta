
package Control;

import BD.Conexion;
import DAO.FondoDAO;
import DAO.ReporteDAO;
import Entidades.Contrato;
import Entidades.Fondo;
import Entidades.Reporte;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class FondoControl {
   private final FondoDAO DATOS;
    private Fondo obj;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;
    

    public FondoControl() {
    this.DATOS = new FondoDAO();
    this.obj = new Fondo();
    this.registrosMostrados=0;
    }

    public DefaultTableModel listar (String Texto){
        List<Fondo> lista = new ArrayList();
        lista.addAll(DATOS.SelectNom(Texto));
        
        String[] titulos = {"Empleado ID","Fecha","Total","Pagos Realizados"};
        this.modeloTabla = new DefaultTableModel(null,titulos);
        
        String[] registro = new String[4];
        this.registrosMostrados=0;
        
        for(Fondo item:lista){
            registro[0]=Integer.toString(item.getEmpleado());
            registro[1]=item.getFecha_reg();
            registro[2]=Double.toString(item.getTotal());
            registro[3]=Integer.toString(item.getPagos());
            this.modeloTabla.addRow(registro);
            this.registrosMostrados++;
        }
        return this.modeloTabla;
    }
    
    public DefaultTableModel listar1 (String Texto){
        List<Fondo> lista = new ArrayList();
        lista.addAll(DATOS.SelectNom1(Texto));
        
        String[] titulos = {"Empleado ID","Retirado","Fecha"};
        this.modeloTabla = new DefaultTableModel(null,titulos);
        
        String[] registro = new String[3];
        this.registrosMostrados=0;
        
        for(Fondo item:lista){
            registro[0]=Integer.toString(item.getEmpleado_id());
            registro[1]=Double.toString(item.getRetirado());
            registro[2]=item.getFecha1();
            this.modeloTabla.addRow(registro);
            this.registrosMostrados++;
        }
        return this.modeloTabla;
    }
    
    public String Insertar(String Fecha,double retiro,int id) {
            obj.setFecha(Fecha);
            obj.setRetiro(retiro);
            obj.setId(id);
            if (DATOS.Insertar(obj)) {
                return "OK";
            } else {
                return "Error en el registro.";
            }
        }
}
