package proyecto1.pdm.fia.ues.sv.valoracionestablecimientos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ComprobanteActualizarActivity extends Activity {

    ControlDBValoracionEstablecimientos helper;
    EditText editId;
    EditText editNum;
    EditText editFecha;
    EditText editMonto;
    EditText editVendedor;
    EditText editIdTipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprobante_actualizar);
        helper      = new ControlDBValoracionEstablecimientos(this);
        editId      = (EditText) findViewById(R.id.editIdComprobanteA);
        editNum     = (EditText) findViewById(R.id.editNumComprobanteA);
        editFecha   = (EditText) findViewById(R.id.editFechaComprobanteA);
        editMonto   = (EditText) findViewById(R.id.editMontoA);
        editVendedor = (EditText) findViewById(R.id.editVendedorA);
        editIdTipo  = (EditText) findViewById(R.id.editIdTipoComprobanteA);
    }

    public void actualizarComprobante(View v){
        if (!editId.getText().equals("")) {
            Comprobante comprobante = new Comprobante();
            comprobante.setIdComprobante(Integer.parseInt(editId.getText().toString()));
            comprobante.setNumComprobante(Integer.parseInt(editNum.getText().toString()));
            comprobante.setFechaComprobante(editFecha.getText().toString());
            comprobante.setMonto(Integer.parseInt(editMonto.getText().toString()));
            comprobante.setVendedor(editVendedor.getText().toString());
            comprobante.setIdTipoComprobante(Integer.parseInt(editIdTipo.getText().toString()));

            helper.abrir();
            String estado = helper.actualizar(comprobante);
            helper.cerrar();
            Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(this, "Inserte id de comprobante por favor", Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v){
        editId.setText("");
        editNum.setText("");
        editFecha.setText("");
        editMonto.setText("");
        editVendedor.setText("");
        editIdTipo.setText("");
    }
}
