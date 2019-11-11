package bootcamp;

public class Calificacion {
    private String asignatura;
    private int calificacion;

    public Calificacion(String asignatura, int calificacion) {
        this.asignatura = asignatura;
        this.calificacion = calificacion;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }
}
