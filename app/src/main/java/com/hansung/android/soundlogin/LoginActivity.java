package com.hansung.android.soundlogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    String id = "root";
    String passwd = "1234";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText ID = (EditText)findViewById(R.id.id);
        final EditText PASSWD = (EditText)findViewById(R.id.passwd);


        Button login_button = (Button) findViewById(R.id.login);
        login_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String ID_ = ID.getText().toString();
                String PASSWD_ = PASSWD.getText().toString();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                if(ID_.equals(id) && (PASSWD_.equals(passwd))){
                    Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }else{
                    Toast.makeText(LoginActivity.this, "아이디 또는 비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG).show();
                }


            }
        });
    }
}
