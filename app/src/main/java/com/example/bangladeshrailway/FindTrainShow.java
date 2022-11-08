package com.example.bangladeshrailway;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.example.bangladeshrailway.databinding.ActivityMainBinding;
import com.example.bangladeshrailway.databinding.FragmentUserInfoBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class FindTrainShow extends AppCompatActivity {

    FirebaseFirestore firestore;
    String documentsearch;
    int i;
    RecyclerView recyclerView;
    MyAdapter myAdapter;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_train_show);

        ArrayList<String> availabletrains = new ArrayList<>();
        ArrayList<trainsearchinfo> AvailableTains = new ArrayList<>();


        recyclerView = findViewById(R.id.findtrainrecycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String from=getIntent().getStringExtra("fromuser");
        String to=getIntent().getStringExtra("touser");

        documentsearch = from+"-"+to;
//        Toast.makeText(this, documentsearch, Toast.LENGTH_SHORT).show();

        firestore=FirebaseFirestore.getInstance();
        firestore.collection("FindTrain").document(documentsearch).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

            }
        });

        myAdapter = new MyAdapter(FindTrainShow.this,AvailableTains);
        recyclerView.setAdapter(myAdapter);
        Toast.makeText(this, "Missing", Toast.LENGTH_SHORT).show();
        for( i=0;i<availabletrains.size();i++){

            ArrayList<String> counter = new ArrayList<>();
            ArrayList<String> online = new ArrayList<>();
            ArrayList<String> price = new ArrayList<>();
            ArrayList<String> time = new ArrayList<>();


            firestore.collection("FindTrain").document(documentsearch).collection(availabletrains.get(i)).document("counter").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if(task.isSuccessful()){
                        DocumentSnapshot documentSnapshot = task.getResult();
                            String ac_b= documentSnapshot.getString("AC_B");
                            String snigdha= documentSnapshot.getString("SNIGDHA");
                            String s_chair= documentSnapshot.getString("S_CHAIR");
                            String shovon= documentSnapshot.getString("SHOVON");
                            counter.add(ac_b);
                            counter.add(snigdha);
                            counter.add(s_chair);
                            counter.add(shovon);

                        firestore.collection("FindTrain").document(documentsearch).collection(availabletrains.get(i)).document("online").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if(task.isSuccessful()){
                                    DocumentSnapshot documentSnapshot = task.getResult();
                                    String ac_b= documentSnapshot.getString("AC_B");
                                    String snigdha= documentSnapshot.getString("SNIGDHA");
                                    String s_chair= documentSnapshot.getString("S_CHAIR");
                                    String shovon= documentSnapshot.getString("SHOVON");
                                    online.add(ac_b);
                                    online.add(snigdha);
                                    online.add(s_chair);
                                    online.add(shovon);
                                }
                            }
                        });


                        firestore.collection("FindTrain").document(documentsearch).collection(availabletrains.get(i)).document("price").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if(task.isSuccessful()){
                                    DocumentSnapshot documentSnapshot = task.getResult();
                                    String ac_b= documentSnapshot.getString("AC_B");
                                    String snigdha= documentSnapshot.getString("SNIGDHA");
                                    String s_chair= documentSnapshot.getString("S_CHAIR");
                                    String shovon= documentSnapshot.getString("SHOVON");
                                    price.add(ac_b);
                                    price.add(snigdha);
                                    price.add(s_chair);
                                    price.add(shovon);
                                }
                            }
                        });


                        firestore.collection("FindTrain").document(documentsearch).collection(availabletrains.get(i)).document("time").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if(task.isSuccessful()){
                                    DocumentSnapshot documentSnapshot = task.getResult();
                                    String departure= documentSnapshot.getString("departure");
                                    String arraival= documentSnapshot.getString("arraival");
                                    time.add(departure);
                                    time.add(arraival);
                                }
                            }
                        });


                        trainsearchinfo trains = new trainsearchinfo( time.toArray(new String[time.size()]), counter.toArray(new String[counter.size()]),online.toArray(new String[online.size()]),price.toArray(new String[price.size()]));
                        AvailableTains.add(trains);
                        myAdapter.notifyDataSetChanged();

                    }
                }
            });
        }


    }

}