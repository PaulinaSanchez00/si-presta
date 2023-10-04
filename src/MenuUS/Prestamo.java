/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package MenuUS;
import BD.Conexion;
import Control.AbonoControl;
import Control.ClienteControl;
import Control.ContratoControl;
import Entidades.Variables;
import static MenuUS.Prestamo.fechaActual;
import javax.swing.JOptionPane;
import javax.swing.table.TableRowSorter;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
public class Prestamo extends javax.swing.JInternalFrame {
    /**/DefaultTableModel Cuotas = new DefaultTableModel();
    
    private final ContratoControl CONTROLCNT;
    private final ClienteControl CONTROLCLT;
    private final AbonoControl CONTROLABO;
    private String accion;

    private final Conexion CON;
    private PreparedStatement ps;

    private int totalPorPagina = 10;
    private int numPagina = 1;
    private boolean primeraCarga = true;
    private int totalRegistros;
    
    private int totalPorPagina2 = 10;
    private int numPagina2 = 1;
    private boolean primeraCarga2 = true;
    private int totalRegistros2;
    
    public Prestamo() {
        initComponents();
        String[] titulo=new String[]{"N° de Cuota","Fecha Pago", "Monto Cuota"};
        Cuotas.setColumnIdentifiers(titulo);
        tblCuotas.setModel(Cuotas);
        this.CONTROLCNT=new ContratoControl();
        this.CONTROLCLT=new ClienteControl();
        this.CONTROLABO=new AbonoControl();
        this.accion="guardar";
        this.paginar();
        this.primeraCarga=false;
        this.paginar2();
        this.primeraCarga2=false;
        this.listar("",false);
        this.listar2("",false);
        this.listarid3("");
        CON = Conexion.getInstancia();
        txtFecha.setText(fechaActual());
        //txtFechaI.setText(GenerarFecha());
        txtFecha.setEditable(false);
        txtFechaI.setEditable(false);
        txtId.setEditable(false);
        txtNombre.setEditable(false);
        txtApellido.setEditable(false);
        txtDocumentos.setEditable(false);
        txtAbono.setEditable(false);
        txtInteresT.setEditable(false);
        txtTotalPagar.setEditable(false);
        txtTabCuota.setVisible(false);
        txtTabFecha.setVisible(false);
        txtTabMonto.setVisible(false);
        
        SpinnerNumberModel nm = new SpinnerNumberModel();
        nm.setMaximum(100);
        nm.setMinimum(0);
        nm.setStepSize(1);
        txtInteres.setModel(nm);
        
        tabGeneral.setEnabledAt(1, false);
        tabGeneral.setEnabledAt(2, false);
        tabGeneral.setEnabledAt(0, true);
        tabGeneral.setSelectedIndex(0);
        
        
        
    }

    private void paginar(){
        int totalPaginas;
        
        this.totalRegistros=this.CONTROLCLT.Total();
        this.totalPorPagina=Integer.parseInt((String)cboTotalPorPagina.getSelectedItem());
        totalPaginas=(int)(Math.ceil((double)this.totalRegistros/this.totalPorPagina));
        if (totalPaginas==0){
            totalPaginas=1;
        }
        cboNumPagina.removeAllItems();
        
        for (int i = 1; i <= totalPaginas; i++) {
            cboNumPagina.addItem(Integer.toString(i));
        }
        cboNumPagina.setSelectedIndex(0);
    }
    
    private void paginar2(){
        int totalPaginas2;
        
        this.totalRegistros2=this.CONTROLCNT.Total();
        this.totalPorPagina2=Integer.parseInt((String)cboTotalPorPagina2.getSelectedItem());
        totalPaginas2=(int)(Math.ceil((double)this.totalRegistros2/this.totalPorPagina2));
        if (totalPaginas2==0){
            totalPaginas2=1;
        }
        cboNumPagina2.removeAllItems();
        
        for (int i = 1; i <= totalPaginas2; i++) {
            cboNumPagina2.addItem(Integer.toString(i));
        }
        cboNumPagina2.setSelectedIndex(0);
    }
    
    public static String fechaActual(){
    
        Date fecha=new Date();
        SimpleDateFormat formatoFecha=new SimpleDateFormat("YYYY/MM/dd");
        
        return formatoFecha.format(fecha);
    }
    
