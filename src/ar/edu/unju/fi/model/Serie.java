package ar.edu.unju.fi.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Serie {
	
	private final String titulo;
    private final String descripcion;
    private final String creador;
    private final String genero;
    private final List<Temporada> temporadas;

    public Serie(String titulo, String descripcion, String creador, String genero) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.creador = creador;
        this.genero = genero;
        this.temporadas = new ArrayList<>();
    }

    public void agregarTemporada(Temporada temporada) {
        if (temporada == null) {
            throw new IllegalArgumentException("La temporada no puede ser nula.");
        }
        this.temporadas.add(temporada);
    }
    
    /**
     * Servicio: Obtener el total de episodios vistos de una serie.
     * Delega en cada temporada su propio conteo.
     */
    public int obtenerTotalEpisodiosVistos() {
        int totalVistos = 0;
        for (Temporada temp : temporadas) {
            totalVistos += temp.obtenerTotalEpisodiosVistos();
        }
        return totalVistos;
    }
    
    /**
     * Servicio: Obtener el promedio de calificaciones de la serie.
     * IMPORTANTE: No se deben promediar los promedios de las temporadas (error matemático clásico).
     * Se debe dividir la suma total de puntos sobre la cantidad total de capítulos calificados.
     */
    public double obtenerPromedioCalificaciones() {
        int sumaTotal = 0;
        int totalCalificados = 0;

        for (Temporada temp : temporadas) {
            sumaTotal += temp.obtenerSumaCalificaciones();
            totalCalificados += temp.obtenerCantidadEpisodiosCalificados();
        }

        if (totalCalificados == 0) {
            return 0.0;
        }

        return (double) sumaTotal / totalCalificados;
    }
    
    /**
     * Servicio: Determinar si el usuario ya vio todos los episodios de la serie.
     */
    public boolean usuarioVioTodaLaSerie() {
        if (temporadas.isEmpty()) {
            return false;
        }
        for (Temporada temp : temporadas) {
            if (!temp.estanTodosVistos()) {
                return false;
            }
        }
        return true;
    }

    public List<Temporada> getTemporadas() {
        return Collections.unmodifiableList(temporadas);
    }
    
    public String getTitulo() { return titulo; }
    public String getDescripcion() { return descripcion; }
    public String getCreador() { return creador; }
    public String getGenero() { return genero; }

}
