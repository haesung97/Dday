package com.example.dday;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class SignInUserActivity extends AppCompatActivity {
    private static final String TAG = "SignIpUserActivity";
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_user);

        Toolbar toolbar = findViewById(R.id.toolbar);// 툴바 추가
        setSupportActionBar(toolbar);  // 툴바 추가
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // 툴바 추가

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.signInButton).setOnClickListener(onClickListener);
        findViewById(R.id.signUpButton).setOnClickListener(onClickListener);
        findViewById(R.id.passwordResetButton).setOnClickListener(onClickListener);

    }

   @Override  // 툴바 기능
    public boolean onOptionsItemSelected(MenuItem item) {
       switch(item.getItemId()){
            case android.R.id.home:{
                finish();
                return true;
           }
        }
        return super.onOptionsItemSelected(item);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {  // 버튼 기능
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.signInButton:
                    signIn();
                    break;
                case R.id.signUpButton:
                    startMyActivity(SignUpUserActivity.class);
                    break;
                case R.id.passwordResetButton:
                    startMyActivity(PasswordResetActivity.class);
                    break;
            }
        }
    };


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    private void signIn() {
        String email = ((EditText)findViewById(R.id.idEditText)).getText().toString();
        String password = ((EditText)findViewById(R.id.passwordEditText)).getText().toString();

        if(email.length() > 0 && password.length() > 0){
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                startToast("로그인을 성공했습니다.");
                                startMainPageActivity();

                            } else { // 실패 했을 때 UI 로직
                                if(task.getException() != null){
                                    startToast(task.getException().toString());
                                }
                            }

                            // ...
                        }
                    });
        }else{
            startToast("이메일 또는 비밀번호를 입력해 주세요.");
        }
    }

    private void startMainPageActivity(){
        Intent intent = new Intent(this, MainPageActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    private void startMyActivity(Class c){
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }

    private void startToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}