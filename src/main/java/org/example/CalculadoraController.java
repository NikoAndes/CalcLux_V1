package org.example;

import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;

/**
 * Controlador de la calculadora gráfica (GUI) para CalcLux.
 * Gestiona la interfaz, eventos de botones y la lógica de operaciones.
 * Permite realizar operaciones básicas y avanzadas usando la clase Calculadora.
 */

public class CalculadoraController {

    // Instancia de la clase lógica de operaciones
    private final Calculadora calculadora = new Calculadora();

    // Estado de la operación actual
    private String operacionActual = "";
    private String operacionPendiente = "";
    private double acumulador = 0;
    private boolean esperandoNuevoNumero = true;

    // Elementos de la interfaz
    private TextField barraOperacion;
    private TextField barraResultado;

    /**
     * Crea y retorna la escena principal de la calculadora gráfica.
     * @return Scene con la interfaz de la calculadora.
     */
    public Scene crearEscenaCalculadora() {
        inicializarCampos();
        HBox filaFunciones = crearFilaFunciones();
        HBox filaOperaciones = crearFilaOperaciones();
        GridPane tecladoNumerico = crearTecladoNumerico();
        VBox root = new VBox(10, barraOperacion, barraResultado, filaFunciones, filaOperaciones, tecladoNumerico);
        root.setPadding(new Insets(15));
        root.setStyle("-fx-background-color: #181a20;");
        VBox.setVgrow(tecladoNumerico, Priority.ALWAYS);
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(this::manejarTecla);
        return scene;
    }

    /**
     * Inicializa los campos de texto de la interfaz.
     */
    private void inicializarCampos() {
        barraOperacion = new TextField();
        barraOperacion.setEditable(false);
        barraOperacion.setAlignment(Pos.CENTER_RIGHT);
        barraOperacion.setStyle("-fx-background-color: #23272e; -fx-text-fill: #bfc7d5; -fx-background-radius: 12px; -fx-border-width: 0;");
        barraOperacion.setMaxWidth(Double.MAX_VALUE);
        barraOperacion.setMinHeight(32);
        barraOperacion.setPrefHeight(36);
        // Ajuste dinámico de tamaño de fuente
        barraOperacion.styleProperty().bind(
                Bindings.concat(
                        "-fx-font-size: ",
                        Bindings.when(barraOperacion.heightProperty().divide(2.5).greaterThan(24))
                                .then(24)
                                .otherwise(barraOperacion.heightProperty().divide(2.5)),
                        "px; -fx-background-color: #23272e; -fx-text-fill: #bfc7d5; -fx-background-radius: 12px; -fx-border-width: 0;"
                )
        );
        barraResultado = new TextField("0");
        barraResultado.setEditable(false);
        barraResultado.setAlignment(Pos.CENTER_RIGHT);
        barraResultado.setStyle("-fx-background-color: #181a20; -fx-text-fill: #f8f8f2; -fx-background-radius: 12px; -fx-border-width: 0;");
        barraResultado.setMaxWidth(Double.MAX_VALUE);
        barraResultado.setMinHeight(48);
        barraResultado.setPrefHeight(56);
        // Ajuste dinámico de tamaño de fuente
        barraResultado.styleProperty().bind(
                Bindings.concat(
                        "-fx-font-size: ",
                        Bindings.when(barraResultado.heightProperty().divide(1.5).greaterThan(40))
                                .then(40)
                                .otherwise(barraResultado.heightProperty().divide(1.5)),
                        "px; -fx-background-color: #181a20; -fx-text-fill: #f8f8f2; -fx-background-radius: 12px; -fx-border-width: 0;"
                )
        );
    }

