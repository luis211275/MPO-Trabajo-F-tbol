package org.example.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.model.Usuario;
import org.example.service.LoginService;
import org.example.exceptions.UsuarioException;

public class LoginController {

    @FXML private TextField txtCorreo;
    @FXML private PasswordField txtContrasenia;
    @FXML private Label lblMensaje;

    private LoginService loginService = new LoginService();

    @FXML
    private void handleLogin() {
        String correo = txtCorreo.getText().trim();
        String contrasenia = txtContrasenia.getText();

        try {
            // Intentamos loguear al usuario
            Usuario usuarioLogueado = loginService.autenticarUsuario(correo, contrasenia);

            // Si llega aquí, las credenciales son correctas
            lblMensaje.setStyle("-fx-text-fill: green;");
            lblMensaje.setText("¡Bienvenido/a, " + usuarioLogueado.getNombre() + "!");

            // TODO: Aquí podrías redirigir al usuario a la pantalla principal de tu app (ej: home.html o catálogo)
            // abrirPantallaPrincipal();

        } catch (UsuarioException e) {
            // Capturamos los errores controlados (Campos vacíos, usuario no encontrado, etc.)
            lblMensaje.setStyle("-fx-text-fill: red;");
            lblMensaje.setText(e.getMessage());
        }
    }


    @FXML
    private void irARegistroDesdeLogin(javafx.event.ActionEvent event) {
        // Cerrar Login actual
        javafx.scene.Node source = (javafx.scene.Node) event.getSource();
        javafx.stage.Stage stageActual = (javafx.stage.Stage) source.getScene().getWindow();
        stageActual.close();

        // Abrir ventana de Registro
        try {
            javafx.scene.Parent root = FXMLLoader.load(getClass().getResource("/view/register.fxml"));
            javafx.stage.Stage stage = new javafx.stage.Stage();
            stage.setTitle("Registro de Usuario");
            stage.setScene(new javafx.scene.Scene(root));
            stage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
