package com.hansung.android.soundlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity { //소리 로그인 준비 화면



    ListView listView; //로그인 이력 조회 리스트
    List fileList = new ArrayList<>();
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String User = intent.getExtras().getString("user_name");
        String USER = User.trim();
        System.out.println("USER1:"+USER);



        Button device_button = (Button) findViewById(R.id.Sound_Login); //소리 로그인 버튼
        Button record_button = (Button) findViewById(R.id.Record); // 로그인 이력 조회 버튼
        TextView User_text = (TextView) findViewById(R.id.user_text);
        User_text.setText(User+" 님");


        FirebaseDatabase database1 = FirebaseDatabase.getInstance();
        DatabaseReference databaseRef1 = database1.getReference("record"); //record database 불러오기

        listView = (ListView) findViewById(R.id.record_list); //로그인 이력 조회 리스트


        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, fileList){ //리스트 어댑터


            @Override

            public View getView(int position, View convertView, ViewGroup parent)

            {

                View view = super.getView(position, convertView, parent);

                TextView tv = (TextView) view.findViewById(android.R.id.text1);

                tv.setTextColor(Color.BLUE);


                return view;

            }

        };

        listView.setAdapter(adapter);

        device_button.setOnClickListener(new View.OnClickListener(){ // 소리 로그인 버튼 클릭 시 음성 인식 화면으로 넘어감
         @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), RegisActivity.class);
            intent.putExtra("user_name", User);
            startActivity(intent);
        }
    });
        record_button.setOnClickListener(new View.OnClickListener(){ //로그인 이력 조회 버튼 클릭 시
            @Override
            public void onClick(View view) {
                System.out.println("USER2:"+USER);

                databaseRef1.addValueEventListener(new ValueEventListener() { //firebase에서 데이터 불러와 띄우기
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot fileSnapshot : dataSnapshot.getChildren()) {
                            if(fileSnapshot.exists()) {
                                String name = fileSnapshot.child("name").getValue(String.class);
                                System.out.println("USER3:"+name);
                                if(USER.equals(name.trim())) {
                                    String time = fileSnapshot.child("time").getValue(String.class);
                                    System.out.println("로그인 이력 띄우기: "+time);
                                    fileList.add(time);

                                    adapter.notifyDataSetChanged();

                                }
                            }
                            else{ //로그인 이력이 없으면
                                Toast.makeText(MainActivity.this, "최근 로그인 이력이 없습니다.", Toast.LENGTH_SHORT).show();
                            }
                        }
                        adapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w("TAG: ", "Failed to read value", databaseError.toException());
                    }
                });

            }
        });


    }

    @Override
    public void onBackPressed() {
        //초반 플래시 화면에서 넘어갈때 뒤로가기 버튼 못누르게 함
    }
}
