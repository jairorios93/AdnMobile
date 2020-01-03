package com.example.alquilervehiculosfront.dominio.repositorio;

import com.example.alquilervehiculosfront.dominio.modelo.Usuario;

public interface RepositorioUsuario {

    boolean registrar(Usuario usuario);

    Usuario buscar(Long cedula);
}
