package com.example.alquilervehiculosfront.presentacion.fragmentos;

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
import com.example.alquilervehiculosfront.datos.dto.VehiculoDTO;
import com.example.alquilervehiculosfront.dominio.modelo.Vehiculo;
import com.example.alquilervehiculosfront.dominio.servicios.ServicioVehiculoDominio;

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

    private ServicioVehiculoDominio servicioVehiculo;

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

        servicioVehiculo = new ServicioVehiculoDominio();

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

                    servicioVehiculo.registrar(vehiculo);
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

                    servicioVehiculo.buscar(placaVehiculo);
                }
            }
        });
    }

    private void limpiarCamposPantalla() {
        placa.setText("");
        modelo.setText("");
        marca.setText("");
        color.setText("");
        precio.setText("");
    }

    public void dismissDialog() {
        progressDialog.dismiss();
    }

    public void resultadoRegistrar() {
        limpiarCamposPantalla();
        Toast.makeText(getContext(), getResources().getString(R.string.fragment_administrar_vehiculo_registrado), Toast.LENGTH_LONG).show();
    }

    public void resultadoBuscar(VehiculoDTO vehiculoDTO) {
        modelo.setText(vehiculoDTO.getModelo());
        marca.setText(vehiculoDTO.getMarca());
        color.setText(vehiculoDTO.getColor());
        precio.setText(String.valueOf(vehiculoDTO.getPrecio()));
    }

    public void mensajeError(String mensaje) {
        Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
    }
}