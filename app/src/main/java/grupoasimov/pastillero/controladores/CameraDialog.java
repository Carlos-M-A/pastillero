package grupoasimov.pastillero.controladores;

import android.app.DialogFragment;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static android.app.Activity.RESULT_OK;


/**
 * Created by ESTIMADO USUARIO on 17/11/2016.
 */


public class CameraDialog extends DialogFragment {

    static final int REQUEST_TAKE_PHOTO = 1;
    private static final String LOG_TAG = "LOG";
    String TAG = "TAG";
    String rutaFotoActual;
    static final int REQUEST_IMAGE_OPEN = 1;
    private String uriFoto;

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

                    }
                })
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
     *
     * @return File que contendra la imagen tomada de la camara
     * @throws IOException
     */
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getAlbumStorageDir(getActivity().getApplication(), "pillPhotoAlbum");
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
     * @param context   de la aplicacion , necesario para obtener el directorio
     * @param albumName el nombre que tendra el album de fotos
     * @return el directorio donde se guardaran las fotos
     */
    public File getAlbumStorageDir(Context context, String albumName) {
        // Get the directory for the app's private pictures directory.
        File file = new File(context.getExternalFilesDir(
                Environment.DIRECTORY_PICTURES), albumName);
        /*if (!file.mkdirs()) {
        }*/
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
            List<ResolveInfo> resInfoList = getActivity().getApplication().getPackageManager().queryIntentActivities(takePictureIntent, PackageManager.MATCH_DEFAULT_ONLY);
            for (ResolveInfo resolveInfo : resInfoList) {
                String packageName = resolveInfo.activityInfo.packageName;
                getActivity().getApplication().grantUriPermission(packageName, photoURI, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            }

            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_OPEN && resultCode == RESULT_OK) {
            Log.d(TAG, "ENTRO EN TRY PARA FOTO pppppGALERIA");
            Uri fotoURI = data.getData();
            uriFoto = fotoURI.toString(); // AQUIIIII CARLOS
            Log.d("FOTO URI CAMERADIALOG", getUriFoto());
            File fotoGaleria = new File(fotoURI.getPath());
            // Do work with full size photo saved at fullPhotoUri
            try {
                File foto = createImageFile();
                Files.copy(fotoGaleria, foto);
                Log.d(TAG, "ENTRO EN TRY PARA FOTO GALERIA");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public String getUriFoto(){
        return uriFoto;
        //return "aaaaaaaaaaa";
    }
}

