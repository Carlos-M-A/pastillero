package grupoasimov.pastillero.recibidores;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import java.util.ArrayList;
import java.util.Calendar;

import grupoasimov.pastillero.R;
import grupoasimov.pastillero.controladores.AlarmaDeMedicinas;
import grupoasimov.pastillero.modelo.Alarma;

import static android.content.Context.NOTIFICATION_SERVICE;

public class AvisoDeAlarma extends BroadcastReceiver {
    ArrayList<Alarma> alarmas;
    Calendar ahora;

    public AvisoDeAlarma() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        ahora = Calendar.getInstance();

        alarmas = (ArrayList<Alarma>) Alarma.find(Alarma.class, "hora = ? AND minuto = ?", Integer.toString(ahora.get(Calendar.HOUR_OF_DAY))
                , Integer.toString(ahora.get(Calendar.MINUTE)));
        for (Alarma alarma : alarmas) {
            if (!alarma.esHoy())
                alarmas.remove(alarma);
        }

        creaNotificacion(context);

    }

    public void creaNotificacion(Context context){
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setContentTitle(context.getString(R.string.debes_tomar) + " " + alarmas.size() + " " + context.getString(R.string.medicinas));
        String text = "";
        for (Alarma alarma : alarmas) {
            text = text + alarma.getMedicina().getNombre() + ". ";
        }
        mBuilder.setContentText(text);

        Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        mBuilder.setSound(uri);
        mBuilder.setAutoCancel(true);
        long[] pattern = {500,500,500,500,500,500,500,500,500};
        mBuilder.setVibrate(pattern);

        Intent resultIntent = new Intent(context, AlarmaDeMedicinas.class);

        resultIntent.putExtra("alarmas", alarmas);

        // Because clicking the notification opens a new ("special") activity, there's
        // no need to create an artificial back stack.
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        context,
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        mBuilder.setContentIntent(resultPendingIntent);


        // Sets an ID for the notification
        int mNotificationId = (int) ahora.getTimeInMillis();
        // Gets an instance of the NotificationManager service
        NotificationManager mNotifyMgr =
                (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        // Builds the notification and issues it.
        mNotifyMgr.notify(mNotificationId, mBuilder.build());
    }
}
