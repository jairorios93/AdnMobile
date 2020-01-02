package com.example.alquilervehiculosfront.aplicacion.servicios;

import com.example.alquilervehiculosfront.R;
import com.example.alquilervehiculosfront.aplicacion.helper.StatusResponse;
import com.example.alquilervehiculosfront.aplicacion.rest.ServicioVehiculo;
import com.example.alquilervehiculosfront.dominio.context.App;
import com.example.alquilervehiculosfront.dominio.helper.FragmentTags;
import com.example.alquilervehiculosfront.dominio.modelo.Vehiculo;
import com.example.alquilervehiculosfront.aplicacion.helper.Endpoint;
import com.example.alquilervehiculosfront.aplicacion.dto.VehiculoDTO;
import com.example.alquilervehiculosfront.vistas.AdministrarVehiculoFragment;
import com.example.alquilervehiculosfront.vistas.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServicioVehiculoApplication {

    private ServicioVehiculo servicioVehiculo;

    public ServicioVehiculoApplication() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Endpoint.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        servicioVehiculo = retrofit.create(ServicioVehiculo.class);
    }

    public void registrar(Vehiculo vehiculo) {
        servicioVehiculo.registrar(vehiculo).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                AdministrarVehiculoFragment fragment = (AdministrarVehiculoFragment) ((MainActivity)
                        App.getContext()).getSupportFragmentManager().findFragmentByTag(FragmentTags.ADMINISTRAR_VEHICULO_FRAGMENT);
                if (fragment != null) {
                    fragment.dismissDialog();
                    if (response.code() == StatusResponse.OK) {
                        fragment.resultadoRegistrar();
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

    public void buscar(String placaVehiculo) {
        servicioVehiculo.buscar(placaVehiculo).enqueue(new Callback<VehiculoDTO>() {
            @Override
            public void onResponse(Call<VehiculoDTO> call, Response<VehiculoDTO> response) {
                AdministrarVehiculoFragment fragment = (AdministrarVehiculoFragment) ((MainActivity)
                        App.getContext()).getSupportFragmentManager().findFragmentByTag(FragmentTags.ADMINISTRAR_VEHICULO_FRAGMENT);
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