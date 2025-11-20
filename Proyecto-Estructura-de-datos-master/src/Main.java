import Sistema.Menu;
import Sistema.Cliente;
import Sistema.Restaurante;
import Sistema.Pedidos;

public class Main {
    public static void main(String[] args) {
//        Restaurante r = new Restaurante(1, "La Parrilla del Norte", "Zona A");
//        //MENÚ
//        r.agregarPlato("Bandeja Paisa");
//        r.agregarPlato("Ajiaco");
//        r.agregarPlato("Postre de Natas");
//        r.mostrarMenu();
//        //PEDIDOS
//        r.agregarPedido(new Pedidos("Pedido 001 - Bandeja Paisa"));
//        r.agregarPedido(new Pedidos("Pedido 002 - Ajiaco"));
//        r.agregarPedido(new Pedidos("Pedido 003 - Postre de Natas"));
//        r.mostrarPedidosPendientes();
//        r.mostrarPedidosPendientes();
//
//        r.cancelarPedido("Pedido 002 - Ajiaco");
//        r.procesarPedido();
//        r.mostrarPedidosPendientes();
//        r.mostrarHistorial();
        Menu menu = new Menu();
        menu.mostrarMenu();
    }
}
