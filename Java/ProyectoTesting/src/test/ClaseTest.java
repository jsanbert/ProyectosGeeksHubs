package test;

import bootcamp.Clase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class ClaseTest {

    Clase cRellenado;

    @BeforeEach
    void init() {
        cRellenado = new Clase();
        cRellenado.addAlumno("Pepe");
        cRellenado.addAlumno("Jose");
    }

    @DisplayName("Test metodo addAlumno")
    @RepeatedTest(5)
    void testNuevoAlumno() {
        System.out.println("Ejecutando test addAlumno");
        Clase c = new Clase();
        c.addAlumno("Paco");
        assertEquals(1, c.numAlumnos());
    }

    @Test
    @DisplayName("Test contador alumnos")
    void testContadorAlumnos() {
        assertEquals(2, cRellenado.numAlumnos());
    }

    @Test
    void comprobarAlumnos() {
        assertAll("alumnos",
                () -> assertEquals("Pepe", cRellenado.getAlumnos().get(0).getNombre()),
                () -> assertEquals("Jose", cRellenado.getAlumnos().get(1).getNombre())
        );
    }

    @Test
    void testTrueAssumption() {
        assumeTrue(5 < 1);
        assertEquals(5 + 2, 7);
    }
}