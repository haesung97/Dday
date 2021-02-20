package com.example.dday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onBackPressed(){    // 뒤로가기 누르면 종료
        super.onBackPressed();
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    public void onChangeActivityuser(View V){
        Intent intent = new Intent(this, SignInUserActivity.class);
        startActivity(intent);
    }

    public void onChangeActivitybusiness(View V){
        Intent intent1 = new Intent(this, SignInBusinessActivity.class);
        startActivity(intent1);
    }
}

