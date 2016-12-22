package grupoasimov.pastillero.controladores;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;


import java.util.ArrayList;

import grupoasimov.pastillero.modelo.Alarma;
import grupoasimov.pastillero.modelo.Medicina;
import grupoasimov.pastillero.R;
import grupoasimov.pastillero.utiles.GestorAlarmas;

public class CrearAlarmas extends AppCompatActivity implements View.OnClickListener {

    TextView nombreMedicina;
    CheckBox checkBoxLunes;
    CheckBox checkBoxMartes;
    CheckBox checkBoxMiercoles;
    CheckBox checkBoxJueves;
    CheckBox checkBoxViernes;
    CheckBox checkBoxSabado;
    CheckBox checkBoxDomingo;
    ArrayList<TimePicker> horasAlarma;
    Button nuevaHora;
    EditText porcionAlarma;
    EditText notaAlarma;
    Button guardarAlarma;
    LinearLayout listaHorasAlarma;

    Medicina medicina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_alarmas);

        // Inicializamos componentes de la interfaz de usuario
        nombreMedicina = (TextView) findViewById(R.id.ca_nombre_medicina);
        checkBoxLunes = (CheckBox) findViewById(R.id.ca_lunes);
        checkBoxMartes = (CheckBox) findViewById(R.id.ca_martes);
        checkBoxMiercoles = (CheckBox) findViewById(R.id.ca_miercoles);
        checkBoxJueves = (CheckBox) findViewById(R.id.ca_jueves);
        checkBoxViernes = (CheckBox) findViewById(R.id.ca_viernes);
        checkBoxSabado = (CheckBox) findViewById(R.id.ca_sabado);
        checkBoxDomingo = (CheckBox) findViewById(R.id.ca_domingo);
        horasAlarma = new ArrayList<>();
        listaHorasAlarma = (LinearLayout) findViewById(R.id.ca_lista_horas) ;
        horasAlarma.add((TimePicker)findViewById(R.id.ca_hora));
        nuevaHora = (Button) findViewById(R.id.ca_nueva_hora);
        porcionAlarma = (EditText) findViewById(R.id.ca_porcion);
        notaAlarma = (EditText) findViewById(R.id.ca_nota);
        guardarAlarma = (Button) findViewById(R.id.ca_guardar);


        // Declaramos los listener
        nuevaHora.setOnClickListener(this);
        guardarAlarma.setOnClickListener(this);

        //Insertamos nombre de la medicina
        medicina = Medicina.findById(Medicina.class, getIntent().getLongExtra("idMedicina", 0));
        nombreMedicina.setText(medicina.getNombre());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ca_nueva_hora:
                TimePicker nuevaHoraAlarma = new TimePicker(this);
                listaHorasAlarma.addView(nuevaHoraAlarma, horasAlarma.size());
                horasAlarma.add(nuevaHoraAlarma);
                break;
            case R.id.ca_guardar:

                boolean lunes = checkBoxLunes.isChecked();
                boolean martes = checkBoxMartes.isChecked();
                boolean miercoles = checkBoxMiercoles.isChecked();
                boolean jueves = checkBoxJueves.isChecked();
                boolean viernes = checkBoxViernes.isChecked();
                boolean sabado = checkBoxSabado.isChecked();
                boolean domingo = checkBoxDomingo.isChecked();

                int cantidadToma = 100;
                String nota = getString(R.string.nota_alarma);
                if(porcionAlarma.getText().length()!=0)
                    cantidadToma = Integer.parseInt(porcionAlarma.getText().toString());
                if(notaAlarma.getText().length()!=0)
                    nota = notaAlarma.getText().toString();

                for (TimePicker hora: horasAlarma) {
                    Alarma alarma = new Alarma(medicina);

                    alarma.setLunes(lunes);
                    alarma.setMartes(martes);
                    alarma.setMiercoles(miercoles);
                    alarma.setJueves(jueves);
                    alarma.setViernes(viernes);
                    alarma.setSabado(sabado);
                    alarma.setDomingo(domingo);


                    alarma.setCantidadToma(cantidadToma);
                    alarma.setNota(nota);

                    // Hay que usar estos metodos deprecated por que son necesarios para que funcione
                    // para la version 17 del SDK

                    alarma.setHora(hora.getCurrentHour());
                    alarma.setMinuto(hora.getCurrentMinute());

                    if(lunes || martes || miercoles || jueves || viernes || sabado || domingo)
                        alarma.save();

                    GestorAlarmas.activarAlarma(getBaseContext());
                }
                finish();
                break;
        }

    }
}
