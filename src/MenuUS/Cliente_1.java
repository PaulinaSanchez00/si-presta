/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package MenuUS;


import BD.Conexion;
import Control.ClienteControl;
import Control.DocumentosControl;
import javax.swing.JOptionPane;
import javax.swing.table.TableRowSorter;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
/**
 *
 * @author pauli
 */
public class Cliente_1 extends javax.swing.JInternalFrame {
    private final ClienteControl CONTROLCLT;
    private final DocumentosControl CONTROLDOC;
    private String Nombre_Ant;
    private String ine_Ant;
    private String accion;
    private String accion1;
    
    private String rutaOrigen;
    private String rutaDestino;
    private final String DIRECTORIO="src/files";
    private String imagen="";
    
    private javax.swing.JScrollPane jScroollPanel;
    private javax.swing.JTable tDocumentos;
    
    private final Conexion CON;
    private PreparedStatement ps;
    
    private int totalPorPagina = 10;
    private int numPagina = 1;
    private boolean primeraCarga = true;
    private int totalRegistros;
    
    private int totalPorPagina1 = 10;
    private int numPagina1 = 1;
    private boolean primeraCarga1 = true;
    private int totalRegistros1;

    
    public Cliente_1() {
        initComponents();
        this.CONTROLCLT=new ClienteControl();
        this.CONTROLDOC=new DocumentosControl();
        this.accion="guardar";
        this.accion1="guardar";
        this.paginar();
        this.paginar1();
        this.primeraCarga=false;
        this.primeraCarga1=false;
        this.listar("",false);
        this.listarid("");
        this.listarid2("");
        this.listar1("",false);
        tabGeneral.setEnabledAt(3, false);
        tabGeneral.setEnabledAt(2, false);
        txtId.setVisible(false);
        txtId2.setVisible(false);
        txtIne.setEditable(false);
        txtActa.setEditable(false);
        txtCom.setEditable(false);
        CON = Conexion.getInstancia();
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
    
    private void paginar1(){
        int totalPaginas1;
        
        this.totalRegistros1=this.CONTROLDOC.Total();
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
   
    private void subirImagenes() throws IOException{
        File origen=new File(this.rutaOrigen);
        File destino=new File(this.rutaDestino);
        try{
            InputStream in=new FileInputStream(origen);
            OutputStream out=new FileOutputStream(destino);
            byte[] buf = new byte[1024];
            int len;
            while((len = in.read(buf)) > 0){
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
            jLabel1.setIcon(null);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }  
    }
    
    
    private void listar1(String texto, boolean paginar){
    this.totalPorPagina1=Integer.parseInt((String)cboTotalPorPagina1.getSelectedItem());
        if ((String)cboNumPagina1.getSelectedItem()!=null){
            this.numPagina1=Integer.parseInt((String)cboNumPagina1.getSelectedItem());
        }
        
        if (paginar==true){
            tablaListado1.setModel(this.CONTROLDOC.listar1(texto,this.totalPorPagina1,this.numPagina1));
        }else{
            tablaListado1.setModel(this.CONTROLDOC.listar1(texto,this.totalPorPagina1,1));
        }TableRowSorter orden=new TableRowSorter(tablaListado1.getModel());
    tablaListado1.setRowSorter(orden);
    lblTotalRegistros1.setText("Mostrando " + this.CONTROLDOC.TotalMostrados() + " de un total de " + this.CONTROLDOC.Total()+ " registros");
    
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
    private void listarid(String texto){
    tbIdDocumentos.setModel(this.CONTROLDOC.listarid(texto));
    TableRowSorter orden=new TableRowSorter(tbIdDocumentos.getModel());
    tbIdDocumentos.setRowSorter(orden);
    this.accion1="guardar";
    tbIdDocumentos.setRowSorter(orden);
    }
    private void listarid2(String texto){
    tbIdCliente.setModel(this.CONTROLCLT.listarid2(texto));
    TableRowSorter orden=new TableRowSorter(tbIdCliente.getModel());
    tbIdCliente.setRowSorter(orden);
    this.accion="guardar";
    tbIdCliente.setRowSorter(orden);
    }
    private void ocultarColumnas(){
        tablaListado.getColumnModel().getColumn(3).setMaxWidth(0);
        tablaListado.getColumnModel().getColumn(3).setMinWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(0);
        tablaListado.getTableHeader().getColumnModel().getColumn(3).setMinWidth(0);
        
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
    private void limpiar(){
    txtNombre.setText("");
    txtApellido.setText("");
    txtFec_Nac.setText("");
    txtCalle.setText("");
    txtColonia.setText("");
    txtNum.setText("");
    txtCiudad.setText("");
    txtPostal.setText("");
    txtMunicipio.setText("");
    txtEstado.setText("");
    txtTelefono.setText("");
    txtCalleT.setText("");
    txtNumT.setText("");
    txtColoniaT.setText("");
    txtCiudadT.setText("");
    txtPostalT.setText("");
    txtMunicipioT.setText("");
    txtEstadoT.setText("");
    txtNombreJ.setText("");
    txtApellidoJ.setText("");
    txtTelefonoT.setText("");
    txtPuesto.setText("");
    txtCorreo.setText("");
    txtDocumentos.setText("");
    
    txtCom.setText("");
    lblIne.setText("");
    txtActa.setText("");
    lblIne.setIcon(null);
    lblAct.setIcon(null);
    lblComp.setIcon(null);
    }
    
    private void mensajeError(String mensaje){
        JOptionPane.showMessageDialog(this, mensaje,"Sistema",JOptionPane.ERROR_MESSAGE);
    }
    
    private void mensajeOk(String mensaje){
        JOptionPane.showMessageDialog(this, mensaje,"Sistema",JOptionPane.INFORMATION_MESSAGE);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabGeneral = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        txtBuscar = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnActivar = new javax.swing.JButton();
        btnDesactivar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaListado = new javax.swing.JTable();
        jLabel25 = new javax.swing.JLabel();
        cboNumPagina = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        cboTotalPorPagina = new javax.swing.JComboBox<>();
        lblTotalRegistros = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        txtBuscar1 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        btnBuscar1 = new javax.swing.JButton();
        btnEditar1 = new javax.swing.JButton();
        cboTotalPorPagina1 = new javax.swing.JComboBox<>();
        jLabel31 = new javax.swing.JLabel();
        cboNumPagina1 = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        lblTotalRegistros1 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaListado1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtCalle = new javax.swing.JTextField();
        txtColonia = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtCiudad = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtMunicipio = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtCalleT = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtColoniaT = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtCiudadT = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtMunicipioT = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtNombreJ = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtApellidoJ = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtTelefonoT = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        txtPuesto = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtNum = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtPostal = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txtEstado = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtNumT = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtPostalT = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtEstadoT = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        txtDocumentos = new javax.swing.JTextField();
        btnCancelar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        txtId = new javax.swing.JTextField();
        jScrolCliente = new javax.swing.JScrollPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbIdCliente = new javax.swing.JTable();
        txtFec_Nac = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        lblComp = new javax.swing.JLabel();
        btnCancelar1 = new javax.swing.JButton();
        lblIne = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        lblAct = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        txtActa = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        txtIne = new javax.swing.JTextField();
        txtCom = new javax.swing.JTextField();
        btnGuardar1 = new javax.swing.JButton();
        txtId2 = new javax.swing.JTextField();
        jScrolDocumentos = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbIdDocumentos = new javax.swing.JTable();

        setBackground(new java.awt.Color(204, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle(" Cliente");

        tabGeneral.setBackground(new java.awt.Color(204, 255, 255));

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        jLabel1.setText("Nombre:");

        btnBuscar.setBackground(new java.awt.Color(204, 204, 204));
        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnNuevo.setBackground(new java.awt.Color(204, 204, 204));
        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/anadir.png"))); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnEditar.setBackground(new java.awt.Color(204, 204, 204));
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/editar_1.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnActivar.setBackground(new java.awt.Color(204, 204, 204));
        btnActivar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/aceptar.png"))); // NOI18N
        btnActivar.setText("Activar");
        btnActivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActivarActionPerformed(evt);
            }
        });

        btnDesactivar.setBackground(new java.awt.Color(204, 204, 204));
        btnDesactivar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancelar_1.png"))); // NOI18N
        btnDesactivar.setText("Desactivar");
        btnDesactivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesactivarActionPerformed(evt);
            }
        });

        jScrollPane1.setBackground(new java.awt.Color(0, 153, 153));

        tablaListado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(tablaListado);

        jScrollPane1.setViewportView(jScrollPane3);

        jLabel25.setText("# Página");

        cboNumPagina.setBackground(new java.awt.Color(204, 204, 204));
        cboNumPagina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNumPaginaActionPerformed(evt);
            }
        });

        jLabel26.setText("Total de registros por página ");

        cboTotalPorPagina.setBackground(new java.awt.Color(204, 204, 204));
        cboTotalPorPagina.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "10", "20", "50", "100", "200", "500" }));
        cboTotalPorPagina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTotalPorPaginaActionPerformed(evt);
            }
        });

        lblTotalRegistros.setText("Registros:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1)
                        .addGap(4, 4, 4)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(btnBuscar)
                        .addGap(18, 18, 18)
                        .addComponent(btnNuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEditar)
                        .addGap(0, 451, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addGap(47, 47, 47)
                        .addComponent(cboNumPagina, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(88, 88, 88)
                        .addComponent(jLabel26)
                        .addGap(35, 35, 35)
                        .addComponent(cboTotalPorPagina, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(btnDesactivar, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnActivar, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblTotalRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscar)
                    .addComponent(btnNuevo)
                    .addComponent(btnEditar)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnDesactivar)
                        .addComponent(btnActivar)
                        .addComponent(cboNumPagina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel25)
                        .addComponent(jLabel26))
                    .addComponent(cboTotalPorPagina))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(lblTotalRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(140, 140, 140))
        );

        tabGeneral.addTab("Lista de Clientes", jPanel1);

        jPanel3.setBackground(new java.awt.Color(204, 255, 255));

        txtBuscar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscar1KeyTyped(evt);
            }
        });

        jLabel24.setText("ID:");

        btnBuscar1.setBackground(new java.awt.Color(204, 204, 204));
        btnBuscar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/buscar.png"))); // NOI18N
        btnBuscar1.setText("Buscar");
        btnBuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar1ActionPerformed(evt);
            }
        });

        btnEditar1.setBackground(new java.awt.Color(204, 204, 204));
        btnEditar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/editar_1.png"))); // NOI18N
        btnEditar1.setText("Editar");
        btnEditar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditar1ActionPerformed(evt);
            }
        });

        cboTotalPorPagina1.setBackground(new java.awt.Color(204, 204, 204));
        cboTotalPorPagina1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "10", "20", "50", "100", "200", "500" }));
        cboTotalPorPagina1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTotalPorPagina1ActionPerformed(evt);
            }
        });

        jLabel31.setText("Total de registros por página ");

        cboNumPagina1.setBackground(new java.awt.Color(204, 204, 204));
        cboNumPagina1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNumPagina1ActionPerformed(evt);
            }
        });

        jLabel32.setText("# Página");

        lblTotalRegistros1.setText("Registros:");

        tablaListado1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(tablaListado1);

        jScrollPane6.setViewportView(jScrollPane4);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel24)
                                .addGap(4, 4, 4)
                                .addComponent(txtBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscar1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEditar1))
                            .addComponent(lblTotalRegistros1, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel32)
                                .addGap(47, 47, 47)
                                .addComponent(cboNumPagina1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(55, 55, 55)
                                .addComponent(jLabel31)
                                .addGap(18, 18, 18)
                                .addComponent(cboTotalPorPagina1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 373, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel24))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtBuscar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBuscar1)
                        .addComponent(btnEditar1)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboNumPagina1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTotalPorPagina1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32)
                    .addComponent(jLabel31))
                .addGap(18, 18, 18)
                .addComponent(lblTotalRegistros1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(149, Short.MAX_VALUE))
        );

        tabGeneral.addTab("Documentos", jPanel3);

        jPanel5.setBackground(new java.awt.Color(204, 255, 255));

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreKeyTyped(evt);
            }
        });

        txtApellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtApellidoKeyTyped(evt);
            }
        });

        jLabel2.setText("Nombres:");

        jLabel4.setText("Calle:");

        jLabel5.setText("Colonia:");

        jLabel3.setText("Apellidos:");

        jLabel6.setText("Ciudad:");

        jLabel7.setText("Num. Ext.:");

        jLabel8.setText("Fecha de Nacimiento:");

        jLabel27.setText("Correo Electronico:");

        txtCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCorreoActionPerformed(evt);
            }
        });

        jLabel12.setText("Telefono:");

        txtTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTelefonoKeyTyped(evt);
            }
        });

        jLabel14.setText("Datos del trabajo");

        jLabel16.setText("Calle:");

        jLabel17.setText("Colonia:");

        jLabel18.setText("Ciudad:");

        jLabel15.setText("Municipio:");

        jLabel9.setText("Nombre Jefe:");

        txtNombreJ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNombreJKeyTyped(evt);
            }
        });

        jLabel10.setText("Apellido Jefe:");

        txtApellidoJ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidoJActionPerformed(evt);
            }
        });

        jLabel11.setText("Telefono Trabajo:");

        jLabel48.setText("Puesto:");

        txtPuesto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPuestoKeyTyped(evt);
            }
        });

        jLabel13.setText("Municipio:");

        txtNum.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumKeyTyped(evt);
            }
        });

        jLabel19.setText("Cod. Postal:");

        txtPostal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPostalKeyTyped(evt);
            }
        });

        jLabel20.setText("Estado:");

        txtEstado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEstadoKeyTyped(evt);
            }
        });

        jLabel21.setText("Num. Ext.:");

        txtNumT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumTActionPerformed(evt);
            }
        });
        txtNumT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumTKeyTyped(evt);
            }
        });

        jLabel22.setText("Cod. Postal:");

        txtPostalT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPostalTActionPerformed(evt);
            }
        });
        txtPostalT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPostalTKeyTyped(evt);
            }
        });

        jLabel23.setText("Estado:");

        txtEstadoT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEstadoTKeyTyped(evt);
            }
        });

        jLabel49.setText("ID Documentos:");

        txtDocumentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDocumentosActionPerformed(evt);
            }
        });
        txtDocumentos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDocumentosKeyTyped(evt);
            }
        });

        btnCancelar.setBackground(new java.awt.Color(204, 204, 204));
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancelar_1.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnGuardar.setBackground(new java.awt.Color(204, 204, 204));
        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/salvar.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });

        tbIdCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane5.setViewportView(tbIdCliente);

        jScrolCliente.setViewportView(jScrollPane5);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPostal, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(txtCorreo)
                                .addGap(120, 120, 120))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(97, 97, 97)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(83, 83, 83)
                                .addComponent(jLabel15))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(txtCiudadT, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(txtMunicipioT, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(txtCalleT, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtColoniaT, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel21)
                                    .addComponent(txtNumT, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtPostalT, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtEstadoT, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel23)))
                                    .addComponent(jScrolCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(46, 46, 46)
                                        .addComponent(jLabel10)
                                        .addGap(71, 71, 71)
                                        .addComponent(jLabel11))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(txtNombreJ, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(btnGuardar)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnCancelar))
                                            .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addComponent(txtApellidoJ, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(10, 10, 10)
                                                .addComponent(txtTelefonoT, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel48))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtDocumentos, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(290, 290, 290)
                                        .addComponent(jLabel3))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(11, 11, 11)
                                        .addComponent(jLabel8))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtFec_Nac, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel12))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(txtCalle, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtColonia)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(97, 97, 97)
                                .addComponent(jLabel5)
                                .addGap(451, 451, 451)))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(txtNum, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))))
                .addGap(287, 287, 287))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3))
                    .addComponent(jLabel8))
                .addGap(6, 6, 6)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFec_Nac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(jLabel6)
                                .addComponent(jLabel13)
                                .addComponent(jLabel7)))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtColonia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCiudad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addGap(6, 6, 6)
                                .addComponent(txtPostal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(6, 6, 6)
                        .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jLabel14)
                .addGap(11, 11, 11)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel18)
                        .addComponent(jLabel21))
                    .addComponent(jLabel15))
                .addGap(6, 6, 6)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCalleT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtColoniaT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNumT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtCiudadT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMunicipioT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11))
                                .addGap(6, 6, 6))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel49)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombreJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtApellidoJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtTelefonoT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtPuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtDocumentos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel48)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addComponent(jLabel22)
                            .addGap(6, 6, 6)
                            .addComponent(txtPostalT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addComponent(jLabel23)
                            .addGap(6, 6, 6)
                            .addComponent(txtEstadoT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(22, 22, 22)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnGuardar)
                        .addComponent(btnCancelar))
                    .addComponent(jScrolCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(71, 71, 71))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 980, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabGeneral.addTab("Registro Cliente", jPanel4);

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));

        lblComp.setBackground(new java.awt.Color(204, 255, 255));
        lblComp.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblComp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCompMouseClicked(evt);
            }
        });

        btnCancelar1.setBackground(new java.awt.Color(204, 204, 204));
        btnCancelar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancelar_1.png"))); // NOI18N
        btnCancelar1.setText("Cancelar");
        btnCancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar1ActionPerformed(evt);
            }
        });

        lblIne.setBackground(new java.awt.Color(204, 255, 255));
        lblIne.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblIne.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblIneMouseClicked(evt);
            }
        });

        jLabel43.setText("INE");

        lblAct.setBackground(new java.awt.Color(204, 255, 255));
        lblAct.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblAct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblActMouseClicked(evt);
            }
        });

        jLabel44.setText("Acta  de  Nacimiento:");

        jLabel45.setText("Comprobante de Domicilio:");

        txtIne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIneActionPerformed(evt);
            }
        });
        txtIne.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIneKeyTyped(evt);
            }
        });

        btnGuardar1.setBackground(new java.awt.Color(204, 204, 204));
        btnGuardar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/salvar.png"))); // NOI18N
        btnGuardar1.setText("Guardar");
        btnGuardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar1ActionPerformed(evt);
            }
        });

        txtId2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtId2ActionPerformed(evt);
            }
        });

        tbIdDocumentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tbIdDocumentos);

        jScrolDocumentos.setViewportView(jScrollPane2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(txtIne, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(txtActa, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtCom, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(lblIne, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addComponent(jLabel43)))
                        .addGap(71, 71, 71)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel44)
                                .addGap(34, 34, 34)
                                .addComponent(jLabel45))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblAct, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(80, 80, 80)
                                .addComponent(lblComp, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(82, 82, 82)
                                .addComponent(txtId2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(btnGuardar1)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar1)
                        .addGap(62, 62, 62)
                        .addComponent(jScrolDocumentos, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(318, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel43)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel45)
                        .addComponent(jLabel44)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblAct, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblIne, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblComp, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtId2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtActa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCancelar1)
                        .addComponent(btnGuardar1))
                    .addComponent(jScrolDocumentos, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        tabGeneral.addTab("Registro Documentos", jPanel2);

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

    private void txtId2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtId2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtId2ActionPerformed

    private void btnGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar1ActionPerformed
        if(txtIne.getText().length()==0){
            JOptionPane.showMessageDialog(this, "Debes insertar una Ine, es obligatorio.","Sistema",JOptionPane.WARNING_MESSAGE);
            txtIne.requestFocus();
            return;
        }
        if(txtActa.getText().length()==0){
            JOptionPane.showMessageDialog(this, "Debes insertar una Acta, es obligatorio.","Sistema",JOptionPane.WARNING_MESSAGE);
            txtActa.requestFocus();
            return;
        }
        if(txtCom.getText().length()==0){
            JOptionPane.showMessageDialog(this, "Debes insertar un Comprobante de Domicilio, es obligatorio.","Sistema",JOptionPane.WARNING_MESSAGE);
            txtCom.requestFocus();
            return;
        }
        String resp3;
        //String resp3;
        if(this.accion1.equals("editar")){
            //Editar
            resp3=this.CONTROLDOC.Actualizar(Integer.parseInt(txtId2.getText()),txtIne.getText(), this.ine_Ant, txtActa.getText(), txtCom.getText());
            if(resp3.equals("OK")){
                this.mensajeOk("Actualizado Correctamente");
                this.limpiar();
                this.listar1("",false);
                tabGeneral.setSelectedIndex(1);
                tabGeneral.setEnabledAt(0, true);
                tabGeneral.setEnabledAt(2, false);
                tabGeneral.setEnabledAt(3, false);
                tabGeneral.setEnabledAt(1, true);
            }else{
                this.mensajeError(resp3);
            }
        }else{
            try {
                //Guardar
                resp3=this.CONTROLDOC.Insertar(txtIne.getText(),txtActa.getText(), txtCom.getText());
                //resp3=this.CONTROLDOC.Insertar(Byte.parseByte(txtIne.getText()),Byte.parseByte(txtActa.getText()),Byte.parseByte(txtCom.getText()));
                if(resp3.equals("OK") /*|| resp3.equals("OK") */){
                    this.subirImagenes();
                    this.mensajeOk("Registrado Correctamente");
                    this.limpiar();
                    this.listar1("",false);
                    tabGeneral.setSelectedIndex(2);
                    tabGeneral.setEnabledAt(0, false);
                    tabGeneral.setEnabledAt(1, false);
                    tabGeneral.setEnabledAt(3, false);
                    tabGeneral.setEnabledAt(2, true);
                }else{
                    this.mensajeError(resp3);
                }
            } catch (IOException ex) {
                Logger.getLogger(Cliente_1.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "No se a Capturado los Documentos Correctamente","Sistema",JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnGuardar1ActionPerformed

    private void lblActMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblActMouseClicked
        JFileChooser file = new JFileChooser();
        int estado=file.showOpenDialog(this);
        if (estado==JFileChooser.APPROVE_OPTION){
            this.imagen=file.getSelectedFile().getName();
            txtActa.setText(String.valueOf(file.getSelectedFile().getAbsolutePath()));
            this.rutaOrigen=file.getSelectedFile().getAbsolutePath();
            this.rutaDestino=this.DIRECTORIO + this.imagen;

            ImageIcon im=new ImageIcon(this.rutaOrigen);
            Icon icono1=new ImageIcon(im.getImage().getScaledInstance(lblAct.getWidth(),lblAct.getHeight(),lblAct.getWidth()));
            lblAct.setIcon(icono1);
            lblAct.repaint();
        }
    }//GEN-LAST:event_lblActMouseClicked

    private void lblIneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblIneMouseClicked
        JFileChooser archivo = new JFileChooser();
        int estado=archivo.showOpenDialog(this);
        if (estado==JFileChooser.APPROVE_OPTION)
        {
            File file = archivo.getSelectedFile();
            txtIne.setText(String.valueOf(file));
            Image foto=getToolkit().getImage(txtIne.getText());
            foto = foto.getScaledInstance(lblIne.getWidth(),lblIne.getHeight(),Image.SCALE_DEFAULT);
            lblIne.setIcon(new ImageIcon(foto));
            lblIne.repaint();
        }
    }//GEN-LAST:event_lblIneMouseClicked

    private void btnCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar1ActionPerformed
        tabGeneral.setEnabledAt(1, true);
        tabGeneral.setEnabledAt(0, true);
        tabGeneral.setEnabledAt(2, false);
        tabGeneral.setEnabledAt(3, false);
        tabGeneral.setSelectedIndex(1);
        this.limpiar();
    }//GEN-LAST:event_btnCancelar1ActionPerformed

    private void lblCompMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCompMouseClicked
        JFileChooser file1 = new JFileChooser();
        int estado=file1.showOpenDialog(this);
        if (estado==JFileChooser.APPROVE_OPTION){
            this.imagen=file1.getSelectedFile().getName();
            txtCom.setText(String.valueOf(file1.getSelectedFile().getAbsolutePath()));
            this.rutaOrigen=file1.getSelectedFile().getAbsolutePath();
            this.rutaDestino=this.DIRECTORIO + this.imagen;

            ImageIcon im=new ImageIcon(this.rutaOrigen);
            Icon icono2=new ImageIcon(im.getImage().getScaledInstance(lblComp.getWidth(),lblComp.getHeight(),lblComp.getWidth()));
            lblComp.setIcon(icono2);
            lblComp.repaint();
        }
    }//GEN-LAST:event_lblCompMouseClicked

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if(txtNombre.getText().length()==0 || txtNombre.getText().length()>70){
            JOptionPane.showMessageDialog(this, "Debes insertar el nombre del cliente y no debe ser mayor a 70 caracteres, es obligatorio.","Sistema",JOptionPane.WARNING_MESSAGE);
            txtNombre.requestFocus();
            return;
        }
        if(txtApellido.getText().length()==0 || txtApellido.getText().length()>70){
            JOptionPane.showMessageDialog(this, "Debes insertar el apellido y no debe ser mayor a 70 caracteres, es obligatorio.","Sistema",JOptionPane.WARNING_MESSAGE);
            txtApellido.requestFocus();
            return;
        }
        if(txtFec_Nac.getText().length()==0){
            JOptionPane.showMessageDialog(this, "Debes insertar una fecha, es obligatorio.","Sistema",JOptionPane.WARNING_MESSAGE);
            txtFec_Nac.requestFocus();
            return;
        }
        if(txtCalle.getText().length()==0 || txtCalle.getText().length()>50){
            JOptionPane.showMessageDialog(this, "Debes insertar una calle y no debe ser mayor a 50 caracteres, es obligatorio.","Sistema",JOptionPane.WARNING_MESSAGE);
            txtCalle.requestFocus();
            return;
        }
        if(txtNum.getText().length()==0 ||txtNum.getText().length()>5){
            JOptionPane.showMessageDialog(this, "Debes insertar un Num. Exterior y no debe ser mayor a 5 caracteres, es obligatorio.","Sistema",JOptionPane.WARNING_MESSAGE);
            txtNum.requestFocus();
            return;
        }
        if(txtColonia.getText().length()==0 ||txtColonia.getText().length()>60){
            JOptionPane.showMessageDialog(this, "Debes insertar una Colonia y no debe ser mayor a 60 caracteres, es obligatorio.","Sistema",JOptionPane.WARNING_MESSAGE);
            txtColonia.requestFocus();
            return;
        }
        if(txtCiudad.getText().length()==0 ||txtCiudad.getText().length()>40){
            JOptionPane.showMessageDialog(this, "Debes insertar una Ciudad y no debe ser mayor a 40 caracteres, es obligatorio.","Sistema",JOptionPane.WARNING_MESSAGE);
            txtCiudad.requestFocus();
            return;
        }

        if(txtPostal.getText().length()<4 ||txtPostal.getText().length()>5){
            JOptionPane.showMessageDialog(this, "Debes insertar un Cod. Postal y no debe ser mayor a 5 caracteres, es obligatorio.","Sistema",JOptionPane.WARNING_MESSAGE);
            txtPostal.requestFocus();
            return;
        }

        if(txtMunicipio.getText().length()==0 ||txtMunicipio.getText().length()>30){
            JOptionPane.showMessageDialog(this, "Debes insertar un Municipio y no debe ser mayor a 30 caracteres, es obligatorio.","Sistema",JOptionPane.WARNING_MESSAGE);
            txtMunicipio.requestFocus();
            return;
        }

        if(txtEstado.getText().length()==0 ||txtEstado.getText().length()>30){
            JOptionPane.showMessageDialog(this, "Debes insertar un Estado y no debe ser mayor a 30 caracteres, es obligatorio.","Sistema",JOptionPane.WARNING_MESSAGE);
            txtEstado.requestFocus();
            return;
        }
        if(txtTelefono.getText().length()<9 ||txtTelefono.getText().length()>10){
            JOptionPane.showMessageDialog(this, "Debes insertar un Telefono y no debe ser mayor a 10 caracteres, es obligatorio.","Sistema",JOptionPane.WARNING_MESSAGE);
            txtTelefono.requestFocus();
            return;
        }
        if(txtCalleT.getText().length()==0 || txtCalleT.getText().length()>50){
            JOptionPane.showMessageDialog(this, "Debes insertar la calle del trabajo y no debe ser mayor a 50 caracteres, es obligatorio.","Sistema",JOptionPane.WARNING_MESSAGE);
            txtCalleT.requestFocus();
            return;
        }
        if(txtNumT.getText().length()==0 ||txtNumT.getText().length()>5){
            JOptionPane.showMessageDialog(this, "Debes insertar el Num. Exterior del trabajo y no debe ser mayor a 5 caracteres, es obligatorio.","Sistema",JOptionPane.WARNING_MESSAGE);
            txtNumT.requestFocus();
            return;
        }
        if(txtColoniaT.getText().length()==0 ||txtColoniaT.getText().length()>60){
            JOptionPane.showMessageDialog(this, "Debes insertar la Colonia del trabajo y no debe ser mayor a 60 caracteres, es obligatorio.","Sistema",JOptionPane.WARNING_MESSAGE);
            txtColoniaT.requestFocus();
            return;
        }
        if(txtCiudadT.getText().length()==0 ||txtCiudadT.getText().length()>40){
            JOptionPane.showMessageDialog(this, "Debes insertar la Ciudad del trabajo y no debe ser mayor a 40 caracteres, es obligatorio.","Sistema",JOptionPane.WARNING_MESSAGE);
            txtCiudadT.requestFocus();
            return;
        }

        if(txtPostalT.getText().length()<4 ||txtPostalT.getText().length()>5){
            JOptionPane.showMessageDialog(this, "Debes insertar el Cod. Postal del trabajo y no debe ser mayor a 5 caracteres, es obligatorio.","Sistema",JOptionPane.WARNING_MESSAGE);
            txtPostalT.requestFocus();
            return;
        }

        if(txtMunicipioT.getText().length()==0 ||txtMunicipioT.getText().length()>30){
            JOptionPane.showMessageDialog(this, "Debes insertar el Municipio del trabajo y no debe ser mayor a 30 caracteres, es obligatorio.","Sistema",JOptionPane.WARNING_MESSAGE);
            txtMunicipioT.requestFocus();
            return;
        }

        if(txtEstadoT.getText().length()==0 ||txtEstadoT.getText().length()>30){
            JOptionPane.showMessageDialog(this, "Debes insertar el Estado del trabajo y no debe ser mayor a 30 caracteres, es obligatorio.","Sistema",JOptionPane.WARNING_MESSAGE);
            txtEstadoT.requestFocus();
            return;
        }
        if(txtCorreo.getText().length()==0 ||txtCorreo.getText().length()>70){
            JOptionPane.showMessageDialog(this, "Debes insertar un Correo y no debe ser mayor a 70 caracteres, es obligatorio.","Sistema",JOptionPane.WARNING_MESSAGE);
            txtCorreo.requestFocus();
            return;
        }
        if(txtTelefonoT.getText().length()<9 ||txtTelefonoT.getText().length()>10){
            JOptionPane.showMessageDialog(this, "Debes insertar el Telefono del jefe y no debe ser mayor a 10 caracteres, es obligatorio.","Sistema",JOptionPane.WARNING_MESSAGE);
            txtTelefonoT.requestFocus();
            return;
        }
        if(txtPuesto.getText().length()==0 ||txtPuesto.getText().length()>50){
            JOptionPane.showMessageDialog(this, "Debes insertar el Puesto de trabajo y no debe ser mayor a 50 caracteres, es obligatorio.","Sistema",JOptionPane.WARNING_MESSAGE);
            txtPuesto.requestFocus();
            return;
        }
        if(txtDocumentos.getText().length()==0){
            JOptionPane.showMessageDialog(this, "Debes insertar el ID Documentos, es obligatorio.","Sistema",JOptionPane.WARNING_MESSAGE);
            txtDocumentos.requestFocus();
            return;
        }
        String resp;
        if(this.accion.equals("editar")){
            //Editar
            jScrolCliente.setVisible(false);
            resp=this.CONTROLCLT.Actualizar(Integer.parseInt(txtId.getText()),txtNombre.getText(), this.Nombre_Ant, txtApellido.getText(), txtFec_Nac.getText(), txtCalle.getText(), txtNum.getText(), txtColonia.getText(), txtCiudad.getText(), txtPostal.getText(),txtMunicipio.getText(), txtEstado.getText(), txtTelefono.getText(), txtCalleT.getText(), txtNumT.getText(), txtColoniaT.getText(), txtCiudadT.getText(), txtPostalT.getText(), txtMunicipioT.getText(), txtEstadoT.getText(), txtNombreJ.getText(), txtApellidoJ.getText(), txtTelefonoT.getText(), txtPuesto.getText(), txtCorreo.getText(), Integer.parseInt(txtDocumentos.getText()));
            if(resp.equals("OK")){
                this.mensajeOk("Actualizado Correctamente");
                this.limpiar();
                this.listar("",false);
                tabGeneral.setSelectedIndex(0);
                tabGeneral.setEnabledAt(1, true);
                tabGeneral.setEnabledAt(0, true);
                tabGeneral.setEnabledAt(2, false);
                tabGeneral.setEnabledAt(3, false);
            }else{
                this.mensajeError(resp);
                tabGeneral.setSelectedIndex(0);
                tabGeneral.setEnabledAt(1, true);
                tabGeneral.setEnabledAt(0, true);
                tabGeneral.setEnabledAt(2, false);
                tabGeneral.setEnabledAt(3, false);
            }
        }else{
            //Guardar
            resp=this.CONTROLCLT.Insertar(txtNombre.getText(), txtApellido.getText(), txtFec_Nac.getText(), txtCalle.getText(), txtNum.getText(), txtColonia.getText(), txtCiudad.getText(), txtPostal.getText(),txtMunicipio.getText(), txtEstado.getText(), txtTelefono.getText(), txtCalleT.getText(), txtNumT.getText(), txtColoniaT.getText(), txtCiudadT.getText(), txtPostalT.getText(), txtMunicipioT.getText(), txtEstadoT.getText(), txtNombreJ.getText(), txtApellidoJ.getText(), txtTelefonoT.getText(), txtPuesto.getText(), txtCorreo.getText(), Integer.parseInt(txtDocumentos.getText()));
            //resp3=this.CONTROLDOC.Insertar(Byte.parseByte(txtIne.getText()),Byte.parseByte(txtActa.getText()),Byte.parseByte(txtCom.getText()));
            if(resp.equals("OK")){
                this.mensajeOk("Registrado Correctamente");
                this.limpiar();
                this.listar("",false);
                tabGeneral.setSelectedIndex(0);
                tabGeneral.setEnabledAt(1, true);
                tabGeneral.setEnabledAt(0, true);
                tabGeneral.setEnabledAt(2, false);
                tabGeneral.setEnabledAt(3, false);
            }else{
                this.mensajeError(resp);
                tabGeneral.setSelectedIndex(0);
                tabGeneral.setEnabledAt(1, true);
                tabGeneral.setEnabledAt(0, true);
                tabGeneral.setEnabledAt(2, false);
                tabGeneral.setEnabledAt(3, false);
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        tabGeneral.setEnabledAt(0, true);
        tabGeneral.setEnabledAt(1, true);
        tabGeneral.setEnabledAt(2, false);
        tabGeneral.setEnabledAt(3, false);
        tabGeneral.setSelectedIndex(0);
        this.limpiar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtDocumentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDocumentosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDocumentosActionPerformed

    private void txtNumTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumTActionPerformed
        
    }//GEN-LAST:event_txtNumTActionPerformed

    private void txtApellidoJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidoJActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidoJActionPerformed

    private void txtCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoActionPerformed

    private void btnEditar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditar1ActionPerformed
        if(tablaListado1.getSelectedRowCount()==1){
            String id= String.valueOf(tablaListado1.getValueAt(tablaListado1.getSelectedRow(), 0));
            String ine = String.valueOf(tablaListado1.getValueAt(tablaListado1.getSelectedRow(), 1));
            this.ine_Ant = String.valueOf(tablaListado.getValueAt(tablaListado1.getSelectedRow(), 1));
            String acta_nac = String.valueOf(tablaListado1.getValueAt(tablaListado1.getSelectedRow(), 2));
            String com = String.valueOf(tablaListado1.getValueAt(tablaListado1.getSelectedRow(), 3));

            txtId2.setText(id);
            txtIne.setText(ine);
            txtActa.setText(acta_nac);
            txtCom.setText(com);

            txtId2.setVisible(true);
            txtId2.setEditable(false);
            jScrolDocumentos.setVisible(false);

            tabGeneral.setEnabledAt(3, true);
            tabGeneral.setEnabledAt(0, false);
            tabGeneral.setEnabledAt(1, false);
            tabGeneral.setEnabledAt(2, false);
            tabGeneral.setSelectedIndex(3);
            this.accion1="editar";
            btnGuardar1.setText("Editar");
        }else{
            this.mensajeError("Selecciona 1 registro a editar.");
        }
    }//GEN-LAST:event_btnEditar1ActionPerformed

    private void btnBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar1ActionPerformed
        this.listar1(txtBuscar1.getText(),false);
    }//GEN-LAST:event_btnBuscar1ActionPerformed

    private void btnDesactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesactivarActionPerformed
        if (tablaListado.getSelectedRowCount() == 1) {
            String id= String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(),0));
            String nombre= String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(),1));

            if(JOptionPane.showConfirmDialog(this,"Deseas desactivar el registro: " + nombre + " ?", "Desactivar", JOptionPane.YES_NO_OPTION)==0){
                String resp=this.CONTROLCLT.Desactivar(Integer.parseInt(id));
                if (resp.equals("OK")){
                    this.mensajeOk("Registro desactivado");
                    this.listar("",false);
                }else{
                    this.mensajeError(resp);
                }
            }
        } else {
            this.mensajeError("Seleccione 1 registro a desactivar.");
        }
    }//GEN-LAST:event_btnDesactivarActionPerformed

    private void btnActivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActivarActionPerformed
        if (tablaListado.getSelectedRowCount() == 1) {
            String id= String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(),0));
            String nombre= String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(),1));

            if(JOptionPane.showConfirmDialog(this,"Deseas activar el registro: " + nombre + " ?", "Activar", JOptionPane.YES_NO_OPTION)==0){
                String resp=this.CONTROLCLT.Activar(Integer.parseInt(id));
                if (resp.equals("OK")){
                    this.mensajeOk("Registro activado");
                    this.listar("",false);
                }else{
                    this.mensajeError(resp);
                }
            }
        } else {
            this.mensajeError("Seleccione 1 registro a activar.");
        }
    }//GEN-LAST:event_btnActivarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        if(tablaListado.getSelectedRowCount()==1){
            String id= String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 0));
            String nombre_cliente = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 1));
            this.Nombre_Ant = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 1));
            String apellido_cliente = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 2));

            String fecha_nac = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 3));
            String calle = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 5));
            String Num_Ext = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 6));
            String Colonia = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 7));
            String Ciudad = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 8));
            String CodPostal = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 9));
            String Municipio = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 10));
            String Estado = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 11));
            String Telefono = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 12));
            String calle_tra = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 13));
            String Num_Ext_tra = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 14));
            String Colonia_tra = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 15));
            String Ciudad_tra = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 16));
            String CodPostal_tra = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 17));
            String Municipio_tra = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 18));
            String Estado_tra = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 19));
            String Nombre_Jefe = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 20));
            String Apellido_Jefe = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 21));
            String Telefono_Jefe = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 22));
            String Puesto = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 23));
            String Correo_Elect = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 24));
            String documentos_id = String.valueOf(tablaListado.getValueAt(tablaListado.getSelectedRow(), 25));

            txtId.setText(id);
            txtNombre.setText(nombre_cliente);
            txtApellido.setText(apellido_cliente);
            txtFec_Nac.setText(fecha_nac);
            txtCalle.setText(calle);
            txtNum.setText(Num_Ext);
            txtColonia.setText(Colonia);
            txtCiudad.setText(Ciudad);
            txtPostal.setText(CodPostal);
            txtMunicipio.setText(Municipio);
            txtEstado.setText(Estado);
            txtTelefono.setText(Telefono);
            txtCalleT.setText(calle_tra);
            txtNumT.setText(Num_Ext_tra);
            txtColoniaT.setText(Colonia_tra);
            txtCiudadT.setText(Ciudad_tra);
            txtPostalT.setText(CodPostal_tra);
            txtMunicipioT.setText(Municipio_tra);
            txtEstadoT.setText(Estado_tra);
            txtNombreJ.setText(Nombre_Jefe);
            txtApellidoJ.setText(Apellido_Jefe);
            txtTelefonoT.setText(Telefono_Jefe);
            txtPuesto.setText(Puesto);
            txtCorreo.setText(Correo_Elect);
            txtDocumentos.setText(documentos_id);

            txtId.setVisible(true);
            txtId.setEditable(false);
            jScrolCliente.setVisible(false);
            
            tabGeneral.setEnabledAt(2, true);
            tabGeneral.setEnabledAt(0, false);
            tabGeneral.setEnabledAt(1, false);
            tabGeneral.setEnabledAt(3, false);
            tabGeneral.setSelectedIndex(2);
            this.accion="editar";
            btnGuardar.setText("Editar");
        }else{
            this.mensajeError("Selecciona 1 registro a editar.");
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        tabGeneral.setEnabledAt(3, true);
        tabGeneral.setEnabledAt(1, false);
        tabGeneral.setEnabledAt(0, false);
        tabGeneral.setEnabledAt(2, false);
        tabGeneral.setSelectedIndex(3);
        this.accion="guardar";
        btnGuardar.setText("Guardar");
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        this.listar(txtBuscar.getText(),false);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void cboNumPaginaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNumPaginaActionPerformed
        if (this.primeraCarga==false){
            this.listar("",true);
        }
    }//GEN-LAST:event_cboNumPaginaActionPerformed

    private void cboTotalPorPaginaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTotalPorPaginaActionPerformed
        this.paginar();
    }//GEN-LAST:event_cboTotalPorPaginaActionPerformed

    private void cboTotalPorPagina1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTotalPorPagina1ActionPerformed
        this.paginar1();
    }//GEN-LAST:event_cboTotalPorPagina1ActionPerformed

    private void cboNumPagina1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNumPagina1ActionPerformed
        if (this.primeraCarga1==false){
            this.listar1("",true);
        }
    }//GEN-LAST:event_cboNumPagina1ActionPerformed

    private void txtNumKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_txtNumKeyTyped

    private void txtIneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIneActionPerformed

    private void txtIneKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIneKeyTyped
        
    }//GEN-LAST:event_txtIneKeyTyped

    private void txtNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyTyped
        int key = evt.getKeyChar();

        boolean mayusculas = key >= 65 && key <= 90;
        boolean minusculas = key >= 97 && key <= 122;
        boolean espacio = key == 32;

        if (!(minusculas || mayusculas || espacio)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreKeyTyped

    private void txtApellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtApellidoKeyTyped
        
    }//GEN-LAST:event_txtApellidoKeyTyped

    private void txtPostalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPostalKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_txtPostalKeyTyped

    private void txtEstadoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEstadoKeyTyped
        int key = evt.getKeyChar();

        boolean mayusculas = key >= 65 && key <= 90;
        boolean minusculas = key >= 97 && key <= 122;
        boolean espacio = key == 32;

        if (!(minusculas || mayusculas || espacio)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtEstadoKeyTyped

    private void txtTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTelefonoKeyTyped
        int key = evt.getKeyChar();

        boolean numeros = key >= 48 && key <= 57;

        if (!numeros) {
            evt.consume();
        }
    }//GEN-LAST:event_txtTelefonoKeyTyped

    private void txtNumTKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumTKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_txtNumTKeyTyped

    private void txtPostalTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPostalTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPostalTActionPerformed

    private void txtPostalTKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPostalTKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_txtPostalTKeyTyped

    private void txtEstadoTKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEstadoTKeyTyped
        int key = evt.getKeyChar();

        boolean mayusculas = key >= 65 && key <= 90;
        boolean minusculas = key >= 97 && key <= 122;
        boolean espacio = key == 32;

        if (!(minusculas || mayusculas || espacio)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtEstadoTKeyTyped

    private void txtNombreJKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreJKeyTyped
    int key = evt.getKeyChar();

        boolean mayusculas = key >= 65 && key <= 90;
        boolean minusculas = key >= 97 && key <= 122;
        boolean espacio = key == 32;

        if (!(minusculas || mayusculas || espacio)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreJKeyTyped

    private void txtPuestoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPuestoKeyTyped
        int key = evt.getKeyChar();

        boolean mayusculas = key >= 65 && key <= 90;
        boolean minusculas = key >= 97 && key <= 122;
        boolean espacio = key == 32;

        if (!(minusculas || mayusculas || espacio)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtPuestoKeyTyped

    private void txtDocumentosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDocumentosKeyTyped
        char c = evt.getKeyChar();
        if(c<'0' || c>'9')evt.consume();
    }//GEN-LAST:event_txtDocumentosKeyTyped

    private void txtBuscar1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscar1KeyTyped
        int key = evt.getKeyChar();

        boolean numeros = key >= 48 && key <= 57;

        if (!numeros) {
            evt.consume();
        }
    }//GEN-LAST:event_txtBuscar1KeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActivar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscar1;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCancelar1;
    private javax.swing.JButton btnDesactivar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEditar1;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardar1;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JComboBox<String> cboNumPagina;
    private javax.swing.JComboBox<String> cboNumPagina1;
    private javax.swing.JComboBox<String> cboTotalPorPagina;
    private javax.swing.JComboBox<String> cboTotalPorPagina1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrolCliente;
    private javax.swing.JScrollPane jScrolDocumentos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel lblAct;
    private javax.swing.JLabel lblComp;
    private javax.swing.JLabel lblIne;
    private javax.swing.JLabel lblTotalRegistros;
    private javax.swing.JLabel lblTotalRegistros1;
    private javax.swing.JTabbedPane tabGeneral;
    private javax.swing.JTable tablaListado;
    private javax.swing.JTable tablaListado1;
    private javax.swing.JTable tbIdCliente;
    private javax.swing.JTable tbIdDocumentos;
    private javax.swing.JTextField txtActa;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtApellidoJ;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtBuscar1;
    private javax.swing.JTextField txtCalle;
    private javax.swing.JTextField txtCalleT;
    private javax.swing.JTextField txtCiudad;
    private javax.swing.JTextField txtCiudadT;
    private javax.swing.JTextField txtColonia;
    private javax.swing.JTextField txtColoniaT;
    private javax.swing.JTextField txtCom;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDocumentos;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtEstadoT;
    private javax.swing.JTextField txtFec_Nac;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtId2;
    private javax.swing.JTextField txtIne;
    private javax.swing.JTextField txtMunicipio;
    private javax.swing.JTextField txtMunicipioT;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtNombreJ;
    private javax.swing.JTextField txtNum;
    private javax.swing.JTextField txtNumT;
    private javax.swing.JTextField txtPostal;
    private javax.swing.JTextField txtPostalT;
    private javax.swing.JTextField txtPuesto;
    private javax.swing.JTextField txtTelefono;
    private javax.swing.JTextField txtTelefonoT;
    // End of variables declaration//GEN-END:variables
}
