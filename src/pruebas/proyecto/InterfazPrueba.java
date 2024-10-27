/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package pruebas.proyecto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Clase que representa la interfaz gráfica de la aplicación.
 * @author Anthony Caldera
 */
public class InterfazPrueba extends javax.swing.JFrame {
    

    /**
     * Inicializacion de la interfaz, para colocar algunos detalles que tendra la interfaz
     * @author Anthony Caldera
     */
    public InterfazPrueba() {
        initComponents();
        setLocationRelativeTo(null);// centrar mi interfaz
        inputArea.setEditable(false); // no puedo editar lo que hay dentro de mi text area
        Boton1.setEnabled(false); // no puedo darle al boton hasta que haya un metodo que me lo pueda acceder
    }
    
    /**
     * Con este metodo, validamos que el archivo seleccionado con jfilechooser por el usuario, tenga el mismo formato de lo que esta en el proyecto, siguiendo la misma logica de nombre parada, nombre estacion, etc etc
     * @author Anthony Caldera
     * @param jsonString le pasamos el parametro json que fue previamente transformado a string al obtener el texto
     * @return verdadero si sigue el mismo formato que nos indica el proyecto o falso en el caso contrario
     */
    
    private static boolean validarFormatoJson(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(new JSONTokener(jsonString)); //transformamos de string a archivo json
            for (String key : jsonObject.keySet()) { //Recorre todas las claves del objeto JSON. Se asume que cada clave representa un conjunto de líneas de metro o en el caso del Transmilenio
                JSONArray metro = jsonObject.getJSONArray(key); //Obtiene un arreglo JSON asociado a la clave actual (que representa las lineas del metro o el caso del transmilenio)
                for (int i = 0; i < metro.length(); i++) { //iteras sobre cada linea del metro o caso del transmilenio
                    JSONObject line = metro.getJSONObject(i); //Obtiene el objeto JSON que representa la línea de metro o caso del transmilenio.
                    String lineName = line.keys().next(); // Obtiene el nombre de la línea (se asume que hay una única clave por línea). o caso del transmilenio
                    JSONArray stops = line.getJSONArray(lineName); //Obtiene el arreglo de paradas para esa línea o caso del transmilenio
                    for (int j = 0; j < stops.length(); j++) { //Itera sobre cada parada en el arreglo de paradas
                        Object stop = stops.get(j); //Obtiene el objeto correspondiente a la parada
                        if (stop instanceof JSONObject) { // Si stop es un JSONObject
                            JSONObject stopObj = (JSONObject) stop;
                            if (stopObj.keys().hasNext()) { //Se valida que tenga al menos una clave.
                                String from = stopObj.keys().next();
                                String to = stopObj.getString(from); //Se obtiene la clave (que representa el nombre de la parada) y su valor (que representa la conexión)
                                if (from.isEmpty() || to.isEmpty()) {// Se verifica que tanto el nombre como la conexión no estén vacíos.
                                    return false;
                                }
                            } else {
                                return false;
                            }
                        } else if (!(stop instanceof String)) {
                            // Asegurarse de que sea una cadena si no es un objeto JSON
                            return false;
                        }
                    }
                }
            }
            return true; // Si todas las validaciones se pasan, el método retorna true, indicando que el formato del JSON es válido según las reglas definidas
        } catch (Exception e) { // Si ocurre cualquier excepción durante el proceso (como un error de formato JSON), se captura y se retorna false.
            return false;
        }
    }
    
    /**
     * Este metodo para extraer y retornar la extensión de un archivo dado. Si el archivo no tiene una extensión (es decir, no contiene un punto en su nombre), el método devuelve una cadena vacía
     * @author Anthony Caldera
     * @param file que seleccionaremos previamente del jfilchooser
     * @return el string de una extension de un archivo dado y si no hay extension, devuelve un string vacio
     */
  
    private static String getFileExtension(File file) {
        String name = file.getName();
        int lastIndex = name.lastIndexOf('.');
        if (lastIndex == -1) {
            return "";
        }
        return name.substring(lastIndex + 1);
    }
    


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        inputArea = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        Boton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        inputArea.setColumns(20);
        inputArea.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N
        inputArea.setRows(5);
        jScrollPane1.setViewportView(inputArea);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(82, 135, 676, 176));

        jButton1.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N
        jButton1.setText("Cargar Archivo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(82, 349, -1, -1));

        Boton1.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N
        Boton1.setText("Siguiente");
        Boton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Boton1ActionPerformed(evt);
            }
        });
        getContentPane().add(Boton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(669, 349, -1, -1));

        jLabel1.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Bienvenidos al Selector de Ubicacion de Sucursal de tu Ciudad");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(97, 33, -1, 61));

        jPanel1.setBackground(new java.awt.Color(255, 153, 51));
        jPanel1.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel2.setFont(new java.awt.Font("Gill Sans Ultra Bold", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("El Cuadro en blanco es para leer tus archivos. No es editable ni nada por el estilo");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addComponent(jLabel2)
                .addContainerGap(139, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(jLabel2)
                .addContainerGap(323, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 840, 450));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        
        //crea la variable para poder seleccionar mi archivo json
        JFileChooser fileChooser = new JFileChooser();

        // Define el filtro de archivos, o sea que desplega unas opciones que me acepten solo archivos de tipo jSon aunque eso puede cambiarse
        fileChooser.setFileFilter(new FileFilter() {
            @Override
            /**
             * Este metodo nos indica que nos devuelve un booleano si el archivo seleccionado es json o no
             * @author Anthony Caldera
             * @return true o false
             */
            public boolean accept(File file) {
                if (file.isDirectory()) {
                    return true;
                }
                String extension = getFileExtension(file);
                return extension.equals("json");
            }

            @Override
            
            /**
             * Este metodo nos ayudara con el filtro para conseguir mas facil uun tipo de archivo json por filtrado, pero sera la descripcion. No es este metodo para eso en especifico
             * @author Anthony Caldera
             * @return String de como sera nombrado el filtro
             */
            public String getDescription() {
                return "Archivos JSON (*.json)";
            }
        });
        
        
        // esto nos ayudara seleccionar el archivo con el jfilechooser
        int result = fileChooser.showOpenDialog(rootPane);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String extension = getFileExtension(selectedFile);
            //validamos si el archivo seleccionado es de tipo json
            if (!extension.equals("json")) {
                JOptionPane.showMessageDialog(rootPane,
                        "Archivo no válido. Por favor, selecciona un archivo JSON.",
                        "Error de tipo de archivo",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                //ahora procedemos a leer ese archivo json correspondiente
                try (BufferedReader reader = new BufferedReader(new FileReader(selectedFile))) {
                    StringBuilder jsonContent = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        jsonContent.append(line).append("\n"); //vamos escribiendo linea por linea nuestro archivo json en string
                    }
                    JOptionPane.showMessageDialog(rootPane, "Archivo seleccionado correctamente");
                    Boton1.setEnabled(true); // ya podemos usar el boton para acceder a la siguiente accion
                    inputArea.setText(jsonContent.toString()); // escribimos nuestro json leido correctamente previamente transformado a string en el texto de area para validar el formato posteriormente
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(rootPane, //caso si ocurre un error inesperado
                            "Error al leer el archivo: " + e.getMessage(),
                            "Error de lectura",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void Boton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Boton1ActionPerformed
        // TODO add your handling code here:
        //creamos un string para obtener el archivo previamente escrito en el text area
        String traertexto = inputArea.getText(); //obtenemos el texto del texto de area
        boolean Esvalido = validarFormatoJson(traertexto); // verificamos si sigue el formato que nos indica el proyecto de como tiene que ser el archivo json
        if (Esvalido) {
                JOptionPane.showMessageDialog(rootPane, "Formato válido");
                CargadorGrafo grafito = new CargadorGrafo(this); // creamos un tipo de dato CargadorGrafo para construir nuestro grafo a partir de lo obtenido en el text area
                grafito.enviardatito(traertexto);// enviamos nuestro archivo para que se construya en la clase CargadorGrafo y lo enviamos
                
                PanelPestanas pestanitas = new PanelPestanas(this); //creamos un tipo de dato PanelPestanas para abrir este jframe
                pestanitas.SetData(traertexto); // tambien enviamos nuestro texto obtenido del texto de area para trabajarlo en la otra ventana
                
                Grafo grafo = CargadorGrafo.cargarGrafoDesdeJson(traertexto); //creamos una variable de tipo Grafo y le pasamos el metodo correspondiente para crear nuestro grafo
                pestanitas.GrafoInterfaz(grafo); //enviamos de igual forma el grafo creado para trabajarlo en la otra ventana
                
                pestanitas.setVisible(true); // abrimos la nueva ventana
                this.setVisible(false); //cerramos esta ventana
                
                
            } else {
                JOptionPane.showMessageDialog(rootPane, "Formato inválido. Revisa el contenido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            }
        
    }//GEN-LAST:event_Boton1ActionPerformed

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
            java.util.logging.Logger.getLogger(InterfazPrueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfazPrueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfazPrueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfazPrueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfazPrueba().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Boton1;
    private javax.swing.JTextArea inputArea;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
