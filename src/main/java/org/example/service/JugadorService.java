package org.example.service;

import org.example.dao.JugadorDAO;
import org.example.dao.impl.JugadorDAOImpl;
import org.example.model.Jugador;
import org.example.exceptions.JugadorException;
import java.util.List;

public class JugadorService {

    private final JugadorDAO jugadorDAO;

    public JugadorService() {
        this.jugadorDAO = new JugadorDAOImpl();
    }

    public void agregarJugador(Jugador jugador) throws JugadorException {
        // Aquí podrías validar cosas, por ejemplo:
        if(jugador.getGoles() < 0 || jugador.getAsistencias() < 0) {
            throw new JugadorException("Los goles y asistencias no pueden ser negativos.");
        }
        jugadorDAO.insertar(jugador);
    }

    public List<Jugador> listarJugadores() throws JugadorException {
        return jugadorDAO.obtenerTodos();
    }
}