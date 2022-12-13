package com.example.bangladeshrailway;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class TicketShow extends AppCompatActivity {

    TextView currentorissu,jpurnydate,trainname,from,to,classname,coachname,seat,price,total,phone,pnrT,name,nid,dear;
    FirebaseFirestore firestore;
    FirebaseAuth auth;
    RelativeLayout relativeLayout;
    Bitmap bitmap;
    String logEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_show);


        relativeLayout=findViewById(R.id.relativeforticket);
        Button button = findViewById(R.id.downloadticket);

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
        phone=findViewById(R.id.phone);
        pnrT=findViewById(R.id.pnr);
        name=findViewById(R.id.name);
        nid=findViewById(R.id.nid);
        dear=findViewById(R.id.dear);

        String pnr=getIntent().getStringExtra("pnr");

        auth=FirebaseAuth.getInstance();
        if(auth.getCurrentUser()!=null){
            logEmail=auth.getCurrentUser().getEmail();
        }

        firestore=FirebaseFirestore.getInstance();
        firestore.collection("UserInfo").document(logEmail).collection("tickets").document(pnr).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                ModelTicket modelTicket = documentSnapshot.toObject(ModelTicket.class);

                currentorissu.setText(modelTicket.getCurrentTime());
                jpurnydate.setText(modelTicket.getDate()+modelTicket.getDeptTime());
                trainname .setText(modelTicket.getTrainname());
                from.setText(modelTicket.getFrom());
                to .setText(modelTicket.getTo());
                classname.setText(modelTicket.getClass_seat());
                coachname .setText(modelTicket.getCoach());
                seat.setText(modelTicket.getSeat());
                price.setText(modelTicket.getPrice()+" BDT");
                total.setText(modelTicket.getPrice()+" BDT");
                name.setText(modelTicket.getName());
                nid.setText(modelTicket.getNid());
                phone.setText(modelTicket.getPhone());
                dear.setText("Dear"+modelTicket.getName());
                pnrT.setText(modelTicket.getPnr());

            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bitmap=loadBitmap(relativeLayout,relativeLayout.getHeight(),relativeLayout.getWidth());
                creatpdf();
            }
        });


    }

    private void creatpdf() {

        WindowManager windowManager = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float width = displayMetrics.widthPixels;
        float height = displayMetrics.heightPixels;

        int convertWidth = (int)width, convertHeight = (int)height;

        PdfDocument pdfDocument = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(convertWidth,convertHeight,1).create();
        PdfDocument.Page page = pdfDocument.startPage(pageInfo);

        Canvas canvas = page.getCanvas();

        Paint paint = new Paint();
        canvas.drawPaint(paint);

        bitmap = Bitmap.createScaledBitmap(bitmap,convertWidth,convertHeight,true);
        canvas.drawBitmap(bitmap,0,0,paint);
        pdfDocument.finishPage(page);


        File file = new File(getApplicationContext().getExternalFilesDir("/"),"Ticket.pdf");


        try {
            pdfDocument.writeTo(new FileOutputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        pdfDocument.close();
        Toast.makeText(this, "Pdf downloaded", Toast.LENGTH_SHORT).show();


    }
    private Bitmap loadBitmap(View v, int height, int width) {
        Bitmap bitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        v.draw(canvas);
        return bitmap;
    }

}