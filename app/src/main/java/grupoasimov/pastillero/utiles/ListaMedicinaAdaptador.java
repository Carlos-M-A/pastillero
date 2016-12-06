package grupoasimov.pastillero.utiles;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import grupoasimov.pastillero.Modelo.Alarma;
import grupoasimov.pastillero.Modelo.Medicina;
import grupoasimov.pastillero.R;

/**
 * Created by ferreri on 6/12/16.
 */

public class ListaMedicinaAdaptador extends ArrayAdapter {
    Context context;
    List<Medicina> objects;

    public ListaMedicinaAdaptador(Context context, List objects) {
        super(context, R.layout.medicina_fila, objects);
        this.context = context;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Inflamos la vista
        LayoutInflater inflater = LayoutInflater.from(context);
        View item = inflater.inflate(R.layout.medicina_fila, null);

        Medicina medicina = objects.get(position);

        TextView nombre = (TextView) item.findViewById(R.id.texto_superior);
        nombre.setText(medicina.getNombre());

        TextView porcion = (TextView) item.findViewById(R.id.texto_inferior);
        porcion.setText(Integer.toString(medicina.getCantidadPorcion()));

        ImageView imagen = (ImageView) item.findViewById(R.id.imagen_single_post_circuito);
        imagen.setImageResource(R.mipmap.pastilla);
        // Devolvemos la vista para que se muestre en el ListView.
        return item;
    }
}
