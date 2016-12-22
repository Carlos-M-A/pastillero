package grupoasimov.pastillero.controladores;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import grupoasimov.pastillero.modelo.Cuidador;
import grupoasimov.pastillero.modelo.Paciente;
import grupoasimov.pastillero.R;

/**
 * Controla la vista del paciente y cuidador.
 * @author Adrián Serrano
 * @author Carlos Martín
 * @author María Varela
 */
public class MostrarPacienteCuidador extends AppCompatActivity {

    TextView nombrePaciente;
    TextView apellidosPaciente;
    TextView direccionPaciente;
    TextView telefonoPaciente;
    TextView emailPaciente;
    TextView nombreCuidador;
    TextView apellidosCuidador;
    TextView direccionCuidador;
    TextView telefonoCuidador;
    TextView emailCuidador;

    Paciente paciente;
    Cuidador cuidador;
    boolean hayDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_paciente_cuidador);

        nombrePaciente = (TextView) findViewById(R.id.mpc_nombre_paciente);
        apellidosPaciente = (TextView) findViewById(R.id.mpc_apellidos_paciente);
        direccionPaciente  = (TextView) findViewById(R.id.mpc_direccion_paciente);
        telefonoPaciente  = (TextView) findViewById(R.id.mpc_telefono_paciente);
        emailPaciente   = (TextView) findViewById(R.id.mpc_email_paciente);
        nombreCuidador  = (TextView) findViewById(R.id.mpc_nombre_cuidador);
        apellidosCuidador  = (TextView) findViewById(R.id.mpc_apellidos_cuidador);
        direccionCuidador  = (TextView) findViewById(R.id.mpc_direccion_cuidador);
        telefonoCuidador  = (TextView) findViewById(R.id.mpc_telefono_cuidador);
        emailCuidador  = (TextView) findViewById(R.id.mpc_email_cuidador);

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
        getMenuInflater().inflate(R.menu.menu_paciente_cuidador, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_paciente_cuidador_editar:
                Intent i = new Intent(this, CrearPacienteCuidador.class);
                startActivity(i);
                return true;
            case R.id.menu_paciente_cuidador_borrar:
                Paciente.deleteAll(Paciente.class);
                Cuidador.deleteAll(Cuidador.class);
                actualizaDatos();
                return true;
            case R.id.menu_paciente_cuidador_ayuda:
                Intent helpActivity = new Intent(this, MostrarAyuda.class);
                startActivity(helpActivity);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Actualiza los datos de la vista.
     */
    public void actualizaDatos(){
        if(Paciente.listAll(Paciente.class).size()==0) {
            hayDatos = false;

            nombrePaciente.setText("");
            apellidosPaciente.setText("");
            direccionPaciente.setText("");
            telefonoPaciente.setText("");
            emailPaciente.setText("");
            nombreCuidador.setText("");
            apellidosCuidador.setText("");
            direccionCuidador.setText("");
            telefonoCuidador.setText("");
            emailCuidador.setText("");
        }
        else{
            hayDatos = true;
            paciente = Paciente.listAll(Paciente.class).get(0);
            cuidador = Cuidador.listAll(Cuidador.class).get(0);

            nombrePaciente.setText(paciente.getNombre());
            apellidosPaciente.setText(paciente.getApellidos());
            direccionPaciente.setText(paciente.getDireccion());
            telefonoPaciente.setText(paciente.getTelefono());
            emailPaciente.setText(paciente.getEmail());
            nombreCuidador.setText(cuidador.getNombre());
            apellidosCuidador.setText(cuidador.getApellidos());
            direccionCuidador.setText(cuidador.getDireccion());
            telefonoCuidador.setText(cuidador.getTelefono());
            emailCuidador.setText(cuidador.getEmail());
        }
    }
}
