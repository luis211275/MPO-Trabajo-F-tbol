package org.example.service;

import org.example.dao.JugadorDAO;
import org.example.dao.impl.JugadorDAOImpl;
import org.example.model.Jugador;
import org.example.exceptions.JugadorException;
import java.util.List;

public class JugadorService {


    //Llamo al jugadorDAOImpl
    private final JugadorDAO jugadorDAO = new JugadorDAOImpl();




    /**
     * Metodo que me ejecuta cuando le doy a elviar en el main.fxml, donde primero me comprueba los
     * parametros de los goles y las asistencias, y despues me manda al DAO, para hacer el metodo del dao
     *
     * @param jugador jugador que creo mediante los inputs del main
     * @throws JugadorException mira si los goles o las asistencias las he puesto en negativo
     */
    public void agregarJugador(Jugador jugador) throws JugadorException {
        // Aquí podrías validar cosas, por ejemplo:
        if(jugador.getGoles() < 0 || jugador.getAsistencias() < 0) {
            throw new JugadorException("Los goles y asistencias no pueden ser negativos.");
        }
        //llama al DAO
        jugadorDAO.insertar(jugador);
    }

    /**
     * Metodo que llama al DAO para sacar todos los datos de los jugadores que hay registrados en la base de datos
     *
     * @return el metodo del DAO donde me devuelve toda la lista de jugadores que hay en la base de datos
     * @throws JugadorException error en los datos
     */
    public List<Jugador> listarJugadores() throws JugadorException {
        return jugadorDAO.obtenerTodos();
    }
}