    private void listar(String texto, boolean paginar){
    this.totalPorPagina=Integer.parseInt((String)cboTotalPorPagina.getSelectedItem());
        if ((String)cboNumPagina.getSelectedItem()!=null){
            this.numPagina=Integer.parseInt((String)cboNumPagina.getSelectedItem());
        }
        
        if (paginar==true){
            tablaListado.setModel(this.CONTROLCLT.listar(texto,this.totalPorPagina,this.numPagina));
        }else{
            tablaListado.setModel(this.CONTROLCLT.listar(texto,this.totalPorPagina,1));
        }
    TableRowSorter orden=new TableRowSorter(tablaListado.getModel());
    tablaListado.setRowSorter(orden);
    this.ocultarColumnas();
    lblTotalRegistros.setText("Mostrando " + this.CONTROLCLT.TotalMostrados() + " de un total de " + this.CONTROLCLT.Total()+ " registros");
    }
    
    
    private void listar2(String texto, boolean paginar){
    this.totalPorPagina2=Integer.parseInt((String)cboTotalPorPagina2.getSelectedItem());
        if ((String)cboNumPagina2.getSelectedItem()!=null){
            this.numPagina2=Integer.parseInt((String)cboNumPagina2.getSelectedItem());
        }
        
        if (paginar==true){
            tabContrato.setModel(this.CONTROLCNT.listar2(texto,this.totalPorPagina2,this.numPagina2));
        }else{
            tabContrato.setModel(this.CONTROLCNT.listar2(texto,this.totalPorPagina2,1));
        }
    TableRowSorter orden=new TableRowSorter(tabContrato.getModel());
    tabContrato.setRowSorter(orden);
    this.ocultarColumnas2();
    lblTotalRegistros2.setText("Mostrando " + this.CONTROLCNT.TotalMostrados() + " de un total de " + this.CONTROLCNT.Total()+ " registros");
    }
    
    private void ocultarColumnas(){
        tablaListado.getColumnModel().getColumn(3).setMaxWidth(0);
        tablaListado.getColumnModel().getColumn(3).setMinWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(3).setMinWidth(0);
        
        tablaListado.getColumnModel().getColumn(5).setMaxWidth(0);
        tablaListado.getColumnModel().getColumn(5).setMinWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(5).setMinWidth(0);
        
        tablaListado.getColumnModel().getColumn(6).setMaxWidth(0);
        tablaListado.getColumnModel().getColumn(6).setMinWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(6).setMinWidth(0);
        
        tablaListado.getColumnModel().getColumn(7).setMaxWidth(0);
        tablaListado.getColumnModel().getColumn(7).setMinWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(7).setMinWidth(0);
        
        tablaListado.getColumnModel().getColumn(8).setMaxWidth(0);
        tablaListado.getColumnModel().getColumn(8).setMinWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(8).setMinWidth(0);
        
        tablaListado.getColumnModel().getColumn(9).setMaxWidth(0);
        tablaListado.getColumnModel().getColumn(9).setMinWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(9).setMaxWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(9).setMinWidth(0);
        
        tablaListado.getColumnModel().getColumn(10).setMaxWidth(0);
        tablaListado.getColumnModel().getColumn(10).setMinWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(10).setMaxWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(10).setMinWidth(0);
        
        tablaListado.getColumnModel().getColumn(11).setMaxWidth(0);
        tablaListado.getColumnModel().getColumn(11).setMinWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(11).setMaxWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(11).setMinWidth(0);
        
        tablaListado.getColumnModel().getColumn(12).setMaxWidth(0);
        tablaListado.getColumnModel().getColumn(12).setMinWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(12).setMaxWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(12).setMinWidth(0);
        
        tablaListado.getColumnModel().getColumn(13).setMaxWidth(0);
        tablaListado.getColumnModel().getColumn(13).setMinWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(13).setMaxWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(13).setMinWidth(0);
        
        tablaListado.getColumnModel().getColumn(14).setMaxWidth(0);
        tablaListado.getColumnModel().getColumn(14).setMinWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(14).setMaxWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(14).setMinWidth(0);
        
        tablaListado.getColumnModel().getColumn(15).setMaxWidth(0);
        tablaListado.getColumnModel().getColumn(15).setMinWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(15).setMaxWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(15).setMinWidth(0);
        
        tablaListado.getColumnModel().getColumn(16).setMaxWidth(0);
        tablaListado.getColumnModel().getColumn(16).setMinWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(16).setMaxWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(16).setMinWidth(0);
        
        tablaListado.getColumnModel().getColumn(17).setMaxWidth(0);
        tablaListado.getColumnModel().getColumn(17).setMinWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(17).setMaxWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(17).setMinWidth(0);
        
        tablaListado.getColumnModel().getColumn(18).setMaxWidth(0);
        tablaListado.getColumnModel().getColumn(18).setMinWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(18).setMaxWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(18).setMinWidth(0);
        
        tablaListado.getColumnModel().getColumn(19).setMaxWidth(0);
        tablaListado.getColumnModel().getColumn(19).setMinWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(19).setMaxWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(19).setMinWidth(0);
        
        tablaListado.getColumnModel().getColumn(20).setMaxWidth(0);
        tablaListado.getColumnModel().getColumn(20).setMinWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(20).setMaxWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(20).setMinWidth(0);
        
        tablaListado.getColumnModel().getColumn(21).setMaxWidth(0);
        tablaListado.getColumnModel().getColumn(21).setMinWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(21).setMaxWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(21).setMinWidth(0);
        
        tablaListado.getColumnModel().getColumn(22).setMaxWidth(0);
        tablaListado.getColumnModel().getColumn(22).setMinWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(22).setMaxWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(22).setMinWidth(0);
        
        tablaListado.getColumnModel().getColumn(23).setMaxWidth(0);
        tablaListado.getColumnModel().getColumn(23).setMinWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(23).setMaxWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(23).setMinWidth(0);
        
        tablaListado.getColumnModel().getColumn(24).setMaxWidth(0);
        tablaListado.getColumnModel().getColumn(24).setMinWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(24).setMaxWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(24).setMinWidth(0);
    }
    
