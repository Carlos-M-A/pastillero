package grupoasimov.pastillero.controladores;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import grupoasimov.pastillero.R;
import grupoasimov.pastillero.modelo.Medicina;

import static android.R.attr.data;


public class AgregarActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int DIALOG_REQUEST_CODE = 1;

    CameraDialog dialogo;
    static final int REQUEST_TAKE_PHOTO = 1;
    private static final String LOG_TAG = "LOG";
    String TAG = "TAG";
    String rutaFotoActual;
    String fotoUri;
    File foto;
    Uri photoUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageButton openImage = (ImageButton) findViewById(R.id.button2);
        openImage.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        dialogo = new CameraDialog();
        dialogo.show(getFragmentManager(),TAG);

        // dispatchTakePictureIntent();




    }
    public void setBackground(){

        ImageButton b = (ImageButton)findViewById(R.id.button2);
        Drawable medicina = Drawable.createFromPath(rutaFotoActual);
        Bitmap bmedicina = ((BitmapDrawable)medicina).getBitmap();
        RoundedBitmapDrawable medicinaBordes = RoundedBitmapDrawableFactory.create(getResources(),bmedicina);
        medicinaBordes.setCircular(true);
        Log.d(TAG,"Ruta en setBackground : "+rutaFotoActual);
        Drawable dd = Drawable.createFromPath(rutaFotoActual);
        Drawable camara =getResources().getDrawable( R.drawable.camera);
        b.invalidateDrawable(camara);
        //b.setImageDrawable(medicinaBordes);
        b.setBackground(medicina);

        Log.d(TAG,"Ruta de la foto en setBackground(): "+rutaFotoActual);
        Log.d(TAG,"ESTOY EN EL METODO setBackground()");
    }

    /**
     * Creamos el File de la imagen, con un nombre que depende del tiempo. De este modo se evitan colisiones con los nombres
     * de las imagenes de las pastillas cuando se guarden en los ficheros de la aplicación
     *
     * @return File que contendra la imagen tomada de la camara
     * @throws IOException
     */
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getAlbumStorageDir(getApplication(), "pillPhotoAlbum");
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save file: path for use with ACTION_VIEW intents
        rutaFotoActual = image.getAbsolutePath();
        Log.d(TAG, rutaFotoActual);
        return image;
    }

    /**
     * @param context   de la aplicacion , necesario para obtener el directorio
     * @param albumName el nombre que tendra el album de fotos
     * @return el directorio donde se guardaran las fotos
     */
    public File getAlbumStorageDir(Context context, String albumName) {
        // Get the directory for the app's private pictures directory.
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
/*    private void dispatchTakePictureIntent() {

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
            this.photoUri = photoURI;
            Log.d(TAG, photoURI.toString());
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            takePictureIntent.putExtra("rutaFoto", rutaFotoActual);
            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            ImageButton b = (ImageButton) findViewById(R.id.button2);
            File f = new File(rutaFotoActual);
            Log.d(TAG, rutaFotoActual);



            Log.d(TAG, "HE LLEGADO HASTA EL FINAL DEL METODO INTENT CAMERA");


        }*/

    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        // Codigo para intent de la camara
        if (requestCode == REQUEST_TAKE_PHOTO) {
            // El intent fue correctamente
            Log.d(TAG,"RequestCode = REQUEST_TAKE_PHOTO y RESULT_OK "+RESULT_OK);
            if (resultCode == RESULT_OK) {

                ImageButton b =(ImageButton)findViewById(R.id.button2);
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

                b.setImageBitmap(bm);


            }
        }

    }
}



