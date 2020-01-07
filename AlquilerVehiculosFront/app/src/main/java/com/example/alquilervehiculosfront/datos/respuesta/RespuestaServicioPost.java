package com.example.alquilervehiculosfront.datos.respuesta;

public class RespuestaServicioPost extends RespuestaServicio {

    private String mensaje;

    public RespuestaServicioPost(String mensaje, int codigoEstado, boolean estado) {
        super(codigoEstado, estado);
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}