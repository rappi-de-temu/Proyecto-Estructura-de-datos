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

public class Lista<T> {
    private Nodo<T> frente;
    private Nodo<T> fin;

    public void recorrerFrenteAFin() {
        if (frente == null) {
            System.out.println("La lista está vacía");
            return;
        }
        Nodo<T> temp = frente;
        while (temp != null) {
            System.out.print(temp.dato + " ");
            temp = temp.siguiente;
        }
        System.out.println();
    }

    public void insertarFinal(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (frente == null) {
            frente = fin = nuevo;
        } else {
            nuevo.anterior = fin;
            fin.siguiente = nuevo;
            fin = nuevo;
        }
    }

    public boolean buscar(T valor) {
        Nodo<T> temp = frente;
        while (temp != null) {
            if (temp.dato.equals(valor)) {
                System.out.println("Elemento encontrado: " + temp.dato);
                return true;
            }
            temp = temp.siguiente;
        }
        System.out.println("Elemento no encontrado.");
        return false;
    }

    public void borrar(T valor) {
        if (frente == null) {
            System.out.println("La lista está vacía");
            return;
        }
        Nodo<T> temp = frente;
        while (temp != null && !temp.dato.equals(valor)) {
            temp = temp.siguiente;
        }
        if (temp == null) {
            System.out.println("Elemento no encontrado");
            return;
        }
        if (temp == frente) {
            frente = frente.siguiente;
            if (frente != null) frente.anterior = null;
        } else if (temp == fin) {
            fin = fin.anterior;
            if (fin != null) fin.siguiente = null;
        } else {
            temp.anterior.siguiente = temp.siguiente;
            temp.siguiente.anterior = temp.anterior;
        }
    }
}
