package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.exceptions.JugadorException;
import org.example.model.Jugador;
import org.example.service.JugadorService;

public class JugadorController {

    @FXML private TextField txtNombre;
    @FXML private TextField txtApellido;
    @FXML private TextField txtGoles;
    @FXML private TextField txtAsistencias;

    @FXML private TableView<Jugador> tablaJugador;
    @FXML private TableColumn<Jugador, String> colNombre;
    @FXML private TableColumn<Jugador, String> colApellido;
    @FXML private TableColumn<Jugador, Integer> colGoles;
    @FXML private TableColumn<Jugador, Integer> colAsistencias;

    private JugadorService jugadorService;
    private ObservableList<Jugador> listaJugadores;

    @FXML
    public void initialize() {
        jugadorService = new JugadorService();
        listaJugadores = FXCollections.observableArrayList();

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        colGoles.setCellValueFactory(new PropertyValueFactory<>("goles"));
        colAsistencias.setCellValueFactory(new PropertyValueFactory<>("asistencias"));

        cargarDatosTabla();


        prefWidthColumns();
    }


    private void prefWidthColumns() {
        tablaJugador.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        colNombre.prefWidthProperty().bind(tablaJugador.widthProperty().multiply(0.35));
        colApellido.prefWidthProperty().bind(tablaJugador.widthProperty().multiply(0.35));
        colGoles.prefWidthProperty().bind(tablaJugador.widthProperty().multiply(0.15));
        colAsistencias.prefWidthProperty().bind(tablaJugador.widthProperty().multiply(0.15));
    }

    // El método que se llama al pulsar tu botón "Enviar"
    @FXML
    public void sendData() {
        try {
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            int goles = Integer.parseInt(txtGoles.getText());
            int asistencias = Integer.parseInt(txtAsistencias.getText());

            Jugador nuevoJugador = new Jugador(nombre, apellido, goles, asistencias);

            jugadorService.agregarJugador(nuevoJugador);

            limpiarCampos();
            cargarDatosTabla();

            mostrarMensaje("Éxito", "Jugador guardado correctamente", Alert.AlertType.INFORMATION);

        } catch (NumberFormatException e) {
            mostrarMensaje("Error", "Goles y Asistencias deben ser números enteros.", Alert.AlertType.ERROR);
        } catch (JugadorException e) {
            mostrarMensaje("Error de Base de Datos", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void cargarDatosTabla() {
        try {
            listaJugadores.clear();
            listaJugadores.addAll(jugadorService.listarJugadores());
            tablaJugador.setItems(listaJugadores);
        } catch (JugadorException e) {
            mostrarMensaje("Error", "No se pudieron cargar los datos de la tabla.", Alert.AlertType.ERROR);
        }
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtApellido.clear();
        txtGoles.clear();
        txtAsistencias.clear();
    }

    private void mostrarMensaje(String titulo, String contenido, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }



    @FXML
    private void mostrarLogin() {
        cargarModal("/view/login.fxml", "Inicio de Sesión"); // Cambia /view/ por tu ruta si es distinta (ej: /html/)
    }

    @FXML
    private void mostrarRegister() {
        cargarModal("/view/register.fxml", "Registro de Usuario");
    }

    private void cargarModal(String ruta, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ruta));
            javafx.scene.Parent root = loader.load();
            javafx.stage.Stage stage = new javafx.stage.Stage();
            stage.setTitle(titulo);
            stage.setScene(new javafx.scene.Scene(root));
            stage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
            stage.show();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}