    private void ocultarColumnas2(){
        tabContrato.getColumnModel().getColumn(2).setMaxWidth(0);
        tabContrato.getColumnModel().getColumn(2).setMinWidth(0);
        tabContrato.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(0);
        tabContrato.getTableHeader().getColumnModel().getColumn(2).setMinWidth(0);
       
        tabContrato.getColumnModel().getColumn(7).setMaxWidth(0);
        tabContrato.getColumnModel().getColumn(7).setMinWidth(0);
        tabContrato.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0);
        tabContrato.getTableHeader().getColumnModel().getColumn(7).setMinWidth(0);
        
        tabContrato.getColumnModel().getColumn(8).setMaxWidth(0);
        tabContrato.getColumnModel().getColumn(8).setMinWidth(0);
        tabContrato.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(0);
        tabContrato.getTableHeader().getColumnModel().getColumn(8).setMinWidth(0);
        
        tabContrato.getColumnModel().getColumn(9).setMaxWidth(0);
        tabContrato.getColumnModel().getColumn(9).setMinWidth(0);
        tabContrato.getTableHeader().getColumnModel().getColumn(9).setMaxWidth(0);
        tabContrato.getTableHeader().getColumnModel().getColumn(9).setMinWidth(0);
        
        tabContrato.getColumnModel().getColumn(10).setMaxWidth(0);
        tabContrato.getColumnModel().getColumn(10).setMinWidth(0);
        tabContrato.getTableHeader().getColumnModel().getColumn(10).setMaxWidth(0);
        tabContrato.getTableHeader().getColumnModel().getColumn(10).setMinWidth(0);
        
        tabContrato.getColumnModel().getColumn(11).setMaxWidth(0);
        tabContrato.getColumnModel().getColumn(11).setMinWidth(0);
        tabContrato.getTableHeader().getColumnModel().getColumn(11).setMaxWidth(0);
        tabContrato.getTableHeader().getColumnModel().getColumn(11).setMinWidth(0);
        
        tabContrato.getColumnModel().getColumn(12).setMaxWidth(0);
        tabContrato.getColumnModel().getColumn(12).setMinWidth(0);
        tabContrato.getTableHeader().getColumnModel().getColumn(12).setMaxWidth(0);
        tabContrato.getTableHeader().getColumnModel().getColumn(12).setMinWidth(0);
        
