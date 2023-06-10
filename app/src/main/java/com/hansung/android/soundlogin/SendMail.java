package com.hansung.android.soundlogin;

import android.content.Context;
import android.content.Intent;
import android.text.util.Linkify;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.mail.SendFailedException;

public class SendMail extends AppCompatActivity { //메일 보내기
    String user = "hs.soundlogin@gmail.com"; // 보내는 계정의 id
    String password = "hansung123!"; // 보내는 계정의 pw
    String text;



    public void sendSecurityCode(Context context, String sendTo) {
        try {
            GMailSender gMailSender = new GMailSender(user, password);
            long now = System.currentTimeMillis();
            Date date = new Date(now);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); //소리 로그인 성공 시간
            String getTime = sdf.format(date);
            text = "※ 제가 로그인한 것이 아닙니다. → CLICK-HERE ";
            gMailSender.sendMail("[Soundlogin]"+ " 새로운 로그인 이력이 발견되었습니다.", "환영합니다 ! \n"+"\n" +" 사용자 님의 계정이 소리 로그인을 통해 로그인되었습니다."
                    +"\n\n"+"로그인 시간: "+getTime+"\n\n"+ text, sendTo);
            Toast.makeText(context, "사용자 님의 이메일로 로그인 성공 메일을 전송합니다.", Toast.LENGTH_SHORT).show();


        } catch (SendFailedException e) {
            Toast.makeText(context, "이메일 형식이 잘못되었습니다.", Toast.LENGTH_SHORT).show();
        } catch (MessagingException e) {
            Toast.makeText(context, "인터넷 연결을 확인해주십시오", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
            e.getMessage();
        } catch (Exception e) {
             }
    }
}