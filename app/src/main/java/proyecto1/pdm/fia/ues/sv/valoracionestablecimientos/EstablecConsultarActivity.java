package proyecto1.pdm.fia.ues.sv.valoracionestablecimientos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EstablecConsultarActivity extends Activity {
    ControlDBValoracionEstablecimientos helper;
    EditText idEstablec;
    EditText nombreEstablec;
    EditText direccion;
    EditText telefono;
    EditText encargadoNit;
    EditText idMun;
    EditText idTipoEstablec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_establec_consultar);
        helper = new ControlDBValoracionEstablecimientos(this);
        idEstablec = (EditText) findViewById(R.id.editIdEstablec);
        nombreEstablec = (EditText) findViewById(R.id.editNombreEstablec);
        direccion = (EditText) findViewById(R.id.editDireccionEstablec);
        telefono = (EditText) findViewById(R.id.editTelefono);
        encargadoNit = (EditText) findViewById(R.id.editEncargadoNit);
        idMun = (EditText) findViewById(R.id.editIdMunicipioE);
        idTipoEstablec = (EditText) findViewById(R.id.editIdTipoEstablec);
    }

    public void consultar(View v) {
        helper.abrir();
        Establecimiento establec = helper.consultarEstablecimiento(idEstablec.getText().toString());
        helper.cerrar();
        if (establec == null)
            Toast.makeText(this, "Establecimiento con Id  " + idEstablec.getText().toString() + "no encontrado", Toast.LENGTH_LONG).show();
        else {
            idEstablec.setText(establec.getIdEstablec());
            nombreEstablec.setText(establec.getNombreEstablec());
            direccion.setText(establec.getDireccion());
            telefono.setText(establec.getTelefono());
            encargadoNit.setText(establec.getEncargadoNit());
            idMun.setText(establec.getEncargadoNit());
            idTipoEstablec.setText(establec.getIdTipoEstablec());
        }
    }

    public void limpiarTexto(View v) {
        idEstablec.setText("");
        nombreEstablec.setText("");
        direccion.setText("");
        telefono.setText("");
        encargadoNit.setText("");
        idMun.setText("");
        idTipoEstablec.setText("");
    }
}
