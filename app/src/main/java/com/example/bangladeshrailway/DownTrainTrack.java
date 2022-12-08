package com.example.bangladeshrailway;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
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


public class DownTrainTrack extends Fragment {

    FirebaseFirestore firestore;
    ArrayList<ModelTrack> arrayList;
    RecyclerView recyclerdontrack;
    AdapterDownTrainTrack adapterDownTrainTrack;
    public String code;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_down_train_track, container, false);

        recyclerdontrack=view.findViewById(R.id.recycle_down_track);

        recyclerdontrack.setHasFixedSize(true);
        recyclerdontrack.setLayoutManager(new LinearLayoutManager(getActivity()));
        arrayList= new ArrayList<>();
        adapterDownTrainTrack = new AdapterDownTrainTrack(arrayList, new AdapterDownTrainTrack.itemClickListener() {
            @Override
            public void onItemClick(ModelTrack modelTrack) {
                code="TR "+modelTrack.getCode();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms","16318",null));
                intent.putExtra("sms_body",code);
                startActivity(intent);
            }
        });
        recyclerdontrack.setAdapter(adapterDownTrainTrack);

        firestore = FirebaseFirestore.getInstance();
        firestore.collection("Down").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                for(DocumentSnapshot snapshot : list){
                    ModelTrack modelTrack = snapshot.toObject(ModelTrack.class);
                    arrayList.add(modelTrack);
                }
                adapterDownTrainTrack.notifyDataSetChanged();
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