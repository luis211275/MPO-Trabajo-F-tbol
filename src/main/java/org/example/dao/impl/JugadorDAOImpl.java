package org.example.dao.impl;

import org.example.config.DatabaseConfig;
import org.example.dao.JugadorDAO;
import org.example.model.Jugador;
import org.example.exceptions.JugadorException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JugadorDAOImpl implements JugadorDAO {

    @Override
    public void insertar(Jugador jugador) throws JugadorException {
        String sql = "INSERT INTO jugador (nombre, apellido, goles, asistencias) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, jugador.getNombre());
            pstmt.setString(2, jugador.getApellido());
            pstmt.setInt(3, jugador.getGoles());
            pstmt.setInt(4, jugador.getAsistencias());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new JugadorException("Error al insertar el jugador en la base de datos");
        }
    }

    @Override
    public List<Jugador> obtenerTodos() throws JugadorException {
        List<Jugador> jugadores = new ArrayList<>();
        String sql = "SELECT * FROM jugador";

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

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
