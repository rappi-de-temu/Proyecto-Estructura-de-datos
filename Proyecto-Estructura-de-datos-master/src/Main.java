import Sistema.*;
import Estructuras_de_datos.*;

public class Main {
    public static void main(String[] args) {
        
        Cliente cliente1 = new Cliente(1, "Juan Pérez", "Centro Histórico");
        Cliente cliente2 = new Cliente(2, "María García", "Bastidas");
        
        Restaurante restaurante1 = new Restaurante(101, "Pizza Express", "Galicia");
        Restaurante restaurante2 = new Restaurante(102, "Burger King", "El prado");
        
        Domicilio domicilio1 = new Domicilio(201, "Carlos Ruiz", "La ciudadela");
        Domicilio domicilio2 = new Domicilio(202, "Ana López", "El jardin");
        
        
      
        System.out.println("\n=== CONFIGURANDO MENÚS ===");
        restaurante1.agregarPlato("Pizza Hawaiana");
        restaurante1.agregarPlato("Pizza Pepperoni");
        restaurante1.agregarPlato("Pizza Vegetariana");
        restaurante1.agregarPlato("Coca-Cola");
        restaurante1.agregarPlato("Sprite");
        
        restaurante2.agregarPlato("Hamburguesa Simple");
        restaurante2.agregarPlato("Hamburguesa Doble");
        restaurante2.agregarPlato("Papas Fritas");
        restaurante2.agregarPlato("Nuggets");
        restaurante2.agregarPlato("Pepsi");
        
        
        System.out.println("\n=== MENÚS DISPONIBLES ===");
        restaurante1.mostrarMenu();
        System.out.println();
        restaurante2.mostrarMenu();
        
        
       
        System.out.println("\n=== CLIENTE 1: PEDIDO CON MÚLTIPLES PLATOS ===");
        Lista<String> platosCliente1 = new Lista<>();
        platosCliente1.insertarFinal("Pizza Hawaiana");
        platosCliente1.insertarFinal("Pizza Pepperoni");
        platosCliente1.insertarFinal("Coca-Cola");
        
        Pedidos pedido1 = cliente1.hacerPedido(restaurante1, domicilio1, platosCliente1);
        
        if (pedido1 != null) {
            System.out.println("\nDetalles del pedido:");
            System.out.println(pedido1);
            pedido1.mostrarPlatoPedidos();
        }
        
        
    
        System.out.println("\n=== CLIENTE 2: PEDIDO CON UN SOLO PLATO ===");
        Pedidos pedido2 = cliente2.hacerPedidoUnPlato(restaurante2, domicilio2, "Hamburguesa Doble");
        
        if (pedido2 != null) {
            System.out.println("\nDetalles del pedido:");
            System.out.println(pedido2);
        }
    }
}
