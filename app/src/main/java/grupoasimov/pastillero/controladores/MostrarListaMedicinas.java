package grupoasimov.pastillero.controladores;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import grupoasimov.pastillero.modelo.Medicina;
import grupoasimov.pastillero.R;
import grupoasimov.pastillero.utiles.ListaMedicinaAdaptador;

/**
 * Actividad inicial de la aplicacion. Muestra una lista con todas las medicinas que fueron
 * introducidas. Para que la aplicacion funcione al instalarla desde Android Studio hay que
 * desactivar el Instant Run (File > Settings > Build, Execution,.. > Instant Run).
 * En los emuladores puede dar fallos, pues se requiere enviar sms con la aplicacion.
 * @author Adrián Serrano
 * @author Carlos Martín
 * @author María Varela
 */
public class MostrarListaMedicinas extends AppCompatActivity  implements AdapterView.OnItemClickListener{

    List<Medicina> medicinas;
    ListView listaMedicinas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_lista_medicinas);

        listaMedicinas = (ListView) findViewById(R.id.mlm_lista);
        medicinas = Medicina.listAll(Medicina.class);
        ListaMedicinaAdaptador listaMedicinaAdaptador = new ListaMedicinaAdaptador(this, medicinas);
        listaMedicinas.setAdapter(listaMedicinaAdaptador);
        listaMedicinas.setOnItemClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        medicinas = Medicina.listAll(Medicina.class);
        ListaMedicinaAdaptador listaMedicinaAdaptador = new ListaMedicinaAdaptador(this, medicinas);

        listaMedicinas.setAdapter(listaMedicinaAdaptador);
        listaMedicinas.setOnItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_listar_medicinas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_listar_medicina_ayuda:
                Intent helpActivity = new Intent(this, MostrarAyuda.class);
                startActivity(helpActivity);
                return true;
            case R.id.menu_listar_medicina_nueva_medicina:
                Intent i = new Intent(this, CrearMedicina.class);
                i.putExtra("actualizar", false);
                Medicina medicina = new Medicina();
                i.putExtra("medicina", medicina);
                startActivity(i);
                return true;
            case R.id.menu_listar_medicina_paciente_cuidador:
                Intent e = new Intent(this, MostrarPacienteCuidador.class);
                startActivity(e);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Medicina medicina = medicinas.get(position);
        Intent i = new Intent(this, MostrarMedicina.class);
        long id2 = medicina.getId();
        i.putExtra("idMedicina", id2);
        startActivity(i);
    }

}
