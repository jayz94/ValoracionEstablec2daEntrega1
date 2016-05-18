package proyecto1.pdm.fia.ues.sv.valoracionestablecimientos;


import android.os.Bundle;
import android.app.Activity;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;

public class TipoComprobanteActualizarActivity extends Activity {

    ControlDBValoracionEstablecimientos helper;
    EditText editIdTipoComp;
    EditText editTipoComp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_comprobante_actualizar);
        helper  = new ControlDBValoracionEstablecimientos(this);
        editIdTipoComp = (EditText) findViewById(R.id.editIdTipoComprobanteA);
        editTipoComp = (EditText) findViewById(R.id.editTipoComprobanteA);
    }

    public void actualizarTipoComprobante(View v){
        if (!editIdTipoComp.getText().equals("")) {
            TipoComprobante tipoComprobante = new TipoComprobante();
            tipoComprobante.setIdTipoComprobante(Integer.parseInt(editIdTipoComp.getText().toString()));
            tipoComprobante.setTipoComprobante(editTipoComp.getText().toString());
            helper.abrir();
            String estado = helper.actualizar(tipoComprobante);
            helper.cerrar();
            Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(this, "Inserte id de tipo de comprobante por favor", Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v){
        editIdTipoComp.setText("");
        editTipoComp.setText("");
    }
}

