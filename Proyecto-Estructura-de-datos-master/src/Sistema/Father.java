public abstract class Father {
    protected int codigo;
    protected String nombre;
    protected String zona;
    public  Father(int codigo, String nombre, String zona) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.zona = zona;
    }
    public String getZona() {
        return zona; }
    public int getCodigo() {
        return codigo; }
    public String getNombreCompleto() {
        return nombre; }
    public String getnombre() {
        return nombre; }
}
