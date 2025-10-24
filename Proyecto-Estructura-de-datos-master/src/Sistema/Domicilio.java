package Sistema;



import Estructuras_de_datos.Coladinamica;
import Estructuras_de_datos.Pila;

public class Domicilio extends Father{
    @SuppressWarnings("unused")
    private int codigo;
    private String nombre;
    @SuppressWarnings("unused")
    private String zona;
    private boolean disponible;
    private Coladinamica<Pedidos> entregasPendientes;
    private Pila<Pedidos> historialEntregas;

    public Domicilio(int codigo, String nombre, String zona) {
        super(codigo, nombre, zona);
        this.disponible = true;
        this.entregasPendientes = new Coladinamica<>();
        this.historialEntregas = new Pila<>();
    }

    public void asignarEntrega(Pedidos pedido) {
        entregasPendientes.enqueue(pedido);
        disponible = false;
        System.out.println("Domicili " + nombre + " recibió el pedido: " + pedido);
    }

    public void entregarPedido() {
        if (entregasPendientes.isEmpty()) {
            System.out.println("No hay pedidos para entregar.");
            disponible = true;
            return;
        }

        Pedidos pedido = entregasPendientes.dequeue();
        pedido.cambiarEstado("Entregado");
        historialEntregas.push(pedido);
        System.out.println(nombre + " entregó el pedido #" + pedido.getCodigo());

        if (entregasPendientes.isEmpty()) disponible = true;
    }

    public void mostrarHistorial() {
        System.out.println("Historial de entregas de " + nombre + ":");
        historialEntregas.print_stack();
    }

    public boolean isDisponible() {
        return disponible; }

}
