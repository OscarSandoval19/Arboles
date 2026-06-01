# Tarea: Árbol Binario de Búsqueda (BST) en Java

**Curso:** Programación 3  
**Tema:** Estructuras de datos no lineales — Árboles  
**Modalidad:** Individual  
**Entrega:** Repositorio Git con el código modificado + capturas de ejecución

---

## 1. Objetivo

Comprender, completar y extender una implementación **manual** de un Árbol Binario de Búsqueda (BST) en Java, **sin usar `java.util` ni librerías externas**, siguiendo el mismo estilo con el que ya implementamos `queue` y `listas` enlazadas en clase.

Al finalizar la tarea el estudiante debe ser capaz de:

- Explicar la propiedad fundamental de un BST.
- Implementar inserción, búsqueda, eliminación y recorridos de forma recursiva.
- Reconocer los 3 casos de eliminación de un nodo en un BST.
- Implementar algoritmos clásicos sobre árboles (altura, balanceo, validación, LCA, espejo, etc.).

---

## 2. Proyecto base

El proyecto Maven `arboles/` ya contiene una base funcional:

```
arboles/
├── pom.xml
└── src/main/java/umg/edu/progra/arboles/
    ├── Nodo.java
    ├── ArbolBinarioBusqueda.java
    └── Principal.java
```

### Lo que YA está implementado

- `Nodo` con `dato`, `izquierdo` y `derecho`.
- `ArbolBinarioBusqueda` con:
  - `insertar(int)`
  - `buscar(int)` / `contiene(int)`
  - `eliminar(int)` cubriendo los 3 casos clásicos.
  - `minimo()`, `maximo()`, `altura()`, `tamanio()`, `contarHojas()`.
  - Recorridos: `inOrden()`, `preOrden()`, `postOrden()`, `recorridoPorNiveles()` (BFS con una cola casera, **sin `java.util`**).
  - `imprimirArbol()` (impresión visual rotada 90°).
- `Principal` con un ejemplo completo de uso.

---

## 3. Cómo ejecutar el proyecto

Desde la carpeta `arboles/`:

```bash
mvn compile
java -cp target/classes umg.edu.progra.arboles.Principal
```

O abriéndolo como proyecto Maven en Eclipse y ejecutando la clase `Principal`.

Salida esperada (resumen):

```
===== Arbol Binario de Busqueda =====
Tamanio: 8
Altura:  3
Minimo:  10
Maximo:  80
Hojas:   4
...
InOrden    (ascendente): 10 20 30 40 50 60 70 80
PreOrden   (raiz primero): 50 30 20 10 40 70 60 80
PostOrden  (raiz al final): 10 20 40 30 60 80 70 50
Por niveles (BFS):         50 30 70 20 40 60 80 10
```

---

## 4. Reglas obligatorias

> El incumplimiento de cualquiera de estas reglas invalida la tarea.

1. **Prohibido usar `java.util.*`** (ni `ArrayList`, ni `LinkedList`, ni `Queue`, ni `Stack`, ni `HashMap`, ni `Arrays`, etc.).
2. **Prohibido usar cualquier librería externa** para la estructura del árbol.
3. Si necesitan una estructura auxiliar (cola, pila, lista), deben implementarla manualmente como ya se hizo con `ColaNodos` dentro de `ArbolBinarioBusqueda`.
4. Toda la lógica nueva debe estar en la clase `ArbolBinarioBusqueda` (o en clases auxiliares dentro del mismo paquete `umg.edu.progra.arboles`).
5. Cada método nuevo debe probarse desde la clase `Principal`.
6. El código debe compilar con `mvn compile` sin errores ni warnings.

---

## 5. Problemas a resolver

Implementar los siguientes métodos en la clase `ArbolBinarioBusqueda` y demostrar su funcionamiento desde `Principal`.

### Problema 1 — Contar nodos recursivamente

Implementar:

```java
public int contarNodos();
```

- Debe devolver la cantidad total de nodos del árbol **usando recursividad**.
- **NO** puede usar el campo `tamanio` ya existente.
- Validar que su resultado coincida con `tamanio()` antes y después de insertar/eliminar.

### Problema 2 — ¿Está balanceado?

Implementar:

```java
public boolean esBalanceado();
```

