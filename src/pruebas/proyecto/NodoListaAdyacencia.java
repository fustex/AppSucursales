/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebas.proyecto;

/**
 *
 * @author ffust
 */
public class NodoListaAdyacencia {
    
    Estacion estacion;
    NodoListaAdyacencia pNext;

    public NodoListaAdyacencia(Estacion estacion) {
        this.estacion = estacion;
        this.pNext = pNext;
    }

    /**
     * @return the estacion
     */
    public Estacion getEstacion() {
        return estacion;
    }

    /**
     * @return the pNext
     */
    public NodoListaAdyacencia getpNext() {
        return pNext;
    }

    /**
     * @param pNext the pNext to set
     */
    public void setpNext(NodoListaAdyacencia pNext) {
        this.pNext = pNext;
    }

  
    
}