    /**
     * Crea la fila de botones de funciones avanzadas (trigonométricas, potencia, raíz, etc.).
     * @return HBox con los botones de funciones.
     */
    private HBox crearFilaFunciones() {
        String btnBase = estiloBotonBase();
        String funcGrad1 = "linear-gradient(to bottom right, #4e54c8, #23272e);";
        String funcGrad2 = "linear-gradient(to bottom right, #fc466b, #3f5efb);";
        String funcGrad3 = "linear-gradient(to bottom right, #f7971e, #ffd200);";
        Button btnSeno = crearBotonFuncion("Sen", btnBase + " -fx-background-color: " + funcGrad1 + " -fx-text-fill: #fff;", () -> operarUnaria("seno", "Sen"));
        Button btnCoseno = crearBotonFuncion("Cos", btnBase + " -fx-background-color: " + funcGrad1 + " -fx-text-fill: #fff;", () -> operarUnaria("coseno", "Cos"));
        Button btnTangente = crearBotonFuncion("Tan", btnBase + " -fx-background-color: " + funcGrad1 + " -fx-text-fill: #fff;", () -> operarUnaria("tangente", "Tan"));
        Button btnPotencia = crearBotonFuncion("x^n", btnBase + " -fx-background-color: " + funcGrad2 + " -fx-text-fill: #fff;", () -> prepararOperacion("potencia", "^"));
        Button btnRaiz = crearBotonFuncion("Raíz n", btnBase + " -fx-background-color: " + funcGrad2 + " -fx-text-fill: #fff;", () -> prepararOperacion("raiz", "√"));
        Button btnFactorial = crearBotonFuncion("n!", btnBase + " -fx-background-color: " + funcGrad2 + " -fx-text-fill: #fff;", () -> operarUnaria("factorial", "n!"));
        Button btnFibonacci = crearBotonFuncion("Fib(n)", btnBase + " -fx-background-color: " + funcGrad2 + " -fx-text-fill: #fff;", () -> operarUnaria("fibonacci", "Fib"));
        Button btnMCM = crearBotonFuncion("MCM", btnBase + " -fx-background-color: " + funcGrad3 + " -fx-text-fill: #23272e;", () -> prepararOperacion("mcm", "MCM"));
        Button btnMCD = crearBotonFuncion("MCD", btnBase + " -fx-background-color: " + funcGrad3 + " -fx-text-fill: #23272e;", () -> prepararOperacion("mcd", "MCD"));
        Button btnIVA = crearBotonFuncion("IVA", btnBase + " -fx-background-color: " + funcGrad3 + " -fx-text-fill: #23272e;", () -> prepararOperacion("iva", "IVA"));
        HBox funciones = new HBox(7, btnSeno, btnCoseno, btnTangente, btnPotencia, btnRaiz, btnFactorial, btnFibonacci, btnMCM, btnMCD, btnIVA);
        funciones.setAlignment(Pos.CENTER);
        funciones.setPadding(new Insets(5, 0, 5, 0));
        funciones.setMaxWidth(Double.MAX_VALUE);
        for (Button b : new Button[]{btnSeno, btnCoseno, btnTangente, btnPotencia, btnRaiz, btnFactorial, btnFibonacci, btnMCM, btnMCD, btnIVA}) {
            HBox.setHgrow(b, Priority.ALWAYS);
            b.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        }
        return funciones;
    }

    /**
     * Crea la fila de botones de operaciones básicas y control (CE, C, =).
     * @return HBox con los botones de operaciones.
     */
    private HBox crearFilaOperaciones() {
        String btnBase = estiloBotonBase();
        String opGrad = "linear-gradient(to bottom right, #ff9800, #ffb347);";
        String eqGrad = "linear-gradient(to bottom right, #43cea2, #185a9d);";
        String ceGrad = "linear-gradient(to bottom right, #ff5858, #f09819);";
        Button btnSuma = crearBotonFuncion("+", btnBase + " -fx-background-color: " + opGrad + " -fx-text-fill: #23272e;", () -> prepararOperacion("sumar", "+"));
        Button btnResta = crearBotonFuncion("-", btnBase + " -fx-background-color: " + opGrad + " -fx-text-fill: #23272e;", () -> prepararOperacion("restar", "-"));
        Button btnMultiplicacion = crearBotonFuncion("*", btnBase + " -fx-background-color: " + opGrad + " -fx-text-fill: #23272e;", () -> prepararOperacion("multiplicar", "*"));
        Button btnDivision = crearBotonFuncion("/", btnBase + " -fx-background-color: " + opGrad + " -fx-text-fill: #23272e;", () -> prepararOperacion("dividir", "/"));
        Button btnCE = crearBotonFuncion("CE", btnBase + " -fx-background-color: " + ceGrad + " -fx-text-fill: #fff;", this::borrarEntradaActual);
        Button btnC = crearBotonFuncion("C", btnBase + " -fx-background-color: " + ceGrad + " -fx-text-fill: #fff;", this::borrarTodo);
        Button btnIgual = crearBotonFuncion("=", btnBase + " -fx-background-color: " + eqGrad + " -fx-text-fill: #fff;", this::calcularOperacion);
        HBox operaciones = new HBox(7, btnSuma, btnResta, btnMultiplicacion, btnDivision, btnCE, btnC, btnIgual);
        operaciones.setAlignment(Pos.CENTER);
        operaciones.setPadding(new Insets(5, 0, 5, 0));
        operaciones.setMaxWidth(Double.MAX_VALUE);
        for (Button b : new Button[]{btnSuma, btnResta, btnMultiplicacion, btnDivision, btnCE, btnC, btnIgual}) {
            HBox.setHgrow(b, Priority.ALWAYS);
            b.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        }
        return operaciones;
    }

