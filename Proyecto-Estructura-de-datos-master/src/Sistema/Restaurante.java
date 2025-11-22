package Sistema;
import Estructuras_de_datos.Coladinamica;
import Estructuras_de_datos.Lista;

public class Restaurante extends Father {

    private int codigo;
    private String nombre;
    private String zona;

    private Lista<String> menu;
    private Coladinamica<Pedidos> pedidosPendientes;

    public Restaurante(int codigo, String nombre, String zona) {
        super(codigo, nombre, zona);
        this.codigo = codigo;
        this.nombre = nombre;
        this.zona = zona;

        this.menu = new Lista<>();
        this.pedidosPendientes = new Coladinamica<>();
    }

    public int getCodigoRestaurante() {
        return codigo;
    }

    public void setCodigoRestaurante(int codigo) {
        this.codigo = codigo;
    }

    public String getNombreRestaurante() {
        return nombre;
    }

    public void setNombreRestaurante(String nombre) {
        this.nombre = nombre;
    }

    public String getZonaRestaurante() {
        return zona;
    }

    public void setZonaRestaurante(String zona) {
        this.zona = zona;
    }

    public Lista<String> getMenu() {
        return menu;
    }

    public Coladinamica<Pedidos> getPedidosPendientes() {
        return pedidosPendientes;
    }

    public void agregarPlato(String plato) {
        menu.insertarFinal(plato);
        System.out.println("Se ha agregado el plato "+ plato + " al menú. " );
    }

    public void eliminarPlato(String plato) {
        menu.borrar(plato);
        System.out.println("Se ha eliminado el plato "+ plato + " del menú. " );
    }

    public boolean buscarPlato(String plato) {
        return menu.buscar(plato);
    }

    public void mostrarMenu() {
        System.out.println("El menú del restaurante " + nombre + " es:");
        menu.recorrerFrenteAFin();
    }

    public void agregarPedido(Pedidos pedido) {
        pedidosPendientes.enqueue(pedido);
        System.out.println("El pedido #" + pedido.getCodigo()
                + "  ha sido agregado a los pendientes del restaurante " + nombre);
    }

    public void mostrarPedidosPendientes() {
        System.out.println("A continuación se muestran los pedidos pendientes del restaurante " + nombre + ":");
        pedidosPendientes.printQueue();
    }
    @Override
    public String toString() {
        return getNombreCompleto() + " ubicado en " + getZona();
    }
}
