package Sistema;
import Estructuras_de_datos.Coladinamica;
import Estructuras_de_datos.Pila;
import Estructuras_de_datos.Lista;

public class Restaurante {
    private int codigo;
    private String nombre;
    private String zonaUbicacion;
    private Lista<String> menu;
    private Coladinamica<String> pedidosPendientes;
    private Pila<String> historialPedidos;

    public Restaurante(int codigo, String nombre, String zonaUbicacion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.zonaUbicacion = zonaUbicacion;
        this.menu = new Lista<>();
        this.pedidosPendientes = new Coladinamica<>();
        this.historialPedidos = new Pila<>();
    }

    // ======== MÉTODOS DEL MENÚ ========
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

    // ======== MÉTODOS DE PEDIDOS ========
    public void agregarPedido(String pedido) {
        pedidosPendientes.enqueue(pedido);
    }

    public void procesarPedido() {
        if (pedidosPendientes.isEmpty()) {
            System.out.println("No hay pedidos pendientes.");
            return;
        }
        String pedido = pedidosPendientes.dequeue();
        historialPedidos.push(pedido);
        System.out.println("Pedido procesado y agregado al historial: " + pedido);
    }

    public void cancelarPedido(String pedido) {
        if (pedidosPendientes.isEmpty()) {
            System.out.println("No hay pedidos para cancelar.");
            return;
        }

        Coladinamica<String> temp = new Coladinamica<>();
        boolean cancelado = false;

        while (!pedidosPendientes.isEmpty()) {
            String actual = pedidosPendientes.dequeue();
            if (actual.equalsIgnoreCase(pedido)) {
                historialPedidos.push("CANCELADO: " + actual);
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

    // ======== GETTERS ========
    public String getNombre() {
        return nombre; }
    public String getZonaUbicacion() {
        return zonaUbicacion; }
    public int getCodigo() {
        return codigo; }
}
