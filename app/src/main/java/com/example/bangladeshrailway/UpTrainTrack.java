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


public class UpTrainTrack extends Fragment {

    FirebaseFirestore firestore;
    ArrayList<ModelTrack> arrayList;
    RecyclerView recycleuptraintrack;
    AdapterUpTrainTrack adapterUpTrainTrack;
    public String code;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_up_train_track, container, false);


        recycleuptraintrack=view.findViewById(R.id.recycle_up_track);


        recycleuptraintrack.setHasFixedSize(true);
        recycleuptraintrack.setLayoutManager(new LinearLayoutManager(getActivity()));
        arrayList= new ArrayList<>();
        adapterUpTrainTrack = new AdapterUpTrainTrack(arrayList, new AdapterUpTrainTrack.itemClickListener() {
            @Override
            public void onItemClick(ModelTrack modelTrack) {
                code="TR "+modelTrack.getCode();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.fromParts("sms","16318",null));
                intent.putExtra("sms_body",code);
                startActivity(intent);
            }
        });
        recycleuptraintrack.setAdapter(adapterUpTrainTrack);

        firestore = FirebaseFirestore.getInstance();
        firestore.collection("Up").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                for(DocumentSnapshot snapshot : list){
                    ModelTrack modelTrack = snapshot.toObject(ModelTrack.class);
                    arrayList.add(modelTrack);
                }
                adapterUpTrainTrack.notifyDataSetChanged();
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