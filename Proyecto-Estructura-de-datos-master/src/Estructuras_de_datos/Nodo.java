package Estructuras_de_datos;

class Nodo<T> {
    T dato;
    Nodo<T> siguiente;
    Nodo<T> anterior;

    public Nodo(T dato) {
        this.dato = dato;
        this.siguiente = null;
        this.anterior = null;
    }
}