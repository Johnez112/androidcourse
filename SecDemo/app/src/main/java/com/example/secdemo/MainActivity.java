package com.example.secdemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

        Button buttonToSec = (Button) findViewById(R.id.buttonAct);
        Button buttonToSearch = (Button) findViewById(R.id.buttonGoogleSearch);

        buttonToSec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SecActivity.class);
                startActivity(intent);
            }
        });

        buttonToSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String googleStr = "https://www.google.com";
                Uri googleURL = Uri.parse(googleStr);

                Intent startGoogle = new Intent(Intent.ACTION_VIEW, googleURL);
                startActivity(startGoogle);

            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}