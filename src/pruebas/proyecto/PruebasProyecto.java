/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pruebas.proyecto;

/**
 * Clase principal para probar metodos en la consola y verificar si todo esta funcionando correctamente
 * @author Francisco Fustero
 */
public class PruebasProyecto {

    /**
     * El main para ir probando todos los metodos por consola y verificando si funcionan todos correctamente
     * @author Francisco Fustero
     * @param args para correr el main (intuititvo al crear el proyecto aca)
     */
    public static void main(String[] args) {
        
        
        //llamados de metodos y haciendo revisiones
        Estacion estacion1 = new Estacion("sabana grande");
        Estacion estacion2 = new Estacion("palo verde");
        Estacion estacion3 = new Estacion("chacaito");
        Estacion estacion4 = new Estacion("dos caminos");
        Estacion estacion5 = new Estacion("petare");
        
        NodoGrafo nodo1 = new NodoGrafo(estacion1);
        FuncionesUtiles.agregarSucursal(nodo1);
        NodoGrafo nodo2 = new NodoGrafo(estacion2);
        NodoGrafo nodo3 = new NodoGrafo(estacion3);
        NodoGrafo nodo4 = new NodoGrafo(estacion4);
        NodoGrafo nodo5 = new NodoGrafo(estacion5);
        
        Grafo grafo = new Grafo(null);
        
        grafo.agregarNodo(nodo1);
        grafo.agregarNodo(nodo2);
        grafo.agregarNodo(nodo3);
        grafo.agregarNodo(nodo4);
        grafo.agregarNodo(nodo5);
        
        
        FuncionesUtiles.enlazarNodos(nodo1, nodo2);
        FuncionesUtiles.enlazarNodos(nodo1,nodo5);
        FuncionesUtiles.enlazarNodos(nodo1,nodo4);
        
        grafo.printGrafo();
        
        ListaSimple lista = FuncionesUtiles.obtenerNodosEnRangoBFS(grafo,nodo1, 3);
        
        lista.imprimir();
        
        grafo.printGrafo();
        
        
        System. out. print(lista.ListaMostrada()); 
       

    }
    
    
}


