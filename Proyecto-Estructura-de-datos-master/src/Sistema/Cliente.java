package Sistema;
import Estructuras_de_datos.Pila;
import Estructuras_de_datos.Coladinamica;

public class Cliente extends Father {
    protected int codigo;
    protected String nombre;
    protected String zona;
    protected Coladinamica<Pedidos> pedidosPendientes;
    protected Pila<Pedidos> historialPedidos;

    public Cliente(int codigo, String nombre, String zona) {
        super(codigo, nombre, zona);
        this.pedidosPendientes = new Coladinamica<>();
        this.historialPedidos = new Pila<>();
    }

    public Pedidos hacerPedido(Restaurante restaurante, Domicilio domicilio) {
        int codigoPedido = (int)(Math.random() * 10000);
        Pedidos nuevo = new Pedidos(codigoPedido, this, restaurante, domicilio);
        pedidosPendientes.enqueue(nuevo);
        System.out.println(nombre + " realizó el pedido #" + codigoPedido);
        return nuevo;
    }

    public void recibirPedido(Pedidos pedido) {
        if (pedidosPendientes.isEmpty()) {
            System.out.println(nombre + " no tiene pedidos pendientes.");
            return;
        }
        Pedidos atendido = pedidosPendientes.dequeue();
        historialPedidos.push(atendido);
        System.out.println(nombre + " recibió su pedido: " + atendido);
    }
    public void CancelarPedido(Pedidos pedido){
        if (pedido == null) {
            System.out.println("Pedido inválido.");
            return;
        }
        pedido.cancelarPedido();
    }

    public void MostrarHistorial(Pedidos pedido){
        pedido.mostrarHistorial();
    }

}
