package proyecto1.pdm.fia.ues.sv.valoracionestablecimientos;

import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DepartamentoConsultarActivity extends Activity {

    ControlDBValoracionEstablecimientos helper;
    EditText editIdDepartamento;
    EditText editNombreDepartamento;
    EditText editZona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departamento_consultar);

        helper = new ControlDBValoracionEstablecimientos(this);
        editIdDepartamento = (EditText) findViewById(R.id.editIdDepartamento);
        editNombreDepartamento = (EditText) findViewById(R.id.editNombreDepartamento);
        editZona = (EditText) findViewById(R.id.editZona);
        editIdDepartamento.setText("0");
    }
    public void consultarDepartamento(View v) {
        helper.abrir();
        Departamento departamento = helper.consultarDepartamento(editIdDepartamento.getText().toString());
        helper.cerrar();
        if(departamento == null)
            Toast.makeText(this, "Departamento con IdDepartamento " + editIdDepartamento.getText().toString() +
                    " no encontrado", Toast.LENGTH_LONG).show();
        else{
            editNombreDepartamento.setText(departamento.getNombreDepartamento());
            editZona.setText(departamento.getZona());
        }
    }
    public void limpiarTexto(View v){
        editIdDepartamento.setText("");
        editNombreDepartamento.setText("");
        editZona.setText("");
    }
}

