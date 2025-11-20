package Sistema;
import Estructuras_de_datos.Lista;
import Sistema.*;
import java.util.Scanner;

public class Menu {

    Scanner scanner = new Scanner(System.in);
    Lista<Cliente> clienteLista = new Lista<>();

    public void mostrarMenu(){
        int opcion;

        do {
            System.out.println("\n==== MENÚ ====");
            System.out.println("1. Registrar cliente");
            System.out.println("2. Consultar clientes");
            System.out.println("3. Salir");
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
                    System.out.println("DEBUG -> codigo=" + codigo + ", nombre=" + nombre + ", zona=" + zona);


                    System.out.println("Cliente agregado con éxito.");
                }

                case 2 -> {
                    clienteLista.recorrerFrenteAFin();
                }

                case 3 -> System.out.println("Saliendo...");

                default -> System.out.println("Opción inválida.");
            }

        } while (opcion != 3);


    }

    }






