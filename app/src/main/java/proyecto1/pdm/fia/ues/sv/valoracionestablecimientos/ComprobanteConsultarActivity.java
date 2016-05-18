package proyecto1.pdm.fia.ues.sv.valoracionestablecimientos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ComprobanteConsultarActivity extends Activity {

    ControlDBValoracionEstablecimientos helper;
    EditText editnumComprobante;
    EditText editidComprobante;
    EditText editfechaComprobante;
    EditText editmonto;
    EditText editvendedor;
    EditText editidTipoComprobante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprobante_consultar);
        helper =new ControlDBValoracionEstablecimientos(this);

        editnumComprobante  = (EditText) findViewById(R.id.editNumComprobante_c);
        editidComprobante   = (EditText) findViewById(R.id.editIdComprobante_c);
        editfechaComprobante= (EditText) findViewById(R.id.editFechaComprobante_c);
        editmonto           = (EditText) findViewById(R.id.editMonto_c);
        editvendedor        = (EditText) findViewById(R.id.editVendedor_c);
        editidTipoComprobante = (EditText) findViewById(R.id.editIDTipoComprobante_c);
    }

    public void consultarComprobante(View v){
        String id    = editidComprobante.getText().toString();
        String monto = editmonto.getText().toString();

        if (id.equals("")&&monto.equals("")){
            Toast.makeText(this,"Error, debe digitar id o monto",Toast.LENGTH_SHORT).show();
        }else{
            if (id.equals("")){//busca por monto
                helper.abrir();
                Comprobante comprobante= helper.consultarComprobante(monto,2);
                helper.cerrar();
                if(comprobante == null)
                    Toast.makeText(this, "Comprobante con monto " + monto +" no encontrado", Toast.LENGTH_LONG).show();
                else {
                    editidComprobante.setText(""+comprobante.getIdComprobante());
                    editnumComprobante.setText(""+comprobante.getNumComprobante());
                    editfechaComprobante.setText(comprobante.getFechaComprobante());
                    editvendedor.setText(comprobante.getVendedor());
                    editidTipoComprobante.setText(comprobante.getIdTipoComprobante());

                }
            }else{//Busca por id
                helper.abrir();
                Comprobante comprobante= helper.consultarComprobante(id,1);
                helper.cerrar();
                if(comprobante == null)
                    Toast.makeText(this, "Comprobante con id " + id +" no encontrado", Toast.LENGTH_LONG).show();
                else {
                    editmonto.setText(""+comprobante.getMonto());
                    editnumComprobante.setText(""+comprobante.getNumComprobante());
                    editfechaComprobante.setText(comprobante.getFechaComprobante());
                    editvendedor.setText(comprobante.getVendedor());
                    editidTipoComprobante.setText(""+comprobante.getIdTipoComprobante());
                }
            }
        }
    }

    public void limpiarTexto(View v){
        editnumComprobante.setText("");
        editidComprobante.setText("");
        editfechaComprobante.setText("");
        editmonto.setText("");
        editvendedor.setText("");
        editidTipoComprobante.setText("");
    }
}
