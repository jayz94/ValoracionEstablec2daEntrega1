package proyecto1.pdm.fia.ues.sv.valoracionestablecimientos;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TiEsMenuActivity extends ListActivity {

    String[] menu = {"Insertar Tipo Establecimiento", "Eliminar Tipo Establecimiento", "Consultar Tipo Establecimiento", "Actualizar Tipo Establecimiento"};
    String[] activities = {"TiEstInsertarActivity", "TiEsEliminarActivity", "TiEsConsultarActivity", "TiEsActualizarActivity"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListView listView=getListView();
        listView.setBackgroundColor(Color.rgb(150, 150, 150));

        ArrayAdapter adapter=new ArrayAdapter<String> (this,android.R.layout.simple_list_item_1,menu);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l,View v,int position,long id){
        super.onListItemClick(l, v, position, id);
        String nombreValue=activities[position];
        try{
            Class<?> clase = Class.forName("proyecto1.pdm.fia.ues.sv.valoracionestablecimientos." + nombreValue);
            Intent inte=new Intent(this,clase);
            this.startActivity(inte);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}