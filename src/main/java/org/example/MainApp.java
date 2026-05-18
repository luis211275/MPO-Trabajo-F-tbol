/**
 * @author Luis López-Nuño Sánchez
 * @version 1.0
 * DAW 1
 */

package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//extends application hace que el programa detecte que esta hecho con JavaFX
public class MainApp extends Application {

    /**
     * Metodo donde va a inicar la aplicaicon, al darle a run, se ejecuta el login
     *
     * @param primaryStage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            // Hacemos que arranque desde el login.fxml, y asi si no inicias sesion no puede entrar al main
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/view/login.fxml"));
            Parent root = loader.load();

            //creacion de la escena
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