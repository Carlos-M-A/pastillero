package grupoasimov.pastillero.modelo;

import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.Calendar;


/**
 * Created by ferreri on 15/11/16.
 */

public class Notificacion extends SugarRecord implements Serializable{
    private Alarma alarma;
    private Calendar fecha;
    private boolean notificado;

    public Notificacion() {
    }

    public Notificacion(Alarma alarma, Calendar fecha) {
        this.alarma = alarma;
        this.fecha = fecha;
        this.notificado = false;
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
