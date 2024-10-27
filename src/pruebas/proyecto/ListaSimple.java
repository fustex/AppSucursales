/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebas.proyecto;

/**
 *Claro que representa una lista simple
 * @author Anthony Caldera
 * 
 */
public class ListaSimple {
    
    private NodoListaSimple head;
    private int size;

    /**
     * Constructor de la clase lista simple
     * @author Anthony Caldera
     */
    
    public ListaSimple() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Obtengo la cabeza de la lista  simple (primer elemento)
     * @author Anthony Caldera
     * @return the head
     */
    public Object getHead() {
        return head;
    }

    /**
     * Colocamos el apuntador a la cabeza de la lista simple
     * @author Anthony Caldera
     * @param head the head to set
     */
    public void setHead(NodoListaSimple head) {
        this.head = head;
    }

    /**
     * Nos da el tamaño de la lista simple
     * @author Anthony Caldera
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * Nos da para colocar el tamano de la lista simple
     * @author Anthony Caldera
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Primitiva para agregar un elemento al principio de la lista
     * @author Anthony Caldera
     * @param estacion para agregar la estacion al inicio
     */
    public void agregar(Estacion estacion) {
           NodoListaSimple nuevo = new NodoListaSimple(estacion);
           nuevo.setpNext(head);
           head = nuevo;
    
}
    
    /**
     * Metodo que nos da dado un elemento de la lista por paramentr (estacion), revisar si esta, falso en dado contrario
     * @param estacion la estacion para ver si esta contenida
     * @return true si hay un elemento dentro de la lista dado por parametro y false si no esta ese elemento en la lista
     */
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
    /**
     * Metodo que imprime todos los elementos de la lista. Este metodo se hizo con el proposito de revisar por consola si la lista se ejecutaba correctamente
     * @author Anthony Caldera
     */
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
    
    /**
     * Metodo para mostrar una lista por consola pero haciendo saltos de lineas cuando muestre el siguiente elemento. Este metodo es para probar si funcionaba correctamente por consola
     * @author Anthony Caldera
     * @return lista de los elementos separados con salto de linea
     */
    
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
    /**
     * Metodo que verifica si la lista esta vacia o no
     * @author Anthony Caldera
     * @return true si la lista esta vacia, falso si la lista no esta vacia
     */
    
    public boolean estaVacia(){

        return(head == null);
    }
    
    /**
     * Metodo que elimina el primer elemento de la lista
     * @author Anthony Caldera
     * @return La estacion eliminada
     */
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
