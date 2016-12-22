package grupoasimov.pastillero.controladores;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import grupoasimov.pastillero.modelo.Medicina;
import grupoasimov.pastillero.R;

/**
 * Actividad para crear las medicinas
 *  @author Adrián Serrano
 * @author Carlos Martín
 * @author María Varela
 */
public class CrearMedicina extends AppCompatActivity implements View.OnClickListener {

    EditText nombreMedicina;
    EditText descripcionMedicina;
    EditText porcionMedicina;
    ImageButton imagenMedicina;
    Button guardarMedicina;

    static final int REQUEST_TAKE_PHOTO = 1;
    private static final String LOG_TAG ="LOG" ;

    String rutaFotoActual;
    String fotoUri;

    Medicina medicina;

    final String TAG = "--En CREAR MEDICINA--";


    /**
     * Se inicializa la vista
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");

        setContentView(R.layout.activity_crear_medicina);

        nombreMedicina = (EditText) findViewById(R.id.cm_nombre);
        descripcionMedicina = (EditText) findViewById(R.id.cm_descripcion);
        porcionMedicina = (EditText) findViewById(R.id.cm_porcion);
        imagenMedicina = (ImageButton) findViewById(R.id.cm_imagen);
        guardarMedicina = (Button) findViewById(R.id.cm_guardar);

        imagenMedicina.setOnClickListener(this);
        guardarMedicina.setOnClickListener(this);



        actualizaMedicina();
        actualizaVista();
    }

    /**
     * Obtiene la medicina del intent, y en caso de ya existir la obtiene de la base de datos
     */
    private void actualizaMedicina(){
        medicina = (Medicina)getIntent().getSerializableExtra("medicina");
        if(getIntent().getBooleanExtra("actualizar", true))
            medicina = Medicina.findById(Medicina.class, getIntent().getLongExtra("idMedicina", 0));
        Log.d(TAG, "id medicina: " + medicina.getId());
    }

    /**
     * Actualiza la vista de esta actividad tras si se llama después de un cambio
     */
    private void actualizaVista(){
        if(medicina.getNombre().length()!=0)
            nombreMedicina.setText(medicina.getNombre());
        if(medicina.getDescripcion().length()!=0)
            descripcionMedicina.setText(medicina.getDescripcion());
        if(medicina.getCantidadPorcion()!=0)
            porcionMedicina.setText(String.valueOf(medicina.getCantidadPorcion()));
        if(medicina.getUrlImagen()!=null && medicina.getUrlImagen().length()!=0) {
            Drawable d = Drawable.createFromPath(medicina.getUrlImagen());
            imagenMedicina.setBackground(d);
        }
    }

    /**
     * Para guardar el estado de la aplicacion antes de que se rote la pantalla
     * @param savedInstanceState
     */
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        Log.d(TAG, "onSaveIntanceState");
        savedInstanceState.putSerializable("medicina", medicina);

