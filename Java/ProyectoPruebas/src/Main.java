import modelo.*;
import vista.VentanaPrincipal;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("--------- Inicio proyecto pruebas ----------");

        Curso cMrJeff = new Curso();
        cMrJeff.setNombre("MR JEFF");
        cMrJeff.setNumeroAlumnos(-5);

        VentanaPrincipal ventana = new VentanaPrincipal();
    }
}