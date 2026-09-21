package ar.edu.unju.fi.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Temporada {
	
	private final int numero;
    private final List<Episodio> episodios;

    public Temporada(int numero) {
        if (numero <= 0) throw new IllegalArgumentException("El número de temporada debe ser mayor a 0.");
        this.numero = numero;
        this.episodios = new ArrayList<>();
    }

    public void agregarEpisodio(Episodio e) {
        if (e != null && !episodios.contains(e)) {
            episodios.add(e);
        }
    }

    public int getNumero() { return numero; }
    public List<Episodio> getEpisodios() { return Collections.unmodifiableList(episodios); }
    public int getCantidadEpisodios() { return episodios.size(); }

}
