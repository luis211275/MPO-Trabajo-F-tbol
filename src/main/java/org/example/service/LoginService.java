package org.example.service;

import org.example.dao.LoginDAO;
import org.example.dao.impl.LoginDAOImpl;
import org.example.model.Usuario;
import org.example.exceptions.UsuarioException;

public class LoginService {


    //Llamo al loginDAOImpl
    private LoginDAO loginDAO = new LoginDAOImpl();


    /**
     * Metodo donde a la hora de hacer el login me comprueba si esta registrado, o si el formato es
     * correcto, mirandome si los campos estan llenos o llamando al DAO para comprobar si
     * existe el correo
     *
     * @param correo del input del login
     * @param contrasenia del input del login
     * @return el usuario para saber quien ha iniciado sesion
     * @throws UsuarioException Correo o contrasenia incorrecta
     */
    public Usuario autenticarUsuario(String correo, String contrasenia) throws UsuarioException {
        // 1. Validamos si los campos estan vacios
        if (correo.isEmpty() || contrasenia.isEmpty()) {
            throw new UsuarioException("Por favor, rellena todos los campos.");
        }

        // 2. Llamamos al DAO y comprobamos si los usuarios existen gracias al correo
        Usuario usuario = loginDAO.obtenerUsuarioPorCorreo(correo);
        if (usuario == null) {
            throw new UsuarioException("El correo electrónico o la contraseña son incorrectos.");
        }

        // 3. Comprobamos la contraseña con la base de datos, como no la encriptamos no usamos ByCrypt
        if (!usuario.getContrasenia().equals(contrasenia)) {
            throw new UsuarioException("El correo electrónico o la contraseña son incorrectos.");
        }

        // Todo correo, devolvemos el usuario
        return usuario;
    }
}
