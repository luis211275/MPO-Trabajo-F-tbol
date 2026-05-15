package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {
        try {
            // CAMBIA "vista_jugador.fxml" por el nombre real de tu archivo FXML
            // Recuerda que este archivo debe estar en la carpeta: src/main/resources/org/example/
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main.fxml"));            Parent root = loader.load();

            // Creamos la escena y le asignamos un tamaño a la ventana (Ancho x Alto)
            Scene scene = new Scene(root, 700, 550);

            stage.setTitle("Gestión de Jugadores - Trabajo Fútbol");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            System.out.println("Error al cargar el archivo FXML. Revisa la ruta y los nombres.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}