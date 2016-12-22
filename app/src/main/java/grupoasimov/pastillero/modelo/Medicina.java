package grupoasimov.pastillero.modelo;


import android.content.Context;

import com.orm.SugarRecord;

import java.io.Serializable;

import grupoasimov.pastillero.R;

/**
 *
 */

public class Medicina extends SugarRecord implements Serializable {
    private String nombre;
    private String descripcion;
    private int cantidadPorcion; // en miligramos
    private String urlImagen;

    public Medicina() {
        nombre = "";
        descripcion = "";
        cantidadPorcion = 0;
        urlImagen = "";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidadPorcion() {
        return cantidadPorcion;
    }

    public void setCantidadPorcion(int cantidadPorcion) {
        this.cantidadPorcion = cantidadPorcion;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    @Override
    public String toString() {
        return "Medicina{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", cantidadPorcion=" + cantidadPorcion +
                ", urlImagen='" + urlImagen + '\'' +
                '}';
    }

    public String getStringPorcion(Context context){
        return Integer.toString(cantidadPorcion) + context.getString(R.string.miligramos_corto);
    }
}
