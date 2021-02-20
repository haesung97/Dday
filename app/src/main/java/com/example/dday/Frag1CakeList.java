package com.example.dday;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Frag1CakeList extends Fragment {

    private View view;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private  RecyclerView.LayoutManager layoutManager;
    private ArrayList<CakeList> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag1_cake_list, container, false);



        recyclerView = view.findViewById(R.id.recyclerView);  // 아이디 연결
        recyclerView.setHasFixedSize(true);  // 리사이클러뷰 기존성능 강화
        layoutManager = new LinearLayoutManager(getContext()); // ?
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>(); // CakeList 객체를 담을 arraylist (to adapter)

        database = FirebaseDatabase.getInstance(); // 파이어베이스 데이터베이스 연동

        databaseReference = database.getReference("business"); // DB 테이블 연결
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) { // 파이어베이스 데이터베이스의 데이터를 받아오는곳
                arrayList.clear(); // 기존 배열리스트 초기화
                for(DataSnapshot snapshot1 : snapshot.getChildren()){ // 반복문으로 데이터 list 추출
                    CakeList cake = snapshot1.getValue(CakeList.class); // 만들어뒀던 CakeList 객체에 데이터를 담는다
                    arrayList.add(cake); // 담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비
                }
                adapter.notifyDataSetChanged(); // 리스트 저장 및 새로고침
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { //디비를 가져오던 중 에러 발생 시
            }
        });

        adapter = new CakeListAdapter(arrayList, getContext());
        recyclerView.setAdapter(adapter);  // 리사이클러뷰에 어댑터 연결


        return view;
    }



}

