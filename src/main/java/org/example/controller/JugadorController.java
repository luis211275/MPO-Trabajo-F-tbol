package org.example.controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.example.model.Jugador;
import org.example.service.JugadorService;

public class JugadorController {

    private final JugadorService service = new JugadorService();


    @FXML private TextField txtNombre;
    @FXML private TextField txtApellido;
    @FXML private TextField txtGoles;
    @FXML private TextField txtAsistencias;


    @FXML private TableView<Jugador> tablaJugador;
    @FXML private TableColumn<Jugador,String> colNombre;
    @FXML private TableColumn<Jugador,String> colApellidos;
    @FXML private TableColumn<Jugador,Integer> colGoles;
    @FXML private TableColumn<Jugador,Integer> colAsistencias;


    @FXML
    private void initialize() {
        prefWidthColumns();
        colNombre.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNombre()));
        colApellidos.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getApellido()));
        colGoles.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getGoles()).asObject());
        colAsistencias.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getAsistencias()).asObject());

    tablaJugador.setItems(service.obtenerData);

    }

    private void prefWidthColumns() {
        tablaJugador.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        colNombre.prefWidthProperty().bind(tablaJugador.widthProperty().multiply(0.35));
        colApellidos.prefWidthProperty().bind(tablaJugador.widthProperty().multiply(0.35));
        colGoles.prefWidthProperty().bind(tablaJugador.widthProperty().multiply(0.15));
        colAsistencias.prefWidthProperty().bind(tablaJugador.widthProperty().multiply(0.15));
    }

    @FXML
    public void sendData() {
        try {
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            int goles = Integer.parseInt(txtGoles.getText());
            int asistencias = Integer.parseInt(txtAsistencias.getText());

            service.sendData(new Jugador(nombre, apellido, goles, asistencias));
            tablaJugador.setItems(service.obtenerData());
            limpiarCampos();

        } catch (Exception e) {
            mostrarError(e.getMessage());
        }
    }





    private void limpiarCampos() {
        txtNombre.clear();
        txtApellido.clear();
        txtGoles.clear();
        txtAsistencias.clear();
    }

    private void mostrarError(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

}