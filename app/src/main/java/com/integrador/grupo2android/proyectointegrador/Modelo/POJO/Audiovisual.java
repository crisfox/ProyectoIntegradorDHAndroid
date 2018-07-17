package com.integrador.grupo2android.proyectointegrador.Modelo.POJO;

public class Audiovisual {
    private String titulo;
    private Integer foto;

    public Audiovisual(Integer imagen, String nombre) {
        this.foto = imagen;
        this.titulo = nombre;
    }

    public String getTitulo() {
        return titulo;
    }

    public Integer getfoto() {
        return foto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Audiovisual that = (Audiovisual) o;

        if (titulo != null ? !titulo.equals(that.titulo) : that.titulo != null) return false;
        return foto != null ? foto.equals(that.foto) : that.foto == null;
    }
}
