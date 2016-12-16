package grupoasimov.pastillero.controladores;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import grupoasimov.pastillero.modelo.Medicina;
import grupoasimov.pastillero.R;

public class CrearMedicina extends AppCompatActivity implements View.OnClickListener {

    EditText nombreMedicina;
    EditText descripcionMedicina;
    EditText porcionMedicina;
    ImageButton imagenMedicina;
    Button guardarMedicina;

    Medicina medicina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_medicina);

        nombreMedicina = (EditText) findViewById(R.id.cm_nombre);
        descripcionMedicina = (EditText) findViewById(R.id.cm_descripcion);
        porcionMedicina = (EditText) findViewById(R.id.cm_porcion);
        imagenMedicina = (ImageButton) findViewById(R.id.cm_imagen);
        guardarMedicina = (Button) findViewById(R.id.cm_guardar);

        guardarMedicina.setOnClickListener(this);

        if(getIntent().getBooleanExtra("actualizar", true)==true){
            medicina = Medicina.findById(Medicina.class, getIntent().getLongExtra("idMedicina", 0));
            nombreMedicina.setText(medicina.getNombre());
            descripcionMedicina .setText(medicina.getDescripcion());
            porcionMedicina.setText(String.valueOf(medicina.getCantidadPorcion()));

        }else {
            medicina = new Medicina();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.cm_guardar:
                medicina.setNombre(nombreMedicina.getText().toString());
                medicina.setDescripcion(descripcionMedicina.getText().toString());
                medicina.setCantidadPorcion(Integer.parseInt(porcionMedicina.getText().toString()));
                medicina.setUrlImagen("url");
                medicina.save();

                finish();
                break;
        }
    }
}
