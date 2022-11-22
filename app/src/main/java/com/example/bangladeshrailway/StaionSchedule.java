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

public class StaionSchedule extends AppCompatActivity {
    FirebaseFirestore firestore;
    ArrayList<ModelStaionSchedule> arrayList;
    RecyclerView recyclerstation;
    AdapterStaionSchedule adapterStaionSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staion_schedule);
        getSupportActionBar().setTitle("Station Schedule");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerstation=findViewById(R.id.containerStation);
        recyclerstation.setHasFixedSize(true);
        recyclerstation.setLayoutManager(new LinearLayoutManager(this));

        arrayList= new ArrayList<>();
        adapterStaionSchedule = new AdapterStaionSchedule(arrayList);
        recyclerstation.setAdapter(adapterStaionSchedule);

        firestore = FirebaseFirestore.getInstance();
        firestore.collection("SourceDes").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                for(DocumentSnapshot snapshot : list){
                    ModelStaionSchedule modelStaionSchedule = snapshot.toObject(ModelStaionSchedule.class);
                    arrayList.add(modelStaionSchedule);
                }
                adapterStaionSchedule.notifyDataSetChanged();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(StaionSchedule.this, "Failed to load image", Toast.LENGTH_SHORT).show();
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