package org.example.dao.impl;

import org.example.config.DatabaseConfig;
import org.example.dao.LoginDAO;
import org.example.model.Usuario;
import org.example.exceptions.UsuarioException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class LoginDAOImpl implements LoginDAO {


    /**
     * Este metodo va a recibir el correo que recibio del login y hara una busqueda con un select,
     * para mirar los correos registrados, posteriormente buscaremos el correo que queremos encontrar,
     * hace la consulta, para ver si hay algun correo igual, si no lo hay, dara error de que el correo
     * escrito esta mal
     *
     * @param correo escrito en el input
     * @return correo recibido, si es correcto
     * @throws UsuarioException error al consultar en la base de datos
     */
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