package bootcamp;

import java.util.ArrayList;
import java.util.List;

public class Alumno {
    private String nombre;
    private List<Calificacion> calificaciones;

    public Alumno(String nombre, List<Calificacion> calificaciones) {
        this.nombre = nombre;
        this.calificaciones = calificaciones;
    }

    public Alumno(String nombre) {
        this(nombre, new ArrayList<>());
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Calificacion> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(List<Calificacion> calificaciones) {
        this.calificaciones = calificaciones;
    }
}
