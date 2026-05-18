package org.example.model;

public class Usuario {
    private int id;
    private String nombre;
    private String apellidos;
    private String correo;
    private String contrasenia;

    public Usuario() {
    }

    /**
     * Constructor del usuario, esta vez no es necesario añadir id, ya que no hacemos ningun select que lo necesitermos
     *
     * @param nombre establece el nombre del usuario
     * @param apellidos establece los apellidos del usuario
     * @param correo establece el correo del usuario
     * @param contrasenia establece la contrasenia del usuario
     */
    public Usuario(String nombre, String apellidos, String correo, String contrasenia) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.contrasenia = contrasenia;
    }


    /**
     * Getter del atributo id
     *
     * @return el id del usuario
     */
    public int getId() {
        return id;
    }

    /**
     * Setter del atributo id
     *
     * @param id establece el id del usuario
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter del atributo nombre
     *
     * @return el nombre del usuario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Setter del atributo nombre
     *
     * @param nombre establece el nombre del usuario
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Getter del atributo nombre
     *
     * @return el apellido del usuario
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Setter del atributo apellidos
     *
     * @param apellidos establece el apellido del usuario
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Getter del atributo correo
     *
     * @return el correo del usuario
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Setter del atributo correo
     *
     * @param correo establece el correo del usuario
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Getter del atributo contrasenia
     *
     * @return la contrasenia del usuario
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * Setter del atributo contraseña
     *
     * @param contrasenia establece la contrasenia del usuario
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
}