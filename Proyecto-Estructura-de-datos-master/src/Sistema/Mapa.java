import Estructuras_de_datos.Grafo;
import Estructuras_de_datos.Cola;
import Estructuras_de_datos.Lista;
import Estructuras_de_datos.Vertice;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class Mapa{

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
    
    Mapa_Nodirigido("Centro Histórico", "Bastidas", 2);
    Mapa_Nodirigido("Centro Histórico", "Galicia", 3);
    Mapa_Nodirigido("Centro Histórico", "El prado", 3);
    Mapa_Nodirigido("Centro Histórico", "El jardin", 3);
    Mapa_Nodirigido("Centro Histórico", "La ciudadela", 3);
    Mapa_Nodirigido("Centro Histórico", "Ciudad Equidad", 3);
    Mapa_Nodirigido("Centro Histórico", "El mercado", 3);
    Mapa_Nodirigido("Centro Histórico", "La paz", 3);
    Mapa_Nodirigido("Centro Histórico", "Nuevo Milenio", 3);
    Mapa_Nodirigido("Centro Histórico", "Nueva Galicia", 3);
    Mapa_Nodirigido("Centro Histórico", "Pescaito", 3);
    Mapa_Nodirigido("Centro Histórico", "Juan23", 3);
    Mapa_Nodirigido("Centro Histórico", "Cartagena", 3);
    Mapa_Nodirigido("Centro Histórico", "Taminaka", 3);
    Mapa_Nodirigido("Centro Histórico", "Ojeda", 3);
    Mapa_Nodirigido("Centro Histórico", "Santa Ana", 3);
    Mapa_Nodirigido("Centro Histórico", "El pando", 3);
    Mapa_Nodirigido("Centro Histórico", "11 de Noviembre", 3);
    Mapa_Nodirigido("Centro Histórico", "El reposo", 3);  

    Mapa_Nodirigido("Bastidas", "Galicia", 2);
    Mapa_Nodirigido("Bastidas", "El prado", 2.5);
    Mapa_Nodirigido("Bastidas", "Taminaka", 4);

    Mapa_Nodirigido("El prado", "El jardin", 1.5);
    Mapa_Nodirigido("El prado", "La ciudadela", 2);
    Mapa_Nodirigido("El prado", "Galicia", 2.5);
    Mapa_Nodirigido("El jardin", "La ciudadela", 1.8);

    Mapa_Nodirigido("La ciudadela", "Ciudad Equidad", 2);
    Mapa_Nodirigido("Ciudad Equidad", "El mercado", 2.5);
    Mapa_Nodirigido("El mercado", "La paz", 1.5);

    Mapa_Nodirigido("Nuevo Milenio", "Nueva Galicia", 1.5);
    Mapa_Nodirigido("Nueva Galicia", "Pescaito", 2);
    Mapa_Nodirigido("Pescaito", "Juan23", 1.8);

    Mapa_Nodirigido("Cartagena", "Taminaka", 3);
    Mapa_Nodirigido("Taminaka", "Ojeda", 2.5);
    Mapa_Nodirigido("Ojeda", "Santa Ana", 2);

    Mapa_Nodirigido("Santa Ana", "El pando", 2);
    Mapa_Nodirigido("El pando", "11 de Noviembre", 2.5);
    Mapa_Nodirigido("11 de Noviembre", "El reposo", 2);

    Mapa_Nodirigido("La paz", "Nuevo Milenio", 3);
    Mapa_Nodirigido("Juan23", "Cartagena", 3.5);
    Mapa_Nodirigido("El reposo", "Ciudad Equidad", 4);
    Mapa_Nodirigido("Galicia", "Nueva Galicia", 2.5);
}

private void Mapa_Nodirigido(String barrio1, String barrio2, double distancia){
    grafo.agregarArista(barrio1, barrio2, distancia);
    grafo.agregarArista(barrio2, barrio1, distancia);
}

/**
 * Calcula el camino más corto entre dos barrios.
 * @param barrioInicio barrio de inicio
 * @param BarrioFinal barrio destino
 * @return distancia mínima o -1 si no existe
 */
public double CalcularCaminoMásCorto(String barrioInicio, String BarrioFinal){
    
    HashMap<Vertice, Double> distancias = grafo.Dijkstra(barrioInicio);

    if(distancias == null){
        System.out.println("El barrio del que quiere partir no existe.");
        return -1;
    }

    for(Vertice clave : distancias.keySet()){
        if(clave.toString().equals(BarrioFinal)){  
            double distancia = distancias.get(clave);  
            if(distancia == Double.POSITIVE_INFINITY){
                System.out.println("No hay camino disponible entre los dos barrios.");
                return -1;
            } else {
                System.out.println("La distancia más corta entre " + barrioInicio + " y " + BarrioFinal + " es de: " + distancia + " Kilometros.");
                return distancia;
            }
        }
    }
    return -1;
}

