package com.example.alquilervehiculosfront.datos.repositorioimpl;

import androidx.lifecycle.MutableLiveData;

import com.example.alquilervehiculosfront.datos.respuesta.RespuestaServicio;
import com.example.alquilervehiculosfront.datos.restutil.Endpoint;
import com.example.alquilervehiculosfront.datos.restutil.StatusResponse;
import com.example.alquilervehiculosfront.datos.llamadorest.LlamadoUsuarioRest;
import com.example.alquilervehiculosfront.dominio.modelo.Usuario;
import com.example.alquilervehiculosfront.dominio.repositorio.RepositorioUsuario;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RepositorioUsuarioImpl implements RepositorioUsuario {

    private LlamadoUsuarioRest llamadoUsuarioRest;
    private static final String USUARIO_REGISTRADO = "Usuario registrado";
    private static final String SERVIDOR_APAGADO = "Fallo de conexion con el servidor";

    public RepositorioUsuarioImpl() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Endpoint.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        llamadoUsuarioRest = retrofit.create(LlamadoUsuarioRest.class);
    }

    @Override
    public MutableLiveData<RespuestaServicio> registrar(Usuario usuario) {
        final MutableLiveData<RespuestaServicio> resultado = new MutableLiveData<>();
        llamadoUsuarioRest.registrar(usuario).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                RespuestaServicio respuestaServicio;
                if (response.code() == StatusResponse.OK) {
                    respuestaServicio = new RespuestaServicio(USUARIO_REGISTRADO, response.code(), true);
                } else {
                    respuestaServicio = new RespuestaServicio(errorRespuesta(response.errorBody()), response.code(), false);
                }
                resultado.setValue(respuestaServicio);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                RespuestaServicio respuestaServicio = new RespuestaServicio(SERVIDOR_APAGADO, StatusResponse.SERVIDOR_APAGADO, false);
                resultado.setValue(respuestaServicio);
            }
        });
        return resultado;
    }

    @Override
    public Usuario buscar(Long cedula) {
        return null;
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
