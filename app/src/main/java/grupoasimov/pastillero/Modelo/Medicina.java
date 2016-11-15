package grupoasimov.pastillero.Modelo;


import com.orm.SugarRecord;

/**
 * Created by ferreri on 15/11/16.
 */

public class Medicina extends SugarRecord {
    String nombre;
    String descripcion;
    int cantidadPorcion; // en miligramos
    String urlImagen;

}
