package ar.edu.unju.fi.service;

import ar.edu.unju.fi.model.Serie;
import ar.edu.unju.fi.model.Usuario;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SeriesPiratas {

	
	private final List<Serie> catalogo;
    private final List<Usuario> usuarios;

    public SeriesPiratas() {
        this.catalogo = new ArrayList<>();
        this.usuarios = new ArrayList<>();
    }

    public void registrarSerie(Serie s) {
        catalogo.add(s);
    }

    public void registrarUsuario(Usuario u) {
        usuarios.add(u);
    }

    public Usuario buscarUsuarioPorId(String id) {
        return usuarios.stream()
                .filter(u -> u.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElse(null);
    }

    public Serie buscarSeriePorTitulo(String titulo) {
        return catalogo.stream()
                .filter(s -> s.getTitulo().equalsIgnoreCase(titulo))
                .findFirst()
                .orElse(null);
    }

    public List<Serie> getCatalogo() { return Collections.unmodifiableList(catalogo); }
    public List<Usuario> getUsuarios() { return Collections.unmodifiableList(usuarios); }
}
