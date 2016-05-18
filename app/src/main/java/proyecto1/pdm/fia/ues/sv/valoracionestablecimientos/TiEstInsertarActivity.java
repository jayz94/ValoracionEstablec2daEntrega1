package proyecto1.pdm.fia.ues.sv.valoracionestablecimientos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TiEstInsertarActivity extends Activity {

    ControlDBValoracionEstablecimientos helper;
    EditText editIdTiEstablec;
    EditText editTiEstablec;
    TextView ultimoId;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ti_est_insertar);
        helper = new ControlDBValoracionEstablecimientos(this);
        editIdTiEstablec = (EditText) findViewById(R.id.editIdTiEstablec);
        editTiEstablec = (EditText) findViewById(R.id.editTiEstablec);
        ultimoId = (TextView) findViewById(R.id.ultimoIdl);
        helper.abrir();
        ultimoId.setText("Ultimo ID " + helper.ultimoRegistro());

    }

    public void insertarTiEstablec(View v) {
        String idTiEstablec = editIdTiEstablec.getText().toString();
        String tiEstablec = editTiEstablec.getText().toString();
        String regIns;
        if (idTiEstablec.equals("") || tiEstablec.equals("")) {
            Toast.makeText(this, "No se guardo,Debe rellenar ambos campos", Toast.LENGTH_SHORT).show();
        } else {
            TipoEstablecimiento tipoEsta = new TipoEstablecimiento();
            tipoEsta.setIdTiestablec(idTiEstablec);
            tipoEsta.setTipoEstablec(tiEstablec);
            helper.cerrar();
            helper.abrir();
            regIns = helper.insertar(tipoEsta);
            ultimoId.setText("Ultimo ID " + helper.ultimoRegistro());
            helper.cerrar();
            Toast.makeText(this, regIns, Toast.LENGTH_SHORT).show();

        }
    }

    public void limpiarTexto(View v) {
        editIdTiEstablec.setText("");
        editTiEstablec.setText("");
    }
}
