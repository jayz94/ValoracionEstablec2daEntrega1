package proyecto1.pdm.fia.ues.sv.valoracionestablecimientos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class EstablecInsertarActivity extends Activity {
    ControlDBValoracionEstablecimientos helper;
    EditText idEstablec;
    EditText nombreEstablec;
    EditText direccion;
    EditText telefono;
    Spinner encargadoNit;
    Spinner idMun;
    Spinner idTipoEstablec;
    TextView ultimoEstablec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_establec_insertar);
        helper = new ControlDBValoracionEstablecimientos(this);
        idEstablec = (EditText) findViewById(R.id.editId);
        nombreEstablec = (EditText) findViewById(R.id.editNombreEstablec);
        direccion = (EditText) findViewById(R.id.editDireccion);
        telefono = (EditText) findViewById(R.id.editTelefono);
        encargadoNit = (Spinner) findViewById(R.id.editEncargadoNit);
        idMun = (Spinner) findViewById(R.id.editIdMunicipioE);
        idTipoEstablec = (Spinner) findViewById(R.id.spinnerTipoEstablec);
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
        ultimoEstablec = (TextView) findViewById(R.id.viewUltimoEstablec);
        ultimoEstablec.setText("Ultimo ID " + helper.ultimoRegistroE());

    }

    public void insertarEstablec(View v) {
        Establecimiento establec = new Establecimiento();
        establec.setIdEstablec(idEstablec.getText().toString());
        establec.setNombreEstablec(nombreEstablec.getText().toString());
        establec.setDireccion(direccion.getText().toString());
        establec.setTelefono(telefono.getText().toString());/*String selec=spinner1.getSelectedItem().toString();*/
        //erificarIntegridad(String encargadoNit, String idTipoEstablec, int idMun)
        String idEs=null;//=helper.verificarIntegridad(encargadoNit.getSelectedItem().toString(),idTipoEstablec.getSelectedItem().toString(),Integer.parseInt(idMun.getSelectedItem().toString()));
        if(idEs==null) {
            establec.setEncargadoNit(encargadoNit.getSelectedItem().toString());
            if (idMun.getSelectedItem().toString().equals(""))
                Toast.makeText(this, "Error, Ingrese Id Municipio", Toast.LENGTH_SHORT).show();
            else {
                int idMuni = Integer.parseInt(idMun.getSelectedItem().toString());
                establec.setIdMunicipio(idMuni);
               establec.setIdTipoEstablec(idTipoEstablec.getSelectedItem().toString());
                helper.cerrar();
                helper.abrir();
                String regInsertados = helper.insertar(establec);
                ultimoEstablec.setText("Ultimo ID " + helper.ultimoRegistroE());
                helper.cerrar();
                Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
            }
       }else Toast.makeText(this,idEs, Toast.LENGTH_SHORT).show();
    }


    public void limpiarTexto(View v) {
        idEstablec.setText("");
        nombreEstablec.setText("");
        direccion.setText("");
        telefono.setText("");
       // encargadoNit.setText("");
       // idMun.setText("0");
        //idTipoEstablec.setText("");
    }
}
