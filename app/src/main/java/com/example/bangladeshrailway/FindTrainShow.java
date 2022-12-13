package com.example.bangladeshrailway;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;

public class FindTrainShow extends AppCompatActivity {

    FirebaseFirestore firestore;
    String documentsearch;
    RecyclerView recyclerView;
    ArrayList<Model> arrayList;
    MyAdapter myAdapter;
    ProgressDialog progressDialog;


    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_train_show);


        recyclerView = findViewById(R.id.findtrainrecycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String from=getIntent().getStringExtra("fromuser");
        String to=getIntent().getStringExtra("touser");

        documentsearch = from+"-"+to;
        Toast.makeText(this, documentsearch, Toast.LENGTH_SHORT).show();

        arrayList=new ArrayList<>();
        myAdapter=new MyAdapter(arrayList, new MyAdapter.itemClickListener() {
            @Override
            public void onItemClick(Model model) {
                Intent intent = new Intent(getApplicationContext(),SeatSelcetion.class);

                String From = model.getFrom();
                String to = model.getTo();
                String trainname = model.getName();
                String deptTime=model.getDepartureTime();


                String date = getIntent().getStringExtra("date");
                String classp = getIntent().getStringExtra("class");

                String price= model.getPrice(classp);


                intent.putExtra("from",from);
                intent.putExtra("to",to);
                intent.putExtra("trainname",trainname);
                intent.putExtra("date",date);
                intent.putExtra("class",classp);
                intent.putExtra("deptTime",deptTime);
                intent.putExtra("price",price);

                Toast.makeText(FindTrainShow.this, model.getName(), Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(myAdapter);



        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Finding Train");
        progressDialog.show();

        firestore=FirebaseFirestore.getInstance();
        firestore.collection("FindTrain").document(documentsearch).collection("TrainsInRoute").get().addOnSuccessListener(queryDocumentSnapshots -> {
            List <DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
            for(DocumentSnapshot snapshot : list){
                Model model = snapshot.toObject(Model.class);
                arrayList.add(model);
            }
            myAdapter.notifyDataSetChanged();
            if (progressDialog.isShowing())
                progressDialog.dismiss();

        });






    }

}