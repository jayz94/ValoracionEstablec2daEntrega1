package proyecto1.pdm.fia.ues.sv.valoracionestablecimientos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TiEsConsultarActivity extends Activity {
    ControlDBValoracionEstablecimientos helper;
    EditText editTiEstablec;
    EditText editIdTiEstablec;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ti_es_consultar);
        helper = new ControlDBValoracionEstablecimientos(this);
        editIdTiEstablec = (EditText) findViewById(R.id.editIdTiEstablec);
        editTiEstablec = (EditText) findViewById(R.id.editTiEstablec);
    }

    public void consultarTiEstablec(View v) {
        helper.abrir();
        TipoEstablecimiento tipoEstablec = helper.consultarTipoEstablecimiento(editIdTiEstablec.getText().toString());
        helper.cerrar();
        if (tipoEstablec == null)
            Toast.makeText(this, "Tipo Establecimiento con ID " + editIdTiEstablec.getText().toString() + "no encontrado", Toast.LENGTH_LONG).show();
        else {
            editIdTiEstablec.setText(tipoEstablec.getIdTiestablec());
            editTiEstablec.setText(tipoEstablec.getTipoEstablec());
        }
    }

    public void limpiarTexto(View v) {
        editIdTiEstablec.setText("");
        editTiEstablec.setText("");
    }
}
