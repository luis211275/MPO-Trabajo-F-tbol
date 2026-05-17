package org.example.dao;

import org.example.model.Usuario;
import org.example.exceptions.UsuarioException;

public interface RegisterDAO {
    void registrarUsuario(Usuario usuario) throws UsuarioException;
    boolean existeCorreo(String correo) throws UsuarioException;
}