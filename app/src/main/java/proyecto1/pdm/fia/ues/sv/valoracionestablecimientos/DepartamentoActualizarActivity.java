package proyecto1.pdm.fia.ues.sv.valoracionestablecimientos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DepartamentoActualizarActivity extends Activity {

    ControlDBValoracionEstablecimientos helper;
    EditText editIdDepartamento;
    EditText editNombreDepartamento;
    EditText editZona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departamento_actualizar);

        helper = new ControlDBValoracionEstablecimientos(this);
        editIdDepartamento = (EditText) findViewById(R.id.editIdDepartamento);
        editNombreDepartamento = (EditText) findViewById(R.id.editNombreDepartamento);
        editZona = (EditText) findViewById(R.id.editZona);
    }
    public void actualizarDepartamento(View v) {
        Departamento departamento = new Departamento();
        departamento.setIdDepartamento(Integer.parseInt(editIdDepartamento.getText().toString()));/*tuve que hacer un parse int por el tipo de dato que recibe IdDepartamento*/
        departamento.setNombreDepartamento(editNombreDepartamento.getText().toString());
        departamento.setZona(editZona.getText().toString());

        helper.abrir();
        String estado = helper.actualizar(departamento);
        helper.cerrar();
        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editIdDepartamento.setText("");
        editNombreDepartamento.setText("");
        editZona.setText("");

    }
}
