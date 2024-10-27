 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebas.proyecto;

import java.util.function.Consumer;

/**
 * Clase que representa un grafo.
 * @author Francisco Fustero
 */
public class Grafo {
    
  private NodoGrafo pFirst;
  private int numeroNodos;

  /**
   * Constructor de la clase Grafo
   * @author Francisco Fustero
   * @param pFirst para el constructor
   */
  
    public Grafo(NodoGrafo pFirst) {
        this.pFirst = pFirst;
        this.numeroNodos=0;
    }

    /**
     * obtenemos el apuntador al primer elemento del grafo
     * @author Francisco Fustero
     * @return pFirst que es el apuntador a nodografo
     */
    public NodoGrafo getpFirst() {
        return pFirst;
    }

    /**
     * Colocamos quien sera nuestro primer apuntador a un nodo del grafo
     * @author Francisco Fustero
     * @param pFirst le pasamos pFirst
     */
    public void setpFirst(NodoGrafo pFirst) {
        this.pFirst = pFirst;
    }
    
    /**
     * Agregamos un nuevo nodo al grafo, esta es una de sus primitivas
     * @author Fransico Fustero
     * @param nuevo para agregar al inicio
     */
    
    public void agregarNodo(NodoGrafo nuevo) {
        if (nuevo == null) {
            throw new IllegalArgumentException("El nodo no puede ser nulo");
    }

    
        if (existeNodo(nuevo)) {
            System.out.println("El nodo ya existe en el grafo.");
            return; 
    }
        numeroNodos++;
        nuevo.setpNext(pFirst); 
        pFirst = nuevo; 
}
    
    /**
     * Verificamos si existe un nodo en el grafo, otra de sus primitivas
     * @author Francisco Fustero
     * @param nodo para verificar si existe
     * @return true si existe, false si no existe
     */
    
    private boolean existeNodo(NodoGrafo nodo) {
        NodoGrafo actual = pFirst; // Asumiendo que pFirst es el primer nodo de la lista
        while (actual != null) {
            if (actual.equals(nodo)) {
                                                  
                return true; // El nodo ya existe
        }
        actual = actual.getpNext(); // Avanzar al siguiente nodo
    }
        return false; // El nodo no existe
}
    

    /**
     * Metodo para imprimir por consola el grafo. Cabe recalcar que esta impresiones por consola era para probar si funcionaba correctamente la creacion del grafo
     * @author Francisco Fustero
     */
    public void printGrafo() {
        NodoGrafo actual = pFirst;
        while (actual != null) {
            System.out.print(actual.estacion.nombreEstacion + " cubieta : "+actual.estacion.cubierta + " sucursal: " +actual.estacion.sucursal   + " Adyacencias: ");
            actual.listaAdyacencia.imprimirAdyacencias();
            actual = actual.getpNext();
        }
        System.out.println(); 
    }
    /**
     * Buscamos un nodo del grafo por parametro del objeto estacion
     * @author Francisco Fustero
     * @param estacion para buscar por Estacion
     * @return el nodo si esta o null si no esta
     */
    
    public NodoGrafo buscarNodoPorEstacion(Estacion estacion) {
            NodoGrafo actual = pFirst;
            while (actual != null) {
                if (actual.getEstacion().equals(estacion)) {
                    return actual; // Devolver el nodo que coincide con la estación
                }
                actual = actual.getpNext();
            }
            return null; // No se encontró el nodo
        }
    
    
        /**
         * Metodo que hace el recorrido por DFS del grafo
         * @author Francisco Fustero
         * @param nodo para buscar por dfs
         * @param visitados los visitados correspodnientes
         */
        public void DFS(NodoGrafo nodo, ListaSimple visitados) {
            if (nodo == null || visitados.contiene(nodo.getEstacion())) {
                return; // Si el nodo es nulo o ya fue visitado, salimos
            }

            // Procesar el nodo
            System.out.println(nodo.getEstacion().getNombreEstacion());
            visitados.agregar(nodo.getEstacion()); // Marcar como visitado

            // Recorrer las adyacencias
            NodoListaAdyacencia adyacente = nodo.getListaAdyacencia().getHead();
            while (adyacente != null) {
                NodoGrafo siguienteNodo = buscarNodoPorEstacion(adyacente.estacion);
                DFS(siguienteNodo, visitados);
                adyacente = adyacente.getpNext(); 
        }
}
        
        /**
         * Metodo que busca un nodo del grafo por el nombre de la estacion
         * @author Francisco Fustero
         * @param nombre para buscar por nombre de la estacion
         * @return el nodo si encontro o null si no encontro
         */
        public NodoGrafo buscarPorNombreEstacion(String nombre){
        NodoGrafo actual = pFirst;
            while (actual != null) {
                if (actual.getEstacion().nombreEstacion.equals(nombre)) {
                    return actual; // Devolver el nodo que coincide con la estación
                }
                actual = actual.getpNext();
            }
            return null;
        
    }
        
        /**
         * Este metodo permite recorrer todos los nodos de un grafo (o lista de adyacencia) y aplicar una acción específica a cada estación. Esto tambien ayudara a llenar las cajitas en la interfaz de PanelPestanas
         * @author Anthony Caldera
         * @param action para ir llenando las cajitas obtenidas
         */
  
        public void forEachEstacion(Consumer<String> action) {
        NodoGrafo actual = pFirst;
        while (actual != null) {
            action.accept(actual.getEstacion().getNombreEstacion());
            actual = actual.getpNext();
        }
    }
        
        /**
         * Obtiene el numero de nodos que existe en el grafo
         * @author Francisco Fustero
         * @return el numero de nodos
         */
        public int getNumeroNodos() {
            
        return numeroNodos;
    }
}

