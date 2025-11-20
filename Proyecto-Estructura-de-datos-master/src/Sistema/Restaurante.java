package Sistema;
import Estructuras_de_datos.Coladinamica;
import Estructuras_de_datos.Pila;
import Estructuras_de_datos.Lista;

public class Restaurante extends Father {
    @SuppressWarnings("unused")
     @SuppressWarnings("unused")
    private Lista<String> menu;
    private Coladinamica<Pedidos> pedidosPendientes;
    private Pila<Pedidos> historialPedidos;

    public Restaurante(int codigo, String nombre, String zona) {
        super(codigo, nombre, zona);
        this.menu = new Lista<>();
        this.pedidosPendientes = new Coladinamica<>();
        this.historialPedidos = new Pila<>();
    }


    public void agregarPlato(String plato) {
        menu.insertarFinal(plato);
        System.out.println("Plato agregado al menú: " + plato);
    }

    public void eliminarPlato(String plato) {
        menu.borrar(plato);
        System.out.println("Plato eliminado del menú: " + plato);
    }

    public boolean buscarPlato(String plato) {
        return menu.buscar(plato);
    }

    public void mostrarMenu() {
        System.out.println("Menú del restaurante " + nombre + ":");
        menu.recorrerFrenteAFin();
    }


    public void agregarPedido(Pedidos pedido) {
        pedidosPendientes.enqueue(pedido);
    }
    
    public void recibirPedido(Pedidos pedido) {
        if (pedido != null) {
            pedidosPendientes.enqueue(pedido);
            pedido.cambiarEstado("En preparación");
            System.out.println("Restaurante " + nombre + " recibió el pedido #" + pedido.getCodigo());
        }
    }

    public void procesarPedido() {
        if (pedidosPendientes.isEmpty()) {
            System.out.println("No hay pedidos pendientes.");
            return;
        }

        Pedidos pedido = pedidosPendientes.dequeue();
        pedido.cambiarEstado("Listo para entrega");
        historialPedidos.push(pedido);
        System.out.println("Pedido #" + pedido.getCodigo() + " está listo para entrega.");
    }

    public void cancelarPedido(String pedido) {
        if (pedidosPendientes.isEmpty()) {
            System.out.println("No hay pedidos para cancelar.");
            return;
        }

        Coladinamica<Pedidos> temp = new Coladinamica<>();
        boolean cancelado = false;

        while (!pedidosPendientes.isEmpty()) {
            Pedidos actual = pedidosPendientes.dequeue();
            if (String.valueOf(actual.getCodigo()).equalsIgnoreCase(pedido)) {
                actual.cambiarEstado("CANCELADO");
                historialPedidos.push(actual);
                cancelado = true;
            } else {
                temp.enqueue(actual);
            }
        }

        while (!temp.isEmpty()) {
            pedidosPendientes.enqueue(temp.dequeue());
        }

        if (!cancelado)
            System.out.println("Pedido no encontrado: " + pedido);
    }

    public void mostrarPedidosPendientes() {
        System.out.println("Pedidos pendientes:");
        pedidosPendientes.printQueue();
    }

    public void mostrarHistorial() {
        System.out.println("Historial de pedidos:");
        historialPedidos.print_stack();
    }
}