public void mostrarDistanciasDesdeXBarrio(String barrioInicio){
    System.out.println("Las distancias desde " + barrioInicio + " usando el algoritmo de Dijkstra son:");
    grafo.mostrarDistanciasDijkstra(barrioInicio);  
}

public boolean Existeruta(String BarrioInicio, String BarrioFinal){
    double distancia = CalcularCaminoMásCorto(BarrioInicio, BarrioFinal);
    boolean existe = distancia != -1 && distancia != Double.POSITIVE_INFINITY;

    if(existe){
        System.out.println("Existe una ruta entre " + BarrioInicio + " y " + BarrioFinal + 
        ". La ruta más corta es: " + distancia + " Kilometros.");
    } else {
        System.out.println("No existe una ruta entre " + BarrioInicio + " y " + BarrioFinal + ".");
    }
    return existe;
}

/**
 * Busca el restaurante más cercano desde un barrio cliente.
 * @param BarrioCliente barrio del cliente
 * @param restaurantes lista de restaurantes
 * @return restaurante más cercano o null
 */
public Restaurante EncontrarElMásCercano(String BarrioCliente, Lista<Restaurante> restaurantes){
    if(restaurantes == null || restaurantes.tamaño() == 0){  
        System.out.println("No hay restaurantes disponibles.");
        return null;
    }
    HashMap<Vertice, Double> distancia = grafo.Dijkstra(BarrioCliente);

    if(distancia == null){
        System.out.println("El barrio del cliente no existe en el mapa.");
        return null;
    }

    Restaurante mascerca = null;
    double minDistancia = Double.POSITIVE_INFINITY;
    // Esas variables nos van a permitir hallar el restaurante mas cercano a cada cliente 

    for(int i = 0; i < restaurantes.tamaño(); i++){  
        Restaurante restaurante = restaurantes.obtenerPorIndice(i);  
        if(restaurante != null){
            String barrioRestaurante = restaurante.getZona();  

            for(Vertice clave : distancia.keySet()){
                if(clave.toString().equals(barrioRestaurante)){  
                    double dist = distancia.get(clave);
                    if(dist < minDistancia){
                        minDistancia = dist;
                        mascerca = restaurante;
                    }
                    break;
                }
            }
        }
    }

    if(mascerca != null){
        System.out.println("El restaurante más cercano al barrio " + BarrioCliente + " es " +
        String.valueOf(mascerca) + " ubicado en " + mascerca.getZona() + " a una distancia de " + 
        minDistancia + " unidades.");
    } else {
        System.out.println("No se encontró ningún restaurante cercano al barrio " + BarrioCliente + ".");
    }
    return mascerca;
}

/**
 * Busca el domiciliario más cercano desde un restaurante.
 * @param BarrioRestaurante barrio del restaurante
 * @param Domiciliarios lista de domiciliarios
 * @return domiciliario más cercano o null
 */
public Domicilio DomiciliarioMásCercano(String BarrioRestaurante, Lista<Domicilio> Domiciliarios){
    if(Domiciliarios == null || Domiciliarios.tamaño() == 0){  
        System.out.println("No hay domiciliarios disponibles.");
        return null;
    }
    HashMap<Vertice, Double> distancia = grafo.Dijkstra(BarrioRestaurante);
    if(distancia == null){
        System.out.println("El barrio del restaurante no existe en el mapa.");
        return null;
    }

    Domicilio mascerca = null;
    double minDistancia = Double.POSITIVE_INFINITY;
    // Esas variables nos van a permitir hallar el domiciliario mas cercano a cada restaurante

    for(int i = 0; i < Domiciliarios.tamaño(); i++){  
        Domicilio domiciliario = Domiciliarios.obtenerPorIndice(i);  
        if(domiciliario != null && domiciliario.isDisponible()){  
            String barrioDomiciliario = domiciliario.getZona();  

            for(Vertice clave : distancia.keySet()){
                if(clave.toString().equals(barrioDomiciliario)){  
                    double dist = distancia.get(clave);
                    if(dist < minDistancia){
                        minDistancia = dist;
                        mascerca = domiciliario;
                    }
                    break;
                }
            }
        }
    }

    if(mascerca != null){
        System.out.println("El domiciliario más cercano al barrio " + BarrioRestaurante + " es " +
       String.valueOf(mascerca) + " ubicado en " + mascerca.getZona() + " a una distancia de " +  
        minDistancia + " Kilometros.");
    } else {
        System.out.println("No se encontró ningún domiciliario cercano al barrio.");
    }
    return mascerca;
}

} 