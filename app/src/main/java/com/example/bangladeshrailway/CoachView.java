package com.example.bangladeshrailway;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CoachView extends AppCompatActivity {

    FirebaseFirestore firestore;
    ArrayList<CoachViewModel> arrayList;
    RecyclerView recyclerViewclass;
    CoachViewAdapter coachViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_view);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Coach View");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerViewclass=findViewById(R.id.class_recycle_container);
        recyclerViewclass.setHasFixedSize(true);
        recyclerViewclass.setLayoutManager(new LinearLayoutManager(this));

        arrayList= new ArrayList<>();
        coachViewAdapter = new CoachViewAdapter(arrayList,getApplicationContext());
        recyclerViewclass.setAdapter(coachViewAdapter);


        firestore = FirebaseFirestore.getInstance();
        firestore.collection("Class").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                for(DocumentSnapshot snapshot : list){
                    CoachViewModel coachViewModel = snapshot.toObject(CoachViewModel.class);
                    arrayList.add(coachViewModel);
                }
                coachViewAdapter.notifyDataSetChanged();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(CoachView.this, "Failed to load image", Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}