package grupoasimov.pastillero.controladores;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class MostrarMedicina extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener,  AdapterView.OnItemClickListener {

    TextView descripcion;
    TextView alarmasEtiqueta;
    boolean mostrar = true;

    Medicina medicina;
    ListView listaAlarmas;
    List<Alarma> alarmas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_medicina);

        //Insertamos nombre de la medicina
        medicina = Medicina.findById(Medicina.class, getIntent().getLongExtra("idMedicina", 0));

        TextView nombre = (TextView) findViewById(R.id.nombreMedicinaM);
        TextView porcion = (TextView) findViewById(R.id.porcionMedicinaM);
        descripcion = (TextView) findViewById(R.id.descripcionMedicinaM);
        descripcion.setOnClickListener(this);
        alarmasEtiqueta = (TextView) findViewById(R.id.alarmaMedicinaM);
        alarmasEtiqueta.setOnClickListener(this);
        alarmasEtiqueta.setOnTouchListener(this);


        listaAlarmas = (ListView) findViewById(R.id.lista_alarmas) ;

        nombre.setText(medicina.getNombre());
        porcion.setText(Integer.toString(medicina.getCantidadPorcion()) + " mg");
        descripcion.setText(medicina.getDescripcion());

        // Obtiene las alarmas de la medicina
        alarmas = Alarma.find(Alarma.class, "medicina = ?", Long.toString(medicina.getId()));

        ListaAlarmaAdaptador listaAlarmaAdaptador = new ListaAlarmaAdaptador(this, alarmas);

        listaAlarmas.setAdapter(listaAlarmaAdaptador);
        listaAlarmas.setOnItemClickListener(this);
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
            case R.id.helpMedicina:
                Intent helpActivity = new Intent(this, MostrarAyuda.class);
                startActivity(helpActivity);
                return true;
            case R.id.addAlarma:
                Intent e = new Intent(this, CrearAlarmas.class);
                long id = medicina.getId();
                e.putExtra("idMedicina", id);
                startActivity(e);
                return true;
            case R.id.editMedicina:
                Intent i = new Intent(this, CrearMedicina.class);
                long id2 = medicina.getId();
                i.putExtra("actualizar", true);
                i.putExtra("idMedicina", id2);
                startActivity(i);

                return true;
            case R.id.deleteMedicina:
                medicina.delete();
                Intent i2 = new Intent(this, ListarMedicinas.class);
                startActivity(i2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.descripcionMedicinaM:
                if(mostrar)
                    descripcion.setText("Descripcion >");
                else
                    descripcion.setText(medicina.getDescripcion());
                mostrar = !mostrar;
                break;
            case R.id.alarmaMedicinaM:
                descripcion.setText("Descripcion >");
                mostrar = false;
                break;

        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        descripcion.setText("Descripcion >");
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
}
