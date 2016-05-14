package proyecto1.pdm.fia.ues.sv.valoracionestablecimientos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class TiEsEliminarActivity extends Activity {
    EditText editIdTiEstablec;
    ControlDBValoracionEstablecimientos controlHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ti_es_eliminar);
        controlHelper = new ControlDBValoracionEstablecimientos(this);
        editIdTiEstablec = (EditText) findViewById(R.id.editIdTiEstablec);
    }

    public void eliminar(View v) {
        String regEli;
        TipoEstablecimiento tiEstablec = new TipoEstablecimiento();
        if (editIdTiEstablec.getText().toString().equals(""))
            Toast.makeText(this, "Ingrese un Id", Toast.LENGTH_SHORT).show();
        else {
            tiEstablec.setIdTiestablec(editIdTiEstablec.getText().toString());
            controlHelper.abrir();
            regEli = controlHelper.eliminar(tiEstablec);
            controlHelper.cerrar();
            Toast.makeText(this, regEli, Toast.LENGTH_SHORT).show();
        }
    }
}
