package grupoasimov.pastillero.recibidores;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;

import java.util.Calendar;
import java.util.List;

import grupoasimov.pastillero.modelo.Alarma;

public class AvisoDeAlarma extends BroadcastReceiver {

    //List<Alarma> alarmas;

    public AvisoDeAlarma() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Calendar ahora = Calendar.getInstance();
        Log.d("Hay una alarma ahora", ahora.toString());

        List<Alarma> alarmas = Alarma.find(Alarma.class, "hora = ? AND minuto = ?", Integer.toString(ahora.get(Calendar.HOUR_OF_DAY))
                                                            , Integer.toString(ahora.get(Calendar.MINUTE)));
        for(Alarma alarma: alarmas){
            if (!alarma.esHoy())
                  alarmas.remove(alarma);
        }

        Log.d("   Las alarmas son", alarmas.toString());
    }
}
