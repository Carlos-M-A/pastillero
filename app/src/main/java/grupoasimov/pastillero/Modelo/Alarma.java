package grupoasimov.pastillero.Modelo;

import com.orm.SugarRecord;

import java.util.Calendar;

/**
 * Created by ferreri on 15/11/16.
 */

public class Alarma extends SugarRecord{
    Medicina medicina;
    Calendar fecha;
    String nota;
    int cantidad; // En miligramos

    public Alarma() {
    }

    public Alarma(Medicina medicina, Calendar fecha, String nota, int cantidad) {
        this.medicina = medicina;
        this.fecha = fecha;
        this.nota = nota;
        this.cantidad = cantidad;
    }

    public Medicina getMedicina() {
        return medicina;
    }

    public void setMedicina(Medicina medicina) {
        this.medicina = medicina;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Alarma{" +
                "medicina=" + medicina +
                ", fecha=" + fecha +
                ", nota='" + nota + '\'' +
                ", cantidad=" + cantidad +
                '}';
    }
}
