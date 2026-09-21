package ar.edu.unju.fi.model;

public class RegistroVisualizacion {
	
	private static final int NO_VISTO = -1;
    private boolean visto;
    private int calificacion;

    public RegistroVisualizacion() {
        this.visto = false;
        this.calificacion = NO_VISTO;
    }

    public void registrarCalificacion(int puntos) {
        if (puntos < 1 || puntos > 5) {
            System.out.printf("[!] Error: Calificación inválida (%d). Debe ser de 1 a 5.%n", puntos);
            return;
        }
        this.calificacion = puntos;
        this.visto = true;
    }

    public void marcarVisto() {
        this.visto = true;
    }

    public boolean isVisto() { return visto; }
    public int getCalificacion() { return calificacion; }
    public boolean tieneCalificacionValida() { return visto && calificacion >= 1 && calificacion <= 5; }

}
