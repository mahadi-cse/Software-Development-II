package com.example.bangladeshrailway;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bnView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bnView=findViewById(R.id.bnView);


        bnView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id= item.getItemId();
                if(id==R.id.sign_in){
                loadFrag(new Login(),false);
                }
                else if(id==R.id.sign_up){
                    loadFrag(new Register(),false);
                }
                else {
                    loadFrag(new Forgetpassword(),false);
                }

                return true;
            }
        });
        bnView.setSelectedItemId(R.id.sign_in);
    }
    public void loadFrag(Fragment fragment , boolean flag ){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if(flag){
            ft.add(R.id.container,fragment);
        }
        else{
            ft.replace(R.id.container,fragment);
        }
        ft.commit();

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            startActivity(new Intent(MainActivity.this, MainActivity2.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK));
            finish();
        }
    }

    public void onBackPressed() {

        new AlertDialog.Builder(MainActivity.this)
                .setTitle(R.string.app_name)
                .setIcon(R.drawable.ic_rail_icon)
                .setMessage("          Do you want to exit ?").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        finish();
                    }
                }).setNegativeButton("No",null)
                .show();
    }
}