package com.example.ejemploscftic.dto;


public class Coche {

    private int id;
    private String modelo;
    private Persona persona;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Coche (int id, String modelo)
    {
        this.id = id;
        this.modelo = modelo;
    }

    public Coche (String modelo)
    {
        this.modelo = modelo;
    }

    public Persona getPersona() {
        return persona;
    }

    public Coche () {}

    public Coche (String modelo, Persona persona)
    {
        this.modelo = modelo;
        this.persona = persona;
    }

}
