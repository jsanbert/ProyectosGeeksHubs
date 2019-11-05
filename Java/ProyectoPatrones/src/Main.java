import patron.constructor.ConstructorAudi;
import patron.constructor.Fabrica;
import patron.singleton.Controller;

public class Main {

    public static void main(String[] args) {
//        Fabrica fabrica = new Fabrica(Fabrica.COCHE_AUDI);
//        fabrica.construirCoche();
        Controller c = Controller.getInstance();
    }
}