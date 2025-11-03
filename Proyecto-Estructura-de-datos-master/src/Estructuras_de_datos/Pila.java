package Estructuras_de_datos;

import java.util.ArrayList;


public class Pila<T> {
    private ArrayList<T> pila;


    public Pila() {
        pila = new ArrayList<>();
    }

    public void push(T elemento) {
        pila.add(elemento);
        System.out.println("Elemento " + elemento + " agregado a la pila.");
    }

    public T pop() {
        if (empty()) {
            System.out.println("La pila está vacía. No se puede sacar elementos.");
            return null;
        }
        T elemento = pila.remove(pila.size() - 1);
        System.out.println("Elemento " + elemento + " removido de la pila.");
        return elemento;
    }


    public T top() {
        if (empty()) {
            System.out.println("La pila está vacía.");
            return null;
        }
        return pila.get(pila.size() - 1);
    }


    public boolean empty() {
        return pila.isEmpty();
    }


    public int size() {
        return pila.size();
    }

    public void print_stack() {
        if (empty()) {
            System.out.println("La pila está vacía.");
        } else {
            System.out.print("Elementos de la pila (top -> bottom): ");
            for (int i = pila.size() - 1; i >= 0; i--) {
                System.out.print(pila.get(i) + " ");
            }
            System.out.println();
        }
    }
}

