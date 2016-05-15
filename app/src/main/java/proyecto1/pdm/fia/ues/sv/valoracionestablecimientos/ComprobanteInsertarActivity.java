package proyecto1.pdm.fia.ues.sv.valoracionestablecimientos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ComprobanteInsertarActivity extends Activity {

    ControlDBValoracionEstablecimientos helper;
    EditText editNumComprobante;
    EditText editIdComprobante;
    EditText editFecha;
    EditText editMonto;
    EditText editVendedor;
    EditText editIdTipoComprobante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprobante_insertar);
        helper=new ControlDBValoracionEstablecimientos(this);
        editNumComprobante = (EditText) findViewById(R.id.editNumComprobante);
        editIdComprobante  = (EditText) findViewById(R.id.editIdComprobante);
        editFecha          = (EditText) findViewById(R.id.editFechaComprobante);
        editMonto          = (EditText) findViewById(R.id.editMonto);
        editVendedor       = (EditText) findViewById(R.id.editVendedor);
        editIdTipoComprobante = (EditText) findViewById(R.id.editTipoComprobanteFK);
    }

    public void insertarComprobante(View v){
        int numcompro = Integer.parseInt(editNumComprobante.getText().toString());
        int idcompro  = Integer.parseInt(editIdComprobante.getText().toString());
        String fecha  = editFecha.getText().toString();
        int monto     = Integer.parseInt(editMonto.getText().toString());
        String vende  = editVendedor.getText().toString();
        int idTipo    = Integer.parseInt(editIdTipoComprobante.getText().toString());
        String regInsertados;
            Comprobante comprobante=new Comprobante();
            comprobante.setNumComprobante(numcompro);
            comprobante.setIdComprobante(idcompro);
            comprobante.setFechaComprobante(fecha);
            comprobante.setMonto(monto);
            comprobante.setVendedor(vende);
            comprobante.setIdTipoComprobante(idTipo);
            helper.abrir();
            regInsertados=helper.insertar(comprobante);
            helper.cerrar();
            Toast.makeText(this,regInsertados,Toast.LENGTH_SHORT).show();

    }

    public void limpiarTexto(View v){
        editNumComprobante.setText("");
        editIdComprobante.setText("");
        editFecha.setText("");
        editMonto.setText("");
        editVendedor.setText("");
        editIdTipoComprobante.setText("");
    }
}
