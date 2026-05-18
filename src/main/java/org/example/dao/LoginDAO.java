package org.example.dao;


import org.example.model.Usuario;
import org.example.exceptions.UsuarioException;

public interface LoginDAO {

    /**
     * Hay que ejecutar los metodos que se han creado, esto hace que el metodo usado funcione
     *
     * @param correo correo que le pasa que recibe del input
     * @return devuelve el correo bien recibido
     * @throws UsuarioException error a la hora de hacer la select
     */
    Usuario obtenerUsuarioPorCorreo(String correo) throws UsuarioException;
}
