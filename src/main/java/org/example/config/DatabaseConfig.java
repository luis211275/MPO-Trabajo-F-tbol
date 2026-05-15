package org.example.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * Esta clase sera lo que dará la conexion entre el java y la base de datos.
 */
public class DatabaseConfig {

    /*Direccion de la base de datos*/
    private static final String URL = "jdbc:postgresql://localhost:5432/mpofutbol";

    /*Usuario de MI base de datos*/
    private static final String USERNAME = "postgres";

    /*Contraseña de MI base de datos*/
    private static final String PASSWORD = "1234";


    /**
     * @return nos dara la conexion a la base de datos
     * @throws SQLException por si la base de datos esta apagada o no encuentra conexion con las credenciales que le hemos dado
     */
    public static Connection getConnection() throws SQLException {

        /*Se conecta con los datos que hemos uesto arriba*/
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}