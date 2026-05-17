package org.example.service;


import org.example.dao.RegisterDAO;
import org.example.dao.impl.RegisterDAOImpl;
import org.example.model.Usuario;
import org.example.exceptions.UsuarioException;

public class RegisterService {
    private RegisterDAO registerDAO = new RegisterDAOImpl();

    public void registrarNuevoUsuario(String nombre, String apellidos, String correo, String contrasenia) throws UsuarioException {
        // Validaciones básicas de negocio
        if (nombre.isEmpty() || apellidos.isEmpty() || correo.isEmpty() || contrasenia.isEmpty()) {
            throw new UsuarioException("Todos los campos son obligatorios.");
        }

        if (!correo.contains("@")) {
            throw new UsuarioException("El formato del correo electrónico no es válido.");
        }

        // Comprobar duplicados
        if (registerDAO.existeCorreo(correo)) {
            throw new UsuarioException("Este correo electrónico ya está registrado.");
        }

        // Si todo está bien, guardamos
        Usuario nuevoUsuario = new Usuario(nombre, apellidos, correo, contrasenia);
        registerDAO.registrarUsuario(nuevoUsuario);
    }
}
