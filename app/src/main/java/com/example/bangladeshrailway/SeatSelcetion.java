package com.example.bangladeshrailway;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SeatSelcetion extends AppCompatActivity implements View.OnClickListener {
    TextView trinnameTV,arraiveselection,departureselection,deparaturetimeselection,arraivetimeselection;

    CardView s_42,s_43;
    CardView gha,neo;

    String seat;
    String coach,price;

    Button continueSelection;

    String logEmail,from,to,trainname,route,date,classp,s_name,s_email,s_nid,s_phone,deptTime,arraivalTime;

    FirebaseAuth auth;
    FirebaseFirestore firestore;
    String pnr;
    char ch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_selcetion);


        s_42=findViewById(R.id.s_42);
        s_43=findViewById(R.id.s_43);

        gha=findViewById(R.id.gha);
        neo=findViewById(R.id.neo);

        trinnameTV=findViewById(R.id.trainnameselection);
        arraiveselection=findViewById(R.id.arraiveselection);
        departureselection=findViewById(R.id.departureselection);
        deparaturetimeselection=findViewById(R.id.deparaturetimeselection);
        arraivetimeselection=findViewById(R.id.arraivetimeselection);

        continueSelection=findViewById(R.id.continuebtn);

        s_42.setOnClickListener(this);
        s_43.setOnClickListener(this);

        gha.setOnClickListener(this);
        neo.setOnClickListener(this);

        auth= FirebaseAuth.getInstance();
        if(auth.getCurrentUser()!=null){
            logEmail=auth.getCurrentUser().getEmail();
        }

        from=getIntent().getStringExtra("from");
        to=getIntent().getStringExtra("to");
        route=from+"-"+to;
        trainname=getIntent().getStringExtra("trainname");
        date=getIntent().getStringExtra("date");
        classp=getIntent().getStringExtra("class");
        deptTime=getIntent().getStringExtra("deptTime");
        price=getIntent().getStringExtra("price");
        arraivalTime=getIntent().getStringExtra("arraivalTime");

        trinnameTV.setText(trainname);
        arraiveselection.setText(to);
        departureselection.setText(from);
        deparaturetimeselection.setText(deptTime);
        arraivetimeselection.setText(arraivalTime);



        Random random = new Random();
        Random rd = new Random();
        ch = (char) (97 + rd.nextInt(26));

        pnr=String.valueOf(ch)+random.nextInt(1000000);

        firestore = FirebaseFirestore.getInstance();
        firestore.collection("UserInfo").document(logEmail).get().addOnCompleteListener(task -> {
            DocumentSnapshot documentSnapshot = task.getResult();
            if(documentSnapshot!=null && documentSnapshot.exists()) {
                s_name = documentSnapshot.getString("name");
                s_nid = documentSnapshot.getString("nid");
                s_phone = documentSnapshot.getString("phone");
            }

            });


        Calendar calendar = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy : hh:mm:ss a");
        String currentTime = simpleDateFormat.format(calendar.getTime());

        continueSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> info = new HashMap<>();
                info.put("from",from);
                info.put("to",to);
                info.put("trainname",trainname);
                info.put("email",logEmail);
                info.put("seat",seat);
                info.put("pnr",pnr);
                info.put("route",route);
                info.put("date",date);
                info.put("name",s_name);
                info.put("nid",s_nid);
                info.put("phone",s_phone);
                info.put("class_seat",classp);
                info.put("coach",coach);
                info.put("deptTime",deptTime);
                info.put("currentTime",currentTime);
                info.put("price",price);

                firestore = FirebaseFirestore.getInstance();
                
                firestore.collection("PNR").document(pnr).set(info).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
//                        Toast.makeText(SeatSelcetion.this, "Added to PNR", Toast.LENGTH_SHORT).show();
                    }
                });

                firestore.collection("UserInfo").document(logEmail).collection("tickets").
                        document(String.valueOf(pnr)).set(info).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
//                                Toast.makeText(SeatSelcetion.this, "Created", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),TicketConfrimed.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(SeatSelcetion.this, "Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.s_42:
                s_42.setBackgroundColor(ContextCompat.getColor(this, R.color.seatselect));
                seat = "42";
//                Toast.makeText(this, seat, Toast.LENGTH_SHORT).show();
                break;

                case R.id.s_43:
               s_43.setBackgroundColor(ContextCompat.getColor(this, R.color.seatselect));
                seat = "43";
//                Toast.makeText(this, seat, Toast.LENGTH_SHORT).show();
                break;

            case R.id.gha:
                gha.setBackgroundColor(ContextCompat.getColor(this, R.color.coach));
                coach="GHA";
                break;

            case R.id.neo:
                neo.setBackgroundColor(ContextCompat.getColor(this, R.color.coach));
                coach="NEO";
                break;

        }

    }
}