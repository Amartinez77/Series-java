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

    public void agregarTemporada(Temporada t) {
        if (t != null) temporadas.add(t);
    }

    public List<Episodio> getTodosLosEpisodios() {
        List<Episodio> todos = new ArrayList<>();
        for (Temporada t : temporadas) {
            todos.addAll(t.getEpisodios());
        }
        return Collections.unmodifiableList(todos);
    }

    public Temporada buscarTemporada(int numero) {
        return temporadas.stream()
                .filter(t -> t.getNumero() == numero)
                .findFirst()
                .orElse(null);
    }

    public String getTitulo() { return titulo; }
    public List<Temporada> getTemporadas() { return Collections.unmodifiableList(temporadas); }
}
