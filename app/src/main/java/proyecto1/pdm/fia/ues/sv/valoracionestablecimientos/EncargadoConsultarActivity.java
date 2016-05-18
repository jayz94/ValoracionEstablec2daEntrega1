package proyecto1.pdm.fia.ues.sv.valoracionestablecimientos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EncargadoConsultarActivity extends AppCompatActivity {
    ControlDBValoracionEstablecimientos helper;

    EditText edNit;
    EditText edNombre;
    EditText edApellido;
    EditText edSexo;
    EditText edEdad;
    EditText edCargo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encargado_consultar);

        helper=new ControlDBValoracionEstablecimientos(this);

        edNit=(EditText)findViewById(R.id.etNitConsulta);
        edNombre=(EditText)findViewById(R.id.etNombreEncargadoConsulta);
        edApellido=(EditText)findViewById(R.id.etApellidoEncargadoConsulta);
        edSexo=(EditText)findViewById(R.id.etSexoEncargadoConsulta);
        edEdad=(EditText)findViewById(R.id.etEdadEncargadoConsulta);
        edCargo=(EditText)findViewById(R.id.etCargoEncargadoConsulta);


    }

    public void consulEncargado(View v){
        String nitConsulta=edNit.getText().toString();

        helper.abrir();
        Encargado encargado=new Encargado();
        encargado=helper.consultarEncargado(nitConsulta);
        helper.cerrar();
        if(encargado==null){
            Toast.makeText(this,"ERROR EN CONSULTA",Toast.LENGTH_SHORT).show();
        }else {
            edNit.setText(encargado.getNit());
            edNombre.setText(encargado.getNombre());
            edApellido.setText(encargado.getApellido());
            edSexo.setText(encargado.getSexo());
            edEdad.setText(String.valueOf(encargado.getEdad()));
            edCargo.setText(encargado.getCargo());

        }

    }

    public void limpiar(View v){
        edNit.setText("");
        edNombre.setText("");
        edApellido.setText("");
        edSexo.setText("");
        edEdad.setText("");
        edCargo.setText("");

    }
}