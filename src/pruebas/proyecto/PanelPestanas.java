/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pruebas.proyecto;

import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Edge;
import org.graphstream.graph.EdgeRejectedException;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.ViewerPipe;
import org.graphstream.ui.swing_viewer.SwingViewer;


/**
 *
 * @author dugla
 */
public class PanelPestanas extends javax.swing.JFrame {
    
    private JSONObject jsonObject;
    private String data;
    private InterfazPrueba interfacita;
    private int nodosDelGrafo;
    private Grafo grafitot;
    public int t = 3;
    
    

    /**
     * Creates new form PanelPestanas
     */
    public PanelPestanas(InterfazPrueba interfacita) {
        this.interfacita = interfacita;       
        initComponents();
        
        setLocationRelativeTo(null);
        AreaMostrarCubiertas.setEditable(false);
        JComponent editor = SpinnerEdit.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
                ((JSpinner.DefaultEditor) editor).getTextField().setEditable(false);
        }
        
    }

    private PanelPestanas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    
    public void SetData(String data){
        this.data = data;
        Grafo grafo = CargadorGrafo.cargarGrafoDesdeJson(data);
        grafo.printGrafo(); // Mostrar en consola
        
        CajitaParadasGestion.removeAllItems(); // Limpiar el JComboBox
        grafo.forEachEstacion(nombre -> CajitaParadasGestion.addItem(nombre)); // Agregar cada nombre al JComboBox
        
        SeleccionParadas1.removeAllItems();
        grafo.forEachEstacion(nombre -> SeleccionParadas1.addItem(nombre));
        
        SeleccionParadas2.removeAllItems();
        grafo.forEachEstacion(nombre -> SeleccionParadas2.addItem(nombre));
        
        CajitaParadasBusqueda.removeAllItems();
        grafo.forEachEstacion(nombre -> CajitaParadasBusqueda.addItem(nombre));
        
        SwingUtilities.invokeLater(() -> dibujarGrafo(grafo));
    }
    public void GrafoInterfaz(Grafo grafitot){
        this.grafitot = grafitot;
    }
    
    private void configurarSpinner(Grafo grafo) {
        
        if (grafo.getNumeroNodos() <= 0){
            SpinnerEdit.setModel(new SpinnerNumberModel(1, 1, 10, 1));
        }else if(grafitot.getNumeroNodos() > 0){
            SpinnerEdit.setModel(new SpinnerNumberModel(1, 1, grafo.getNumeroNodos(), 1));
        }
        

        
        // Configura el modelo del JSpinner con un rango de 1 al numero maximo de nodos del grafo

        // Deshabilitar la edición directa
        JComponent editor = SpinnerEdit.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            ((JSpinner.DefaultEditor) editor).getTextField().setEditable(false);
        }
    }
    

    
    public void dibujarGrafo(Grafo grafitot) {
    Graph graph = new SingleGraph("Grafo");

    // Agregar nodos
    NodoGrafo actual = grafitot.getpFirst();
    while (actual != null) {
        String nombreEstacion = actual.getEstacion().getNombreEstacion().replace(":", "").replace(" ", "_");
        graph.addNode(nombreEstacion).setAttribute("ui.label", actual.getEstacion().getNombreEstacion());
        actual = actual.getpNext();
    }

    // Agregar aristas
    actual = grafitot.getpFirst();
    while (actual != null) {
        String nombreEstacion = actual.getEstacion().getNombreEstacion().replace(":", "").replace(" ", "_");
        NodoListaAdyacencia adyacente = actual.getListaAdyacencia().getHead();
        while (adyacente != null) {
            String nombreAdyacente = adyacente.getEstacion().getNombreEstacion().replace(":", "").replace(" ", "_");
            String edgeId = nombreEstacion + "-" + nombreAdyacente;
            if (graph.getEdge(edgeId) == null) {
                try {
                    graph.addEdge(edgeId, nombreEstacion, nombreAdyacente, true);
                } catch (EdgeRejectedException e) {
                    System.out.println("Edge rejected: " + edgeId);
                }
            }
            adyacente = adyacente.getpNext();
        }
        actual = actual.getpNext();
    }

    // Estilo del grafo
    graph.setAttribute("ui.stylesheet", "node { fill-color: red; size: 20px; }"); // Ajusta el tamaño aquí
    graph.setAttribute("ui.quality");
    graph.setAttribute("ui.antialias");

    // Crear el GraphViewer
    SwingViewer viewer = new SwingViewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
    viewer.enableAutoLayout();
    viewer.addDefaultView(false);
    
    JPanel viewerPanel = (JPanel) viewer.getDefaultView();
    MostrareAcaPanelGrafo.setLayout(new BorderLayout());
    MostrareAcaPanelGrafo.removeAll();
    MostrareAcaPanelGrafo.add(viewerPanel, BorderLayout.CENTER);
    MostrareAcaPanelGrafo.revalidate();
    MostrareAcaPanelGrafo.repaint();
}
    
    
    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        CajitaParadasGestion = new javax.swing.JComboBox<>();
        AgregarSucursalB = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        SeleccionParadas1 = new javax.swing.JComboBox<>();
        SeleccionParadas2 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        AgregarLineasBB = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        MostrareAcaPanelGrafo = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ActualizarBotonGrafo = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        CajitaParadasBusqueda = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        AreaMostrarCubiertas = new javax.swing.JTextArea();
        BotonBFS = new javax.swing.JButton();
        BotonDFS = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        SpinnerEdit = new javax.swing.JSpinner();
        jLabel11 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        ValorT = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel5.setText("Agregar Sucursal en Parada: ");

        CajitaParadasGestion.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        CajitaParadasGestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CajitaParadasGestionActionPerformed(evt);
            }
        });

        AgregarSucursalB.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        AgregarSucursalB.setText("Agregar Sucursal");
        AgregarSucursalB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarSucursalBActionPerformed(evt);
            }
        });

        jLabel10.setText("listo");

        jLabel13.setText("Esta listo aca, solo falta agregar la logica para que se entienda en el grafo");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(CajitaParadasGestion, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(AgregarSucursalB)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel13))
                .addContainerGap(1186, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(167, 167, 167)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(CajitaParadasGestion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AgregarSucursalB)
                    .addComponent(jLabel10))
                .addGap(26, 26, 26)
                .addComponent(jLabel13)
                .addContainerGap(612, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Gestion", jPanel2);

        SeleccionParadas1.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        SeleccionParadas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SeleccionParadas1ActionPerformed(evt);
            }
        });

        SeleccionParadas2.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel6.setText("Selecciona Parada 1:");

        jLabel7.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel7.setText("Selecciona Parada 2:");

        AgregarLineasBB.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        AgregarLineasBB.setText("Agregar Lineas");
        AgregarLineasBB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarLineasBBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(SeleccionParadas1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(81, 81, 81)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(SeleccionParadas2, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(293, 293, 293)
                        .addComponent(AgregarLineasBB, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(1309, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(SeleccionParadas2)
                    .addComponent(SeleccionParadas1, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE))
                .addGap(53, 53, 53)
                .addComponent(AgregarLineasBB, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(599, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Conectar Lineas", jPanel3);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MostrareAcaPanelGrafo.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout MostrareAcaPanelGrafoLayout = new javax.swing.GroupLayout(MostrareAcaPanelGrafo);
        MostrareAcaPanelGrafo.setLayout(MostrareAcaPanelGrafoLayout);
        MostrareAcaPanelGrafoLayout.setHorizontalGroup(
            MostrareAcaPanelGrafoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 810, Short.MAX_VALUE)
        );
        MostrareAcaPanelGrafoLayout.setVerticalGroup(
            MostrareAcaPanelGrafoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 540, Short.MAX_VALUE)
        );

        jPanel1.add(MostrareAcaPanelGrafo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 20, 810, 540));

        jLabel1.setText("Amarillo: Cubierta");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        jLabel2.setText("Rojo: No Cubierta");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 698, -1));

        jLabel3.setText("Verde: Sucursal");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 697, -1));

        ActualizarBotonGrafo.setText("Actualizar");
        ActualizarBotonGrafo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActualizarBotonGrafoActionPerformed(evt);
            }
        });
        jPanel1.add(ActualizarBotonGrafo, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, -1, 20));

        jTabbedPane1.addTab("Grafo", jPanel1);

        AreaMostrarCubiertas.setColumns(20);
        AreaMostrarCubiertas.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        AreaMostrarCubiertas.setRows(5);
        jScrollPane1.setViewportView(AreaMostrarCubiertas);

        BotonBFS.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        BotonBFS.setText("Buscar (Metodo BFS)");
        BotonBFS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonBFSActionPerformed(evt);
            }
        });

        BotonDFS.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        BotonDFS.setText("Buscar (Metodo DFS)");
        BotonDFS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonDFSActionPerformed(evt);
            }
        });

        jLabel8.setText("Ten en cuenta que debes agregar la sucursal antes y seleccionar esa misma sucursal para verificar cuales estaciones estan cubiertas");

        jLabel9.setText("Estaciones que pueden ser alcanazadas");

        SpinnerEdit.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Calibri", 0, 18)); // NOI18N
        jLabel11.setText("Selecciona las t a cubrir: ");

        jLabel12.setText("esto no funciona aun");

        ValorT.setText("Aceptar valor de t");
        ValorT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ValorTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SpinnerEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(ValorT))
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 725, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(CajitaParadasBusqueda, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(BotonDFS)
                                    .addGap(35, 35, 35)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(BotonBFS))))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(93, 93, 93)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 1037, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(SpinnerEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ValorT))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addGap(26, 26, 26)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(CajitaParadasBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BotonDFS, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotonBFS, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12)
                        .addGap(53, 53, 53)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addContainerGap(307, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cobertura Sucursal", jPanel4);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CajitaParadasGestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CajitaParadasGestionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CajitaParadasGestionActionPerformed

    private void SeleccionParadas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SeleccionParadas1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SeleccionParadas1ActionPerformed

    private void ActualizarBotonGrafoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActualizarBotonGrafoActionPerformed
        // TODO add your handling code here:
        FuncionesUtiles.marcarCubiertas(grafitot, t);
        grafitot.printGrafo();
        SwingUtilities.invokeLater(() -> dibujarGrafo(grafitot));
        
    }//GEN-LAST:event_ActualizarBotonGrafoActionPerformed

    private void AgregarSucursalBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarSucursalBActionPerformed
        // TODO add your handling code here:
        NodoGrafo estacionEncontrada = grafitot.buscarPorNombreEstacion(CajitaParadasGestion.getSelectedItem().toString());
        FuncionesUtiles.agregarSucursal(estacionEncontrada);
        
        
        grafitot.printGrafo();
        JOptionPane.showMessageDialog(rootPane, "Se agrego la sucursal con éxito, las paradas cubiertas por default es a 3 alcanzadas.");
        
        
        
    }//GEN-LAST:event_AgregarSucursalBActionPerformed

    private void AgregarLineasBBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarLineasBBActionPerformed
        // TODO add your handling code here:
        NodoGrafo estacion1 = grafitot.buscarPorNombreEstacion(SeleccionParadas1.getSelectedItem().toString());
        NodoGrafo estacion2 = grafitot.buscarPorNombreEstacion(SeleccionParadas2.getSelectedItem().toString());
        if(estacion1 == estacion2){
            JOptionPane.showMessageDialog(rootPane, "Error, las estaciones son iguales");
        }else{
        FuncionesUtiles.enlazarNodos(estacion1, estacion2);
        grafitot.printGrafo();
        JOptionPane.showMessageDialog(rootPane, "Se han enlazado con éxito");
        }  
    }//GEN-LAST:event_AgregarLineasBBActionPerformed

    private void BotonDFSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonDFSActionPerformed
        // TODO add your handling code here:
        NodoGrafo estaciondfs = grafitot.buscarPorNombreEstacion(CajitaParadasBusqueda.getSelectedItem().toString());
        t = (Integer) SpinnerEdit.getValue();
        ListaSimple ListaEnRango = FuncionesUtiles.obtenerNodosEnRangoDFS(grafitot, estaciondfs, t);
        
        System.out.println(ListaEnRango.ListaMostrada());
        AreaMostrarCubiertas.setText(ListaEnRango.ListaMostrada());
        JOptionPane.showMessageDialog(rootPane, "agregado al cuadro");
        FuncionesUtiles.marcarCubiertas(grafitot, t);
        grafitot.printGrafo();
        
        
        
        
    }//GEN-LAST:event_BotonDFSActionPerformed

    private void BotonBFSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonBFSActionPerformed
        // TODO add your handling code here:
        NodoGrafo estacionbfs = grafitot.buscarPorNombreEstacion(CajitaParadasBusqueda.getSelectedItem().toString());
        Integer estacionestbfs = (Integer) SpinnerEdit.getValue();
        //ListaSimple ListaEnRango = FuncionesUtiles.BFS(grafitot, estacionbfs, estacionestbfs);
        //System.out.println(ListaEnRango.ListaMostrada());
        //AreaMostrarCubiertas.setText(ListaEnRango.ListaMostrada());
        //JOptionPane.showMessageDialog(rootPane, "agregado al cuadro");
    }//GEN-LAST:event_BotonBFSActionPerformed

    private void ValorTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ValorTActionPerformed
        // TODO add your handling code here:
        t = (Integer) SpinnerEdit.getValue();
        if (t <= 0){
            t = 3;
            JOptionPane.showMessageDialog(rootPane, "Valor de t no valido, estableciendo t = 3 nuevamente");
        }else if (t >= grafitot.getNumeroNodos()){
            t = 3;
            JOptionPane.showMessageDialog(rootPane, "Valor de t no valido, estableciento t = 3 nuevamente");
        }else{
            JOptionPane.showMessageDialog(rootPane, "valor de t actualizado con éxito.");
        }
    }//GEN-LAST:event_ValorTActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PanelPestanas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PanelPestanas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PanelPestanas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PanelPestanas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PanelPestanas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ActualizarBotonGrafo;
    private javax.swing.JButton AgregarLineasBB;
    private javax.swing.JButton AgregarSucursalB;
    private javax.swing.JTextArea AreaMostrarCubiertas;
    private javax.swing.JButton BotonBFS;
    private javax.swing.JButton BotonDFS;
    private javax.swing.JComboBox<String> CajitaParadasBusqueda;
    private javax.swing.JComboBox<String> CajitaParadasGestion;
    private javax.swing.JPanel MostrareAcaPanelGrafo;
    private javax.swing.JComboBox<String> SeleccionParadas1;
    private javax.swing.JComboBox<String> SeleccionParadas2;
    private javax.swing.JSpinner SpinnerEdit;
    private javax.swing.JButton ValorT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
