package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Scanner;

/**
 * Clase principal del proyecto CalcLux.
 * Permite al usuario elegir entre la calculadora de consola o la calculadora gráfica (GUI).
 * Creado por:
 * - Niko Andes (202012345)
 * - Camila Torres (202056789)
 * - Juan Pérez (202034567)
 */
public class Main extends Application {

    // Indica si se debe lanzar la GUI
    private static boolean lanzarGUI = false;

    /**
     * Metodo principal. Muestra el menú de selección, da la bienvenida y lanza la versión elegida.
     * Al salir, muestra un mensaje de despedida y los créditos.
     *
     * @param args Argumentos de línea de comandos (no usados).
     */
    public static void main(String[] args) {
        mostrarBienvenida();
        int opcion = leerOpcionMenu();
        if (opcion == 2) {
            lanzarGUI = true;
            launch(args); // Lanza la aplicación JavaFX
        } else {
            new CalculadoraConsola().iniciar(); // Lanza la calculadora de consola
        }
        mostrarDespedida();
    }

    /**
     * Muestra un mensaje de bienvenida personalizado y los créditos del proyecto.
     */
    private static void mostrarBienvenida() {
        System.out.println("==============================================");
        System.out.println("  ¡Bienvenido a CalcLux! ");
        System.out.println("  Tu calculadora avanzada de consola y GUI");
        System.out.println("  Laboratorio de Programación 3 - 2025");
        System.out.println("  Creado por:");
        System.out.println("    Nicolas Isaza (7004625)");
        System.out.println("    Camila Torres (202056789)");
        System.out.println("    Juan Pérez (202034567)");
        System.out.println("==============================================\n");
    }

    /**
     * Muestra un mensaje de despedida.
     */
    private static void mostrarDespedida() {
        System.out.println("\n==============================================");
        System.out.println("  ¡Gracias por usar CalcLux!");
        System.out.println("  ¡Hasta pronto!");
        System.out.println("==============================================");
    }

    /**
     * Muestra el menú de selección y valida la opción ingresada.
     * Solo acepta 1 (consola) o 2 (GUI).
     *
     * @return Opción elegida por el usuario.
     */
    private static int leerOpcionMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion = -1;
        do {
            System.out.println("=== Seleccione la versión de la calculadora ===");
            System.out.println("1. Calculadora de consola");
            System.out.println("2. Calculadora gráfica (GUI)");
            System.out.print("Opción: ");
            String entrada = scanner.nextLine();
            try {
                opcion = Integer.parseInt(entrada);
                if (opcion != 1 && opcion != 2) {
                    System.out.println("Opción no válida. Por favor, ingrese 1 o 2.\n");
                    opcion = -1;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, ingrese 1 o 2.\n");
            }
        } while (opcion == -1);
        return opcion;
    }

    /**
     * Metodo de inicio de JavaFX. Solo se ejecuta si se selecciona la GUI.
     *
     * @param primaryStage Ventana principal de la aplicación.
     */
    @Override
    public void start(Stage primaryStage) {
        if (!lanzarGUI) return;
        // Crea el controlador de la calculadora gráfica y muestra la ventana principal
        CalculadoraController controller = new CalculadoraController();
        Scene scene = controller.crearEscenaCalculadora();
        primaryStage.setTitle("CalcLux - Laboratorio de Programación 3");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.setResizable(true);
        primaryStage.setFullScreen(true); // <-- Pantalla completa al abrir
        primaryStage.show();
    }
}