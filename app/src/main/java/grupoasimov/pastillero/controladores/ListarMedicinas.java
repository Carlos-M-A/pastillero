package grupoasimov.pastillero.controladores;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import grupoasimov.pastillero.R;

import java.util.ArrayList;

public class ListarMedicinas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ArrayList<Lista_entrada> datos = new ArrayList<Lista_entrada>();

        datos.add(new Lista_entrada(R.mipmap.pastilla, "PARACETAMOL", "DOLOR DE CABEZA"));
        datos.add(new Lista_entrada(R.mipmap.pastilla, "PARACETAMOL", "DOLOR DE CABEZA"));
        datos.add(new Lista_entrada(R.mipmap.pastilla, "PARACETAMOL", "DOLOR DE CABEZA"));
        datos.add(new Lista_entrada(R.mipmap.pastilla, "PARACETAMOL", "DOLOR DE CABEZA"));
        datos.add(new Lista_entrada(R.mipmap.pastilla, "PARACETAMOL", "DOLOR DE CABEZA"));
        datos.add(new Lista_entrada(R.mipmap.pastilla, "PARACETAMOL", "DOLOR DE CABEZA"));
        datos.add(new Lista_entrada(R.mipmap.pastilla, "PARACETAMOL", "DOLOR DE CABEZA"));
        datos.add(new Lista_entrada(R.mipmap.pastilla, "PARACETAMOL", "DOLOR DE CABEZA"));
        datos.add(new Lista_entrada(R.mipmap.pastilla, "PARACETAMOL", "DOLOR DE CABEZA"));


        ListView lista = (ListView) findViewById(R.id.listaEntrada);
        lista.setAdapter(new Lista_adaptador(this, R.layout.list_row, datos) {
            @Override
            public void onEntrada(Object entrada, View view) {
                TextView texto_superior_entrada = (TextView) view.findViewById(R.id.texto_superior);
                texto_superior_entrada.setText(((Lista_entrada) entrada).get_textoEncima());

                TextView texto_inferior_entrada = (TextView) view.findViewById(R.id.texto_inferior);
                texto_inferior_entrada.setText(((Lista_entrada) entrada).get_textoDebajo());

                ImageView imagen_entrada = (ImageView) view.findViewById(R.id.imagen_single_post_circuito);
                imagen_entrada.setImageResource(((Lista_entrada) entrada).get_idImagen());


            }
        });
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> pariente, View view, int posicion, long id) {
                Lista_entrada elegido = (Lista_entrada) pariente.getItemAtPosition(posicion);

                CharSequence texto = "Seleccionado: " + elegido.get_textoDebajo();
                Toast toast = Toast.makeText(ListarMedicinas.this, texto, Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mcmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.help:
                Intent helpActivity = new Intent(getApplicationContext(),AyudaActivity.class);
                startActivity(helpActivity);
                return true;
            case R.id.add:
                Intent addActivity = new Intent(getApplicationContext(),AgregarActivity.class);
                startActivity(addActivity);
                return true;
            case R.id.edit:

                return true;
            case R.id.delete:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}