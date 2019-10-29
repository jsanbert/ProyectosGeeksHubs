import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class CodificadorCesar {
    private int desplazamiento;

    public CodificadorCesar(int desplazamiento) {
        this.desplazamiento = desplazamiento;
    }

    public int getDesplazamiento() { return this.desplazamiento; }

    public void setDesplazamiento(int desplazamiento) { this.desplazamiento = desplazamiento; }

    public String codificar(String mensaje) {
        String criptograma = "";
        for(int i = 0; i < mensaje.length(); i++) {
            char c = mensaje.charAt(i);
            if(c != ' ')
                c += this.desplazamiento;
            criptograma += c;
        }
        return criptograma;
    }

    public String decodificar(String criptograma) {
        String mensaje = "";
        for(int i = 0; i < criptograma.length(); i++) {
            char c = criptograma.charAt(i);
            if(c != ' ')
                c -= this.desplazamiento;
            mensaje += c;
        }
        return mensaje;
    }

    //Cada n caracteres inserta una letra aleatoria
    public String codificarMejorado(String mensaje, int n) {
        String mensajeCodificado = "";
        int count = 1;
        for(int i = 0; i < mensaje.length(); i++) {
            char c = mensaje.charAt(i);
            if(c != ' ')
                c += this.desplazamiento;
            mensajeCodificado += c;
            if(count == n) {
                count = 1;
                // Simbolos ascii: [A-z], [a-z], [, \, ], ^, _, `, {, |, }, ~
                char letraAleatoriaAscii = (char) numeroAleatorio(65, 126);
                while(!(letraAleatoriaAscii > 'A' && letraAleatoriaAscii < 'Z' ||
                        letraAleatoriaAscii > 'a' && letraAleatoriaAscii < 'z')) {

                    letraAleatoriaAscii = (char) numeroAleatorio(65, 126);
                }
                mensajeCodificado += letraAleatoriaAscii;
            }
            count++;
        }
        return mensajeCodificado;
    }

    public String decodificarMejorado(String criptograma, int n) {
        String mensaje = "";
        int count = 1;
        for(int i = 0; i < criptograma.length(); i++) {
            char c = criptograma.charAt(i);
            if(count == n) {
                count = 1;
                continue;
            }
            if(c != ' ')
                c -= this.desplazamiento;
            mensaje += c;
            count++;
        }
        return mensaje;
    }

    public static int numeroAleatorio(int min, int max) {
        return min + (int)(Math.random() * ((max - min) + 1));
    }
}
