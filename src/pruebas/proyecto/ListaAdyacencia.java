/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebas.proyecto;

/**
 * Clase que representa una lista de adyacencia para el grafo.
 * @author Francisco Fustero
 */
public class ListaAdyacencia {
    private NodoListaAdyacencia head;
    private int size; 
    
    /**
     * Constructor de la clase de ListaAdyacencia
     * @author Francisco Fustero
     */
    public ListaAdyacencia() {
        this.head = null;
        this.size = 0;
    }

    /**
     * obtenemos la cabeza (primer elemento) de la lista de adyacencia
     * @author Francisco Fustero
     * @return la cabeza
     */
    public NodoListaAdyacencia getHead() {
        return head;
    }

    /**
     * Colocamos quien sera la cabeza en nuestra lista de adyacencia
     * @author Francisco Fustero
     * @param head para establecer la cabeza
     */
    public void setHead(NodoListaAdyacencia head) {
        this.head = head;
    }

    /**
     * Obtenemos el tamaño de la lista de adyacencia
     * @author Francisco Fustero
     * @return el tamaño
     */
    public int getSize() {
        return size;
    }

    /**
     * Damos el tamaño de la lista de adyacencia
     * @author Francisco Fustero
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }
    

    /**
     *Agregar un nodoGrafo al principio de la lista de adyacencia , puede ser una primitiva
     * @author Francisco Fustero
     * @param nuevoNodoGrafo1  para agregar este nodo al inicio
     */
    public void nuevaAdyacencia(NodoGrafo nuevoNodoGrafo1) {
        if (nuevoNodoGrafo1 == null) {
            throw new IllegalArgumentException("El nodo no puede ser nulo");
        }

        // Verificar si ya existe la adyacencia
        if (existeAdyacencia(nuevoNodoGrafo1)) {
            System.out.println("La adyacencia ya existe.");
            return; // No se agrega si ya existe
        }

        NodoListaAdyacencia nuevo1 = new NodoListaAdyacencia(nuevoNodoGrafo1.getEstacion());

        nuevo1.setpNext(head);
        head = nuevo1;
        
    }
    
    /**
     * Elimina las adyacencias entre los nodos
     * @author Francisco Fustero
     * @param nodo1 para enlazar las adyacencias 
     * @param nodo2 para enlazar las adyacencias
     */
    
    public static void eliminarAdyacenciasEntreNodos(NodoGrafo nodo1, NodoGrafo nodo2) {
        NodoListaAdyacencia aux1 = nodo1.getListaAdyacencia().head;
        NodoListaAdyacencia anterior1 = null;

    // Eliminar adyacencias de nodo1 a nodo2
        while (aux1 != null) {
            if (aux1.estacion.equals(nodo2.estacion)) { // Comparar estaciones
                if (anterior1 == null) {
                nodo1.getListaAdyacencia().head = aux1.pNext; // Eliminar la cabeza
                } else {
                anterior1.pNext = aux1.pNext; // Eliminar el nodo
                }
                aux1 = (anterior1 == null) ? nodo1.getListaAdyacencia().head : anterior1.pNext; // Actualizar aux1
            } else {
                anterior1 = aux1; // Mover anterior solo si no se eliminó
                aux1 = aux1.pNext; // Avanzar al siguiente nodo
            }
        }

        NodoListaAdyacencia aux2 = nodo2.getListaAdyacencia().head;
        NodoListaAdyacencia anterior2 = null;

    // Eliminar adyacencias de nodo2 a nodo1
        while (aux2 != null) {
            if (aux2.estacion.equals(nodo1.estacion)) { // Comparar estaciones
                if (anterior2 == null) {
                    nodo2.getListaAdyacencia().head = aux2.pNext; // Eliminar la cabeza
                } else {
                    anterior2.pNext = aux2.pNext; // Eliminar el nodo
                }
                aux2 = (anterior2 == null) ? nodo2.getListaAdyacencia().head : anterior2.pNext; // Actualizar aux2
            } else {
                anterior2 = aux2; // Mover anterior solo si no se eliminó
                aux2 = aux2.pNext; // Avanzar al siguiente nodo
            }
        }
    }
    
    /**
     * Metodo para imprimir las adyacencias por consola. Este metodo es de prueba para ver si funcionaba correctamente el metodo
     * @author Francisco Fustero
     */
    
    public void imprimirAdyacencias() {
        NodoListaAdyacencia actual = head;
        while (actual != null) {
            System.out.print(actual.estacion.getNombreEstacion() + " ");
            actual = actual.getpNext();
        }
        System.out.println();

        }
    
    /**
     * Metodo para verificar si existen adyacencias
     * @author Francisco Fustero
     * @param nodo para verificar y revisar si existe
     * @return true si existe o false en caso contrario
     */
    
    public boolean existeAdyacencia(NodoGrafo nodo) {
    NodoListaAdyacencia actual = head; // Asumiendo que head es el inicio de la lista de adyacencias

        while (actual != null) {
            if (actual.estacion.equals(nodo.getEstacion())) { // Comparar estaciones
                return true; // Ya existe la adyacencia
            }
            actual = actual.getpNext(); // Avanzar al siguiente nodo de adyacencia
        }
        return false; // No existe adyacencia
    }
}
            
  
   
       
    
