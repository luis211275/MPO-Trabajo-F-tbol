package org.example.model;

public class Club {
    private int id_equipo;
    private String nombre;
    private int fundacion;
    private String presidente;

    public Club(int id_equipo, String nombre, int fundacion, String presidente) {
        this.id_equipo = id_equipo;
        this.nombre = nombre;
        this.fundacion = fundacion;
        this.presidente = presidente;
    }


    public int getId_equipo() {
        return id_equipo;
    }

    public void setId_equipo(int id_equipo) {
        this.id_equipo = id_equipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFundacion() {
        return fundacion;
    }

    public void setFundacion(int fundacion) {
        this.fundacion = fundacion;
    }

    public String getPresidente() {
        return presidente;
    }

    public void setPresidente(String presidente) {
        this.presidente = presidente;
    }
}
