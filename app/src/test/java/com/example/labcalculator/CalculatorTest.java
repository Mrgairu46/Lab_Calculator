package com.example.labcalculator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class CalculatorTest {

    private Calculator calculator;

    @Before
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void add_twoNumbers_returnsCorrectSum() {
        double result = calculator.add(2, 3);
        assertEquals(5.0, result, 0.0001);
    }

    @Test
    public void subtract_twoNumbers_returnsCorrectDifference() {
        double result = calculator.subtract(10, 4);
        assertEquals(6.0, result, 0.0001);
    }

    @Test
    public void multiply_twoNumbers_returnsCorrectProduct() {
        double result = calculator.multiply(3, 5);
        assertEquals(15.0, result, 0.0001);
    }

    @Test
    public void divide_twoNumbers_returnsCorrectQuotient() {
        double result = calculator.divide(10, 2);
        assertEquals(5.0, result, 0.0001);
    }

    @Test
    public void divide_byZero_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> calculator.divide(10, 0));
    }
}
