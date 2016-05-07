package boaviagem.casadocodigo.com.br.boaviagem;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SimpleAdapter;
import android.widget.AdapterView;
import android.widget.SimpleAdapter.ViewBinder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.content.DialogInterface.OnClickListener;
import java.util.ArrayList;
import java.util.Map;
import android.widget.Toast;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
/**
 * Created by matmany on 14/04/16.
 */
public class GastoListActivity extends ListActivity implements OnItemClickListener {

    private List<Map<String, Object>> gastos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String [] de = {"data","descricao","valor","categoria"};
        int[] para= {R.id.data,R.id.descricao,R.id.valor,R.id.categoria};
        //setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listarGastos()));
        //ListView listView = getListView();
        //listView.setOnItemClickListener(this);


        SimpleAdapter adapter = new SimpleAdapter(this, listarGastos(),
                R.layout.lista_gasto, de, para);
         adapter.setViewBinder(new GastoViewBinder());

        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);

        registerForContextMenu(getListView());
        //registerForContextMenu(getListView());
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo)
    {   //tranformar opçoes do xml em objeto do tipo Contextmenu
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.gasto_menu,menu);

    }

    public boolean onContextItemSelected(MenuItem item)
    {
        if(item.getItemId()==R.id.remover)
        {
            AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
            gastos.remove(info.position);
            getListView().invalidateViews();
            dataAnterior="";
            return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {

        Map<String,Object>map = gastos.get(position);
        String descricao = (String) map.get("descricao");
        String mensagem = "Gasto selecionado: "+descricao;
        Toast.makeText(this,mensagem,Toast.LENGTH_SHORT).show();
    }



   private List<Map<String, Object>> listarGastos() {
        gastos = new ArrayList<Map<String, Object>>();

        Map<String, Object> item = new HashMap<String, Object>();
        item.put("data", "04/02/2012");
        item.put("descricao", "Diária Hotel");
        item.put("valor", "R$ 260,00");
        item.put("categoria", R.color.categoria_hospedagem);
        gastos.add(item);


        return gastos;
    }

    private String dataAnterior="";

    private class GastoViewBinder implements ViewBinder{


        @Override
        public boolean setViewValue(View view,Object data,String textRepresentation)
        {
            if(view.getId() == R.id.data)
            {
                if(!dataAnterior.equals(data))
                {
                    TextView textView = (TextView) view;
                    textView.setText(textRepresentation);
                    dataAnterior = (textRepresentation);
                    view.setVisibility(View.VISIBLE);
                }
                else {
                    view.setVisibility(View.GONE);
                }
                return true;

            }
            if(view.getId() == R.id.categoria)
            {
                Integer id = (Integer) data;
                view.setBackgroundColor(getResources().getColor(id));
                return true;
            }
            return false;

        }
    }


}
