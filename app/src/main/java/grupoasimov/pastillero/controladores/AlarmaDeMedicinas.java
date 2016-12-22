package grupoasimov.pastillero.controladores;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import grupoasimov.pastillero.R;
import grupoasimov.pastillero.modelo.Alarma;
import grupoasimov.pastillero.modelo.Paciente;

/**
 * Actividad que se abre cuando pulsas sobre la notificación , para que confirmes que lo has tomado
 * @author Adrián Serrano
 * @author Carlos Martín
 * @author María Varela
 */
public class AlarmaDeMedicinas extends AppCompatActivity implements View.OnClickListener {

    ImageView imagenMedicina;
    TextView nombreMedicina;
    TextView porcionAlarma;
    TextView notaAlarma;
    Button botonTomado;
    Paciente paciente;

    int contadorAlarmas = 0;
    ArrayList<Alarma> alarmas;

    /**
     * Inicializamos todos los elementos de la vista y mostramos las alarmas que estan activadas
     * @param savedInstanceState a
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarma_de_medicinas);

        imagenMedicina = (ImageView) findViewById(R.id.adm_imagen_medicina);
        nombreMedicina = (TextView) findViewById(R.id.adm_nombre_medicina);
        porcionAlarma = (TextView) findViewById(R.id.adm_porcion_alarma);
        notaAlarma = (TextView) findViewById(R.id.adm_nota_alarma);
        botonTomado = (Button) findViewById(R.id.adm_boton_tomado);
        botonTomado.setOnClickListener(this);

        alarmas = (ArrayList<Alarma>) getIntent().getSerializableExtra("alarmas");

        nuevosDatosAlarma();
    }

    /**
     * Enviar mensaje con confirmación de que la persona ha tomado la medicina
     */
    private void enviarConfirmacion(){
        paciente = Paciente.listAll(Paciente.class).get(0);
        String strPhone = paciente.getTelefono();
        String strMessage = "He tomado "+nombreMedicina.getText();

        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        sendIntent.setType("vnd.android-dir/mms-sms");
        sendIntent.putExtra("address", strPhone);
        sendIntent.putExtra("sms_body", strMessage);
        startActivity(sendIntent);
    }

    /**
     *Enviar confirmación mediante email
     */
    private void enviarEmail() {
        paciente = Paciente.listAll(Paciente.class).get(0);
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, paciente.getEmail());
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Medicinas");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "He tomado la medicina "+nombreMedicina.getText());
        emailIntent.setType("message/rfc822");
        startActivity(Intent.createChooser(emailIntent, "Email "));
    }

    /**
     * Actualiza la vista de manera que sigan apareciendo el resto de medicinas que no se ha tomado
     */
    private void nuevosDatosAlarma(){
        Alarma alarma = alarmas.get(contadorAlarmas);
        imagenMedicina.setImageDrawable(Drawable.createFromPath(alarma.getMedicina().getUrlImagen()));
        nombreMedicina.setText(alarma.getMedicina().getNombre());
        porcionAlarma.setText(alarma.getStringPorcion(getBaseContext()));
        notaAlarma.setText(alarma.getNota());
        contadorAlarmas++;
    }

    /**
     * Si quedan medicinas se muestran y si no la actividad termina
     * @param v
     */
    @Override
    public void onClick(View v) {
        if(Paciente.listAll(Paciente.class)!=null) enviarConfirmacion();//enviarEmail();
        if(contadorAlarmas >= alarmas.size()){
            finish();
        }else{
            nuevosDatosAlarma();
        }

    }
}
