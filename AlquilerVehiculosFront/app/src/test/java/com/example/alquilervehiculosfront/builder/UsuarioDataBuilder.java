package com.example.alquilervehiculosfront.builder;

import com.example.alquilervehiculosfront.dominio.modelo.Usuario;

public class UsuarioDataBuilder {

    private Long cedula;

    private String nombres;

    private String apellidos;

    private String fechaNacimiento;

    public UsuarioDataBuilder() {
        cedula = Long.valueOf("10949351");
        nombres = "Jairo Andres";
        apellidos = "Rios Franco";
        fechaNacimiento = "2019-12-27";
    }

    public Usuario build() {
        return new Usuario(cedula, nombres, apellidos, fechaNacimiento);
    }
}