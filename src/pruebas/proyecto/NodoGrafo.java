/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebas.proyecto;

/**
 *
 * @author ffust
 */
public class NodoGrafo {
    
    ListaAdyacencia listaAdyacencia;
    Estacion estacion;
    NodoGrafo pNext;

    public NodoGrafo( Estacion estacion) {
        this.listaAdyacencia = new ListaAdyacencia();
        this.estacion = estacion;
        this.pNext = null;
    }

    /**
     * @return the listaAdyacencia
     */
    public ListaAdyacencia getListaAdyacencia() {
        return listaAdyacencia;
    }

    /**
     * @param listaAdyacencia the listaAdyacencia to set
     */
    public void setListaAdyacencia(ListaAdyacencia listaAdyacencia) {
        this.listaAdyacencia = listaAdyacencia;
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

    /**
     * @return the pNext
     */
    public NodoGrafo getpNext() {
        return pNext;
    }

    /**
     * @param pNext the pNext to set
     */
    public void setpNext(NodoGrafo pNext) {
        this.pNext = pNext;
    }
    
    
}
