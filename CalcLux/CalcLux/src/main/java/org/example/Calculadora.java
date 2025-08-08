package org.example;

public class Calculadora {

    // Método para sumar dos números
    public double sumar(double a, double b) {
        // Retorna la suma de a y b
        return a + b;
    }

    // Método para restar dos números
    public double restar(double a, double b) {
        // Retorna la resta de a menos b
        return a - b;
    }

    // Método para multiplicar dos números
    public double multiplicar(double a, double b) {
        // Retorna el producto de a y b
        return a * b;
    }

    // Método para dividir dos números
    public double dividir(double a, double b) {
        // Validamos que b no sea cero para evitar división por cero
        if (b == 0) {
            throw new ArithmeticException("Error: División por cero");
        }
        return a / b;
    }

    // Método para calcular el seno de un ángulo en grados
    public double seno(double grados) {
        // Convertimos grados a radianes y aplicamos Math.sin
        return Math.sin(Math.toRadians(grados));
    }

    // Método para calcular el coseno de un ángulo en grados
    public double coseno(double grados) {
        // Convertimos grados a radianes y aplicamos Math.cos
        return Math.cos(Math.toRadians(grados));
    }

    // Método para calcular la tangente de un ángulo en grados
    public double tangente(double grados) {
        // Convertimos grados a radianes y aplicamos Math.tan
        return Math.tan(Math.toRadians(grados));
    }

    // Método para calcular la raíz enésima de un número
    public double raizEnesima(double base, double indice) {
        // Validamos que el índice no sea cero y que la base no sea negativa si el índice es par
        if (indice == 0) {
            throw new ArithmeticException("Error: Índice de raíz no puede ser cero");
        }
        if (base < 0 && indice % 2 == 0) {
            throw new ArithmeticException("Error: Raíz par de número negativo");
        }
        return Math.pow(base, 1.0 / indice);
    }

    // Método para calcular la potencia enésima de un número
    public double potencia(double base, double exponente) {
        // Retorna base elevado al exponente
        return Math.pow(base, exponente);
    }

    // Método para calcular el factorial de un número entero
    public long factorial(int n) {
        // Validamos que n no sea negativo
        if (n < 0) {
            throw new IllegalArgumentException("Error: Factorial de número negativo");
        }
        long resultado = 1;
        for (int i = 2; i <= n; i++) {
            resultado *= i;
        }
        return resultado;
    }

    // Método para calcular el n-ésimo número de la serie de Fibonacci
    public long fibonacci(int n) {
        // Validamos que n no sea negativo
        if (n < 0) {
            throw new IllegalArgumentException("Error: Índice negativo en Fibonacci");
        }
        long a = 0, b = 1;
        for (int i = 0; i < n; i++) {
            long temp = a;
            a = b;
            b = temp + b;
        }
        return a;
    }

    // Método para calcular el Mínimo Común Múltiplo (MCM)
    public int mcm(int a, int b) {
        // Validamos que ninguno sea cero
        if (a == 0 || b == 0) {
            throw new ArithmeticException("Error: MCM con cero no definido");
        }
        return Math.abs(a * b) / mcd(a, b);
    }

    // Método para calcular el Máximo Común Divisor (MCD)
    public int mcd(int a, int b) {
        // Usamos el algoritmo de Euclides
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return Math.abs(a);
    }

    // Método para calcular el valor con IVA
    public double calcularIVA(double valor, double porcentajeIVA) {
        // Calculamos el valor del IVA y lo sumamos al valor original
        return valor + (valor * porcentajeIVA / 100.0);
    }
}

