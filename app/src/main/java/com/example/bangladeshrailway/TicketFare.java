package com.example.bangladeshrailway;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Objects;

public class TicketFare extends AppCompatActivity {

    ProgressDialog progressDialog;
    AutoCompleteTextView from,to;
    FirebaseFirestore firestore;
    Button submit;
    public String fromUser,toUser,path;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_fare);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Ticket Fare");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        from=findViewById(R.id.from_des);
        to=findViewById(R.id.to_des);
        submit = findViewById(R.id.sub_find_train);

        Intent intent = new Intent(getApplicationContext(),TicketFareShow.class);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Getting Data");
        progressDialog.show();

        ArrayList<String> Location = new ArrayList<>();
        ArrayAdapter<String> source = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,Location);
        from.setAdapter(source);
        to.setAdapter(source);

        firestore= FirebaseFirestore.getInstance();
        firestore.collection("SourceDes").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot snapshot : task.getResult()){
                        Location.add(snapshot.getId());
                    }
                    if (progressDialog.isShowing())
                        progressDialog.dismiss();

                }
            }
        });


        from.setOnItemClickListener((parent, view, position, id) -> {
            fromUser = parent.getItemAtPosition(position).toString();
            intent.putExtra("from",fromUser);
        });

        to.setOnItemClickListener((parent, view, position, id) -> {
            toUser = parent.getItemAtPosition(position).toString();
            intent.putExtra("to",toUser);
        });





        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(intent);
            }
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