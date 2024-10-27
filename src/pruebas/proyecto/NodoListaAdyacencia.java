/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebas.proyecto;

/**
 *Clase que representa los NodoListaAdyacencia
 *@author Francisco Fustero
 */
public class NodoListaAdyacencia {
    
    Estacion estacion;
    NodoListaAdyacencia pNext;
    
    /**
     * Constructor de la clase NodoListaAdyacenncia dado la estacion como parametro
     * @author Fransisco Fustero
     * @param estacion para el constructor de la clase
     */

    public NodoListaAdyacencia(Estacion estacion) {
        this.estacion = estacion;
        this.pNext = pNext;
    }

    /**
     * Metodo que obtiene la estacion de los nodos de la lista adyacencia
     * @author Francisco Fustero
     * @return la estacion
     */
    public Estacion getEstacion() {
        return estacion;
    }

    /**
     * Metodo que obtiene el apuntador al siguiente del nodo lista de adyacencia
     * @author Francisco Fustero
     * @return el pNext
     */
    public NodoListaAdyacencia getpNext() {
        return pNext;
    }

    /**
     * Metodo que coloca el apuntador al siguiente de los nodos de la lista de adyacencia
     * @author Francisco Fustero
     * @param pNext el pNext para colocar
     */
    public void setpNext(NodoListaAdyacencia pNext) {
        this.pNext = pNext;
    }

  
    
}