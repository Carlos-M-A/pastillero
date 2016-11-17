package grupoasimov.pastillero.controladores;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import grupoasimov.pastillero.Modelo.Medicina;
import grupoasimov.pastillero.R;

public class CrearAlarmas extends AppCompatActivity implements View.OnClickListener {

    TextView textView4;
    CalendarView fechaInicio;
    CalendarView fechaFin;
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
        textView4 = (TextView) findViewById(R.id.textView4);
        fechaInicio = (CalendarView) findViewById(R.id.fechaInicio);
        fechaFin = (CalendarView) findViewById(R.id.fechaFin);
        checkBoxLunes = (CheckBox) findViewById(R.id.checkBoxLunes);
        checkBoxMartes = (CheckBox) findViewById(R.id.checkBoxMartes);
        checkBoxMiercoles = (CheckBox) findViewById(R.id.checkBoxMiercoles);
        checkBoxJueves = (CheckBox) findViewById(R.id.checkBoxJueves);
        checkBoxViernes = (CheckBox) findViewById(R.id.checkBoxViernes);
        checkBoxSabado = (CheckBox) findViewById(R.id.checkBoxSabado);
        checkBoxDomingo = (CheckBox) findViewById(R.id.checkBoxDomingo);
        horasAlarma = new ArrayList<TimePicker>();
        listaHorasAlarma = (LinearLayout) findViewById(R.id.listaHorasAlarma) ;
        horasAlarma.add((TimePicker)findViewById(R.id.horaAlarma));
        nuevaHora = (Button) findViewById(R.id.nuevaHora);
        porcionAlarma = (EditText) findViewById(R.id.porcionAlarma);
        notaAlarma = (EditText) findViewById(R.id.notaAlarma);
        guardarAlarma = (Button) findViewById(R.id.guardarAlarma);


        // Declaramos los listener
        nuevaHora.setOnClickListener(this);
        guardarAlarma.setOnClickListener(this);

        //Insertamos nombre de la medicina
        medicina = Medicina.findById(Medicina.class, getIntent().getIntExtra("idMedicina", 0));
        textView4.setText(medicina.getNombre());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.nuevaHora:
                TimePicker nuevaHoraAlarma = new TimePicker(this);
                listaHorasAlarma.addView(nuevaHoraAlarma, horasAlarma.size());
                horasAlarma.add(nuevaHoraAlarma);
                break;
            case R.id.guardarAlarma:
                Calendar a = Calendar.getInstance();
                a.setTimeInMillis(fechaInicio.getDate());
                fechaInicio.setDate(a.getTimeInMillis());

                Log.d("Fecha inicio", a.toString());
                break;
        }

    }
}
