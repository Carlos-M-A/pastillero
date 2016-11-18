package grupoasimov.pastillero.controladores;

import android.app.DialogFragment;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import grupoasimov.pastillero.R;


/**
 * Created by ESTIMADO USUARIO on 17/11/2016.
 */



public class CameraDialog extends DialogFragment {
    public static final int SELECT_PICTURE = 2;
    public static final int REQUEST_IMAGE_CAPTURE = 1;
   String TAG = "TAG";
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder =
                new AlertDialog.Builder(getActivity());

        builder.setMessage("¿Cámara o Galería?")
                .setTitle("Añadir imagen")
                .setPositiveButton("Cámara", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
                    }
                })
                .setNegativeButton("Galería", new DialogInterface.OnClickListener() {


                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent galeryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                        startActivityForResult(galeryIntent,SELECT_PICTURE);
                    }
                });


        return builder.create();
    }



}
