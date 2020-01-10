package com.example.alquilervehiculosfront.builder;

public class RespuestaServicioDataBuilder {

    private int codigoEstado;
    private boolean estado;

    public RespuestaServicioDataBuilder(int codigoEstado, boolean estado) {
        this.codigoEstado = codigoEstado;
        this.estado = estado;
    }

    public int getCodigoEstado() {
        return codigoEstado;
    }

    public boolean isEstado() {
        return estado;
    }
}