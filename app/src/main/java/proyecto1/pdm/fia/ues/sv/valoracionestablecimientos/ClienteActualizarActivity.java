package proyecto1.pdm.fia.ues.sv.valoracionestablecimientos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ClienteActualizarActivity extends AppCompatActivity {

    EditText dui;
    EditText nombre;
    EditText apellido;
    EditText sexo;
    EditText edad;
    EditText correo;
    ControlDBValoracionEstablecimientos helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_actualizar);
        helper=new ControlDBValoracionEstablecimientos(this);
        dui=(EditText)findViewById(R.id.editTextDui);
        nombre=(EditText)findViewById(R.id.editTextNombre);
        apellido=(EditText)findViewById(R.id.editTextApellido);
        sexo=(EditText)findViewById(R.id.editTextSexo);
        edad=(EditText)findViewById(R.id.editTextEdad);
        edad.setText("0");
        correo=(EditText)findViewById(R.id.editTextCorreo);


    }

    public void acCliente (View v){
        if(dui.getText().toString().equals(""))
            Toast.makeText(this,"Debe Ingresar el DUI",Toast.LENGTH_SHORT).show();
        else {
            Cliente cliente = new Cliente();
            String Dui = dui.getText().toString();
            String Nombre = nombre.getText().toString();
            String Apellido = apellido.getText().toString();
            String Sexo = sexo.getText().toString();
            Integer Edad = Integer.valueOf(edad.getText().toString());
            String Correo = correo.getText().toString();
            cliente.setDui(Dui);
            cliente.setNombres(Nombre);
            cliente.setApellidos(Apellido);
            cliente.setSexo(Sexo);
            cliente.setEdad(Edad);
            cliente.setCorreo(Correo);

            helper.abrir();
            String estado = helper.actualizarCliente(cliente);
            helper.cerrar();

            Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();

        }
    }

    public void limpiar(View v){
        dui.setText("");
        nombre.setText("");
        apellido.setText("");
        sexo.setText("");
        edad.setText("");
        correo.setText("");
    }
}
