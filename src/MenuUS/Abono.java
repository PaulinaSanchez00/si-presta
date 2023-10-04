/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package MenuUS;
import Control.AbonoControl;
import imprimir.Impresion;
import Control.EmpleadoControl;
import Control.PagoControl;
import Entidades.Variables;
import Impresion.Print;
import Login.Login;
import static MenuUS.Abono.fechaActual;
import static imprimir.Impresion.txtIDPago;
import imprimir.InterfazImprimir;
import javax.swing.JOptionPane;
import javax.swing.table.TableRowSorter;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Abono extends javax.swing.JInternalFrame {
    //Abono
    private final AbonoControl CONTROLABO;
    private String accion;
    
    private int totalPorPagina1 = 10;
    private int numPagina1 = 1;
    private boolean primeraCarga1 = true;
    private int totalRegistros1;
    
    //Pago
    private final PagoControl CONTROLPAG;
    private String accion1;
    
    private int totalPorPagina = 10;
    private int numPagina = 1;
    private boolean primeraCarga = true;
    private int totalRegistros;
    
    public Abono() {
        initComponents();
        //Abono
        this.CONTROLABO=new AbonoControl();
        this.accion="guardar";
        this.paginar1();
        this.primeraCarga1=false;
        this.listar1("",false);
        txtFecha.setText(fechaActual());
        txtFecha.setEditable(false);
        txtCuota.setEditable(false);
        txtFechaL.setEditable(false);
        txtMonto.setEditable(false);
        txtCliente_id.setEditable(false);
        
        
        
        //Pago
        this.CONTROLPAG=new PagoControl();
        this.accion1="guardar";
        this.paginar();
        this.primeraCarga=false;
        this.listar("",false);
        
        txtID.setVisible(false);
        txtIDCONTRATO.setVisible(false);
        txtIDCLIENTE.setVisible(false);
        txtFECHA.setVisible(false);
        txtFECHAL.setVisible(false);
        txtMONTO.setVisible(false);
        txtDINERO.setVisible(false);
        txtFERIA.setVisible(false);
        txtIDEMPLEADO.setVisible(false);
        
        tabGeneral.setEnabledAt(1, false);
        tabGeneral.setEnabledAt(0, true);
        tabGeneral.setEnabledAt(2, true);
        tabGeneral.setSelectedIndex(0);
     }
    
    private void paginar1(){
        int totalPaginas1;
        
        this.totalRegistros1=this.CONTROLABO.Total();
        this.totalPorPagina1=Integer.parseInt((String)cboTotalPorPagina1.getSelectedItem());
        totalPaginas1=(int)(Math.ceil((double)this.totalRegistros1/this.totalPorPagina1));
        if (totalPaginas1==0){
            totalPaginas1=1;
        }
        cboNumPagina1.removeAllItems();
        
        for (int i = 1; i <= totalPaginas1; i++) {
            cboNumPagina1.addItem(Integer.toString(i));
        }
        cboNumPagina1.setSelectedIndex(0);
    }
    
    private void paginar(){
        int totalPaginas;
        
        this.totalRegistros=this.CONTROLPAG.Total();
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
    
    public static String fechaActual(){
    
        Date fecha=new Date();
        SimpleDateFormat formatoFecha=new SimpleDateFormat("YYYY/MM/dd");
        
        return formatoFecha.format(fecha);
    }
    
    
    private void listar1(String texto, boolean paginar){
    this.totalPorPagina1=Integer.parseInt((String)cboTotalPorPagina1.getSelectedItem());
        if ((String)cboNumPagina1.getSelectedItem()!=null){
            this.numPagina1=Integer.parseInt((String)cboNumPagina1.getSelectedItem());
        }
        
        if (paginar==true){
            tabAbonos.setModel(this.CONTROLABO.listar1(texto,this.totalPorPagina1,this.numPagina1));
        }else{
            tabAbonos.setModel(this.CONTROLABO.listar1(texto,this.totalPorPagina1,1));
        }
    TableRowSorter orden=new TableRowSorter(tabAbonos.getModel());
    tabAbonos.setRowSorter(orden);
    this.ocultarColumnas1();
    lblTotalRegistros1.setText("Mostrando " + this.CONTROLABO.TotalMostrados() + " de un total de " + this.CONTROLABO.Total()+ " registros");
    }
    
    private void listar(String texto, boolean paginar){
    this.totalPorPagina=Integer.parseInt((String)cboTotalPorPagina.getSelectedItem());
        if ((String)cboNumPagina.getSelectedItem()!=null){
            this.numPagina=Integer.parseInt((String)cboNumPagina.getSelectedItem());
        }
        if (paginar==true){
            tabPago.setModel(this.CONTROLPAG.listar(texto,this.totalPorPagina,this.numPagina));
        }else{
            tabPago.setModel(this.CONTROLPAG.listar(texto,this.totalPorPagina,1));
        }
    TableRowSorter orden=new TableRowSorter(tabPago.getModel());
    tabPago.setRowSorter(orden);
    //this.ocultarColumnas();
    lblTotalRegistros.setText("Mostrando " + this.CONTROLPAG.TotalMostrados() + " de un total de " + this.CONTROLPAG.Total()+ " registros");
    }
    
    private void ocultarColumnas1(){
        tabAbonos.getColumnModel().getColumn(0).setMaxWidth(0);
        tabAbonos.getColumnModel().getColumn(0).setMinWidth(0);
        tabAbonos.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        tabAbonos.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
        
        tabAbonos.getColumnModel().getColumn(5).setMaxWidth(0);
        tabAbonos.getColumnModel().getColumn(5).setMinWidth(0);
        tabAbonos.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
        tabAbonos.getTableHeader().getColumnModel().getColumn(5).setMinWidth(0);
        
        tabAbonos.getColumnModel().getColumn(6).setMaxWidth(0);
        tabAbonos.getColumnModel().getColumn(6).setMinWidth(0);
        tabAbonos.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(0);
        tabAbonos.getTableHeader().getColumnModel().getColumn(6).setMinWidth(0);
        
    }
    
    private void limpiar(){
    txtCuota.setText("");
    txtBuscar1.setText("");
    txtFechaL.setText("");
    txtMonto.setText("");
    txtCliente_id.setText("");
    txtDinero.setText("");
    txtContrato_id.setText("");
    txtContra.setText("");
    this.accion="guardar";}
    
    private void mensajeError(String mensaje){
        JOptionPane.showMessageDialog(this, mensaje,"Sistema",JOptionPane.ERROR_MESSAGE);
    }
    
    private void mensajeOk(String mensaje){
        JOptionPane.showMessageDialog(this, mensaje,"Sistema",JOptionPane.INFORMATION_MESSAGE);
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
        jPanel1 = new javax.swing.JPanel();
        txtBuscar1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jScrolAbonos = new javax.swing.JScrollPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        tabAbonos = new javax.swing.JTable();
        jLabel31 = new javax.swing.JLabel();
        cboNumPagina1 = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        cboTotalPorPagina1 = new javax.swing.JComboBox<>();
        lblTotalRegistros1 = new javax.swing.JLabel();
        btnNuevo1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        txtDinero = new javax.swing.JTextField();
        txtFecha = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtContrato_id = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtCuota = new javax.swing.JTextField();
        txtFechaL = new javax.swing.JTextField();
        txtMonto = new javax.swing.JTextField();
        txtCliente_id = new javax.swing.JTextField();
        txtContra = new javax.swing.JPasswordField();
        jLabel18 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrolAbonos1 = new javax.swing.JScrollPane();
        jScrollPane7 = new javax.swing.JScrollPane();
        tabPago = new javax.swing.JTable();
        jLabel33 = new javax.swing.JLabel();
        cboNumPagina = new javax.swing.JComboBox<>();
        jLabel34 = new javax.swing.JLabel();
        cboTotalPorPagina = new javax.swing.JComboBox<>();
        lblTotalRegistros = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        txtID = new javax.swing.JTextField();
        txtIDCLIENTE = new javax.swing.JTextField();
        txtIDCONTRATO = new javax.swing.JTextField();
        txtFECHA = new javax.swing.JTextField();
        txtFECHAL = new javax.swing.JTextField();
        txtMONTO = new javax.swing.JTextField();
        txtDINERO = new javax.swing.JTextField();
        txtFERIA = new javax.swing.JTextField();
        txtIDEMPLEADO = new javax.swing.JTextField();

        setBackground(new java.awt.Color(204, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Abono");

        tabGeneral.setBackground(new java.awt.Color(204, 255, 255));

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        txtBuscar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscar1KeyTyped(evt);
            }
        });

        jLabel4.setText("ID Contrato:");

        jButton6.setBackground(new java.awt.Color(204, 204, 204));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar.png"))); // NOI18N
        jButton6.setText("Buscar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        tabAbonos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane6.setViewportView(tabAbonos);

        jScrolAbonos.setViewportView(jScrollPane6);

        jLabel31.setText("# Página");

        cboNumPagina1.setBackground(new java.awt.Color(0, 153, 153));
        cboNumPagina1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNumPagina1ActionPerformed(evt);
            }
        });

        jLabel32.setText("Total de registros por página ");

        cboTotalPorPagina1.setBackground(new java.awt.Color(0, 153, 153));
        cboTotalPorPagina1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "10", "20", "50", "100", "200", "500" }));
        cboTotalPorPagina1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTotalPorPagina1ActionPerformed(evt);
            }
        });

        lblTotalRegistros1.setText("Registros:");

        btnNuevo1.setBackground(new java.awt.Color(204, 204, 204));
        btnNuevo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/bolsa-de-dinero (1).png"))); // NOI18N
        btnNuevo1.setText("Pagar");
        btnNuevo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevo1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrolAbonos, javax.swing.GroupLayout.PREFERRED_SIZE, 623, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnNuevo1)))
                .addContainerGap(151, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboNumPagina1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboTotalPorPagina1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotalRegistros1, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jButton6)
                    .addComponent(btnNuevo1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrolAbonos, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(cboTotalPorPagina1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotalRegistros1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboNumPagina1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabGeneral.addTab("Historial Abonos", jPanel1);

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));

        jPanel3.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ABONO");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addContainerGap(687, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        txtDinero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDineroKeyTyped(evt);
            }
        });

        jLabel50.setText("ID Contrato:");

        jLabel2.setText("Fecha de Registro:");

        txtContrato_id.setBackground(new java.awt.Color(204, 204, 204));
        txtContrato_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContrato_idActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(204, 204, 204));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/bolsa-de-dinero.png"))); // NOI18N
        jButton4.setText("Pagar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel16.setText("Dinero Recibido:");

        jLabel49.setText("ID Cliente:");

        jButton5.setBackground(new java.awt.Color(204, 204, 204));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancelar_1.png"))); // NOI18N
        jButton5.setText("Cancelar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel13.setText("Monto a Pagar:");

        jLabel12.setText("Fecha Limite:");

        jLabel15.setText("N° de Cuota:");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 102, 102));
        jLabel14.setText("INFORMACION DEL ABONO");

        txtCuota.setBackground(new java.awt.Color(204, 204, 204));

        txtFechaL.setBackground(new java.awt.Color(204, 204, 204));

        txtMonto.setBackground(new java.awt.Color(204, 204, 204));

        txtCliente_id.setBackground(new java.awt.Color(204, 204, 204));
        txtCliente_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCliente_idActionPerformed(evt);
            }
        });

        jLabel18.setText("Contraseña:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(txtDinero, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtCuota, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel12)
                                        .addComponent(txtFechaL, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel13)
                                        .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel49, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtCliente_id, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel50, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtContrato_id, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(270, 270, 270)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtContra, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtContra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(15, 15, 15)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel49)
                                .addComponent(jLabel13)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCuota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFechaL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCliente_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel50)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtContrato_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDinero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        tabGeneral.addTab("Registrar Prestamo", jPanel2);

        jPanel5.setBackground(new java.awt.Color(204, 255, 255));

        tabPago.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane7.setViewportView(tabPago);

        jScrolAbonos1.setViewportView(jScrollPane7);

        jLabel33.setText("# Página");

        cboNumPagina.setBackground(new java.awt.Color(0, 153, 153));
        cboNumPagina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNumPaginaActionPerformed(evt);
            }
        });

        jLabel34.setText("Total de registros por página ");

        cboTotalPorPagina.setBackground(new java.awt.Color(0, 153, 153));
        cboTotalPorPagina.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "10", "20", "50", "100", "200", "500" }));
        cboTotalPorPagina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTotalPorPaginaActionPerformed(evt);
            }
        });

        lblTotalRegistros.setText("Registros:");

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarKeyTyped(evt);
            }
        });

        jLabel5.setText("ID Contrato:");

        jButton7.setBackground(new java.awt.Color(204, 204, 204));
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar.png"))); // NOI18N
        jButton7.setText("Buscar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton1.setText("Imprimir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(160, Short.MAX_VALUE)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboNumPagina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboTotalPorPagina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTotalRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton7)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIDCLIENTE, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIDCONTRATO, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFECHA, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFECHAL, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMONTO, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDINERO, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFERIA, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIDEMPLEADO, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrolAbonos1)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jButton7)
                    .addComponent(jButton1)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIDCLIENTE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIDCONTRATO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFECHA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFECHAL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMONTO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDINERO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFERIA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIDEMPLEADO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrolAbonos1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(cboTotalPorPagina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotalRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboNumPagina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        tabGeneral.addTab("Historial Pagos", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabGeneral)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabGeneral)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCliente_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCliente_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCliente_idActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        tabGeneral.setEnabledAt(0, true);
        tabGeneral.setEnabledAt(2, true);
        tabGeneral.setEnabledAt(1, false);
        tabGeneral.setSelectedIndex(0);
        limpiar();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int empleado_id = Variables.EmpleadoId;
        if (txtDinero.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Debes de ingresar el Dinero Recibido", "Sistema", JOptionPane.WARNING_MESSAGE);
            txtDinero.requestFocus();
            return;
        }
        double Monto, Dinero, Feria;
        Monto = Double.parseDouble(txtMonto.getText());
        Dinero = (Double.parseDouble(txtDinero.getText()));
        Feria = Dinero - Monto;
        if (Dinero < Monto) {
            JOptionPane.showMessageDialog(this, "La cantidad de Dinero Recibido es menor a el Monto a Pagar", "Sistema", JOptionPane.WARNING_MESSAGE);
        } else {
            if (txtContra.getText().length() == 0 || txtContra.getText().length() > 64) {
                JOptionPane.showMessageDialog(this, "Debes ingresar una contraseña", "Inicio de sesion", JOptionPane.INFORMATION_MESSAGE);
                txtContra.requestFocus();
                return;
            }
            EmpleadoControl Control = new EmpleadoControl();
            String resp2 = Control.login(empleado_id, txtContra.getText());
            if (resp2.equals("1")) {

                String id = String.valueOf(tabAbonos.getValueAt(tabAbonos.getSelectedRow(), 0));
                String resp = this.CONTROLABO.Activar(Integer.parseInt(id));
                String resp1 = this.CONTROLPAG.Insertar(Integer.parseInt(txtCliente_id.getText()), Integer.parseInt(txtContrato_id.getText()), txtFecha.getText(), txtFechaL.getText(), Double.parseDouble(txtMonto.getText()), Double.parseDouble(txtDinero.getText()), Double.parseDouble(Double.toString(Feria)), empleado_id);
                if (resp.equals("OK") || resp1.equals("OK")) {
                    this.mensajeOk("Abono Correcto");
                    JOptionPane.showMessageDialog(this, ("Regresa al Cliente $" + Feria), "Sistema", JOptionPane.INFORMATION_MESSAGE);
                    this.listar1("", false);
                    this.listar("", false);
                    Print p = new Print();
                    p.setVisible(true);
                    tabGeneral.setEnabledAt(0, true);
                    tabGeneral.setEnabledAt(1, false);
                    tabGeneral.setEnabledAt(2, true);
                    tabGeneral.setSelectedIndex(2);
                    limpiar();
                } else {
                    this.mensajeError(resp);
                }
            } else if (resp2.equals("2")) {
                JOptionPane.showMessageDialog(this, "El empleado se encuentra en descanso", "Acceso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "La Contraseña o Id no existen", "Acceso", JOptionPane.INFORMATION_MESSAGE);
            } 
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void cboTotalPorPagina1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTotalPorPagina1ActionPerformed
        this.paginar1();
    }//GEN-LAST:event_cboTotalPorPagina1ActionPerformed

    private void cboNumPagina1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNumPagina1ActionPerformed
        if (this.primeraCarga1==false){
            this.listar1("",true);
        }
    }//GEN-LAST:event_cboNumPagina1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        this.listar1(txtBuscar1.getText(),false);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void btnNuevo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevo1ActionPerformed
        
        if (tabAbonos.getSelectedRowCount() == 1) {
            tabGeneral.setEnabledAt(1, true);
            tabGeneral.setEnabledAt(0, false);
            tabGeneral.setEnabledAt(2, false);
            tabGeneral.setSelectedIndex(1);

            String cuota = String.valueOf(tabAbonos.getValueAt(tabAbonos.getSelectedRow(), 1));
            String fecha = String.valueOf(tabAbonos.getValueAt(tabAbonos.getSelectedRow(), 2));
            String monto = String.valueOf(tabAbonos.getValueAt(tabAbonos.getSelectedRow(), 3));
            String Cliente_id = String.valueOf(tabAbonos.getValueAt(tabAbonos.getSelectedRow(), 5));
            String Contrato_id = String.valueOf(tabAbonos.getValueAt(tabAbonos.getSelectedRow(), 6));

            txtCuota.setText(cuota);
            txtFechaL.setText(fecha);
            txtMonto.setText(monto);
            txtCliente_id.setText(Cliente_id);
            txtContrato_id.setText(Contrato_id);
        } else {
            JOptionPane.showMessageDialog(this, "Debes Seleccionar un Abono.", "Sistema", JOptionPane.WARNING_MESSAGE);
        }
        this.accion = "guardar";
    }//GEN-LAST:event_btnNuevo1ActionPerformed

    private void txtContrato_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContrato_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtContrato_idActionPerformed

    private void cboNumPaginaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNumPaginaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboNumPaginaActionPerformed

    private void cboTotalPorPaginaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTotalPorPaginaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboTotalPorPaginaActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        this.listar(txtBuscar.getText(),false);
        txtBuscar.setText("");
    }//GEN-LAST:event_jButton7ActionPerformed

    private void txtBuscar1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscar1KeyTyped
        int key = evt.getKeyChar();

        boolean numeros = key >= 48 && key <= 57;

        if (!numeros) {
            evt.consume();
        }
    }//GEN-LAST:event_txtBuscar1KeyTyped

    private void txtDineroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDineroKeyTyped
        
    }//GEN-LAST:event_txtDineroKeyTyped

    private void txtBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyTyped
        int key = evt.getKeyChar();

        boolean numeros = key >= 48 && key <= 57;

        if (!numeros) {
            evt.consume();
        }
    }//GEN-LAST:event_txtBuscarKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         if (tabPago.getSelectedRowCount() == 1) {
            String id = String.valueOf(tabPago.getValueAt(tabPago.getSelectedRow(), 0));
            String contrato = String.valueOf(tabPago.getValueAt(tabPago.getSelectedRow(), 1));
            String cliente = String.valueOf(tabPago.getValueAt(tabPago.getSelectedRow(), 2));
            String fecha = String.valueOf(tabPago.getValueAt(tabPago.getSelectedRow(), 3));
            String fechal = String.valueOf(tabPago.getValueAt(tabPago.getSelectedRow(), 4));
            String monto = String.valueOf(tabPago.getValueAt(tabPago.getSelectedRow(), 5));
            String dinero = String.valueOf(tabPago.getValueAt(tabPago.getSelectedRow(), 6));
            String feria = String.valueOf(tabPago.getValueAt(tabPago.getSelectedRow(), 7));
            String empleado = String.valueOf(tabPago.getValueAt(tabPago.getSelectedRow(), 8));

            txtID.setText(id);
            txtIDCONTRATO.setText(contrato);
            txtIDCLIENTE.setText(cliente);
            txtFECHA.setText(fecha);
            txtFECHAL.setText(fechal);
            txtMONTO.setText(monto);
            txtDINERO.setText(dinero);
            txtFERIA.setText(feria);
            txtIDEMPLEADO.setText(empleado);
            
            
            Impresion i = new Impresion();
            i.setVisible(true);
            String info=txtID.getText();
            Impresion.txtIDPago.setText(info);
            
            InterfazImprimir II = new InterfazImprimir();
            II.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Debes Seleccionar un Pago.", "Sistema", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnNuevo1;
    private javax.swing.JComboBox<String> cboNumPagina;
    private javax.swing.JComboBox<String> cboNumPagina1;
    private javax.swing.JComboBox<String> cboTotalPorPagina;
    private javax.swing.JComboBox<String> cboTotalPorPagina1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrolAbonos;
    private javax.swing.JScrollPane jScrolAbonos1;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JLabel lblTotalRegistros;
    private javax.swing.JLabel lblTotalRegistros1;
    private javax.swing.JTable tabAbonos;
    private javax.swing.JTabbedPane tabGeneral;
    private javax.swing.JTable tabPago;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtBuscar1;
    public static javax.swing.JTextField txtCliente_id;
    private javax.swing.JPasswordField txtContra;
    public static javax.swing.JTextField txtContrato_id;
    private javax.swing.JTextField txtCuota;
    public static javax.swing.JTextField txtDINERO;
    public static javax.swing.JTextField txtDinero;
    public static javax.swing.JTextField txtFECHA;
    public static javax.swing.JTextField txtFECHAL;
    public static javax.swing.JTextField txtFERIA;
    public static javax.swing.JTextField txtFecha;
    public static javax.swing.JTextField txtFechaL;
    public static javax.swing.JTextField txtID;
    public static javax.swing.JTextField txtIDCLIENTE;
    public static javax.swing.JTextField txtIDCONTRATO;
    public static javax.swing.JTextField txtIDEMPLEADO;
    public static javax.swing.JTextField txtMONTO;
    public static javax.swing.JTextField txtMonto;
    // End of variables declaration//GEN-END:variables
}

