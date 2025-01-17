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
 * Clase que representa el panel de pestañas en la interfaz gráfica.
 * @author Anthony Caldera
 */
public class PanelPestanas extends javax.swing.JFrame {
    
    private JSONObject jsonObject;
    private String data;
    private InterfazPrueba interfacita;
    private int nodosDelGrafo;
    private Grafo grafitot;
    public int t = 0;
    
    

    /**
     * Metodo que al inicializar la interfaz, hara las primeras configuraciones y guardara algun que otro dato
     * author: Anthony Caldera
     * @param interfacita para obtener los datos de la intefaz anterior
     */
    public PanelPestanas(InterfazPrueba interfacita) {
        this.interfacita = interfacita;// constructor para trabajar datos del panel anterior
        initComponents();
        setLocationRelativeTo(null); //coloca la interfaz en el medio de la pantalla
        AreaMostrarCubiertas.setEditable(false); //un text area que no se puede escribir/borrar texto
        JComponent editor = SpinnerEdit.getEditor(); //parte para crear un spinner
        if (editor instanceof JSpinner.DefaultEditor) {
                ((JSpinner.DefaultEditor) editor).getTextField().setEditable(false); // evitamos que se escriba algo en el spinner
        }
        
    }
    /**
     * Metodo que genero netbeans al crear el jframe
     * @author Anthony Caldera
     */
    private PanelPestanas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    /**
     * Constructor para recibir el json de forma de string del panel anterior y metodos aplicables que ayudan a llenar la interfaz por defaul correspondiente
     * @author Anthony Caldera
     * @param data  para pasar el string que obtenimos del json de la ventana anterior y guardarla
     */
    public void SetData(String data){
        this.data = data;
        Grafo grafo = CargadorGrafo.cargarGrafoDesdeJson(data); //cargamos el grafo
        grafo.printGrafo(); // Mostrar en consola (esto era para probar igualmente lo dejamos)
        
        CajitaParadasGestion.removeAllItems(); // Limpiar el JComboBox si tiene algun elemento o algo por el estilo
        grafo.forEachEstacion(nombre -> CajitaParadasGestion.addItem(nombre)); // Agregar cada estacion al JCombobox
        
        SeleccionParadas1.removeAllItems(); // Lo mismo para este caso, elimina algun elemento que contenga
        grafo.forEachEstacion(nombre -> SeleccionParadas1.addItem(nombre)); //Agrega cada estacion al jcombobox
        
        SeleccionParadas2.removeAllItems(); // lo mismo aca debido que son varias cajitas
        grafo.forEachEstacion(nombre -> SeleccionParadas2.addItem(nombre)); // igualmente para otra cajita
        
        CajitaParadasBusqueda.removeAllItems();// la ultima cajita para eliminar elementos adentro
        grafo.forEachEstacion(nombre -> CajitaParadasBusqueda.addItem(nombre)); //agregamos elementos en esta ultima cajita igualmente
        
        SwingUtilities.invokeLater(() -> actualizarGrafo(grafo)); //este metodo nos va permitir mostrar el grafo en un panel con el metodo de actualizargrafo
    }
    
    /**
     * Constructor que nos perminte guardar el grafo correspondiente en alguna variable globar de tipo Grafo y trabajarlo con él en el futuro
     * @author Anthony Caldera
     * @param grafitot para guarda un grafo en una variable
     */
    public void GrafoInterfaz(Grafo grafitot){
        this.grafitot = grafitot;
    }
    
