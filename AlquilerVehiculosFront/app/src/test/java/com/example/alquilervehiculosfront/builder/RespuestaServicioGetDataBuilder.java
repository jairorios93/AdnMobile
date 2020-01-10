package com.example.alquilervehiculosfront.builder;

import com.example.alquilervehiculosfront.datos.respuesta.RespuestaServicioGet;

public class RespuestaServicioGetDataBuilder extends RespuestaServicioDataBuilder {

    public RespuestaServicioGetDataBuilder() {
        super(200, true);
    }

    public RespuestaServicioGet build(Object objeto) {
        return new RespuestaServicioGet(objeto, getCodigoEstado(), isEstado());
    }
}