import java.util.Comparator;

public class ComparadorJugadores implements Comparator<Jugador> {

    @Override
    public int compare(Jugador j1, Jugador j2) {
        return j1.compareTo(j2);
    }

}