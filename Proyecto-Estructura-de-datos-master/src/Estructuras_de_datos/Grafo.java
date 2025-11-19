package Estructuras_de_datos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import Estructuras_de_datos.Cola;


// Arista o Arco
@SuppressWarnings("unused")
class Arista {
    Object peso = null;
    Arista siguiente = null;
    Vertice destino = null;

    public Arista(Vertice destino, Object peso) {
        this.destino = destino;
        this.peso = peso;
    }

    public Arista(Vertice destino) {
        this.destino = destino;
    }
}

// Lista de Adyacencia
class ListaAdyacencia {
    Arista primera = null;
    Arista ultima = null;

    private boolean esVacia() {
        return primera == null;
    }

    private boolean buscarAdyacencia(Vertice destino) {
        Arista temporal = primera;
        while (temporal != null) {
            if (temporal.destino.toString().equals(destino.toString()))
                return true;
            temporal = temporal.siguiente;
        }
        return false;
    }

    public void agregar(Vertice destino) {
        if (!buscarAdyacencia(destino))
            agregarArista(new Arista(destino));
    }

    public void agregar(Vertice destino, Object peso) {
        if (!buscarAdyacencia(destino))
            agregarArista(new Arista(destino, peso));
    }

    private void agregarArista(Arista nuevaArista) {
        if (esVacia()) {
            primera = nuevaArista;
            ultima = nuevaArista;
            return;
        }
        String dato = nuevaArista.destino.toString();
        if (dato.compareTo(primera.destino.toString()) < 0) {
            nuevaArista.siguiente = primera;
            primera = nuevaArista;
            return;
        }

        if (dato.compareTo(primera.destino.toString()) > 0) {
            ultima.siguiente = nuevaArista;
            ultima = nuevaArista;
            return;
        }

        Arista temporal = primera;
        while (temporal.siguiente != null &&
                dato.compareTo(temporal.destino.toString()) > 0)
            temporal = temporal.siguiente;

        nuevaArista.siguiente = temporal.siguiente;
        temporal.siguiente = nuevaArista;
    }

    public void eliminar(Vertice destino) {
        if (esVacia())
            return;

        if (primera.destino.toString().equals(destino.toString())) {
            primera = primera.siguiente;
            if (primera == null)
                ultima = null;
            return;
        }

        Arista temporal = primera;
        while (temporal.siguiente != null &&
                temporal.siguiente.destino.toString().equals(destino.toString())) {
            temporal = temporal.siguiente;
        }

        if (temporal.siguiente != null) {
            temporal.siguiente = temporal.siguiente.siguiente;
            if (temporal.siguiente == null)
                ultima = temporal;
        }
    }
}

public class Grafo {
    private Vertice primero = null;
    private Vertice ultimo = null;

    public void agregarArista(Object origen, Object destino) {
        Vertice verticeOrigen = buscarVertice(origen);
        Vertice verticeDestino = buscarVertice(destino);
        if (verticeOrigen != null && verticeDestino != null)
            verticeOrigen.listaAdyacencia.agregar(verticeDestino);
    }

    public void agregarArista(Object origen, Object destino, Object peso) {
        Vertice verticeOrigen = buscarVertice(origen);
        Vertice verticeDestino = buscarVertice(destino);
        if (verticeOrigen != null && verticeDestino != null)
            verticeOrigen.listaAdyacencia.agregar(verticeDestino, peso);
    }

    public void eliminarArista(Object origen, Object destino) {
        Vertice verticeOrigen = buscarVertice(origen);
        Vertice verticeDestino = buscarVertice(destino);
        if (verticeOrigen != null && verticeDestino != null)
            verticeOrigen.listaAdyacencia.eliminar(verticeDestino);
    }

    public void agregarVertice(Object dato) {
        if (buscarVertice(dato) != null)
            return;

        Vertice nuevoVertice = new Vertice(dato);
        if (esVacio()) {
            primero = nuevoVertice;
            ultimo = nuevoVertice;
            return;
        }
        String nuevoDato = dato.toString();
        if (nuevoDato.compareTo(primero.toString()) < 0) {
            nuevoVertice.siguiente = primero;
            primero = nuevoVertice;
            return;
        }

        if (nuevoDato.compareTo(primero.toString()) > 0) {
            ultimo.siguiente = nuevoVertice;
            ultimo = nuevoVertice;
            return;
        }

        Vertice temporal = primero;
        while (temporal.siguiente != null &&
                nuevoDato.compareTo(temporal.toString()) > 0)
            temporal = temporal.siguiente;

        nuevoVertice.siguiente = temporal.siguiente;
        temporal.siguiente = nuevoVertice;
    }

