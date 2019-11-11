public class Equipo {
    private String nombre;
    private int numJugadores;
    private int partidosGanados;

    public Equipo(String nombre, int numJugadores, int partidosGanados) {
        this.nombre = nombre;
        this.numJugadores = numJugadores;
        this.partidosGanados = partidosGanados;
    }

    public Equipo() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getJugadores() {
        return numJugadores;
    }

    public void setJugadores(int numJugadores) {
        this.numJugadores = numJugadores;
    }

    public int getPartidosGanados() {
        return partidosGanados;
    }

    public void setPartidosGanados(int partidosGanados) {
        this.partidosGanados = partidosGanados;
    }

    @Override
    public String toString() {
        return "Nombre: " + this.nombre + ", núm. jugadores: " + this.numJugadores + ", partidos ganados: " + this.partidosGanados;
    }

    public int compareTo(Equipo e) {
        if(this.partidosGanados < e.partidosGanados)
            return 1;
        else if(this.partidosGanados == e.partidosGanados)
            return 0;
        else
            return -1;
    }
}
