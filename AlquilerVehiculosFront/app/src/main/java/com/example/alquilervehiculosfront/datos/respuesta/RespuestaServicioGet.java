package com.example.alquilervehiculosfront.datos.respuesta;

public class RespuestaServicioGet extends RespuestaServicio {

    private Object objeto;

    public RespuestaServicioGet(Object objeto, int codigoEstado, boolean estado) {
        super(codigoEstado, estado);
        this.objeto = objeto;
    }

    public Object getObjeto() {
        return objeto;
    }

    public void setObjeto(Object objeto) {
        this.objeto = objeto;
    }
}
