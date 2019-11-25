package test;

import bootcamp.Calculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    Calculator calculator;

    @BeforeEach
    public void initData() {
        calculator = new Calculator();
    }

    @Test
    public void sum_With4And6_ShouldReturn10() {
        // Arrange
        int a = 4;
        int b = 6;

        // Act
        int result = calculator.sum(a, b);

        // Assert
        assertEquals(result, 10);
    }

    @Test
    public void substract_With4And6_ShouldReturnMinus2() {
        // Arrange
        int a = 4;
        int b = 6;

        // Act
        int result = calculator.substract(a, b);

        // Assert
        assertEquals(result, -2);
    }

    @Test
    public void multiply_With4And6_ShouldReturn24() {
        // Arrange
        int a = 4;
        int b = 6;

        // Act
        int result = calculator.multiply(a, b);

        // Assert
        assertEquals(result, 24);
    }

    @Test
    public void divide_With6And4_ShouldReturn1() {
        // Arrange
        int a = 6;
        int b = 4;

        // Act
        int result = calculator.divide(a, b);

        // Assert
        assertEquals(result, 1);
    }

    @Test
    public void divide_With6And0_ShouldReturn0() {
        // Arrange
        int a = 6;
        int b = 0;

        // Act
        int result = calculator.divide(a, b);

        // Assert
        assertEquals(result, 0);
    }
}