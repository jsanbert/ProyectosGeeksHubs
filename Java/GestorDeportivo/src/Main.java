import modelo.*;
import persistencia.Conector;
import vista.VistaPrincipal;

public class Main {

    public static void main(String[] args) {
        System.out.println("--------- Inicio proyecto pruebas ----------");
        Conector conector = new Conector();

        VistaPrincipal vista = new VistaPrincipal();
        vista.setConector(conector);
        vista.setVisible(true);
    }
}