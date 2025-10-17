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

// Lista doblemente enlazada
public class Lista<T> {
    private Nodo<T> frente;
    private Nodo<T> fin;

    // Recorrer de frente a fin
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

    // Recorrer de fin a frente
    public void recorrerFinAFrente() {
        if (fin == null) {
            System.out.println("La lista está vacía");
            return;
        }
        Nodo<T> temp = fin;
        while (temp != null) {
            System.out.print(temp.dato + " ");
            temp = temp.anterior;
        }
        System.out.println();
    }

    // Insertar al inicio
    public void insertarInicio(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (frente == null) {
            frente = fin = nuevo;
        } else {
            nuevo.siguiente = frente;
            frente.anterior = nuevo;
            frente = nuevo;
        }
    }

    // Insertar al final
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

    // Insertar después de un nodo
    public void insertarDespues(T referencia, T dato) {
        if (frente == null) {
            System.out.println("La lista está vacía");
            return;
        }
        Nodo<T> temp = frente;
        while (temp != null && !temp.dato.equals(referencia)) {
            temp = temp.siguiente;
        }
        if (temp == null) {
            System.out.println("Elemento no encontrado");
            return;
        }
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.siguiente = temp.siguiente;
        nuevo.anterior = temp;
        if (temp.siguiente != null) {
            temp.siguiente.anterior = nuevo;
        } else {
            fin = nuevo;
        }
        temp.siguiente = nuevo;
    }

    // Insertar antes de un nodo
    public void insertarAntes(T referencia, T dato) {
        if (frente == null) {
            System.out.println("La lista está vacía");
            return;
        }
        Nodo<T> temp = frente;
        while (temp != null && !temp.dato.equals(referencia)) {
            temp = temp.siguiente;
        }
        if (temp == null) {
            System.out.println("Elemento no encontrado");
            return;
        }
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.siguiente = temp;
        nuevo.anterior = temp.anterior;
        if (temp.anterior != null) {
            temp.anterior.siguiente = nuevo;
        } else {
            frente = nuevo;
        }
        temp.anterior = nuevo;
    }

    // Buscar un elemento
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

    // Borrar primero
    public void borrarPrimero() {
        if (frente == null) {
            System.out.println("La lista está vacía");
            return;
        }
        if (frente == fin) {
            frente = fin = null;
        } else {
            frente = frente.siguiente;
            frente.anterior = null;
        }
    }

    // Borrar último
    public void borrarUltimo() {
        if (fin == null) {
            System.out.println("La lista está vacía");
            return;
        }
        if (frente == fin) {
            frente = fin = null;
        } else {
            fin = fin.anterior;
            fin.siguiente = null;
        }
    }

    // Borrar después de un nodo
    public void borrarDespues(T referencia) {
        if (frente == null) {
            System.out.println("La lista está vacía");
            return;
        }
        Nodo<T> temp = frente;
        while (temp != null && !temp.dato.equals(referencia)) {
            temp = temp.siguiente;
        }
        if (temp == null || temp.siguiente == null) {
            System.out.println("No hay elemento después de " + referencia);
            return;
        }
        Nodo<T> borrar = temp.siguiente;
        temp.siguiente = borrar.siguiente;
        if (borrar.siguiente != null) {
            borrar.siguiente.anterior = temp;
        } else {
            fin = temp;
        }
    }

    // Borrar antes de un nodo
    public void borrarAntes(T referencia) {
        if (frente == null) {
            System.out.println("La lista está vacía");
            return;
        }
        Nodo<T> temp = frente;
        while (temp != null && !temp.dato.equals(referencia)) {
            temp = temp.siguiente;
        }
        if (temp == null || temp.anterior == null) {
            System.out.println("No hay elemento antes de " + referencia);
            return;
        }
        Nodo<T> borrar = temp.anterior;
        temp.anterior = borrar.anterior;
        if (borrar.anterior != null) {
            borrar.anterior.siguiente = temp;
        } else {
            frente = temp;
        }
    }

    // Borrar por valor (normal)
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
            borrarPrimero();
        } else if (temp == fin) {
            borrarUltimo();
        } else {
            temp.anterior.siguiente = temp.siguiente;
            temp.siguiente.anterior = temp.anterior;
        }
    }
}
