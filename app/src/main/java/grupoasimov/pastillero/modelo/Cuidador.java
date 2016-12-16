package grupoasimov.pastillero.modelo;

import com.orm.SugarRecord;

/**
 * Created by ferreri on 1/12/16.
 */

public class Cuidador extends SugarRecord {

    private String nombre;
    private String apellidos;
    private String direccion;
    private String telefono;
    private String email;

    public Cuidador() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
