package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // 1. Cargamos el archivo FXML principal (el catálogo/home)
            // Asegúrate de escribir la ruta exacta donde guardaste tu home.fxml o main.fxml
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/view/main.fxml"));            Parent root = loader.load();

            // 2. Creamos la escena con el diseño cargado
            Scene scene = new Scene(root, 1024, 768); // Puedes ajustar el ancho y alto aquí

            // 3. Configuramos la ventana principal
            primaryStage.setTitle("Catálogo de Compra-Venta de Coches");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error crítico al arrancar la aplicación principal.");
        }
    }

    public static void main(String[] args) {
        // Lanzamos la aplicación JavaFX
        launch(args);
    }
}