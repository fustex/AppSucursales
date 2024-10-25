/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebas.proyecto;

/**
 *
 * @author ffust
 */
public class NodoListaSimple {
    
    NodoListaSimple pNext;
    
    Estacion estacion;

    public NodoListaSimple(Estacion estacion) {
        this.estacion = estacion;
        this.pNext = null;
    }

    /**
     * @return the pNext
     */
    public NodoListaSimple getpNext() {
        return pNext;
    }

    /**
     * @param pNext the pNext to set
     */
    public void setpNext(NodoListaSimple pNext) {
        this.pNext = pNext;
    }

    /**
     * @return the estacion
     */
    public Estacion getEstacion() {
        return estacion;
    }

    /**
     * @param estacion the estacion to set
     */
    public void setEstacion(Estacion estacion) {
        this.estacion = estacion;
    }
    
    
}
