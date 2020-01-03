package com.example.alquilervehiculosfront.datos.respuesta;

public abstract class RespuestaServicio {

    private int codigoEstado;
    private boolean estado;

    public RespuestaServicio(int codigoEstado, boolean estado) {
        this.codigoEstado = codigoEstado;
        this.estado = estado;
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