        super.onSaveInstanceState(savedInstanceState);
    }

    /**
     * Para recuperar el estado de la aplicacion despues de rotar la pantalla
     * @param savedInstanceState
     */
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        Log.d(TAG, "onRestoreIntanceState");

        medicina = (Medicina) savedInstanceState.getSerializable("medicina");
        //Drawable d = Drawable.createFromPath(medicina.getUrlImagen()) ;
        //imagenMedicina.setBackground(d);

        if(medicina!=null && medicina.getUrlImagen()==null)
            medicina.setUrlImagen("nulo");
    }

    /**
     * Caso guardar medicina en la base de datos
     * Caso imagen que llama a la camara y crea la fotografia
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.cm_guardar:
                int cantidadPorcion = 100;
                String nombre = getString(R.string.nombre);
                String descripcion = getString(R.string.descripcion);
                if(nombreMedicina.getText().length()!=0)
                    nombre = nombreMedicina.getText().toString();
                if(descripcionMedicina.getText().length()!=0)
                    descripcion = descripcionMedicina.getText().toString();
                if(porcionMedicina.getText().length()!=0)
                    cantidadPorcion = Integer.parseInt(porcionMedicina.getText().toString());
                Log.d("CREAR MEDICINA", "url: " + medicina.getUrlImagen());

                medicina.setNombre(nombre);
                medicina.setDescripcion(descripcion);
                medicina.setCantidadPorcion(cantidadPorcion);
                medicina.save();

                finish();
                break;

            case R.id.cm_imagen:
                dispatchTakePictureIntent();
                break;
        }
    }

    /**
     * Creamos el File de la imagen, con un nombre que depende del tiempo. De este modo se evitan colisiones con los nombres
     * de las imagenes de las pastillas cuando se guarden en los ficheros de la aplicación
     * @return File que contendra la imagen tomada de la camara
     * @throws IOException
     */
    private File createImageFile() throws IOException {
        // Create an image file name
        //String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String timeStamp = String.valueOf((int)Calendar.getInstance().getTimeInMillis());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getAlbumStorageDir(getApplication());
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save file: path for use with ACTION_VIEW intents
        rutaFotoActual = image.getAbsolutePath();
        return image;
    }

    /**
     *
     * @param context de la aplicacion , necesario para obtener el directorio
     * @return el directorio donde se guardaran las fotos
     */
    public File getAlbumStorageDir(Context context) {
        // Get the directory for the app's private pictures directory.
        String albumName = "pillPhotoAlbum";
        File file = new File(context.getExternalFilesDir(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            Log.d(LOG_TAG, "DIRECTORIO NO CREADO");
        }
        return file;
    }

    /**
     * Lanza la camara y se guarda la fotografia
     */
    private void dispatchTakePictureIntent() {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // Creamos el fichero donde ira la imagen
        File foto = null;
        try {
            foto = createImageFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        // Sigue solo si se crea el fichero
        if (foto != null) {
            Uri photoURI = FileProvider.getUriForFile(getApplication(),
                    "com.example.android.fileprovider",
                    foto);
            List<ResolveInfo> resInfoList = getApplication().getPackageManager().queryIntentActivities(takePictureIntent, PackageManager.MATCH_DEFAULT_ONLY);
            for (ResolveInfo resolveInfo : resInfoList) {
                String packageName = resolveInfo.activityInfo.packageName;
                getApplication().grantUriPermission(packageName, photoURI, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            }

            fotoUri = photoURI.toString();
            Log.d(TAG,fotoUri+"EEEEEEEEEEEEEEEEEEEEEE");
            takePictureIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, photoURI);


            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            Log.d(TAG,"HE LLEGADO HASTA EL FINAL DEL METODO INTENT CAMERA");

            Log.d(TAG, rutaFotoActual);

        }

    }

    /**
     * Una vez hecha la foto se pone como fondo en el ImageButton que llama al intent de camara
     * @param requestCode REQUEST_TAKE_PHOTO
     * @param resultCode RESULT_OK
     * @param data Intent que llamo a la camara
     */
    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        // Codigo para intent de la camara
        if (requestCode == REQUEST_TAKE_PHOTO) {
            // El intent fue correctamente
            Log.d(TAG,"RequestCode = REQUEST_TAKE_PHOTO y RESULT_OK "+RESULT_OK);
            Log.d(TAG,"EN ON ACTIVITY RESULT -------------------------------------------------  "+RESULT_OK);
            if (resultCode == RESULT_OK) {

                Log.d(TAG,"RUTA FOTO:  "+ medicina.getUrlImagen());

                Log.d(TAG,"ESTOY EN ON ACTIVITY RESULT !!!!!!!!!!");
                medicina.setUrlImagen(rutaFotoActual);
                Drawable d = Drawable.createFromPath(medicina.getUrlImagen()) ;

                imagenMedicina.setBackground(d);


            }
        }

    }
}
