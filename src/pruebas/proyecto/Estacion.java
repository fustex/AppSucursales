/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebas.proyecto;

/**
 * Clase que representa una estación.
 * @author Francisco Fustero
 * este sera nuestro objeto que estara dentro de los nodos del grafo como tipo de dato (lo que hay dentro de la cajita)
 */
public class Estacion {
    
    String nombreEstacion;
    boolean sucursal;
    boolean cubierta;
    
    /**
     * Constructor de la clase Estacion
     * @author Francisco Fustero
     * @param nombreEstacion 
     */

    public Estacion(String nombreEstacion) {
        this.nombreEstacion = nombreEstacion;
        this.sucursal = false;
        this.cubierta = false;
    }

    /**
     * Obtenemos el get del nombre de la estacion
     * @author Francisco Fustero
     * @return the nombreEstacion
     */
    public String getNombreEstacion() {
        return nombreEstacion;
    }

    /**
     * Damos el set del nombre de la estacion
     * @author Francisco Fustero
     * @param nombreEstacion the nombreEstacion to set
     */
    public void setNombreEstacion(String nombreEstacion) {
        this.nombreEstacion = nombreEstacion;
    }

    /**
     * Obtenemos si la sucursal esta cubierta un true o false
     * @author Francisco Fustero
     * @return the sucursal
     */
    public boolean isSucursal() {
        return sucursal;
    }

    /**
     * Damos el set de la sucursal para colocarlo true o false 
     * @author Francisco Fustero
     * @param sucursal the sucursal to set
     */
    public void setSucursal(boolean sucursal) {
        this.sucursal = sucursal;
    }

    /**
     * Obtenemos el booleano si la estacion esta cubierta un true o false
     * @author Francisco Fustero
     * @return the cubierta
     */
    public boolean isCubierta() {
        return cubierta;
    }

    /**
     * Damos el set para colocar la cubierta tanto true como false
     * @author Francisco Fustero
     * @param cubierta the cubierta to set
     */
    public void setCubierta(boolean cubierta) {
        this.cubierta = cubierta;
    }
    
}
