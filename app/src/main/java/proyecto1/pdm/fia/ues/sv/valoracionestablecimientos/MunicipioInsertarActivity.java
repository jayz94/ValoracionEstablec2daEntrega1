package proyecto1.pdm.fia.ues.sv.valoracionestablecimientos;

import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MunicipioInsertarActivity extends Activity {

    ControlDBValoracionEstablecimientos helper;
    EditText editIdMunicipio;
    EditText editIdDepartamento;
    EditText editNombreMunicipio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipio_insertar);

        helper = new ControlDBValoracionEstablecimientos(this);
        editIdMunicipio = (EditText) findViewById(R.id.editIdMunicipio);
        editIdDepartamento = (EditText) findViewById(R.id.editIdDepartamento);
        editNombreMunicipio = (EditText) findViewById(R.id.editNombreMunicipio);
    }
    public void insertarMunicipio(View v) {
        String regInsertados;
        String IdMunicipio=editIdMunicipio.getText().toString();
        String IdDepartamento=editIdDepartamento.getText().toString();
        String NombreMunicipio=editNombreMunicipio.getText().toString();

        Municipio municipio = new Municipio();
        municipio.setIdMunicipio(Integer.parseInt(IdMunicipio)); /*tuve que hacer un parse por el tipo de dato*/
        municipio.setIdDepartamento(Integer.parseInt(IdDepartamento)); /*tuve que hacer un parse por el tipo de dato*/
        municipio.setNombreMunicipio(NombreMunicipio);

        helper.abrir();
        regInsertados=helper.insertar(municipio);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editIdMunicipio.setText("");
        editIdDepartamento.setText("");
        editNombreMunicipio.setText("");

    }
}

