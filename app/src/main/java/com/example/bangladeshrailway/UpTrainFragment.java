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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class UpTrainFragment extends Fragment {

    FirebaseFirestore firestore;
    ArrayList<ModelUpTrainFrag> arrayList;
    RecyclerView recycletraindownfrag;
    AdapterUpTrainFrag adapterUpTrainFrag;
    String train;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_up_train2, container, false);
        train=getActivity().getIntent().getStringExtra("train");

        recycletraindownfrag=view.findViewById(R.id.trainuprecycle);
        recycletraindownfrag.setHasFixedSize(true);
        recycletraindownfrag.setLayoutManager(new LinearLayoutManager(getActivity()));
        arrayList= new ArrayList<>();
        adapterUpTrainFrag = new AdapterUpTrainFrag(arrayList);
        recycletraindownfrag.setAdapter(adapterUpTrainFrag);

        firestore = FirebaseFirestore.getInstance();
        firestore.collection("Train").document(train).collection("up").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                for(DocumentSnapshot snapshot : list){
                    ModelUpTrainFrag modelUpTrainFrag = snapshot.toObject(ModelUpTrainFrag.class);
                    arrayList.add(modelUpTrainFrag);
                }
                adapterUpTrainFrag.notifyDataSetChanged();
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