package com.example.bangladeshrailway;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Tickets extends Fragment {

    FirebaseFirestore firestore;
    ArrayList<ModelTicket> arrayList;
    RecyclerView recycleticket;
    AdapterTicket adapterTicket;
    FirebaseAuth auth;
    String logEmail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_tickets, container, false);

        recycleticket=view.findViewById(R.id.ticketrecycle);
        recycleticket.setHasFixedSize(true);
        recycleticket.setLayoutManager(new LinearLayoutManager(getActivity()));
        arrayList= new ArrayList<>();
        adapterTicket = new AdapterTicket(arrayList, new AdapterTicket.itemClickListener() {
            @Override
            public void onItemClick(ModelTicket modelTicket) {
                Toast.makeText(getActivity(), modelTicket.getPnr(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(),TicketShow.class);
                startActivity(intent);
            }
        });
        recycleticket.setAdapter(adapterTicket);


        auth= FirebaseAuth.getInstance();
        if(auth.getCurrentUser()!=null){
            logEmail=auth.getCurrentUser().getEmail();
        }

        firestore = FirebaseFirestore.getInstance();
        firestore.collection("UserInfo").document(logEmail).collection("tickets").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                for(DocumentSnapshot snapshot : list){
                    ModelTicket modelTicket = snapshot.toObject(ModelTicket.class);
                    arrayList.add(modelTicket);
                }
                adapterTicket.notifyDataSetChanged();
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