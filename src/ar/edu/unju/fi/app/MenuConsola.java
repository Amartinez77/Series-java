package ar.edu.unju.fi.app;

import ar.edu.unju.fi.model.*;
import ar.edu.unju.fi.service.SeriesPiratas;
import java.util.List;
import java.util.Scanner;

public class MenuConsola {
	
	private final Scanner scanner = new Scanner(System.in);
    private final SeriesPiratas sistema;
    private Usuario usuarioActivo;

    public MenuConsola(SeriesPiratas sistema) {
        this.sistema = sistema;
        // Por defecto seleccionamos al primer usuario cargado
        if (!sistema.getUsuarios().isEmpty()) {
            this.usuarioActivo = sistema.getUsuarios().get(0);
        }
    }

    public void iniciar() {
        int opcion = -1;
        do {
            mostrarEncabezado();
            System.out.println("1. Cambiar de usuario activo");
            System.out.println("2. Ver / Calificar un episodio");
            System.out.println("3. Ver estadísticas de una Temporada");
            System.out.println("4. Ver estadísticas de una Serie completa");
            System.out.println("0. Salir");
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1 -> cambiarUsuario();
                case 2 -> calificarEpisodio();
                case 3 -> estadisticasTemporada();
                case 4 -> estadisticasSerie();
                case 0 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("[!] Opción inválida.");
            }
        } while (opcion != 0);
    }

    private void mostrarEncabezado() {
        System.out.println("\n==========================================");
        System.out.println("        PLATAFORMA SERIES PIRATAS         ");
        System.out.printf(" Usuario activo: %s (ID: %s)%n", 
                usuarioActivo != null ? usuarioActivo.getNombre() : "Ninguno",
                usuarioActivo != null ? usuarioActivo.getId() : "-");
        System.out.println("==========================================");
    }

    private void cambiarUsuario() {
        System.out.println("\n--- Seleccionar Usuario ---");
        List<Usuario> users = sistema.getUsuarios();
        for (int i = 0; i < users.size(); i++) {
            System.out.printf("%d. %s (ID: %s)%n", i + 1, users.get(i).getNombre(), users.get(i).getId());
        }
        int seleccion = leerEntero("Elija el número de usuario: ");
        if (seleccion >= 1 && seleccion <= users.size()) {
            usuarioActivo = users.get(seleccion - 1);
            System.out.println("[OK] Sesión cambiada a: " + usuarioActivo.getNombre());
        } else {
            System.out.println("[!] Opción inválida.");
        }
    }

    private void calificarEpisodio() {
        Serie serie = seleccionarSerie();
        if (serie == null) return;

        Temporada temp = seleccionarTemporada(serie);
        if (temp == null) return;

        List<Episodio> episodios = temp.getEpisodios();
        for (int i = 0; i < episodios.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, episodios.get(i).getTitulo());
        }
        int epIdx = leerEntero("Seleccione el episodio: ") - 1;
        if (epIdx < 0 || epIdx >= episodios.size()) {
            System.out.println("[!] Episodio inválido.");
            return;
        }

        Episodio ep = episodios.get(epIdx);
        System.out.println("1. Marcar solo como visto");
        System.out.println("2. Asignar calificación (1 al 5)");
        int accion = leerEntero("Acción: ");

        if (accion == 1) {
            usuarioActivo.marcarEpisodioVisto(ep);
            System.out.println("[OK] Episodio marcado como visto.");
        } else if (accion == 2) {
            int nota = leerEntero("Ingrese la calificación (1-5): ");
            usuarioActivo.calificarEpisodio(ep, nota);
        } else {
            System.out.println("[!] Opción inválida.");
        }
    }

    private void estadisticasTemporada() {
        Serie serie = seleccionarSerie();
        if (serie == null) return;
        Temporada temp = seleccionarTemporada(serie);
        if (temp == null) return;

        System.out.println("\n--- Reporte de Temporada " + temp.getNumero() + " para " + usuarioActivo.getNombre() + " ---");
        System.out.printf("Episodios vistos : %d de %d%n", 
                usuarioActivo.obtenerTotalEpisodiosVistos(temp), temp.getCantidadEpisodios());
        System.out.printf("Promedio de notas: %.2f%n", usuarioActivo.obtenerPromedioCalificaciones(temp));
    }

    private void estadisticasSerie() {
        Serie serie = seleccionarSerie();
        if (serie == null) return;

        System.out.println("\n--- Reporte Global de '" + serie.getTitulo() + "' para " + usuarioActivo.getNombre() + " ---");
        System.out.printf("Total episodios vistos : %d de %d%n",
                usuarioActivo.obtenerTotalEpisodiosVistos(serie), serie.getTodosLosEpisodios().size());
        System.out.printf("Promedio global de notas: %.2f%n", usuarioActivo.obtenerPromedioCalificaciones(serie));
        System.out.printf("¿Vio la serie completa?  : %s%n", 
                usuarioActivo.vioTodosLosEpisodios(serie) ? "SÍ" : "NO");
    }

    private Serie seleccionarSerie() {
        List<Serie> catalogo = sistema.getCatalogo();
        if (catalogo.isEmpty()) {
            System.out.println("[!] No hay series registradas.");
            return null;
        }
        System.out.println("\nSeleccione una serie:");
        for (int i = 0; i < catalogo.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, catalogo.get(i).getTitulo());
        }
        int idx = leerEntero("Número de serie: ") - 1;
        if (idx < 0 || idx >= catalogo.size()) {
            System.out.println("[!] Selección inválida.");
            return null;
        }
        return catalogo.get(idx);
    }

    private Temporada seleccionarTemporada(Serie serie) {
        List<Temporada> temps = serie.getTemporadas();
        System.out.println("Seleccione temporada:");
        for (Temporada t : temps) {
            System.out.printf("Temporada %d (%d episodios)%n", t.getNumero(), t.getCantidadEpisodios());
        }
        int num = leerEntero("Número de temporada: ");
        Temporada t = serie.buscarTemporada(num);
        if (t == null) System.out.println("[!] Temporada no encontrada.");
        return t;
    }

    private int leerEntero(String msg) {
        while (true) {
            System.out.print(msg);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("[!] Por favor ingrese un número entero.");
            }
        }
    }

}
