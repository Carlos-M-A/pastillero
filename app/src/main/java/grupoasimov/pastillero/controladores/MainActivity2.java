package grupoasimov.pastillero.controladores;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import grupoasimov.pastillero.R;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);
        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                Intent i = new Intent(this, CrearMedicina.class);
                i.putExtra("actualizar", false);
                i.putExtra("idMedicina", 7);
                startActivity(i);
                break;
            case R.id.button2:
                Intent e = new Intent(this, CrearAlarmas.class);
                e.putExtra("idMedicina", 1);
                startActivity(e);
                break;
            case R.id.button3:
                Intent helpActivity = new Intent(this, ListarMedicinas.class);
                startActivity(helpActivity);
                break;

    }
    }
}