    public void eliminarVertice(Object dato) {
        if (esVacio())
            return;

        Vertice verticeBorrar = null;

        if (primero.toString().equals(dato.toString())) {
            verticeBorrar = primero;
            primero = primero.siguiente;
            if (primero == null)
                ultimo = null;
        } else {
            Vertice temporal = primero;
            while (temporal.siguiente != null &&
                    temporal.siguiente.toString().equals(dato.toString())) {
                temporal = temporal.siguiente;
            }

            if (temporal.siguiente != null) {
                verticeBorrar = temporal.siguiente;
                temporal.siguiente = temporal.siguiente.siguiente;
                if (temporal.siguiente == null)
                    ultimo = temporal;
            }
        }

        if (verticeBorrar != null) {
            Vertice temporal = primero;
            while (temporal != null) {
                temporal.listaAdyacencia.eliminar(verticeBorrar);
                temporal = temporal.siguiente;
            }
        }

    }

    private boolean esVacio() {
        return primero == null;
    }

    private Vertice buscarVertice(Object dato) {
        Vertice temporal = primero;
        while (temporal != null) {
            if (temporal.toString().equals(dato.toString()))
                return temporal;
            temporal = temporal.siguiente;
        }
        return null;
    }

    public void recorridoAnchura(Object dato) {
        Vertice vertice = buscarVertice(dato);
        if (vertice == null)
            return;

        HashSet<Vertice> visitados = new HashSet<>();
        visitados.add(vertice);
        Cola<Vertice> cola = new Cola<>();
        cola.enqueue(vertice);

        while (cola.getSize() > 0) {
            Vertice verticeActual = cola.dequeue();
            System.out.print(verticeActual + ",");
            Arista temporal = verticeActual.listaAdyacencia.primera;
            while (temporal != null) {
                if (!visitados.contains(temporal.destino)) {
                    visitados.add(temporal.destino);
                    cola.enqueue(temporal.destino);
                }
                temporal = temporal.siguiente;
            }
        }
        System.out.println();
    }

    public void recorridoProfundidad(Object dato) {
        Vertice vertice = buscarVertice(dato);
        if (vertice == null)
            return;

        HashSet<Vertice> visitados = new HashSet<>();
        recorridoProfundidad(vertice, visitados);
        System.out.println();
    }

    private void recorridoProfundidad(Vertice vertice, HashSet<Vertice> visitados) {
        System.out.print(vertice.toString() + " ");
        visitados.add(vertice);

        Arista aristaActual = vertice.listaAdyacencia.primera;
        while (aristaActual != null) {
            if (!visitados.contains(aristaActual.destino)) {
                recorridoProfundidad(aristaActual.destino, visitados);
            }
            aristaActual = aristaActual.siguiente;
        }
    }

    public HashMap<Vertice, Double> Dijkstra(Object origen) {
        Vertice verticeOrigen = buscarVertice(origen);
        if (verticeOrigen == null) {
            return null;
        }

        HashMap<Vertice, Boolean> visitados = new HashMap<>();
        HashMap<Vertice, Double> distancias = new HashMap<>();

        // Inicializa los mapas de distancias y visitados
        Vertice temporal = primero;
        while (temporal != null) {
            distancias.put(temporal, Double.POSITIVE_INFINITY);
            visitados.put(temporal, false);
            temporal = temporal.siguiente;
        }

        // La distancia al vértice de origen es 0
        distancias.put(verticeOrigen, 0.0);

        // Inicializa la cola de prioridad por distancias
        ColaConPrioridad<Vertice> cola = new ColaConPrioridad<>(
                Comparator.comparing(distancias::get));
        cola.encolar(verticeOrigen);

        while (!cola.estaVacia()) {
            Vertice actual = cola.desencolar();

            if (visitados.get(actual)) {
                continue; // Si ya fue visitado, saltar
            }

            visitados.put(actual, true);

            // Recorre las aristas adyacentes y actualiza las distancias
            Arista aristaActual = actual.listaAdyacencia.primera;
            while (aristaActual != null) {
                if (!visitados.get(aristaActual.destino)) {

                    double nuevaDistancia = distancias.get(actual) + (double) aristaActual.peso;

                    if (nuevaDistancia < distancias.get(aristaActual.destino)) {
                        distancias.put(aristaActual.destino, nuevaDistancia);
                        cola.encolar(aristaActual.destino);
                    }
                }
                aristaActual = aristaActual.siguiente;
            }
        }

        return distancias;
    }

    public void mostrarDistanciasDijkstra(Object origen) {
        HashMap<Vertice, Double> distancias = Dijkstra(origen);
        if (distancias != null) {
            System.out.println("Distancias desde: " + origen.toString());
            for (Map.Entry<Vertice, Double> entry : distancias.entrySet()) {
                System.out.println(entry.getKey().dato + ":" + entry.getValue());
            }
        }
    }

