package com.example.bangladeshrailway;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
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

public class TrainTime extends AppCompatActivity {

    FirebaseFirestore firestore;
    ArrayList<ModelTrainRecycle> arrayList;
    RecyclerView recyclertraintime;
    AdapterTrainRecycle adapterTrainRecycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_time);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Train Time");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclertraintime=findViewById(R.id.containerTrain);
        recyclertraintime.setHasFixedSize(true);
        recyclertraintime.setLayoutManager(new LinearLayoutManager(this));

        arrayList= new ArrayList<>();
        adapterTrainRecycle = new AdapterTrainRecycle(arrayList, new AdapterTrainRecycle.itemClickListener() {
            @Override
            public void onItemClick(ModelTrainRecycle modelTrainRecycle) {
                Intent intent = new Intent(getApplicationContext(),SpecificTrainSchedule.class);
                String train = modelTrainRecycle.getTittle();
                intent.putExtra("train",train);
                startActivity(intent);
            }
        });
        recyclertraintime.setAdapter(adapterTrainRecycle);

        firestore = FirebaseFirestore.getInstance();
        firestore.collection("Train").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                for(DocumentSnapshot snapshot : list){
                    ModelTrainRecycle modelTrainRecycle = snapshot.toObject(ModelTrainRecycle.class);
                    arrayList.add(modelTrainRecycle);
                }
                adapterTrainRecycle.notifyDataSetChanged();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(TrainTime.this, "Failed to load image", Toast.LENGTH_SHORT).show();
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