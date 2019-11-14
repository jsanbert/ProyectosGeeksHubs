import java.util.Comparator;

public class ComparadorEquipos implements Comparator<Equipo> {

    @Override
    public int compare(Equipo e1, Equipo e2) {
        return e1.compareTo(e2);
    }

}