import java.time.LocalDateTime;

import Estructuras_de_datos.Coladinamica;
import Estructuras_de_datos.Pila;

public class Pedidos {
    private int codigo; 
    private Cliente cliente; 
    private Restaurante restaurante;
    private Domicilio domicilio;
    private String estado;
    private LocalDateTime fecha;
    private Pila<String> historialEstados; 
    private Coladinamica<String> pasosEntrega;
    @SuppressWarnings("unused")
    private String descripcion;

    


    public Pedidos(int codigo, Cliente cliente, Restaurante restaurante, Domicilio domicilio) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.restaurante = restaurante;
        this.domicilio = domicilio;
        this.estado = "Pendiente";
        this.fecha = LocalDateTime.now();
        this.historialEstados = new Pila<>();
        this.pasosEntrega = new Coladinamica<>();

        pasosEntrega.enqueue("El pedido ha sido creado");
        historialEstados.push("Pedido creado en:" + this.fecha);

    }

    @Override
    public String toString() {
    return "\n Pedido #" + codigo + "\nCliente: " + cliente.getNombreCompleto() + "\nRestaurante: " + restaurante.getNombreCompleto() + "\nDomiciliario: " + (domicilio != null ? domicilio.getNombreCompleto() : "No asignado") + "\nEstado: " + estado + "\nFecha: " + fecha + "\n";
    }


    public Pila<String> getHistorialEstados() {
        return historialEstados;
    }

    public void setHistorialEstados(Pila<String> historialEstados) {
        this.historialEstados = historialEstados;
    }

    public Coladinamica<String> getPasosEntrega() {
        return pasosEntrega;
    }

    public void setPasosEntrega(Coladinamica<String> pasosEntrega) {
        this.pasosEntrega = pasosEntrega;
    }

    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public Restaurante getRestaurante() {
        return restaurante;
    }
    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }
    public Domicilio getDomicilio() {
        return domicilio;
    }
    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public LocalDateTime getFecha() {
        return fecha;
    }
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }


public void registrarPaso(String paso) {
    pasosEntrega.enqueue(paso);
}

public void asignarDomiciliario(Domicilio nuevoDomicilio) {
    this.domicilio = nuevoDomicilio;
    registrarPaso("El domiciliario asignado es: " + nuevoDomicilio.getNombreCompleto());
}


public void mostrarPasos() {
    pasosEntrega.printQueue(); 
}


public void cancelarPedido() {
    if (estado.equalsIgnoreCase("Entregado")) {
        System.out.println("Error: No se puede cancelar un pedido ya entregado.");
        return;
    }

    this.estado = "Cancelado";

    historialEstados.push("CANCELADO: " + LocalDateTime.now());
    pasosEntrega.enqueue("Pedido cancelado");

    String quien = (cliente != null ? cliente.getNombreCompleto() : "Cliente desconocido");
    System.out.println("El cliente: " + quien + " canceló el pedido #" + codigo);
}


public void cambiarEstado(String nuevoEstado) {
    if (nuevoEstado == null || nuevoEstado.isEmpty()) {
        System.out.println("Estado inválido.");
        return;
    }
    this.estado = nuevoEstado;
    String registro = "|" + LocalDateTime.now() + "| Estado cambiado a: " + nuevoEstado;
    historialEstados.push(registro);
}

public void mostrarHistorial() {
    if (historialEstados.empty()) {
        System.out.println(" No hay historial de estados.");
        return;
    }
    System.out.println(" Historial de estados del pedido #" + codigo + ":");
    historialEstados.print_stack(); 
}

public Pedidos(String descripcion) {
        this.descripcion = descripcion;
    } 



   
}
