/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebas.proyecto;

/**
 *Clase que representa un nodo en el grafo.
 *@author Francisco Fustero
 */
public class NodoGrafo {
    
    ListaAdyacencia listaAdyacencia;
    Estacion estacion;
    NodoGrafo pNext;
    
    /**
     * Constructor de la clase NodoGrafo pasando por parametro la estacion
     * @author Francisco Fustero
     * @param estacion para el constructor de la clase
     */

    public NodoGrafo( Estacion estacion) {
        this.listaAdyacencia = new ListaAdyacencia();
        this.estacion = estacion;
        this.pNext = null;
    }

    /**
     * Metodo para obtener la listadeadyacencia de los nodos grafos correspondientes
     * @author Francisco Fustero
     * @return la lista de adyacencia
     */
    public ListaAdyacencia getListaAdyacencia() {
        return listaAdyacencia;
    }

    /**
     * Metodo para colocar una lista de adyacencia de los nodos grafos correspondientes
     * @author Francisco Fustero
     * @param listaAdyacencia the listaAdyacencia to set
     */
    public void setListaAdyacencia(ListaAdyacencia listaAdyacencia) {
        this.listaAdyacencia = listaAdyacencia;
    }

    /**
     * Metodo para obtener el objeto estacion del nodo grafo
     * @author Francisco Fustero
     * @return the estacion
     */
    public Estacion getEstacion() {
        return estacion;
    }

    /**
     * Metodo que coloca una estacion de objeto estacion a los nodos grafos correspondientes
     * @author Francisco Fustero
     * @param estacion the estacion to set
     */
    public void setEstacion(Estacion estacion) {
        this.estacion = estacion;
    }

    /**
     * Metodo que retorna el que apuntaor al siguiente de los nodosgrafos
     * @author Francisco Fustero
     * @return el pNext
     */
    public NodoGrafo getpNext() {
        return pNext;
    }

    /**
     * Metodo para colocar el apuntador al siguiente de los nodos grafos
     * @author Fransico Fustero
     * @param pNext the pNext to set
     */
    public void setpNext(NodoGrafo pNext) {
        this.pNext = pNext;
    }
    
    
}
