package org.example.dao;


import org.example.model.Usuario;
import org.example.exceptions.UsuarioException;

public interface LoginDAO {
    Usuario obtenerUsuarioPorCorreo(String correo) throws UsuarioException;
}
