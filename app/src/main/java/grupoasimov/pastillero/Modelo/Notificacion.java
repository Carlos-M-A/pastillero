package grupoasimov.pastillero.Modelo;

import com.orm.SugarRecord;


/**
 * Created by ferreri on 15/11/16.
 */

public class Notificacion extends SugarRecord {
    Alarma alarma;
    boolean notificado;

    public Notificacion() {
    }

    public Notificacion(Alarma alarma, boolean notificado) {
        this.alarma = alarma;
        this.notificado = notificado;
    }

    public Alarma getAlarma() {
        return alarma;
    }

    public void setAlarma(Alarma alarma) {
        this.alarma = alarma;
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
                ", notificado=" + notificado +
                '}';
    }
}
