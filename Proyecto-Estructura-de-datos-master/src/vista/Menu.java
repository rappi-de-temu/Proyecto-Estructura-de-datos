package vista;

import controlador.Controlador;
import Sistema.*;
import Estructuras_de_datos.*;
import java.util.Scanner;

public class Menu {

    private static Scanner sc = new Scanner(System.in);

    public static void iniciar() {
        main(new String[0]);
    }

    public static void main(String[] args) {
        Controlador.ControladorGlobal global = new Controlador.ControladorGlobal();
        Controlador.ControladorCliente cc = new Controlador.ControladorCliente(global);
        Controlador.ControladorRestaurante cr = new Controlador.ControladorRestaurante(global);
        Controlador.ControladorDomiciliario cd = new Controlador.ControladorDomiciliario(global);

        cargarDatosPrueba(global);

        int opcion;
        do {
            System.out.println("\n========== SISTEMA DE PEDIDOS ==========");
            System.out.println("1. Menu Cliente");
            System.out.println("2. Menu Restaurante");
            System.out.println("3. Menu Domiciliario");
            System.out.println("0. Salir");
            System.out.print("Seleccione: ");
            opcion = leerEntero();

            switch (opcion) {
                case 1 -> menuCliente(global, cc);
                case 2 -> menuRestaurante(global, cr);
                case 3 -> menuDomiciliario(global, cd);
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 0);
    }

    /* ------------------ MENÚ CLIENTE ------------------ */
    private static void menuCliente(Controlador.ControladorGlobal global, Controlador.ControladorCliente cc) {
        int op;
        do {
            System.out.println("\n--- MENÚ CLIENTE ---");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Ver menú del restaurante más cercano");
            System.out.println("3. Hacer pedido");
            System.out.println("4. Ver mis pedidos");
            System.out.println("0. Volver");
            System.out.print("Seleccione: ");
            op = leerEntero();

            switch (op) {
                case 1 -> registrarCliente(global, cc);
                case 2 -> {
                    Cliente c = pedirCliente(global);
                    if (c != null) cc.verMenuCliente(c);
                }
                case 3 -> {
                    Cliente c = pedirCliente(global);
                    if (c != null) hacerPedidoConSeleccion(global, cc, c);
                }
                case 4 -> {
                    Cliente c = pedirCliente(global);
                    if (c != null) cc.verPedidos(c);
                }
                case 0 -> {}
                default -> System.out.println("Opción inválida.");
            }
        } while (op != 0);
    }

    private static void registrarCliente(Controlador.ControladorGlobal global, Controlador.ControladorCliente cc) {
        System.out.print("Código (int): ");
        int codigo = leerEntero();
        System.out.print("Nombre completo: ");
        String nombre = sc.nextLine().trim();
        System.out.print("Zona (nombre exacto del barrio): ");
        String zona = sc.nextLine().trim();

        cc.registrarCliente(codigo, nombre, zona);
    }

    private static Cliente pedirCliente(Controlador.ControladorGlobal global) {
        System.out.print("Ingrese código del cliente: ");
        int cod = leerEntero();
        Cliente c = global.buscarClientePorCodigo(cod);
        if (c == null) {
            System.out.println("Cliente no encontrado.");
        }
        return c;
    }

    private static void hacerPedidoConSeleccion(Controlador.ControladorGlobal global, Controlador.ControladorCliente cc, Cliente cliente) {
        System.out.print("Código del restaurante: ");
        int codigoRest = leerEntero();
        Restaurante r = global.buscarRestaurantePorCodigo(codigoRest);
        if (r == null) {
            System.out.println("Restaurante no encontrado.");
            return;
        }

        System.out.println("\nPlatos disponibles:");
        r.mostrarMenu();
        System.out.print("Nombre del plato exacto: ");
        String plato = sc.nextLine().trim();

        // Mostrar domiciliarios cercanos y disponibles (usa el método que debe existir en Mapa)
        Lista<Domicilio> cercanos = global.mapa.DomiciliariosCercanosDisponibles(r.getZona(), global.domiciliarios);
        if (cercanos == null || cercanos.tamaño() == 0) {
            System.out.println("No hay domiciliarios cercanos disponibles.");
            return;
        }

        System.out.println("\nDomiciliarios disponibles cercanos:");
        for (int i = 0; i < cercanos.tamaño(); i++) {
            Domicilio d = cercanos.obtenerPorIndice(i);
            System.out.println(d.getCodigo() + " - " + d.getNombreCompleto() + " (" + d.getZona() + ")");
        }

        System.out.print("Ingrese código del domiciliario que desea asignar: ");
        int codigoDom = leerEntero();

        // Llamada al controlador (firma que existe en tu controlador)
        cc.hacerPedidoUnPlato(cliente, codigoRest, codigoDom, plato);
    }

    /* ------------------ MENÚ RESTAURANTE ------------------ */
    private static void menuRestaurante(Controlador.ControladorGlobal global, Controlador.ControladorRestaurante cr) {
        System.out.print("Ingrese código del restaurante: ");
        int codigo = leerEntero();
        Restaurante r = global.buscarRestaurantePorCodigo(codigo);
        if (r == null) {
            System.out.println("Restaurante no encontrado.");
            return;
        }

        int op;
        do {
            System.out.println("\n--- MENÚ RESTAURANTE (" + r.getNombreCompleto() + ") ---");
            System.out.println("1. Ver menú");
            System.out.println("2. Agregar plato");
            System.out.println("3. Ver pedidos pendientes");
            System.out.println("0. Volver");
            System.out.print("Seleccione: ");
            op = leerEntero();

            switch (op) {
                case 1 -> cr.verMenu(r);
                case 2 -> {
                    System.out.print("Nombre del plato: ");
                    String nombrePlato = sc.nextLine().trim();
                    cr.agregarPlato(r, nombrePlato);
                }
                case 3 -> cr.verPedidosPendientes(r);
                case 0 -> {}
                default -> System.out.println("Opción inválida.");
            }
        } while (op != 0);
    }

    /* ------------------ MENÚ DOMICILIARIO ------------------ */
    private static void menuDomiciliario(Controlador.ControladorGlobal global, Controlador.ControladorDomiciliario cd) {
        System.out.print("Ingrese código del domiciliario: ");
        int codigo = leerEntero();
        Domicilio d = global.buscarDomiciliarioPorCodigo(codigo);
        if (d == null) {
            System.out.println("Domiciliario no encontrado.");
            return;
        }

        int op;
        do {
            System.out.println("\n--- MENÚ DOMICILIARIO (" + d.getNombreCompleto() + ") ---");
            System.out.println("1. Entregar pedido");
            System.out.println("0. Volver");
            System.out.print("Seleccione: ");
            op = leerEntero();

            switch (op) {
                case 1 -> cd.entregarPedido(d);
                case 0 -> {}
                default -> System.out.println("Opción inválida.");
            }
        } while (op != 0);
    }

    /* ------------------ UTILIDADES ------------------ */
    private static int leerEntero() {
        while (true) {
            try {
                String line = sc.nextLine();
                return Integer.parseInt(line.trim());
            } catch (Exception e) {
                System.out.print("Entrada inválida. Ingrese un número entero: ");
            }
        }
    }

    private static void cargarDatosPrueba(Controlador.ControladorGlobal global) {
        // clientes
        global.clientes.insertarFinal(new Cliente(1, "Juan Pérez", "Centro Histórico"));
        global.clientes.insertarFinal(new Cliente(2, "María López", "Bastidas"));

        // restaurantes y platos
        Restaurante r1 = new Restaurante(100, "Pizza Express", "Galicia");
        r1.agregarPlato("Margarita");
        r1.agregarPlato("Hawaiana");
        global.restaurantes.insertarFinal(r1);

        Restaurante r2 = new Restaurante(101, "Burger House", "El prado");
        r2.agregarPlato("Clasica");
        r2.agregarPlato("Doble Queso");
        global.restaurantes.insertarFinal(r2);

        // domiciliarios
        global.domiciliarios.insertarFinal(new Domicilio(200, "Carlos Ruiz", "Galicia"));
        global.domiciliarios.insertarFinal(new Domicilio(201, "Ana Torres", "El prado"));
    }
}
