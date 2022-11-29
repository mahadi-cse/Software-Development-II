package com.example.bangladeshrailway;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class SpecificTrainSchedule extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_train_schedule);

        tabLayout=findViewById(R.id.tablayoutTrain);
        viewPager=findViewById(R.id.viewpagerTrain);

        tabLayout.setupWithViewPager(viewPager);
        TabAdapterTrain tabAdapterTrain = new TabAdapterTrain(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        tabAdapterTrain.addFragment(new UpTrainFragment(),"Up Train");
        tabAdapterTrain.addFragment(new DownTrainFragment(),"Down Train");
        viewPager.setAdapter(tabAdapterTrain);


    }
}