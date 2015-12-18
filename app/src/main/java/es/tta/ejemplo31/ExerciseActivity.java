package es.tta.ejemplo31;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

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

        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            Toast.makeText(this, R.string.no_camera, Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (intent.resolveActivity(getPackageManager()) != null) {
                File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                try {
                    File file = File.createTempFile("tta-foto", ".png", dir);
                    Uri pictureUri = Uri.fromFile(file);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri);
                    startActivity(intent);
                } catch (IOException e) {

                }
            } else {
                Toast.makeText(this, R.string.no_app, Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void recordAudio(View view) {
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_MICROPHONE)) {
            Toast.makeText(this, R.string.no_micro, Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
            if (intent.resolveActivity(getPackageManager()) != null) {
                File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
                try {
                    File file = File.createTempFile("tta-audio", ".wav", dir);
                    Uri audioUri = Uri.fromFile(file);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, audioUri);
                    intent.putExtra(MediaStore.Audio.Media.EXTRA_MAX_BYTES, 1000000); //1MB
                    startActivity(intent);
                } catch (IOException e) {

                }
            } else {
                Toast.makeText(this, R.string.no_app, Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void recordVideo(View view) {
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            Toast.makeText(this, R.string.no_camera, Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            if (intent.resolveActivity(getPackageManager()) != null) {
                File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                try {
                    File file = File.createTempFile("tta-video", ".mp4", dir);
                    Uri videoUri = Uri.fromFile(file);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, videoUri);
                    intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 10000); //10 segundos
                    intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1); //Alta calidad
                    intent.putExtra(MediaStore.EXTRA_SIZE_LIMIT, 10000000); //10MB
                    startActivity(intent);
                } catch (IOException e) {

                }
            } else {
                Toast.makeText(this, R.string.no_app, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
