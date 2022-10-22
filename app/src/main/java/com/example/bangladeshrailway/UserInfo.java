package com.example.bangladeshrailway;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserInfo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserInfo extends Fragment {
    FirebaseAuth auth;
    FirebaseFirestore firestore;
    String logEmail;

    TextView name,email,nid,phone;
    String s_name,s_email,s_nid,s_phone;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UserInfo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserInfo.
     */
    // TODO: Rename and change types and number of parameters
    public static UserInfo newInstance(String param1, String param2) {
        UserInfo fragment = new UserInfo();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

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
        firestore.collection("UserInfo").document(logEmail).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
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
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}