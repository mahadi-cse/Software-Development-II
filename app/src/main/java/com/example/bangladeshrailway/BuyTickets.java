package com.example.bangladeshrailway;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class BuyTickets extends AppCompatActivity  {

    String fromUser,toUser;
    TextView etDate;
    Button btnTrainSubmit;
    AutoCompleteTextView from,to;
    FirebaseFirestore firestore;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_tickets);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Buy Tickets");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        etDate = findViewById(R.id.date);
        from=findViewById(R.id.from_des);
        to=findViewById(R.id.to_des);

       Intent intent = new Intent(BuyTickets.this,FindTrainShow.class);

        etDate.setGravity(Gravity.CENTER_VERTICAL );
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        ArrayList<String> Location = new ArrayList<>();
        ArrayAdapter<String> source = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,Location);
        from.setAdapter(source);
        to.setAdapter(source);


        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Getting Data");
        progressDialog.show();

        firestore=FirebaseFirestore.getInstance();
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

       from.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               fromUser = parent.getItemAtPosition(position).toString();
                intent.putExtra("fromuser",fromUser);
           }
       });
        to.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                toUser = parent.getItemAtPosition(position).toString();
                intent.putExtra("touser",toUser);
            }
        });

        etDate.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    BuyTickets.this, (view, year1, month1, day1) -> {
                        month1 +=1;
                        String date = day1 +"-"+ month1 +"-"+ year1;
                        intent.putExtra("date",date);
                        etDate.setText(date);
                    },year,month,day);
            long time = 777600000 ;
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
            datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis()+time);
            datePickerDialog.show();
        });
        Spinner spinner = findViewById(R.id.spinner_class);
        Spinner personSelect = findViewById(R.id.person_spinner);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.class_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String textClass = parent.getItemAtPosition(position).toString();
                intent.putExtra("class",textClass);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



        ArrayAdapter<CharSequence> adapterPerson = ArrayAdapter.createFromResource(this,
                R.array.person_count, android.R.layout.simple_spinner_item);
        adapterPerson.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        personSelect.setAdapter(adapterPerson);
        personSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String textPerson = parent.getItemAtPosition(position).toString();
//                Toast.makeText(BuyTickets.this, textPerson, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnTrainSubmit= findViewById(R.id.sub_find_train);
        btnTrainSubmit.setOnClickListener(v -> {
//            Toast.makeText(BuyTickets.this, fromUser, Toast.LENGTH_SHORT).show();
            startActivity(intent);
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