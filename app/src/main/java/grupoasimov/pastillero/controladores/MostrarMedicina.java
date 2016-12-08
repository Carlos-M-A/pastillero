package grupoasimov.pastillero.controladores;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import grupoasimov.pastillero.Modelo.Alarma;
import grupoasimov.pastillero.Modelo.Medicina;
import grupoasimov.pastillero.R;
import grupoasimov.pastillero.utiles.ListaAlarmaAdaptador;

public class MostrarMedicina extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener, AdapterView.OnItemClickListener {

    TextView descripcion;
    TextView alarmasEtiqueta;
    TextView nombre;
    TextView porcion;

    boolean mostrar = true;

    Medicina medicina;
    ListView listaAlarmas;
    List<Alarma> alarmas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_medicina);

        nombre = (TextView) findViewById(R.id.mm_nombre);
        porcion = (TextView) findViewById(R.id.mm_porcion);
        descripcion = (TextView) findViewById(R.id.mm_descripcion);
        alarmasEtiqueta = (TextView) findViewById(R.id.mm_texto_alarmas);
        listaAlarmas = (ListView) findViewById(R.id.mm_lista_alarmas);
        descripcion.setOnClickListener(this);
        alarmasEtiqueta.setOnClickListener(this);
        alarmasEtiqueta.setOnTouchListener(this);

        medicina = Medicina.findById(Medicina.class, getIntent().getLongExtra("idMedicina", 0));

        actualizaDatos();
    }

    @Override
    protected void onResume() {
        super.onResume();
        actualizaDatos();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_medicina, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.menu_medicina_ayuda:
                Intent helpActivity = new Intent(this, MostrarAyuda.class);
                startActivity(helpActivity);
                return true;

            case R.id.menu_medicina_nueva_alarma:
                Intent e = new Intent(this, CrearAlarmas.class);
                long id = medicina.getId();
                e.putExtra("idMedicina", id);
                startActivity(e);
                return true;

            case R.id.menu_medicina_editar:
                Intent i = new Intent(this, CrearMedicina.class);
                long id2 = medicina.getId();
                i.putExtra("actualizar", true);
                i.putExtra("idMedicina", id2);
                startActivity(i);
                return true;

            case R.id.menu_medicina_borrar:
                medicina.delete();
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mm_descripcion:
                if (mostrar)
                    descripcion.setText(getBaseContext().getString(R.string.descripcion)+" >");
                else
                    descripcion.setText(medicina.getDescripcion());
                mostrar = !mostrar;
                break;
            case R.id.mm_texto_alarmas:
                descripcion.setText(getBaseContext().getString(R.string.descripcion)+" >");
                mostrar = false;
                break;

        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        descripcion.setText(getBaseContext().getString(R.string.descripcion) + " >");
        mostrar = false;
        return false;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Alarma alarma = alarmas.get(position);
        Intent i = new Intent(this, MostrarAlarma.class);
        i.putExtra("idAlarma", alarma.getId());
        startActivity(i);
    }

    public void actualizaDatos(){
        medicina = Medicina.findById(Medicina.class, medicina.getId());
        nombre.setText(medicina.getNombre());
        porcion.setText(medicina.getStringPorcion(getBaseContext()));
        descripcion.setText(medicina.getDescripcion());
        // Obtiene las alarmas de la medicina
        alarmas = Alarma.find(Alarma.class, "medicina = ?", Long.toString(medicina.getId()));
        ListaAlarmaAdaptador listaAlarmaAdaptador = new ListaAlarmaAdaptador(this, alarmas);
        listaAlarmas.setAdapter(listaAlarmaAdaptador);
        listaAlarmas.setOnItemClickListener(this);
    }
}