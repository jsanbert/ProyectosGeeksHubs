package vista;

import modelo.Curso;
import persistencia.Conector;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class PanelAltaCurso extends JFrame {
    private Container panelPrincipal;
    public static final int ANCHURA_MAX = 300, ALTURA_MAX = 200;
    public static final int ALTURA_MAX_FILA = 20;

    public Conector conector;

    private JPanel columnaPrincipal, filaNombreCurso, filaNumeroAlumnos, filaConfirmar;

    public PanelAltaCurso() {
        super();
        this.setTitle("Gestor deportivo - Panel de alta de cursos");
        this.setLayout(new BorderLayout());
        this.setSize(ANCHURA_MAX, ALTURA_MAX);

        panelPrincipal = this.getContentPane();
        panelPrincipal.setLayout(new BorderLayout());

        columnaPrincipal = new JPanel();
        columnaPrincipal.setBorder(new EmptyBorder(25, 25, 25, 25));
        columnaPrincipal.setSize(30, panelPrincipal.getWidth());
        columnaPrincipal.setLayout(new BoxLayout(columnaPrincipal, BoxLayout.Y_AXIS));

        filaNombreCurso = this.crearFilaTextLabel("Nombre del curso: ");

        columnaPrincipal.add(filaNombreCurso);

        filaNumeroAlumnos = this.crearFilaTextLabel("Número de alumnos: ");

        columnaPrincipal.add(filaNumeroAlumnos);

        filaConfirmar = crearFilaConfirmacion();
        filaConfirmar.setBorder(new EmptyBorder(10,10,10,10));

        columnaPrincipal.add(filaConfirmar);

        panelPrincipal.add(columnaPrincipal);
    }

    public JPanel crearFilaTextLabel(String labelFilaString) {
        JPanel fila = new JPanel();
        Dimension maxDimensionesFila = new Dimension(ANCHURA_MAX, ALTURA_MAX_FILA);
        Dimension minDimensionLabel = new Dimension(125, ALTURA_MAX_FILA);
        fila.setMaximumSize(maxDimensionesFila);
        fila.setLayout(new BoxLayout(fila, BoxLayout.X_AXIS));
        JLabel label = new JLabel (labelFilaString);
        label.setMinimumSize(minDimensionLabel);
        label.setPreferredSize(minDimensionLabel);
        fila.add(label);
        JTextField textField = new JTextField();
        fila.add(textField);
        return fila;
    }

    public JPanel crearFilaConfirmacion() {
        JPanel fila = new JPanel();
        fila.setLayout(new BoxLayout(fila, BoxLayout.X_AXIS));
        JButton botonConfirmar = new JButton("Confirmar");
        botonConfirmar.addActionListener(actionEvent -> {
            String nombre = ((JTextField) filaNombreCurso.getComponent(1)).getText();
            String numAlumnos = ((JTextField) filaNumeroAlumnos.getComponent(1)).getText();
            Curso curso = new Curso(nombre, Integer.parseInt(numAlumnos));

            boolean insertado = conector.insertarCurso(curso);
            if(insertado)
                JOptionPane.showMessageDialog(null, "Curso añadido");
            else
                JOptionPane.showMessageDialog(null, "Hubo un error al añadir el curso");

        });
        JButton botonCancelar = new JButton("Cancelar");
        botonCancelar.addActionListener(actionEvent -> this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));
        fila.add(botonConfirmar);
        fila.add(botonCancelar);
        return fila;
    }

    public void setConector(Conector conector) {
        this.conector = conector;
    }
}