package com.example.alquilervehiculosfront.datos.repositorioimpl;

import androidx.lifecycle.MutableLiveData;

import com.example.alquilervehiculosfront.datos.respuesta.RespuestaServicioGet;
import com.example.alquilervehiculosfront.datos.respuesta.RespuestaServicioPost;
import com.example.alquilervehiculosfront.datos.restutil.UrlServicio;
import com.example.alquilervehiculosfront.datos.llamadorest.LlamadoAlquilerRest;
import com.example.alquilervehiculosfront.datos.restutil.CodigoEstadoRespuesta;
import com.example.alquilervehiculosfront.dominio.modelo.AlquilarVehiculo;
import com.example.alquilervehiculosfront.dominio.repositorio.RepositorioAlquiler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RepositorioAlquilerImpl implements RepositorioAlquiler {

    private LlamadoAlquilerRest llamadoAlquilerRest;
    private static final String ALQUILER_REGISTRADO = "Alquiler registrado";
    private static final String ALQUILER_DEVUELTO = "Vehiculo devuelto";
    private static final String SERVIDOR_APAGADO = "Fallo de conexion con el servidor";

    public RepositorioAlquilerImpl() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(UrlServicio.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        llamadoAlquilerRest = retrofit.create(LlamadoAlquilerRest.class);
    }

    @Override
    public MutableLiveData<RespuestaServicioPost> alquilar(AlquilarVehiculo alquilarVehiculo) {
        final MutableLiveData<RespuestaServicioPost> resultado = new MutableLiveData<>();
        llamadoAlquilerRest.alquilar(alquilarVehiculo).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                RespuestaServicioPost respuestaServicioPost;
                if (response.code() == CodigoEstadoRespuesta.OK) {
                    respuestaServicioPost = new RespuestaServicioPost(ALQUILER_DEVUELTO, response.code(), true);
                } else {
                    respuestaServicioPost = new RespuestaServicioPost(errorRespuesta(response.errorBody()), response.code(), false);
                }
                resultado.setValue(respuestaServicioPost);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                RespuestaServicioPost respuestaServicioPost = new RespuestaServicioPost(SERVIDOR_APAGADO, CodigoEstadoRespuesta.SERVIDOR_APAGADO, false);
                resultado.setValue(respuestaServicioPost);
            }
        });
        return resultado;
    }

    @Override
    public MutableLiveData<RespuestaServicioGet> devolver(String placaVehiculo) {
        final MutableLiveData<RespuestaServicioGet> resultado = new MutableLiveData<>();
        llamadoAlquilerRest.devolver(placaVehiculo).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                RespuestaServicioGet respuestaServicioGet;
                if (response.code() == CodigoEstadoRespuesta.OK) {
                    respuestaServicioGet = new RespuestaServicioGet(ALQUILER_REGISTRADO, response.code(), true);
                } else {
                    respuestaServicioGet = new RespuestaServicioGet(errorRespuesta(response.errorBody()), response.code(), false);
                }
                resultado.setValue(respuestaServicioGet);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                RespuestaServicioGet respuestaServicioGet = new RespuestaServicioGet(SERVIDOR_APAGADO, CodigoEstadoRespuesta.SERVIDOR_APAGADO, false);
                resultado.setValue(respuestaServicioGet);
            }
        });
        return resultado;
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