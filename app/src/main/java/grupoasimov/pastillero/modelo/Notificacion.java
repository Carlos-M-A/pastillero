package grupoasimov.pastillero.modelo;

import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.Calendar;


/**
 * Notificaciones guardadas que deben ser enviadas al Cuidador.
 * @author Adrián Serrano
 * @author Carlos Martín
 * @author María Varela
 */

public class Notificacion extends SugarRecord implements Serializable{
    private Alarma alarma;
    private Calendar fecha;
    private boolean notificado;

    public Notificacion() {
    }


    public Alarma getAlarma() {
        return alarma;
    }

    public void setAlarma(Alarma alarma) {
        this.alarma = alarma;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public boolean isNotificado() {
        return notificado;
    }

    public void setNotificado(boolean notificado) {
        this.notificado = notificado;
    }

    @Override
    public String toString() {
        return "Notificacion{" +
                "alarma=" + alarma +
                ", fecha=" + fecha +
                ", notificado=" + notificado +
                '}';
    }
}
