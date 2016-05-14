package proyecto1.pdm.fia.ues.sv.valoracionestablecimientos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EncargadoActualizarActivity extends AppCompatActivity {
    ControlDBValoracionEstablecimientos helper;

    EditText editTextNit;
    EditText editTextNombre;
    EditText editTextApellido;
    EditText editTextSexo;
    EditText editTextEdad;
    EditText editTextCargo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encargado_actualizar);
        helper= new ControlDBValoracionEstablecimientos(this);

        editTextNit=(EditText)findViewById(R.id.etNitEncargado);
        editTextNombre=(EditText)findViewById(R.id.etNombreEncargado);
        editTextApellido=(EditText)findViewById(R.id.etApellidoEncargado);
        editTextSexo=(EditText)findViewById(R.id.etSexoEncargado);
        editTextEdad=(EditText)findViewById(R.id.etEdadEncargado);
        editTextCargo=(EditText)findViewById(R.id.etCargoEncargado);

    }
    public void actuaEncargado(View v){
        String registroInsetados;
        Encargado encargado= new Encargado();

        encargado.setNit(editTextNit.getText().toString());
        encargado.setNombre(editTextNombre.getText().toString());
        encargado.setApellido(editTextApellido.getText().toString());
        encargado.setSexo(editTextSexo.getText().toString());
        encargado.setEdad(Integer.valueOf(editTextEdad.getText().toString()));
        encargado.setCargo(editTextCargo.getText().toString());

        helper.abrir();
        registroInsetados=helper.actualizarEncargado(encargado);
        helper.cerrar();
        Toast.makeText(this, registroInsetados, Toast.LENGTH_SHORT).show();




    }
}
