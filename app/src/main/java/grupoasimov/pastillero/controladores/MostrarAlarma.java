package grupoasimov.pastillero.controladores;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import grupoasimov.pastillero.modelo.Alarma;
import grupoasimov.pastillero.R;
import grupoasimov.pastillero.utiles.GestorAlarmas;

/**
 * Actividad para mostrar una alarma cuando la seleccionas desde una medicina
 * @author Adrián Serrano
 * @author Carlos Martín
 * @author María Varela
 */
public class MostrarAlarma extends AppCompatActivity {

    Alarma alarma;

    TextView nombreMedicinaA;
    TextView horaAlarmaA;
    TextView diasAlarmaA;
    TextView porcionAlarmaA;
    TextView notaAlarmaA;

    /**
     * Inicializas la vista , rellenando los campos con la infrmación de la alarma
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_alarma);
        long id = getIntent().getLongExtra("idAlarma", 0);
        alarma = Alarma.findById(Alarma.class, id);


        nombreMedicinaA = (TextView) findViewById(R.id.ma_nombre_medicina);
        horaAlarmaA = (TextView) findViewById(R.id.ma_hora);
        diasAlarmaA = (TextView) findViewById(R.id.ma_dias);
        porcionAlarmaA = (TextView) findViewById(R.id.ma_porcion);
        notaAlarmaA = (TextView) findViewById(R.id.ma_nota);

        nombreMedicinaA.setText(alarma.getMedicina().getNombre());
        horaAlarmaA.setText(alarma.getStringHora());
        diasAlarmaA.setText(alarma.getStringDiasLargo(getBaseContext()));
        porcionAlarmaA.setText(alarma.getStringPorcion(getBaseContext()));
        notaAlarmaA.setText(alarma.getNota());
    }

    /**
     * Inflamos el menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_alarma, menu);
        return true;
    }

    /**
     * Acciones para las opciones del menu
     * @param item seleccionado del menu
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.menu_alarma_borrar:
                alarma.delete();
                GestorAlarmas.activarAlarma(getBaseContext());
                finish();
                return true;

            case R.id.menu_alarma_ayuda:
                Intent helpActivity = new Intent(this, MostrarAyuda.class);
                startActivity(helpActivity);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
