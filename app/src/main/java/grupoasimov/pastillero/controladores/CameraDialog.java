package grupoasimov.pastillero.controladores;

import android.app.DialogFragment;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.AlarmClock;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;

import static android.app.Activity.RESULT_OK;


/**
 * Created by ESTIMADO USUARIO on 17/11/2016.
 */



public class CameraDialog extends DialogFragment {
    public static final int SELECT_PICTURE = 2;
    static final int REQUEST_TAKE_PHOTO = 1;
    private static final String LOG_TAG ="LOG" ;
    String TAG = "TAG";
    String rutaFotoActual;
    static final int REQUEST_IMAGE_OPEN = 1;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());

        builder.setMessage("¿Cámara o Galería?")
                .setTitle("Añadir imagen")
                .setPositiveButton("Cámara", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        //startActivityForResult(cameraIntent, REQUEST_TAKE_PHOTO);
                        dispatchTakePictureIntent();

                    }})
                .setNegativeButton("Galería", new DialogInterface.OnClickListener() {


                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Intent galeryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                        // galeryIntent.setType("image/*");
                        //startActivityForResult(galeryIntent,SELECT_PICTURE);
                        //Log.d(TAG,galeryIntent.getDataString());
                        //selectImage();

                    }
                });


        return builder.create();
    }



    /**
     * Creamos el File de la imagen, con un nombre que depende del tiempo. De este modo se evitan colisiones con los nombres
     * de las imagenes de las pastillas cuando se guarden en los ficheros de la aplicación
     * @return File que contendra la imagen tomada de la camara
     * @throws IOException
     */
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getAlbumStorageDir(getActivity().getApplication(),"pillPhotoAlbum");
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


            Uri photoURI = FileProvider.getUriForFile(getActivity().getApplication(),
                    "com.example.android.fileprovider",
                    foto);

            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            Log.d(TAG,"HE LLEGADO HASTA EL FINAL DEL METODO INTENT CAMERA");
        }

    }


    public void selectImage() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("image/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        File foto = null;
        try {
            foto = createImageFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(foto !=null) {

            Uri photoURI = FileProvider.getUriForFile(getActivity().getApplication(),
                    "com.example.android.fileprovider",
                    foto);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);

            // Only the system receives the ACTION_OPEN_DOCUMENT, so no need to test.
            startActivityForResult(intent, REQUEST_IMAGE_OPEN);
            Log.d(TAG, "ENTRO EN TRY PARA FOTO pppppLLLLLGALERIA");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_OPEN && resultCode == RESULT_OK) {
            Log.d(TAG,"ENTRO EN TRY PARA FOTO pppppGALERIA");
            Uri fotoURI = data.getData();
            File fotoGaleria = new File(fotoURI.getPath());
            // Do work with full size photo saved at fullPhotoUri
            try {
                File foto = createImageFile();
                Files.copy(fotoGaleria,foto);
                Log.d(TAG,"ENTRO EN TRY PARA FOTO GALERIA");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    /*public void createAlarm(String message, int hour, int minutes) {
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                .putExtra(AlarmClock.EXTRA_MESSAGE, message)
                .putExtra(AlarmClock.EXTRA_HOUR, hour)
                .putExtra(AlarmClock.EXTRA_MINUTES, minutes);

        startActivity(intent);

    }*/
}

