package proyecto1.pdm.fia.ues.sv.valoracionestablecimientos;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ClienteMenuActivity extends ListActivity {

    String [] menu = {"Ingresar Cliente","Consultar Cliente","Actualizar Cliente","Eliminar Cliente"};
    String [] activities={"ClienteInsertarActivity","ClienteConsultarActivity","ClienteActualizarActivity","ClienteEliminarActivity"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,menu));

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String nombreValue=activities[position];
        try {
            Class<?> clase=Class.forName("proyecto1.pdm.fia.ues.sv.valoracionestablecimientos."+nombreValue);
            Intent intent= new Intent(this,clase);
            startActivity(intent);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }




}
