package com.example.bangladeshrailway;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class TrainList extends AppCompatActivity {
    Button btn_sb_add;
    EditText  routes,trainnameaddd,counterac_b,countersnigdha,counters_chair,countershovon,
            onlineac_b,onlinesnigdha,onlines_chair,onlineshovon,
            priceac_b,pricesnigdha,prices_chair,priceshovon , from,to,arraivaltime,departuretime,name;

    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_list);
        getSupportActionBar().setTitle("Train List");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        counterac_b=findViewById(R.id.counter_ab_add);
        counters_chair=findViewById(R.id.counter_chair_add);
        countersnigdha=findViewById(R.id.counter_snigdha_add);
        countershovon=findViewById(R.id.counter_shovon_add);

        onlineac_b=findViewById(R.id.online_ac_b_add);
        onlines_chair=findViewById(R.id.online_chair_add);
        onlinesnigdha=findViewById(R.id.online_snigdha_add);
        onlineshovon=findViewById(R.id.online_shovon_add);


        priceac_b=findViewById(R.id.ac_b_price_add);
        prices_chair=findViewById(R.id.chair_price_add);
        pricesnigdha=findViewById(R.id.snigdha_price_add);
        priceshovon=findViewById(R.id.shovon_price_add);


        name=findViewById(R.id.nameinmap);
        from=findViewById(R.id.fromadd);
        to=findViewById(R.id.toadd);
        arraivaltime=findViewById(R.id.arravaladd);
        departuretime=findViewById(R.id.depttimeadd);

        btn_sb_add=findViewById(R.id.submit_add);


        routes=findViewById(R.id.routesadd);
        trainnameaddd=findViewById(R.id.trainnameadd);

        btn_sb_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firestore=FirebaseFirestore.getInstance();

                        Map<String,Object> info = new HashMap<>();
                        info.put("From",from.getText().toString());
                        info.put("To",to.getText().toString());
                        info.put("Name",name.getText().toString());
                        info.put("arraivalTime",arraivaltime.getText().toString());
                        info.put("departureTime",arraivaltime.getText().toString());

                        info.put("counterAC_B",counterac_b.getText().toString());
                        info.put("counterSNIGDHA",countersnigdha.getText().toString());
                        info.put("counterS_CHAIR",counters_chair.getText().toString());
                        info.put("counterSHOVON",countershovon.getText().toString());

                        info.put("onlineAC_B",onlineac_b.getText().toString());
                        info.put("onlineSNIGDHA",onlinesnigdha.getText().toString());
                        info.put("onlineS_CHAIR",onlines_chair.getText().toString());
                        info.put("onlineSHOVON",onlineshovon.getText().toString());

                        info.put("priceAC_B",priceac_b.getText().toString());
                        info.put("priceSNIGDHA",pricesnigdha.getText().toString());
                        info.put("priceS_CHAIR",prices_chair.getText().toString());
                        info.put("priceSHOVON",priceshovon.getText().toString());

                firestore.collection("FindTrain").document(routes.getText().toString()).
                        collection("TrainsInRoute").document(trainnameaddd.getText().toString()).
                        set(info).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(TrainList.this, "Data Added", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(TrainList.this, "Data add failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
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