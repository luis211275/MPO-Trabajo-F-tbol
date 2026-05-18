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

    //Conecta con la lógica del negocio (el Service)
    private JugadorService jugadorService;

    //Lista de JavaFX, avisa a la interfaz, cuando se añade o se borra una para que se escriba sola
    private ObservableList<Jugador> listaJugadores;


    /**
     * Primer metodo que se ejecuta en el inicio de la pantalla, prepara la lista, indica que columna del
     * Model Jugador querré, al final llama a prefWidthColumns.
     */
    @FXML
    public void initialize() {
        jugadorService = new JugadorService();
        listaJugadores = FXCollections.observableArrayList();

        //Llama al model para saber que poner en cada columna
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        colGoles.setCellValueFactory(new PropertyValueFactory<>("goles"));
        colAsistencias.setCellValueFactory(new PropertyValueFactory<>("asistencias"));


        //llama al metodo
        cargarDatosTabla();


        //llama al otro metodo
        prefWidthColumns();
    }


    /**
     * Colocamos la tabla con el tamaño que queramos, la suma debe dar 1
     */
    private void prefWidthColumns() {
        tablaJugador.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_ALL_COLUMNS);

        colNombre.prefWidthProperty().bind(tablaJugador.widthProperty().multiply(0.35));
        colApellido.prefWidthProperty().bind(tablaJugador.widthProperty().multiply(0.35));
        colGoles.prefWidthProperty().bind(tablaJugador.widthProperty().multiply(0.15));
        colAsistencias.prefWidthProperty().bind(tablaJugador.widthProperty().multiply(0.15));
    }


    /**
     * Metodo cuando se le da al boton de enviar, esto metera un nuevo futbolista
     * @exception NumberFormatException se escriben en un formato que no sea int, salta el error
     * @exception JugadorException si salta algun error en la base de datos
     */
    @FXML

    public void sendData() {
        try {
            //coge los tectos de los inputs y los pasa a text
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            //Los numeros se hace un ParseInt
            int goles = Integer.parseInt(txtGoles.getText());
            int asistencias = Integer.parseInt(txtAsistencias.getText());

            Jugador nuevoJugador = new Jugador(nombre, apellido, goles, asistencias);


            //correcto, se lo envia al Service
            jugadorService.agregarJugador(nuevoJugador);


            //Borra los datos que se habian escrito y carga de nuevo los datos, para que aparezca e nuevo jugador.
            limpiarCampos();
            cargarDatosTabla();

            mostrarMensaje("Éxito", "Jugador guardado correctamente", Alert.AlertType.INFORMATION);

        } catch (NumberFormatException e) {
            mostrarMensaje("Error", "Goles y Asistencias deben ser números enteros.", Alert.AlertType.ERROR);
        } catch (JugadorException e) {
            mostrarMensaje("Error de Base de Datos", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    /**
     * "recarga" la pagina para emter al jugador que se ha añadido
     * @exception JugadorException error si no se cargan los datos correctamente
     */
    private void cargarDatosTabla() {
        try {
            //lo borra
            listaJugadores.clear();
            //lista de nuevo
            listaJugadores.addAll(jugadorService.listarJugadores());
            //añade la lista
            tablaJugador.setItems(listaJugadores);
        } catch (JugadorException e) {
            mostrarMensaje("Error", "No se pudieron cargar los datos de la tabla.", Alert.AlertType.ERROR);
        }
    }


    /**
     * Limpia todos los inputs escritos
     */
    private void limpiarCampos() {
        txtNombre.clear();
        txtApellido.clear();
        txtGoles.clear();
        txtAsistencias.clear();
    }


    /**
     * forma mas sencilla de crear la ventanas de alertas
     *
     * @param titulo titulo del alert
     * @param contenido explicacion del alert
     * @param tipo icono que explica el tipo de alert
     */
    private void mostrarMensaje(String titulo, String contenido, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }


    /**
     * llama al cargar modal del login, en este caso
     */
    @FXML
    private void mostrarLogin() {
        cargarModal("/view/login.fxml", "Inicio de Sesión");
    }


    /**
     * llama a cargar modal del register, en este otro caso
     */
    @FXML
    private void mostrarRegister() {
        cargarModal("/view/register.fxml", "Registro de Usuario");
    }


    /**
     * Hace el cambio de escena, dependiendo de cada caso
     *
     * @param ruta la ruta enviada, en este caso fue la de login y register
     * @param titulo genera la escena
     */
    private void cargarModal(String ruta, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(ruta));
            javafx.scene.Parent root = loader.load();
            //crea una nueva escena
            javafx.stage.Stage stage = new javafx.stage.Stage();
            stage.setTitle(titulo);
            stage.setScene(new javafx.scene.Scene(root));
            //bloquea la ventana de atras, obligando a tener que iniciar sesion primero
            stage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
            stage.show();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}