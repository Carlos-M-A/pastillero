package grupoasimov.pastillero.utiles;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import grupoasimov.pastillero.recibidores.AvisoActualizarAlarmas;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by ferreri on 21/12/16.
 */

public class GestorAlarmas {

    public GestorAlarmas(){

        }

    public static void activarAlarma(Context context){
        //Programamos la llamada para la siguiente actualizacion
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        Intent intent2 = new Intent(context, AvisoActualizarAlarmas.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context.getApplicationContext(), 234324243, intent2, 0);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 2*1000, pendingIntent);
    }
}
