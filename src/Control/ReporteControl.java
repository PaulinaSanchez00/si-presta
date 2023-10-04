
package Control;

import DAO.ReporteDAO;
import Entidades.Reporte;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;


public class ReporteControl {
    private final ReporteDAO DATOS;
    private Reporte obj;
    private DefaultTableModel modeloTabla;
    public int RegMost;

    public ReporteControl() {
    this.DATOS = new ReporteDAO();
        this.obj = new Reporte();
        this.RegMost = 0;
    }

    public DefaultTableModel listar (String Texto){
    List<Reporte> lista = new ArrayList();
        lista.addAll(DATOS.SelectNom(Texto));
        
        String[] titulos = {"Empleado id","Nombre","Contratos realizados"};
        this.modeloTabla = new DefaultTableModel(null,titulos);
        
        String[] registro = new String[3];
        this.RegMost=0;
        
        for(Reporte item:lista){
            registro[0]=Integer.toString(item.getID());
            registro[1]=item.getNombreTrab();
            registro[2]=Integer.toString(item.getNumClientes());
                        
            
            this.modeloTabla.addRow(registro);
            this.RegMost++;
        }
        return this.modeloTabla;
    }   
}
