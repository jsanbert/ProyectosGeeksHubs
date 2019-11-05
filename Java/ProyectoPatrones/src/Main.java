import patron.builder.Fabrica;
import patron.singleton.Controller;

public class Main {

    public static void main(String[] args) {
        // Ejemplo builder
        Fabrica fabrica = new Fabrica(Fabrica.COCHE_AUDI);
        fabrica.construirCoche();

        // Ejemplo singleton
        Controller c = Controller.getInstance();

        // Ejemplo adapter

    }
}