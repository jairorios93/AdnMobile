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
import com.example.alquilervehiculosfront.aplicacion.dto.UsuarioDTO;
import com.example.alquilervehiculosfront.aplicacion.dto.VehiculoDTO;
import com.example.alquilervehiculosfront.dominio.modelo.AlquilarVehiculo;
import com.example.alquilervehiculosfront.dominio.modelo.Usuario;
import com.example.alquilervehiculosfront.dominio.modelo.Vehiculo;
import com.example.alquilervehiculosfront.aplicacion.servicios.ServicioAlquilerApplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class AdministrarAlquilerFragment extends Fragment {

    private EditText cedula;
    private Button buscarUsuario;
    private EditText placa;
    private Button buscarVehiculo;
    private EditText fechaInicio;
    private EditText fechaFin;
    private EditText valor;
    private Button alquilar;
    private Button devolver;
    private ProgressDialog progressDialog;
    private AlertDialog.Builder alertDialog;

    private Usuario usuario;
    private Vehiculo vehiculo;

    private ServicioAlquilerApplication servicioAlquiler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_administrar_alquiler, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findElementViewById(view);
        iniciarComponentes();

        servicioAlquiler = new ServicioAlquilerApplication();

        buscarUsuario();
        buscarVehiculo();
        alquilar();
        devolver();
    }

    private void findElementViewById(View view) {
        cedula = view.findViewById(R.id.cedula);
        buscarUsuario = view.findViewById(R.id.buscarUsuario);
        placa = view.findViewById(R.id.placa);
        buscarVehiculo = view.findViewById(R.id.buscarVehiculo);
        fechaInicio = view.findViewById(R.id.fechaInicio);
        fechaFin = view.findViewById(R.id.fechaFin);
        valor = view.findViewById(R.id.valor);
        alquilar = view.findViewById(R.id.alquilar);
        devolver = view.findViewById(R.id.devolver);
    }

    private void iniciarComponentes() {
        fechaInicio.setFocusable(false);
        fechaFin.setFocusable(false);

        fechaInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFechaInicio(fechaInicio);
            }
        });
        fechaFin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setFechaFin(fechaFin);
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

    private void buscarUsuario() {
        buscarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cedula.getText().toString().equals("")) {
                    mostarAlert(getResources().getString(R.string.mensajes_generales_alert_dialog_mensaje_cedula_requerida), getResources().getString(R.string.mensajes_generales_alert_dialog_titulo_campos_requeridos));
                } else {

                    progressDialog.setMessage(getResources().getString(R.string.mensajes_generales_buscando));
                    progressDialog.show();

                    Long cedulaUsuario = Long.valueOf(cedula.getText().toString());

                    servicioAlquiler.buscar(cedulaUsuario);
                }
            }
        });
    }

    private void buscarVehiculo() {
        buscarVehiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String placaVehiculo = placa.getText().toString();

                if (placaVehiculo.equals("")) {
                    mostarAlert(getResources().getString(R.string.mensajes_generales_alert_dialog_mensaje_placa_requerida), getResources().getString(R.string.mensajes_generales_alert_dialog_titulo_campos_requeridos));
                } else {

                    progressDialog.setMessage(getResources().getString(R.string.mensajes_generales_buscando));
                    progressDialog.show();

                    servicioAlquiler.buscar(placaVehiculo);
                }
            }
        });
    }

    private void alquilar() {
        alquilar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fechaInicioAlquiler = fechaInicio.getText().toString();
                String fechaFinAlquiler = fechaFin.getText().toString();

                if (fechaInicioAlquiler.equals("") || fechaFinAlquiler.equals("")) {
                    mostarAlert(getResources().getString(R.string.mensajes_generales_alert_dialog_mensaje_campos_requeridos), getResources().getString(R.string.mensajes_generales_alert_dialog_titulo_campos_requeridos));
                } else if (usuario == null) {
                    mostarAlert(getResources().getString(R.string.mensajes_generales_alert_dialog_mensaje_usuario_requerido), getResources().getString(R.string.mensajes_generales_alert_dialog_titulo_campos_requeridos));
                } else if (vehiculo == null) {
                    mostarAlert(getResources().getString(R.string.mensajes_generales_alert_dialog_mensaje_vehiculo_requerido), getResources().getString(R.string.mensajes_generales_alert_dialog_titulo_campos_requeridos));
                } else {

                    progressDialog.setMessage(getResources().getString(R.string.fragment_administrar_alquiler_alquilando));
                    progressDialog.show();

                    double valorAlquiler = Double.parseDouble(valor.getText().toString());
                    AlquilarVehiculo alquilarVehiculo = new AlquilarVehiculo(usuario, vehiculo, fechaInicioAlquiler, fechaFinAlquiler, true, valorAlquiler);

                    servicioAlquiler.alquilar(alquilarVehiculo);
                }
            }
        });
    }

    private void devolver() {
        devolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String placaVehiculo = placa.getText().toString();

                if (placaVehiculo.equals("")) {
                    mostarAlert(getResources().getString(R.string.mensajes_generales_alert_dialog_mensaje_placa_requerida), getResources().getString(R.string.mensajes_generales_alert_dialog_titulo_campos_requeridos));
                } else {
                    progressDialog.setMessage(getResources().getString(R.string.fragment_administrar_alquiler_devolviendo));
                    progressDialog.show();

                    servicioAlquiler.devolver(placaVehiculo);
                }
            }
        });
    }

    /**
     * llena el campo con la fecha seleccionada
     *
     * @param input el input a llenar
     */
    private void setFechaInicio(final EditText input) {
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
                fechaFin.setText("");
                input.setText(year + "-" + mes + "-" + dia);
            }
        }, 0, 0, 2019);
        Calendar fechaHoy = new GregorianCalendar();
        datePickerDialog.updateDate(fechaHoy.get(Calendar.YEAR), fechaHoy.get(Calendar.MONTH), fechaHoy.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(Calendar.getInstance().getTimeInMillis());
        datePickerDialog.show();
    }

    /**
     * llena el campo con la fecha seleccionada
     *
     * @param input el input a llenar
     */
    private void setFechaFin(final EditText input) {
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
                if (!fechaInicio.getText().toString().equals("")) {
                    input.setText(year + "-" + mes + "-" + dia);
                } else {
                    Toast.makeText(getContext(), getResources().getString(R.string.fragment_administrar_alquiler_seleccione_fecha_inicio), Toast.LENGTH_SHORT).show();
                }
            }
        }, 0, 0, 2019);
        Calendar fechaHoy = new GregorianCalendar();
        datePickerDialog.updateDate(fechaHoy.get(Calendar.YEAR), fechaHoy.get(Calendar.MONTH), fechaHoy.get(Calendar.DAY_OF_MONTH));
        if (!fechaInicio.getText().toString().equals("")) {
            Date date = null;
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(fechaInicio.getText().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            datePickerDialog.getDatePicker().setMinDate(date.getTime());
        }
        datePickerDialog.show();
    }

    private void limpiarCamposPantalla() {
        placa.setText("");
        cedula.setText("");
        fechaInicio.setText("");
        fechaFin.setText("");
        valor.setText("");
        usuario = null;
        vehiculo = null;
    }

    private void mostarAlert(String mensaje, String titulo) {
        alertDialog.setMessage(mensaje);
        alertDialog.setTitle(titulo);
        alertDialog.create();
        alertDialog.show();
    }

    public void dismissDialog() {
        progressDialog.dismiss();
    }

    public void resultadoBuscar(UsuarioDTO usuarioDTO) {
        usuario = new Usuario(usuarioDTO.getCedula(), usuarioDTO.getNombres(),
                usuarioDTO.getApellidos(), usuarioDTO.getFechaNacimiento());
    }

    public void resultadoBuscar(VehiculoDTO vehiculoDTO) {
        vehiculo = new Vehiculo(vehiculoDTO.getPlaca(), vehiculoDTO.getModelo(),
                vehiculoDTO.getMarca(), vehiculoDTO.getColor(), vehiculoDTO.getPrecio());
        valor.setText(String.valueOf(vehiculo.getPrecio()));
    }

    public void resultadoAlquilar() {
        limpiarCamposPantalla();
        Toast.makeText(getContext(), getResources().getString(R.string.fragment_administrar_alquiler_alquilado), Toast.LENGTH_SHORT).show();
    }

    public void resultadoDevolver() {
        limpiarCamposPantalla();
        Toast.makeText(getContext(), getResources().getString(R.string.fragment_administrar_alquiler_devuelto), Toast.LENGTH_SHORT).show();
    }

    public void mensajeError(String mensaje) {
        Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
    }
}