package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Calculadora de consola acumulativa.
 * Permite realizar operaciones matemáticas usando la clase Calculadora.
 * El resultado de cada operación se usa como acumulador para la siguiente.
 * Incluye historial de operaciones y validaciones para evitar desbordamientos.
 * El historial se guarda en un archivo de texto.
 */
public class CalculadoraConsola {

    private final Calculadora calculadora = new Calculadora();
    private final Scanner scanner = new Scanner(System.in);

    // Acumulador para operaciones sucesivas
    private double acumulador = 0.0;
    // Indica si es la primera operación (para pedir ambos operandos)
    private boolean primeraOperacion = true;
    // Historial de operaciones realizadas
    private final List<String> historial = new ArrayList<>();
    // Archivo para historial persistente
    private static final String ARCHIVO_HISTORIAL = "historial.txt";

    /**
     * Constructor. Carga el historial desde archivo si existe.
     */
    public CalculadoraConsola() {
        cargarHistorial();
    }

    /**
     * Inicia el ciclo principal de la calculadora de consola.
     * Muestra el menú, procesa la opción y ejecuta la operación correspondiente.
     * Guarda el historial al salir.
     */
    public void iniciar() {
        int opcion;
        do {
            mostrarEstado();
            mostrarMenu();
            opcion = leerEntero();
            try {
                switch (opcion) {
                    case 1 -> operarBinaria("Sumar", "+");
                    case 2 -> operarBinaria("Restar", "-");
                    case 3 -> operarBinaria("Multiplicar", "*");
                    case 4 -> operarBinaria("Dividir", "/");
                    case 5 -> operarUnaria("Seno", "sen");
                    case 6 -> operarUnaria("Coseno", "cos");
                    case 7 -> operarUnaria("Tangente", "tan");
                    case 8 -> operarBinaria("Potencia", "^");
                    case 9 -> operarBinaria("Raíz enésima", "raíz");
                    case 10 -> operarUnaria("Factorial", "!");
                    case 11 -> operarUnaria("Fibonacci", "fib");
                    case 12 -> operarBinaria("MCM", "mcm");
                    case 13 -> operarBinaria("MCD", "mcd");
                    case 14 -> operarBinaria("IVA", "IVA");
                    case 15 -> mostrarHistorial();
                    case 20 -> reiniciarAcumulador();
                    case 0 -> {
                        System.out.println("¡Hasta luego!");
                        guardarHistorial();
                    }
                    default -> System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                System.out.print("Presione ENTER para continuar...");
                scanner.nextLine(); // Espera a que el usuario presione una tecla
                reiniciarAcumulador(); // Reinicia el acumulador y el estado
            }
            System.out.println();
        } while (opcion != 0);
    }
    /**
     * Muestra el valor actual del acumulador.
     */
    private void mostrarEstado() {
        System.out.println("\n[Acumulador: " + acumulador + "]");
    }

    /**
     * Muestra el menú de operaciones disponibles.
     */
    private void mostrarMenu() {
        System.out.println("=== Calculadora Consola Acumulativa ===");
        System.out.println("1. Sumar");
        System.out.println("2. Restar");
        System.out.println("3. Multiplicar");
        System.out.println("4. Dividir");
        System.out.println("5. Seno");
        System.out.println("6. Coseno");
        System.out.println("7. Tangente");
        System.out.println("8. Potencia");
        System.out.println("9. Raíz enésima");
        System.out.println("10. Factorial");
        System.out.println("11. Fibonacci");
        System.out.println("12. MCM");
        System.out.println("13. MCD");
        System.out.println("14. Calcular IVA");
        System.out.println("15. Mostrar historial");
        System.out.println("20. Reiniciar acumulador");
        System.out.println("0. Salir");
    }

    /**
     * Realiza una operación binaria usando el acumulador y un nuevo valor.
     * Si es la primera operación, pide ambos operandos.
     * Para MCM/MCD, trunca decimales y advierte al usuario.
     * @param operacion Nombre de la operación a realizar.
     * @param simbolo Símbolo para mostrar la operación.
     */
    private void operarBinaria(String operacion, String simbolo) {
        double a, b;
        if (primeraOperacion) {
            a = leerDouble("Ingrese el primer número: ");
            b = leerDouble("Ingrese el segundo número: ");
            primeraOperacion = false;
        } else {
            a = acumulador;
            b = leerDouble("Ingrese el siguiente número: ");
        }
        double resultado = 0;
        String operacionTexto = "";
        switch (operacion) {
            case "Sumar" -> {
                resultado = calculadora.sumar(a, b);
                operacionTexto = a + " + " + b + " = " + resultado;
            }
            case "Restar" -> {
                resultado = calculadora.restar(a, b);
                operacionTexto = a + " - " + b + " = " + resultado;
            }
            case "Multiplicar" -> {
                resultado = calculadora.multiplicar(a, b);
                operacionTexto = a + " * " + b + " = " + resultado;
            }
            case "Dividir" -> {
                resultado = calculadora.dividir(a, b);
                operacionTexto = a + " / " + b + " = " + resultado;
            }
            case "Potencia" -> {
                resultado = calculadora.potencia(a, b);
                operacionTexto = a + " ^ " + b + " = " + resultado;
            }
            case "Raíz enésima" -> {
                resultado = calculadora.raizEnesima(a, b);
                operacionTexto = b + "√" + a + " = " + resultado;
            }
            case "MCM" -> {
                // Truncar decimales y advertir si corresponde
                int ia = (int) a;
                int ib = (int) b;
                if (a != ia || b != ib) {
                    System.out.println("Advertencia: Se truncaron los valores decimales para MCM: " + a + "→" + ia + ", " + b + "→" + ib);
                }
                if (ia == 0 || ib == 0)
                    throw new IllegalArgumentException("MCM con cero no definido");
                resultado = calculadora.mcm(ia, ib);
                operacionTexto = "mcm(" + ia + ", " + ib + ") = " + (int) resultado;
            }
            case "MCD" -> {
                int ia = (int) a;
                int ib = (int) b;
                if (a != ia || b != ib) {
                    System.out.println("Advertencia: Se truncaron los valores decimales para MCD: " + a + "→" + ia + ", " + b + "→" + ib);
                }
                resultado = calculadora.mcd(ia, ib);
                operacionTexto = "mcd(" + ia + ", " + ib + ") = " + (int) resultado;
            }
            case "IVA" -> {
                resultado = calculadora.calcularIVA(a, b);
                operacionTexto = a + " + " + b + "% IVA = " + resultado;
            }
            default -> throw new IllegalArgumentException("Operación no soportada");
        }
        acumulador = resultado;
        historial.add(operacionTexto);
        System.out.println("Resultado: " + operacionTexto);
    }

    /**
     * Realiza una operación unaria sobre el acumulador o un valor ingresado.
     * Si es la primera operación, pide el valor.
     * Valida límites para evitar desbordamientos en factorial y fibonacci.
     * @param operacion Nombre de la operación a realizar.
     * @param simbolo Símbolo para mostrar la operación.
     */
    private void operarUnaria(String operacion, String simbolo) {
        double valor;
        if (primeraOperacion) {
            valor = leerDouble("Ingrese el número: ");
            primeraOperacion = false;
        } else {
            valor = acumulador;
        }
        double resultado = 0;
        String operacionTexto = "";
        switch (operacion) {
            case "Seno" -> {
                resultado = calculadora.seno(valor);
                operacionTexto = "sen(" + valor + ") = " + resultado;
            }
            case "Coseno" -> {
                resultado = calculadora.coseno(valor);
                operacionTexto = "cos(" + valor + ") = " + resultado;
            }
            case "Tangente" -> {
                resultado = calculadora.tangente(valor);
                operacionTexto = "tan(" + valor + ") = " + resultado;
            }
            case "Factorial" -> {
                // Validación de rango para evitar desbordamiento
                if (valor < 0 || valor > 20 || valor != (int) valor)
                    throw new IllegalArgumentException("El factorial solo está definido para enteros entre 0 y 20.");
                resultado = calculadora.factorial((int) valor);
                operacionTexto = (int) valor + "! = " + (long) resultado;
            }
            case "Fibonacci" -> {
                // Validación de rango para evitar desbordamiento
                if (valor < 0 || valor > 92 || valor != (int) valor)
                    throw new IllegalArgumentException("Fibonacci solo está definido para enteros entre 0 y 92.");
                resultado = calculadora.fibonacci((int) valor);
                operacionTexto = "fib(" + (int) valor + ") = " + (long) resultado;
            }
            default -> throw new IllegalArgumentException("Operación no soportada");
        }
        acumulador = resultado;
        historial.add(operacionTexto);
        System.out.println("Resultado: " + operacionTexto);
    }

    /**
     * Reinicia el acumulador y permite comenzar una nueva secuencia de operaciones.
     * También reinicia el estado para pedir ambos operandos en la siguiente operación.
     */
    private void reiniciarAcumulador() {
        acumulador = 0.0;
        primeraOperacion = true;
        System.out.println("Acumulador reiniciado.");
    }

    /**
     * Muestra el historial de operaciones realizadas en consola.
     * Si el historial está vacío, lo indica al usuario.
     */
    private void mostrarHistorial() {
        System.out.println("=== Historial de operaciones ===");
        if (historial.isEmpty()) {
            System.out.println("No hay operaciones registradas.");
        } else {
            for (String op : historial) {
                System.out.println(op);
            }
        }
    }

    /**
     * Lee un número entero desde la consola, validando la entrada.
     * Repite hasta que el usuario ingrese un valor válido.
     * @return Número entero ingresado.
     */
    private int leerEntero() {
        while (true) {
            System.out.print("Elige una opción: ");
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número entero.");
            }
        }
    }

