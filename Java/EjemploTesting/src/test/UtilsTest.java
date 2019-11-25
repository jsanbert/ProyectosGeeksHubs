package test;

import bootcamp.Utils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void isEven_WithTwo_ShouldTrue() {
        // Arrange
        int number = 2;

        // Act
        boolean result = Utils.isEven(number);

        // Assert
        assertTrue(result);
    }

    @Test
    void isEven_WithOne_ShouldFalse() {
        // Arrange
        int number = 1;

        // Act
        boolean result = Utils.isEven(number);

        // Assert
        assertFalse(result);
    }

    @Test
    void isPrime_WithFive_ShouldTrue() {
        // Arrange
        int number = 5;

        // Act
        boolean result = Utils.isPrime(number);

        // Assert
        assertTrue(result);
    }

    @Test
    void isPrime_WithFour_ShouldFalse() {
        // Arrange
        int number = 4;

        // Act
        boolean result = Utils.isPrime(number);

        // Assert
        assertFalse(result);
    }
}