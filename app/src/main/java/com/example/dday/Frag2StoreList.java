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

public class Frag2StoreList extends Fragment {

    private View view;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private  RecyclerView.LayoutManager layoutManager;
    private ArrayList<StoreList> arrayList;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag2_store_list, container, false);



        recyclerView = view.findViewById(R.id.recyclerView);  // 아이디 연결
        recyclerView.setHasFixedSize(true);  // 리사이클러뷰 기존성능 강화
        layoutManager = new LinearLayoutManager(getContext()); // ?
        recyclerView.setLayoutManager(layoutManager);
        arrayList = new ArrayList<>(); // User 객체를 담을 arraylist (to adapter)

        database = FirebaseDatabase.getInstance(); // 파이어베이스 데이터베이스 연동

        databaseReference = database.getReference("user"); // DB 테이블 연결
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) { // 파이어베이스 데이터베이스의 데이터를 받아오는곳
                arrayList.clear(); // 기존 배열리스트 초기화
                for(DataSnapshot snapshot1 : snapshot.getChildren()){ // 반복문으로 데이터 list 추출
                    StoreList store = snapshot1.getValue(StoreList.class); // 만들어뒀던 User 객체에 데이터를 담는다
                    arrayList.add(store); // 담은 데이터들을 배열리스트에 넣고 리사이클러뷰로 보낼 준비
                }
                adapter.notifyDataSetChanged(); // 리스트 저장 및 새로고침
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { //디비를 가져오던 중 에러 발생 시
            }
        });

        adapter = new StoreListAdapter(arrayList, getContext());
        recyclerView.setAdapter(adapter);  // 리사이클러뷰에 어댑터 연결


        return view;
    }

}

