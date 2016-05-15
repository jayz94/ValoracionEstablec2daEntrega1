package proyecto1.pdm.fia.ues.sv.valoracionestablecimientos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ClienteInsertarActivity extends AppCompatActivity implements View.OnClickListener{
    ControlDBValoracionEstablecimientos helper;
    EditText dui;
    EditText nombre;
    EditText apellido;
    RadioGroup radioGroupSexo;
    EditText edad;
    EditText correo;
    String sexo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_insertar);

        helper=new ControlDBValoracionEstablecimientos(this);

        dui=(EditText)findViewById(R.id.editTextDui);
        nombre=(EditText)findViewById(R.id.editTextNombre);
        apellido=(EditText)findViewById(R.id.editTextApellido);

        radioGroupSexo=(RadioGroup)findViewById(R.id.radioGroupCliente);
        edad=(EditText)findViewById(R.id.editTextEdad);
        edad.setText("0");
        correo=(EditText)findViewById(R.id.editTextCorreo);

        radioGroupSexo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.radioButton){
                    sexo="M";

                }else if(checkedId==R.id.radioButton2){
                    sexo="F";
                }
            }
        });


    }



    public void insertarCliente(View v){
        if(dui.getText().toString().equals(""))
            Toast.makeText(this,"Debe Ingresar el DUI",Toast.LENGTH_SHORT).show();
        else {
            String regInsertados;

            String Dui = dui.getText().toString();
            String Nombre = nombre.getText().toString();
            String Apellido = apellido.getText().toString();

            Integer Edad = Integer.valueOf(edad.getText().toString());
            String Correo = correo.getText().toString();


            Cliente cliente = new Cliente();
            cliente.setDui(Dui);
            cliente.setNombres(Nombre);
            cliente.setApellidos(Apellido);
            cliente.setSexo(sexo);
            cliente.setEdad(Edad);
            cliente.setCorreo(Correo);
            helper.abrir();
            regInsertados = helper.insertar(cliente);
            helper.cerrar();
            Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
        }


    }
    public void limpiar(View v){
        dui.setText("");
        nombre.setText("");
        apellido.setText("");

        correo.setText("");
    }

    @Override
    public void onClick(View v) {

    }
}
