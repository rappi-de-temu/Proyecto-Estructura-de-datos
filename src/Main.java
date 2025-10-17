

import Sistema.Restaurante;

public class Main {
    public static void main(String[] args) {
        Restaurante r = new Restaurante(1, "La Parrilla del Norte", "Zona A");

        //MENÚ
        r.agregarPlato("Bandeja Paisa");
        r.agregarPlato("Ajiaco");
        r.agregarPlato("Postre de Natas");
        r.mostrarMenu();

        //PEDIDOS
        r.agregarPedido("Pedido 001 - Bandeja Paisa");
        r.agregarPedido("Pedido 002 - Ajiaco");
        r.agregarPedido("Pedido 003 - Postre de Natas");
        r.mostrarPedidosPendientes();

        r.cancelarPedido("Pedido 002 - Ajiaco");
        r.procesarPedido();
        r.mostrarPedidosPendientes();
        r.mostrarHistorial();
    }
}
