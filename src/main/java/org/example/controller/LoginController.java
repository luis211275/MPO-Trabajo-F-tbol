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

    @FXML private TextField txtCorreo;
    @FXML private PasswordField txtContrasenia;
    @FXML private Label lblMensaje;

    private LoginService loginService = new LoginService();

    @FXML
    private void handleLogin(javafx.event.ActionEvent event) {
        try {
            // 1. Validamos las credenciales con el servicio y la base de datos
            Usuario user = loginService.autenticarUsuario(txtCorreo.getText().trim(), txtContrasenia.getText());

            // 2. Si es correcto, procedemos a CERRAR la ventana actual de Login directamente
            Node source = (Node) event.getSource();
            Stage stageLogin = (Stage) source.getScene().getWindow();
            stageLogin.close();

            // 3. ABRIR la ventana principal (main.fxml)
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

    @FXML
    private void irARegistroDesdeLogin(javafx.event.ActionEvent event) {
        // Cerrar Login actual
        Node source = (Node) event.getSource();
        Stage stageActual = (Stage) source.getScene().getWindow();
        stageActual.close();

        // Abrir ventana de Registro
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