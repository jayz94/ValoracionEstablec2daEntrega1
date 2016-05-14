package proyecto1.pdm.fia.ues.sv.valoracionestablecimientos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EstablecActualizarActivity extends Activity {

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
        setContentView(R.layout.activity_establec_actualizar);
        helper = new ControlDBValoracionEstablecimientos(this);
        idEstablec = (EditText) findViewById(R.id.editId);
        nombreEstablec = (EditText) findViewById(R.id.editNombreEstablec);
        direccion = (EditText) findViewById(R.id.editDireccion);
        telefono = (EditText) findViewById(R.id.editTelefono);
        encargadoNit = (EditText) findViewById(R.id.editEncargadoNit);
        idMun = (EditText) findViewById(R.id.editIdMunicipioE);
        idTipoEstablec = (EditText) findViewById(R.id.editIdTipoEstablecl);
    }

    public void actualizar(View v) {
        Establecimiento establec = new Establecimiento();
        if (idEstablec.getText().toString().equals("")) {
            Toast.makeText(this, "Error, Ingrese Id Establecimiento", Toast.LENGTH_SHORT).show();

        } else {
            establec.setIdEstablec(idEstablec.getText().toString());
            establec.setNombreEstablec(nombreEstablec.getText().toString());
            establec.setDireccion(direccion.getText().toString());
            establec.setTelefono(telefono.getText().toString());
            establec.setEncargadoNit(encargadoNit.getText().toString());
            if (idMun.getText().toString().equals(""))
                Toast.makeText(this, "Error, Ingrese Id Municipio", Toast.LENGTH_SHORT).show();
            else {
                int idMuni = Integer.parseInt(idMun.getText().toString());
                establec.setIdMunicipio(idMuni);
                establec.setIdTipoEstablec(idTipoEstablec.getText().toString());
                helper.abrir();
                String regActualizados = helper.actualizar(establec);
                helper.cerrar();
                Toast.makeText(this, regActualizados, Toast.LENGTH_SHORT).show();
            }
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
