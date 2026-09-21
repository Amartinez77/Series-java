package ar.edu.unju.fi.model;

import java.util.Objects;

public class Episodio {
	
	private final String id;
    private final String titulo;
    private final String descripcion;

    public Episodio(String id, String titulo, String descripcion) {
        if (id == null || id.isBlank() || titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("El ID y el título son obligatorios.");
        }
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public String getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getDescripcion() { return descripcion; }

    // Necesarios para poder usar Episodio como clave en Maps o comparaciones de colecciones
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Episodio episodio)) return false;
        return Objects.equals(id, episodio.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
