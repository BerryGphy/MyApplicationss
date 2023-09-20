package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.os.Vibrator;
import android.media.MediaPlayer;
public class MainActivity extends AppCompatActivity {
    private static final int DEFAULT_AMPLITUDE = 100;
    private EditText editTextNumberDecimal; // ID de l'editText
    private Button button2; // ID du bouton convertir
    private TextView label_finalResult; // ID du texte
    private MediaPlayer mediaPlayer;
    private Switch switchAuto;
    private Spinner spinnerEntry;
    private Spinner spinnerExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(this, R.raw.kwak);
        editTextNumberDecimal = findViewById(R.id.editTextNumberDecimal);
        button2 = findViewById(R.id.button2);
        label_finalResult = findViewById(R.id.label_finalResult);
        spinnerEntry = findViewById(R.id.spinnerEntry);
        spinnerExit = findViewById(R.id.spinnerExit);
        switchAuto = findViewById(R.id.switchAuto);
        switchAuto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Appel de la méthode de conversion automatique lorsque le switch est activé
                if (isChecked) {
                    performAutoConversion();
                }
            }
        });
        spinnerEntry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Appel de la méthode de conversion automatique lorsque la sélection change
                if (switchAuto.isChecked()) {
                    performAutoConversion();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        spinnerExit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Appel de la méthode de conversion automatique lorsque la sélection change
                if (switchAuto.isChecked()) {
                    performAutoConversion();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
        editTextNumberDecimal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2){
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (switchAuto.isChecked()) {
                    performAutoConversion();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });


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
        double inputValue = 0.0;
        try {
            inputValue = Double.parseDouble(editTextNumberDecimal.getText().toString());
        } catch (NumberFormatException e) {
            label_finalResult.setText("Entrée invalide");
            return;
        }
        String itemEntry = spinnerEntry.getSelectedItem().toString();
        String itemExit = spinnerExit.getSelectedItem().toString();
        if (itemEntry.equals(itemExit)){
            label_finalResult.setText(inputValue+"");
        } else if ("°F".equals(itemEntry)){
            if("°K".equals(itemExit)){
                inputValue = (inputValue + 459.67)/1.8;
                label_finalResult.setText(inputValue+"");
            } else if ("°C".equals(itemExit)) {
                inputValue = (inputValue-32)*5/9;
                label_finalResult.setText(inputValue +"");
            }
        } else if ("°K".equals(itemEntry)) {
            if("°F".equals(itemExit)){
                inputValue = inputValue*1.8-459.67;
                label_finalResult.setText(inputValue+"");
            } else if ("°C".equals(itemExit)) {
                inputValue = inputValue-273.15;
                label_finalResult.setText(inputValue+"");
            }
        } else if ("°C".equals(itemEntry)){
            if("°K".equals(itemExit)){
                inputValue = inputValue + 273.15;
                label_finalResult.setText(inputValue+"");
            } else if ("°F".equals(itemExit)) {
                inputValue = (inputValue*9/5)+32;
                label_finalResult.setText(inputValue+"");
            }
        } else {
            label_finalResult.setText("erreur");
        }
        vibrate(1000);
        playButtonClickSound();
    }
    private void performAutoConversion() {
        calculateResult();
    }
    private void playButtonClickSound() {
        if (mediaPlayer != null) {
            mediaPlayer.seekTo(2500); // Réinitialiser la position de lecture
            mediaPlayer.start();
        }
    }

}