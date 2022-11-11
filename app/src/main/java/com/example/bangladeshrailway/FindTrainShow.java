package com.example.bangladeshrailway;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
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
        myAdapter=new MyAdapter(arrayList);
        recyclerView.setAdapter(myAdapter);



        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Finding Train");
        progressDialog.show();

        firestore=FirebaseFirestore.getInstance();
        firestore.collection("FindTrain").document(documentsearch).collection("TrainsInRoute").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List <DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                for(DocumentSnapshot snapshot : list){
                    Model model = snapshot.toObject(Model.class);
                    arrayList.add(model);
                }
                myAdapter.notifyDataSetChanged();
                if (progressDialog.isShowing())
                    progressDialog.dismiss();

            }
        });






    }

}