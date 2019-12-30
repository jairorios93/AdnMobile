package com.example.alquilervehiculosfront.vistas;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.alquilervehiculosfront.R;
import com.example.alquilervehiculosfront.servicios.dto.UsuarioDTO;
import com.example.alquilervehiculosfront.dominio.modelo.Usuario;
import com.example.alquilervehiculosfront.servicios.Endpoint;
import com.example.alquilervehiculosfront.servicios.ServicioUsuario;
import com.example.alquilervehiculosfront.servicios.StatusResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdministrarUsuarioFragment extends Fragment {

    private EditText cedula;
    private EditText nombres;
    private EditText apellidos;
    private EditText fechaNacimiento;
    private Button guardar;
    private Button buscar;
    private ProgressDialog progressDialog;
    private AlertDialog.Builder alertDialog;

    private ServicioUsuario servicioUsuario;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_administrar_usuario, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findElementViewById(view);
        iniciarComponentes();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Endpoint.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        servicioUsuario = retrofit.create(ServicioUsuario.class);

        registrar();
        buscar();
    }

    private void findElementViewById(View view) {
        cedula = view.findViewById(R.id.cedula);
        nombres = view.findViewById(R.id.nombres);
        apellidos = view.findViewById(R.id.apellidos);
        fechaNacimiento = view.findViewById(R.id.fechaNacimiento);
        guardar = view.findViewById(R.id.guardar);
        buscar = view.findViewById(R.id.buscar);
    }

    private void iniciarComponentes() {
        fechaNacimiento.setFocusable(false);
        fechaNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFecha(fechaNacimiento);
            }
        });

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);

        alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setCancelable(true);
        alertDialog.setPositiveButton(
                getResources().getString(R.string.mensajes_generales_aceptar),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
    }

    private void registrar() {
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombresUsuario = nombres.getText().toString();
                String apellidosUsuario = apellidos.getText().toString();
                String fechaNacimientoUsuario = fechaNacimiento.getText().toString();

                if (cedula.getText().toString().equals("") || nombresUsuario.equals("") || apellidosUsuario.equals("") || fechaNacimientoUsuario.equals("")) {
                    alertDialog.setMessage(getResources().getString(R.string.mensajes_generales_alert_dialog_mensaje_campos_requeridos));
                    alertDialog.setTitle(getResources().getString(R.string.mensajes_generales_alert_dialog_titulo_campos_requeridos));
                    alertDialog.create();
                    alertDialog.show();
                } else {
                    progressDialog.setMessage(getResources().getString(R.string.mensajes_generales_registrando));
                    progressDialog.show();

                    Long cedulaUsuario = Long.valueOf(cedula.getText().toString());
                    Usuario usuario = new Usuario(cedulaUsuario, nombresUsuario, apellidosUsuario, fechaNacimientoUsuario);

                    servicioUsuario.registrar(usuario).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            progressDialog.dismiss();
                            if (response.code() == StatusResponse.OK) {
                                limpiarCamposPantalla();
                                Toast.makeText(getContext(), getResources().getString(R.string.fragment_administrar_usuario_registrado), Toast.LENGTH_SHORT).show();
                            } else {
                                errorRespuesta(response.errorBody());
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            progressDialog.dismiss();
                            t.printStackTrace();
                        }
                    });
                }
            }
        });
    }

    private void buscar() {
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cedula.getText().toString().equals("")) {
                    alertDialog.setMessage(getResources().getString(R.string.mensajes_generales_alert_dialog_mensaje_cedula_requerida));
                    alertDialog.setTitle(getResources().getString(R.string.mensajes_generales_alert_dialog_titulo_campos_requeridos));
                    alertDialog.create();
                    alertDialog.show();
                } else {

                    progressDialog.setMessage(getResources().getString(R.string.mensajes_generales_buscando));
                    progressDialog.show();

                    Long cedulaUsuario = Long.valueOf(cedula.getText().toString());

                    servicioUsuario.buscar(cedulaUsuario).enqueue(new Callback<UsuarioDTO>() {
                        @Override
                        public void onResponse(Call<UsuarioDTO> call, Response<UsuarioDTO> response) {
                            progressDialog.dismiss();
                            if (response.body() != null) {
                                nombres.setText(response.body().getNombres());
                                apellidos.setText(response.body().getApellidos());
                                fechaNacimiento.setText(response.body().getFechaNacimiento());
                            } else {
                                Toast.makeText(getContext(), getResources().getString(R.string.fragment_administrar_usuario_no_encontrado), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<UsuarioDTO> call, Throwable t) {
                            progressDialog.dismiss();
                            Toast.makeText(getContext(), getResources().getString(R.string.fragment_administrar_usuario_no_encontrado), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    /**
     * llena el campo con la fecha seleccionada
     *
     * @param input el input a llenar
     */
    private void setFecha(final EditText input) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                String dia = String.valueOf(dayOfMonth);
                String mes = String.valueOf((monthOfYear + 1));
                if (dayOfMonth < 10) {
                    dia = "0" + dayOfMonth;
                }
                if ((monthOfYear + 1) < 10) {
                    mes = "0" + (monthOfYear + 1);
                }
                input.setText(year + "-" + mes + "-" + dia);
            }
        }, 0, 0, 2019);
        Calendar fechaHoy = new GregorianCalendar();
        datePickerDialog.updateDate(fechaHoy.get(Calendar.YEAR), fechaHoy.get(Calendar.MONTH), fechaHoy.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void errorRespuesta(ResponseBody errorBody) {
        try {
            JSONObject jsonObject = new JSONObject(errorBody.string());
            Toast.makeText(getContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    private void limpiarCamposPantalla() {
        cedula.setText("");
        nombres.setText("");
        apellidos.setText("");
        fechaNacimiento.setText("");
    }
}