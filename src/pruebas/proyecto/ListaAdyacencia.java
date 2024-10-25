/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebas.proyecto;

/**
 *
 * @author ffust
 */
public class ListaAdyacencia {
    private NodoListaAdyacencia head;
    private int size; 
    

    public ListaAdyacencia() {
        this.head = null;
        this.size = 0;
    }

    /**
     * @return the head
     */
    public NodoListaAdyacencia getHead() {
        return head;
    }

    /**
     * @param head the head to set
     */
    public void setHead(NodoListaAdyacencia head) {
        this.head = head;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }
    
    // agregar nodoGrafo Al principio de la lista de adyacencia 

    /**
     *
     * @param nuevoNodoGrafo1
     
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
    public void imprimirAdyacencias() {
        NodoListaAdyacencia actual = head;
        while (actual != null) {
            System.out.print(actual.estacion.getNombreEstacion() + " ");
            actual = actual.getpNext();
        }
        System.out.println();

        }
    
    private boolean existeAdyacencia(NodoGrafo nodo) {
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
            
  
   
       
    