        tabContrato.getColumnModel().getColumn(13).setMaxWidth(0);
        tabContrato.getColumnModel().getColumn(13).setMinWidth(0);
        tabContrato.getTableHeader().getColumnModel().getColumn(13).setMaxWidth(0);
        tabContrato.getTableHeader().getColumnModel().getColumn(13).setMinWidth(0);
        
    }
    
    private void limpiar(){
    txtId.setText("");
    txtBuscar.setText("");
    txtBuscar2.setText("");
    txtNombre.setText("");
    txtApellido.setText("");
    txtDocumentos.setText("");
    txtFechaI.setText("");
    txtAbono.setText("");
    txtInteresT.setText("");
    txtTotalPagar.setText("");
    txtMonto.setValue(0);
    txtInteres.setValue(0);
    txtCuotas.setValue(0);
    txtClausulas.setText("");
    txtIdContrato.setText("");
    LimpiarTabla(tblCuotas);
    this.accion="guardar";}
    
    public void LimpiarTabla(JTable tabla){
        int filas=tblCuotas.getRowCount();
        for(int i=0;filas>i;i++){
            Cuotas.removeRow(0);
        }
        
    }
    
    private void mensajeError(String mensaje){
        JOptionPane.showMessageDialog(this, mensaje,"Sistema",JOptionPane.ERROR_MESSAGE);
    }
    
    private void mensajeOk(String mensaje){
        JOptionPane.showMessageDialog(this, mensaje,"Sistema",JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void listarid3(String texto){
    tbIdCliente.setModel(this.CONTROLCNT.listarid3(texto));
    TableRowSorter orden=new TableRowSorter(tbIdCliente.getModel());
    tbIdCliente.setRowSorter(orden);
    this.accion="guardar";
    tbIdCliente.setRowSorter(orden);
    }
    
    
    
    public void GenerarFechas(String texto) {
        LimpiarTabla(tblCuotas);
        String opcion = (String) txtForma.getSelectedItem();
        if (opcion.equals("Diario")) {
            LocalDate date = LocalDate.now();
            date = date.plusDays(1);
            String oldString1 = date.toString();
            String newString1 = oldString1.replace("-", "/");
            txtFechaI.setText(newString1);
            txtFechaI.setEditable(false);
            int Cuota = Integer.parseInt(txtCuotas.getValue().toString());
            int i = 1;
            while (i <= Cuota) {
                this.Cuotas.addRow(new Object[]{
                    i, (date), txtAbono.getText()
                });
                //fecha = fecha.plusDays(8);
                i++;
                date = date.plusDays(1);
            }
        }
        if (opcion.equals("Semanal")) {
            LocalDate date = LocalDate.now();
            date = date.plusDays(7);
            String oldString1 = date.toString();
            String newString1 = oldString1.replace("-", "/");
            txtFechaI.setText(newString1);
            txtFechaI.setEditable(false);
            int Cuota = Integer.parseInt(txtCuotas.getValue().toString());
            int i = 1;
            while (i <= Cuota) {
                this.Cuotas.addRow(new Object[]{
                    i, (date), txtAbono.getText()
                });
                //fecha = fecha.plusDays(8);
                i++;
                date = date.plusDays(7);
            }
        }
        if (opcion.equals("Quincenal")) {
            LocalDate date = LocalDate.now();
            date = date.plusDays(15);
            String oldString1 = date.toString();
            String newString1 = oldString1.replace("-", "/");
            txtFechaI.setText(newString1);
            txtFechaI.setEditable(false);
            int Cuota = Integer.parseInt(txtCuotas.getValue().toString());
            int i = 1;
            while (i <= Cuota) {
                this.Cuotas.addRow(new Object[]{
                    i, (date), txtAbono.getText()
                });
                //fecha = fecha.plusDays(8);
                i++;
                date = date.plusDays(15);
            }
        }
        if (opcion.equals("Mensual")) {
            LocalDate date = LocalDate.now();
            date = date.plusMonths(1);
            String oldString1 = date.toString();
            String newString1 = oldString1.replace("-", "/");
            txtFechaI.setText(newString1);
            txtFechaI.setEditable(false);
            int Cuota = Integer.parseInt(txtCuotas.getValue().toString());
            int i = 1;
            while (i <= Cuota) {
                this.Cuotas.addRow(new Object[]{
                    i, (date), txtAbono.getText()
                });
                //fecha = fecha.plusDays(8);
                i++;
                date = date.plusMonths(1);
            }
        }
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabGeneral = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        txtBuscar2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        cboNumPagina2 = new javax.swing.JComboBox<>();
        jLabel34 = new javax.swing.JLabel();
        cboTotalPorPagina2 = new javax.swing.JComboBox<>();
        lblTotalRegistros2 = new javax.swing.JLabel();
        btnNuevo1 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        tabContrato = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtBuscar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaListado = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbIdContrato = new javax.swing.JTable();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        cboTotalPorPagina = new javax.swing.JComboBox<>();
        lblTotalRegistros = new javax.swing.JLabel();
        btnCliente = new javax.swing.JButton();
        txtFecha = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cboNumPagina = new javax.swing.JComboBox<>();
        jLabel49 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtMonto = new javax.swing.JSpinner();
        jLabel20 = new javax.swing.JLabel();
        txtForma = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        txtInteres = new javax.swing.JSpinner();
        jLabel22 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtCuotas = new javax.swing.JSpinner();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        txtTotalPagar = new javax.swing.JTextField();
        txtInteresT = new javax.swing.JTextField();
        txtAbono = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtDocumentos = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        BntCuotas = new javax.swing.JButton();
        txtFechaI = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        txtTabCuota = new javax.swing.JTextField();
        txtTabFecha = new javax.swing.JTextField();
        txtTabMonto = new javax.swing.JTextField();
        txtIdContrato = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblCuotas = new javax.swing.JTable();
        jScrollPane8 = new javax.swing.JScrollPane();
        tbIdCliente = new javax.swing.JTable();
        jLabel30 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtClausulas = new javax.swing.JTextArea();
        jButton7 = new javax.swing.JButton();
        txtCancelar = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Prestamo");

        tabGeneral.setBackground(new java.awt.Color(204, 255, 255));

        jPanel4.setBackground(new java.awt.Color(204, 255, 255));

        txtBuscar2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscar2KeyTyped(evt);
            }
        });

        jLabel5.setText("ID Cliente:");

        jButton8.setBackground(new java.awt.Color(204, 204, 204));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar.png"))); // NOI18N
        jButton8.setText("Buscar");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel33.setText("# Página");

        cboNumPagina2.setBackground(new java.awt.Color(0, 153, 153));
        cboNumPagina2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNumPagina2ActionPerformed(evt);
            }
        });

        jLabel34.setText("Total de registros por página ");

        cboTotalPorPagina2.setBackground(new java.awt.Color(0, 153, 153));
        cboTotalPorPagina2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "10", "20", "50", "100", "200", "500" }));
        cboTotalPorPagina2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTotalPorPagina2ActionPerformed(evt);
            }
        });

        lblTotalRegistros2.setText("Registros:");

        btnNuevo1.setBackground(new java.awt.Color(204, 204, 204));
        btnNuevo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/anadir.png"))); // NOI18N
        btnNuevo1.setText("Nuevo");
        btnNuevo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevo1ActionPerformed(evt);
            }
        });

        tabContrato.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane7.setViewportView(tabContrato);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboNumPagina2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboTotalPorPagina2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblTotalRegistros2, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNuevo1))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 732, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jButton8)
                    .addComponent(btnNuevo1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(jLabel34)
                    .addComponent(cboTotalPorPagina2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotalRegistros2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboNumPagina2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(237, Short.MAX_VALUE))
        );

        tabGeneral.addTab("Historial Prestamo", jPanel4);

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));

        jLabel14.setText("INFORMACION DEL CLIENTE");

        jPanel3.setBackground(new java.awt.Color(204, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)));

        jLabel1.setText("Cliente:");

        tablaListado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaListado);

        jButton1.setBackground(new java.awt.Color(204, 204, 204));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar.png"))); // NOI18N
        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tbIdContrato.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tbIdContrato);

        jLabel28.setText("# Página");

        jLabel29.setText("Total de registros por página ");

        cboTotalPorPagina.setBackground(new java.awt.Color(0, 153, 153));
        cboTotalPorPagina.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "10", "20", "50", "100", "200", "500" }));
        cboTotalPorPagina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTotalPorPaginaActionPerformed(evt);
            }
        });

        lblTotalRegistros.setText("Registros:");

        btnCliente.setBackground(new java.awt.Color(204, 204, 204));
        btnCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/aceptar.png"))); // NOI18N
        btnCliente.setText("Aceptar");
        btnCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClienteActionPerformed(evt);
            }
        });

        jLabel2.setText("Fecha de Registro:");

        cboNumPagina.setBackground(new java.awt.Color(0, 153, 153));
        cboNumPagina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNumPaginaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboNumPagina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboTotalPorPagina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTotalRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(35, 35, 35)
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(28, 28, 28)
                            .addComponent(jButton1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnCliente)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(878, 878, 878)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addComponent(btnCliente, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29)
                    .addComponent(cboTotalPorPagina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotalRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboNumPagina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel49.setText("ID Documentos:");

        jLabel13.setText("Apellidos:");

        jLabel12.setText("Nombres:");

        jLabel15.setText("ID Cliente:");

        txtId.setBackground(new java.awt.Color(204, 204, 204));

        txtApellido.setBackground(new java.awt.Color(204, 204, 204));

        jLabel19.setText("INFORMACION DEL PRESTAMO");

        jLabel16.setText("Monto Prestamo:");

        jLabel20.setText("Forma de Pago:");

        txtForma.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Diario", "Semanal", "Quincenal", "Mensual", " " }));
        txtForma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFormaActionPerformed(evt);
            }
        });

        jLabel17.setText("Interes %:");

        jLabel22.setText("Fecha de Inicio:");

        jLabel18.setText("N°  de Cuotas:");

        jLabel23.setText("Monto por Cuota:");

        jLabel24.setText("Total Interes:");

        jLabel25.setText("Monto Total a Pagar:");

        jButton4.setBackground(new java.awt.Color(204, 204, 204));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/anadir.png"))); // NOI18N
        jButton4.setText("Registrar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(204, 204, 204));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancelar_1.png"))); // NOI18N
        jButton5.setText("Cancelar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        txtTotalPagar.setBackground(new java.awt.Color(204, 204, 204));

        txtInteresT.setBackground(new java.awt.Color(204, 204, 204));
        txtInteresT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtInteresTActionPerformed(evt);
            }
        });

        txtAbono.setBackground(new java.awt.Color(204, 204, 204));

        txtNombre.setBackground(new java.awt.Color(204, 204, 204));

        txtDocumentos.setBackground(new java.awt.Color(204, 204, 204));
        txtDocumentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDocumentosActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(204, 204, 204));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/menu.png"))); // NOI18N
        jButton2.setText("Añadir Cláusulas");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        BntCuotas.setBackground(new java.awt.Color(204, 204, 204));
        BntCuotas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/bolsa-de-dinero (1).png"))); // NOI18N
        BntCuotas.setText("Generar Cuotas");
        BntCuotas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BntCuotasActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(204, 204, 204));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/bolsa-de-dinero.png"))); // NOI18N
        jButton3.setText("Cuotas");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        txtIdContrato.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdContratoKeyTyped(evt);
            }
        });

        tblCuotas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane5.setViewportView(tblCuotas);

        jScrollPane4.setViewportView(jScrollPane5);

        tbIdCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane8.setViewportView(tbIdCliente);

        jLabel30.setText("ID Contrato:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel23)
                                        .addComponent(jLabel20)
                                        .addComponent(txtAbono)
                                        .addComponent(txtForma, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtInteresT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel24))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel25)
                                                .addComponent(txtTotalPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addGap(7, 7, 7)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtFechaI, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel22))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtIdContrato, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel30)))))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtInteres, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtCuotas))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addGap(33, 33, 33)
                                        .addComponent(jLabel17)
                                        .addGap(31, 31, 31)
                                        .addComponent(jLabel18)))
                                .addComponent(jLabel19))
                            .addGap(125, 125, 125)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(txtTabMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(21, 21, 21)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(BntCuotas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton2)))))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel15)
                                .addComponent(jLabel14))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel12)
                                            .addGap(105, 105, 105)
                                            .addComponent(jLabel13)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtDocumentos, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(151, 151, 151)
                                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(txtTabFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtTabCuota, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 664, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel49))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDocumentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel18)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtTabMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23))))
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BntCuotas)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTabFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTabCuota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtInteres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCuotas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel22)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtForma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtFechaI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel30)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIdContrato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAbono, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtInteresT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtTotalPagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, Short.MAX_VALUE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addGap(4, 4, 4)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(282, 282, 282))
        );

        tabGeneral.addTab("Registrar Prestamo", jPanel2);

        jPanel5.setBackground(new java.awt.Color(204, 255, 255));

        txtClausulas.setColumns(20);
        txtClausulas.setRows(5);
        jScrollPane2.setViewportView(txtClausulas);

        jButton7.setBackground(new java.awt.Color(204, 204, 204));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/aceptar.png"))); // NOI18N
        jButton7.setText("Aceptar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        txtCancelar.setBackground(new java.awt.Color(204, 204, 204));
        txtCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/cancelar_1.png"))); // NOI18N
        txtCancelar.setText("Cancelar");
        txtCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCancelarActionPerformed(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(0, 102, 102));
        jPanel6.setForeground(new java.awt.Color(0, 102, 102));

        jLabel3.setFont(new java.awt.Font("Tahoma", 3, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Cláusulas del Prestamo:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(39, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(122, Short.MAX_VALUE))
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(txtCancelar))
                .addContainerGap(162, Short.MAX_VALUE))
        );

        tabGeneral.addTab("Cláusulas", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabGeneral)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 630, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCancelarActionPerformed
        tabGeneral.setEnabledAt(1, true);
        tabGeneral.setEnabledAt(0, false);
        tabGeneral.setEnabledAt(2, false);
        tabGeneral.setSelectedIndex(1);
        txtClausulas.setText("");
    }//GEN-LAST:event_txtCancelarActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        tabGeneral.setEnabledAt(1, true);
        tabGeneral.setEnabledAt(0, false);
        tabGeneral.setEnabledAt(2, false);
        tabGeneral.setSelectedIndex(1);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(tblCuotas.getSelectedRowCount()>=1){
        TableModel model1 = tblCuotas.getModel();
        int indexs[] = tblCuotas.getSelectedRows();
        for (int i = 0; i < indexs.length; i++) {
            String Cuota = String.valueOf(model1.getValueAt(indexs[i], 0));
            String Fecha = String.valueOf(model1.getValueAt(indexs[i], 1));
            String Monto = String.valueOf(model1.getValueAt(indexs[i], 2));

            txtTabCuota.setText(Cuota);
            txtTabFecha.setText(Fecha);
            txtTabMonto.setText(Monto);

            String resp;
            resp = this.CONTROLABO.Insertar(Integer.parseInt(txtTabCuota.getText()), txtTabFecha.getText(), Double.parseDouble(txtTabMonto.getText()), Integer.parseInt(txtId.getText()), Integer.parseInt(txtIdContrato.getText()));
            if (resp.equals("OK")) {
            } else {
                this.mensajeError(resp);
            }
        }
        this.mensajeOk("Registrado correctamente");
        this.listarid3("");
        this.limpiar();
        tabGeneral.setEnabledAt(1, false);
        tabGeneral.setEnabledAt(2, false);
        tabGeneral.setEnabledAt(0, true);
        tabGeneral.setSelectedIndex(0);
        }else{
            JOptionPane.showMessageDialog(this, "Debes Seleccionar TODAS las Cuotas","Sistema", JOptionPane.WARNING_MESSAGE);

        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void BntCuotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BntCuotasActionPerformed
        
        if (txtMonto.getValue().toString().length()==0){
            JOptionPane.showMessageDialog(this, "Debes insertar monto.","Sistema", JOptionPane.WARNING_MESSAGE);
            txtMonto.requestFocus();
            return;
        }
        if (txtInteres.getValue().toString().length()==0){
            JOptionPane.showMessageDialog(this, "Debes ingresar el porcentaje de interes.","Sistema", JOptionPane.WARNING_MESSAGE);
            txtInteres.requestFocus();
            return;
        }
        if (txtCuotas.getValue().toString().length()==0){
            JOptionPane.showMessageDialog(this, "Debes de ingresar el N° de Cuotas.","Sistema", JOptionPane.WARNING_MESSAGE);
            txtCuotas.requestFocus();
            return;
        }
        double Monto,Interes,Cuotas,Abono,InteresT,TotalP;
        Monto=Double.parseDouble(txtMonto.getValue().toString());
        Interes=(Double.parseDouble(txtInteres.getValue().toString()))/100;
        Cuotas=Double.parseDouble(txtCuotas.getValue().toString());
        Abono=((Monto*Interes)+Monto)/Cuotas;
        InteresT=Monto*Interes;
        TotalP=Monto+InteresT;
        txtAbono.setText(String.valueOf(Abono));
        txtInteresT.setText(String.valueOf(InteresT));
        txtTotalPagar.setText(String.valueOf(TotalP));
        GenerarFechas("");
        //LimpiarTabla(tblCuotas);
    }//GEN-LAST:event_BntCuotasActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        tabGeneral.setEnabledAt(2, true);
        tabGeneral.setEnabledAt(0, false);
        tabGeneral.setEnabledAt(1, false);
        tabGeneral.setSelectedIndex(2);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtDocumentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDocumentosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDocumentosActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        tabGeneral.setEnabledAt(0, true);
        tabGeneral.setEnabledAt(2, false);
        tabGeneral.setEnabledAt(1, false);
        tabGeneral.setSelectedIndex(0);
        limpiar();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void txtFormaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFormaActionPerformed

    }//GEN-LAST:event_txtFormaActionPerformed

    private void cboNumPaginaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNumPaginaActionPerformed
        if (this.primeraCarga==false){
            this.listar("",true);
        }
    }//GEN-LAST:event_cboNumPaginaActionPerformed

    private void btnClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClienteActionPerformed
        if(tablaListado.getSelectedRowCount()==1){
            String id= String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 0));
            String nombre_cliente = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 1));
            String apellido_cliente = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 2));
            String documentos_id = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 25));

            txtId.setText(id);
            txtNombre.setText(nombre_cliente);
            txtApellido.setText(apellido_cliente);
            txtDocumentos.setText(documentos_id);
        }else{
            JOptionPane.showMessageDialog(this, "Debes Seleccionar un cliente.","Sistema", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnClienteActionPerformed

    private void cboTotalPorPaginaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTotalPorPaginaActionPerformed
        this.paginar();
    }//GEN-LAST:event_cboTotalPorPaginaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.listar(txtBuscar.getText(),false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtInteresTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtInteresTActionPerformed
        
    }//GEN-LAST:event_txtInteresTActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        this.listar2(txtBuscar2.getText(),false);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void cboNumPagina2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNumPagina2ActionPerformed
        if (this.primeraCarga2==false){
            this.listar2("",true);
        }
    }//GEN-LAST:event_cboNumPagina2ActionPerformed

    private void cboTotalPorPagina2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTotalPorPagina2ActionPerformed
        this.paginar2();
    }//GEN-LAST:event_cboTotalPorPagina2ActionPerformed

    private void btnNuevo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo1ActionPerformed
        tabGeneral.setEnabledAt(1, true);
        tabGeneral.setEnabledAt(0, false);
        tabGeneral.setEnabledAt(2, false);
        tabGeneral.setSelectedIndex(1);
        this.accion="guardar";
    }//GEN-LAST:event_btnNuevo1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (txtId.getText().length()==0){
            JOptionPane.showMessageDialog(this, "Debes seleccionar un cliente.","Sistema", JOptionPane.WARNING_MESSAGE);
            btnCliente.requestFocus();
            return;
        }
        if (txtFechaI.getText().length()==0){
            JOptionPane.showMessageDialog(this, "Debes generar cuotas.","Sistema", JOptionPane.WARNING_MESSAGE);
            BntCuotas.requestFocus();
            return;
        }
        if (tablaListado.getRowCount()==0){
            JOptionPane.showMessageDialog(this, "Debes generar cuotas.","Sistema", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (txtIdContrato.getText().length()==0){
            JOptionPane.showMessageDialog(this, "Debes ingresar el ID Contrato.","Sistema", JOptionPane.WARNING_MESSAGE);
            txtIdContrato.requestFocus();
            return;
        }
        String resp="";
        int empleado_id = Variables.EmpleadoId;
        resp=this.CONTROLCNT.Insertar(Integer.parseInt(txtId.getText()),Integer.parseInt(txtDocumentos.getText()),txtFecha.getText(),txtFechaI.getText(),Double.parseDouble(txtMonto.getValue().toString()),Integer.parseInt(txtInteres.getValue().toString()),Integer.parseInt(txtCuotas.getValue().toString()),(String)txtForma.getSelectedItem(),Double.parseDouble(txtAbono.getText()),Double.parseDouble(txtInteresT.getText()),Double.parseDouble(txtTotalPagar.getText()),txtClausulas.getText(),empleado_id);
        if (resp.equals("OK")) {
            this.mensajeOk("Prestamo registrado correctamente");
            this.listar2("",false);
        } else {
            this.mensajeError(resp);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txtBuscar2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscar2KeyTyped
        int key = evt.getKeyChar();

        boolean numeros = key >= 48 && key <= 57;

        if (!numeros) {
            evt.consume();
        }
    }//GEN-LAST:event_txtBuscar2KeyTyped

    private void txtIdContratoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdContratoKeyTyped
        int key = evt.getKeyChar();

        boolean numeros = key >= 48 && key <= 57;

        if (!numeros) {
            evt.consume();
        }
    }//GEN-LAST:event_txtIdContratoKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BntCuotas;
    private javax.swing.JButton btnCliente;
    private javax.swing.JButton btnNuevo1;
    private javax.swing.JComboBox<String> cboNumPagina;
    private javax.swing.JComboBox<String> cboNumPagina2;
    private javax.swing.JComboBox<String> cboTotalPorPagina;
    private javax.swing.JComboBox<String> cboTotalPorPagina2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JLabel lblTotalRegistros;
    private javax.swing.JLabel lblTotalRegistros2;
    private javax.swing.JTable tabContrato;
    private javax.swing.JTabbedPane tabGeneral;
    private javax.swing.JTable tablaListado;
    private javax.swing.JTable tbIdCliente;
    private javax.swing.JTable tbIdContrato;
    private javax.swing.JTable tblCuotas;
    private javax.swing.JTextField txtAbono;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtBuscar2;
    private javax.swing.JButton txtCancelar;
    private javax.swing.JTextArea txtClausulas;
    private javax.swing.JSpinner txtCuotas;
    private javax.swing.JTextField txtDocumentos;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtFechaI;
    private javax.swing.JComboBox<String> txtForma;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtIdContrato;
    private javax.swing.JSpinner txtInteres;
    private javax.swing.JTextField txtInteresT;
    private javax.swing.JSpinner txtMonto;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTabCuota;
    private javax.swing.JTextField txtTabFecha;
    private javax.swing.JTextField txtTabMonto;
    private javax.swing.JTextField txtTotalPagar;
    // End of variables declaration//GEN-END:variables
}

