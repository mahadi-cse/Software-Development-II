package com.example.bangladeshrailway;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class TicketFareShow extends AppCompatActivity {

    TextView ac_b,ac_s,snigdha,s_chair,shovon,route;

    String from,to,path;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_fare_show);

        from=getIntent().getStringExtra("from");
        to=getIntent().getStringExtra("to");
        path = from+"-"+to;

        ac_b=findViewById(R.id.ac_b);
        ac_s=findViewById(R.id.ac_s);
        snigdha=findViewById(R.id.snigdha);
        s_chair=findViewById(R.id.s_chair);
        shovon=findViewById(R.id.shovon);
        route=findViewById(R.id.path);

        route.setText(path);



        firestore= FirebaseFirestore.getInstance();
        firestore.collection("FindTrain").document(path).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        ModelFare modelFare = documentSnapshot.toObject(ModelFare.class);
                        ac_b.setText(modelFare.getAC_B());
                        ac_s.setText(modelFare.getAC_S());
                        snigdha.setText(modelFare.getSNIGDHA());
                        s_chair.setText(modelFare.getS_CHAIR());
                        shovon.setText(modelFare.getSHOVON());
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Failed to load Data", Toast.LENGTH_SHORT).show();
                    }
                });


    }
}