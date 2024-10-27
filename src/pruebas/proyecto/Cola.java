/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebas.proyecto;

/**
 * Clase que representa una cola de estaciones.
 * @author Anthony Caldera
 * se hizo esta clase debido a la investigacion del metodo BFS se requeria usar cola y usamos esta alternativa
 */



public class Cola {
    private NodoListaSimple head;
    private NodoListaSimple tail; // Para mantener el final de la cola
    private int size;

    /**
     * Constructor de la clase Cola
     * @author Anthony Caldera
     */
    public Cola() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    
    /**
     * Agrega una estación a la cola.
     * @author Anthony Caldera
     * @param estacion Estación a agregar.
     * primitivas de la cola
     */

    public void agregar(Estacion estacion) {
        NodoListaSimple nuevo = new NodoListaSimple(estacion);
        if (tail != null) {
            tail.setpNext(nuevo); // Conectar el nuevo nodo al final
        }
        tail = nuevo; // Actualizar el tail
        if (head == null) {
            head = nuevo; // Si la cola estaba vacía, el nuevo nodo es también el head
        }
        size++;
    }
    
    /**
     * Elimina y retorna la estación en la cabeza de la cola.
     * @author Anthony Caldera
     * @return Estación eliminada.
     * primitivas de la cola
     */

    public Estacion eliminar() {
        if (head == null) {
            return null; // Si la cola está vacía, retornamos null
        }
        Estacion estacion = head.getEstacion(); // Guardamos la estación del nodo a eliminar
        head = head.getpNext(); // Avanzamos el head al siguiente nodo
        if (head == null) {
            tail = null; // Si la cola queda vacía, también actualizamos el tail
        }
        size--; // Decrementamos el tamaño
        return estacion; // Retornamos la estación eliminada
    }

    /**
     * Verifica si la cola esta vacia
     * @author Anthony Caldera
     * @return si la cabeza es nula, la cola es vacia
     * primitivas de la cola
     */
    public boolean estaVacia() {
        return head == null;
    }
    
    /**
     * Tamaño que tiene la cola
     * @author Francisco Fustero
     * @return tamaño de la cola
     * primitivas de la cola
     */

    public int getSize() {
        return size;
    }
}
