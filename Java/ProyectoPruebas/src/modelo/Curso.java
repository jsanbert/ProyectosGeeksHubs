package modelo;

public class Curso {
    private String nombre;
    private int numeroAlumnos;

    public Curso(String nombre, int numeroAlumnos) {
        this.nombre = nombre;
        this.setNumeroAlumnos(numeroAlumnos);
    }

    public Curso() {}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroAlumnos() {
        return numeroAlumnos;
    }

    public void setNumeroAlumnos(int numeroAlumnos) {
        if(numeroAlumnos > 0)
            this.numeroAlumnos = numeroAlumnos;
        else
            this.numeroAlumnos = 0;
    }
}
