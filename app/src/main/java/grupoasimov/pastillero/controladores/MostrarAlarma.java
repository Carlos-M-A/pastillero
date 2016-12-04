package grupoasimov.pastillero.controladores;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import grupoasimov.pastillero.Modelo.Alarma;
import grupoasimov.pastillero.R;

public class MostrarAlarma extends AppCompatActivity {

    Alarma alarma;

    TextView nombreMedicinaA;
    TextView horaAlarmaA;
    TextView diasAlarmaA ;
    TextView porcionAlarmaA;
    TextView notaAlarmaA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_alarma);
        long id = getIntent().getLongExtra("idAlarma", 0);
        alarma = Alarma.findById(Alarma.class, id);



        nombreMedicinaA = (TextView) findViewById(R.id.nombreMedicinaA);
        horaAlarmaA = (TextView) findViewById(R.id.horaAlarmaA);
        diasAlarmaA = (TextView) findViewById(R.id.diasAlarmaA);
        porcionAlarmaA = (TextView) findViewById(R.id.porcionAlarmaA);
        notaAlarmaA = (TextView) findViewById(R.id.notaAlarmaA);

        nombreMedicinaA.setText(alarma.getMedicina().getNombre());
        horaAlarmaA.setText(alarma.getStringHora());
        diasAlarmaA.setText(alarma.getStringDiasLargo());
        porcionAlarmaA.setText(alarma.getStringPorcion());
        notaAlarmaA.setText(alarma.getNota());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_alarma, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_alarma_1:
                long id = alarma.getMedicina().getId();
                Intent i = new Intent(getApplicationContext(), MostrarMedicina.class);
                i.putExtra("idMedicina", id);
                alarma.delete();
                finish();
                startActivity(i);
                return true;
            case R.id.menu_alarma_2:
                Intent helpActivity = new Intent(this, MostrarAyuda.class);
                startActivity(helpActivity);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
