package grupoasimov.pastillero.modelo;

import android.content.Context;

import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.Calendar;

import grupoasimov.pastillero.R;

/**
 * Created by ferreri on 15/11/16.
 */

public class Alarma extends SugarRecord implements Serializable{

    private Medicina medicina;

    private int hora;
    private int minuto;

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

    public String getStringDiasCorto(Context context){

        String cadenaDias = "";
            if (isLunes()) cadenaDias = cadenaDias + " " + context.getString(R.string.lunes_corto);
            if (isMartes()) cadenaDias = cadenaDias + " " + context.getString(R.string.martes_corto);
            if (isMiercoles()) cadenaDias = cadenaDias + " " + context.getString(R.string.miercoles_corto);
            if (isJueves()) cadenaDias = cadenaDias + " " + context.getString(R.string.jueves_corto);
            if (isViernes()) cadenaDias = cadenaDias + " " + context.getString(R.string.viernes_corto);
            if (isSabado()) cadenaDias = cadenaDias + " " + context.getString(R.string.sabado_corto);
            if (isDomingo()) cadenaDias = cadenaDias + " " + context.getString(R.string.domingo_corto);

        return cadenaDias;
    }

    public String getStringDiasLargo(Context context){
        String cadenaDias = "";
            if (isLunes()) cadenaDias = cadenaDias + " " + context.getString(R.string.lunes_largo);
            if (isMartes()) cadenaDias = cadenaDias + " " + context.getString(R.string.martes_largo);
            if (isMiercoles()) cadenaDias = cadenaDias + " " + context.getString(R.string.miercoles_largo);
            if (isJueves()) cadenaDias = cadenaDias + " " + context.getString(R.string.jueves_largo);
            if (isViernes()) cadenaDias = cadenaDias + " " + context.getString(R.string.viernes_largo);
            if (isSabado()) cadenaDias = cadenaDias + " " + context.getString(R.string.sabado_largo);
            if (isDomingo()) cadenaDias = cadenaDias + " " + context.getString(R.string.domingo_largo);

        return cadenaDias;
    }

    public String getStringPorcion(Context context){
        return Integer.toString(cantidadToma) + context.getString(R.string.miligramos_corto);
    }

    /**
     * Devuelve si la alarma es hoy
     * @return
     */
    public boolean esHoy(){
        Calendar ahora = Calendar.getInstance();
        boolean diaActivo = false;
        switch (ahora.get(Calendar.DAY_OF_WEEK)){
            case Calendar.MONDAY:
                diaActivo = isLunes();
                break;
            case Calendar.TUESDAY:
                diaActivo = isMartes();
                break;
            case Calendar.WEDNESDAY:
                diaActivo = isMiercoles();
                break;
            case Calendar.THURSDAY:
                diaActivo = isJueves();
                break;
            case Calendar.FRIDAY:
                diaActivo = isViernes();
                break;
            case Calendar.SATURDAY:
                diaActivo = isSabado();
                break;
            case Calendar.SUNDAY:
                diaActivo = isDomingo();
                break;
        }
        return diaActivo;
    }
    /**
     * Devuelve si debe ser activada o no en el dia de hoy
     * @return
     */
    public boolean debeSerActivada(){
        boolean diaActivo = esHoy(); //Si la alarma debe ser activada este dia
        boolean activoHoy; // Si en el dia de hoy la alarma sigue activa

        Calendar ahora = Calendar.getInstance();

        Calendar alarma = Calendar.getInstance();
        alarma.set(Calendar.HOUR_OF_DAY, getHora());
        alarma.set(Calendar.MINUTE, getMinuto());
        alarma.set(Calendar.SECOND, 0);
        alarma.set(Calendar.MILLISECOND, 0);

        activoHoy = alarma.after(ahora);

        return (diaActivo && activoHoy);

    }
}
