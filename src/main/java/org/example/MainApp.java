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
            // CAMBIO: Ahora arrancamos directamente con la pantalla de LOGIN
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/view/login.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            primaryStage.setTitle("Inicio de Sesión");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al arrancar la pantalla de Login.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}