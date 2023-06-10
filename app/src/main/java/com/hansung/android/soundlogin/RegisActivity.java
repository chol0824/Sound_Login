package com.hansung.android.soundlogin;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;

public class RegisActivity extends AppCompatActivity { //소리 정보 확인 화면


    TextView tvResult;



    String sound;
    String username;
    String message;
    String re;

    SpeechRecognizer mRecognizer;

    private Socket nSocket;
    {
        try{
            nSocket = IO.socket("http://192.168.0.17:5000");
            System.out.println("접속 완료");
        } catch (URISyntaxException e) {
            System.out.println("접속 실패");
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soundcheck);

        tvResult = findViewById(R.id.tvResult);

        Intent intent = getIntent();
        final String User = intent.getExtras().getString("user_name");
        username = User;


        //Toast.makeText(RegisActivity.this, "소리를 인식합니다.", Toast.LENGTH_SHORT).show();

        requestPermission();

        intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName());
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-KR");




        mRecognizer = SpeechRecognizer.createSpeechRecognizer(getApplicationContext());
        mRecognizer.setRecognitionListener(listener);
        mRecognizer.startListening(intent);

        //if(TextUtils.isEmpty(sound)){
            //return;
        //}
        //nSocket.emit("send", sound);

        nSocket.on("s_result",onNewMessage);
        nSocket.connect();



    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {

            // 거부해도 계속 노출됨. ("다시 묻지 않음" 체크 시 노출 안됨.)
            // 허용은 한 번만 승인되면 그 다음부터 자동으로 허용됨.
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.RECORD_AUDIO}, 0);
        }
    }


    private RecognitionListener listener = new RecognitionListener() { //음성 인식
        @Override
        public void onReadyForSpeech(Bundle params) {

            Toast.makeText(getApplicationContext(), "음성인식 시작", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onBeginningOfSpeech() {

        }

        @Override
        public void onRmsChanged(float rmsdB) {

        }

        @Override
        public void onBufferReceived(byte[] buffer) {

        }

        @Override
        public void onEndOfSpeech() {

        }

        @Override
        public void onError(int error) {
            String message;

            switch (error) {
                case SpeechRecognizer.ERROR_AUDIO:
                    message = "오디오 에러";
                    break;

                case SpeechRecognizer.ERROR_CLIENT:
                    message = "클라이언트 에러";
                    break;

                case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                    message = "퍼미션 에러";
                    break;

                case SpeechRecognizer.ERROR_NETWORK:
                    message = "네트워크 에러";
                    break;

                case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                    message = "네트워크 타임아웃";
                    break;

                case SpeechRecognizer.ERROR_NO_MATCH:
                    message = "찾을 수 없음";
                    break;

                case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                    message = "인식 시도 중";
                    break;

                case SpeechRecognizer.ERROR_SERVER:
                    message = "서버가 이상함";
                    break;

                case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                    message = "말하는 시간 초과";
                    break;

                default:
                    message = "알 수 없는 에러";
                    break;
            }

            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

        }
        @Override
        public void onResults(Bundle results) {
            ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

            for (int i=0; i<matches.size(); i++) {
                tvResult.setText(matches.get(i));
                sound = matches.get(i);
                System.out.println("sound1"+sound);
            }

            if(TextUtils.isEmpty(sound)){
              return;
            }

            nSocket.emit("send_sound", "{'user_name': '"+username+"',"+" 'message': '"+sound+"'}");
            Toast.makeText(getApplicationContext(), "확인중", Toast.LENGTH_SHORT).show();



        }

        @Override
        public void onPartialResults(Bundle partialResults) {

        }

        @Override
        public void onEvent(int eventType, Bundle params) {

        }

        };

    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            RegisActivity.this.runOnUiThread(new Runnable() {
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
                    Intent intent = new Intent(getApplicationContext(), SuccessActivity.class);
                    if("Success".equals(re)){ // 소리 정보 일치 시 소리 로그인 성공 화면으로 넘어가기
                        Toast.makeText(RegisActivity.this, "소리 정보 일치", Toast.LENGTH_SHORT).show();
                        intent.putExtra("USERNAME", username);
                        startActivity(intent);
                    }else{ //불일치 시
                        Toast.makeText(RegisActivity.this, "소리 정보 불일치", Toast.LENGTH_LONG).show();
                    }


                }
            });
        }
    };

    @Override
    public void onBackPressed() {
        //초반 플래시 화면에서 넘어갈때 뒤로가기 버튼 못누르게 함
    }



    }




