package org.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    // Instanciamos la clase Calculadora que contiene toda la lógica matemática
    Calculadora calc = new Calculadora();

    @Override
    public void start(Stage primaryStage) {
        // Campo de texto para entrada y salida
        TextField display = new TextField();
        display.setPromptText("Ingresa número");
        display.setEditable(true);

        // Botones básicos
        Button btnSuma = new Button("+");
        Button btnResta = new Button("-");
        Button btnMultiplicacion = new Button("*");
        Button btnDivision = new Button("/");

        // Botones trigonométricos
        Button btnSeno = new Button("Sen");
        Button btnCoseno = new Button("Cos");
        Button btnTangente = new Button("Tan");

        // Botones de potencia y raíz
        Button btnPotencia = new Button("x^n");
        Button btnRaiz = new Button("Raíz n");

        // Botones especiales
        Button btnFactorial = new Button("n!");
        Button btnFibonacci = new Button("Fib(n)");
        Button btnMCM = new Button("MCM");
        Button btnMCD = new Button("MCD");
        Button btnIVA = new Button("IVA");

        // Acciones de botones
        btnSuma.setOnAction(e -> display.setText(String.valueOf(calc.sumar(obtenerValor(display), 1))));
        btnResta.setOnAction(e -> display.setText(String.valueOf(calc.restar(obtenerValor(display), 1))));
        btnMultiplicacion.setOnAction(e -> display.setText(String.valueOf(calc.multiplicar(obtenerValor(display), 2))));
        btnDivision.setOnAction(e -> {
            try {
                display.setText(String.valueOf(calc.dividir(obtenerValor(display), 2)));
            } catch (Exception ex) {
                display.setText("Error: " + ex.getMessage());
            }
        });

        btnSeno.setOnAction(e -> display.setText(String.valueOf(calc.seno(obtenerValor(display)))));
        btnCoseno.setOnAction(e -> display.setText(String.valueOf(calc.coseno(obtenerValor(display)))));
        btnTangente.setOnAction(e -> display.setText(String.valueOf(calc.tangente(obtenerValor(display)))));
        btnPotencia.setOnAction(e -> display.setText(String.valueOf(calc.potencia(obtenerValor(display), 2))));
        btnRaiz.setOnAction(e -> {
            try {
                display.setText(String.valueOf(calc.raizEnesima(obtenerValor(display), 2)));
            } catch (Exception ex) {
                display.setText("Error: " + ex.getMessage());
            }
        });

        btnFactorial.setOnAction(e -> display.setText(String.valueOf(calc.factorial((int) obtenerValor(display)))));
        btnFibonacci.setOnAction(e -> display.setText(String.valueOf(calc.fibonacci((int) obtenerValor(display)))));
        btnMCM.setOnAction(e -> display.setText(String.valueOf(calc.mcm((int) obtenerValor(display), 6))));
        btnMCD.setOnAction(e -> display.setText(String.valueOf(calc.mcd((int) obtenerValor(display), 6))));
        btnIVA.setOnAction(e -> display.setText(String.valueOf(calc.calcularIVA(obtenerValor(display), 19))));

        // Layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setVgap(10);
        grid.setHgap(10);

        // Agregar elementos
        grid.add(display, 0, 0, 4, 1);
        grid.add(btnSuma, 0, 1);
        grid.add(btnResta, 1, 1);
        grid.add(btnMultiplicacion, 2, 1);
        grid.add(btnDivision, 3, 1);

        grid.add(btnSeno, 0, 2);
        grid.add(btnCoseno, 1, 2);
        grid.add(btnTangente, 2, 2);
        grid.add(btnPotencia, 3, 2);

        grid.add(btnRaiz, 0, 3);
        grid.add(btnFactorial, 1, 3);
        grid.add(btnFibonacci, 2, 3);
        grid.add(btnIVA, 3, 3);

        grid.add(btnMCM, 0, 4);
        grid.add(btnMCD, 1, 4);

        // Mostrar escena
        Scene scene = new Scene(grid, 500, 300);
        primaryStage.setTitle("Calculadora Extendida - CalcLux");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Método auxiliar para obtener el valor del campo de texto
    private double obtenerValor(TextField campo) {
        String texto = campo.getText();
        if (texto.isEmpty()) {
            return 0;
        }
        return Double.parseDouble(texto);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