    /**
     * Lee un número decimal desde la consola, validando la entrada.
     * Repite hasta que el usuario ingrese un valor válido.
     * @param mensaje Mensaje a mostrar al usuario.
     * @return Número decimal ingresado.
     */
    private double leerDouble(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
            }
        }
    }

    /**
     * Guarda el historial de operaciones en un archivo de texto.
     * Se llama automáticamente al salir del programa.
     */
    private void guardarHistorial() {
        try (PrintWriter out = new PrintWriter(new FileWriter(ARCHIVO_HISTORIAL))) {
            for (String op : historial) {
                out.println(op);
            }
        } catch (IOException e) {
            System.out.println("No se pudo guardar el historial: " + e.getMessage());
        }
    }

    /**
     * Carga el historial de operaciones desde un archivo de texto si existe.
     * Se llama automáticamente al iniciar la calculadora.
     */
    private void cargarHistorial() {
        File archivo = new File(ARCHIVO_HISTORIAL);
        if (!archivo.exists()) return;
        try (BufferedReader in = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = in.readLine()) != null) {
                historial.add(linea);
            }
        } catch (IOException e) {
            System.out.println("No se pudo cargar el historial: " + e.getMessage());
        }
    }

    /**
     * Metodo principal para ejecutar la calculadora de consola.
     * @param args Argumentos de línea de comandos (no usados).
     */
    public static void main(String[] args) {
        new CalculadoraConsola().iniciar();
    }
}