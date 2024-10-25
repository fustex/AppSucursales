/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebas.proyecto;

/**
 *
 * @author ffust
 */
public class Estacion {
    
    String nombreEstacion;
    boolean sucursal;
    boolean cubierta;

    public Estacion(String nombreEstacion) {
        this.nombreEstacion = nombreEstacion;
        this.sucursal = false;
        this.cubierta = false;
    }

    /**
     * @return the nombreEstacion
     */
    public String getNombreEstacion() {
        return nombreEstacion;
    }

    /**
     * @param nombreEstacion the nombreEstacion to set
     */
    public void setNombreEstacion(String nombreEstacion) {
        this.nombreEstacion = nombreEstacion;
    }

    /**
     * @return the sucursal
     */
    public boolean isSucursal() {
        return sucursal;
    }

    /**
     * @param sucursal the sucursal to set
     */
    public void setSucursal(boolean sucursal) {
        this.sucursal = sucursal;
    }

    /**
     * @return the cubierta
     */
    public boolean isCubierta() {
        return cubierta;
    }

    /**
     * @param cubierta the cubierta to set
     */
    public void setCubierta(boolean cubierta) {
        this.cubierta = cubierta;
    }
    
}
