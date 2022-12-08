package com.example.bangladeshrailway;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class VerifyTicket extends AppCompatActivity {

    TextView currentorissu,jpurnydate,trainname,from,to,classname,coachname,seat,price,total;
    EditText pnr;
    Button submit;
    FirebaseFirestore firestore;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_ticket);
        getSupportActionBar().setTitle("Verify Ticket");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        currentorissu=findViewById(R.id.currentorissu);
        jpurnydate=findViewById(R.id.jpurnydate);
        trainname=findViewById(R.id.trainname);
        from=findViewById(R.id.from);
        to=findViewById(R.id.to);
        classname=findViewById(R.id.classname);
        coachname=findViewById(R.id.coachname);
        seat=findViewById(R.id.seat);
        price=findViewById(R.id.price);
        total=findViewById(R.id.totalprice);

        relativeLayout=findViewById(R.id.relativeforticket);
        relativeLayout.setVisibility(View.GONE);

        pnr=findViewById(R.id.etpnr);
        submit=findViewById(R.id.submitbutton);

        submit.setOnClickListener(v -> {

           String pnrt= pnr.getText().toString();
           firestore  = FirebaseFirestore.getInstance();
           firestore.collection("PNR").document(pnrt).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
               @Override
               public void onSuccess(DocumentSnapshot documentSnapshot) {

                    ModelVerifyTicket modelVerifyTicket = documentSnapshot.toObject(ModelVerifyTicket.class);

                   currentorissu.setText(modelVerifyTicket.getCurrentTime());
                   jpurnydate.setText(modelVerifyTicket.getDate()+modelVerifyTicket.getDeptTime());
                   trainname .setText(modelVerifyTicket.getTrainname());
                    from.setText(modelVerifyTicket.getFrom());
                   to .setText(modelVerifyTicket.getTo());
                   classname.setText(modelVerifyTicket.getClass_seat());
                   coachname .setText(modelVerifyTicket.getCoach());
                   seat.setText(modelVerifyTicket.getSeat());
                   price.setText(modelVerifyTicket.getPrice()+" BDT");
                   total.setText(modelVerifyTicket.getPrice()+" BDT");

                   relativeLayout.setVisibility(View.VISIBLE);
                     }
           });
        });

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}