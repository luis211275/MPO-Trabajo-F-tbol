package org.example.dao.impl;

import org.example.config.DatabaseConfig;
import org.example.dao.LoginDAO;
import org.example.model.Usuario;
import org.example.exceptions.UsuarioException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
// Importa tu clase de conexión aquí (ej: util.ConexionBD)

public class LoginDAOImpl implements LoginDAO {

    @Override
    public Usuario obtenerUsuarioPorCorreo(String correo) throws UsuarioException {
        String sql = "SELECT * FROM USUARIOS WHERE CORREO = ?";

        // Reemplaza 'ConexionBD.getConnection()' por tu método real de conexión
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, correo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getInt("ID"));
                    usuario.setNombre(rs.getString("NOMBRE"));
                    usuario.setApellidos(rs.getString("APELLIDOS"));
                    usuario.setCorreo(rs.getString("CORREO"));
                    usuario.setContrasenia(rs.getString("CONTRASENIA"));
                    return usuario;
                }
            }
        } catch (SQLException e) {
            throw new UsuarioException("Error al consultar el usuario en la base de datos.");
        }
        return null; // Si no encuentra ningún usuario con ese correo
    }
}