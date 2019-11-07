package principal;

import modelo.universo.Carta;
import modelo.universo.Curandero;
import modelo.universo.Espadachin;
import modelo.universo.Mago;

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

    public static boolean finJuego;

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

        // Ordenar cartas:
        // - c1.compararPorAtaque(c2)
        // - c1.compararPorDefensa(c2)
        // - c1.compararPorTipoAlfabeticamente(c2)

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
        System.out.println("Elige qué quieres hacer:\n");

        String[] opciones = new String[5];
        opciones[0] = "Listar mazo de cartas";
        opciones[1] = "Atacar";
        opciones[2] = "Usar habilidad especial";
        opciones[3] = "Robar carta aleatoria (máximo 2 - " + cartasRobadasJugador + "/" + MAXIMO_ROBAR +")";
        opciones[4] = "Salir del juego";

        int opc = elegirOpcion(opciones);

        Carta origen, objetivo;
        List<Carta> cartasVivas, mazoSeleccionado;
        switch (opc) {
            case 1:
                System.out.println("¿Qué mazo quieres ver?\n");
                opciones = new String[2];
                opciones[0] = "El tuyo";
                opciones[1] = "El del adversario";
                opc = elegirOpcion(opciones);

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
                opciones = new String[mazoJugador.size()];
                rellenarOpcionesCartas(mazoJugador, opciones);
                opc = elegirOpcion(opciones);
                origen = mazoJugador.get(opc-1);

                System.out.println("Ahora elige la víctima, del mazo del adversario:\n");

                opciones = new String[mazoCPU.size()];
                rellenarOpcionesCartas(mazoCPU, opciones);
                opc = elegirOpcion(opciones);
                objetivo = mazoCPU.get(opc-1);

                origen.atacar(objetivo);
                break;
            case 3:
                gastaTurno = true;

                System.out.println("Elige la carta de tu mazo que realizará la habilidad especial: ");
                cartasVivas = getCartasEnMazo(mazoJugador, true);
                opciones = new String[cartasVivas.size()];
                rellenarOpcionesCartas(cartasVivas, opciones);

                opc = elegirOpcion(opciones);
                origen = cartasVivas.get(opc-1);

                System.out.print("Como [" + origen.getTipo() + "], tienes la habilidad especial [" + origen.getHabilidadEspecial() + "]. ");
                System.out.println("Descripción: " + origen.getDescripcionHabilidadEspecial());

                Main.esperarEnter(ENTER_CONTINUAR);

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
                opc = elegirOpcion(opciones);
                objetivo = mazoSeleccionado.get(opc-1);
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

    public static void rellenarOpcionesCartas(List<Carta> cartas, String[] opciones) {
        for(int i = 0; i < opciones.length; i++) {
            Carta carta = cartas.get(i);
            opciones[i] = getStringFormattedCarta(carta);
        }
    }

    public static String getStringFormattedCarta(Carta carta) {
        return String.format(carta.toStringFormatted(), carta.getTipo(), "", carta.getAtaque(), "", carta.getDefensa(), "", carta.getSalud(), Carta.MAX_SALUD);
    }

    public static List<Carta> getCartasEnMazo(List<Carta> mazo, boolean vivasOMuertas) {
        return mazo.stream().filter(c -> c.estaVivo() == vivasOMuertas).collect(Collectors.toList());
    }

    public static void mostrarCartas(List<Carta> mazoCartas) {
        System.out.println("Mostrando cartas...\n");
        int vivos = 0, i = 1;
        for (Carta c : mazoCartas) {
            System.out.printf("Carta " + (i++) + " - " + getStringFormattedCarta(c) + "\n");
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
        System.out.println("\n" + mensaje);
        sc.nextLine();
    }

    //public static void turnoCPU() {}

    public static int elegirOpcion(String[] opciones) {
        int opc;
        do {
            for (int i = 0; i < opciones.length; i++) {
                System.out.println("[" + (i + 1) + "] -> " + opciones[i]);
            }
            System.out.print("> ");
            opc = sc.nextInt();
            sc.nextLine();
            if(opc < 1 || opc > opciones.length) {
                System.out.println("Error: Opción incorrecta");
                esperarEnter(ENTER_VOLVER_ELEGIR);
            }
        } while(opc < 1 || opc > opciones.length);
        System.out.println("");
        return opc;
    }

    public static boolean checkFinJuego() {
        String msg = "----- FIN DEL JUEGO ------\n\nEl ganador es: ";
        // Si todas las cartas de mi mazo O el del cpu están muertas ---> fin del juego

        if (getCartasEnMazo(mazoCPU, true).isEmpty())
            msg += "Jugador :)";
        else if(getCartasEnMazo(mazoJugador, true).isEmpty())
            msg += "CPU :(";
        else
            return false;

        System.out.println(msg);
        return true;
    }
}