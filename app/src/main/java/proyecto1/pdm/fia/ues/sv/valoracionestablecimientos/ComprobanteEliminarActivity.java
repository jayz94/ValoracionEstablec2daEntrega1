package proyecto1.pdm.fia.ues.sv.valoracionestablecimientos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ComprobanteEliminarActivity extends Activity {

    ControlDBValoracionEstablecimientos helper;
    EditText editIdComprobante;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprobante_eliminar);
        helper = new ControlDBValoracionEstablecimientos(this);
        editIdComprobante = (EditText) findViewById(R.id.editIdComprobanteE);
    }

    public void eliminarComprobante(View v){
        String regEliminados;
        Comprobante comprobante=new Comprobante();
        comprobante.setIdComprobante(Integer.parseInt(editIdComprobante.getText().toString()));
        helper.abrir();
        regEliminados=helper.eliminar(comprobante);
        helper.cerrar();
        Toast.makeText(this, regEliminados, Toast.LENGTH_SHORT).show();
    }


}
