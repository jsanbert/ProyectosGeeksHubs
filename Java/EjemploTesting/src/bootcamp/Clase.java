package bootcamp;

import java.util.ArrayList;
import java.util.List;

public class Clase {
    private String nombreCurso;
    private List<Alumno> alumnos;

    public Clase() {
        this("Default", new ArrayList<>());
    }

    public Clase(String nombreCurso, List<Alumno> alumnos) {
        this.nombreCurso = nombreCurso;
        this.alumnos = alumnos;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

    public void addAlumno(String nombre) {
        alumnos.add(new Alumno(nombre));
    }

    public int numAlumnos() {
        return alumnos.size();
    }
}
