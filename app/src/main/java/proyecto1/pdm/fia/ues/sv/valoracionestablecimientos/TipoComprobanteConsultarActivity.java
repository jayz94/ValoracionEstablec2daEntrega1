package proyecto1.pdm.fia.ues.sv.valoracionestablecimientos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TipoComprobanteConsultarActivity extends Activity {

    ControlDBValoracionEstablecimientos helper;
    EditText editIdTipoComprobante;
    EditText editTipoComprobante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_comprobante_consultar);
        helper =new ControlDBValoracionEstablecimientos(this);

        editIdTipoComprobante = (EditText) findViewById(R.id.editIdTipoComprobante_c);
        editTipoComprobante = (EditText) findViewById(R.id.editTipoComprobante_c);
    }
    public void consultarTipoComprobante(View v){
        String id = editIdTipoComprobante.getText().toString();

        if (id.equals("")){
            Toast.makeText(this,"Error, debe digitar id",Toast.LENGTH_SHORT).show();
        }else{//Busca por id
            helper.abrir();
            TipoComprobante tipocomprobante= helper.consultarTipoComprobante(id);
            helper.cerrar();
            if(tipocomprobante == null)
                Toast.makeText(this, "Tipo de Comprobante con id " + id +" no encontrado", Toast.LENGTH_LONG).show();
            else {
                editTipoComprobante.setText("" + tipocomprobante.getTipoComprobante());
                editIdTipoComprobante.setText("" + tipocomprobante.getIdTipoComprobante());

            }
        }

    }

    public void limpiarTexto(View v){
        editIdTipoComprobante.setText("");
        editTipoComprobante.setText("");
    }
}
