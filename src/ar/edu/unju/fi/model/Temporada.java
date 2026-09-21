package ar.edu.unju.fi.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Temporada {
	
	private final int numero;
	private final List<Episodio> episodios;
	
	public Temporada(int numero) {
		
		if (numero <= 0) {
			
			throw new IllegalArgumentException("El numero de temporada debe ser mayor a 0 ");
			
		}
		this.numero = numero;
		this.episodios = new ArrayList<>();
		
	}
	
	public void agregarEpisodio(Episodio episodio) {
		
		if (episodio == null) {
            throw new IllegalArgumentException("No se puede agregar un episodio nulo.");
        }
        this.episodios.add(episodio);
		
	}
	
	/**
	 * Servicip: Obtiene el total de episodios vistos de una temporada particular
	 */
	public int obtenerTotalEpisodiosVistos() {
		
		int contador = 0;
		for (Episodio ep : episodios) {
			
			if (ep.estaVisto()) {
				contador++;
			}
			
		}
		
		return contador;
	}
	
	/**
     * Auxiliar para el cálculo de promedio en cascada.
     */
    public int obtenerSumaCalificaciones() {
        int suma = 0;
        for (Episodio ep : episodios) {
            // Solo suma episodios calificados (valores de 1 a 5)
            if (ep.estaVisto() && ep.getCalificacion() > 0) {
                suma += ep.getCalificacion();
            }
        }
        return suma;
    }
    
    /**
     * Cantidad de episodios vistos que efectivamente tienen una calificación válida asignada.
     */
    public int obtenerCantidadEpisodiosCalificados() {
        int contador = 0;
        for (Episodio ep : episodios) {
            if (ep.estaVisto() && ep.getCalificacion() > 0) {
                contador++;
            }
        }
        return contador;
    }

    /**
     * Servicio: Obtener el promedio de las calificaciones dadas por el usuario para una temporada.
     */
    public double obtenerPromedioCalificaciones() {
        int calificados = obtenerCantidadEpisodiosCalificados();
        if (calificados == 0) {
            return 0.0;
        }
        return (double) obtenerSumaCalificaciones() / calificados;
    }
    
    /**
     * Evalúa si todos los episodios de esta temporada fueron vistos.
     */
    public boolean estanTodosVistos() {
        if (episodios.isEmpty()) {
            return false;
        }
        for (Episodio ep : episodios) {
            if (!ep.estaVisto()) {
                return false;
            }
        }
        return true;
    }
    
    public int getNumero() { return numero; }

    // Encapsulamiento defensivo: devuelve lista inmodificable para no romper la integridad interna
    public List<Episodio> getEpisodios() {
        return Collections.unmodifiableList(episodios);
    }

}
