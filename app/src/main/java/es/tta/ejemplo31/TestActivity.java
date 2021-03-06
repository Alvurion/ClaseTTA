package es.tta.ejemplo31;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class TestActivity extends AppCompatActivity {

    //Actividad en la que se muestran las opciones de una pregunta del test

    @Override
    protected void onCreate(Bundle savedInstanceState) throws NullPointerException {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Intent intent = getIntent();

        final Button enviar = (Button) findViewById(R.id.button_send_test);

        TextView pregunta = (TextView) findViewById(R.id.pregunta);
        pregunta.setText(R.string.preguntaTest);

        String[] opciones = {
                "Versión de la aplicación",
                "Listado de componentes de la aplicación",
                "Opciones del menú de ajustes",
                "Nivel mínimo de la API Android requerida",
                "Nombre del paquete java de la aplicación"
        };

        int num_opt = opciones.length;
        Toast.makeText(getApplicationContext(), "Número de opciones: "+Integer.toString(num_opt),Toast.LENGTH_SHORT).show();

        final RadioGroup group = (RadioGroup) findViewById(R.id.test_choices);
        int i;
        for (i = 0; i < num_opt; i++) {
            RadioButton radio = new RadioButton(this);
            radio.setText(opciones[i]);
            radio.setId(i);
            radio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    findViewById(R.id.button_send_test).setVisibility(View.VISIBLE);
                }
            });
            group.addView(radio);
        }
    }

    public void enviar(View view) {

        Button enviar = (Button) findViewById(R.id.button_send_test);
        Button ayuda = (Button) findViewById(R.id.button_advice);
        RadioGroup group = (RadioGroup) findViewById(R.id.test_choices);

        final int correct = 2;

        enviar.setVisibility(View.GONE); //La vista desaparece y no ocupa espacio en la actividad
        ayuda.setVisibility(View.VISIBLE);

        int choices = group.getChildCount();
        for (int i = 0; i < choices; i++) {
            group.getChildAt(i).setEnabled(false);
        }

        int selected = group.getCheckedRadioButtonId();

        group.getChildAt(correct).setBackgroundColor(Color.GREEN);

        if (selected != correct) {
            Toast.makeText(getApplicationContext(), "Has fallado", Toast.LENGTH_SHORT).show();
            group.getChildAt(selected).setBackgroundColor(Color.RED);
        } else {
            Toast.makeText(getApplicationContext(), "Has acertado", Toast.LENGTH_SHORT).show();
        }
    }

    public void ayuda(View view) {
        TextView texto_ayuda = (TextView) findViewById(R.id.texto_ayuda);
        texto_ayuda.setText(R.string.texto_ayuda);
    }

    private void showVideo(String advise) {
        LinearLayout layout = (LinearLayout) findViewById(R.id.layout_test);
        VideoView video = new VideoView(this);
        video.setVideoURI(Uri.parse(advise));
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        video.setLayoutParams(params);

        MediaController controller = new MediaController(this) {
            @Override
            public void hide() {
            }

            @Override
            public boolean dispatchKeyEvent(KeyEvent event) {

                if (event.getKeyCode() == KeyEvent.KEYCODE_BACK)
                    try {
                        finalize();
                    } catch (Throwable throwable) {
                        //throwable.printStackTrace();
                    }
                return super.dispatchKeyEvent(event);
            }
        };
        controller.setAnchorView(video);
        video.setMediaController(controller);
        layout.addView(video);
        video.start();

    }


}
