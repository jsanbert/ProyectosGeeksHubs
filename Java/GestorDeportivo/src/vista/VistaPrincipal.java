package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaPrincipal extends JFrame {
    private JMenuBar menuTop;
    private JMenu cursosMenu;
    private JPanel menuCentral;
    private JButton boton;

    public VistaPrincipal() {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Gestor deportivo - Menú Principal");
        this.setLayout(new BorderLayout());
        this.setSize(300, 300);

        // Menú superior
        menuTop = new JMenuBar();
        cursosMenu = new JMenu("Cursos");
        JMenuItem item = new JMenuItem("Nuevo curso");
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                PanelAltaCurso panel = new PanelAltaCurso();
            }
        });
        cursosMenu.add(item);
        menuTop.add(cursosMenu);

        // Panel central
        menuCentral = new JPanel();
        menuCentral.setLayout(new FlowLayout());
        
        this.add(menuTop, BorderLayout.NORTH);
        this.add(menuCentral, BorderLayout.CENTER);
        this.setVisible(true);
    }
}