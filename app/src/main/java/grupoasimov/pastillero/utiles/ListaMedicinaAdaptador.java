package grupoasimov.pastillero.utiles;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import grupoasimov.pastillero.modelo.Medicina;
import grupoasimov.pastillero.R;

/**
 * Usado por el ListView de MostrarListasMedicinas para mostrar una lista de medicinas.
 * @author Adrián Serrano
 * @author Carlos Martín
 * @author María Varela
 */

public class ListaMedicinaAdaptador extends ArrayAdapter {
    Context context;
    List<Medicina> objects;

    public ListaMedicinaAdaptador(Context context, List objects) {
        super(context, R.layout.fila_medicina, objects);
        this.context = context;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Inflamos la vista
        LayoutInflater inflater = LayoutInflater.from(context);
        View item = inflater.inflate(R.layout.fila_medicina, null);

        Medicina medicina = objects.get(position);

        TextView nombre = (TextView) item.findViewById(R.id.texto_superior);
        nombre.setText(medicina.getNombre());

        TextView porcion = (TextView) item.findViewById(R.id.texto_inferior);
        porcion.setText(medicina.getStringPorcion(context));

        ImageView imagen = (ImageView) item.findViewById(R.id.imagen_single_post_circuito);
        if(medicina.getUrlImagen()!=null && medicina.getUrlImagen().length()>10) {
            Bitmap imagenBitMap = crearBitmap(medicina.getUrlImagen());
            imagen.setImageBitmap(imagenBitMap);
            //imagen.setImageDrawable(imagenMet);
        }
        // Devolvemos la vista para que se muestre en el ListView.
        return item;
    }
    /**
     * Reducimos la imagen para que ocupen menos
     * @param image bitmap de la imagen
     * @param maxSize tamaño imagen
     * @return
     */
    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }

        return Bitmap.createScaledBitmap(image, width, height, true);
    }
    public Bitmap crearBitmap(String url){

        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeFile(url,bmOptions);
        Bitmap smallBm =getResizedBitmap(bitmap,400);
        bitmap.recycle();
        return smallBm;
    }
}
