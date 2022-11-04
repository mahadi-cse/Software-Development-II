package com.example.bangladeshrailway;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class Login extends Fragment {
    EditText et_email,et_pass;
    Button sign_up;
    FirebaseAuth auth;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);



        sign_up=view.findViewById(R.id.log_in);
        et_email=view.findViewById(R.id.email_login);
        et_pass=view.findViewById(R.id.pass_login);


        auth= FirebaseAuth.getInstance();

        sign_up.setOnClickListener(v -> {
            String email = et_email.getText().toString();
            String pass = et_pass.getText().toString();
            loginUser(email,pass);
        });
        // Inflate the layout for this fragment
        return view;
    }

    private void loginUser(String email, String pass) {
    auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(task -> {
    if(task.isSuccessful()){
        Toast.makeText(getActivity(), "Login Successful", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getActivity(), MainActivity2.class));
        requireActivity().finish();
    }
    else{
        Toast.makeText(getActivity(), "Login Failed", Toast.LENGTH_SHORT).show();
    }
    });
    }
}