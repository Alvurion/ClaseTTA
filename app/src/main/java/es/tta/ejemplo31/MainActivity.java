package es.tta.ejemplo31;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Actividad del login
    public final static String EXTRA_LOGIN = "es.tta.ejemplo31.login";
    public final static String EXTRA_PASSWD = "es.tta.ejemplo31.passwd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle(R.string.alumnoTTA);

        EditText editLogin = (EditText) findViewById(R.id.login);

        String l = loadLogin();
        if (l != null || l.isEmpty()) {
            editLogin.setText(l);
        }
    }

    public void login(View view) {

        EditText passwd = (EditText) findViewById(R.id.passwd);
        String pwd = passwd.getText().toString();

        if (!pwd.equals("tta") || pwd.isEmpty()) {
            Toast.makeText(this, "Contraseña errónea", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, MenuActivity.class);
            EditText editLogin = (EditText) findViewById(R.id.login);
            EditText editPasswd = (EditText) findViewById(R.id.passwd);
            saveLogin(editLogin.getText().toString());
            intent.putExtra(EXTRA_LOGIN, editLogin.getText().toString());
            intent.putExtra(EXTRA_PASSWD, editPasswd.getText().toString());
            startActivity(intent);
        }
    }

    private String loadLogin() {
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        return prefs.getString(EXTRA_LOGIN, null);
    }

    private void saveLogin(String login) {
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(EXTRA_LOGIN, login);
        editor.commit();
    }
}
