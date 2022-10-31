package com.example.bangladeshrailway;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.Toast;

public class UserActivity extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_user_profile, container, false);
        GridLayout mainGrid;
        mainGrid = (GridLayout) view.findViewById(R.id.mainGrid);
        //Set Event
        setSingleEvent(mainGrid);
        //setToggleEvent(mainGrid);



        return view;
    }

    private void setSingleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(view -> {

                Intent intent = new Intent(getActivity(),ActivityOne.class);
                intent.putExtra("info","This is activity from card item index  "+finalI);
                startActivity(intent);

            });
        }
    }
    private void setToggleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            final CardView cardView = (CardView) mainGrid.getChildAt(i);
            cardView.setOnClickListener(view -> {
                if (cardView.getCardBackgroundColor().getDefaultColor() == -1) {
                    //Change background color
                    cardView.setCardBackgroundColor(Color.parseColor("#FF6F00"));
                    Toast.makeText(getActivity(), "State : True", Toast.LENGTH_SHORT).show();

                } else {
                    //Change background color
                    cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
                    Toast.makeText(getActivity(), "State : False", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}