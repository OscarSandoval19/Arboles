package umg.edu.progra.arboles;

/**
 * Clase principal que demuestra el uso del Arbol Binario de Busqueda (BST)
 * implementado manualmente, sin usar librerias como java.util.
 *
 * Ejecucion sugerida:
 *   1. mvn compile
 *   2. mvn exec:java -Dexec.mainClass="umg.edu.progra.arboles.Principal"
 *
 * @author Walter Cordova
 */
public class Principal {

    public static void main(String[] args) {

        ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();
        int[] valores = { 50, 30, 70, 20, 40, 60, 80, 10 };
        for (int v : valores) {
            arbol.insertar(v);
        }

        System.out.println("===== Arbol Binario de Busqueda =====");
        System.out.println("Tamanio: " + arbol.tamanio());
        System.out.println("Altura:  " + arbol.altura());
        System.out.println("Minimo:  " + arbol.minimo());
        System.out.println("Maximo:  " + arbol.maximo());
        System.out.println("Hojas:   " + arbol.contarHojas());

        System.out.println("\n--- Representacion visual (rotada 90 grados) ---");
        arbol.imprimirArbol();

        System.out.println("\n--- Recorridos ---");
        System.out.print("InOrden    (ascendente): ");
        arbol.inOrden();

        System.out.print("PreOrden   (raiz primero): ");
        arbol.preOrden();

        System.out.print("PostOrden  (raiz al final): ");
        arbol.postOrden();

        System.out.print("Por niveles (BFS):         ");
        arbol.recorridoPorNiveles();

        System.out.println("\n--- Busquedas ---");
        System.out.println("Contiene 40? " + arbol.contiene(40));
        System.out.println("Contiene 99? " + arbol.contiene(99));

        System.out.println("\n--- Eliminacion ---");
        System.out.println("Eliminando 20 (nodo con 1 hijo)...");
        arbol.eliminar(20);
        System.out.print("InOrden tras eliminar 20: ");
        arbol.inOrden();

        System.out.println("Eliminando 30 (nodo con 2 hijos)...");
        arbol.eliminar(30);
        System.out.print("InOrden tras eliminar 30: ");
        arbol.inOrden();

        System.out.println("Eliminando 50 (raiz)...");
        arbol.eliminar(50);
        System.out.print("InOrden tras eliminar la raiz: ");
        arbol.inOrden();

        System.out.println("\n--- Estado final ---");
        arbol.imprimirArbol();
        System.out.println("Tamanio final: " + arbol.tamanio());
        System.out.println("Altura final:  " + arbol.altura());

        ArbolBinarioBusqueda arbol2 = new ArbolBinarioBusqueda();
        int[] valoresP1 = { 50, 30, 70, 20, 40, 60, 80, 10 };
        for (int v : valoresP1) {
            arbol2.insertar(v);
        }

        System.out.println("\n===== Problema 1: contarNodos() recursivo =====");

        int contado = arbol2.contarNodos();
        int tamAntes = arbol2.tamanio();
        System.out.println("Arbol inicial (8 nodos):");
        System.out.println("  contarNodos() = " + contado);
        System.out.println("  tamanio()     = " + tamAntes);
        System.out.println("  Coinciden?    " + (contado == tamAntes));

        arbol2.insertar(25);
        contado = arbol2.contarNodos();
        int tamDespuesInsertar = arbol2.tamanio();
        System.out.println("\nTras insertar 25 (9 nodos):");
        System.out.println("  contarNodos() = " + contado);
        System.out.println("  tamanio()     = " + tamDespuesInsertar);
        System.out.println("  Coinciden?    " + (contado == tamDespuesInsertar));

        arbol2.eliminar(25);
        contado = arbol2.contarNodos();
        int tamDespuesEliminar = arbol2.tamanio();
        System.out.println("\nTras eliminar 25 (8 nodos):");
        System.out.println("  contarNodos() = " + contado);
        System.out.println("  tamanio()     = " + tamDespuesEliminar);
        System.out.println("  Coinciden?    " + (contado == tamDespuesEliminar));

        ArbolBinarioBusqueda arbolVacio = new ArbolBinarioBusqueda();
        System.out.println("\nArbol vacio:");
        System.out.println("  contarNodos() = " + arbolVacio.contarNodos());
        System.out.println("  tamanio()     = " + arbolVacio.tamanio());


        ArbolBinarioBusqueda arbolUnNodo = new ArbolBinarioBusqueda();
        arbolUnNodo.insertar(42);
        System.out.println("\nArbol de un solo nodo (42):");
        System.out.println("  contarNodos() = " + arbolUnNodo.contarNodos());
        System.out.println("  tamanio()     = " + arbolUnNodo.tamanio());

        System.out.println("\n===== Problema 2: esBalanceado() =====");
        System.out.println("¿El arbol principal esta balanceado? " + arbol.esBalanceado()); // Deberia ser true o false segun las eliminaciones previas

        System.out.println("¿El arbol2 (valores iniciales) esta balanceado? " + arbol2.esBalanceado());

   
        ArbolBinarioBusqueda arbolDesbalanceado = new ArbolBinarioBusqueda();
        arbolDesbalanceado.insertar(1);
        arbolDesbalanceado.insertar(2);
        arbolDesbalanceado.insertar(3);
        arbolDesbalanceado.insertar(4);
        arbolDesbalanceado.insertar(5);

        System.out.println("\nArbol lineal desbalanceado (1 -> 2 -> 3 -> 4 -> 5):");
        arbolDesbalanceado.imprimirArbol();
        System.out.println("¿Esta balanceado? " + arbolDesbalanceado.esBalanceado()); // Debe ser false
        
     // Problema 3 — Validar que sea un BST
        System.out.println("\n===== Problema 3: esBSTValido() =====");
        
        System.out.println("¿El árbol 2 es un BST válido?: " + arbol2.esBSTValido()); // Debe ser true

        ArbolBinarioBusqueda arbolRoto = new ArbolBinarioBusqueda();
        arbolRoto.insertar(50); 

        Nodo nodoIzquierdoInvalido = new Nodo(60); 
        Nodo nodoDerechoValido = new Nodo(70);
        arbolRoto.getRaiz().izquierdo = nodoIzquierdoInvalido;
        arbolRoto.getRaiz().derecho = nodoDerechoValido;

        System.out.println("\nEstructura del árbol alterado artificialmente:");
        arbolRoto.imprimirArbol();
        System.out.println("¿El árbol alterado es un BST válido?: " + arbolRoto.esBSTValido()); // Debe ser false
        
     // Problema 4 — Ancestro común más bajo (LCA)
        System.out.println("\n===== Problema 4: ancestroComunMasBajo() =====");
        System.out.println("Estructura del árbol 2 para referencia de LCA:");
        arbol2.imprimirArbol();

        System.out.println("LCA de 10 y 40 (Debe ser 30): " + arbol2.ancestroComunMasBajo(10, 40));
        System.out.println("LCA de 10 y 80 (Debe ser 50): " + arbol2.ancestroComunMasBajo(10, 80));
        System.out.println("LCA de 60 y 80 (Debe ser 70): " + arbol2.ancestroComunMasBajo(60, 80));

        try {
            System.out.print("Intentando buscar LCA con un nodo inexistente (99)... ");
            arbol2.ancestroComunMasBajo(10, 99);
        } catch (IllegalArgumentException e) {
            System.out.println("\nExcepción cachada con éxito: " + e.getMessage());
    }
    
   }
}

