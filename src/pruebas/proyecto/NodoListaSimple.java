/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebas.proyecto;

/**
 *Clase que representa los Nodos de una lista simple
 * @author Anthony Caldera
 */
public class NodoListaSimple {
    
    NodoListaSimple pNext; 
    Estacion estacion;
    
    /**
     * Constructor de la clase Nodo de lista simple correspondiente
     * @author Anthony Caldera
     * @param estacion para el constructor de la clase
     */

    public NodoListaSimple(Estacion estacion) {
        this.estacion = estacion;
        this.pNext = null;
    }

    /**
     * Metodo que obtienes el apuntador del siguiente de los nodos de la lista simple
     * @author Anthony Caldera
     * @return el pNext
     */
    public NodoListaSimple getpNext() {
        return pNext;
    }

    /**
     * Metodo que coloca el apuntador al siguiente de los nodos de la lista simple
     * @author Anthony Caldera
     * @param pNext el pNext para colocar
     */
    public void setpNext(NodoListaSimple pNext) {
        this.pNext = pNext;
    }

    /**
     * Metodo que obtienes la estacion de los nodos la lista simple 
     * @author Anthony Caldera
     * @return la estacion
     */
    public Estacion getEstacion() {
        return estacion;
    }

    /**
     * Metodo que coloca el apuntador del siguiente de los nodos de la lista simple
     * @author Anthony Caldera
     * @param estacion la estacion para colocar
     */
    public void setEstacion(Estacion estacion) {
        this.estacion = estacion;
    }
    
    
}
