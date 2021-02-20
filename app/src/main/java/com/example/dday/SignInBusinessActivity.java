package com.example.dday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class SignInBusinessActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_business);


    }

    public void onBackButtonClicked(View v){
        finish();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);

    }
}