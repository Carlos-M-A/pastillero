package grupoasimov.pastillero.recibidores;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;
import java.util.List;

import grupoasimov.pastillero.modelo.Alarma;

import static android.content.Context.ALARM_SERVICE;

public class EncendidoDelTelefono extends BroadcastReceiver {
    public EncendidoDelTelefono() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.d("Lo acabas de encender", "ENCENDIDOoooooooooooooooooooooooooooooooooooooooooooo");

        actualizarAlarmas(context);

        programaSiguienteActualizacionAlarmas(context);


    }

    public void actualizarAlarmas(Context context){
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);


        Intent intent2 = new Intent(context, AvisoDeAlarma.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context.getApplicationContext(), 234324243, intent2, 0);

        alarmManager.cancel(pendingIntent);

        List<Alarma> alarmas =Alarma.listAll(Alarma.class);

        for(Alarma alarma: alarmas){

        }
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                + (15 * 1000), pendingIntent);
    }

    public void programaSiguienteActualizacionAlarmas(Context context){
        // Para obtener las 00:00 del dia siguiente
        Calendar calendar = Calendar.getInstance();
        int minutosHastaFinDia = (24*60) - ((calendar.get(Calendar.HOUR_OF_DAY) * 60) + calendar.get(Calendar.MINUTE));
        calendar.setTimeInMillis(calendar.getTimeInMillis() + minutosHastaFinDia*60*1000);

        //Programamos la alarma
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        Intent intent2 = new Intent(context, EncendidoDelTelefono.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context.getApplicationContext(), 234324243, intent2, 0);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }
}
