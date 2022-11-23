package com.example.bangladeshrailway;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class UpTrain extends Fragment {

    FirebaseFirestore firestore;
    ArrayList<ModelUpTrain> arrayList;
    RecyclerView recycleruptrain;
    AdapterUpTrain adapterUpTrain;
    String station;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_up_train, container, false);

        recycleruptrain=view.findViewById(R.id.UPcontainterspecificstaion);
        station=getActivity().getIntent().getStringExtra("station");

        recycleruptrain.setHasFixedSize(true);
        recycleruptrain.setLayoutManager(new LinearLayoutManager(getActivity()));
        arrayList= new ArrayList<>();
        adapterUpTrain = new AdapterUpTrain(arrayList);
        recycleruptrain.setAdapter(adapterUpTrain);

        firestore = FirebaseFirestore.getInstance();
        firestore.collection("SourceDes").document(station).collection("up").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                for(DocumentSnapshot snapshot : list){
                    ModelUpTrain modelUpTrain = snapshot.toObject(ModelUpTrain.class);
                    arrayList.add(modelUpTrain);
                }
                adapterUpTrain.notifyDataSetChanged();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "Failed to load image", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}