package com.example.alquilervehiculosfront.dominio.servicios;

import android.util.Log;

import com.example.alquilervehiculosfront.datos.repositorioimpl.RepositorioUsuarioImpl;
import com.example.alquilervehiculosfront.dominio.modelo.Usuario;
import com.example.alquilervehiculosfront.dominio.repositorio.RepositorioUsuario;

public class ServicioUsuarioDominio {

    private RepositorioUsuario repositorioUsuario;

    public ServicioUsuarioDominio() {
        repositorioUsuario = new RepositorioUsuarioImpl();
    }

    public void registrar(Usuario usuario) {
        boolean resultado = repositorioUsuario.registrar(usuario);
        Log.e("------", resultado + "");

        /**

         servicioUsuario.registrar(usuario).enqueue(new Callback<Void>() {
        @Override public void onResponse(Call<Void> call, Response<Void> response) {
        AdministrarUsuarioFragment fragment = (AdministrarUsuarioFragment) ((MainActivity)
        App.getContext()).getSupportFragmentManager().findFragmentByTag(FragmentTags.ADMINISTRAR_USUARIO_FRAGMENT);
        if (fragment != null) {
        fragment.dismissDialog();
        if (response.code() == StatusResponse.OK) {
        fragment.resultadoRegistrar();
        } else {
        fragment.mensajeError(errorRespuesta(response.errorBody()));
        }
        }
        }

        @Override public void onFailure(Call<Void> call, Throwable t) {
        t.printStackTrace();
        }
        });**/
    }

    public void buscar(Long cedulaUsuario) {
        /**
         servicioUsuario.buscar(cedulaUsuario).enqueue(new Callback<UsuarioDTO>() {
        @Override public void onResponse(Call<UsuarioDTO> call, Response<UsuarioDTO> response) {
        AdministrarUsuarioFragment fragment = (AdministrarUsuarioFragment) ((MainActivity)
        App.getContext()).getSupportFragmentManager().findFragmentByTag(FragmentTags.ADMINISTRAR_USUARIO_FRAGMENT);
        if (fragment != null) {
        fragment.dismissDialog();
        if (response.body() != null) {
        fragment.resultadoBuscar(response.body());
        } else {
        fragment.mensajeError(App.getContext().getResources().getString(R.string.fragment_administrar_usuario_no_encontrado));
        }
        }
        }

        @Override public void onFailure(Call<UsuarioDTO> call, Throwable t) {
        t.printStackTrace();
        }
        });**/
    }

    /**
     private String errorRespuesta(ResponseBody errorBody) {
     try {
     JSONObject jsonObject = new JSONObject(errorBody.string());
     return jsonObject.getString("message");
     } catch (JSONException | IOException e) {
     e.printStackTrace();
     }
     return "";
     }**/
}