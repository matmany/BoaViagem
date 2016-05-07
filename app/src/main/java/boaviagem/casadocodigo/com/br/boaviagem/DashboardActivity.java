package boaviagem.casadocodigo.com.br.boaviagem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by matmany on 13/04/16.
 */
public class DashboardActivity extends ActionBarActivity {
    int a;
    public static final String MODO_VIAGEM = "modo_viagem";
    public static final String VIAGEM_ID = "viagem_id";
    public static final String VIAGEM_DESTINO = "viagem_destino";
    public static final String MODO_SELECIONAR_VIAGEM = "modo_selecionar_viagem";

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dashboard_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item )
    {
        finish();
        return true;

    }

    public void selecionarOpcao(View view)
    { a=view.getId();
        switch(a)
        {
            case R.id.nova_viagem:
                startActivity(new Intent(this,ViagemActivity.class));
                break;

            case R.id.minhas_viagens:
                startActivity(new Intent(this,ViagemListActivity.class));
                break;

            case R.id.novo_gasto:
                startActivity(new Intent(this, GastoActivity.class));
                //startActivity();
                break;

            case R.id.configuracoes:
                startActivity(new Intent(this,ConfiguracoesActivity.class));
                Toast toast4 = Toast.makeText(this,"configurações",Toast.LENGTH_LONG);
                toast4.show();
                //startActivity();
                break;

        }
        //TextView textView = (TextView) view;
        //String opcao = "Opção: " + textView.getText().toString();
        //Toast.makeText(this,opcao,Toast.LENGTH_LONG);

    }

}
