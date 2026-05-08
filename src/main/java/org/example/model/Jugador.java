package org.example.model;

public class Jugador {
    private String nombre;
    private String apellido;
    private int goles;
    private int asistencias;


    public Jugador( String nombre, String apellido, int goles, int asistencias) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.goles = goles;
        this.asistencias = asistencias;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getGoles() {
        return goles;
    }

    public void setGoles(int goles) {
        this.goles = goles;
    }

    public int getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(int asistencias) {
        this.asistencias = asistencias;
    }


}
