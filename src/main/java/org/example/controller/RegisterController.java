package org.example.controller;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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
    private void handleRegister() {
        String nombre = txtNombre.getText().trim();
        String apellidos = txtApellidos.getText().trim();
        String correo = txtCorreo.getText().trim();
        String contrasenia = txtContrasenia.getText();

        try {
            registerService.registrarNuevoUsuario(nombre, apellidos, correo, contrasenia);

            // Si no salta excepción, el registro fue un éxito
            lblMensaje.setStyle("-fx-text-fill: green;");
            lblMensaje.setText("¡Usuario registrado con éxito!");
            limpiarCampos();

        } catch (UsuarioException e) {
            // Mostramos el mensaje de error controlado
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