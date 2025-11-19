package Estructuras_de_datos;

import java.util.ArrayList;
import java.util.Comparator;

public class ColaConPrioridad<T> {
    private final ArrayList<T> items;
    private final Comparator<T> comparador;

    // Constructor que recibe un comparador para la prioridad
    public ColaConPrioridad(Comparator<T> comparador) {
        this.items = new ArrayList<>();
        this.comparador = comparador;
    }

    public void encolar(T elemento) {
        // Insertar el elemento en la posición correcta según la prioridad
        int i = 0;
        while (i < items.size() && comparador.compare(elemento, items.get(i)) >= 0) {
            i++;
        }
        items.add(i, elemento);
    }

    public T desencolar() {
        if (!estaVacia()) {
            return items.remove(0);
        }
        return null;
    }

    public void imprimir() {
        for (T elemento : items) {
            System.out.print(elemento + " ");
        }
        System.out.println();
    }

    public boolean estaVacia() {
        return items.isEmpty();
    }
}

