package proyecto1.pdm.fia.ues.sv.valoracionestablecimientos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ClienteEliminarActivity extends AppCompatActivity {
    ControlDBValoracionEstablecimientos helper;

    EditText editDui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_eliminar);

        helper=new ControlDBValoracionEstablecimientos(this);
        editDui=(EditText)findViewById(R.id.editTextDui);
    }

    public void elimCliente(View v){

        String regEliminadas;
        Cliente cliente=new Cliente();
        cliente.setDui(editDui.getText().toString());

        helper.abrir();
        regEliminadas=helper.eliminarCliente(cliente);
        helper.cerrar();
        Toast.makeText(this, regEliminadas,Toast.LENGTH_SHORT).show();
    }
    public void limpiar(View v){
        editDui.setText("");

    }
}
