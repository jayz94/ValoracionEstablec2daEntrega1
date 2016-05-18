package proyecto1.pdm.fia.ues.sv.valoracionestablecimientos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class EstablecActualizarActivity extends Activity {

    ControlDBValoracionEstablecimientos helper;
    EditText idEstablec;
    EditText nombreEstablec;
    EditText direccion;
    EditText telefono;
    Spinner encargadoNit;
    Spinner idMun;
    Spinner idTipoEstablec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_establec_actualizar);
        helper = new ControlDBValoracionEstablecimientos(this);
        idEstablec = (EditText) findViewById(R.id.editId);
        nombreEstablec = (EditText) findViewById(R.id.editNombreEstablec);
        direccion = (EditText) findViewById(R.id.editDireccion);
        telefono = (EditText) findViewById(R.id.editTelefono);
        encargadoNit = (Spinner) findViewById(R.id.editEncargadoNit);
        idMun = (Spinner) findViewById(R.id.editIdMunicipioE);
        idTipoEstablec = (Spinner) findViewById(R.id.editIdTipoEstablecl);
        helper.abrir();
       /*Spinner de tipo estblecimiento*/
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,helper.listaIdTiEstablec());
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        idTipoEstablec.setAdapter(adaptador);
        /*Spinner*/

        /*Spinnerde nit encargado*/
        ArrayAdapter<String> adaptadorNE = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,helper.listaNitEncargados());
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        encargadoNit.setAdapter(adaptadorNE);
        /*Spinner*/

        /*Spinnerde idMunicipio*/
        ArrayAdapter<String> adaptadorM = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,helper.listaMunicipios());
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        idMun.setAdapter(adaptadorM);
        /*Spinner*/
    }

    public void actualizar(View v) {
        Establecimiento establec = new Establecimiento();
        if (idEstablec.getText().toString().equals("")) {
            Toast.makeText(this, "Error, Ingrese Id Establecimiento", Toast.LENGTH_SHORT).show();

        } else {
            int cont=0;
            cont=helper.buscarEstablec(idEstablec.getText().toString());
            if(cont<1){
                Toast.makeText(this, "Error,El Id no Existe", Toast.LENGTH_SHORT).show();
            }
            else{
                establec.setIdEstablec(idEstablec.getText().toString());
                establec.setNombreEstablec(nombreEstablec.getText().toString());
                establec.setDireccion(direccion.getText().toString());
                establec.setTelefono(telefono.getText().toString());
                establec.setEncargadoNit(encargadoNit.getSelectedItem().toString());
                if (idMun.getSelectedItem().toString().equals(""))
                    Toast.makeText(this, "Error, Ingrese Id Municipio", Toast.LENGTH_SHORT).show();
                else {
                    int idMuni = Integer.parseInt(idMun.getSelectedItem().toString());
                    establec.setIdMunicipio(idMuni);
                    establec.setIdTipoEstablec(idTipoEstablec.getSelectedItem().toString());

                    String regActualizados = helper.actualizar(establec);
                    helper.cerrar();
                    Toast.makeText(this, regActualizados, Toast.LENGTH_SHORT).show();
                }
            }

        }

    }

    public void limpiarTexto(View v) {
        idEstablec.setText("");
        nombreEstablec.setText("");
        direccion.setText("");
        telefono.setText("");
        /*encargadoNit.setText("");
        idMun.setText("0");
        idTipoEstablec.setText("");*/
    }

}
