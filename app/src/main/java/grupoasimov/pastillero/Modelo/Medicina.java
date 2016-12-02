package grupoasimov.pastillero.Modelo;


import com.orm.SugarRecord;

/**
 * Created by ferreri on 15/11/16.
 */

public class Medicina extends SugarRecord {
    private String nombre;
    private String descripcion;
    private int cantidadPorcion; // en miligramos
    private String urlImagen;

    public Medicina() {
    }

    public Medicina(String nombre, String descripcion, int cantidadPorcion, String urlImagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidadPorcion = cantidadPorcion;
        this.urlImagen = urlImagen;
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
}
