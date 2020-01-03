package com.example.alquilervehiculosfront.datos.respuesta;

public class RespuestaServicio {

    private String mensaje;
    private int codigoEstado;
    private boolean estado;

    public RespuestaServicio(String mensaje, int codigoEstado, boolean estado) {
        this.mensaje = mensaje;
        this.codigoEstado = codigoEstado;
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getCodigoEstado() {
        return codigoEstado;
    }

    public void setCodigoEstado(int codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
