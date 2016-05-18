package proyecto1.pdm.fia.ues.sv.valoracionestablecimientos;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TipoComprobanteInsertarActivity extends Activity {

    ControlDBValoracionEstablecimientos helper;
    EditText editIdTipoComprobante;
    EditText editTipoComprobante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_comprobante_insertar);
        helper=new ControlDBValoracionEstablecimientos(this);
        editIdTipoComprobante = (EditText) findViewById(R.id.editIdTipoComprobanteI);
        editTipoComprobante = (EditText) findViewById(R.id.editTipoComprobanteI);
    }

    public void insertarTipoComprobante(View v){
        int idtipocompro = Integer.parseInt(editIdTipoComprobante.getText().toString());
        String tipocompro  = editTipoComprobante.getText().toString();
        String regInsertados;
        TipoComprobante tipoComprobante=new TipoComprobante();
        tipoComprobante.setIdTipoComprobante(idtipocompro);
        tipoComprobante.setTipoComprobante(tipocompro);
        helper.abrir();
        regInsertados=helper.insertar(tipoComprobante);
        helper.cerrar();
        Toast.makeText(this,regInsertados,Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v){
        editIdTipoComprobante.setText("");
        editTipoComprobante.setText("");
    }
}
