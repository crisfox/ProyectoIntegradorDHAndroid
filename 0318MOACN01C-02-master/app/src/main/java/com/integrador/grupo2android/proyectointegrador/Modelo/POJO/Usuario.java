package com.integrador.grupo2android.proyectointegrador.Modelo.POJO;

public class Usuario {
    private String nombre;
    private String apellido;
    private Integer foto;

    public String getApellido() {
        return apellido;
    }

    public Usuario(String nombre, String apellido, Integer foto) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getFoto() {
        return foto;
    }
}