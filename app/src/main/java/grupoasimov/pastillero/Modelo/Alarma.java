package grupoasimov.pastillero.Modelo;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by ferreri on 15/11/16.
 */

public class Alarma extends SugarRecord{
    Medicina medicina;
    Calendar fechaInicio;
    Calendar fechaFin;
    Calendar horaAlarma;

    boolean lunes;
    boolean martes;
    boolean miercoles;
    boolean jueves;
    boolean viernes;
    boolean sabado;
    boolean domingo;

    String nota;
    int cantidadToma; // En miligramos

    public Alarma() {
    }

    public Alarma(Medicina medicina) {
        this.medicina = medicina;
        fechaFin = Calendar.getInstance();
        fechaInicio = Calendar.getInstance();
        horaAlarma = Calendar.getInstance();
        nota = "";
    }

    public Medicina getMedicina() {
        return medicina;
    }

    public void setMedicina(Medicina medicina) {
        this.medicina = medicina;
    }

    public Calendar getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Calendar fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Calendar getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Calendar fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Calendar getHoraAlarma() {
        return horaAlarma;
    }

    public void setHoraAlarma(Calendar horaAlarma) {
        this.horaAlarma = horaAlarma;
    }

    public boolean isLunes() {
        return lunes;
    }

    public void setLunes(boolean lunes) {
        this.lunes = lunes;
    }

    public boolean isMartes() {
        return martes;
    }

    public void setMartes(boolean martes) {
        this.martes = martes;
    }

    public boolean isMiercoles() {
        return miercoles;
    }

    public void setMiercoles(boolean miercoles) {
        this.miercoles = miercoles;
    }

    public boolean isJueves() {
        return jueves;
    }

    public void setJueves(boolean jueves) {
        this.jueves = jueves;
    }

    public boolean isViernes() {
        return viernes;
    }

    public void setViernes(boolean viernes) {
        this.viernes = viernes;
    }

    public boolean isSabado() {
        return sabado;
    }

    public void setSabado(boolean sabado) {
        this.sabado = sabado;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public boolean isDomingo() {
        return domingo;
    }

    public void setDomingo(boolean domingo) {
        this.domingo = domingo;
    }

    public int getCantidadToma() {
        return cantidadToma;
    }

    public void setCantidadToma(int cantidadToma) {
        this.cantidadToma = cantidadToma;
    }

    @Override
    public String toString() {
        return "Alarma{" +
                "medicina=" + medicina +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", horaAlarma=" + horaAlarma +
                ", lunes=" + lunes +
                ", martes=" + martes +
                ", miercoles=" + miercoles +
                ", jueves=" + jueves +
                ", viernes=" + viernes +
                ", sabado=" + sabado +
                ", domingo=" + domingo +
                ", nota='" + nota + '\'' +
                ", cantidadToma=" + cantidadToma +
                '}';
    }
}
