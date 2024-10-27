/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebas.proyecto;

/**
 *
 * @author ffust
 */




public class Cola {
    private NodoListaSimple head;
    private NodoListaSimple tail; // Para mantener el final de la cola
    private int size;

    public Cola() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

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

    public boolean estaVacia() {
        return head == null;
    }

    public int getSize() {
        return size;
    }
}
