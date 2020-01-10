package com.example.alquilervehiculosfront.builder;

import com.example.alquilervehiculosfront.datos.respuesta.RespuestaServicioPost;

public class RespuestaServicioPostDataBuilder extends RespuestaServicioDataBuilder {

    private String mensaje;

    public RespuestaServicioPostDataBuilder() {
        super(200, true);
        this.mensaje = "Operacion exitosa";
    }

    public RespuestaServicioPost build() {
        return new RespuestaServicioPost(mensaje, getCodigoEstado(), isEstado());
    }
}