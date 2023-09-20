package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Button;
import android.widget.TextView;
import android.os.Vibrator;
import android.media.MediaPlayer;
public class MainActivity extends AppCompatActivity {
    private static final int DEFAULT_AMPLITUDE = 100;
    private EditText editTextNumberDecimal; // ID de l'editText
    private RadioGroup radioGroup;
    private RadioButton radio_Fahreiheit; // ID du radio bouton F°
    private RadioButton radio_Celcius; // ID du radio bouton C°
    private Button button2; // ID du bouton convertir
    private TextView label_finalResult; // ID du texte
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(this, R.raw.kwak);
        editTextNumberDecimal = findViewById(R.id.editTextNumberDecimal);
        radioGroup = findViewById(R.id.radioGroup);
        radio_Fahreiheit = findViewById(R.id.radio_Fahreiheit);
        radio_Celcius = findViewById(R.id.radio_Celcius);
        button2 = findViewById(R.id.button2);
        label_finalResult = findViewById(R.id.label_finalResult);
        //button2.setOnClickListener(view -> calculateResult());
    }

    public void vibrate(int duration){
        duration = Math.max(duration,1);
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator !=null) {
            vibrator.vibrate(VibrationEffect.createOneShot(duration,DEFAULT_AMPLITUDE));
        }
    }
    public void action_convert(android.view.View v){
        calculateResult();
    }
    private void calculateResult() {
        double inputValue = Double.parseDouble(editTextNumberDecimal.getText().toString());
        if (radio_Fahreiheit.isChecked()) {
            inputValue = (inputValue-32)*5/9;
            label_finalResult.setText(inputValue + "°C");
        } else if (radio_Celcius.isChecked()) {
            inputValue = (inputValue*9/5)+32;
            label_finalResult.setText(inputValue + "°F");
        }
        vibrate(1000);
        playButtonClickSound();
    }
    private void playButtonClickSound() {
        if (mediaPlayer != null) {
            mediaPlayer.seekTo(0); // Réinitialiser la position de lecture
            mediaPlayer.start(); // Commencer la lecture du son
        }
    }
}