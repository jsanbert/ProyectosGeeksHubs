package vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class PanelAltaCurso extends JFrame {
    private Container panelPrincipal;

    private JPanel columnaPrincipal, filaNombreCurso, filaNumeroAlumnos, filaConfirmar;
    private JLabel labelNombreCurso, labelNumeroAlumnos;
    private JTextField textFieldNombreCurso, textFieldNumeroAlumnos;
    private JButton botonConfirmar, botonCancelar;

    public PanelAltaCurso() {
        super();
        this.setTitle("Gestor deportivo - Panel de alta de cursos");
        this.setLayout(new BorderLayout());
        this.setSize(300, 200);

        panelPrincipal = this.getContentPane();
        panelPrincipal.setLayout(new BorderLayout());
        Dimension maxDimensionesFila = new Dimension((int) panelPrincipal.getMaximumSize().getWidth(), 20);


        columnaPrincipal = new JPanel();
        columnaPrincipal.setBorder(new EmptyBorder(25, 25, 25, 25));
        columnaPrincipal.setSize(30, panelPrincipal.getWidth());
        columnaPrincipal.setLayout(new BoxLayout(columnaPrincipal, BoxLayout.Y_AXIS));

        filaNombreCurso = new JPanel();
        filaNombreCurso.setMaximumSize(maxDimensionesFila);
        filaNombreCurso.setLayout(new BoxLayout(filaNombreCurso, BoxLayout.X_AXIS));
        labelNombreCurso = new JLabel("Nombre curso: ");
        textFieldNombreCurso = new JTextField();
        filaNombreCurso.add(labelNombreCurso);
        filaNombreCurso.add(textFieldNombreCurso);

        columnaPrincipal.add(filaNombreCurso);

        filaNumeroAlumnos = new JPanel();
        filaNumeroAlumnos.setMaximumSize(maxDimensionesFila);
        filaNumeroAlumnos.setLayout(new BoxLayout(filaNumeroAlumnos, BoxLayout.X_AXIS));
        labelNumeroAlumnos = new JLabel("Número alumnos: ");
        textFieldNumeroAlumnos = new JTextField();
        filaNumeroAlumnos.add(labelNumeroAlumnos);
        filaNumeroAlumnos.add(textFieldNumeroAlumnos);

        columnaPrincipal.add(filaNumeroAlumnos);

        filaConfirmar = new JPanel();
        filaConfirmar.setLayout(new BoxLayout(filaConfirmar, BoxLayout.X_AXIS));
        botonConfirmar = new JButton("Confirmar");
        botonConfirmar.addActionListener(actionEvent -> JOptionPane.showMessageDialog(null, "Curso añadido"));
        botonCancelar = new JButton("Cancelar");
        botonCancelar.addActionListener(actionEvent -> this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));
        filaConfirmar.add(botonConfirmar);
        filaConfirmar.add(botonCancelar);

        columnaPrincipal.add(filaConfirmar);

        panelPrincipal.add(columnaPrincipal);
        this.setVisible(true);
    }
}