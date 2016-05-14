package proyecto1.pdm.fia.ues.sv.valoracionestablecimientos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ClienteConsultarActivity extends AppCompatActivity {
    ControlDBValoracionEstablecimientos helper;
    EditText editDui;
    EditText editNombre;
    EditText editApellido;
    EditText editSexo;
    EditText editEdad;
    EditText editCorreo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_consultar);

        helper=new ControlDBValoracionEstablecimientos(this);

        editDui=(EditText)findViewById(R.id.etDui);
        editNombre=(EditText)findViewById(R.id.etNombre);
        editApellido=(EditText)findViewById(R.id.etApellido);
        editSexo=(EditText)findViewById(R.id.etSexo);
        editEdad=(EditText)findViewById(R.id.etEdad);
        editCorreo=(EditText)findViewById(R.id.etCorreo);
    }

    public void consultarCliente(View v){

        helper.abrir();

        Cliente cliente= helper.consultarCliente(editDui.getText().toString());

        helper.cerrar();

        if(cliente==null){
            Toast.makeText(this,"cliente " +editDui.getText().toString() +" no encontrado",Toast.LENGTH_SHORT).show();
        }else {
            editNombre.setText(cliente.getNombres());
            editApellido.setText(cliente.getApellidos());
            editSexo.setText(cliente.getSexo());
            editEdad.setText(String.valueOf(cliente.getEdad()));
            editCorreo.setText(cliente.getCorreo());
        }


    }

    public void limpiar(View v){
        editDui.setText("");
        editNombre.setText("");
        editApellido.setText("");
        editSexo.setText("");
        editEdad.setText("");
        editCorreo.setText("");
    }
}
