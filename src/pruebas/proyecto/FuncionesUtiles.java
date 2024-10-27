/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pruebas.proyecto;



/**
 * Clase que contiene funciones útiles para la manipulación de grafos.
 * @author Francisco Fustero
 */
public class FuncionesUtiles {
    
    /**
     * Enlaza dos nodos en el grafo.
     * @author Anthony Caldera
     * @param nodo1 Primer nodo.
     * @param nodo2 Segundo nodo.
     */
    
    public static void enlazarNodos(NodoGrafo nodo1, NodoGrafo nodo2){
        
        nodo1.listaAdyacencia.nuevaAdyacencia(nodo2);
        nodo2.listaAdyacencia.nuevaAdyacencia(nodo1);
        
        
    }
    
    /**
     * Agregamos que existe una sucursal en esta estacion y se pone true
     * @author Anthony Caldera
     * @param nodo a pasar
     */
    
    
    public static void agregarSucursal(NodoGrafo nodo){
        
        nodo.estacion.setSucursal(true);
    }
    
    /**
     * Eliminamos una sucursal de la estacion y se pone false
     * @author Francisco Fustero
     * @param nodo  a pasar
     */
    
    public static void eliminarSucursal(NodoGrafo nodo){
        nodo.estacion.setSucursal(false);
    }
    
    /**
     * Dependiendo del numero de t estaciones marcadas por el usuario, marcamos las estaciones cubiertas por una sucursal
     * @author Francisco Fustero
     * @param grafo a pasar
     * @param t a pasar
     */
    
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
    
    /**
     * Este metodo modificara las true o false de las estaciones cubiertas por una sucursal siguiendo el metodo del DFS
     * @author Francisco Fustero
     * @param grafo a pasar
     * @param nodo a pasar
     * @param visitados pasar tambien
     * @param t a pasar 
     * @param distancia a pasar
     */

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
    
    /**
     * Buscamos un nodo en especifico del grafo por el objeto estacion
     * @author Francisco Fustero
     * @param grafo a pasar
     * @param estacion a pasar
     * @return 
     */

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
     * Con este metodo, nos devolvera una lista simple de las estaciones cubierta por una sucursal
     * @author Francisco Fustero
     * @param grafo a pasar
     * @param nodoInicial a pasar 
     * @param t a pasar
     * @return la lista de las estaciones cubiertas por una sucursal
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
    
    /**
     * Con este metodo, marcamos las estaciones cubiertas por una sucursal y agregamos las estaciones cubierta en una lista
     * @author Francisco Fustero
     * @param grafo a pasar
     * @param nodo pasamos esto tambien
     * @param nodosEnRango pasamos
     * @param visitados pasamos
     * @param t pasamos
     * @param distancia pasamos
     */

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
            NodoGrafo siguienteNodo = buscarNodoPorEstacion(grafo, adyacente.estacion);
            DFSRecorrido(grafo, siguienteNodo, nodosEnRango, visitados, t, distancia + 1);
            adyacente = adyacente.getpNext(); 
        }
    }  
    
    /**
     * Este metodo nos va retornar el rango (una lista) de las estaciones cubiertas por una sucursal siguiendo la definicion del metodo del BFS, aca usamos colas tambien
     * @author Anthony Caldera
     * @param grafo nuestro grafo a pasar
     * @param nodoInicial nodografo en el que empezara
     * @param t dependiendo del valor de t del usuario
     * @return lista de las estaciones cubiertas por una sucursal por el metodo del BFS
     */
    
    public static ListaSimple obtenerNodosEnRangoBFS(Grafo grafo, NodoGrafo nodoInicial, int t) {
        if (grafo == null || nodoInicial == null || t < 0) {
            throw new IllegalArgumentException("El grafo y el nodo no pueden ser nulos, y t debe ser un entero positivo.");
        }

        ListaSimple nodosEnRango = new ListaSimple();
        Cola cola = new Cola(); // Usando la clase Cola
        ListaSimple visitados = new ListaSimple(); // Para llevar un registro de nodos visitados

        cola.agregar(nodoInicial.getEstacion()); // Agregar la estación del nodo inicial a la cola
        visitados.agregar(nodoInicial.getEstacion()); // Marcar el nodo inicial como visitado
        int distancia = 0;

        while (!cola.estaVacia() && distancia <= t) {
            int size = cola.getSize(); // Tamaño de la cola en el nivel actual

            for (int i = 0; i < size; i++) {
                Estacion estacionActual = cola.eliminar(); // Obtener y eliminar la estación de la cola
                NodoGrafo nodoActual = buscarNodoPorEstacion(grafo, estacionActual); // Buscar el nodo correspondiente

                // Agregar el nodo a la lista de nodos en rango
                nodosEnRango.agregar(nodoActual.getEstacion());

                // Recorrer las adyacencias
                NodoListaAdyacencia adyacente = nodoActual.getListaAdyacencia().getHead();
                while (adyacente != null) {
                    NodoGrafo siguienteNodo = buscarNodoPorEstacion(grafo, adyacente.estacion);
                    if (siguienteNodo != null && !visitados.contiene(siguienteNodo.getEstacion())) {
                        cola.agregar(siguienteNodo.getEstacion()); // Agregar la estación del nodo a la cola
                        visitados.agregar(siguienteNodo.getEstacion()); // Marcar como visitado
                    }
                    adyacente = adyacente.getpNext();
                }
            }
            distancia++; // Incrementar la distancia después de procesar todos los nodos en el nivel actual
        }

        return nodosEnRango; // Retornar la lista de nodos alcanzables
    }
    
    
    /**
     * Este metodo va a desmarcar cuales estaciones estan cubiertas por una sucursal que ya no hay sucursal en esa estacion
     * @author Anthony Caldera
     * @param grafo un grafo a pasar
     */
    
    public static void desmarcarCubiertas(Grafo grafo){
        NodoGrafo actual = grafo.getpFirst();
        while(actual != null){
            if (actual.estacion.sucursal !=  true){
                actual.estacion.setCubierta(false);
                
            }
            actual = actual.getpNext();
        }
    }
    
    
    
}

    

 
