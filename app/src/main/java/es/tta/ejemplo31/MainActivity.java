package es.tta.ejemplo31;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;

import es.tta.ejemplo31.comunicaciones.NetworkReceiver;
import es.tta.ejemplo31.model.Status;
import es.tta.ejemplo31.presentation.Data;

public class MainActivity extends AppCompatActivity {

    //Actividad del login
    public final static String EXTRA_LOGIN = "es.tta.ejemplo31.login";
    public final static String EXTRA_PASSWD = "es.tta.ejemplo31.passwd";
    public final static String EXTRA_USER = "es.tta.ejemplo31.user";
    public final static String EXTRA_LESSON_NUMBER = "es.tta.ejemplo31.lessonnumber";
    public final static String EXTRA_LESSON_TITLE = "es.tta.ejemplo31.lessontitle";
    public final static String EXTRA_NEXT_TEST = "es.tta.ejemplo31.nexttest";
    public final static String EXTRA_NEXT_EXERCISE = "es.tta.ejemplo31.nextexercise";

    //public final static String EXTRA_USERSTATUS = "es.tta.ejemplo31.userStatus";

    private NetworkReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle(R.string.alumnoTTA);

        //Register BroadcastReceiver to track network connection changes
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        receiver = new NetworkReceiver();
        this.registerReceiver(receiver, filter);

        //Cargamos los datos del login (dni) si existe
        EditText editLogin = (EditText) findViewById(R.id.login);
        String log = getLogin();
        if (log != null) {
            editLogin.setText(log);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //unregisters BroadcastReceiver when app is destroyed
        if (receiver != null) {
            this.unregisterReceiver(receiver);
        }
    }

    public void login(final View view){

        EditText editLogin = (EditText) findViewById(R.id.login);
        EditText editPasswd = (EditText) findViewById(R.id.passwd);
        final String dni = editLogin.getText().toString();
        final String pwd = editPasswd.getText().toString();

        final Intent intent = new Intent(this, MenuActivity.class);
        putLogin(dni);
        final Data data = new Data(dni, pwd);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Status userStatus = null;
                try {
                    userStatus = data.getStatus(dni, pwd);
                } catch (Exception e) {
                    Log.e("ALERTA",e.getMessage(), e);
                } finally {
                    intent.putExtra(EXTRA_USER, userStatus.getUser());
                    intent.putExtra(EXTRA_LESSON_NUMBER, userStatus.getLesson());
                    intent.putExtra(EXTRA_LESSON_TITLE, userStatus.getLessonTitle());
                    intent.putExtra(EXTRA_NEXT_TEST, userStatus.getNextTest());
                    intent.putExtra(EXTRA_NEXT_EXERCISE, userStatus.getNextExercise());
                    view.post(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(intent);
                        }
                    });
                }
            }
        }).start();
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
