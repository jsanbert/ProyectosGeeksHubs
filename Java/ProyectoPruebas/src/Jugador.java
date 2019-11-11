public class Jugador {
    private String nombre;
    private int triples;
    private int dobles;
    private int minutosJugados;

    public Jugador(String nombre, int triples, int dobles, int minutosJugados) {
        this.nombre = nombre;
        this.triples = triples;
        this.dobles = dobles;
        this.minutosJugados = minutosJugados;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTriples() {
        return triples;
    }

    public void setTriples(int triples) {
        this.triples = triples;
    }

    public int getDobles() {
        return dobles;
    }

    public void setDobles(int dobles) {
        this.dobles = dobles;
    }

    public int getMinutosJugados() {
        return minutosJugados;
    }

    public void setMinutosJugados(int minutosJugados) {
        this.minutosJugados = minutosJugados;
    }

    @Override
    public String toString() {
        return "Nombre: " + this.nombre + ", triples: " + this.triples + ", dobles: " + this.dobles + ", mins. jugados: " + this.minutosJugados;
    }

    public int compareTo(Jugador j) {
        int puntos = this.triples * 3 + this.dobles * 2;
        int puntosOtro = j.triples * 3 + j.dobles * 2;
        if(puntos < puntosOtro)
            return 1;
        else if(puntos == puntosOtro)
            return 0;
        else
            return -1;
    }
}
