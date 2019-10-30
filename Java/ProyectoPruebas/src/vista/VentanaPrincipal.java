package vista;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
    private JMenuBar menuTop;
    private JMenu cursos;
    private JPanel menuCentral;
    private JButton boton;

    public VentanaPrincipal() {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(300, 300);

        Container pane = this.getContentPane();

        // Menú superior
        menuTop = new JMenuBar();
        cursos = new JMenu("Cursos");
        menuTop.add(cursos);

        // Panel central
        menuCentral = new JPanel();
        menuCentral.setLayout(new FlowLayout());

        boton = new JButton("Botón");
        
        this.add(menuTop, BorderLayout.NORTH);
        this.add(menuCentral, BorderLayout.CENTER);
        this.add(boton, BorderLayout.SOUTH);
        this.setVisible(true);
    }
}