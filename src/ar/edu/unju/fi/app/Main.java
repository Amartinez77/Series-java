package ar.edu.unju.fi.app;

import ar.edu.unju.fi.model.*;
import ar.edu.unju.fi.service.SeriesPiratas;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 1. Instanciamos el manager central
        SeriesPiratas plataforma = new SeriesPiratas();

        // 2. Cargamos el catálogo compartido
        Serie breakingBad = new Serie("Breaking Bad", "Un profesor de química entra al narcotráfico", "Vince Gilligan", "Drama");
        Temporada t1 = new Temporada(1);
        t1.agregarEpisodio(new Episodio("BB-101", "Piloto", "Walter White descubre su enfermedad"));
        t1.agregarEpisodio(new Episodio("BB-102", "El gato en la bolsa", "Complicaciones químicas"));

        Temporada t2 = new Temporada(2);
        t2.agregarEpisodio(new Episodio("BB-201", "Siete treinta y siete", "Consecuencias con Tuco"));

        breakingBad.agregarTemporada(t1);
        breakingBad.agregarTemporada(t2);
        plataforma.registrarSerie(breakingBad);

        // 3. Cargamos usuarios independientes
        Usuario user1 = new Usuario("U01", "Ariel");
        Usuario user2 = new Usuario("U02", "Maria");
        plataforma.registrarUsuario(user1);
        plataforma.registrarUsuario(user2);

        // 4. Arrancamos el menú
        MenuConsola menu = new MenuConsola(plataforma);
        menu.iniciar();
    
		
		
		}

}
