package es.tta.ejemplo31;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TestActivity extends AppCompatActivity {

    //Actividad en la que se muestran las opciones de una pregunta del test

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Intent intent = getIntent();
    }
}
