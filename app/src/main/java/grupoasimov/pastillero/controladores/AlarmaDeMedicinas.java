package grupoasimov.pastillero.controladores;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import grupoasimov.pastillero.R;
import grupoasimov.pastillero.modelo.Alarma;

public class AlarmaDeMedicinas extends AppCompatActivity implements View.OnClickListener {

    ImageView imagenMedicina;
    TextView nombreMedicina;
    TextView porcionAlarma;
    TextView notaAlarma;
    Button botonTomado;

    int contadorAlarmas = 0;
    ArrayList<Alarma> alarmas;

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

    private void nuevosDatosAlarma(){
        Alarma alarma = alarmas.get(contadorAlarmas);
        imagenMedicina.setImageDrawable(Drawable.createFromPath(alarma.getMedicina().getUrlImagen()));
        nombreMedicina.setText(alarma.getMedicina().getNombre());
        porcionAlarma.setText(alarma.getStringPorcion(getBaseContext()));
        notaAlarma.setText(alarma.getNota());
        contadorAlarmas++;
    }

    @Override
    public void onClick(View v) {
        if(contadorAlarmas >= alarmas.size()){
            finish();
        }else{
            nuevosDatosAlarma();
        }

    }
}
