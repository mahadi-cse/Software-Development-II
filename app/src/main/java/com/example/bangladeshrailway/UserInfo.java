package com.example.bangladeshrailway;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
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
import com.google.android.gms.tasks.OnSuccessListener;
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
    ProgressDialog progressDialog;


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
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Getting User Data...");
        progressDialog.show();
        firestore=FirebaseFirestore.getInstance();
        firestore.collection("UserInfo").document(logEmail).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {

                ModelUser modelUser = documentSnapshot.toObject(ModelUser.class);

                name.setText("Name : "+modelUser.getName());
                email.setText("Email  : "+modelUser.getEmail());
                nid.setText("NID : "+modelUser.getNid());
                phone.setText("Phone : "+modelUser.getPhone());
                if (progressDialog.isShowing())
                    progressDialog.dismiss();
            }
        });
        return view;
    }
}