package ar.edu.unju.fi.app;

import ar.edu.unju.fi.model.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Serie dark = new Serie("Dark", "Viajes en el tiempo en Winden", "Baran bo Odar", "Ciencia Ficción");

        Temporada t1 = new Temporada(1);
        Episodio e1 = new Episodio("Secretos", "Desaparición de un niño en la cueva");
        Episodio e2 = new Episodio("Mentiras", "Aparece un cuerpo no identificado");
        t1.agregarEpisodio(e1);
        t1.agregarEpisodio(e2);

        Temporada t2 = new Temporada(2);
        Episodio e3 = new Episodio("Materia Oscura", "El futuro postapocalíptico");
        t2.agregarEpisodio(e3);

        dark.agregarTemporada(t1);
        dark.agregarTemporada(t2);

        // Simulamos acciones del usuario
        System.out.println("--- Evaluando validaciones ---");
        e1.ingresarCalificacion(8); // Debe fallar e imprimir error
        e1.ingresarCalificacion(5); // Correcto

        e2.marcarComoVisto(); // Visto pero no calificado (calificación permanece en -1)

        System.out.println("\n--- Reportes de Temporada 1 ---");
        System.out.println("Episodios vistos T1: " + t1.obtenerTotalEpisodiosVistos() + " / " + t1.getEpisodios().size());
        System.out.println("Promedio calificaciones T1: " + t1.obtenerPromedioCalificaciones());
        System.out.println("¿T1 completa?: " + t1.estanTodosVistos());

        System.out.println("\n--- Reportes Globales de Serie ---");
        System.out.println("Total vistos en toda la serie: " + dark.obtenerTotalEpisodiosVistos());
        System.out.println("Promedio global de la serie: " + dark.obtenerPromedioCalificaciones());
        System.out.println("¿Serie terminada completamente?: " + dark.usuarioVioTodaLaSerie());
	}

}
