package grupoasimov.pastillero.Modelo;

import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by ferreri on 15/11/16.
 */

public class Alarma extends SugarRecord{

    private Medicina medicina;

    private int hora;
    private int minuto;

    private int cadaXDias;
    private boolean lunes;
    private boolean martes;
    private boolean miercoles;
    private boolean jueves;
    private boolean viernes;
    private boolean sabado;
    private boolean domingo;

    private String nota;
    private int cantidadToma; // En miligramos

    public Alarma() {
    }

    public Alarma(Medicina medicina) {
        this.medicina = medicina;
        nota = "";
        cadaXDias = 0;
    }

    public Medicina getMedicina() {
        return medicina;
    }

    public void setMedicina(Medicina medicina) {
        this.medicina = medicina;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public void setMinuto(int minuto) {
        this.minuto = minuto;
    }

    public int getCadaXDias() {
        return cadaXDias;
    }

    public void setCadaXDias(int cadaXDias) {
        this.cadaXDias = cadaXDias;
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
                ", hora=" + hora +
                ", minuto=" + minuto +
                ", cadaXDias=" + cadaXDias +
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

    public String getStringHora(){
        String horaString = "";
        horaString = horaString + Integer.toString(hora) + ":";
        if (minuto<10)
            horaString = horaString + "0";
        horaString = horaString + Integer.toString(minuto);
        return horaString;
    }

    public String getStringDiasCorto(){
        String cadenaDias = "";
        if(cadaXDias==0) {
            if (isLunes()) cadenaDias = cadenaDias + " Lu";
            if (isMartes()) cadenaDias = cadenaDias + " Ma";
            if (isMiercoles()) cadenaDias = cadenaDias + " Mi";
            if (isJueves()) cadenaDias = cadenaDias + " Ju";
            if (isViernes()) cadenaDias = cadenaDias + " Vi";
            if (isSabado()) cadenaDias = cadenaDias + " Sa";
            if (isDomingo()) cadenaDias = cadenaDias + " Do";
        } else{
            cadenaDias = "Cada " + Integer.toString(cadaXDias) + " dias";
        }
        return cadenaDias;
    }

    public String getStringDiasLargo(){
        String cadenaDias = "";
        if(cadaXDias==0) {
            if (isLunes()) cadenaDias = cadenaDias + "Lunes";
            if (isMartes()) cadenaDias = cadenaDias + " Martes";
            if (isMiercoles()) cadenaDias = cadenaDias + " Miercoles";
            if (isJueves()) cadenaDias = cadenaDias + " Jueves";
            if (isViernes()) cadenaDias = cadenaDias + " Viernes";
            if (isSabado()) cadenaDias = cadenaDias + " Sabado";
            if (isDomingo()) cadenaDias = cadenaDias + " Domingo";
        } else{
            cadenaDias = "Cada " + Integer.toString(cadaXDias) + " dias";
        }
        return cadenaDias;
    }

    public String getStringPorcion(){
        String cadenaPorcion = Integer.toString(cantidadToma) + " mg";
        return cadenaPorcion;
    }
}
