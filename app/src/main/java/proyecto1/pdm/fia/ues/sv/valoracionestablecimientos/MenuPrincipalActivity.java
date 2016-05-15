package proyecto1.pdm.fia.ues.sv.valoracionestablecimientos;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MenuPrincipalActivity extends ListActivity {
    String [] menu={"Tabla Cliente","Tabla Valoracion","Tabla TipoValoracion","Tabla Encargado","Tabla Establecimiento","Tabla TipoEstablecimiento","Tabla Comprobante","Tabla TipoComprobante","Tabla Departamento","Tabla Municipio","llenar Base de Datos"};
    String [] activities={"ClienteMenuActivity","ComprobanteMenuActivity","TipoComprobanteMenuActivity","EncargadoMenuActivity","EstablecMenuActivity","TiEsMenuActivity","","","DepartamentoMenuActivity","MunicipioMenuActivity"};

    ControlDBValoracionEstablecimientos BDhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BDhelper=new ControlDBValoracionEstablecimientos(this);
        Intent intentRecibe= getIntent();
        Bundle b= intentRecibe.getExtras();
        String idUsuario =(String) b.get("idUsuario");
        BDhelper.abrir();
        menu=BDhelper.obtenerMenu(idUsuario);
        activities=BDhelper.obtenerActivites(idUsuario);
        BDhelper.cerrar();
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,menu));


    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        if(position != 10) {    /*tiene 10 porque tenemos 10 tablas mas la opcion de llenar BD*/
            String nombreValue = activities[position];
            try {
                Class<?> clase = Class.forName("proyecto1.pdm.fia.ues.sv.valoracionestablecimientos." + nombreValue);
                Intent intent = new Intent(this, clase);
                this.startActivity(intent);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else{

        }

    }

}
