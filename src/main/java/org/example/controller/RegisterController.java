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

    //Datos para poner en el register
    @FXML private TextField txtNombre;
    @FXML private TextField txtApellidos;
    @FXML private TextField txtCorreo;
    @FXML private PasswordField txtContrasenia;
    @FXML private Label lblMensaje;


    //metodo que llama a la logica de negocio del service
    private RegisterService registerService = new RegisterService();


    /**
     * metodo se inicia cuando le das al boton de registrarse
     *
     * @param event boton de evento
     * @exception Exception salta un error si no me manda bien al login
     * @exception UsuarioException error si no se contestan todos los datos o el correo ya existe
     */
    @FXML
    private void handleRegister(javafx.event.ActionEvent event) { // Añadimos el event aquí
        try {
            // 1.Cogemos lo que hay en las cajas y se lo mandamos a la base de datos
            registerService.registrarNuevoUsuario(
                    txtNombre.getText().trim(),
                    txtApellidos.getText().trim(),
                    txtCorreo.getText().trim(),
                    txtContrasenia.getText()
            );

            // 2. No salta el error, se ha registrado, entonces cerramos la ventana de registro
            Node source = (Node) event.getSource();
            Stage stageRegister = (Stage) source.getScene().getWindow();
            stageRegister.close();

            // 3. Automaticamente, cerramos abrimos la ventada de login para logearse y que tambien pueda comprobar los datos del login
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
            lblMensaje.setStyle("-fx-text-fill: red;");
            lblMensaje.setText(e.getMessage());
        }
    }


    /**
     * Metodo que al darle al boton va a limpiar los campos del registro, ya no es necesario
     */
    private void limpiarCampos() {
        txtNombre.clear();
        txtApellidos.clear();
        txtCorreo.clear();
        txtContrasenia.clear();
    }
}

