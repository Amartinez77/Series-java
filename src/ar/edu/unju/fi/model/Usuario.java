package ar.edu.unju.fi.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Usuario {
	
	private final String id;
    private final String nombre;
    private final Map<Episodio, RegistroVisualizacion> historial;

    public Usuario(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.historial = new HashMap<>();
    }

    private RegistroVisualizacion obtenerOCrearRegistro(Episodio ep) {
        return historial.computeIfAbsent(ep, k -> new RegistroVisualizacion());
    }

    // Servicio: Ingresar calificación
    public void calificarEpisodio(Episodio ep, int calificacion) {
        obtenerOCrearRegistro(ep).registrarCalificacion(calificacion);
    }

    public void marcarEpisodioVisto(Episodio ep) {
        obtenerOCrearRegistro(ep).marcarVisto();
    }

    // Servicio: Total episodios vistos de una temporada
    public int obtenerTotalEpisodiosVistos(Temporada temporada) {
        int vistos = 0;
        for (Episodio ep : temporada.getEpisodios()) {
            RegistroVisualizacion reg = historial.get(ep);
            if (reg != null && reg.isVisto()) {
                vistos++;
            }
        }
        return vistos;
    }

    // Servicio: Total episodios vistos de una serie
    public int obtenerTotalEpisodiosVistos(Serie serie) {
        int vistos = 0;
        for (Episodio ep : serie.getTodosLosEpisodios()) {
            RegistroVisualizacion reg = historial.get(ep);
            if (reg != null && reg.isVisto()) {
                vistos++;
            }
        }
        return vistos;
    }

    // Servicio: Promedio de una temporada particular
    public double obtenerPromedioCalificaciones(Temporada temporada) {
        int suma = 0;
        int calificados = 0;

        for (Episodio ep : temporada.getEpisodios()) {
            RegistroVisualizacion reg = historial.get(ep);
            if (reg != null && reg.tieneCalificacionValida()) {
                suma += reg.getCalificacion();
                calificados++;
            }
        }
        return calificados == 0 ? 0.0 : (double) suma / calificados;
    }

    // Servicio: Promedio de una serie completa
    public double obtenerPromedioCalificaciones(Serie serie) {
        int suma = 0;
        int calificados = 0;

        for (Episodio ep : serie.getTodosLosEpisodios()) {
            RegistroVisualizacion reg = historial.get(ep);
            if (reg != null && reg.tieneCalificacionValida()) {
                suma += reg.getCalificacion();
                calificados++;
            }
        }
        return calificados == 0 ? 0.0 : (double) suma / calificados;
    }

    // Servicio: Determinar si ya vio todos los episodios de la serie
    public boolean vioTodosLosEpisodios(Serie serie) {
        List<Episodio> todos = serie.getTodosLosEpisodios();
        if (todos.isEmpty()) return false;

        for (Episodio ep : todos) {
            RegistroVisualizacion reg = historial.get(ep);
            if (reg == null || !reg.isVisto()) {
                return false;
            }
        }
        return true;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
}
