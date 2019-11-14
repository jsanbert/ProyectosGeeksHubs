import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        // ArrayList de Equipo
//        List<Equipo> equipos = new ArrayList<>();
//        equipos.add(new Equipo("Barça FC", 20, 3));
//        equipos.add(new Equipo("Real Madrid", 16, 15));
//        equipos.add(new Equipo("Valencia", 18, 7));
//        equipos.add(new Equipo("Villareal", 25, 4));
//        equipos.add(new Equipo("Levante", 13, 10));
//
//        equipos.sort(new ComparadorEquipos()); // Usando comparador
//        equipos.sort((e1, e2) -> e1.compareTo(e2)); // Usando lambdas
//        visualizarEquipos(equipos);

        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(new Jugador("Pepe", 5, 3, 20));
        jugadores.add(new Jugador("Marcos", 1, 3, 5));
        jugadores.add(new Jugador("Aitor", 3, 3, 8));
        jugadores.add(new Jugador("Paco", 8, 3, 11));
        jugadores.add(new Jugador("Jesus", 10, 3, 26));
        jugadores.add(new Jugador("Jorge", 4, 3, 3));
        jugadores.add(new Jugador("Manolo", 9, 3, 50));

        jugadores.sort(new ComparadorJugadores());
        visualizarJugadores(jugadores);

        // --- Contando los triples de jugadores que han jugado más de 10 minutos ---

        // Usando lo clasico
        calculoTriples(jugadores);

        // Usando Streams y lambdas
        int resultado = jugadores.stream().filter(j -> j.getMinutosJugados() > 10).mapToInt(j -> j.getTriples() * 3).sum();
        System.out.println("Puntos de canastas triples de jugadores que han jugado más de 10 minutos: " + resultado + " (\"lambdas\")");

    }

    public static void visualizarEquipos(List<Equipo> equipos) {
        for (Equipo e : equipos) {
            System.out.println(e);
        }
    }

    public static void visualizarJugadores(List<Jugador> jugadores) {
        int sumaTotalTriples = 0;
        for(Jugador jugador : jugadores) {
            System.out.println(jugador);
        }
    }

    public static void calculoTriples(List<Jugador> jugadores) {
        int sumaTotalTriples = 0;
        for(Jugador jugador : jugadores) {
            if(jugador.getMinutosJugados() > 10) {
                int triples = jugador.getTriples();
                sumaTotalTriples += triples * 3;
            }
        }

        System.out.println("\nPuntos de canastas triples de jugadores que han jugado más de 10 minutos: " + sumaTotalTriples + (" (\"clásico\")"));

    }
}
