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

import es.tta.ejemplo31.model.Status;

public class MenuActivity extends AppCompatActivity {

    //Actividad en la que se muestra "nuevo test", "nuevo ejercicio" y "seguimiento"

    public final static String EXTRA_NEXT_TEST = "es.tta.ejemplo31.nextTest";
    public final static String EXTRA_NEXT_EXERCISE = "es.tta.ejemplo31.nextExercise";

    Integer next_test_g = null;
    Integer next_exercise_g = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Intent intent = getIntent();
        TextView textUser = (TextView) findViewById(R.id.menu_user);
        TextView textLesson = (TextView) findViewById(R.id.menu_lesson);

        final String dni = intent.getStringExtra(MainActivity.EXTRA_USER);
        final Integer lesson_number = intent.getIntExtra(MainActivity.EXTRA_LESSON_NUMBER, 0);
        final String lesson_title = intent.getStringExtra(MainActivity.EXTRA_LESSON_TITLE);

        final Integer next_test = intent.getIntExtra(MainActivity.EXTRA_LESSON_NUMBER, 0);
        final Integer next_exercise = intent.getIntExtra(MainActivity.EXTRA_LESSON_NUMBER, 0);

        next_test_g=next_test;
        next_exercise_g=next_exercise_g;

        textUser.setText("Bienvenido " + dni);
        textLesson.setText("Lección "+lesson_number.toString()+": "+lesson_title);
    }

    public void test(View view) {
        Intent intent = new Intent(this, TestActivity.class);
        //cargaremos el test correspondiente al next_test que tenemos
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
