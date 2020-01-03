package com.example.alquilervehiculosfront.dominio.servicios;

import com.example.alquilervehiculosfront.R;
import com.example.alquilervehiculosfront.datos.restutil.StatusResponse;
import com.example.alquilervehiculosfront.datos.dto.UsuarioDTO;
import com.example.alquilervehiculosfront.datos.dto.VehiculoDTO;
import com.example.alquilervehiculosfront.datos.llamadorest.LlamadoAlquilerRest;
import com.example.alquilervehiculosfront.datos.llamadorest.LlamadoUsuarioRest;
import com.example.alquilervehiculosfront.datos.llamadorest.LlamadoVehiculoRest;
import com.example.alquilervehiculosfront.presentacion.context.App;
import com.example.alquilervehiculosfront.presentacion.fragmentosutil.FragmentTags;
import com.example.alquilervehiculosfront.dominio.modelo.AlquilarVehiculo;
import com.example.alquilervehiculosfront.datos.restutil.Endpoint;
import com.example.alquilervehiculosfront.presentacion.fragmentos.AdministrarAlquilerFragment;
import com.example.alquilervehiculosfront.presentacion.actividades.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServicioAlquilerDominio {

    private LlamadoUsuarioRest servicioUsuario;
    private LlamadoVehiculoRest servicioVehiculo;
    private LlamadoAlquilerRest servicioAlquiler;

    public ServicioAlquilerDominio() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Endpoint.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        servicioUsuario = retrofit.create(LlamadoUsuarioRest.class);
        servicioVehiculo = retrofit.create(LlamadoVehiculoRest.class);
        servicioAlquiler = retrofit.create(LlamadoAlquilerRest.class);
    }

    public void alquilar(AlquilarVehiculo alquilarVehiculo) {







        servicioAlquiler.alquilar(alquilarVehiculo).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                AdministrarAlquilerFragment fragment = (AdministrarAlquilerFragment) ((MainActivity)
                        App.getContext()).getSupportFragmentManager().findFragmentByTag(FragmentTags.ADMINISTRAR_ALQUILER_FRAGMENT);
                if (fragment != null) {
                    fragment.dismissDialog();
                    if (response.code() == StatusResponse.OK) {
                        fragment.resultadoAlquilar();
                    } else {
                        fragment.mensajeError(errorRespuesta(response.errorBody()));
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void devolver(String placaVehiculo) {
        servicioAlquiler.devolver(placaVehiculo).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                AdministrarAlquilerFragment fragment = (AdministrarAlquilerFragment) ((MainActivity)
                        App.getContext()).getSupportFragmentManager().findFragmentByTag(FragmentTags.ADMINISTRAR_ALQUILER_FRAGMENT);
                if (fragment != null) {
                    fragment.dismissDialog();
                    if (response.code() == StatusResponse.OK) {
                        fragment.resultadoDevolver();
                    } else {
                        fragment.mensajeError(errorRespuesta(response.errorBody()));
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void buscar(Long cedulaUsuario) {
        servicioUsuario.buscar(cedulaUsuario).enqueue(new Callback<UsuarioDTO>() {
            @Override
            public void onResponse(Call<UsuarioDTO> call, Response<UsuarioDTO> response) {
                AdministrarAlquilerFragment fragment = (AdministrarAlquilerFragment) ((MainActivity)
                        App.getContext()).getSupportFragmentManager().findFragmentByTag(FragmentTags.ADMINISTRAR_ALQUILER_FRAGMENT);
                if (fragment != null) {
                    fragment.dismissDialog();
                    if (response.body() != null) {
                        fragment.resultadoBuscar(response.body());
                    } else {
                        fragment.mensajeError(App.getContext().getResources().getString(R.string.fragment_administrar_usuario_no_encontrado));
                    }
                }
            }

            @Override
            public void onFailure(Call<UsuarioDTO> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void buscar(String placaVehiculo) {
        servicioVehiculo.buscar(placaVehiculo).enqueue(new Callback<VehiculoDTO>() {
            @Override
            public void onResponse(Call<VehiculoDTO> call, Response<VehiculoDTO> response) {
                AdministrarAlquilerFragment fragment = (AdministrarAlquilerFragment) ((MainActivity)
                        App.getContext()).getSupportFragmentManager().findFragmentByTag(FragmentTags.ADMINISTRAR_ALQUILER_FRAGMENT);
                if (fragment != null) {
                    fragment.dismissDialog();
                    if (response.body() != null) {
                        fragment.resultadoBuscar(response.body());
                    } else {
                        fragment.mensajeError(App.getContext().getResources().getString(R.string.fragment_administrar_vehiculo_no_encontrado));
                    }
                }
            }

            @Override
            public void onFailure(Call<VehiculoDTO> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private String errorRespuesta(ResponseBody errorBody) {
        try {
            JSONObject jsonObject = new JSONObject(errorBody.string());
            return jsonObject.getString("message");
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}