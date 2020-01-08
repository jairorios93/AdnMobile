package com.example.alquilervehiculosfront.datos.repositorioimpl;

import androidx.lifecycle.MutableLiveData;

import com.example.alquilervehiculosfront.datos.dto.UsuarioDTO;
import com.example.alquilervehiculosfront.datos.respuesta.RespuestaServicioGet;
import com.example.alquilervehiculosfront.datos.respuesta.RespuestaServicioPost;
import com.example.alquilervehiculosfront.datos.restutil.UrlServicio;
import com.example.alquilervehiculosfront.datos.restutil.CodigoEstadoRespuesta;
import com.example.alquilervehiculosfront.datos.llamadorest.LlamadoUsuarioRest;
import com.example.alquilervehiculosfront.dominio.modelo.Usuario;
import com.example.alquilervehiculosfront.dominio.repositorio.RepositorioUsuario;

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

public class RepositorioUsuarioImpl implements RepositorioUsuario {

    private LlamadoUsuarioRest llamadoUsuarioRest;
    private static final String USUARIO_REGISTRADO = "Usuario registrado";
    private static final String SERVIDOR_APAGADO = "Fallo de conexion con el servidor";

    @Inject
    public RepositorioUsuarioImpl() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(UrlServicio.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        llamadoUsuarioRest = retrofit.create(LlamadoUsuarioRest.class);
    }

    @Override
    public MutableLiveData<RespuestaServicioPost> registrar(Usuario usuario) {
        final MutableLiveData<RespuestaServicioPost> resultado = new MutableLiveData<>();
        llamadoUsuarioRest.registrar(usuario).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                RespuestaServicioPost respuestaServicioPost;
                if (response.code() == CodigoEstadoRespuesta.OK) {
                    respuestaServicioPost = new RespuestaServicioPost(USUARIO_REGISTRADO, response.code(), true);
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
    public MutableLiveData<RespuestaServicioGet> buscar(Long cedula) {
        final MutableLiveData<RespuestaServicioGet> resultado = new MutableLiveData<>();
        llamadoUsuarioRest.buscar(cedula).enqueue(new Callback<UsuarioDTO>() {
            @Override
            public void onResponse(Call<UsuarioDTO> call, Response<UsuarioDTO> response) {
                if (response.body() != null) {
                    Usuario usuario = new Usuario(response.body().getCedula(), response.body().getNombres(),
                            response.body().getApellidos(), response.body().getFechaNacimiento());
                    RespuestaServicioGet respuestaServicioGet = new RespuestaServicioGet(usuario, response.code(), true);
                    resultado.setValue(respuestaServicioGet);
                }
            }

            @Override
            public void onFailure(Call<UsuarioDTO> call, Throwable t) {
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
