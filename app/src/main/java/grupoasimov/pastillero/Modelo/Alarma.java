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
}
