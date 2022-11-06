package com.example.bangladeshrailway;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserInfo extends Fragment {
    FirebaseAuth auth;
    FirebaseFirestore firestore;
    String logEmail;

    TextView name,email,nid,phone;
    String s_name,s_email,s_nid,s_phone;


    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_info, container, false);
        name=view.findViewById(R.id.name_show);
        nid=view.findViewById(R.id.nid_show);
        phone=view.findViewById(R.id.phone_show);
        email=view.findViewById(R.id.email_show);
        auth=FirebaseAuth.getInstance();
        if(auth.getCurrentUser()!=null){
            logEmail=auth.getCurrentUser().getEmail();
        }
        else{
            Toast.makeText(getActivity(), "No users found", Toast.LENGTH_SHORT).show();
        }
        firestore=FirebaseFirestore.getInstance();
        firestore.collection("UserInfo").document(logEmail).get().addOnCompleteListener(task -> {
            DocumentSnapshot documentSnapshot = task.getResult();
            if(documentSnapshot!=null && documentSnapshot.exists()){
            s_name=documentSnapshot.getString("name");
            s_nid=documentSnapshot.getString("nid");
            s_phone=documentSnapshot.getString("phone");
            s_email=documentSnapshot.getString("email");

            name.setText("Name : "+s_name);
            email.setText("Email  : "+s_email);
            nid.setText("NID : "+s_nid);
            phone.setText("Phone : "+s_phone);
            }
        }).addOnFailureListener(e -> Toast.makeText(getActivity(), "Error"+e.getMessage(), Toast.LENGTH_SHORT).show());
        return view;
    }
}