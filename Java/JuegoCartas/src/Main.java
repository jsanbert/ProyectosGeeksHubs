import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public enum Tipos {
        ESPADACHIN,
        MAGO,
        MEDICO
    }

    public static final String PULSA_ENTER = "Pulsa [ENTER]";
    public static final String ENTER_CONTINUAR = PULSA_ENTER + " para continuar";
    public static final String ENTER_VOLVER_ELEGIR = PULSA_ENTER + " para volver a elegir";


    public static Scanner sc;
    public static final int CARTAS_POR_JUGADOR = 5;
    public static final int MAXIMO_ROBAR = 2;

    public static List<Carta> mazoJugador;
    public static List<Carta> mazoCPU;

    public static int cartasRobadasJugador;
    public static int cartasRobadasCPU;

    public static void main(String[] args) {
        sc = new Scanner(System.in).useLocale(Locale.US);

        cartasRobadasJugador = 0;
        cartasRobadasCPU = 0;

        mazoJugador = new ArrayList<>();
        mazoCPU = new ArrayList<>();

        // Añadir mínimo 1 carta de cada tipo a jugador y cpu
        int tiposDistintos = Tipos.values().length;
        for(int i = 0; i < tiposDistintos; i++) {
            mazoJugador.add(getCartaDeEnum(i));
            mazoCPU.add(getCartaDeEnum(i));
        }

        // Resto de cartas hasta el máximo, aleatorias
        for (int i = tiposDistintos; i < CARTAS_POR_JUGADOR; i++) {
            mazoJugador.add(getCartaAleatoria());
            mazoCPU.add(getCartaAleatoria());
        }

        // Ordenar cartas por puntos de ataque
        mazoJugador.sort((c1, c2) -> c1.compararPorAtaque(c2));
        mazoCPU.sort((c1, c2) -> c1.compararPorAtaque(c2));

        do {
            turnoJugador();
            //turnoCPU();
        } while(checkFinJuego() == false);
    }

    public static Carta getCartaAleatoria() {
        Carta resultado = null;
        int tiposCartaDistintos = Tipos.values().length;
        int numAleatorio = generarNumeroAleatorio(0, tiposCartaDistintos);
        resultado = getCartaDeEnum(numAleatorio);
        return resultado;
    }

    public static Carta getCartaDeEnum(int i) {
        Carta resultado = null;
        if (i == Tipos.ESPADACHIN.ordinal()) {
            resultado = new Espadachin();
        }
        else if (i == Tipos.MAGO.ordinal()) {
            resultado = new Mago();
        }
        else if (i == Tipos.MEDICO.ordinal()) {
            resultado = new Curandero();
        }
        return resultado;
    }

    public static int generarNumeroAleatorio(int min, int max) {
        return (int) (Math.random() * max + min);
    }

    public static boolean turnoJugador() {
        // No se gasta si solo se lista mazo de cartas
        boolean gastaTurno = false;
        System.out.print("\n\n*** TURNO JUGADOR ***\n\n");
        int opc;
        do {
            System.out.println("Elige qué quieres hacer:\n");
            System.out.println("1. Listar mazo de cartas");
            System.out.println("2. Atacar");
            System.out.println("3. Usar habilidad especial");
            System.out.println("4. Robar carta aleatoria (máximo 2 - " + cartasRobadasJugador + "/" + MAXIMO_ROBAR +")");
            System.out.println("5. Salir del juego");
            System.out.print("> ");
            opc = sc.nextInt();
            sc.nextLine();
            if(opc < 1 || opc > 5) {
                System.out.println("Error: Opción incorrecta");
                esperarEnter(ENTER_VOLVER_ELEGIR);
            }
        } while(opc < 1 && opc > 4);

        Carta origen, objetivo;
        List<Carta> cartasVivas, mazoSeleccionado;
        switch (opc) {
            case 1:
                do {
                    System.out.println("\n¿Qué mazo quieres seleccionar para ver sus cartas y salud?\n");
                    System.out.println("1. El tuyo");
                    System.out.println("2. El del adversario");
                    System.out.print("> ");
                    opc = sc.nextInt();
                    sc.nextLine();
                    if(opc < 1 || opc > 2) {
                        System.out.println("Error: Opción incorrecta");
                        esperarEnter(ENTER_VOLVER_ELEGIR);
                    }
                } while(opc < 1 && opc > 2);

                if(opc == 1) {
                    mazoSeleccionado = mazoJugador;
                } else {
                    mazoSeleccionado = mazoCPU;
                }

                mostrarCartas(mazoSeleccionado);
                esperarEnter(ENTER_CONTINUAR);

                break;
            case 2:
                gastaTurno = true;
                System.out.println("Elige la carta de tu mazo que realizará el ataque:\n");
                String[] opciones = new String[mazoJugador.size()];
                rellenarOpcionesCartas(mazoJugador, opciones);
                opc = elegirOpcionCarta(opciones, opciones.length);
                origen = mazoJugador.get(opc);

                System.out.println("Ahora elige la víctima, del mazo del adversario:\n");

                opciones = new String[mazoCPU.size()];
                rellenarOpcionesCartas(mazoCPU, opciones);
                opc = elegirOpcionCarta(opciones, opciones.length);
                objetivo = mazoCPU.get(opc);

                origen.atacar(objetivo);
                break;
            case 3:
                gastaTurno = true;

                System.out.println("Elige la carta de tu mazo que realizará la habilidad especial: ");
                cartasVivas = getCartasEnMazo(mazoJugador, true);
                opciones = new String[cartasVivas.size()];
                rellenarOpcionesCartas(cartasVivas, opciones);

                opc = elegirOpcionCarta(opciones, opciones.length);
                origen = cartasVivas.get(opc);

                System.out.print("Como [" + origen.getTipo() + "], tienes la habilidad especial [" + origen.getHabilidadEspecial() + "]. ");
                System.out.println("Descripción: " + origen.getDescripcionHabilidadEspecial());

                System.out.println("\nAhora elige la carta objetivo: ");

                mazoSeleccionado = null;
                if(origen.getTipo().equals("Espadachín")) {
                    mazoSeleccionado = mazoCPU;
                } else if (origen.getTipo().equals("Mago")) {
                    mazoSeleccionado = mazoJugador;
                } else if(origen.getTipo().equals("Curandero")) {
                    mazoSeleccionado = mazoJugador;
                }

                opciones = new String[mazoSeleccionado.size()];
                rellenarOpcionesCartas(mazoSeleccionado, opciones);
                opc = elegirOpcionCarta(opciones, opciones.length);
                objetivo = mazoSeleccionado.get(opc);
                origen.habilidadEspecial(objetivo);
                break;
            case 4:
                if(cartasRobadasJugador < MAXIMO_ROBAR) {
                    cartasRobadasJugador++;
                    mazoJugador.add(getCartaAleatoria());
                    gastaTurno = true;
                } else {
                    System.out.println("Ya no puedes robar más cartas.");
                    esperarEnter(ENTER_CONTINUAR);
                }
                break;
            case 5:
                System.out.println("--- Finalizando juego ---");
                System.exit(0);
                break;
        }

        return gastaTurno;
    }

    private static void rellenarOpcionesCartas(List<Carta> cartas, String[] opciones) {
        for(int i = 0; i < opciones.length; i++) {
            Carta carta = cartas.get(i);
            opciones[i] = String.format(carta.toStringFormatted(), carta.getTipo(), "", carta.getAtaque(), "", carta.getSalud(), Carta.MAX_SALUD);
        }
    }

    private static List<Carta> getCartasEnMazo(List<Carta> mazo, boolean vivasOMuertas) {
        return mazoJugador.stream().filter(c -> c.estaVivo() == vivasOMuertas).collect(Collectors.toList());
    }

    public static void mostrarCartas(List<Carta> mazoCartas) {
        System.out.println("Mostrando cartas...\n");
        int vivos = 0, i = 1;
        for (Carta c : mazoCartas) {
            String strFormatted = c.toStringFormatted();
            //System.out.println(strFormatted)        int i = 1;;
            System.out.printf("Carta " + (i++) + " - " + strFormatted + "\n", c.getTipo(), "", c.getAtaque(), "", c.getSalud(), Carta.MAX_SALUD);
            if(c.estaVivo())
                vivos++;
        }
        System.out.println("------------------------");
        System.out.println("Total: " + mazoCartas.size());
        System.out.println("Vivos: " + vivos);
        System.out.println("Muertos: " + (mazoCartas.size() - vivos));
        System.out.println("------------------------");
    }

    public static void esperarEnter(String mensaje) {
        System.out.println(mensaje);
        sc.nextLine();
    }

    //public static void turnoCPU() {}

    public static int elegirOpcionCarta(String[] opciones, int numOpciones) {
        int opc;
        for (int i = 0; i < numOpciones; i++) {
            System.out.println("[" + (i + 1) + "] -> " + opciones[i]);
        }
        do {
            System.out.print("> ");
            opc = sc.nextInt();
            sc.nextLine();
            if(opc < 1 || opc > numOpciones) {
                System.out.println("Error: Opción incorrecta");
                esperarEnter(ENTER_VOLVER_ELEGIR);
            }
        } while(opc < 1 && opc > numOpciones);
        return opc-1;
    }

    public static boolean checkFinJuego() {
        return false;
    }
}