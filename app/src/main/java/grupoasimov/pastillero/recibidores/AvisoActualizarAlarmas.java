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

public class AvisoActualizarAlarmas extends BroadcastReceiver {

    public static boolean programadoAMediaNoche = false;
    public AvisoActualizarAlarmas() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.d("Lo acabas de encender", "ENCENDIDOoooooooooooooooooooooooooooooooooooooooooooo");

        actualizarAlarmas(context);


        Calendar ahora = Calendar.getInstance();
        if(!programadoAMediaNoche || ((int)ahora.get(Calendar.MINUTE)==0 && (int)ahora.get(Calendar.HOUR_OF_DAY)==0
                                        && (int)ahora.get(Calendar.SECOND)==30))
            programaSiguienteActualizacionAlarmas(context);


    }

    public void actualizarAlarmas(Context context){
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        Log.d("ALARMA", "ENTRAMOS EN ACTUALIZAR ALARMAS");

        Intent intent3 = new Intent(context, AvisoDeAlarma.class);
        PendingIntent pendingIntent2 = PendingIntent.getBroadcast(
                context.getApplicationContext(), 0, intent3, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.cancel(pendingIntent2);

        List<Alarma> alarmas =Alarma.listAll(Alarma.class);


        for(Alarma alarma: alarmas){
            Calendar alarmaCalendarBORRAR = Calendar.getInstance();
            alarmaCalendarBORRAR.set(Calendar.HOUR_OF_DAY, alarma.getHora());
            alarmaCalendarBORRAR.set(Calendar.MINUTE, alarma.getMinuto());
            alarmaCalendarBORRAR.set(Calendar.SECOND, 0);
            alarmaCalendarBORRAR.set(Calendar.MILLISECOND, 0);
            Log.d("ALARMA", alarmaCalendarBORRAR.toString());
            if(alarma.debeSerActivada()) {

                Calendar alarmaCalendar = Calendar.getInstance();
                alarmaCalendar.set(Calendar.HOUR_OF_DAY, alarma.getHora());
                alarmaCalendar.set(Calendar.MINUTE, alarma.getMinuto());
                alarmaCalendar.set(Calendar.SECOND, 0);
                alarmaCalendar.set(Calendar.MILLISECOND, 0);
                Log.d("ALARMA - ACTIVADA", alarmaCalendar.toString());
                Intent intent2 = new Intent(context, AvisoDeAlarma.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(
                        context.getApplicationContext(), (int)alarmaCalendar.getTimeInMillis(), intent2, PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.set(AlarmManager.RTC_WAKEUP, alarmaCalendar.getTimeInMillis(), pendingIntent);
            }

        }

    }

    public void programaSiguienteActualizacionAlarmas(Context context){

        //Si guiente llamada a las 00:00:30 del proximo dia, como tarde
        Calendar alarmaCalendar = Calendar.getInstance();
        alarmaCalendar.add(Calendar.DAY_OF_YEAR, 1);
        alarmaCalendar.add(Calendar.HOUR_OF_DAY, -(alarmaCalendar.get(Calendar.HOUR_OF_DAY)));
        alarmaCalendar.add(Calendar.MINUTE, -(alarmaCalendar.get(Calendar.MINUTE)));
        if (alarmaCalendar.get(Calendar.SECOND)>30){
            alarmaCalendar.add(Calendar.SECOND, -((alarmaCalendar.get(Calendar.SECOND)-30)));
        }else{
            alarmaCalendar.add(Calendar.SECOND, (30 - (alarmaCalendar.get(Calendar.SECOND))));
        }
        alarmaCalendar.add(Calendar.MILLISECOND, -(alarmaCalendar.get(Calendar.MILLISECOND)));
        Log.d("SIGUIENTE ACT", alarmaCalendar.toString());


        //Programamos la llamada para la siguiente actualizacion


        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        Intent intent2 = new Intent(context, AvisoActualizarAlarmas.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context.getApplicationContext(), (int)alarmaCalendar.getTimeInMillis(), intent2, PendingIntent.FLAG_UPDATE_CURRENT);

            alarmManager.set(AlarmManager.RTC_WAKEUP, alarmaCalendar.getTimeInMillis(), pendingIntent);

        programadoAMediaNoche = true;
    }
}
