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


public class Register extends Fragment {
    EditText et_email,et_password,et_name,et_phone,et_nid;
    Button register;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        register=view.findViewById(R.id.sign_up);
            register.setOnClickListener(v -> {


       et_email= view.findViewById(R.id.email);
    et_password=view.findViewById(R.id.password_res);
    et_name=view.findViewById(R.id.name);
    et_phone=view.findViewById(R.id.phone_number);
    et_nid=view.findViewById(R.id.nid);

//
    String phone_number = et_phone.getText().toString();

    String email = et_email.getText().toString();
    String name = et_name.getText().toString();
    String nid = et_nid.getText().toString();
    String pass = et_password.getText().toString();

    Intent intent = new Intent(getActivity(),OTPVerrifcation.class);
    intent.putExtra("name",name);
    intent.putExtra("nid",nid);
    intent.putExtra("phone_number",phone_number);
    intent.putExtra("email",email);
    intent.putExtra("pass",pass);


    Toast.makeText(getActivity(), "Verification process going on", Toast.LENGTH_SHORT).show();
    startActivity(intent);
            });
//
        return view;
    }
}