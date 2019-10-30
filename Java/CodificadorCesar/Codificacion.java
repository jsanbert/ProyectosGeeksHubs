import java.util.ArrayList;
import java.util.Scanner;

public class Codificacion {

    public static ArrayList<Character> Cifrado(String texto){

        ArrayList<Character> numero = new ArrayList<Character>();

        for (int i = 0; i < texto.length(); i++) {

            char cifrado = (char) (texto.charAt(i) + 21 + i);

            numero.add(cifrado);

        }


        return numero;
    };

    public static ArrayList<Character> Descifrado(ArrayList<Character> numero){

        ArrayList<Character> frase = new ArrayList<Character>();

        for (int i = 0; i < numero.size(); i++) {

            char descifrado = (char) (numero.get(i) - 21 - i);
            frase.add(descifrado);

            System.out.print(descifrado);

        }

        return frase;
    };



    public static void main(String[] args) {

        ArrayList<Character> frase_cifrado = new ArrayList<Character>();
        ArrayList<Character> frase_descifrado = new ArrayList<Character>();

        Scanner sc = new Scanner(System.in);

        String texto = sc.nextLine();

        frase_cifrado = Cifrado(texto);

        System.out.println(frase_cifrado);

        frase_descifrado = Descifrado(frase_cifrado);

    }
}

