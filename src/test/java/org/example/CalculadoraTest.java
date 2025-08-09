// src/test/java/org/example/CalculadoraTest.java
package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase Calculadora.
 * Justificación:

 * Estas pruebas se realizan para:
 * - Verificar que los métodos matemáticos funcionan correctamente.
 * - Detectar errores en casos normales y extremos (como divisiones por cero o argumentos inválidos).
 * - Aprender el uso de JUnit y la importancia de las pruebas automáticas en el desarrollo de software.

 * Ejemplos de casos reales:
 * - Un usuario puede intentar dividir por cero o calcular el factorial de un número negativo.
 * - Se debe asegurar que operaciones como el cálculo de IVA o el MCM sean correctas para cualquier entrada válida.
 */
class CalculadoraTest {

    Calculadora calc = new Calculadora();

    @Test
    void testSumar() {
        assertEquals(5.0, calc.sumar(2, 3));
        assertEquals(-1.0, calc.sumar(-2, 1));
    }

    @Test
    void testRestar() {
        assertEquals(1.0, calc.restar(4, 3));
        assertEquals(-5.0, calc.restar(-2, 3));
    }

    @Test
    void testMultiplicar() {
        assertEquals(6.0, calc.multiplicar(2, 3));
        assertEquals(0.0, calc.multiplicar(0, 5));
    }

    @Test
    void testDividir() {
        assertEquals(2.0, calc.dividir(6, 3));
        assertThrows(ArithmeticException.class, () -> calc.dividir(5, 0));
    }

    @Test
    void testSeno() {
        assertEquals(0.0, calc.seno(0), 1e-10);
        assertEquals(1.0, calc.seno(90), 1e-10);
    }

    @Test
    void testCoseno() {
        assertEquals(1.0, calc.coseno(0), 1e-10);
        assertEquals(0.0, calc.coseno(90), 1e-10);
    }

    @Test
    void testTangente() {
        assertEquals(1.0, calc.tangente(45), 1e-10);
        assertThrows(ArithmeticException.class, () -> calc.tangente(90));
    }

    @Test
    void testRaizEnesima() {
        assertEquals(3.0, calc.raizEnesima(27, 3), 1e-10);
        assertThrows(ArithmeticException.class, () -> calc.raizEnesima(-8, 2));
        assertThrows(ArithmeticException.class, () -> calc.raizEnesima(8, 0));
    }

    @Test
    void testPotencia() {
        assertEquals(8.0, calc.potencia(2, 3));
        assertEquals(1.0, calc.potencia(5, 0));
    }

    @Test
    void testFactorial() {
        assertEquals(120, calc.factorial(5));
        assertThrows(IllegalArgumentException.class, () -> calc.factorial(-1));
        assertThrows(IllegalArgumentException.class, () -> calc.factorial(21));
    }

    @Test
    void testFibonacci() {
        assertEquals(8, calc.fibonacci(6));
        assertThrows(IllegalArgumentException.class, () -> calc.fibonacci(-1));
        assertThrows(IllegalArgumentException.class, () -> calc.fibonacci(93));
    }

    @Test
    void testMcd() {
        assertEquals(6, calc.mcd(54, 24));
        assertEquals(1, calc.mcd(17, 13));
    }

    @Test
    void testMcm() {
        assertEquals(12, calc.mcm(3, 4));
        assertThrows(ArithmeticException.class, () -> calc.mcm(0, 5));
    }

    @Test
    void testCalcularIVA() {
        assertEquals(119.0, calc.calcularIVA(100, 19), 1e-10);
        assertEquals(0.0, calc.calcularIVA(0, 19), 1e-10);
    }
}