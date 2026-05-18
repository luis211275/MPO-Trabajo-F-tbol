package org.example.model;

public class Jugador {
    private int id; // Usualmente la BD genera esto
    private String nombre;
    private String apellido;
    private int goles;
    private int asistencias;


    /**
     * ¿Porque dos? Constructor sin el id, ya que a la hora de rellenar los inputs no se fija en los id, y la base de datos te la crea automaticamente con el autoIncrement
     *
     * @param nombre establece el nombre del jugador
     * @param apellido establece el apellido del jugador
     * @param goles establece los goles del jugador
     * @param asistencias establecen las asistencias del jugador
     */
    public Jugador(String nombre, String apellido, int goles, int asistencias) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.goles = goles;
        this.asistencias = asistencias;
    }


    /**
     * Este constructor con id lo uso principalmente en los metodos donde tengo que leer fila por fila de la base de datos, y ahi si que debo tener en cuenta el id
     *
     * @param id establece el id del jugador
     * @param nombre establece el nombre del jugador
     * @param apellido establece el apellido del jugador
     * @param goles establece los goles del jugador
     * @param asistencias establece las asistencias del jugador
     */
    public Jugador(int id, String nombre, String apellido, int goles, int asistencias) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.goles = goles;
        this.asistencias = asistencias;
    }

    /**
     * Getter del atributo id
     *
     * @return el id del jugador
     */
    public int getId() {
        return id;
    }

    /**
     * Setter del atributo id
     *
     * @param id establece el id del jugador
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter del atributo nombre
     *
     * @return el nombre del jugador
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Setter del atributo nombre
     *
     * @param nombre establece el nombre del jugador
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    /**
     * Getter del atributo apellido
     *
     * @return el apellido del jugador
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Setter del apellido jugador
     *
     * @param apellido establece el apellido del jugador
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Getter del atributo goles
     *
     * @return los goles del jugador
     */
    public int getGoles() {
        return goles;
    }

    /**
     * Setter del atributo goles
     *
     * @param goles establece los goles del jugador
     */
    public void setGoles(int goles) {
        this.goles = goles;
    }


    /**
     * Getter del atributo asistencias
     *
     * @return las asistencias del jugador
     */
    public int getAsistencias() {
        return asistencias;
    }


    /**
     * Setter del atributo asistencias
     *
     * @param asistencias establece las asistencias del jugador
     */
    public void setAsistencias(int asistencias) {
        this.asistencias = asistencias;
    }
}