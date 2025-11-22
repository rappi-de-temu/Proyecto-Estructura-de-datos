package Sistema;

import Estructuras_de_datos.Lista;
import java.util.Scanner;

public class Menu {

    Scanner scanner = new Scanner(System.in);

    // Listas para cada tipo
    Lista<Cliente> clienteLista = new Lista<>();
    Lista<Restaurante> restauranteLista = new Lista<>();
    Lista<Domicilio> domiciliarioLista = new Lista<>();

    public void iniciar() {
        int opcion;

        do {
            System.out.println("\n==== MENÚ PRINCIPAL ====");
            System.out.println("1. Clientes");
            System.out.println("2. Restaurantes");
            System.out.println("3. Domiciliarios");
            System.out.println("4. Salir");
            System.out.print("Opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> menuClientes();
                case 2 -> menuRestaurantes();
                case 3 -> menuDomiciliarios();
                case 4 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción inválida.");
            }

        } while (opcion != 4);
    }

    // ================= MENÚ CLIENTES =================
    private void menuClientes() {
        int opcion;

        do {
            System.out.println("\n==== MENÚ CLIENTES ====");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Consultar clientes");
            System.out.println("3. Volver");
            System.out.print("Opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1 -> {
                    System.out.print("Codigo: ");
                    int codigo = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();

                    System.out.print("Zona: ");
                    String zona = scanner.nextLine();

                    Cliente nuevo = new Cliente(codigo, nombre, zona);
                    clienteLista.insertarInicio(nuevo);
                    System.out.println("Cliente agregado con éxito.");
                }

                case 2 -> clienteLista.recorrerFrenteAFin();

                case 3 -> System.out.println("Volviendo al menú principal...");

                default -> System.out.println("Opción inválida.");
            }

        } while (opcion != 3);
    }

    // ================= MENÚ RESTAURANTES =================
    private void menuRestaurantes() {
        int opcion;

        do {
            System.out.println("\n==== MENÚ RESTAURANTES ====");
            System.out.println("1. Registrar restaurante");
            System.out.println("2. Consultar restaurantes");
            System.out.println("3. Gestionar menú de un restaurante");
            System.out.println("4. Volver");
            System.out.print("Opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1 -> {
                    System.out.print("Codigo: ");
                    int codigo = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();

                    System.out.print("Zona: ");
                    String zona = scanner.nextLine();

                    Restaurante nuevo = new Restaurante(codigo, nombre, zona);
                    restauranteLista.insertarInicio(nuevo);
                    System.out.println("Restaurante agregado con éxito.");
                }

                case 2 -> restauranteLista.recorrerFrenteAFin();

                case 3 -> gestionarMenuRestaurante();

                case 4 -> System.out.println("Volviendo al menú principal...");

                default -> System.out.println("Opción inválida.");
            }

        } while (opcion != 4);
    }

    // ======= GESTIONAR MENÚ DE UN RESTAURANTE =======
    private void gestionarMenuRestaurante() {
        System.out.println("\n==== GESTIONAR MENÚ DE RESTAURANTE ====");
        System.out.println("Restaurantes registrados:");
        restauranteLista.recorrerFrenteAFin();

        System.out.print("Ingrese el código del restaurante: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();

        Restaurante r = buscarRestaurantePorCodigo(codigo);
        if (r == null) {
            System.out.println("Restaurante no encontrado.");
            return;
        }

        int opcion;
        do {
            System.out.println("\nGestión de menú para: " + r.getNombreCompleto());
            System.out.println("1. Agregar plato");
            System.out.println("2. Eliminar plato");
            System.out.println("3. Mostrar menú");
            System.out.println("4. Volver");
            System.out.print("Opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.print("Nombre del plato a agregar: ");
                    String plato = scanner.nextLine();
                    r.agregarPlato(plato);
                }
                case 2 -> {
                    System.out.print("Nombre del plato a eliminar: ");
                    String plato = scanner.nextLine();
                    r.eliminarPlato(plato);
                }
                case 3 -> r.mostrarMenu();
                case 4 -> System.out.println("Volviendo al menú de restaurantes...");
                default -> System.out.println("Opción inválida.");
            }

        } while (opcion != 4);
    }

    // ================= MENÚ DOMICILIARIOS =================
    private void menuDomiciliarios() {
        int opcion;

        do {
            System.out.println("\n==== MENÚ DOMICILIARIOS ====");
            System.out.println("1. Registrar domiciliario");
            System.out.println("2. Consultar domiciliarios");
            System.out.println("3. Volver");
            System.out.print("Opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {

                case 1 -> {
                    System.out.print("Codigo: ");
                    int codigo = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Nombre: ");
                    String nombre = scanner.nextLine();

                    System.out.print("Zona: ");
                    String zona = scanner.nextLine();

                    Domicilio nuevo = new Domicilio(codigo, nombre, zona);
                    domiciliarioLista.insertarInicio(nuevo);
                    System.out.println("Domiciliario agregado con éxito.");
                }

                case 2 -> domiciliarioLista.recorrerFrenteAFin();

                case 3 -> System.out.println("Volviendo al menú principal...");

                default -> System.out.println("Opción inválida.");
            }

        } while (opcion != 3);
    }


    private Restaurante buscarRestaurantePorCodigo(int codigo) {

        return null;
    }
}
