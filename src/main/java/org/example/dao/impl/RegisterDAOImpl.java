package org.example.dao.impl;


import org.example.config.DatabaseConfig;
import org.example.dao.RegisterDAO;
import org.example.model.Usuario;
import org.example.exceptions.UsuarioException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
// Importa tu clase de conexión aquí (ej: util.ConexionBD)

public class RegisterDAOImpl implements RegisterDAO {

    @Override
    public boolean existeCorreo(String correo) throws UsuarioException {
        String sql = "SELECT COUNT(*) FROM USUARIOS WHERE CORREO = ?";
        // Reemplaza 'ConexionBD.getConnection()' por tu método real de conexión
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, correo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new UsuarioException("Error al comprobar el correo en la base de datos.");
        }
        return false;
    }

    @Override
    public void registrarUsuario(Usuario usuario) throws UsuarioException {
        String sql = "INSERT INTO USUARIOS (NOMBRE, APELLIDOS, CORREO, CONTRASENIA) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellidos());
            ps.setString(3, usuario.getCorreo());
            ps.setString(4, usuario.getContrasenia()); // Nota: Idealmente aquí aplicarías un Hash (BCrypt)

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new UsuarioException("Error crítico al insertar el usuario en la base de datos.");
        }
    }
}