    public void mostrarDistanciaBellmanFord(Object origen) {
        HashMap<Vertice, Double> distancias = BellmanFord(origen);
        if (distancias != null) {
            System.out.println("Distancias desde: " + origen.toString());
            for (Map.Entry<Vertice, Double> entry : distancias.entrySet()) {
                System.out.println(entry.getKey().dato + ":" + entry.getValue());
            }
        }
    }

    public HashMap<Vertice, Double> BellmanFord(Object origen) {
        Vertice verticeOrigen = buscarVertice(origen);
        if (verticeOrigen == null) {
            return null;
        }

        HashMap<Vertice, Double> distancias = new HashMap<>();

        // Inicializa los mapas de distancias
        Vertice temporal = primero;
        while (temporal != null) {
            distancias.put(temporal, Double.POSITIVE_INFINITY);
            temporal = temporal.siguiente;
        }

        // La distancia al vértice de origen es 0
        distancias.put(verticeOrigen, 0.0);

        // Relaja las aristas n-1 veces
        for (int i = 1; i < distancias.size(); i++) {
            temporal = primero;
            while (temporal != null) {
                Arista aristaActual = temporal.listaAdyacencia.primera;
                while (aristaActual != null) {
                    if (distancias.get(temporal) + (double) aristaActual.peso < distancias.get(aristaActual.destino)) {
                        distancias.put(aristaActual.destino, distancias.get(temporal) + (double) aristaActual.peso);
                    }
                    aristaActual = aristaActual.siguiente;
                }
                temporal = temporal.siguiente;
            }
        }

        // Verifica si hay ciclos negativos
        temporal = primero;
        while (temporal != null) {
            Arista aristaActual = temporal.listaAdyacencia.primera;
            while (aristaActual != null) {
                if (distancias.get(temporal) + (double) aristaActual.peso < distancias.get(aristaActual.destino)) {
                    return null; // Hay un ciclo negativo
                }
                aristaActual = aristaActual.siguiente;
            }
            temporal = temporal.siguiente;
        }

        return distancias;
    }

    public ArrayList<ArrayList<Double>> FloydWarshall() {
        ArrayList<Vertice> vertices = new ArrayList<>();
        Vertice actual = primero;

        // Crear una lista de vértices
        while (actual != null) {
            vertices.add(actual);
            actual = actual.siguiente;
        }

        int n = vertices.size();
        ArrayList<ArrayList<Double>> distancias = new ArrayList<>();

        // Inicializar la matriz de distancias
        for (int i = 0; i < n; i++) {
            ArrayList<Double> fila = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    fila.add(0.0); // Distancia de un vértice a sí mismo es 0
                } else {
                    fila.add(Double.POSITIVE_INFINITY); // Inicialmente, las distancias son infinitas
                }
            }
            distancias.add(fila);
        }

        // Rellenar la matriz con las distancias iniciales (pesos de las aristas)
        for (int i = 0; i < n; i++) {
            Arista aristaActual = vertices.get(i).listaAdyacencia.primera;
            while (aristaActual != null) {
                int j = vertices.indexOf(aristaActual.destino);
                distancias.get(i).set(j, (double) aristaActual.peso);
                aristaActual = aristaActual.siguiente;
            }
        }

        // Aplicar el algoritmo de Floyd–Warshall
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (distancias.get(i).get(k) != Double.POSITIVE_INFINITY &&
                            distancias.get(k).get(j) != Double.POSITIVE_INFINITY) {

                        double nuevo = distancias.get(i).get(k) + distancias.get(k).get(j);

                        if (nuevo < distancias.get(i).get(j)) {
                            distancias.get(i).set(j, nuevo);
                        }
                    }
                }
            }
        }

        return distancias;
    }

    public void mostrarDistanciasFloydWarshall() {
        ArrayList<ArrayList<Double>> distancias = FloydWarshall();
        ArrayList<Vertice> vertices = new ArrayList<>();
        Vertice temp = primero;
        while (temp != null) {
            vertices.add(temp);
            temp = temp.siguiente;
        }

        int n = vertices.size();

        // Encabezado
        System.out.print("\t");
        for (Vertice v : vertices) {
            System.out.print(v.dato + "\t");
        }
        System.out.println();

        // Filas
        for (int i = 0; i < n; i++) {
            System.out.print(vertices.get(i).dato + "\t");
            for (int j = 0; j < n; j++) {
                double valor = distancias.get(i).get(j);
                if (valor == Double.POSITIVE_INFINITY) {
                    System.out.print("INF\t");
                } else {
                    System.out.print(valor + "\t");
                }
            }
            System.out.println();
        }
    }

}
