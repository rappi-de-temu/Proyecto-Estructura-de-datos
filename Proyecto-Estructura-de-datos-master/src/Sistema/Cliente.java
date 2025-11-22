import Estructuras_de_datos.Pila;
import Estructuras_de_datos.Coladinamica;
import Estructuras_de_datos.Lista;

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

    public Pedidos hacerPedido(Restaurante restaurante, Domicilio domicilio, Lista<String> descripcion) {
        if(descripcion == null || descripcion.tamaño() == 0){
            System.out.println("La descripción del pedido no puede estar vacía.");
            return null;
        }
        boolean disponibilidad = true;
        for (int i = 0; i < descripcion.tamaño(); i++) {
            String plato = descripcion.obtenerPorIndice(i);
            if (!restaurante.buscarPlato(plato)) {
                System.out.println("El plato '" + plato + "' no está disponible en el menú del restaurante.");
                disponibilidad = false;
            }
        }
        if (!disponibilidad) {
            System.out.println("No se pueden hacer pedidos con platos que no están en el menú");
            return null;
        }
        
        int codigoPedido = (int)(Math.random() * 10000);
        Pedidos nuevo = new Pedidos(codigoPedido, this, restaurante, domicilio, descripcion);
        pedidosPendientes.enqueue(nuevo);
        System.out.println("El cliente" + nombre + " realizó el pedido #" + codigoPedido);
        return nuevo;
    }

    public Pedidos hacerPedidoUnPlato(Restaurante restaurante, Domicilio domicilio, String plato) {
        Lista<String> descripcion = new Lista<>();
        descripcion.insertarInicio(plato);
        return hacerPedidoUnPlato(restaurante, domicilio, plato);
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

    public void mostrarPedidosPendientes(){
        if (pedidosPendientes.isEmpty()) {
            System.out.println("El cliente" + nombre + " no tiene pedidos pendientes.");
            return;
        }
        System.out.println("Pedidos pendientes de " + nombre + ":");
        pedidosPendientes.printQueue();
    }

    public void MostrarHistorial(Pedidos pedido){
        if(historialPedidos.empty()){
            System.out.println("El cliente " + nombre + " no tiene historial de pedidos.");
            return;
        }
        System.out.println("El historial de pedidos del cliente " + nombre + " es:");
        historialPedidos.print_stack();
    }

}
