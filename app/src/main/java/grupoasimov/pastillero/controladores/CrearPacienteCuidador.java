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
        String nombreP = getString(R.string.nombre);
        String apellidosP = getString(R.string.apellidos);
        String direccionP = getString(R.string.direccion);
        String telefonoP = getString(R.string.telefono);
        String emailP = getString(R.string.email);
        String nombreC = getString(R.string.nombre);
        String apellidosC = getString(R.string.apellidos);
        String direccionC = getString(R.string.direccion);
        String telefonoC = getString(R.string.telefono);
        String emailC = getString(R.string.email);

        if(nombrePaciente.getText().length()!=0)
            nombreP = nombrePaciente.getText().toString();
        if(apellidosPaciente.getText().length()!=0)
            apellidosP = apellidosPaciente.getText().toString();
        if(direccionPaciente.getText().length()!=0)
            direccionP = direccionPaciente.getText().toString();
        if(telefonoPaciente.getText().length()!=0)
            telefonoP = telefonoPaciente.getText().toString();
        if(emailPaciente.getText().length()!=0)
            emailP = emailPaciente.getText().toString();
        if(nombreCuidador.getText().length()!=0)
            nombreC = nombreCuidador.getText().toString();
        if(apellidosCuidador.getText().length()!=0)
            apellidosC = apellidosCuidador.getText().toString();
        if(direccionCuidador.getText().length()!=0)
            direccionC = direccionCuidador.getText().toString();
        if(telefonoCuidador.getText().length()!=0)
            telefonoC = telefonoCuidador.getText().toString();
        if(emailCuidador.getText().length()!=0)
            emailC = emailCuidador.getText().toString();


        paciente.setNombre(nombreP);
        paciente.setApellidos(apellidosP);
        paciente.setDireccion(direccionP);
        paciente.setTelefono(telefonoP);
        paciente.setEmail(emailP);
        cuidador.setNombre(nombreC);
        cuidador.setApellidos(apellidosC);
        cuidador.setDireccion(direccionC);
        cuidador.setTelefono(telefonoC);
        cuidador.setEmail(emailC);


        paciente.save();
        cuidador.save();

        finish();
    }
}