- Un árbol está balanceado si, para **cada nodo**, la diferencia de altura entre su subárbol izquierdo y derecho es `<= 1`.
- Probarlo con un árbol balanceado y con uno claramente desbalanceado (por ejemplo, insertando 1, 2, 3, 4, 5 en ese orden).

### Problema 3 — Validar que sea un BST

Implementar:

```java
public boolean esBSTValido();
```

- Debe verificar que el árbol cumple la propiedad de BST (todo el subárbol izquierdo `<` raíz, todo el subárbol derecho `>` raíz).
- **Pista:** una solución limpia es pasar un rango `(min, max)` permitido en cada llamada recursiva.
- Probarlo en el árbol generado por `Principal`. Debe retornar `true`.
- Para demostrar el caso `false`, construir manualmente un árbol "roto" (modificando nodos directamente) y validar que devuelve `false`.

### Problema 4 — Ancestro común más bajo (LCA)

Implementar:

```java
public int ancestroComunMasBajo(int a, int b);
```

- Debe devolver el dato del nodo que es el **ancestro común más bajo** (Lowest Common Ancestor) de los valores `a` y `b`.
- Aprovechar la propiedad del BST: si ambos valores son menores que el actual → ir a la izquierda; si ambos son mayores → ir a la derecha; en caso contrario, el nodo actual es el LCA.
- Si `a` o `b` no existen en el árbol, lanzar `IllegalArgumentException`.
- Ejemplo con el árbol de `Principal`:
  - `lca(10, 40)` → `30`
  - `lca(10, 80)` → `50`
  - `lca(60, 80)` → `70`

### Problema 5 — Espejo del árbol (inversión)

Implementar:

```java
public void invertir();
```

- Debe intercambiar `izquierdo` y `derecho` en **todos** los nodos del árbol (reflejo / espejo).
- Antes de invertir, mostrar el árbol con `imprimirArbol()` e `inOrden()`.
- Después de invertir, volver a mostrarlos. El `inOrden` original ya **no** estará ordenado (se invierte).

---

## 6. Ejercicios extra (opcionales, suman puntos)

Solo cuentan si los 5 problemas anteriores están correctos.

- **E1.** `int kEsimoMenor(int k)` — devuelve el k-ésimo valor más pequeño usando inOrden.
- **E2.** `void imprimirRangoOrdenado(int min, int max)` — imprime en orden todos los valores en el rango `[min, max]` recorriendo lo menos posible el árbol.
- **E3.** `int diametro()` — el camino más largo (en aristas) entre dos nodos cualesquiera del árbol.
- **E4.** Construir un BST a partir de un arreglo `int[]` recibido por la consola (`args`).

---

## 7. Entregables

1. **Repositorio Git** (GitHub / GitLab) con el proyecto modificado.
2. **Capturas** de la salida en consola que demuestren cada problema resuelto.
3. **README.md propio** del estudiante (puede partir de este) explicando:
   - Cómo compilar y ejecutar.
   - Qué hace cada método nuevo.
   - Un ejemplo de entrada y salida por cada problema.
4. Commits descriptivos por cada problema (por ejemplo: `feat: problema 1 contarNodos recursivo`).

---

## 8. Rúbrica de evaluación (100 pts)

| Criterio                                            | Puntos |
| --------------------------------------------------- | -----: |
| Problema 1 — `contarNodos` recursivo                | 10     |
| Problema 2 — `esBalanceado`                         | 15     |
| Problema 3 — `esBSTValido`                          | 15     |
| Problema 4 — `ancestroComunMasBajo` (LCA)           | 20     |
| Problema 5 — `invertir` (espejo)                    | 15     |
| Pruebas claras en `Principal` (cada método se ve)   | 10     |
| Código limpio, recursivo cuando aplica, sin `util`  | 10     |
| Commits, README propio y capturas                   | 5      |
| **Ejercicios extra (E1–E4)**                        | +10    |

---

## 9. Recomendaciones

- **Piensen recursivamente.** Casi todo en árboles se resuelve definiendo el caso base (nodo `null`) y combinando el resultado de los subárboles izquierdo y derecho.
- **Dibujen el árbol antes de codificar.** Especialmente para `eliminar`, `LCA` e `invertir`.
- **Prueben con árboles vacíos** (`raiz == null`) y con árboles de un solo nodo. La mitad de los errores aparecen en esos bordes.
- **No copien código de internet sin entenderlo.** En la defensa oral se pregunta sobre cualquier línea.

---

