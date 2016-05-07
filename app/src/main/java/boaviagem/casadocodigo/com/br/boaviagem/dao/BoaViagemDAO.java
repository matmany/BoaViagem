package boaviagem.casadocodigo.com.br.boaviagem.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import boaviagem.casadocodigo.com.br.boaviagem.DatabaseHelper;
import boaviagem.casadocodigo.com.br.boaviagem.domain.Viagem;

/**
 * Created by matmany on 22/04/16.
 */

public class BoaViagemDAO {

    private DatabaseHelper helper;
    private SQLiteDatabase db;

    public BoaViagemDAO(Context context)
    {
        helper = new DatabaseHelper(context);
    }

    private SQLiteDatabase getDb()
    {
        if(db==null)
        {
            db = helper.getWritableDatabase();
        }

        return db;
    }

    public List<Viagem> listarViagens()
    {
      Cursor cursor = getDb().query(DatabaseHelper.Viagem.TABELA,
              DatabaseHelper.Viagem.COLUNAS,
              null,null,null,null,null);

    List<Viagem> viagens = new ArrayList<Viagem>();

        while(cursor.moveToNext())
        {
            Viagem viagem = criarViagem(cursor);
            viagens.add(viagem);
        }
        cursor.close();
        return viagens;
    }

    void criarViagem()
    {

    }



    public void close()
    {
        helper.close();

    }
}
