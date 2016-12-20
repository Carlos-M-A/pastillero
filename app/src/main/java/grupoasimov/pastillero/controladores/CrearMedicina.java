package grupoasimov.pastillero.controladores;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.List;

import grupoasimov.pastillero.modelo.Medicina;
import grupoasimov.pastillero.R;

public class CrearMedicina extends AppCompatActivity implements View.OnClickListener {

    EditText nombreMedicina;
    EditText descripcionMedicina;
    EditText porcionMedicina;
    ImageButton imagenMedicina;
    Button guardarMedicina;

    Medicina medicina;

    CameraDialog dialogo;
    String TAG = "TAG";
    static final int REQUEST_TAKE_PHOTO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_medicina);

        nombreMedicina = (EditText) findViewById(R.id.cm_nombre);
        descripcionMedicina = (EditText) findViewById(R.id.cm_descripcion);
        porcionMedicina = (EditText) findViewById(R.id.cm_porcion);
        imagenMedicina = (ImageButton) findViewById(R.id.cm_imagen);
        guardarMedicina = (Button) findViewById(R.id.cm_guardar);

        imagenMedicina.setOnClickListener(this);
        guardarMedicina.setOnClickListener(this);

        if(getIntent().getBooleanExtra("actualizar", true)==true){
            medicina = Medicina.findById(Medicina.class, getIntent().getLongExtra("idMedicina", 0));
            nombreMedicina.setText(medicina.getNombre());
            descripcionMedicina .setText(medicina.getDescripcion());
            porcionMedicina.setText(String.valueOf(medicina.getCantidadPorcion()));

        }else {
            medicina = new Medicina();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.cm_guardar:
                medicina.setNombre(nombreMedicina.getText().toString());
                medicina.setDescripcion(descripcionMedicina.getText().toString());
                medicina.setCantidadPorcion(Integer.parseInt(porcionMedicina.getText().toString()));
                medicina.setUrlImagen("url");
                medicina.save();

                finish();
                break;

            case R.id.cm_imagen:
                dialogo = new CameraDialog();
                dialogo.show(getFragmentManager(),TAG);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        // Codigo para intent de la camara
        if (requestCode == REQUEST_TAKE_PHOTO) {
            // El intent fue correctamente
            Log.d(TAG,"RequestCode = REQUEST_TAKE_PHOTO y RESULT_OK "+RESULT_OK);
            if (resultCode == RESULT_OK) {

                // String rutaFoto = data.getStringExtra("rutaFotoActual");
                // Uri fu = data.getData();
                List<Medicina> medicinas = Medicina.listAll(Medicina.class);
                Medicina medicina = medicinas.get(medicinas.size()-1);
                Log.d(TAG,"RUTA FOTO:  "+ medicina.getUrlImagen());

                   /*try {
                       Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), );
                       b.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.d(TAG,"NO SE HA CREADO EL BITMAP ");
                    }*/
                Log.d(TAG,"ESTOY EN ON ACTIVITY RESULT !!!!!!!!!!");
                Drawable d = Drawable.createFromPath(medicina.getUrlImagen()) ;
                Bitmap bm = ((BitmapDrawable)d).getBitmap();

                imagenMedicina.setImageBitmap(bm);


            }
        }

    }
}
