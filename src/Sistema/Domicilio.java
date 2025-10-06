package Sistema;



import Estructuras_de_datos.Coladinamica;
import Estructuras_de_datos.Pila;

public class Domicilio {
    private int codigo;
    private String nombre;
    private String zonaActual;
    private boolean disponible;
    private Coladinamica<String> entregasPendientes;
    private Pila<String> historialEntregas;

    public Domicilio(int codigo, String nombre, String zonaActual) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.zonaActual = zonaActual;
        this.disponible = true;
        this.entregasPendientes = new Coladinamica<>();
        this.historialEntregas = new Pila<>();
    }

    public void asignarEntrega(String pedido) {
        entregasPendientes.enqueue(pedido);
        disponible = false;
        System.out.println("Domicili " + nombre + " recibió el pedido: " + pedido);
    }

    public void entregarPedido() {
        if (entregasPendientes.isEmpty()) {
            System.out.println(nombre + " no tiene entregas pendientes.");
            disponible = true;
            return;
        }
        String pedido = entregasPendientes.dequeue();
        historialEntregas.push(pedido);
        System.out.println(nombre + " entregó el pedido: " + pedido);
        if (entregasPendientes.isEmpty()) disponible = true;
    }

    public void mostrarHistorial() {
        System.out.println("Historial de entregas de " + nombre + ":");
        historialEntregas.print_stack();
    }

    public boolean isDisponible() {
        return disponible; }
    public String getZonaActual() {
        return zonaActual; }
    public String getNombre() {
        return nombre; }
    public int getCodigo() {
        return codigo; }
}
