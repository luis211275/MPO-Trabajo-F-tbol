package org.example.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.model.Usuario;
import org.example.service.LoginService;
import org.example.exceptions.UsuarioException;

public class LoginController {


    //Datos para poner en el login
    @FXML private TextField txtCorreo;
    @FXML private PasswordField txtContrasenia;
    @FXML private Label lblMensaje;


    //llama a la logica de negocio
    private LoginService loginService = new LoginService();


    /**
     * metodo se inicia cuando le das al boton de iniciar sesion
     *
     * @param event evento del boton
     * @exception Exception Error si no puede abrir el main.fxml
     * @exception UsuarioException Error en la validacion del usuario, ponemos que el mensaje me lo ponga en rojo, cambiando el estilo
     */
    @FXML
    private void handleLogin(javafx.event.ActionEvent event) {
        try {
            // Llamamos al service para el metodo de autentificar usuario
            Usuario user = loginService.autenticarUsuario(txtCorreo.getText().trim(), txtContrasenia.getText());

            // 2. Si la autentificacion esta bien, se cierra la escena
            Node source = (Node) event.getSource();
            Stage stageLogin = (Stage) source.getScene().getWindow();
            stageLogin.close();

            // 3. Abrimos la ventana de main.fxml
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main.fxml"));
                Parent root = loader.load();

                Stage stageMain = new Stage();
                stageMain.setTitle("Panel Principal - Gestión de Jugadores");
                stageMain.setScene(new Scene(root));
                stageMain.show();

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error al abrir la pantalla principal tras el Login.");
            }

        } catch (UsuarioException e) {
            // Si las credenciales fallan, mostramos el mensaje de error en la interfaz
            lblMensaje.setStyle("-fx-text-fill: red;");
            lblMensaje.setText(e.getMessage());
        }
    }


    /**
     * Metodo que me envia al metodo de registro desde el login, haciendo que me cierre el login y me cargue y me entre al regster
     *
     * @param event evento del boton
     * @exception Exception error a la hora de abrir la ventana del registro
     */
    @FXML
    private void irARegistroDesdeLogin(javafx.event.ActionEvent event) {
        // Me va a cerrar el login actual, que es desde donde estoy
        Node source = (Node) event.getSource();
        Stage stageActual = (Stage) source.getScene().getWindow();
        stageActual.close();

        // Me va a abrir la ventana del registro, repetitivo a la anterior
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/view/register.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Registro de Usuario");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}