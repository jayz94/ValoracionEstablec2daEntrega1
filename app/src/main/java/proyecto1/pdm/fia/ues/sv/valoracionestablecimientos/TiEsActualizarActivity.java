package proyecto1.pdm.fia.ues.sv.valoracionestablecimientos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TiEsActualizarActivity extends Activity {

    ControlDBValoracionEstablecimientos helper;
    EditText editIdTiEstablec;
    EditText editTiEstablec;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ti_es_actualizar);
        helper = new ControlDBValoracionEstablecimientos(this);
        editIdTiEstablec = (EditText) findViewById(R.id.editId);
        editTiEstablec = (EditText) findViewById(R.id.editTipo);
    }

    public void actualizarTiEstablec(View v) {
        String idTiEstablec = editIdTiEstablec.getText().toString();
        String tiEstablec = editTiEstablec.getText().toString();
        String regIns;
        TipoEstablecimiento tipoEsta = new TipoEstablecimiento();
        if (idTiEstablec.equals(""))
            Toast.makeText(this, "Ingrese Id", Toast.LENGTH_SHORT).show();
        else {
            tipoEsta.setIdTiestablec(idTiEstablec);
            tipoEsta.setTipoEstablec(tiEstablec);
            helper.abrir();
            regIns = helper.actualizarTipoEstablecimiento(tipoEsta);
            helper.cerrar();
            Toast.makeText(this, regIns, Toast.LENGTH_SHORT).show();
        }
    }

    public void limpiarTexto(View v) {
        editIdTiEstablec.setText("");
        editTiEstablec.setText("");
    }


}
