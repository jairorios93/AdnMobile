package com.example.alquilervehiculosfront.datos.repositorioimpl;

import androidx.lifecycle.MutableLiveData;

import com.example.alquilervehiculosfront.datos.dto.VehiculoDTO;
import com.example.alquilervehiculosfront.datos.llamadorest.LlamadoVehiculoRest;
import com.example.alquilervehiculosfront.datos.respuesta.RespuestaServicioGet;
import com.example.alquilervehiculosfront.datos.respuesta.RespuestaServicioPost;
import com.example.alquilervehiculosfront.datos.restutil.UrlServicio;
import com.example.alquilervehiculosfront.datos.restutil.CodigoEstadoRespuesta;
import com.example.alquilervehiculosfront.dominio.modelo.Vehiculo;
import com.example.alquilervehiculosfront.dominio.repositorio.RepositorioVehiculo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RepositorioVehiculoImpl implements RepositorioVehiculo {

    private LlamadoVehiculoRest llamadoVehiculoRest;
    private static final String VEHICULO_REGISTRADO = "Vehiculo registrado";
    private static final String SERVIDOR_APAGADO = "Fallo de conexion con el servidor";

    @Inject
    public RepositorioVehiculoImpl() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(UrlServicio.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        llamadoVehiculoRest = retrofit.create(LlamadoVehiculoRest.class);
    }

    @Override
    public MutableLiveData<RespuestaServicioPost> registrar(Vehiculo vehiculo) {
        final MutableLiveData<RespuestaServicioPost> resultado = new MutableLiveData<>();
        llamadoVehiculoRest.registrar(vehiculo).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                RespuestaServicioPost respuestaServicioPost;
                if (response.code() == CodigoEstadoRespuesta.OK) {
                    respuestaServicioPost = new RespuestaServicioPost(VEHICULO_REGISTRADO, response.code(), true);
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
    public MutableLiveData<RespuestaServicioGet> buscar(String placaVehiculo) {
        final MutableLiveData<RespuestaServicioGet> resultado = new MutableLiveData<>();
        llamadoVehiculoRest.buscar(placaVehiculo).enqueue(new Callback<VehiculoDTO>() {
            @Override
            public void onResponse(Call<VehiculoDTO> call, Response<VehiculoDTO> response) {
                if (response.body() != null) {
                    Vehiculo vehiculo = new Vehiculo(response.body().getPlaca(), response.body().getModelo(),
                            response.body().getMarca(), response.body().getColor(), response.body().getPrecio());
                    RespuestaServicioGet respuestaServicioGet = new RespuestaServicioGet(vehiculo, response.code(), true);
                    resultado.setValue(respuestaServicioGet);
                }
            }

            @Override
            public void onFailure(Call<VehiculoDTO> call, Throwable t) {
                RespuestaServicioGet respuestaServicioGet = new RespuestaServicioGet(null, CodigoEstadoRespuesta.ERROR, false);
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