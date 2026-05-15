package org.example.dao;

import org.example.model.Jugador;
import org.example.exceptions.JugadorException;
import java.util.List;

public interface JugadorDAO {
    void insertar(Jugador jugador) throws JugadorException;
    List<Jugador> obtenerTodos() throws JugadorException;
}