package org.example.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.exceptions.JugadorException;
import org.example.model.Jugador;
import org.example.service.JugadorService;

public class JugadorController {

    // Vincular con los fx:id de tu FXML
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

    // Se ejecuta automáticamente al cargar el FXML
    @FXML
    public void initialize() {
        jugadorService = new JugadorService();
        listaJugadores = FXCollections.observableArrayList();

        // Configurar cómo las columnas leen los datos de la clase Jugador
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        colGoles.setCellValueFactory(new PropertyValueFactory<>("goles"));
        colAsistencias.setCellValueFactory(new PropertyValueFactory<>("asistencias"));

        // Cargar los datos iniciales de la base de datos
        cargarDatosTabla();


        prefWidthColumns();
    }


    private void prefWidthColumns() {
        // Activamos la política para que no cree columnas vacías al final
        tablaJugador.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        // Multiplicamos por el porcentaje decimal que queremos (0.35 = 35%, 0.15 = 15%)
        colNombre.prefWidthProperty().bind(tablaJugador.widthProperty().multiply(0.35));
        colApellido.prefWidthProperty().bind(tablaJugador.widthProperty().multiply(0.35));
        colGoles.prefWidthProperty().bind(tablaJugador.widthProperty().multiply(0.15));
        colAsistencias.prefWidthProperty().bind(tablaJugador.widthProperty().multiply(0.15));
    }

    // El método que se llama al pulsar tu botón "Enviar"
    @FXML
    public void sendData() {
        try {
            // 1. Obtener datos de la interfaz
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            int goles = Integer.parseInt(txtGoles.getText());
            int asistencias = Integer.parseInt(txtAsistencias.getText());

            // 2. Crear objeto Jugador
            Jugador nuevoJugador = new Jugador(nombre, apellido, goles, asistencias);

            // 3. Mandarlo a la base de datos a través del servicio
            jugadorService.agregarJugador(nuevoJugador);

            // 4. Limpiar campos y recargar la tabla
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
}