package org.example.dao.impl;

import org.example.config.DatabaseConfig;
import org.example.dao.JugadorDAO;
import org.example.model.Jugador;
import org.example.exceptions.JugadorException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JugadorDAOImpl implements JugadorDAO {

    /**
     * Este metodo recibe el objeto jugador, que ha escrito el usuario en la clase y me lo introduce en la tabla de jugador
     * de la base de datos
     *
     * @param jugador jugador que se ha introducido en los inputs
     * @throws JugadorException error si no se logra añadir el jugador en la base de datos
     */
    @Override
    public void insertar(Jugador jugador) throws JugadorException {
        //hago la orden en sql
        String sql = "INSERT INTO jugador (nombre, apellido, goles, asistencias) VALUES (?, ?, ?, ?)";


        //introduzco los datos a la base de datos
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, jugador.getNombre());
            pstmt.setString(2, jugador.getApellido());
            pstmt.setInt(3, jugador.getGoles());
            pstmt.setInt(4, jugador.getAsistencias());

            //orden definitiva, me lo guarda
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new JugadorException("Error al insertar el jugador en la base de datos");
        }
    }


    /**
     * Metodo que hace obtener a todos los jugadores que hay registrados en la base de datos
     * y los devuelve en una lista de Java
     *
     * @return
     * @throws JugadorException
     */
    @Override
    public List<Jugador> obtenerTodos() throws JugadorException {
        List<Jugador> jugadores = new ArrayList<>();

        //hago la select a la tabla de todos los jugadores
        String sql = "SELECT * FROM jugador";

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            //lee fila por fila y va sacando los datos que obtiene del select
            while (rs.next()) {
                Jugador j = new Jugador(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getInt("goles"),
                        rs.getInt("asistencias")
                );
                jugadores.add(j);
            }

        } catch (SQLException e) {
            throw new JugadorException("Error al obtener los jugadores");
        }
        return jugadores;
    }
}
