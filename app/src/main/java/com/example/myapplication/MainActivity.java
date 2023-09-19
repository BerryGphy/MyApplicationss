package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Button;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    private EditText editTextNumberDecimal; // ID de l'editText
    private RadioGroup radioGroup;
    private RadioButton radio_Fahreiheit; // ID du radio bouton F°
    private RadioButton radio_Celcius; // ID du radio bouton C°
    private Button button2; // ID du bouton C°
    private TextView label_finalResult; // ID du texte

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNumberDecimal = findViewById(R.id.editTextNumberDecimal);
        radioGroup = findViewById(R.id.radioGroup);
        radio_Fahreiheit = findViewById(R.id.radio_Fahreiheit);
        radio_Celcius = findViewById(R.id.radio_Celcius);
        button2 = findViewById(R.id.button2);
        label_finalResult = findViewById(R.id.label_finalResult);
        button2.setOnClickListener(view -> calculateResult());
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
    }
}