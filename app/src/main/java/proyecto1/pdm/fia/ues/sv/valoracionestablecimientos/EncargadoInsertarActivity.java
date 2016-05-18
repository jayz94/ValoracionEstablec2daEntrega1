package proyecto1.pdm.fia.ues.sv.valoracionestablecimientos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class EncargadoInsertarActivity extends AppCompatActivity {
    ControlDBValoracionEstablecimientos helper;

    EditText editTextNit;
    EditText editTextNombre;
    EditText editTextApellido;
    String sexo;
    RadioGroup radioGroupEncargado;
    EditText editTextEdad;
    EditText editTextCargo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encargado_insertar);

        helper= new ControlDBValoracionEstablecimientos(this);

        editTextNit=(EditText)findViewById(R.id.etNitEncargado);
        editTextNombre=(EditText)findViewById(R.id.etNombreEncargado);
        editTextApellido=(EditText)findViewById(R.id.etApellidoEncargado);
        radioGroupEncargado=(RadioGroup)findViewById(R.id.radioGroupEncargado);
        editTextEdad=(EditText)findViewById(R.id.etEdadEncargado);
        editTextCargo=(EditText)findViewById(R.id.etCargoEncargado);


        radioGroupEncargado.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){

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

    public void insertEncargado(View v){
        String registroInsetados;
        Encargado encargado= new Encargado();

        encargado.setNit(editTextNit.getText().toString());
        encargado.setNombre(editTextNombre.getText().toString());
        encargado.setApellido(editTextApellido.getText().toString());
        encargado.setSexo(sexo);
        encargado.setEdad(Integer.valueOf(editTextEdad.getText().toString()));
        encargado.setCargo(editTextCargo.getText().toString());

        helper.abrir();
        registroInsetados=helper.insertarEncargado(encargado);
        helper.cerrar();
        Toast.makeText(this,registroInsetados,Toast.LENGTH_SHORT).show();
    }
    public void limpiar(View v){
        editTextNit.setText(" ");
        editTextNombre.setText(" ");
        editTextApellido.setText(" ");

        editTextEdad.setText(" ");
        editTextCargo.setText(" ");

    }
}