package org.example.service;

import org.example.dao.LoginDAO;
import org.example.dao.impl.LoginDAOImpl;
import org.example.model.Usuario;
import org.example.exceptions.UsuarioException;

public class LoginService {
    private LoginDAO loginDAO = new LoginDAOImpl();

    public Usuario autenticarUsuario(String correo, String contrasenia) throws UsuarioException {
        // 1. Validar campos vacíos
        if (correo.isEmpty() || contrasenia.isEmpty()) {
            throw new UsuarioException("Por favor, rellena todos los campos.");
        }

        // 2. Buscar si el usuario existe por correo
        Usuario usuario = loginDAO.obtenerUsuarioPorCorreo(correo);
        if (usuario == null) {
            throw new UsuarioException("El correo electrónico o la contraseña son incorrectos.");
        }

        // 3. Comprobar contraseña
        // Nota: Si en el register aplicaste Hash (BCrypt), aquí deberías usar BCrypt.checkpw
        if (!usuario.getContrasenia().equals(contrasenia)) {
            throw new UsuarioException("El correo electrónico o la contraseña son incorrectos.");
        }

        // Si todo coincide, devolvemos el objeto Usuario para saber quién se ha logueado
        return usuario;
    }
}
