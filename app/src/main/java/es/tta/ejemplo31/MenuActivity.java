package es.tta.ejemplo31;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    //Actividad en la que se muestra "nuevo test", "nuevo ejercicio" y "seguimiento"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        TextView textLogin = (TextView) findViewById(R.id.menu_login);

        final String dni = intent.getStringExtra(MainActivity.EXTRA_LOGIN);

        if (dni.equals("78993014C")) {
            textLogin.setText("Bienvenido Álvaro Urionabarrenetxea");
        } else {
            textLogin.setText("Bienvenido " + dni);
        }

    }

    public void test(View view) {
        Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);
    }

    public void ejercicio(View view) {
        Intent intent = new Intent(this, ExerciseActivity.class);
        startActivity(intent);
    }

    public void seguimiento(View view) {
        Toast toast = Toast.makeText(this, "\"Seguimiento\" sin implementar", Toast.LENGTH_SHORT);
        toast.show();
    }
}
