package grupoasimov.pastillero.utiles;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import grupoasimov.pastillero.Modelo.Alarma;
import grupoasimov.pastillero.R;


/**
 * Created by ferreri on 29/11/16.
 */

//http://www.proyectosimio.com/es/programacion-android-listview-adapter-personalizado-i/
public class ListaAlarmaAdaptador extends ArrayAdapter {

    private Context context;
    private List<Alarma> objects;

    public ListaAlarmaAdaptador(Context context, List<Alarma> objects) {
        super(context, R.layout.fila_alarma, objects);
        this.context = context;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Inflamos la vista
        LayoutInflater inflater = LayoutInflater.from(context);
        View item = inflater.inflate(R.layout.fila_alarma, null);

        Alarma alarma = objects.get(position);

        TextView hora = (TextView) item.findViewById(R.id.hora_alarma);
        hora.setText(alarma.getStringHora());

        TextView dias = (TextView) item.findViewById(R.id.dias_alarma);
        dias.setText(alarma.getStringDiasCorto(context));

        TextView porcion = (TextView) item.findViewById(R.id.porcion_alarma);
        porcion.setText(alarma.getStringPorcion(context));

        // Devolvemos la vista para que se muestre en el ListView.
        return item;
    }
}
