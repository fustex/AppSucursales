 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebas.proyecto;

/**
 *
 * @author dugla
 */
public class Grafo {
    
  private NodoGrafo pFirst;

    public Grafo(NodoGrafo pFirst) {
        this.pFirst = pFirst;
    }

    /**
     * @return the pFirst
     */
    public NodoGrafo getpFirst() {
        return pFirst;
    }

    /**
     * @param pFirst the pFirst to set
     */
    public void setpFirst(NodoGrafo pFirst) {
        this.pFirst = pFirst;
    }
    
    public void agregarNodo(NodoGrafo nuevo) {
        if (nuevo == null) {
            throw new IllegalArgumentException("El nodo no puede ser nulo");
    }

    
        if (existeNodo(nuevo)) {
            System.out.println("El nodo ya existe en el grafo.");
            return; 
    }

        nuevo.setpNext(pFirst); 
        pFirst = nuevo; 
}
    private boolean existeNodo(NodoGrafo nodo) {
        NodoGrafo actual = pFirst; // Asumiendo que pFirst es el primer nodo de la lista
        while (actual != null) {
            if (actual.equals(nodo)) {
                                                  
                return true; // El nodo ya existe
        }
        actual = actual.getpNext(); // Avanzar al siguiente nodo
    }
        return false; // El nodo no existe
}
    
    
    
    public void printGrafo() {
        NodoGrafo actual = pFirst;
        while (actual != null) {
            System.out.print(actual.estacion.nombreEstacion + " cubieta : "+actual.estacion.cubierta + " sucursal: " +actual.estacion.sucursal   + " Adyacencias: ");
            actual.listaAdyacencia.imprimirAdyacencias();
            actual = actual.getpNext();
        }
        System.out.println(); 
    }
    
    public NodoGrafo buscarNodoPorEstacion(Estacion estacion) {
            NodoGrafo actual = pFirst;
            while (actual != null) {
                if (actual.getEstacion().equals(estacion)) {
                    return actual; // Devolver el nodo que coincide con la estación
                }
                actual = actual.getpNext();
            }
            return null; // No se encontró el nodo
        }
        public void DFS(NodoGrafo nodo, ListaSimple visitados) {
            if (nodo == null || visitados.contiene(nodo.getEstacion())) {
                return; // Si el nodo es nulo o ya fue visitado, salimos
            }

            // Procesar el nodo
            System.out.println(nodo.getEstacion().getNombreEstacion());
            visitados.agregar(nodo.getEstacion()); // Marcar como visitado

            // Recorrer las adyacencias
            NodoListaAdyacencia adyacente = nodo.getListaAdyacencia().getHead();
            while (adyacente != null) {
                NodoGrafo siguienteNodo = buscarNodoPorEstacion(adyacente.estacion);
                DFS(siguienteNodo, visitados);
                adyacente = adyacente.getpNext(); 
        }
}
}

