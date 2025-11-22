package Sistema;

import Estructuras_de_datos.Grafo;
import Estructuras_de_datos.Lista;
import Estructuras_de_datos.Vertice;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class Mapa {

    private Grafo grafo;

    public static String[] Barrios = {
            "Centro Histórico", "Bastidas", "Galicia", "El prado", "El jardin", "La ciudadela",
            "Ciudad Equidad", "El mercado", "La paz", "Nuevo Milenio", "Nueva Galicia",
            "Pescaito", "Juan23","Cartagena","Taminaka", "Ojeda","Santa Ana","El pando",
            "11 de Noviembre", "El reposo"
    };

    public Mapa(){
        grafo = new Grafo();
        InicializarMapa();
    }

    private void InicializarMapa(){
        for(String barrio : Barrios){
            grafo.agregarVertice(barrio);
        }

        // CONEXIONES (NODIRIGIDO)
        Nodirigido("Centro Histórico", "Bastidas", 2);
        Nodirigido("Centro Histórico", "Galicia", 3);
        Nodirigido("Centro Histórico", "El prado", 3);
        Nodirigido("Centro Histórico", "El jardin", 3);
        Nodirigido("Centro Histórico", "La ciudadela", 3);
        Nodirigido("Centro Histórico", "Ciudad Equidad", 3);
        Nodirigido("Centro Histórico", "El mercado", 3);
        Nodirigido("Centro Histórico", "La paz", 3);
        Nodirigido("Centro Histórico", "Nuevo Milenio", 3);
        Nodirigido("Centro Histórico", "Nueva Galicia", 3);
        Nodirigido("Centro Histórico", "Pescaito", 3);
        Nodirigido("Centro Histórico", "Juan23", 3);
        Nodirigido("Centro Histórico", "Cartagena", 3);
        Nodirigido("Centro Histórico", "Taminaka", 3);
        Nodirigido("Centro Histórico", "Ojeda", 3);
        Nodirigido("Centro Histórico", "Santa Ana", 3);
        Nodirigido("Centro Histórico", "El pando", 3);
        Nodirigido("Centro Histórico", "11 de Noviembre", 3);
        Nodirigido("Centro Histórico", "El reposo", 3);

        Nodirigido("Bastidas", "Galicia", 2);
        Nodirigido("Bastidas", "El prado", 2.5);
        Nodirigido("Bastidas", "Taminaka", 4);

        Nodirigido("El prado", "El jardin", 1.5);
        Nodirigido("El prado", "La ciudadela", 2);
        Nodirigido("El prado", "Galicia", 2.5);

        Nodirigido("El jardin", "La ciudadela", 1.8);
        Nodirigido("La ciudadela", "Ciudad Equidad", 2);
        Nodirigido("Ciudad Equidad", "El mercado", 2.5);
        Nodirigido("El mercado", "La paz", 1.5);

        Nodirigido("Nuevo Milenio", "Nueva Galicia", 1.5);
        Nodirigido("Nueva Galicia", "Pescaito", 2);
        Nodirigido("Pescaito", "Juan23", 1.8);

        Nodirigido("Cartagena", "Taminaka", 3);
        Nodirigido("Taminaka", "Ojeda", 2.5);
        Nodirigido("Ojeda", "Santa Ana", 2);

        Nodirigido("Santa Ana", "El pando", 2);
        Nodirigido("El pando", "11 de Noviembre", 2.5);
        Nodirigido("11 de Noviembre", "El reposo", 2);

        Nodirigido("La paz", "Nuevo Milenio", 3);
        Nodirigido("Juan23", "Cartagena", 3.5);
        Nodirigido("El reposo", "Ciudad Equidad", 4);
        Nodirigido("Galicia", "Nueva Galicia", 2.5);
    }

    private void Nodirigido(String a, String b, double dist){
        grafo.agregarArista(a, b, dist);
        grafo.agregarArista(b, a, dist);
    }

    // ===============================================
    // DISTANCIA USANDO DIJKSTRA
    // ===============================================
    public double distancia(String origen, String destino){
        HashMap<Vertice, Double> distancias = grafo.Dijkstra(origen);
        if(distancias == null) return Double.MAX_VALUE;

        for(Map.Entry<Vertice, Double> entry : distancias.entrySet()){
            if(entry.getKey().toString().equals(destino)){
                return entry.getValue();
            }
        }
        return Double.MAX_VALUE;
    }

    // ===============================================
    // RESTAURANTE MÁS CERCANO
    // ===============================================
    public Restaurante EncontrarElRestauranteMasCercano(String zonaOrigen, Lista<Restaurante> lista){
        double menor = Double.MAX_VALUE;
        Restaurante elegido = null;

        if (lista == null || lista.tamaño() == 0) return null;

        for (int i = 0; i < lista.tamaño(); i++){
            Restaurante r = lista.obtenerPorIndice(i);
            if (r == null) continue;
            double dist = distancia(zonaOrigen, r.getZona());
            if (dist < menor){
                menor = dist;
                elegido = r;
            }
        }
        return elegido;
    }

    // ===============================================
    // DOMICILIARIOS CERCANOS DISPONIBLES
    // ===============================================
    public Lista<Domicilio> DomiciliariosCercanosDisponibles(String zona, Lista<Domicilio> lista){
        Lista<Domicilio> resultado = new Lista<>();

        for(int i = 0; i < lista.tamaño(); i++){
            Domicilio d = lista.obtenerPorIndice(i);
            if(d != null && d.isDisponible() && distancia(zona, d.getZona()) != Double.MAX_VALUE){
                resultado.insertarFinal(d);
            }
        }
        return resultado;
    }
}
