package boaviagem.casadocodigo.com.br.boaviagem;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.View;

/**
 * Created by matmany on 20/04/16.
 */
public class ConfiguracoesActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstance)
    {
        super.onCreate(savedInstance);
        addPreferencesFromResource(R.xml.preferencias);

    }


}
