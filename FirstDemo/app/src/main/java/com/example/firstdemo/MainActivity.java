package com.example.firstdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Button addButton = (Button) findViewById(R.id.buttonAdd);

        EditText firstNumEdit = (EditText) findViewById(R.id.editTextNumber);
        EditText secNumEdit = (EditText) findViewById(R.id.editTextNumber2);


        TextView resultText = (TextView) findViewById(R.id.resultText);


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number1 = Integer.parseInt(firstNumEdit.getText().toString());
                int number2 = Integer.parseInt(secNumEdit.getText().toString());

                int resultInt = number1 + number2;

                resultText.setText("Tulos on: " + resultInt);

            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            return insets;
        });


    }
}