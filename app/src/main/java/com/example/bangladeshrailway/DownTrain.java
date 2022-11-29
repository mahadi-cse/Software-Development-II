package com.example.bangladeshrailway;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
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
import java.util.Objects;

public class DownTrain extends Fragment {

    FirebaseFirestore firestore;
    ArrayList<ModelUpTrain> arrayList;
    RecyclerView recyclerdowntrain;
    AdapterUpTrain adapterdowntrain;
    String station;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            requireActivity().finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_down_train, container, false);
        recyclerdowntrain=view.findViewById(R.id.Downcontainterspecificstaion);
        station=getActivity().getIntent().getStringExtra("station");

        ((SpecificStationSchedule) getActivity()).setTitle(station);
        Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(((AppCompatActivity) getActivity()).getSupportActionBar()).setDisplayShowHomeEnabled(true);

        recyclerdowntrain.setHasFixedSize(true);
        recyclerdowntrain.setLayoutManager(new LinearLayoutManager(getActivity()));
        arrayList= new ArrayList<>();
        adapterdowntrain = new AdapterUpTrain(arrayList);
        recyclerdowntrain.setAdapter(adapterdowntrain);

        firestore = FirebaseFirestore.getInstance();
        firestore.collection("SourceDes").document(station).collection("down").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                for(DocumentSnapshot snapshot : list){
                    ModelUpTrain modelUpTrain = snapshot.toObject(ModelUpTrain.class);
                    arrayList.add(modelUpTrain);
                }
                adapterdowntrain.notifyDataSetChanged();
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