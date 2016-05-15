package proyecto1.pdm.fia.ues.sv.valoracionestablecimientos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TipoComprobanteEliminarActivity extends Activity {

    ControlDBValoracionEstablecimientos helper;
    EditText editIdTipoComprobante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_comprobante_eliminar);
        helper = new ControlDBValoracionEstablecimientos(this);
        editIdTipoComprobante = (EditText) findViewById(R.id.editIdTipoComprobanteE);
    }

    public void eliminarTipoComprobante(View v){
        String regEliminados;
        TipoComprobante tipoComprobante = new TipoComprobante();
        tipoComprobante.setIdTipoComprobante(Integer.parseInt(editIdTipoComprobante.getText().toString()));
        helper.abrir();
        regEliminados=helper.eliminar(tipoComprobante);
        helper.cerrar();
        Toast.makeText(this, regEliminados, Toast.LENGTH_SHORT).show();
    }
}
