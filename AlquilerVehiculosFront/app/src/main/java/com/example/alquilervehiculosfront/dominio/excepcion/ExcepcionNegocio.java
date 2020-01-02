package com.example.alquilervehiculosfront.dominio.excepcion;

public class ExcepcionNegocio extends RuntimeException {

    public ExcepcionNegocio(String mensaje){
        super(mensaje);
    }
}
