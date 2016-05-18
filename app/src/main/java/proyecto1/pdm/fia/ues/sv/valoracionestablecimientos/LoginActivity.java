package proyecto1.pdm.fia.ues.sv.valoracionestablecimientos;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
    private Button btnLogin;
    private EditText editUsuario;
    private EditText editPassword;
    private TextView notaError;
    private ControlDBValoracionEstablecimientos helper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editUsuario = (EditText) findViewById(R.id.editUsuario);
        editPassword = (EditText) findViewById(R.id.editPass);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        notaError= (TextView) findViewById(R.id.nota);
        helper=new ControlDBValoracionEstablecimientos(this);
        helper.abrir();
        String tost=helper.llenarBDProyecto1();
        Toast.makeText(this, tost, Toast.LENGTH_SHORT).show();
        helper.cerrar();
    }

    public void logear(View v){
        String logeo="";
        if(editUsuario.getText().toString().equals("") || editPassword.getText().toString().equals("")){
            notaError.setText("Error, Ingrese los datos");
        }
        else{
            try {
                helper.abrir();
               logeo= helper.logear(editUsuario.getText().toString(),editPassword.getText().toString());

                //CODIGO PARA LLENAR BASE DE DATOS
                //String tost=helper.llenarBDProyecto1();
                //Toast.makeText(this, tost, Toast.LENGTH_SHORT).show();
                helper.cerrar();
                if(logeo=="")
                    notaError.setText("No esta regitrado");
                else{
                    Class<?> clase = Class.forName("proyecto1.pdm.fia.ues.sv.valoracionestablecimientos.MenuPrincipalActivity");
                    Intent intent = new Intent(this, clase);
                    intent.putExtra("idUsuario",logeo);
                    this.startActivity(intent);

                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


}