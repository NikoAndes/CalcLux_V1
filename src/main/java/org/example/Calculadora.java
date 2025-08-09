package org.example;

/**
 Clase que implementa operaciones matemáticas básicas y avanzadas.
 Excepciones:
 - ArithmeticException: Se usa para errores aritméticos (división por cero, raíz inválida, etc.).
 - IllegalArgumentException: Se usa para argumentos inválidos (por ejemplo, factorial o fibonacci de número negativo o fuera de rango).
 */
public class Calculadora {

    /**
    Suma dos números.
     */
    public double sumar(double a, double b) {
        return a + b;
    }

    /**
    Resta el segundo número al primero.
     */
    public double restar(double a, double b) {
        return a - b;
    }

    /**
    Multiplica dos números.
     */
    public double multiplicar(double a, double b) {
        return a * b;
    }

    /**
    Divide el primer número por el segundo.
    @throws ArithmeticException si el divisor es cero.
     */
    public double dividir(double a, double b) {
        if (b == 0) throw new ArithmeticException("División por cero");
        return a / b;
    }

    /**
    Calcula el seno de un ángulo en grados.
     */
    public double seno(double grados) {
        return Math.sin(Math.toRadians(grados));
    }

    /**
    Calcula el coseno de un ángulo en grados.
     */
    public double coseno(double grados) {
        return Math.cos(Math.toRadians(grados));
    }

    /**
    Calcula la tangente de un ángulo en grados.
    @throws ArithmeticException si la tangente es indefinida.
     */
    public double tangente(double grados) {
        double cos = Math.cos(Math.toRadians(grados));
        if (Math.abs(cos) < 1e-10) throw new ArithmeticException("Tangente indefinida");
        return Math.tan(Math.toRadians(grados));
    }

    /**
    Calcula la raíz enésima de un número.
    @throws ArithmeticException si el índice es cero o raíz par de número negativo.
     */
    public double raizEnesima(double base, double indice) {
        if (indice == 0) throw new ArithmeticException("Índice de raíz no puede ser cero");
        if (base < 0 && ((int)indice) % 2 == 0) throw new ArithmeticException("Raíz par de número negativo");
        return Math.pow(base, 1.0 / indice);
    }

    /**
    Calcula la potencia de un número.
     */
    public double potencia(double base, double exponente) {
        return Math.pow(base, exponente);
    }

    /**
    Calcula el factorial de un número entero no negativo.
    Limita el rango para evitar desbordamiento de tipo long.
    @throws IllegalArgumentException si n es negativo o mayor a 20.
     */
    public long factorial(int n) {
        if (n < 0) throw new IllegalArgumentException("Factorial de número negativo");
        if (n > 20) throw new IllegalArgumentException("Factorial demasiado grande (máximo 20)");
        long resultado = 1;
        for (int i = 2; i <= n; i++) resultado *= i;
        return resultado;
    }

    /**
    Calcula el n-ésimo número de Fibonacci.
    Limita el rango para evitar desbordamiento de tipo long.
    @throws IllegalArgumentException si n es negativo o mayor a 92.
     */
    public long fibonacci(int n) {
        if (n < 0) throw new IllegalArgumentException("Índice negativo en Fibonacci");
        if (n > 92) throw new IllegalArgumentException("Fibonacci demasiado grande (máximo 92)");
        long a = 0, b = 1;
        for (int i = 0; i < n; i++) {
            long temp = a;
            a = b;
            b = temp + b;
        }
        return a;
    }

    /**
    Calcula el mínimo común múltiplo de dos enteros.
    @throws ArithmeticException si alguno de los números es cero.
     */
    public int mcm(int a, int b) {
        if (a == 0 || b == 0) throw new ArithmeticException("MCM con cero no definido");
        return Math.abs(a * b) / mcd(a, b);
    }

    /**
    Calcula el máximo común divisor de dos enteros.
     */
    public int mcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return Math.abs(a);
    }

    /**
    Calcula el valor con IVA incluido.
     */
    public double calcularIVA(double valor, double porcentajeIVA) {
        return valor + (valor * porcentajeIVA / 100.0);
    }
}