package com.example.dday;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import android.content.Intent;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.google.firebase.auth.FirebaseAuth;

public class MainPageActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private Frag1CakeList frag1;
    private Frag2StoreList frag2;
    private Frag3Design frag3;
    private Frag4MyPage frag4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        Toolbar toolbar = findViewById(R.id.toolbar);// 툴바 추가
        setSupportActionBar(toolbar);  // 툴바 추가
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        if(FirebaseAuth.getInstance().getCurrentUser() == null){    // 로그인 정보 없을시
            startMainActivity();
        }else{  // 회원가입 or 로그인 된 상태태

       }

      //  findViewById(R.id.logoutButton).setOnClickListener(onClickListener);

        bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_1:
                        setFrag(0);
                        break;
                    case R.id.navigation_2:
                        setFrag(1);
                        break;
                    case R.id.navigation_3:
                        setFrag(2);
                        break;
                    case R.id.navigation_4:
                        setFrag(3);
                        break;
                }
                return true;
            }
        });

        frag1 = new Frag1CakeList();
        frag2 = new Frag2StoreList();
        frag3 = new Frag3Design();
        frag4 = new Frag4MyPage();
        setFrag(0); //첫 프래그먼트 화면을 무엇으로 지정해 줄 것인지 선택.
    }

    // 프래그먼트 교체가 일어나는 실행문
    private void setFrag(int n) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch(n) {
            case 0 :
                ft.replace(R.id.mainFrame, frag1);
                ft.commit();
                break;
            case 1 :
                ft.replace(R.id.mainFrame, frag2);
                ft.commit();
                break;
            case 2 :
                ft.replace(R.id.mainFrame, frag3);
                ft.commit();
                break;
            case 3 :
                ft.replace(R.id.mainFrame, frag4);
                ft.commit();
                break;
        }
    }

   // View.OnClickListener onClickListener = new View.OnClickListener() {
      //  @Override
        //public void onClick(View v) {
           // switch(v.getId()){
                //case R.id.logoutButton:
                   // FirebaseAuth.getInstance().signOut();
                    //startMainActivity();
                    //break;
            //}
        //}
   // };

    private void startMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}