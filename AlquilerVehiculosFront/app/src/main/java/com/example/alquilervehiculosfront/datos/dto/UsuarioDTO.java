package com.example.alquilervehiculosfront.datos.dto;

public class UsuarioDTO {

    private Long cedula;

    private String nombres;

    private String apellidos;

    private String fechaNacimiento;

    public void setCedula(Long cedula) {
        this.cedula = cedula;
    }

    public Long getCedula() {
        return cedula;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getNombres() {
        return nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }
}