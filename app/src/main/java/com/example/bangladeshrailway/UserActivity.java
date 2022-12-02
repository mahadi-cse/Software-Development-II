package com.example.bangladeshrailway;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class UserActivity extends Fragment implements View.OnClickListener{

    CardView buyTickets,fare,routes,track,coach,trainTime,schedule,verifyTicket,refund;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_user_activity, container, false);

        buyTickets=view.findViewById(R.id.buy_tickets);
        fare=view.findViewById(R.id.fare);
        routes=view.findViewById(R.id.routes);
        track=view.findViewById(R.id.track);
        coach=view.findViewById(R.id.coach);
        trainTime=view.findViewById(R.id.train_time);
        schedule=view.findViewById(R.id.schedule);
        verifyTicket=view.findViewById(R.id.verify_ticket);
        refund=view.findViewById(R.id.refund);

        buyTickets.setOnClickListener(this);
        fare.setOnClickListener(this);
        routes.setOnClickListener(this);
        track.setOnClickListener(this);
        coach.setOnClickListener(this);
        trainTime.setOnClickListener(this);
        schedule.setOnClickListener(this);
        verifyTicket.setOnClickListener(this);
        refund.setOnClickListener(this);

        return view;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buy_tickets:
                startActivity(new Intent(getActivity(),BuyTickets.class));
                break;
            case R.id.fare:
                startActivity(new Intent(getActivity(),TicketFare.class));
                break;
            case R.id.routes:
                startActivity(new Intent(getActivity(),TrainRoutes.class));
                break;
            case R.id.track:
                startActivity(new Intent(getActivity(),Track.class));
                break;
            case R.id.coach:
                startActivity(new Intent(getActivity(),CoachView.class));
                break;
            case R.id.train_time:
                startActivity(new Intent(getActivity(), TrainTime.class));
                break;
            case R.id.schedule:
                startActivity(new Intent(getActivity(),StaionSchedule.class));
                break;
            case R.id.verify_ticket:
                startActivity(new Intent(getActivity(),VerifyTicket.class));
                break;
            case R.id.refund:
                startActivity(new Intent(getActivity(),RefundCalculator.class));
                break;
        }
    }
}