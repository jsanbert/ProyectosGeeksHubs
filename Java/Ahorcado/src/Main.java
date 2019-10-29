import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        // Crear scanner
        Scanner sc = new Scanner(System.in);

        // Pedir número de palabras a usuario
        System.out.print("> Introduce un número de palabras a insertar: ");
        int numPalabras = sc.nextInt();
        sc.nextLine();

        // Crear array para almacenar palabras
        String[] palabras = new String[numPalabras];

        // Expresión regular para evitar palabras inválidas (con números)
        String regExp = "^[A-Za-zÑñ]+$";
        Pattern r = Pattern.compile(regExp);

        // Pedir palabras al usuario, comprobando que cada una cumpla la expresión regular
        String palabraIntroducida;
        for (int i = 0; i < numPalabras; i++) {
            boolean error;
            do {
                System.out.print("> Introduce palabra [" + (i + 1) + "/" + numPalabras + "] (sin espacios/números): ");
                palabraIntroducida = sc.nextLine().toLowerCase();
                error = r.matcher(palabraIntroducida).find() ? false : true; // si cumple la regexp -> correcto; sino -> incorrecto
                if (error)
                    System.out.println("ERROR: La palabra contiene números y/o espacios entre medias");
                else
                    palabras[i] = palabraIntroducida;
            }
            while (error);
        }

        // Elegir ahora un número aleatorio de entre el número de palabras introducido, para coger una palabra aleatoria
        Random generador = new Random();
        int aleatorio = generador.nextInt(numPalabras); // Desde 0 a numPalabras-1

        // Coger palabra aleatoria
        String palabraEscogida = palabras[aleatorio];
        System.out.println(palabraEscogida);

        // Pedir al usuario número de intentos máximo
        System.out.print("> ¿Cuántos intentos se van a permitir? ");
        int numeroIntentos = sc.nextInt();
        sc.nextLine();

        int intento = 1;
        String seleccionesAnteriores = "";
        do {
            String palabraOculta = "";
            for (int i = 0; i < palabraEscogida.length(); i++) {
                char c = palabraEscogida.charAt(i);
                if (seleccionesAnteriores.contains(Character.toString(c)))
                    palabraOculta += c;
                else
                    palabraOculta += "_";
            }
            System.out.println("\n*******************\n");
            System.out.println("Número de intentos restantes: " + (numeroIntentos - intento + 1) + "\n");
            System.out.println("Palabra oculta: " + palabraOculta + "\n");
            System.out.println("¿Qué vas a hacer?");
            System.out.println("1. Introducir letra");
            System.out.println("2. Resolver");
            int seleccion = -1;
            do {
                System.out.println("> Introduce tu selección [1 ó 2]: ");
                seleccion = sc.nextInt();
                sc.nextLine();
            }
            while (seleccion != 1 && seleccion != 2);

            if (seleccion == 1) { // Usuario decide introducir letra
                String letra;
                do {
                    System.out.print("> Introduce una letra: ");
                    letra = sc.nextLine();
                    if (letra.length() != 1)
                        System.out.println("ERROR: no has introducido una letra.");
                }
                while (letra.length() != 1);

                if (seleccionesAnteriores.contains(letra))
                    System.out.println("ERROR: La letra seleccionada ya fue elegida.");
                else
                    seleccionesAnteriores += letra;
            } else { // Usuario decide resolver
                System.out.println("> ¿Cuál es la palabra?");
                String resultado = sc.nextLine().trim().toLowerCase();
                if (resultado.equals(palabraEscogida)) {
                    System.out.println("¡Es correcto! Fin del juego.");
                    System.out.println("Número de intentos realizados: " + intento);
                    System.exit(0);
                } else {
                    intento++;
                    System.out.println("¡No es correcto!");
                }
            }
        }
        while (intento <= numeroIntentos);
        System.out.println("Se ha alcanzado el número de intentos. Fin del juego.");
    }
}
