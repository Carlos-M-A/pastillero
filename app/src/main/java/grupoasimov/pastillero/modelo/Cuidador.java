package grupoasimov.pastillero.modelo;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Cuidador que quiere ser notificado por los sucesos del usuario(paciente) con la aplicación.
 * @author Adrián Serrano
 * @author Carlos Martín
 * @author María Varela
 */
public class Cuidador extends SugarRecord implements Serializable{

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
