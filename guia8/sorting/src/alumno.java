public class alumno {
    // Atributos
    private int nota;
    private String genero;
    private String nombre;

    // Constructor
    public alumno(int nota, String genero, String nombre) {
        // Verificar que la nota esté en el rango válido (0 al 10)
        if (nota < 0 || nota > 10) {
            throw new IllegalArgumentException("La nota debe estar en el rango de 0 a 10.");
        }

        // Verificar que el género sea "masculino" o "femenino"
        if (!genero.equalsIgnoreCase("masculino") && !genero.equalsIgnoreCase("femenino")) {
            throw new IllegalArgumentException("El género debe ser 'masculino' o 'femenino'.");
        }

        // Asignar valores a los atributos
        this.nota = nota;
        this.genero = genero.toLowerCase(); // Convertir a minúsculas para evitar problemas de mayúsculas/minúsculas
        this.nombre = nombre;
    }

    // Métodos getter
    public int getNota() {
        return nota;
    }

    public String getGenero() {
        return genero;
    }

    public String getNombre() {
        return nombre;
    }

    // Otros métodos según sea necesario
}
