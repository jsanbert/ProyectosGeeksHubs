import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public enum Tipos {
        ESPADACHIN,
        MAGO,
        MEDICO
    }

    public static Scanner sc;
    public static final int CARTAS_POR_JUGADOR = 5;
    public static final int MAXIMO_ROBAR = 2;

    public static List<Carta> mazoJugador;
    public static List<Carta> mazoCPU;

    public static void main(String[] args) {
        sc = new Scanner(System.in).useLocale(Locale.US);

        mazoJugador = new ArrayList<>();
        mazoCPU = new ArrayList<>();

        // Añadir mínimo 1 carta de cada tipo a jugador y cpu
        for(int i = 0; i < Tipos.values().length; i++) {
            mazoJugador.add(getCartaCorrespondienteInt(i));
            mazoCPU.add(getCartaCorrespondienteInt(i));
        }

        // Resto de cartas hasta el máximo, aleatorias
        for (int i = 0; i < CARTAS_POR_JUGADOR; i++) {
            mazoJugador.add(getCartaAleatoria());
            mazoCPU.add(getCartaAleatoria());
        }

        do {
            turnoJugador();
            turnoCPU();
        } while(checkFinJuego() == false);
    }

    public static Carta getCartaAleatoria() {
        Carta resultado = null;
        int tiposCartaDistintos = Tipos.values().length;
        int numAleatorio = generarNumeroAleatorio(0, tiposCartaDistintos);
        resultado = getCartaCorrespondienteInt(numAleatorio);
        return resultado;
    }

    public static Carta getCartaCorrespondienteInt(int i) {
        Carta resultado = null;
        if (i == Tipos.ESPADACHIN.ordinal()) {
            resultado = new Espadachin();
        }
        else if (i == Tipos.MAGO.ordinal()) {
            resultado = new Mago();
        }
        else if (i == Tipos.MEDICO.ordinal()) {
            resultado = new Medico();
        }
        return resultado;
    }

    public static int generarNumeroAleatorio(int min, int max) {
        return (int) (Math.random() * max + min);
    }

    public static boolean turnoJugador() {
        // Si solo lista las cartas DEL JUGADOR, no gasta turno

        boolean gastaTurno = false;
        System.out.println("*** TURNO JUGADOR ***");
        int opc;
        do {
            System.out.print("Elige qué quieres hacer: ");
            System.out.println("1. Listar mazo de cartas (no gasta turno, si solo miras las tuyas)");
            System.out.println("2. Atacar");
            System.out.println("3. Usar habilidad especial");
            System.out.println("4. Robar carta");
            System.out.print("> ");
            opc = sc.nextInt();
            if(opc < 1 && opc > 4)
                System.out.println("Opción incorrecta\n");
        } while(opc < 1 && opc > 4);

        switch (opc) {
            case 1:
                do {
                    System.out.print("¿Qué mazo quieres seleccionar para ver sus cartas (vivas) y salud?");
                    System.out.println("1. El tuyo");
                    System.out.println("2. El del adversario");
                    System.out.print("> ");
                    opc = sc.nextInt();
                    if(opc < 1 && opc > 4)
                        System.out.println("Opción incorrecta\n");
                } while(opc < 1 && opc > 4);

                List<Carta> mazoSeleccionado;
                if(opc == 1) {
                    mazoSeleccionado = mazoJugador;
                } else {
                    mazoSeleccionado = mazoCPU;
                    gastaTurno = true;
                }

                mostrarCartas(mazoSeleccionado);

                break;
            case 2:
                gastaTurno = true;


                break;
            case 3:
                gastaTurno = true;
                break;
            case 4:
                gastaTurno = true;
                break;
        }

        return gastaTurno;
    }

    public static void mostrarCartas(List<Carta> mazoCartas) {
        System.out.println("Mostrando cartas: ");
        for (Carta c : mazoCartas) {
            System.out.println("-> [" + c.tipo + "] (salud: " + c.getSalud() + "/" + Carta.MAX_SALUD + ")");
        }
    }

    public static void turnoCPU() {}

    public static boolean checkFinJuego() {}
}