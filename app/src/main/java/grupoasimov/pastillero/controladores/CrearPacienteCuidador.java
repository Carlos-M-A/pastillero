package grupoasimov.pastillero.controladores;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import grupoasimov.pastillero.modelo.Cuidador;
import grupoasimov.pastillero.modelo.Paciente;
import grupoasimov.pastillero.R;

public class CrearPacienteCuidador extends AppCompatActivity implements View.OnClickListener {

    EditText nombrePaciente;
    EditText apellidosPaciente;
    EditText direccionPaciente;
    EditText telefonoPaciente;
    EditText emailPaciente;
    EditText nombreCuidador;
    EditText apellidosCuidador;
    EditText direccionCuidador;
    EditText telefonoCuidador;
    EditText emailCuidador;
    Button guardar;

    Paciente paciente;
    Cuidador cuidador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_paciente_cuidador);

        nombrePaciente = (EditText) findViewById(R.id.cpc_nombre_paciente);
        apellidosPaciente = (EditText) findViewById(R.id.cpc_apellidos_paciente);
        direccionPaciente  = (EditText) findViewById(R.id.cpc_direccion_paciente);
        telefonoPaciente  = (EditText) findViewById(R.id.cpc_telefono_paciente);
        emailPaciente   = (EditText) findViewById(R.id.cpc_email_paciente);
        nombreCuidador  = (EditText) findViewById(R.id.cpc_nombre_cuidador);
        apellidosCuidador  = (EditText) findViewById(R.id.cpc_apellidos_cuidador);
        direccionCuidador  = (EditText) findViewById(R.id.cpc_direccion_cuidador);
        telefonoCuidador  = (EditText) findViewById(R.id.cpc_telefono_cuidador);
        emailCuidador  = (EditText) findViewById(R.id.cpc_email_cuidador);
        guardar = (Button) findViewById(R.id.cpc_guardar);

        guardar.setOnClickListener(this);

        if(Paciente.listAll(Paciente.class).size()==0) {
            paciente = new Paciente();
            cuidador = new Cuidador();
        } else {
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

    @Override
    public void onClick(View v) {
        paciente.setNombre(nombrePaciente.getText().toString());
        paciente.setApellidos(apellidosPaciente.getText().toString());
        paciente.setDireccion(direccionPaciente.getText().toString());
        paciente.setTelefono(telefonoPaciente.getText().toString());
        paciente.setEmail(emailPaciente.getText().toString());
        cuidador.setNombre(nombreCuidador.getText().toString());
        cuidador.setApellidos(apellidosCuidador.getText().toString());
        cuidador.setDireccion(direccionCuidador.getText().toString());
        cuidador.setTelefono(telefonoCuidador.getText().toString());
        cuidador.setEmail(emailCuidador.getText().toString());

        paciente.save();
        cuidador.save();

        finish();
    }
}
