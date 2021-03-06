package com.example.alquilervehiculosfront.presentacion.actividades;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.example.alquilervehiculosfront.R;
import com.example.alquilervehiculosfront.dominio.repositorio.RepositorioUsuario;
import com.example.alquilervehiculosfront.presentacion.fragmentos.AdministrarAlquilerFragment;
import com.example.alquilervehiculosfront.presentacion.fragmentos.AdministrarUsuarioFragment;
import com.example.alquilervehiculosfront.presentacion.fragmentos.AdministrarVehiculoFragment;
import com.example.alquilervehiculosfront.presentacion.fragmentos.PrincipalFragment;
import com.example.alquilervehiculosfront.presentacion.fragmentosutil.EtiquetasFragment;
import com.google.android.material.navigation.NavigationView;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Inject
    public RepositorioUsuario repositorioUsuario;

    private FragmentManager fragmentManager;
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findElementViewById();

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, new PrincipalFragment(), "PrincipalFragment").commit();
        getSupportActionBar().setTitle(getResources().getString(R.string.menu_principal));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.nav_menu_principal:
                fragmentManager.beginTransaction().replace(R.id.container, new PrincipalFragment(), EtiquetasFragment.PRINCIPAL_FRAGMENT).commit();
                getSupportActionBar().setTitle(getResources().getString(R.string.menu_principal));
                break;

            case R.id.nav_administrar_vehiculos:
                fragmentManager.beginTransaction().replace(R.id.container, new AdministrarVehiculoFragment(), EtiquetasFragment.ADMINISTRAR_VEHICULO_FRAGMENT).commit();
                getSupportActionBar().setTitle(getResources().getString(R.string.administrar_vehiculos));
                break;

            case R.id.nav_administrar_usuarios:
                fragmentManager.beginTransaction().replace(R.id.container, new AdministrarUsuarioFragment(), EtiquetasFragment.ADMINISTRAR_USUARIO_FRAGMENT).commit();
                getSupportActionBar().setTitle(getResources().getString(R.string.administrar_usuarios));
                break;

            case R.id.nav_administrar_alquileres:
                fragmentManager.beginTransaction().replace(R.id.container, new AdministrarAlquilerFragment(), EtiquetasFragment.ADMINISTRAR_ALQUILER_FRAGMENT).commit();
                getSupportActionBar().setTitle(getResources().getString(R.string.administrar_alquileres));
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void findElementViewById() {
        toolbar = findViewById(R.id.toolbar);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
    }
}