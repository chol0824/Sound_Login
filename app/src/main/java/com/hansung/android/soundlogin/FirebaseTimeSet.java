package com.hansung.android.soundlogin;

public class FirebaseTimeSet {// Firebase에 올릴 시간과 사용자 이름
    public String time;
    public String name;


    public FirebaseTimeSet(){
        // Default constructor required for c, alls to DataSnapshot.getValue(FirebasePost.class)
    }

    public FirebaseTimeSet(String time, String name) {
        this.time = time;
        this.name = name;

    }
}
