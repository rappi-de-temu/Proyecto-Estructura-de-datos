package Estructuras_de_datos;

// Vértice o Nodo
@SuppressWarnings("unused")
public class Vertice {
    Object dato;
    Vertice siguiente = null;
    ListaAdyacencia listaAdyacencia = new ListaAdyacencia();

    public Vertice(Object dato) {
        this.dato = dato;
    }

    public String toString() {
        return dato.toString();
    }
}