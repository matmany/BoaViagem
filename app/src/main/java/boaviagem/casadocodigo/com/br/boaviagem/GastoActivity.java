package boaviagem.casadocodigo.com.br.boaviagem;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;

import java.util.Calendar;

/**
 * Created by matmany on 13/04/16.
 */
public class GastoActivity extends ActionBarActivity {
   private int ano,mes,dia;
    private Button dataGasto;
    private Spinner categoria;
   // DatePickerDialog.OnDateSetListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gasto);

        Calendar calendar = Calendar.getInstance();
        ano=calendar.get(Calendar.YEAR);
        mes=calendar.get(Calendar.MONTH);
        dia=calendar.get(Calendar.DAY_OF_MONTH);
        dataGasto = (Button)findViewById(R.id.data);
        dataGasto.setText(dia+"/"+(mes+1)+"/"+ano);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,R.array.categoria_gasto,android.R.layout.simple_spinner_item);
         categoria = (Spinner) findViewById(R.id.categoria);
         categoria.setAdapter(adapter);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.gasto_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item)
    {
        finish();
        return true;

    }

    public void selecionarData(View view)
    {
        showDialog(view.getId());
    }

    @Override
    protected Dialog onCreateDialog(int id)
    {
        if(R.id.data == id)
        {
            return new DatePickerDialog(this,listener,ano,mes,dia);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int mothOfYear, int dayOfMonth)
        {
            ano=year;
            mes=mothOfYear;
            dia=dayOfMonth;
            dataGasto.setText(dia+"/"+(mes+1)+"/"+ano);
        }
    };


}
