package proyecto1.pdm.fia.ues.sv.valoracionestablecimientos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EstablecEliminarActivity extends Activity {
    EditText idEstablec;
    ControlDBValoracionEstablecimientos helper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_establec_eliminar);
        helper = new ControlDBValoracionEstablecimientos(this);
        idEstablec = (EditText) findViewById(R.id.idEstablec);
    }

    public void eliminar(View v) {
        String eliminados;
        Establecimiento establec = new Establecimiento();
        if (idEstablec.getText().toString().equals(""))
            Toast.makeText(this, "Ingrese un Id", Toast.LENGTH_SHORT).show();
        else {
            establec.setIdEstablec(idEstablec.getText().toString());
            helper.abrir();
            eliminados = helper.eliminar(establec);
            helper.cerrar();
            Toast.makeText(this, eliminados, Toast.LENGTH_SHORT).show();
        }
    }

}
