package com.hansung.android.soundlogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

public class LoginActivity extends AppCompatActivity { //로그인 화면


    //TextView tv_response;
    String username;
    String message;
    String passwd;
    String email;
    String re;
    private Socket mSocket; //소켓 통신
    {
        try{
            mSocket = IO.socket("http://192.168.0.17:5000");
            System.out.println("접속 완료");
        } catch (URISyntaxException e) {
            System.out.println("접속 실패");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //tv_response = (TextView)findViewById(R.id.tv_response);

        final EditText ID = (EditText)findViewById(R.id.id);
        final EditText PASSWD = (EditText)findViewById(R.id.passwd);

        mSocket.on("result",onNewMessage);
        mSocket.connect();


        Button login_button = (Button) findViewById(R.id.login); //로그인 버튼
        login_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String ID_ = ID.getText().toString();
                String PASSWD_ = PASSWD.getText().toString();
                username = ID_;
                mSocket.emit("send_user", "{'user_name': '"+ID_+"',"+" 'password': '"+PASSWD_+"'}");

            }
        });

    }

    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            LoginActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject data = (JSONObject) args[0];
                    try {
                        message = data.getString("str");
                        //System.out.println("성공" + message);
                    } catch (JSONException e) {
                        //System.out.println("실패");
                        e.printStackTrace();
                        return;
                    }

                    String []arr = message.split(":");
                    for(int i=0; i<arr.length; i++){
                        re = arr[i].trim();
                        System.out.println("re");
                        System.out.println(re);

                    }
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    if("success".equals(re)){ //로그인 성공 시 소리 로그인 준비 화면 (메인 화면 ) 으로 이동
                        Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                        intent.putExtra("user_name", username); //사용자 이름도 같이 보내기
                        startActivity(intent);
                    }else{ //실패 시
                        Toast.makeText(LoginActivity.this, "아이디 또는 비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG).show();
                    }


                }
            });
        }
    };



    @Override
    public void onDestroy() {
        mSocket.disconnect();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        //초반 플래시 화면에서 넘어갈때 뒤로가기 버튼 못누르게 함
    }
}
