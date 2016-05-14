package proyecto1.pdm.fia.ues.sv.valoracionestablecimientos;

import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DepartamentoEliminarActivity extends Activity {

    EditText editIdDepartamento;
    ControlDBValoracionEstablecimientos controlhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departamento_eliminar);

        controlhelper=new ControlDBValoracionEstablecimientos (this);
        editIdDepartamento=(EditText)findViewById(R.id.editIdDepartamento);
    }
    public void eliminarDepartamento(View v){
        String regEliminadas;
        Departamento departamento=new Departamento();
        departamento.setIdDepartamento(Integer.parseInt(editIdDepartamento.getText().toString()));/* tuve que hacer un parse por el tipo de dato que recibe IdDepartamento*/
        controlhelper.abrir();
        regEliminadas=controlhelper.eliminar(departamento);
        /*regEliminadas = String.valueOf(regEliminadas);/*agregue esto para convertirlo a string x el dato*/
        controlhelper.cerrar();                       /*que envia el metodo eliminar de la clase controladora*/
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}
