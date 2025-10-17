package Sistema;
import Estructuras_de_datos.Pila;
import Estructuras_de_datos.Coladinamica;

public class Cliente extends Father {
    private int codigo;
    private String nombre;
    private String zona;
    private Coladinamica<String> pedidosPendientes;
    private Pila<String> historialPedidos;

    public Cliente(int codigo, String nombre, String zona) {
        super(codigo, nombre, zona);
        this.pedidosPendientes = new Coladinamica<>();
        this.historialPedidos = new Pila<>();
    }

    public void hacerPedido(String pedido) {
        pedidosPendientes.enqueue(pedido);
        System.out.println(nombre + " hizo el pedido: " + pedido);
    }

    public void recibirPedido() {
        if (pedidosPendientes.isEmpty()) {
            System.out.println(nombre + " no tiene pedidos pendientes.");
            return;
        }
        String pedido = pedidosPendientes.dequeue();
        historialPedidos.push(pedido);
        System.out.println(nombre + " recibió su pedido: " + pedido);
    }

    public void cancelarPedido(String pedido) {
        Coladinamica<String> temp = new Coladinamica<>();
        boolean cancelado = false;

        while (!pedidosPendientes.isEmpty()) {
            String actual = pedidosPendientes.dequeue();
            if (actual.equalsIgnoreCase(pedido)) {
                historialPedidos.push("CANCELADO: " + actual);
                cancelado = true;
                System.out.println(nombre + " canceló el pedido: " + actual);
            } else {
                temp.enqueue(actual);
            }
        }

        while (!temp.isEmpty()) {
            pedidosPendientes.enqueue(temp.dequeue());
        }

        if (!cancelado) {
            System.out.println("Pedido no encontrado: " + pedido);
        }
    }

    public void mostrarHistorial() {
        System.out.println("Historial de pedidos de " + nombre + ":");
        historialPedidos.print_stack();
    }

}
