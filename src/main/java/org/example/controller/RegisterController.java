package org.example.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.service.RegisterService;
import org.example.exceptions.UsuarioException;

public class RegisterController {

    @FXML private TextField txtNombre;
    @FXML private TextField txtApellidos;
    @FXML private TextField txtCorreo;
    @FXML private PasswordField txtContrasenia;
    @FXML private Label lblMensaje;

    private RegisterService registerService = new RegisterService();

    @FXML
    private void handleRegister(javafx.event.ActionEvent event) { // Añadimos el event aquí
        try {
            // 1. Intentamos registrar al usuario en la BD
            registerService.registrarNuevoUsuario(
                    txtNombre.getText().trim(),
                    txtApellidos.getText().trim(),
                    txtCorreo.getText().trim(),
                    txtContrasenia.getText()
            );

            // 2. Si no salta catch, el registro fue un éxito.
            // Cerramos la ventana actual de Registro
            Node source = (Node) event.getSource();
            Stage stageRegister = (Stage) source.getScene().getWindow();
            stageRegister.close();

            // 3. Abrimos de nuevo la ventana de Login de forma automática
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
                Parent root = loader.load();

                Stage stageLogin = new Stage();
                stageLogin.setTitle("Inicio de Sesión");
                stageLogin.setScene(new Scene(root));
                stageLogin.show();

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error al regresar al Login tras el registro.");
            }

        } catch (UsuarioException e) {
            // Si el correo ya existía o hay campos vacíos, mostramos el error y no se mueve de pantalla
            lblMensaje.setStyle("-fx-text-fill: red;");
            lblMensaje.setText(e.getMessage());
        }
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtApellidos.clear();
        txtCorreo.clear();
        txtContrasenia.clear();
    }
}


