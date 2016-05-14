package proyecto1.pdm.fia.ues.sv.valoracionestablecimientos;

import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DepartamentoInsertarActivity extends Activity {

    ControlDBValoracionEstablecimientos helper;
    EditText editIdDepartamento;
    EditText editNombreDepartamento;
    EditText editZona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departamento_insertar);

        helper = new ControlDBValoracionEstablecimientos(this);
        editIdDepartamento = (EditText) findViewById(R.id.editIdDepartamento);
        editNombreDepartamento = (EditText) findViewById(R.id.editNombreDepartamento);
        editZona = (EditText) findViewById(R.id.editZona);
    }
    public void insertarDepartamento(View v) {
        int IdDepartamento=Integer.parseInt(editIdDepartamento.getText().toString()); /*tuve que hacer un parse xq IdDepartamento es entero*/
        String NombreDepartamento=editNombreDepartamento.getText().toString();
        String Zona=editZona.getText().toString();

        String regInsertados;
        Departamento departamento=new Departamento();
        departamento.setIdDepartamento(IdDepartamento);
        departamento.setNombreDepartamento(NombreDepartamento);
        departamento.setZona(Zona);

        helper.abrir();
        regInsertados=helper.insertar(departamento);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v) {
        editIdDepartamento.setText("");
        editNombreDepartamento.setText("");
        editZona.setText("");

    }
}
