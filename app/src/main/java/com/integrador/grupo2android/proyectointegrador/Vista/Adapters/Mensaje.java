package com.integrador.grupo2android.proyectointegrador.Vista.Adapters;

/**
 * Created by emmchierchie on 23/7/18.
 */

public class Mensaje {
    private String mensaje;
    private String nameUser;
    private String clock;

    public Mensaje() {
    }

    public Mensaje(String mensaje, String nameUser, String clock) {
        this.mensaje = mensaje;
        this.nameUser = nameUser;
        this.clock = clock;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getClock() {
        return clock;
    }

    public void setClock(String clock) {
        this.clock = clock;
    }
}
