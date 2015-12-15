package es.tta.ejemplo31;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class TestActivity extends AppCompatActivity {

    //Actividad en la que se muestran las opciones de una pregunta del test

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Intent intent = getIntent();

        final Button enviar = (Button) findViewById(R.id.button_send_test);
        final Button ayuda = (Button) findViewById(R.id.button_advice);

        TextView pregunta = (TextView) findViewById(R.id.pregunta);
        pregunta.setText(R.string.preguntaTest);

        String[] opciones = {
                "Versión de la aplicación",
                "Listado de componentes de la aplicación",
                "Opciones del menú de ajustes",
                "Nivel mínimo de la API Android requerida",
                "Nombre del paquete java de la aplicación"
        };

        final RadioGroup group = (RadioGroup) findViewById(R.id.test_choices);
        int i;
        for (i = 0; i < 5; i++) {
            RadioButton radio = new RadioButton(this);
            radio.setText(opciones[i]);
            radio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    findViewById(R.id.button_send_test).setVisibility(View.VISIBLE);
                }
            });
            group.addView(radio);
        }

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviar.setVisibility(View.GONE); //La vista desaparece y no ocupa espacio en la actividad
                ayuda.setVisibility(View.VISIBLE);

                final int correct = 2;
                int selected = 0;

                int choices = group.getChildCount();
                for (int i = 0; i < choices; i++) {
                    if (group.getChildAt(i).isSelected()) {
                        selected = i;
                    }
                    group.getChildAt(i).setEnabled(false);
                }

                group.getChildAt(correct).setBackgroundColor(Color.GREEN);

                if (selected != correct) {
                    Toast.makeText(getApplicationContext(), "Has fallado", Toast.LENGTH_SHORT).show();
                    group.getChildAt(selected).setBackgroundColor(Color.RED);
                } else {
                    Toast.makeText(getApplicationContext(), "Has acertado", Toast.LENGTH_SHORT).show();
                }

            }
        });

        ayuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView texto_ayuda = (TextView) findViewById(R.id.texto_ayuda);
                texto_ayuda.setText(R.string.texto_ayuda);
            }
        });


    }


}
