package proyecto1.pdm.fia.ues.sv.valoracionestablecimientos;

import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MunicipioEliminarActivity extends Activity {

    EditText editIdMunicipio;
    ControlDBValoracionEstablecimientos controlhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipio_eliminar);

        controlhelper=new ControlDBValoracionEstablecimientos(this);
        editIdMunicipio=(EditText)findViewById(R.id.editIdMunicipio);
        /*editCodmateria=(EditText)findViewById(R.id.editCodmateria);
        editCiclo=(EditText)findViewById(R.id.editCiclo);*/
    }
    public void eliminarMunicipio(View v){
        String regEliminadas;
        Municipio municipio=new Municipio();
        municipio.setIdMunicipio(Integer.parseInt(editIdMunicipio.getText().toString()));/*tuve que hacer un parse*/
        /*municipio.setCodmateria(editCodmateria.getText().toString());
        municipio.setCiclo(editCiclo.getText().toString());*/
        controlhelper.abrir();
        regEliminadas=controlhelper.eliminar(municipio);
        controlhelper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
    public void limpiar(View v){
        editIdMunicipio.setText("");
        /*editCodmateria.setText("");
        editCiclo.setText("");*/
    }
}
