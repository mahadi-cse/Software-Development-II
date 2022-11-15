package com.example.bangladeshrailway;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2 extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.sign_out_options:
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(this, "Signed out", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity2.this,MainActivity.class));
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.user_options,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        bottomNavigationView=findViewById(R.id.bnView_userProfile);


        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int id= item.getItemId();
            if(id==R.id.Home){
            loadFrag(new UserActivity(),false);
            }
            else if(id==R.id.Tickets){
                loadFrag(new Tickets(),false);
            }
            else {
                loadFrag(new UserInfo(),false);
            }

            return true;
        });
        bottomNavigationView.setSelectedItemId(R.id.Home);


    }

    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(MainActivity2.this)
                .setTitle(R.string.app_name)
                .setIcon(R.drawable.ic_rail_icon)
        .setMessage("          Do you want to exit ?").setCancelable(false)
                .setPositiveButton("Yes", (dialog, which) -> {
                    dialog.cancel();
                    finish();
                }).setNegativeButton("No",null)
                .show();
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
}