package controlador;

import Sistema.*;
import Estructuras_de_datos.*;

public class Controlador {

    /* ============================================================
       CONTROLADOR GLOBAL: administra listas y acceso al mapa
       ============================================================ */
    public static class ControladorGlobal {
        public Lista<Cliente> clientes;
        public Lista<Restaurante> restaurantes;
        public Lista<Domicilio> domiciliarios;
        public Mapa mapa;

        public ControladorGlobal() {
            clientes = new Lista<>();
            restaurantes = new Lista<>();
            domiciliarios = new Lista<>();
            mapa = new Mapa();
        }

        public Cliente buscarClientePorCodigo(int codigo){
            for(int i=0;i<clientes.tamaño();i++){
                Cliente c = clientes.obtenerPorIndice(i);
                if(c.getCodigo() == codigo) return c;
            }
            return null;
        }

        public Restaurante buscarRestaurantePorCodigo(int codigo){
            for(int i=0;i<restaurantes.tamaño();i++){
                Restaurante r = restaurantes.obtenerPorIndice(i);
                if(r.getCodigo() == codigo) return r;
            }
            return null;
        }

        public Domicilio buscarDomiciliarioPorCodigo(int codigo){
            for(int i=0;i<domiciliarios.tamaño();i++){
                Domicilio d = domiciliarios.obtenerPorIndice(i);
                if(d.getCodigo() == codigo) return d;
            }
            return null;
        }
    }

    /* ============================================================
       CONTROLADOR CLIENTE
       ============================================================ */
    public static class ControladorCliente {
        private ControladorGlobal global;

        public ControladorCliente(ControladorGlobal g){
            this.global = g;
        }

        public void registrarCliente(int codigo, String nombre, String zona){
            Cliente c = new Cliente(codigo, nombre, zona);
            global.clientes.insertarFinal(c);
            System.out.println("Cliente registrado: " + nombre);
        }

        public void verMenuCliente(Cliente c){
            Restaurante cercano = global.mapa.EncontrarElRestauranteMasCercano(c.getZona(), global.restaurantes);
            if(cercano != null){
                System.out.println("\n Restaurante más cercano: " + cercano.getNombreCompleto());
                cercano.mostrarMenu();
            } else {
                System.out.println("No se encontró restaurante cercano.");
            }
        }

        public void hacerPedidoUnPlato(Cliente c, int codigoRestaurante, int codigoDomiciliario, String plato){
            Restaurante r = global.buscarRestaurantePorCodigo(codigoRestaurante);
            if(r == null){
                System.out.println("Restaurante no encontrado.");
                return;
            }

            Domicilio d = global.buscarDomiciliarioPorCodigo(codigoDomiciliario);
            if(d == null){
                System.out.println("Domiciliario no encontrado.");
                return;
            }
            if(!d.isDisponible()){
                System.out.println("El domiciliario no está disponible.");
                return;
            }

            Pedidos pedido = c.hacerPedidoUnPlato(r, d, plato);
            if(pedido != null){
                r.agregarPedido(pedido);
                d.setDisponible(false);
                System.out.println("Pedido creado con el código: " + pedido.getCodigo());
            }
        }

        public void verPedidos(Cliente c){
            System.out.println("\n PEDIDOS PENDIENTES:");
            c.mostrarPedidosPendientes();
            System.out.println("\n HISTORIAL:");
            c.MostrarHistorial(null);
        }

        public void cancelarPedido(Cliente c, Pedidos p){
            c.CancelarPedido(p);
        }
    }

    /* ============================================================
       CONTROLADOR RESTAURANTE
       ============================================================ */
    public static class ControladorRestaurante {
        private ControladorGlobal global;

        public ControladorRestaurante(ControladorGlobal g){
            this.global = g;
        }

        public void verMenu(Restaurante r){
            r.mostrarMenu();
        }

        public void agregarPlato(Restaurante r, String nombrePlato){
            r.agregarPlato(nombrePlato);
        }

        public void verPedidosPendientes(Restaurante r){
            r.mostrarPedidosPendientes();
        }
    }

    /* ============================================================
       CONTROLADOR DOMICILIARIO
       ============================================================ */
    public static class ControladorDomiciliario {
        private ControladorGlobal global;

        public ControladorDomiciliario(ControladorGlobal g){
            this.global = g;
        }

        public void entregarPedido(Domicilio d){
            Restaurante r = global.mapa.EncontrarElRestauranteMasCercano(d.getZona(), global.restaurantes);

            if(r == null){
                System.out.println("No hay restaurantes cercanos.");
                return;
            }

            if(r.getPedidosPendientes().size() == 0){
                System.out.println("No hay pedidos pendientes en: " + r.getNombreCompleto());
                return;
            }

            Pedidos p = r.getPedidosPendientes().dequeue();
            p.entregarPedido();
            d.setDisponible(true);

            System.out.println("\n Pedido entregado!");
            System.out.println(" Código: " + p.getCodigo());
            System.out.println(" Restaurante: " + r.getNombreCompleto());
            System.out.println(" Cliente: " + p.getCliente().getNombreCompleto());
        }
    }
}
