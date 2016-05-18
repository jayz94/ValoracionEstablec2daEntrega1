package proyecto1.pdm.fia.ues.sv.valoracionestablecimientos;

import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MunicipioConsultarActivity extends Activity {

    ControlDBValoracionEstablecimientos helper;
    EditText editIdMunicipio;
    EditText editIdDepartamento;
    EditText editNombreMunicipio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipio_consultar);

        helper = new ControlDBValoracionEstablecimientos(this);
        editIdMunicipio = (EditText) findViewById(R.id.editIdMunicipio);
        editIdDepartamento = (EditText) findViewById(R.id.editIdDepartamento);
        editNombreMunicipio = (EditText) findViewById(R.id.editNombreMunicipio);
        editIdMunicipio.setText("0");
    }
    public void consultarMunicipio(View v) {
        helper.abrir();
        Municipio municipio = helper.consultarMunicipio(Integer.parseInt(editIdMunicipio.getText().toString()));/*tuve que hacer parse*/
        helper.cerrar();
        if(municipio == null)
            Toast.makeText(this, "Municipio no registrado", Toast.LENGTH_LONG).show();
        else{
            editIdDepartamento.setText(String.valueOf(municipio.getIdDepartamento()));
            editNombreMunicipio.setText(String.valueOf(municipio.getNombreMunicipio()));
        }
    }
    public void limpiarTexto(View v) {
        editIdMunicipio.setText("0");
        editIdDepartamento.setText("0");
        editNombreMunicipio.setText("");
    }
}
