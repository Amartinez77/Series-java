package ar.edu.unju.fi.model;

public class Episodio {
	
	private static final int NO_VISTO = -1;
    private static final int CALIF_MINIMA = 1;
    private static final int CALIF_MAXIMA = 5;

    private final String titulo;
    private final String descripcion;
    private boolean visto;
    private int calificacion;

    public Episodio(String titulo, String descripcion) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("El título del episodio no puede estar vacío.");
        }
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.visto = false;
        this.calificacion = NO_VISTO; // Valor negativo por defecto según consigna
    }

    /**
     * Servicio: Ingresar la calificación de un episodio.
     * Si no es correcto, imprime mensaje y no cambia el valor anterior.
     */
    public void ingresarCalificacion(int nuevaCalificacion) {
        if (nuevaCalificacion < CALIF_MINIMA || nuevaCalificacion > CALIF_MAXIMA) {
            System.out.printf("[!] Error: La calificación %d no es válida. Debe estar entre %d y %d.%n",
                    nuevaCalificacion, CALIF_MINIMA, CALIF_MAXIMA);
            return;
        }
        this.calificacion = nuevaCalificacion;
        this.visto = true; // Si lo califica, automáticamente se asume visto
    }

    public void marcarComoVisto() {
        this.visto = true;
    }

    public boolean estaVisto() {
        return this.visto;
    }

    public int getCalificacion() {
        return this.calificacion;
    }

    public String getTitulo() { return titulo; }
    public String getDescripcion() { return descripcion; }

}
