package Sistema;

public class Domicilio {

    private int codigo;
    private String nombreCompleto;
    private String zona;
    private boolean disponible;

    public Domicilio(int codigo, String nombreCompleto, String zona){
        this.codigo = codigo;
        this.nombreCompleto = nombreCompleto;
        this.zona = zona;
        this.disponible = true; // TODOS inician disponibles
    }

    public int getCodigo(){
        return codigo;
    }

    public String getNombreCompleto(){
        return nombreCompleto;
    }

    public String getZona(){
        return zona;
    }

    // === DISPONIBILIDAD CORRECTA ===
    public boolean isDisponible(){
        return disponible;
    }

    public void setDisponible(boolean disponible){
        this.disponible = disponible;
    }
}