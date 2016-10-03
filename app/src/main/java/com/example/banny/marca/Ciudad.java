package com.example.banny.marca;

/**
 * Created by Banny on 2/10/2016.
 */

public class Ciudad {
    private String _Nombre;
    private double _Lat;
    private double _Lng;

    public Ciudad( String nombre, double _Lat, double _Lng) {

        this._Nombre = nombre;
        this._Lat = _Lat;
        this._Lng = _Lng;
    }

    public String get_Nombre() {
        return _Nombre;
    }

    public void set_Nombre(String _Nombre) {
        this._Nombre = _Nombre;
    }

    public double get_Lat() {
        return _Lat;
    }

    public void set_Lat(double _Lat) {
        this._Lat = _Lat;
    }

    public double get_Lng() {
        return _Lng;
    }

    public void set_Lng(double _Lng) {
        this._Lng = _Lng;
    }
}
