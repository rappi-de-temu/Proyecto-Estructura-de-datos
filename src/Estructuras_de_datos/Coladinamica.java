package Estructuras_de_datos;

import java.util.ArrayList;

public class Coladinamica<T> {
    private ArrayList<T> cola;

    public Coladinamica() {
        cola = new ArrayList<>();
    }

    // Encolar elemento (agregar al final)
    public void enqueue(T elemento) {
        cola.add(elemento);
        System.out.println("Elemento " + elemento + " encolado.");
    }

    // Desencolar elemento (eliminar del frente)
    public T dequeue() {
        if (isEmpty()) {
            System.out.println("La cola está vacía. No se puede desencolar.");
            return null;
        }
        T elemento = cola.remove(0);
        System.out.println("Elemento " + elemento + " desencolado.");
        return elemento;
    }

    // Consultar el primer elemento
    public T front() {
        if (isEmpty()) {
            System.out.println("La cola está vacía.");
            return null;
        }
        return cola.get(0);
    }

    // Verificar si la cola está vacía
    public boolean isEmpty() {
        return cola.isEmpty();
    }

    // Cantidad de elementos en la cola
    public int size() {
        return cola.size();
    }

    // Mostrar todos los elementos
    public void printQueue() {
        if (isEmpty()) {
            System.out.println("La cola está vacía.");
        } else {
            System.out.println("Elementos de la cola (frente -> final):");
            for (T elemento : cola) {
                System.out.print(elemento + " ");
            }
            System.out.println();
        }
    }
}