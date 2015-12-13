package es.tta.ejemplo31;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ExerciseActivity extends AppCompatActivity {

    //subir ejercicio, audio, etc...

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        Intent intent = getIntent();
    }

    public void sendFile(View view) {
        Toast.makeText(this, "\"Subir fichero\" sin implementar", Toast.LENGTH_SHORT).show();
    }

    public void takePhoto(View view) {
        Toast.makeText(this, "\"Sacar foto\" sin implementar", Toast.LENGTH_SHORT).show();
    }

    public void recordAudio(View view) {
        Toast.makeText(this, "\"Grabar audio\" sin implementar", Toast.LENGTH_SHORT).show();
    }

    public void recordVideo(View view) {
        Toast.makeText(this, "\"Grabar vídeo\" sin implementar", Toast.LENGTH_SHORT).show();
    }
}
