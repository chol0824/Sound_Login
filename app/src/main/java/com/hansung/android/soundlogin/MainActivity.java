package com.hansung.android.soundlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button device_button = (Button) findViewById(R.id.Sound_Login);
        device_button.setOnClickListener(new View.OnClickListener(){
         @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), RegisActivity.class);
            startActivity(intent);
        }
    });
    }
}