    /**
     * Metodo que nos permite dibujar y mostrar el grafo gracias a las importaciones de las librerias de graph stream para mostrarlo por la interfaz en un panel
     * @author Anthony Caldera
     * @param grafitot para que nos vaya modificando el grafo que guardamos para trabajar globlamente en este archivo
     */
    public void actualizarGrafo(Grafo grafitot) {
    Graph graph = new SingleGraph("Grafo Actualizado"); //parte para crear el grafo en graph stream

    // Agregar nodos con colores específicos
    NodoGrafo actual = grafitot.getpFirst(); //recorriendo el grafo correspondientemente
    while (actual != null) {
        String nombreEstacion = actual.getEstacion().getNombreEstacion().replace(":", "").replace(" ", "_"); //por si tiene algun espacio o _ seprados
        Node nodo = graph.addNode(nombreEstacion); // agregamos ese nodo al grafo que dibujaremos
        nodo.setAttribute("ui.label", actual.getEstacion().getNombreEstacion()); //le ponemos para mostrar el nombre que llevada ese nodo del grafo del grapshtream

        // Establecer color del nodo según condiciones
        if (actual.getEstacion().isSucursal()) {
            nodo.setAttribute("ui.style", "fill-color: green; size: 20px;");
        } else if (actual.getEstacion().isCubierta() && !actual.getEstacion().isSucursal()) {
            nodo.setAttribute("ui.style", "fill-color: yellow; size: 20px;");
        } else {
            nodo.setAttribute("ui.style", "fill-color: red; size: 20px;");
        }

        // Aumentar el tamaño de la fuente
        nodo.setAttribute("ui.label.size", "16");

        actual = actual.getpNext();
    }

    // Agregar aristas para el graphstream
    actual = grafitot.getpFirst();// volvemos a recorrer el grafo para poder agregar las aristas
    while (actual != null) {
        String nombreEstacion = actual.getEstacion().getNombreEstacion().replace(":", "").replace(" ", "_");
        NodoListaAdyacencia adyacente = actual.getListaAdyacencia().getHead();
        while (adyacente != null) {
            String nombreAdyacente = adyacente.getEstacion().getNombreEstacion().replace(":", "").replace(" ", "_");
            String edgeId = nombreEstacion + "-" + nombreAdyacente;
            if (graph.getEdge(edgeId) == null) {
                try {
                    graph.addEdge(edgeId, nombreEstacion, nombreAdyacente, true).setAttribute("ui.style", "size: 1px;"); // Ajustar tamaño de las flechas
                } catch (EdgeRejectedException e) {
                    System.out.println("Edge rejected: " + edgeId);
                }
            }
            adyacente = adyacente.getpNext();
        }
        actual = actual.getpNext();
    }

    // Estilo del grafo
    
    graph.setAttribute("ui.layout", "fruchterman");
    graph.setAttribute("ui.repulsion", 3000);
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
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        CajitaParadasBusqueda = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        AreaMostrarCubiertas = new javax.swing.JTextArea();
        BotonBFS = new javax.swing.JButton();
        BotonDFS = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        SpinnerEdit = new javax.swing.JSpinner();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        ValorT = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        CajitaParadasGestion = new javax.swing.JComboBox<>();
        AgregarSucursalB = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        RegresarVentana = new javax.swing.JButton();
        EliminarSucursalB = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setForeground(new java.awt.Color(255, 153, 0));
        jTabbedPane1.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N

        jPanel3.setBackground(new java.awt.Color(255, 153, 51));

        SeleccionParadas1.setFont(new java.awt.Font("Bauhaus 93", 0, 18)); // NOI18N
        SeleccionParadas1.setForeground(new java.awt.Color(255, 153, 51));
        SeleccionParadas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SeleccionParadas1ActionPerformed(evt);
            }
        });

        SeleccionParadas2.setFont(new java.awt.Font("Bauhaus 93", 0, 18)); // NOI18N
        SeleccionParadas2.setForeground(new java.awt.Color(255, 153, 0));

        jLabel6.setBackground(new java.awt.Color(255, 153, 51));
        jLabel6.setFont(new java.awt.Font("Bauhaus 93", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Selecciona Parada 1:");

        jLabel7.setFont(new java.awt.Font("Bauhaus 93", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Selecciona Parada 2:");

        AgregarLineasBB.setFont(new java.awt.Font("Bauhaus 93", 0, 18)); // NOI18N
        AgregarLineasBB.setText("Conectar Paradas");
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
                .addGap(110, 110, 110)
                .addComponent(SeleccionParadas1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 249, Short.MAX_VALUE)
                .addComponent(SeleccionParadas2, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(150, 150, 150))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(142, 142, 142)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(205, 205, 205))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(397, 397, 397)
                .addComponent(AgregarLineasBB, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(198, 198, 198)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SeleccionParadas1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SeleccionParadas2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(109, 109, 109)
                .addComponent(AgregarLineasBB, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(193, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Conectar Lineas", jPanel3);

        jPanel1.setBackground(new java.awt.Color(255, 153, 0));
        jPanel1.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MostrareAcaPanelGrafo.setBackground(new java.awt.Color(255, 255, 255));
        MostrareAcaPanelGrafo.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 0, 0)));

        javax.swing.GroupLayout MostrareAcaPanelGrafoLayout = new javax.swing.GroupLayout(MostrareAcaPanelGrafo);
        MostrareAcaPanelGrafo.setLayout(MostrareAcaPanelGrafoLayout);
        MostrareAcaPanelGrafoLayout.setHorizontalGroup(
            MostrareAcaPanelGrafoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        MostrareAcaPanelGrafoLayout.setVerticalGroup(
            MostrareAcaPanelGrafoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 518, Short.MAX_VALUE)
        );

        jPanel1.add(MostrareAcaPanelGrafo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 790, 520));

        jLabel1.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Amarillo: Cubierta");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        jLabel2.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Rojo: No Cubierta");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 698, -1));

        jLabel3.setFont(new java.awt.Font("Bauhaus 93", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("- Si todos los nodos dejan de tener color rojo, significa que ya tienes toda la ciudad cubierta!");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 600, 800, 50));

        ActualizarBotonGrafo.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N
        ActualizarBotonGrafo.setText("Actualizar");
        ActualizarBotonGrafo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActualizarBotonGrafoActionPerformed(evt);
            }
        });
        jPanel1.add(ActualizarBotonGrafo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 100, 30));

        jLabel14.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Verde: Sucursal");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 697, -1));

        jLabel15.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 620, 697, -1));

        jLabel16.setFont(new java.awt.Font("Bauhaus 93", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Grafo:");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 697, -1));

        jTabbedPane1.addTab("Grafo", jPanel1);

        jPanel4.setBackground(new java.awt.Color(255, 153, 0));

        CajitaParadasBusqueda.setFont(new java.awt.Font("Bauhaus 93", 0, 18)); // NOI18N
        CajitaParadasBusqueda.setForeground(new java.awt.Color(255, 153, 51));

        AreaMostrarCubiertas.setColumns(20);
        AreaMostrarCubiertas.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N
        AreaMostrarCubiertas.setForeground(new java.awt.Color(255, 153, 51));
        AreaMostrarCubiertas.setRows(5);
        jScrollPane1.setViewportView(AreaMostrarCubiertas);

        BotonBFS.setFont(new java.awt.Font("Bauhaus 93", 0, 18)); // NOI18N
        BotonBFS.setText("Buscar (Metodo BFS)");
        BotonBFS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonBFSActionPerformed(evt);
            }
        });

        BotonDFS.setFont(new java.awt.Font("Bauhaus 93", 0, 18)); // NOI18N
        BotonDFS.setText("Buscar (Metodo DFS)");
        BotonDFS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonDFSActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Bauhaus 93", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Estaciones que pueden ser alcanazadas");

        SpinnerEdit.setFont(new java.awt.Font("Bauhaus 93", 0, 18)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Bauhaus 93", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Seleccione la estacion para ver su alcance");

        ValorT.setFont(new java.awt.Font("Bauhaus 93", 0, 18)); // NOI18N
        ValorT.setText("Actualizar valor de t");
        ValorT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ValorTActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Bauhaus 93", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Selecciona las t paradas a cubrir: ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(SpinnerEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(ValorT))
                    .addComponent(jLabel11)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(CajitaParadasBusqueda, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                            .addComponent(BotonDFS)
                            .addGap(35, 35, 35)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(BotonBFS)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(73, 73, 73))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SpinnerEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ValorT)
                    .addComponent(jLabel13))
                .addGap(32, 32, 32)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(CajitaParadasBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BotonBFS, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotonDFS, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Alcance de estaciones", jPanel4);

        jPanel2.setBackground(new java.awt.Color(255, 153, 51));

        jLabel5.setFont(new java.awt.Font("Bauhaus 93", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Colocar Sucursales");

        CajitaParadasGestion.setFont(new java.awt.Font("Bauhaus 93", 0, 18)); // NOI18N
        CajitaParadasGestion.setForeground(new java.awt.Color(255, 153, 51));
        CajitaParadasGestion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CajitaParadasGestionActionPerformed(evt);
            }
        });

        AgregarSucursalB.setFont(new java.awt.Font("Bauhaus 93", 0, 18)); // NOI18N
        AgregarSucursalB.setText("Agregar Sucursal");
        AgregarSucursalB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarSucursalBActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Bauhaus 93", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Agregar Sucursal en Parada: ");

        jLabel18.setFont(new java.awt.Font("Bauhaus 93", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));

        RegresarVentana.setFont(new java.awt.Font("Bauhaus 93", 0, 18)); // NOI18N
        RegresarVentana.setText("Cargar Nuevo Archivo Json");
        RegresarVentana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegresarVentanaActionPerformed(evt);
            }
        });

        EliminarSucursalB.setFont(new java.awt.Font("Bauhaus 93", 0, 18)); // NOI18N
        EliminarSucursalB.setText("Eliminar Sucursal");
        EliminarSucursalB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarSucursalBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(95, 95, 95)
                            .addComponent(jLabel17)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(CajitaParadasGestion, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(AgregarSucursalB))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(88, 88, 88)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel18)
                                .addComponent(RegresarVentana)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(EliminarSucursalB)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(416, 416, 416)
                        .addComponent(jLabel5)))
                .addContainerGap(221, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(AgregarSucursalB, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 64, Short.MAX_VALUE)
                        .addComponent(EliminarSucursalB, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CajitaParadasGestion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addGap(69, 69, 69)))
                .addComponent(jLabel18)
                .addGap(313, 313, 313)
                .addComponent(RegresarVentana)
                .addGap(80, 80, 80))
        );

        jTabbedPane1.addTab("Gestion", jPanel2);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 5, 1020, 680));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CajitaParadasGestionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CajitaParadasGestionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CajitaParadasGestionActionPerformed

    private void SeleccionParadas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SeleccionParadas1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SeleccionParadas1ActionPerformed

    
    /**
     * Metodo que actualizara el grafo cada vez que lo presiones y lo muestre por pantalla en la interfaz
     * @author Anthony Caldera
     * @param evt evento de boton
     */
    private void ActualizarBotonGrafoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActualizarBotonGrafoActionPerformed
        // TODO add your handling code here:
        //Funciones que van a modificar el grafo mostrado por pantalla al darle al boton de actualizar
        FuncionesUtiles.desmarcarCubiertas(grafitot); //desmarcara las cubiertas
        FuncionesUtiles.marcarCubiertas(grafitot, t); // marcara las cubiertas
        grafitot.printGrafo(); //imprimimos por consola (esto era de prueba pero lo dejamos de igual forma)
        
        SwingUtilities.invokeLater(() -> actualizarGrafo(grafitot)); // dibujamos el grafo correspondientemente
        
    }//GEN-LAST:event_ActualizarBotonGrafoActionPerformed

    /**
     * Metodo para agregar una sucursal en tu ciudad
     * @author Anthony Caldera
     * @param evt evento de boton
     */
    private void AgregarSucursalBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarSucursalBActionPerformed
        // TODO add your handling code here:
        //Boton que agregara una sucursal dependiendo de la estacion escogida en la cajita de estaciones
        NodoGrafo estacionEncontrada = grafitot.buscarPorNombreEstacion(CajitaParadasGestion.getSelectedItem().toString()); //lo que obtengo de la cajita de las estaciones
        FuncionesUtiles.agregarSucursal(estacionEncontrada); //agregamos la sucursal
        
        //la t tambien es una variable globar, verificamos cada caso para cuando t sea igual a un numero correspondiente
        if (t == 0){
            t = 3;
            JOptionPane.showMessageDialog(rootPane, "Por Default, el valor de t = 3. Sucursal agregada con éxito.");
        }else if (t >= grafitot.getNumeroNodos()){
            t = 3;
            JOptionPane.showMessageDialog(rootPane, "Sucursal agregada con un valor de t = 3. el valor de t es muy grade, verifiquelo y pruebe con uno mas pequeño");
        }else{
            JOptionPane.showMessageDialog(rootPane, "Sucursal con valor de t = " + t + " agregada con éxito.");
        }
    
        
        
        grafitot.printGrafo(); //mostramos por consola (esto era para probar pero lo dejamos de igual forma)
        
        
        
    }//GEN-LAST:event_AgregarSucursalBActionPerformed

    /**
     * Metodo para enlazar lineas entre 2 estaciones
     * @author Anthony Caldera
     * @param evt evento de boton
     */
    private void AgregarLineasBBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarLineasBBActionPerformed
        // TODO add your handling code here:
        //este metodo sera para enlazar dos paradas correspondientes
        NodoGrafo estacion1 = grafitot.buscarPorNombreEstacion(SeleccionParadas1.getSelectedItem().toString()); // una estacion de una cajita
        NodoGrafo estacion2 = grafitot.buscarPorNombreEstacion(SeleccionParadas2.getSelectedItem().toString()); // otra estacion de otra cajita
        if(estacion1 == estacion2){
            JOptionPane.showMessageDialog(rootPane, "Error, las estaciones son iguales");
        }else{
        FuncionesUtiles.enlazarNodos(estacion1, estacion2); //enlazamientos
        grafitot.printGrafo(); //probando por consola si funciona correctamente
        JOptionPane.showMessageDialog(rootPane, "Se han enlazado con éxito");
        }  
    }//GEN-LAST:event_AgregarLineasBBActionPerformed

    /**
     * Metodo para revisar cuales estaciones estaran cubiertas dependiendo del valor de t para una sucursal que colocaras por metodo DFS
     * @author Anthony Caldera
     * @param evt evento de boton
     */
    private void BotonDFSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonDFSActionPerformed
        // TODO add your handling code here:
        NodoGrafo estaciondfs = grafitot.buscarPorNombreEstacion(CajitaParadasBusqueda.getSelectedItem().toString()); //buscamos la estacion
        ListaSimple ListaEnRango = FuncionesUtiles.obtenerNodosEnRangoDFS(grafitot, estaciondfs, t); //creamos una lista con las estaciones alcanzadas y estaran cubiertas correspondientemente
        System.out.println(ListaEnRango.ListaMostrada()); //pruebas en consola, no pasa nada aca
        AreaMostrarCubiertas.setText(ListaEnRango.ListaMostrada()); //muestra en el text area todas las estaciones alcanzadas para cubrir
        JOptionPane.showMessageDialog(rootPane, "agregado al cuadro");
        grafitot.printGrafo(); //por consola de prueba
        
        
        
        
    }//GEN-LAST:event_BotonDFSActionPerformed
    /**
     * Metodo para revisar cuales estaciones estaran cubiertas dependiendo del valor de t para una sucursal que colocaras por metodo BFS
     * @author Anthony Caldera
     * @param evt evento de boton
     */
    private void BotonBFSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonBFSActionPerformed
        // TODO add your handling code here:
        //repetimos el mismo caso para el dfs solo que aca aplicamos con bfs
        NodoGrafo estacionbfs = grafitot.buscarPorNombreEstacion(CajitaParadasBusqueda.getSelectedItem().toString());
        ListaSimple ListaEnRango = FuncionesUtiles.obtenerNodosEnRangoBFS(grafitot, estacionbfs, t);
        System.out.println(ListaEnRango.ListaMostrada());
        AreaMostrarCubiertas.setText(ListaEnRango.ListaMostrada());
        JOptionPane.showMessageDialog(rootPane, "agregado al cuadro");
    }//GEN-LAST:event_BotonBFSActionPerformed

 
    /**
     * Metodo para actualizar el valor de t cada vez que quieras, dependiendo de lo que coloques en el spinner y cumpla ciertas condiciones, te dara un valor en especifico
     * @author Anthony Caldera
     * @param evt evento de boton
     */
    private void ValorTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ValorTActionPerformed
        // TODO add your handling code here:
        //con esto, dependiendo de lo que coloques en el spinner, asignara un valor de t siguiendo unas series de condiciones
        t = (Integer) SpinnerEdit.getValue();
        if (t <= 0){
            t = 3;
            JOptionPane.showMessageDialog(rootPane, "Valor de t no valido, estableciendo t = 3 nuevamente. Verifique el valor de t que no sea menor o igual a 0");
        }else if (t >= grafitot.getNumeroNodos()){
            t = 3;
            JOptionPane.showMessageDialog(rootPane, "Valor de t no valido, estableciento t = 3 nuevamente. Verifique el valor de t no sea mayor o igual a " + grafitot.getNumeroNodos());
        }else{
            JOptionPane.showMessageDialog(rootPane, "valor de t = " + t + " actualizado con éxito.");
        }
    }//GEN-LAST:event_ValorTActionPerformed


    /**
     * Metodo para regresar a la ventana anterior
     * @author Anthony Caldera
     * @param evt evento de boton
     */
    private void RegresarVentanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegresarVentanaActionPerformed
        // TODO add your handling code here:
        //regresamos a la ventana anterior
        InterfazPrueba interfaanterior = new InterfazPrueba();
        interfaanterior.setVisible(true); //con esto abrimos la interfaz anterior
        this.setVisible(false); // cerramos la actual
    }//GEN-LAST:event_RegresarVentanaActionPerformed

    /**
     * Metodo para eliminar una sucursal escogida
     * @author Anthony Caldera
     * @param evt evento de boton
     */
    private void EliminarSucursalBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarSucursalBActionPerformed
        // TODO add your handling code here:
        //Con esto podemos eliminar una sucursal correspondientemente
        NodoGrafo estacionEncontrada = grafitot.buscarPorNombreEstacion(CajitaParadasGestion.getSelectedItem().toString());
        FuncionesUtiles.eliminarSucursal(estacionEncontrada);
        JOptionPane.showMessageDialog(rootPane, estacionEncontrada.estacion.nombreEstacion + " Eliminada con éxito");
    }//GEN-LAST:event_EliminarSucursalBActionPerformed

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
    private javax.swing.JButton EliminarSucursalB;
    private javax.swing.JPanel MostrareAcaPanelGrafo;
    private javax.swing.JButton RegresarVentana;
    private javax.swing.JComboBox<String> SeleccionParadas1;
    private javax.swing.JComboBox<String> SeleccionParadas2;
    private javax.swing.JSpinner SpinnerEdit;
    private javax.swing.JButton ValorT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
