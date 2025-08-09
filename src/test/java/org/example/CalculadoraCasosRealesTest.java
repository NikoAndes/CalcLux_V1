package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Pruebas unitarias para validar el uso de funciones matemáticas de la calculadora en contextos reales.

 * Esta clase contiene ejemplos prácticos de cómo las funciones matemáticas pueden aplicarse en situaciones cotidianas
 * o profesionales, como compras, ingeniería, sincronización de procesos, fracciones y combinatoria.
 */
public class CalculadoraCasosRealesTest {

    Calculadora calc = new Calculadora();

    /**
     * Caso real: Cálculo de IVA en una compra.
     * Un cliente realiza una compra de $250.000 con un IVA del 19%.
     * Se espera que el total con IVA sea $297.500.
     */
    @Test
    void testIVAEnCompra() {
        assertEquals(297500.0, calc.calcularIVA(250000, 19), 1e-10);
    }

    /**
     * Caso real: Raíz cúbica en ingeniería eléctrica.
     * Se desea calcular la raíz cúbica de 1000 para determinar la corriente en un sistema trifásico.
     */
    @Test
    void testRaizCubicaIngenieria() {
        assertEquals(10.0, calc.raizEnesima(1000, 3), 1e-10);
    }

    /**
     * Caso real: MCM en sincronización de ciclos de máquinas.
     * Dos máquinas tienen ciclos de 12 y 18 segundos. Se desea saber cuándo coincidirán nuevamente.
     */
    @Test
    void testMcmSincronizacion() {
        assertEquals(36, calc.mcm(12, 18));
    }

    /**
     * Caso real: MCD en simplificación de fracciones.
     * Se desea simplificar la fracción 48/18. El MCD permite reducirla a su forma más simple.
     */
    @Test
    void testMcdFracciones() {
        assertEquals(6, calc.mcd(48, 18));
    }

    /**
     * Caso real: Factorial en combinatoria.
     * Se desea calcular cuántas formas hay de ordenar 5 elementos distintos (5! = 120).
     */
    @Test
    void testFactorialCombinatoria() {
        assertEquals(120, calc.factorial(5));
    }
}
