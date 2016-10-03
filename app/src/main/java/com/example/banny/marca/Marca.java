package com.example.banny.marca;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Marca extends AppCompatActivity implements OnMapReadyCallback{
    private GoogleMap _mapa;

    private Spinner sp_ciudad;
    String[] datos = new String[]{"SELECCIONAR","LIMA","TRUJILLO","CHIMBOTE", "CUSCO"};
    private Ciudad[] Ciudades =
            new Ciudad[]{
                    new Ciudad("LIMA",-12.0463,-77.04280 ),
                    new Ciudad("TRUJILLO",-8.1117,-79.0287 ),
                    new Ciudad("CHIMBOTE",-9.0643,-78.59077 ),
                    new Ciudad("CUSCO",-13.5338,-71.9660)
                    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marca);

        sp_ciudad = (Spinner)findViewById(R.id.sp_ciudad);

        MapFragment mapFragment = (MapFragment)getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,datos);
        sp_ciudad.setAdapter(adaptador);

        sp_ciudad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String texto = sp_ciudad.getSelectedItem().toString();
                buscar(texto);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        _mapa = googleMap;
    }

    public void mover(Double latitud, Double longitud, String titulo){
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(latitud,longitud),10);
        _mapa.animateCamera(cameraUpdate);

        _mapa.addMarker(new MarkerOptions()
        .position(new LatLng(latitud,longitud))
        .title(titulo));
    }

    public void buscar(String ciudadBuscar){
        for(int i = 0 ; i<Ciudades.length; i++){
            if(ciudadBuscar == Ciudades[i].get_Nombre()){
                Double latitud = Ciudades[i].get_Lat();
                Double longitud = Ciudades[i].get_Lng();
                String titulo = Ciudades[i].get_Nombre();
                mover(latitud,longitud,titulo);
            }
            if(ciudadBuscar == "SELECCIONAR"){
                _mapa.clear();
                Toast.makeText(getApplicationContext(),"Se eliminaron los marcadores",Toast.LENGTH_SHORT).show();
            }
        }
    }

}

