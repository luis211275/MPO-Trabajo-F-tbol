package org.example.dao;

import org.example.model.Usuario;
import org.example.exceptions.UsuarioException;

public interface RegisterDAO {

    /**
     * Orden del metodo de registrar usuario
     *
     * @param usuario suario introducido en el register
     * @throws UsuarioException error a la hora de introducir el usuario en la base de datos
     */
    void registrarUsuario(Usuario usuario) throws UsuarioException;

    /**
     * Orden del metodo existe correo
     *
     * @param correo el correo introducido
     * @return boolean de si se repite, o no
     * @throws UsuarioException error por si la base de datos se sobrecarga
     */
    boolean existeCorreo(String correo) throws UsuarioException;
}