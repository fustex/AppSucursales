/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebas.proyecto;



/**
 *
 * @author ffust
 */
public class FuncionesUtiles {
    
    public static void enlazarNodos(NodoGrafo nodo1, NodoGrafo nodo2){
        
        nodo1.listaAdyacencia.nuevaAdyacencia(nodo2);
        nodo2.listaAdyacencia.nuevaAdyacencia(nodo1);
        
        
    }
    
    public static void agregarSucursal(NodoGrafo nodo){
        
        nodo.estacion.setSucursal(true);
    }
    
    
    
    public static void marcarCubiertas(Grafo grafo, int t) {
            if (grafo == null || t < 0) {
                throw new IllegalArgumentException("El grafo no puede ser nulo y t debe ser un entero positivo.");
            }

            ListaSimple visitados = new ListaSimple();
            NodoGrafo actual = grafo.getpFirst();

            // Buscar nodos con sucursal = true
            while (actual != null) {
                if (actual.getEstacion().isSucursal()) {
                    // Realizar DFS desde este nodo con la distancia t
                    DFSMarcarCubiertas(grafo, actual, visitados, t, 0); // Pasar el grafo
                }
                actual = actual.getpNext();
            }
        }

    private static void DFSMarcarCubiertas(Grafo grafo, NodoGrafo nodo, ListaSimple visitados, int t, int distancia) {
        if (nodo == null || visitados.contiene(nodo.getEstacion()) || distancia > t) {
            return; // Si el nodo es nulo, ya fue visitado o se ha superado la distancia
        }

        // Marcar el nodo como visitado
        visitados.agregar(nodo.getEstacion());
        
        // Cambiar cubierta a true
        nodo.getEstacion().setCubierta(true);

        // Recorrer las adyacencias
        NodoListaAdyacencia adyacente = nodo.getListaAdyacencia().getHead();
        while (adyacente != null) {
            NodoGrafo siguienteNodo = buscarNodoPorEstacion(grafo, adyacente.estacion); // Ahora tiene acceso a grafo
            DFSMarcarCubiertas(grafo, siguienteNodo, visitados, t, distancia + 1); // Pasar el grafo
            adyacente = adyacente.getpNext(); 
        }
    }

    private static NodoGrafo buscarNodoPorEstacion(Grafo grafo, Estacion estacion) {
        if (grafo == null || estacion == null) {
            return null; // Si el grafo o la estación son nulos, retornamos null
        }

        NodoGrafo actual = grafo.getpFirst();
        while (actual != null) {
            if (actual.getEstacion().equals(estacion)) {
                return actual; // Retornamos el nodo que coincide con la estación
            }
            actual = actual.getpNext(); // Avanzar al siguiente nodo
        }
        return null; // No se encontró el nodo
    }
    
    /**
     *
     * @param grafo
     * @param nodoInicial
     * @param t
     * @return
     */
    public static ListaSimple obtenerNodosEnRangoDFS(Grafo grafo, NodoGrafo nodoInicial, int t) {
        if (grafo == null || nodoInicial == null || t < 0) {
            throw new IllegalArgumentException("El grafo y el nodo no pueden ser nulos, y t debe ser un entero positivo.");
        }

        ListaSimple nodosEnRango = new ListaSimple();
        ListaSimple visitados = new ListaSimple();
        DFSRecorrido(grafo, nodoInicial, nodosEnRango, visitados, t, 0);
        return nodosEnRango; // Esta funcion retorna una lista
    }

    private static void DFSRecorrido(Grafo grafo, NodoGrafo nodo, ListaSimple nodosEnRango, ListaSimple visitados, int t, int distancia) {
        if (nodo == null || visitados.contiene(nodo.getEstacion()) || distancia > t) {
            return; // Si el nodo es nulo, ya fue visitado o se ha superado la distancia
        }

        // Marcar el nodo como visitado
        visitados.agregar(nodo.getEstacion());

        // Agregar el nodo a la lista de nodos en rango
        nodosEnRango.agregar(nodo.getEstacion());

        // Recorrer las adyacencias
        NodoListaAdyacencia adyacente = nodo.getListaAdyacencia().getHead();
        while (adyacente != null) {
            NodoGrafo siguienteNodo = buscarNodoPorEstacion(grafo, adyacente.estacion); // Asegúrate de que esta función esté definida
            DFSRecorrido(grafo, siguienteNodo, nodosEnRango, visitados, t, distancia + 1);
            adyacente = adyacente.getpNext(); 
        }
    }  
    
    public static ListaSimple obtenerNodosEnRangoBFS(Grafo grafo, NodoGrafo nodoInicial, int t) {
        if (grafo == null || nodoInicial == null || t < 0) {
            throw new IllegalArgumentException("El grafo y el nodo no pueden ser nulos, y t debe ser un entero positivo.");
        }

        ListaSimple nodosEnRango = new ListaSimple();
        ListaSimple visitados = new ListaSimple();
        ListaSimple cola = new ListaSimple(); // Usando ListaSimple como cola
        
        cola.agregar(nodoInicial.getEstacion()); // Agregar la estación del nodo inicial a la cola
        int distancia = 0;

        while (!cola.estaVacia() && distancia <= t) {
            int size = cola.getSize(); // Tamaño de la cola en el nivel actual

            for (int i = 0; i < size; i++) {
                Estacion estacionActual = (Estacion) cola.eliminarPrimero(); // Obtener y eliminar la estación de la cola
                NodoGrafo nodo = buscarNodoPorEstacion(grafo, estacionActual); // Buscar el nodo correspondiente

                if (nodo == null || visitados.contiene(nodo.getEstacion())) {
                    continue; // Si el nodo es nulo o ya fue visitado, continuar
                }

                // Marcar el nodo como visitado
                visitados.agregar(nodo.getEstacion());

                // Agregar el nodo a la lista de nodos en rango
                nodosEnRango.agregar(nodo.getEstacion());

                // Recorrer las adyacencias
                NodoListaAdyacencia adyacente = nodo.getListaAdyacencia().getHead();
                while (adyacente != null) {
                    NodoGrafo siguienteNodo = buscarNodoPorEstacion(grafo, adyacente.estacion);
                    if (siguienteNodo != null && !visitados.contiene(siguienteNodo.getEstacion())) {
                        cola.agregar(siguienteNodo.getEstacion()); // Agregar la estación del nodo a la cola
                    }
                    adyacente = adyacente.getpNext();
                }
            }
            distancia++; // Incrementar la distancia después de procesar todos los nodos en el nivel actual
        }

        return nodosEnRango; // Esta función retorna una lista de nodos en rango
    }
    
    
    
}

    

 
