package Estructuras_de_datos;

public class Cola<T> {
    private Nodo<T> head = null;
    private Nodo<T> tail = null;
    private int size = 0;

    public void enqueue(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        if (head == null) {
            head = tail = nuevoNodo;
        } else {
            tail.siguiente = nuevoNodo;
            tail = nuevoNodo;
        }
        size++;
    }
    public T dequeue() {
        if (head == null)
            return null;
        T dato = head.dato;
        head = head.siguiente;
        if (head == null)
            tail = null;
        size--;
        return dato;
    }
    public T getHead() {
        return (head == null) ? null : head.dato;
    }
    public T getTail() {
        return (head == null) ? null : tail.dato;
    }
    public int getSize() {
        return size;
    }
    public void show() {
        Nodo<T> tmp = head;
        while (tmp != null) {
            System.out.print(tmp.dato + " -> ");
            tmp = tmp.siguiente;
        }
        System.out.println("null");
    }
}
