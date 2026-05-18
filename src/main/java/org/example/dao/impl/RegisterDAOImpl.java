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


    /**
     * Metodo donde hara un select de la tabla usuarios, ahi comprobara si existe el correo que has introducido
     * si existe, no te registrará y cambiara el boolean
     *
     * @param correo el correo del input que se pone en el registro
     * @return boolean si existe el correo o por el otro lado, no existe
     * @throws UsuarioException error al comprobar el correo en la base de datos
     */
    @Override
    public boolean existeCorreo(String correo) throws UsuarioException {
        //consulta de sql
        String sql = "SELECT COUNT(*) FROM USUARIOS WHERE CORREO = ?";
        // Hace la conexion a mi base de datos
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            //cambiamos el ? por un correo real, ya que queremos comprobar si lo comprueba
            ps.setString(1, correo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    //el resultado del count siempre es serial (0,1,2...) esto hace que si da 0 significa que esta libre y que no existe, en cambio, si detecta un numero es por que lo tiene otra persona, entonces dara false
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new UsuarioException("Error al comprobar el correo en la base de datos.");
        }
        return false;
    }


    /**
     * Me hace un insert de los datos introducidos de los inputs para metermelos en la tabla usuarios de la
     * base de datos
     *
     * @param usuario introducido en el register.fxml
     * @throws UsuarioException error al insertar el usuario
     */
    @Override
    public void registrarUsuario(Usuario usuario) throws UsuarioException {
        //peticio sql
        String sql = "INSERT INTO USUARIOS (NOMBRE, APELLIDOS, CORREO, CONTRASENIA) VALUES (?, ?, ?, ?)";

        //cojo los datos y los meto usando gets
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellidos());
            ps.setString(3, usuario.getCorreo());
            ps.setString(4, usuario.getContrasenia()); //se puede meter una encriptacion


            //orden definitiva, me lo mete en la tabla usuarios
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new UsuarioException("Error crítico al insertar el usuario en la base de datos.");
        }
    }
}