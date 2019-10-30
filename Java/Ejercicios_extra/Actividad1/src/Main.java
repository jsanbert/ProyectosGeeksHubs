import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Ejecutar Actividad 1 (descomentar)

//        System.out.print("> Introduce un NIF: ");
//        String nif = sc.nextLine();
//        System.out.println(calculoLetraNif("X1234567"));



        // Ejecutar Actividad 2 (descomentar)

//        System.out.print("> Introduce una palabra: ");
//        String palabra = sc.nextLine();
//        System.out.println("La palabra '" + palabra + "' es palíndromo:" + esPalindromo(palabra));


        // Ejecutar Actividad 3 (descomentar)

//         System.out.print("> Introduce una palabra con mayúsculas: ");
//         String palabra = sc.nextLine();
//         System.out.println(aMinusculas(palabra));


        // Ejecutar Actividad 4 (descomentar)


//        System.out.print("> Introduce coeficiente de x^2: ");
//        double coeficienteXCuadrado = sc.nextDouble();
//        sc.nextLine();
//        System.out.print("> Introduce coeficiente de x: ");
//        double coeficienteX = sc.nextDouble();
//        sc.nextLine();
//        System.out.print("> Introduce coeficiente independiente: ");
//        double coeficienteIndependiente = sc.nextDouble();
//        sc.nextLine();
//        double[] resultado = resolverEcuacionSegundoGrado(coeficienteXCuadrado, coeficienteX, coeficienteIndependiente);
//        System.out.println("Resultado ecuación: x1 = " + resultado[0] + ", x2 = " + resultado[1]);

    }

    public static char calculoLetraNif(String nif) {
        char letra = '-';
        if(nif != null) {
            char[] buffer = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B',
                             'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
            nif = nif.trim();
            if(nif.length() == 8) {
                // Chequeo si es extranjero
                switch(nif.charAt(0)) {
                    case 'X':
                        nif = nif.replace('X', '0');
                        break;
                    case 'Y':
                        nif = nif.replace('Y', '1');
                        break;
                    case 'Z':
                        nif = nif.replace('Z', '2');
                        break;
                }
                try {
                    return buffer[(int) (Long.parseLong(nif) % 23)];
                } catch (NumberFormatException e) {
                    System.out.println("Error: el NIF/NIE introducido solo debe contener números, o en su defecto, comenzar por X, Y ó Z.");
                }
            } else {
                System.out.println("Error: el NIF/NIE introducido tiene que tener 8 dígitos.");
            }
        } else {
            System.out.println("Error: NIF/NIE es NULL");
        }
        return letra;
    }

    // Actividad 2
    public static boolean esPalindromo(String palabra) {
        palabra = palabra.toLowerCase();
        StringBuilder sb = new StringBuilder(palabra);
        String palabraRevertida = sb.reverse().toString();
        return palabra.equals(palabraRevertida);
    }

    // Actividad 3
    public static double[] resolverEcuacionSegundoGrado(double a, double b, double c) {
        // -b +- sqrt(b²-4ac)/2a
        double[] resultado = new double[2];

        double raiz = Math.sqrt(Math.pow(b, 2) - 4 * a * c);
        double denominador = 2 * a;

        resultado[0] = (-b - raiz) / denominador;
        resultado[1] = (-b + raiz) / denominador;

        return resultado;
    }

    // Actividad 4
    public static String aMinusculas(String palabra) {
        String resultado = "";
        for (int i = 0; i < palabra.length(); i++) {
            char c = palabra.charAt(i);
            if(c >= 'A' && c <= 'Z') {
                c += 32;
                resultado += c;
            }
        }
        return resultado;
    }
}