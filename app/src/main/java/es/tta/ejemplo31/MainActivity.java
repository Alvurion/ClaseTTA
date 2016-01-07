package es.tta.ejemplo31;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import es.tta.ejemplo31.comunicaciones.NetworkReceiver;

public class MainActivity extends AppCompatActivity {

    //Actividad del login
    public final static String EXTRA_LOGIN = "es.tta.ejemplo31.login";
    public final static String EXTRA_PASSWD = "es.tta.ejemplo31.passwd";

    private NetworkReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle(R.string.alumnoTTA);

        //Register BroadcastReceiver to track network connection changes
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        receiver = new NetworkReceiver();
        this.registerReceiver(receiver,filter);

        //Cargamos los datos del login (dni)
        EditText editLogin = (EditText) findViewById(R.id.login);
        String log = getLogin();
        if (log != null) {
            editLogin.setText(log);
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        //unregisters BroadcastReceiver when app is destroyed
        if(receiver != null){
            this.unregisterReceiver(receiver);
        }
    }

    public void login(View view) {

        EditText passwd = (EditText) findViewById(R.id.passwd);
        String pwd = passwd.getText().toString();

        //Comprobamos que el password es el correcto
        if (!pwd.equals("tta")) {
            Toast.makeText(this, R.string.badPassword, Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, MenuActivity.class);
            EditText editLogin = (EditText) findViewById(R.id.login);
            putLogin(editLogin.getText().toString());
            intent.putExtra(EXTRA_LOGIN, editLogin.getText().toString());
            intent.putExtra(EXTRA_PASSWD, pwd);
            startActivity(intent);
        }
    }

    private String getLogin() {
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        return prefs.getString(EXTRA_LOGIN, null);
    }

    private void putLogin(String login) {
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(EXTRA_LOGIN, login);
        editor.commit();
    }
}
