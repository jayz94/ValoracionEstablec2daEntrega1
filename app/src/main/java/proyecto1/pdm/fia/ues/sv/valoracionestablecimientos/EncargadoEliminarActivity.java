package proyecto1.pdm.fia.ues.sv.valoracionestablecimientos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EncargadoEliminarActivity extends AppCompatActivity {
    ControlDBValoracionEstablecimientos helper;
    EditText editNit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encargado_eliminar);

        helper=new ControlDBValoracionEstablecimientos(this);
        editNit=(EditText)findViewById(R.id.edNit);
    }

    public void elimEncargado(View v){
        String filasAfectadas;
        Encargado encargado=new Encargado();

        String nitEliminar=editNit.getText().toString();
        encargado.setNit(nitEliminar);
        helper.abrir();
        filasAfectadas=helper.eliminarEncargado(encargado);
        helper.cerrar();
        Toast.makeText(this,filasAfectadas,Toast.LENGTH_SHORT).show();

    }
    public void limpiar(View v){
        editNit.setText("");

    }
}
