package org.example.service;


import org.example.dao.RegisterDAO;
import org.example.dao.impl.RegisterDAOImpl;
import org.example.model.Usuario;
import org.example.exceptions.UsuarioException;

public class RegisterService {

    //Llamamos al registerDAO para hacer las consultas de correos
    private RegisterDAO registerDAO = new RegisterDAOImpl();


    /**
     * Metodo donde a la hora de crear el usuario me hace diferentes validaciones, como si esta vacio
     * si contiene un @ el correo, asi mejorar la forma de escribir el correo y tambien si existe duplicados del correo
     * que hemos introducido
     *
     * @param nombre del input de register
     * @param apellidos del input de register
     * @param correo del input de register
     * @param contrasenia del input de register
     * @throws UsuarioException campos obligatorios, correo electronico ya registrado o email no valido
     */
    public void registrarNuevoUsuario(String nombre, String apellidos, String correo, String contrasenia) throws UsuarioException {
        // Vacio o no
        if (nombre.isEmpty() || apellidos.isEmpty() || correo.isEmpty() || contrasenia.isEmpty()) {
            throw new UsuarioException("Todos los campos son obligatorios.");
        }

        //Contiene @ el email
        if (!correo.contains("@")) {
            throw new UsuarioException("El formato del correo electrónico no es válido.");
        }

        // Comprobar duplicado, con DAO
        if (registerDAO.existeCorreo(correo)) {
            throw new UsuarioException("Este correo electrónico ya está registrado.");
        }

        // Todo correcto = guardamos
        Usuario nuevoUsuario = new Usuario(nombre, apellidos, correo, contrasenia);
        registerDAO.registrarUsuario(nuevoUsuario);
    }
}
