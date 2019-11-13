import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraTest {

    @Test
    void suma() {
        assertEquals(3, Calculadora.suma(2, 1));
    }

    @Test
    void resta() {
        assertEquals(1, Calculadora.resta(2, 1));
    }
}