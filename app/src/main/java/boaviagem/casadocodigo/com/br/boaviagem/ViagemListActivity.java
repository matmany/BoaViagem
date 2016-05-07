package boaviagem.casadocodigo.com.br.boaviagem;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.support.v7.internal.widget.ListViewCompat;
import android.view.View;
import android.widget.Toast;
import android.content.DialogInterface.OnClickListener;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import android.widget.AdapterView.OnItemClickListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface;
import android.widget.AdapterView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import android.widget.AdapterView.OnItemClickListener;
import java.util.List;
import java.util.Map;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter.ViewBinder;
import android.widget.EditText;
import android.widget.RadioGroup;

import android.widget.SimpleAdapter;


/**
 * Created by matmany on 14/04/16.
 */

public class ViagemListActivity extends ListActivity
        implements OnItemClickListener,OnClickListener, ViewBinder
{   private List<Map<String, Object>> viagens;
    private int viagemSelecionada;
    private AlertDialog alertDialog;
    private AlertDialog dialogconfirmacao;

    private DatabaseHelper helper;
    private SimpleDateFormat dateFormat;
    private double valorLimite;


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.

     */
    /*
    private GoogleApiClient client;
    SimpleAdapter adapter;
    String de[];
    int para[];
    private AlertDialog alertDialog;
    private AlertDialog dialogConfirmacao;
    private int viagemSelecionada;
    private boolean modoSelecionarViagem;*/


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        helper = new DatabaseHelper(this);
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        SharedPreferences preferencias =
                PreferenceManager.getDefaultSharedPreferences(this);

        String valor = preferencias.getString("valor_limite","-1");
        valorLimite = Double.valueOf(valor);

        String[] de = {"imagem","destino","data","total", "barraProgresso"};
        int[] para = {R.id.tipoViagem,R.id.destino,R.id.data,R.id.valor,R.id.barraProgresso};

        SimpleAdapter adapter=
                new SimpleAdapter(this,listarViagens(),
                R.layout.viagemlist,de,para);


        setListAdapter(adapter);
        adapter.setViewBinder(this);

        getListView().setOnItemClickListener(this);

        this.alertDialog = criaAlertDialog();
        this.dialogconfirmacao = criaDialogConfrimacao();
        /*
        setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,listarViagens()));
         ListView listView=getListView();
         listView.setOnItemClickListener(this);*/

    }

    private List<Map<String, Object>> listarViagens()
    {   SQLiteDatabase db = helper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT _id, tipo_viagem,destino,"+
                "data_chegada,data_saida,orcamento FROM viagem",
                null);

        cursor.moveToFirst();

        viagens = new ArrayList<Map<String, Object>>();

        for(int i=0;i<cursor.getCount(); i++)
        {
            Map<String, Object> item = new HashMap<String,Object>();

            String id = cursor.getString(0);
            int tipoViagem = cursor.getInt(1);
            String destino = cursor.getString(2);
            long dataChegada = cursor.getLong(3);
            long dataSaida = cursor.getLong(4);
            double orcamento = cursor.getDouble(5);

            item.put("id",id);

            if(tipoViagem == Constantes.VIAGEM_LAZER)
            {
               item.put("image", R.drawable.lazer);
            }
            else{
                item.put("image", R.drawable.negocioss);
            }

            item.put("destino",destino);

            Date dataChegadaDate = new Date(dataChegada);
            Date dataSaidaDate = new Date(dataSaida);

            String periodo = dateFormat.format(dataChegadaDate)+"a"
             +dateFormat.format(dataSaidaDate);

            item.put("data", periodo);

            double totalGasto = calcularTotalGasto(db,id);

            item.put("total", "Gasto total R$" + totalGasto);

            double alerta = orcamento * valorLimite / 100;
            Double [] valores = new Double[] {orcamento, alerta, totalGasto };
            item.put("barraProgresso", valores);

            viagens.add(item);

            cursor.moveToNext();
        }
        cursor.close();
        return viagens;
    }

    private double calcularTotalGasto(SQLiteDatabase db,String id)
    {
        Cursor cursor = db.rawQuery(
                "SELECT SUM(valor) FROM gasto WHERE  viagem_id=?",
                new String[]{id});
        cursor.moveToFirst();
        double total = cursor.getDouble(0);
        cursor.close();
        return total;
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {

        /*Map<String, Object> map = viagens.get(position);
        String destino = (String) map.get("destino");
        String mensagem = "Viagem selecionada: " + destino;
        Toast.makeText(this, mensagem,Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, GastoListActivity.class));*/

        this.viagemSelecionada = position;
        alertDialog.show();



    }

    @Override
    public void onClick(DialogInterface dialog, int item)
    {
        Intent intent;
        String id = (String) viagens.get(viagemSelecionada).get("id");

        switch (item)
        {
            case 0:
                 intent = new Intent(this,ViagemActivity.class);
                 intent.putExtra(Constantes.VIAGEM_ID,id);
                 startActivity(intent);
                break;

            case 1:
                startActivity(new Intent(this, GastoActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, GastoListActivity.class));
                break;
            case 3:
                //viagens.remove(this.viagemSelecionada);
                //getListView().invalidateViews();
                dialogconfirmacao.show();
                break;
            case 4:
                Toast.makeText(this,"TEST",Toast.LENGTH_SHORT).show();
                break;

            case DialogInterface.BUTTON_POSITIVE:
                // exclusão
                viagens.remove(viagemSelecionada);
                removerViagem(id);
                getListView().invalidateViews();
                break;

            case DialogInterface.BUTTON_NEGATIVE:
                dialogconfirmacao.dismiss();

                break;
        }

    }

    private AlertDialog criaAlertDialog()
    {
        final CharSequence[] item = {
        getString(R.string.editar),
        getString(R.string.novo_gasto),
        getString(R.string.gasto_realizado),
        getString(R.string.remover),
        getString(R.string.test)
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.opcoes);
        builder.setItems(item,this);

        return builder.create();
    }

    private AlertDialog criaDialogConfrimacao()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.confirmacao_exclusao_viagem);
        builder.setPositiveButton(getString(R.string.sim),this);
        builder.setNegativeButton(getString(R.string.nao), this);

        return builder.create();

    }

   @Override
    public boolean setViewValue(View view,Object data,String textRepresentation)
    {
        if(view.getId() == R.id.barraProgresso)
        {
            Double valores[] = (Double[]) data;
            ProgressBar progressBar = (ProgressBar) view;
            progressBar.setMax(valores[0].intValue());
            progressBar.setSecondaryProgress(valores[1].intValue());
            progressBar.setProgress(valores[2].intValue());
           return true;
        }
        return false;
    }

    public class Constantes{
        public static final String VIAGEM_ID="viagem_id ";
        public static final int VIAGEM_LAZER=1;
        public static final int VIAGEM_NEGOCIOS=2;
    }

    private void removerViagem(String id)
    {
        SQLiteDatabase db = helper.getWritableDatabase();
        String where[] = new String[]{id};
        db.delete("gasto","viagem_id=?",where);
        db.delete("viagem","_id=?",where);
    }

    @Override
    protected void onDestroy()
    {
        helper.close();
        super.onDestroy();
    }



}

