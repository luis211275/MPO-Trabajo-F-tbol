package org.example.dao;

import org.example.model.Jugador;
import org.example.exceptions.JugadorException;
import java.util.List;

public interface JugadorDAO {

    /**
     * la orden que hace guardar a los futbolistas, ¿Por que esta separado? admite mas escalabilidad
     * obliga a tener una clase Jugador para ejcutar la insercion
     *
     * @param jugador jugador que escribimos nosotros
     * @throws JugadorException por si la insercion falla
     */
    void insertar(Jugador jugador) throws JugadorException;

    /**
     * La orden obligatorioa para listar a todos los jugadores que se piden en la select * from
     * jugador
     *
     * @return la select de todos los jugadores que estan registrados en la base de datos
     * @throws JugadorException error por si la select falla
     */
    List<Jugador> obtenerTodos() throws JugadorException;
}