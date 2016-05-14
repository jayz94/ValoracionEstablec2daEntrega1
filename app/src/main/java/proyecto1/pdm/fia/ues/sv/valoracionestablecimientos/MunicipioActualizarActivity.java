package proyecto1.pdm.fia.ues.sv.valoracionestablecimientos;

import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MunicipioActualizarActivity extends Activity {

    ControlDBValoracionEstablecimientos helper;
    EditText editIdMunicipio;
    EditText editIdDepartamento;
    EditText editNombreMunicipio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipio_actualizar);

        helper = new ControlDBValoracionEstablecimientos(this);
        editIdMunicipio = (EditText) findViewById(R.id.editIdMunicipio);
        editIdDepartamento = (EditText) findViewById(R.id.editIdDepartamento);
        editNombreMunicipio = (EditText) findViewById(R.id.editNombreMunicipio);
    }
    public void actualizarMunicipio(View v) {
        Municipio municipio = new Municipio();
        municipio.setIdMunicipio(Integer.parseInt(editIdMunicipio.getText().toString()));/* tuve  que hacer parse*/
        municipio.setIdDepartamento(Integer.parseInt(editIdDepartamento.getText().toString()));/*tuve que hacer parse*/
        municipio.setNombreMunicipio(editNombreMunicipio.getText().toString());

        helper.abrir();
        String estado = helper.actualizar(municipio);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editIdMunicipio.setText("");
        editIdDepartamento.setText("");
        editNombreMunicipio.setText("");
    }
}