> "Un BST bien implementado es más rápido que muchas estructuras prediseñadas… si entienden el porqué."


## 1. Descripción del Proyecto
Este proyecto consiste en la extensión y completación manual de una estructura de datos no lineal: un **Árbol Binario de Búsqueda (BST)** en Java

## 2. Instrucciones de Compilación y Ejecución

El proyecto está gestionado con Maven. Para compilar y ejecutar el código desde la consola de comandos hay que ubicarse en la carpeta raíz del proyecto (`arboles/`) y ejecutar los siguientes comandos:

### Compilación:
mvn compile

java -cp target/classes umg.edu.progra.arboles.Principal

## Métodos Implementados y Solución de Problemas
## Problema 1: Contar Nodos Recursivamente (contarNodos())
Descripción: Devuelve la cantidad total de nodos presentes en el árbol utilizando una estrategia recursiva de "dividir y vencerás". No hace uso de la variable interna tamanio.

Lógica: Si el nodo actual es null, aporta 0 al conteo. De lo contrario, aporta 1 (el nodo actual) más la suma de los nodos devueltos por el subárbol izquierdo y el subárbol derecho.

## Ejemplo de Entrada y Salida:

Entrada: Árbol con raíz 50 e inserciones estándar (8 nodos).

Salida:

Arbol inicial (8 nodos):
  contarNodos() = 8
  tamanio()     = 8
  Coinciden?    true
  
## Problema 2: ¿Está Balanceado? (esBalanceado())
Descripción: Determina si el árbol cumple con la propiedad de estar balanceado (la diferencia de altura entre el subárbol izquierdo y derecho de cualquier nodo es como máximo 1).

Lógica: Para cada nodo, se calcula de forma recursiva la altura de su lado izquierdo y de su lado derecho empleando alturaRecursiva(). Se evalúa que la diferencia absoluta sea <= 1 y que, simultáneamente, sus hijos izquierdo y derecho también estén balanceados.

## Ejemplo de Entrada y Salida:

Entrada: Inserción secuencial de los valores 1, 2, 3, 4, 5.

Salida:

¿Está balanceado?: false

## Problema 3: Validar que sea un BST (esBSTValido())
Descripción: Verifica de manera estricta que todas las ramificaciones del árbol respeten las propiedades de orden de un BST.

Lógica: Para evitar el error clásico de evaluar solo nodos adyacentes de forma aislada, el método arrastra un rango dinámico de valores permitidos (min, max) en cada llamada recursiva. Al avanzar a la izquierda, el valor máximo se actualiza al dato del nodo actual; al avanzar a la derecha, el valor mínimo se actualiza al dato actual.

## Ejemplo de Entrada y Salida:

Entrada: Árbol con raíz 50 alterado artificialmente colocando un 60 en su extremo izquierdo.

Salida:

¿El árbol alterado es un BST válido?: false

## Problema 4: Ancestro Común Más Bajo (ancestroComunMasBajo(a, b))
Descripción: Encuentra el nodo más profundo que comparte la paternidad o descendencia de dos valores dados (a y b). Lanza una excepción si alguno no existe en el árbol.

Lógica: Se valida primero la existencia de ambos con contiene(). Luego, aprovechando las propiedades del BST: si ambos valores son menores al nodo actual, se busca recursivamente a la izquierda; si ambos son mayores, se busca a la derecha. En el momento en que los caminos se dividen, ese nodo actual es el LCA.

## Ejemplo de Entrada y Salida:

Entrada: Valores 10 y 40 en el árbol base.

Salida: 30

Entrada: Valores 60 y 80 en el árbol base.

Salida: 70

## Problema 5: Espejo del Árbol / Inversión (invertir())
Descripción: Transforma el árbol invirtiendo físicamente los punteros de cada nodo, logrando un efecto espejo completo.

Lógica: Mediante un recorrido recursivo, se intercambia la referencia del hijo izquierdo con la del hijo derecho utilizando una variable temporal, repitiendo el proceso en cascada para todo el árbol. Esto provoca que el recorrido inOrden resulte invertido de forma descendente.

## Ejemplo de Entrada y Salida:

Entrada: Árbol ordenado balanceado.

Salida:

--- ANTES de invertir ---
Recorrido InOrden: 20 30 40 50 60 70 80 

--- DESPUÉS de invertir ---
Recorrido InOrden: 80 70 60 50 40 30 20
