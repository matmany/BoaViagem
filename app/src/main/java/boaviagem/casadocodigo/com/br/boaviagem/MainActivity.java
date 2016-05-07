package boaviagem.casadocodigo.com.br.boaviagem;

import android.content.Intent;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.CheckBox;


public class MainActivity extends ActionBarActivity {
  private static final String MANTER_CONECTADO = "manter_conectado";
 private EditText usuario;
 private EditText senha;
private CheckBox manterConectado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        usuario = (EditText)findViewById(R.id.usuario);
        senha = (EditText)findViewById(R.id.senha);
        manterConectado = (CheckBox) findViewById(R.id.manterConectado);
        SharedPreferences preferencias = getPreferences(MODE_PRIVATE);
        boolean conectado = preferencias.getBoolean(MANTER_CONECTADO,false);

        if(conectado)
        {
            startActivity(new Intent(this,DashboardActivity.class));
        }

    }
    public void entrarOnclick(View v)
    {
      String usuarioInformado = usuario.getText().toString();
      String senhaInformado = senha.getText().toString();

        if("leitor".equals(usuarioInformado)&&
                "123".equals(senhaInformado))
        {
             SharedPreferences preferencias = getPreferences(MODE_PRIVATE);

            Editor editor = preferencias.edit();
            editor.putBoolean(MANTER_CONECTADO,manterConectado.isChecked());
            editor.commit();

            startActivity(new Intent(this,DashboardActivity.class));
        }

        else
        {
            String mensagemErro = getString(R.string.erro_autenticacao);
            Toast toast = Toast.makeText(this,mensagemErro,Toast.LENGTH_LONG);
            toast.show();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
