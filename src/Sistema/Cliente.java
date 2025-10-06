package Sistema;
import Estructuras_de_datos.Pila;
import Estructuras_de_datos.Coladinamica;

public class Cliente {
    private int codigo;
    private String nombreCompleto;
    private String zona;
    private Coladinamica<String> pedidosPendientes;
    private Pila<String> historialPedidos;

    public Cliente(int codigo, String nombreCompleto, String zona) {
        this.codigo = codigo;
        this.nombreCompleto = nombreCompleto;
        this.zona = zona;
        this.pedidosPendientes = new Coladinamica<>();
        this.historialPedidos = new Pila<>();
    }

    public void hacerPedido(String pedido) {
        pedidosPendientes.enqueue(pedido);
        System.out.println(nombreCompleto + " hizo el pedido: " + pedido);
    }

    public void recibirPedido() {
        if (pedidosPendientes.isEmpty()) {
            System.out.println(nombreCompleto + " no tiene pedidos pendientes.");
            return;
        }
        String pedido = pedidosPendientes.dequeue();
        historialPedidos.push(pedido);
        System.out.println(nombreCompleto + " recibió su pedido: " + pedido);
    }

    public void cancelarPedido(String pedido) {
        Coladinamica<String> temp = new Coladinamica<>();
        boolean cancelado = false;

        while (!pedidosPendientes.isEmpty()) {
            String actual = pedidosPendientes.dequeue();
            if (actual.equalsIgnoreCase(pedido)) {
                historialPedidos.push("CANCELADO: " + actual);
                cancelado = true;
                System.out.println(nombreCompleto + " canceló el pedido: " + actual);
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
        System.out.println("Historial de pedidos de " + nombreCompleto + ":");
        historialPedidos.print_stack();
    }

    public String getZona() {
        return zona; }
    public int getCodigo() {
        return codigo; }
    public String getNombreCompleto() {
        return nombreCompleto; }
}