    /**
     * Crea el teclado numérico de la calculadora.
     * @return GridPane con los botones numéricos y el punto decimal.
     */
    private GridPane crearTecladoNumerico() {
        String btnBase = estiloBotonBase();
        Button[] numButtons = new Button[10];
        for (int i = 0; i <= 9; i++) {
            int num = i;
            numButtons[i] = new Button(String.valueOf(i));
            numButtons[i].setStyle(btnBase + " -fx-background-color: linear-gradient(to bottom right, #23272e, #393e46); -fx-text-fill: #f8f8f2;");
            numButtons[i].setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            numButtons[i].setOnAction(e -> agregarNumero(String.valueOf(num)));
        }
        Button btnPunto = new Button(".");
        btnPunto.setStyle(btnBase + " -fx-background-color: linear-gradient(to bottom right, #23272e, #393e46); -fx-text-fill: #f8f8f2;");
        btnPunto.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btnPunto.setOnAction(e -> agregarPuntoDecimal());
        GridPane teclado = new GridPane();
        teclado.setHgap(7);
        teclado.setVgap(7);
        teclado.setAlignment(Pos.CENTER);
        teclado.setMaxWidth(Double.MAX_VALUE);
        int idx = 1;
        // Distribuye los botones del 1 al 9 en la cuadrícula
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                teclado.add(numButtons[idx], col, row);
                GridPane.setHgrow(numButtons[idx], Priority.ALWAYS);
                GridPane.setVgrow(numButtons[idx], Priority.ALWAYS);
                numButtons[idx].setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                idx++;
            }
        }
        // Botón 0 y punto decimal
        teclado.add(numButtons[0], 1, 3);
        GridPane.setHgrow(numButtons[0], Priority.ALWAYS);
        GridPane.setVgrow(numButtons[0], Priority.ALWAYS);
        numButtons[0].setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        teclado.add(btnPunto, 2, 3);
        GridPane.setHgrow(btnPunto, Priority.ALWAYS);
        GridPane.setVgrow(btnPunto, Priority.ALWAYS);
        btnPunto.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        return teclado;
    }

    /**
     * Crea un botón de función con estilo y acción personalizada.
     * @param texto Texto del botón.
     * @param estilo Estilo CSS.
     * @param accion Acción a ejecutar al presionar el botón.
     * @return Botón configurado.
     */
    private Button crearBotonFuncion(String texto, String estilo, Runnable accion) {
        Button btn = new Button(texto);
        btn.setStyle(estilo);
        btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btn.setOnAction(e -> accion.run());
        return btn;
    }

    /**
     * Devuelve el estilo base para los botones.
     * @return String con el CSS base.
     */
    private String estiloBotonBase() {
        return "-fx-background-radius: 14px; -fx-font-weight: bold; -fx-font-size: 1.2em; -fx-border-width: 0; -fx-cursor: hand;";
    }

    /**
     * Verifica si la barra de resultado muestra un error.
     * @return true si hay error, false si no.
     */
    private boolean hayError() {
        return barraResultado.getText().startsWith("Error");
    }

    /**
     * Agrega un número a la barra de resultado.
     * Si hay error, reinicia la calculadora antes de continuar.
     * @param num Número a agregar.
     */
    private void agregarNumero(String num) {
        if (hayError()) borrarTodo();
        if (esperandoNuevoNumero || barraResultado.getText().equals("0")) {
            barraResultado.setText("");
            esperandoNuevoNumero = false;
        }
        barraResultado.setText(barraResultado.getText() + num);
    }

    /**
     * Agrega un punto decimal a la barra de resultado.
     * Si hay error, reinicia la calculadora antes de continuar.
     */
    private void agregarPuntoDecimal() {
        if (hayError()) borrarTodo();
        if (esperandoNuevoNumero) {
            barraResultado.setText("0");
            esperandoNuevoNumero = false;
        }
        if (!barraResultado.getText().contains(".")) {
            barraResultado.setText(barraResultado.getText() + ".");
        }
    }

    /**
     * Prepara una operación binaria (suma, resta, etc.).
     * Si hay error, reinicia la calculadora antes de continuar.
     * @param operacion Nombre de la operación.
     * @param simbolo Símbolo para mostrar en la barra de operación.
     */
    private void prepararOperacion(String operacion, String simbolo) {
        if (hayError()) borrarTodo();
        try {
            double valor = Double.parseDouble(barraResultado.getText());
            if (!operacionPendiente.isEmpty()) {
                calcularOperacion();
            }
            acumulador = valor;
            operacionPendiente = operacion;
            operacionActual = barraResultado.getText() + " " + simbolo;
            barraOperacion.setText(operacionActual);
            esperandoNuevoNumero = true;
        } catch (Exception ex) {
            mostrarError(ex.getMessage());
        }
    }

    /**
     * Realiza la operación pendiente y muestra el resultado.
     * Si hay error, reinicia la calculadora antes de continuar.
     */
    private void calcularOperacion() {
        if (hayError()) borrarTodo();
        try {
            double valor = Double.parseDouble(barraResultado.getText());
            double resultado;
            String operacionTexto = operacionActual + " " + barraResultado.getText();
            resultado = switch (operacionPendiente) {
                case "sumar" -> calculadora.sumar(acumulador, valor);
                case "restar" -> calculadora.restar(acumulador, valor);
                case "multiplicar" -> calculadora.multiplicar(acumulador, valor);
                case "dividir" -> calculadora.dividir(acumulador, valor);
                case "potencia" -> calculadora.potencia(acumulador, valor);
                case "raiz" -> calculadora.raizEnesima(acumulador, valor);
                case "mcm" -> calculadora.mcm((int) acumulador, (int) valor);
                case "mcd" -> calculadora.mcd((int) acumulador, (int) valor);
                case "iva" -> calculadora.calcularIVA(acumulador, valor);
                default -> valor;
            };
            barraResultado.setText(String.valueOf(resultado));
            barraOperacion.setText(operacionTexto + " =");
            acumulador = resultado;
            operacionPendiente = "";
            operacionActual = "";
            esperandoNuevoNumero = true;
        } catch (Exception ex) {
            mostrarError(ex.getMessage());
        }
    }

    /**
     * Realiza una operación unaria (seno, coseno, factorial, etc.).
     * Si hay error, reinicia la calculadora antes de continuar.
     * @param operacion Nombre de la operación.
     * @param simbolo Símbolo para mostrar en la barra de operación.
     */
    private void operarUnaria(String operacion, String simbolo) {
        if (hayError()) borrarTodo();
        try {
            double valor = Double.parseDouble(barraResultado.getText());
            double resultado = 0;
            String operacionTexto = simbolo + "(" + barraResultado.getText() + ")";
            resultado = switch (operacion) {
                case "seno" -> calculadora.seno(valor);
                case "coseno" -> calculadora.coseno(valor);
                case "tangente" -> calculadora.tangente(valor);
                case "factorial" -> calculadora.factorial((int) valor);
                case "fibonacci" -> calculadora.fibonacci((int) valor);
                default -> resultado;
            };
            barraResultado.setText(String.valueOf(resultado));
            barraOperacion.setText(operacionTexto + " =");
            acumulador = resultado;
            operacionPendiente = "";
            operacionActual = "";
            esperandoNuevoNumero = true;
        } catch (Exception ex) {
            mostrarError(ex.getMessage());
        }
    }

    /**
     * Borra solo la entrada actual (barra de resultado).
     */
    private void borrarEntradaActual() {
        barraResultado.setText("0");
        esperandoNuevoNumero = true;
    }

    /**
     * Borra_todo el estado de la calculadora y reinicia la interfaz.
     */
    private void borrarTodo() {
        barraResultado.setText("0");
        barraOperacion.clear();
        operacionActual = "";
        operacionPendiente = "";
        acumulador = 0;
        esperandoNuevoNumero = true;
    }

    /**
     * Muestra un mensaje de error en la barra de resultado y limpia la barra de operación.
     * @param mensaje Mensaje de error a mostrar.
     */
    private void mostrarError(String mensaje) {
        barraResultado.setText("Error: " + mensaje);
        barraOperacion.clear();
    }

    /**
     * Maneja eventos de teclado para permitir el uso con el teclado físico.
     * @param event Evento de teclado.
     */
    private void manejarTecla(KeyEvent event) {
        KeyCode code = event.getCode();
        String text = event.getText();
        if (code.isDigitKey()) {
            agregarNumero(text);
        } else if (code == KeyCode.PERIOD || code == KeyCode.DECIMAL) {
            agregarPuntoDecimal();
        } else if (code == KeyCode.ADD || text.equals("+")) {
            prepararOperacion("sumar", "+");
        } else if (code == KeyCode.SUBTRACT || text.equals("-")) {
            prepararOperacion("restar", "-");
        } else if (code == KeyCode.MULTIPLY || text.equals("*")) {
            prepararOperacion("multiplicar", "*");
        } else if (code == KeyCode.DIVIDE || text.equals("/")) {
            prepararOperacion("dividir", "/");
        } else if (code == KeyCode.ENTER || code == KeyCode.EQUALS) {
            calcularOperacion();
        } else if (code == KeyCode.BACK_SPACE) {
            if (!barraResultado.getText().isEmpty() && !esperandoNuevoNumero) {
                String t = barraResultado.getText();
                barraResultado.setText(t.length() > 1 ? t.substring(0, t.length() - 1) : "0");
            }
        } else if (code == KeyCode.ESCAPE) {
            borrarTodo();
        }
    }
}