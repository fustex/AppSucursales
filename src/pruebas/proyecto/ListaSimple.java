/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebas.proyecto;

/**
 *
 * @author ffust
 */
public class ListaSimple {
    
    private NodoListaSimple head;
    private int size;

    public ListaSimple() {
        this.head = null;
        this.size = 0;
    }

    /**
     * @return the head
     */
    public Object getHead() {
        return head;
    }

    /**
     * @param head the head to set
     */
    public void setHead(NodoListaSimple head) {
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

    public void agregar(Estacion estacion) {
           NodoListaSimple nuevo = new NodoListaSimple(estacion);
           nuevo.setpNext(head);
           head = nuevo;
    
}
    public boolean contiene(Estacion estacion) {
           NodoListaSimple actual = head;
           while (actual != null) {
               if (actual.estacion.equals(estacion)) {
                   return true; 
               }
               actual = actual.getpNext();
           }
           return false; 
       }
    public void imprimir() {
        if (head == null) {
            System.out.println("La lista está vacía.");
            return;
        }

        NodoListaSimple nodoActual = head;
        System.out.print("Elementos de la lista: ");
        
        while (nodoActual != null) {
            System.out.print(nodoActual.getEstacion().nombreEstacion + " "); // Asumiendo que getEstacion() devuelve una representación adecuada
            nodoActual = nodoActual.getpNext(); // Mover al siguiente nodo
        }
        
        System.out.println(); // Nueva línea al final
    }
    public String ListaMostrada() {
    String resultado = "";
    NodoListaSimple actual = head;

    while (actual != null) {
        resultado += actual.getEstacion().getNombreEstacion(); // Suponiendo que Estacion tiene un método toString()
        resultado += "\n"; // Agrega un salto de línea
        actual = actual.getpNext(); // Avanza al siguiente nodo
    }

    return resultado; // Retorna el resultado como String
}
    public boolean estaVacia(){
        
        return(head == null);
    }
    public Estacion eliminarPrimero() {
        if (head == null) {
            return null; // Si la lista está vacía, retornamos null
        }
        Estacion estacion = head.getEstacion(); // Guardamos la estación del nodo a eliminar
        head = head.getpNext(); // Avanzamos el head al siguiente nodo
        size--; // Decrementamos el tamaño
        return estacion; // Retornamos la estación eliminada
}
}
