package umg.edu.progra.arboles;

/**
 * Arbol Binario de Busqueda (BST) implementado manualmente,
 * sin utilizar java.util ni librerias externas.
 *
 * Reglas del BST:
 *  - Para cada nodo N, todos los valores del subarbol izquierdo
 *    son MENORES que N.dato.
 *  - Para cada nodo N, todos los valores del subarbol derecho
 *    son MAYORES que N.dato.
 *  - No se permiten duplicados (se ignoran al insertar).
 *
 * @author Walter Cordova
 */
public class ArbolBinarioBusqueda {

    private Nodo raiz;
    private int tamanio;

    public ArbolBinarioBusqueda() {
        this.raiz = null;
        this.tamanio = 0;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    public int tamanio() {
        return tamanio;
    }

  
    public void insertar(int valor) {
        if (raiz == null) {
            raiz = new Nodo(valor);
            tamanio++;
            return;
        }
        raiz = insertarRecursivo(raiz, valor);
    }

    private Nodo insertarRecursivo(Nodo actual, int valor) {
        if (actual == null) {
            tamanio++;
            return new Nodo(valor);
        }
        if (valor < actual.dato) {
            actual.izquierdo = insertarRecursivo(actual.izquierdo, valor);
        } else if (valor > actual.dato) {
            actual.derecho = insertarRecursivo(actual.derecho, valor);
        }
        return actual;
    }


    public Nodo buscar(int valor) {
        return buscarRecursivo(raiz, valor);
    }

    private Nodo buscarRecursivo(Nodo actual, int valor) {
        if (actual == null) {
            return null;
        }
        if (valor == actual.dato) {
            return actual;
        }
        if (valor < actual.dato) {
            return buscarRecursivo(actual.izquierdo, valor);
        }
        return buscarRecursivo(actual.derecho, valor);
    }

    public boolean contiene(int valor) {
        return buscar(valor) != null;
    }
    
    public boolean eliminar(int valor) {
        int tamanioPrevio = tamanio;
        raiz = eliminarRecursivo(raiz, valor);
        return tamanio < tamanioPrevio;
    }

    private Nodo eliminarRecursivo(Nodo actual, int valor) {
        if (actual == null) {
            return null;
        }
        if (valor < actual.dato) {
            actual.izquierdo = eliminarRecursivo(actual.izquierdo, valor);
        } else if (valor > actual.dato) {
            actual.derecho = eliminarRecursivo(actual.derecho, valor);
        } else {
      
            if (actual.izquierdo == null && actual.derecho == null) {
                tamanio--;
                return null;
            }
            if (actual.izquierdo == null) {
                tamanio--;
                return actual.derecho;
            }
            if (actual.derecho == null) {
                tamanio--;
                return actual.izquierdo;
            }
      
            int sucesor = minimo(actual.derecho);
            actual.dato = sucesor;
            actual.derecho = eliminarRecursivo(actual.derecho, sucesor);
        }
        return actual;
    }

   
    public int minimo() {
        if (raiz == null) {
            throw new IllegalStateException("El arbol esta vacio");
        }
        return minimo(raiz);
    }

    private int minimo(Nodo nodo) {
        Nodo actual = nodo;
        while (actual.izquierdo != null) {
            actual = actual.izquierdo;
        }
        return actual.dato;
    }

   
    public int maximo() {
        if (raiz == null) {
            throw new IllegalStateException("El arbol esta vacio");
        }
        Nodo actual = raiz;
        while (actual.derecho != null) {
            actual = actual.derecho;
        }
        return actual.dato;
    }


    public int altura() {
        return alturaRecursiva(raiz);
    }

    private int alturaRecursiva(Nodo nodo) {
        if (nodo == null) {
            return -1;
        }
        int izq = alturaRecursiva(nodo.izquierdo);
        int der = alturaRecursiva(nodo.derecho);
        return 1 + (izq > der ? izq : der);
    }

   
    public int contarHojas() {
        return contarHojasRecursivo(raiz);
    }

    private int contarHojasRecursivo(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        if (nodo.izquierdo == null && nodo.derecho == null) {
            return 1;
        }
        return contarHojasRecursivo(nodo.izquierdo) + contarHojasRecursivo(nodo.derecho);
    }

    public void inOrden() {
        inOrdenRecursivo(raiz);
        System.out.println();
    }

    private void inOrdenRecursivo(Nodo nodo) {
        if (nodo == null) {
            return;
        }
        inOrdenRecursivo(nodo.izquierdo);
        System.out.print(nodo.dato + " ");
        inOrdenRecursivo(nodo.derecho);
    }

    public void preOrden() {
        preOrdenRecursivo(raiz);
        System.out.println();
    }

    private void preOrdenRecursivo(Nodo nodo) {
        if (nodo == null) {
            return;
        }
        System.out.print(nodo.dato + " ");
        preOrdenRecursivo(nodo.izquierdo);
        preOrdenRecursivo(nodo.derecho);
    }

    public void postOrden() {
        postOrdenRecursivo(raiz);
        System.out.println();
    }

    private void postOrdenRecursivo(Nodo nodo) {
        if (nodo == null) {
            return;
        }
        postOrdenRecursivo(nodo.izquierdo);
        postOrdenRecursivo(nodo.derecho);
        System.out.print(nodo.dato + " ");
    }

   
    public void recorridoPorNiveles() {
        if (raiz == null) {
            System.out.println();
            return;
        }
        ColaNodos cola = new ColaNodos();
        cola.encolar(raiz);
        while (!cola.estaVacia()) {
            Nodo actual = cola.desencolar();
            System.out.print(actual.dato + " ");
            if (actual.izquierdo != null) {
                cola.encolar(actual.izquierdo);
            }
            if (actual.derecho != null) {
                cola.encolar(actual.derecho);
            }
        }
        System.out.println();
    }

    public void imprimirArbol() {
        if (raiz == null) {
            System.out.println("(arbol vacio)");
            return;
        }
        imprimirArbolRecursivo(raiz, 0);
    }

    private void imprimirArbolRecursivo(Nodo nodo, int nivel) {
        if (nodo == null) {
            return;
        }
        imprimirArbolRecursivo(nodo.derecho, nivel + 1);
        for (int i = 0; i < nivel; i++) {
            System.out.print("     ");
        }
        System.out.println("-> " + nodo.dato);
        imprimirArbolRecursivo(nodo.izquierdo, nivel + 1);
    }

    public int contarNodos() {
        return contarNodosRecursivo(raiz);
    }

    private int contarNodosRecursivo(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + contarNodosRecursivo(nodo.izquierdo)
                 + contarNodosRecursivo(nodo.derecho);
    }


    private static class NodoCola {
        Nodo valor;
        NodoCola siguiente;

        NodoCola(Nodo valor) {
            this.valor = valor;
        }
    }

    private static class ColaNodos {
        private NodoCola frente;
        private NodoCola fondo;

        boolean estaVacia() {
            return frente == null;
        }

        void encolar(Nodo n) {
            NodoCola nuevo = new NodoCola(n);
            if (frente == null) {
                frente = fondo = nuevo;
            } else {
                fondo.siguiente = nuevo;
                fondo = nuevo;
            }
        }

        Nodo desencolar() {
            if (frente == null) {
                throw new IllegalStateException("Cola vacia");
            }
            Nodo valor = frente.valor;
            frente = frente.siguiente;
            if (frente == null) {
                fondo = null;
            }
            return valor;
        }
    }
    public boolean esBalanceado() {
        return esBalanceadoRecursivo(raiz);
    }
// Problema 2: Determina si el arbol esta balanceado.
    private boolean esBalanceadoRecursivo(Nodo nodo) {
        if (nodo == null) {
            return true;
        }

        int alturaIzq = alturaRecursiva(nodo.izquierdo);
        int alturaDer = alturaRecursiva(nodo.derecho);

        int diferencia = alturaIzq - alturaDer;
        if (diferencia < 0) {
            diferencia = -diferencia;
        }


        if (diferencia <= 1 && esBalanceadoRecursivo(nodo.izquierdo) && esBalanceadoRecursivo(nodo.derecho)) {
            return true;
        }

        return false;
    }
    // Problema 3: Verifica si el arbol cumple con las propiedades de un BST valido
    public boolean esBSTValido() {
        return esBSTValidoRecursivo(raiz, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean esBSTValidoRecursivo(Nodo nodo, int min, int max) {
        if (nodo == null) {
            return true;
        }
        if (nodo.leerDato() <= min || nodo.leerDato() >= max) {
            return false;
        }
        
        return esBSTValidoRecursivo(nodo.izquierdo, min, nodo.leerDato()) 
            && esBSTValidoRecursivo(nodo.derecho, nodo.leerDato(), max);
    }
}
