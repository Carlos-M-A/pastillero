package grupoasimov.pastillero.Modelo;

import com.orm.SugarRecord;

/**
 * Created by ferreri on 1/12/16.
 */

public class Paciente extends SugarRecord {

    private String nombre;
    private String apellidos;
    private int edad;
    private String direccion;
    private String numeroDeTelefono;
    private String email;

    public Paciente(){
        /*email = "";
        nombre = "";
        apellidos = "";
        direccion = "";
        numeroDeTelefono = "";*/
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNumeroDeTelefono() {
        return numeroDeTelefono;
    }

    public void setNumeroDeTelefono(String numeroDeTelefono) {
        this.numeroDeTelefono = numeroDeTelefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
