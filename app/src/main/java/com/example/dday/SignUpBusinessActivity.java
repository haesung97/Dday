package com.example.dday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class SignUpBusinessActivity extends AppCompatActivity {

    private Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign_up_business);


        signUpButton = findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpBusinessActivity.this, SignUpUserActivity.class);
                startActivity(intent); // 엑티비티 이동
            }
        });

    }

    public void onBackButtonClicked(View v){
        finish();
        //Intent intent = new Intent(this,MainActivity.class);
        //startActivity(intent);

    }
}

