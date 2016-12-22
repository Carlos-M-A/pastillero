package grupoasimov.pastillero.controladores;


        import android.app.Activity;
        import android.app.AlertDialog;
        import android.app.Dialog;
        import android.app.DialogFragment;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.content.pm.PackageManager;
        import android.content.pm.ResolveInfo;
        import android.graphics.Camera;
        import android.net.Uri;
        import android.nfc.Tag;
        import android.os.Bundle;
        import android.os.Environment;
        import android.provider.MediaStore;
        import android.support.v4.content.FileProvider;
        import android.util.Log;
        import java.io.File;
        import java.io.IOException;
        import java.net.URI;
        import java.text.SimpleDateFormat;
        import java.util.Date;
        import java.util.List;

        import grupoasimov.pastillero.modelo.Medicina;


public class CameraDialog extends DialogFragment {

    static final int REQUEST_TAKE_PHOTO = 1;
    private static final String LOG_TAG ="LOG" ;
    String TAG = "--En CameraDialog--";
    String rutaFotoActual;
    String fotoUri;
    String MY_VALUE="mi valor";
    Medicina medicina;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Bundle bundle = new Bundle();
        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());

        builder.setMessage("¿Cámara o Galería?")
                .setTitle("Añadir imagen")
                .setPositiveButton("Cámara", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
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
            List<ResolveInfo> resInfoList = getActivity().getApplication().getPackageManager().queryIntentActivities(takePictureIntent, PackageManager.MATCH_DEFAULT_ONLY);
            for (ResolveInfo resolveInfo : resInfoList) {
                String packageName = resolveInfo.activityInfo.packageName;
                getActivity().getApplication().grantUriPermission(packageName, photoURI, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            }

            fotoUri = photoURI.toString();
            Log.d(TAG,fotoUri+"EEEEEEEEEEEEEEEEEEEEEE");
            takePictureIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, photoURI);

            //no se si vale para algo esto
            medicina.setUrlImagen(rutaFotoActual);

            getActivity().startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            Log.d(TAG,"HE LLEGADO HASTA EL FINAL DEL METODO INTENT CAMERA");

            Log.d("Camera dialog url", rutaFotoActual);
            //getActivity().getIntent().putExtra("url", rutaFotoActual);

            //Medicina medicina = new Medicina();
            //medicina.setNombre("nombre1");
            //medicina.setCantidadPorcion(23);
            //medicina.setDescripcion("sfg");
            medicina.setUrlImagen(rutaFotoActual);
            //medicina.save();

        }

    }

    public void setMedicina(Medicina medicina) {
        this.medicina = medicina;
        Log.d(TAG, this.medicina.toString());
    }
}

