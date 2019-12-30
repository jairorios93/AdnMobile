package com.example.alquilervehiculosfront.vistas;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.alquilervehiculosfront.R;
import com.example.alquilervehiculosfront.servicios.dto.VehiculoDTO;
import com.example.alquilervehiculosfront.dominio.modelo.Vehiculo;
import com.example.alquilervehiculosfront.servicios.Endpoint;
import com.example.alquilervehiculosfront.servicios.ServicioVehiculo;
import com.example.alquilervehiculosfront.servicios.StatusResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdministrarVehiculoFragment extends Fragment {

    private EditText placa;
    private EditText modelo;
    private EditText marca;
    private EditText color;
    private EditText precio;
    private Button guardar;
    private Button buscar;
    private ProgressDialog progressDialog;
    private AlertDialog.Builder alertDialog;

    private ServicioVehiculo servicioVehiculo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_administrar_vehiculo, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findElementViewById(view);
        iniciarComponentes();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Endpoint.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        servicioVehiculo = retrofit.create(ServicioVehiculo.class);

        registrar();
        buscar();
    }

    private void findElementViewById(View view) {
        placa = view.findViewById(R.id.placa);
        modelo = view.findViewById(R.id.modelo);
        marca = view.findViewById(R.id.marca);
        color = view.findViewById(R.id.color);
        precio = view.findViewById(R.id.precio);
        guardar = view.findViewById(R.id.guardar);
        buscar = view.findViewById(R.id.buscar);
    }

    private void iniciarComponentes() {
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
                String placaVehiculo = placa.getText().toString();
                String modeloVehiculo = modelo.getText().toString();
                String marcaVehiculo = marca.getText().toString();
                String colorVehiculo = color.getText().toString();

                if (precio.getText().toString().equals("") || placaVehiculo.equals("") || modeloVehiculo.equals("") || marcaVehiculo.equals("") || colorVehiculo.equals("")) {
                    alertDialog.setMessage(getResources().getString(R.string.mensajes_generales_alert_dialog_mensaje_campos_requeridos));
                    alertDialog.setTitle(getResources().getString(R.string.mensajes_generales_alert_dialog_titulo_campos_requeridos));
                    alertDialog.create();
                    alertDialog.show();
                } else {

                    progressDialog.setMessage(getResources().getString(R.string.mensajes_generales_registrando));
                    progressDialog.show();

                    double precioVehiculo = Double.parseDouble(precio.getText().toString());

                    Vehiculo vehiculo = new Vehiculo(placaVehiculo, modeloVehiculo, marcaVehiculo, colorVehiculo, precioVehiculo);

                    servicioVehiculo.registrar(vehiculo).enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            progressDialog.dismiss();
                            if (response.code() == StatusResponse.OK) {
                                limpiarCamposPantalla();
                                Toast.makeText(getContext(), getResources().getString(R.string.fragment_administrar_vehiculo_registrado), Toast.LENGTH_LONG).show();
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
                String placaVehiculo = placa.getText().toString();

                if (placaVehiculo.equals("")) {
                    alertDialog.setMessage(getResources().getString(R.string.mensajes_generales_alert_dialog_mensaje_placa_requerida));
                    alertDialog.setTitle(getResources().getString(R.string.mensajes_generales_alert_dialog_titulo_campos_requeridos));
                    alertDialog.create();
                    alertDialog.show();
                } else {

                    progressDialog.setMessage(getResources().getString(R.string.mensajes_generales_buscando));
                    progressDialog.show();

                    servicioVehiculo.buscar(placaVehiculo).enqueue(new Callback<VehiculoDTO>() {
                        @Override
                        public void onResponse(Call<VehiculoDTO> call, Response<VehiculoDTO> response) {
                            progressDialog.dismiss();
                            if (response.body() != null) {
                                modelo.setText(response.body().getModelo());
                                marca.setText(response.body().getMarca());
                                color.setText(response.body().getColor());
                                precio.setText(String.valueOf(response.body().getPrecio()));
                            } else {
                                Toast.makeText(getContext(), getResources().getString(R.string.fragment_administrar_vehiculo_no_encontrado), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<VehiculoDTO> call, Throwable t) {
                            progressDialog.dismiss();
                            Toast.makeText(getContext(), getResources().getString(R.string.fragment_administrar_vehiculo_no_encontrado), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
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
        placa.setText("");
        modelo.setText("");
        marca.setText("");
        color.setText("");
        precio.setText("");
    }
}