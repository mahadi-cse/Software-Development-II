package com.example.bangladeshrailway;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CoachViewAdapter extends RecyclerView.Adapter<CoachViewAdapter.Myviewholder> {

    ArrayList<CoachViewModel> datalist;
    Context context;

    public CoachViewAdapter(ArrayList<CoachViewModel> datalist, Context context) {
        this.datalist = datalist;
        this.context = context;
    }

    @NonNull
    @Override
    public CoachViewAdapter.Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.coach_view,parent,false);
        return new CoachViewAdapter.Myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoachViewAdapter.Myviewholder holder, int position) {

        CoachViewModel coachViewModel= datalist.get(position);

        holder.tittle.setText(datalist.get(position).getTittle());
        holder.broadcapasity.setText(datalist.get(position).getBroad());
        holder.metercapasity.setText(datalist.get(position).getMeter());

        Glide.with(holder.coachview.getContext()).load(coachViewModel.getUrl()).into(holder.coachview);
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    class Myviewholder extends RecyclerView.ViewHolder{

        TextView tittle,broadcapasity,metercapasity;
        ImageView coachview;
        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            tittle=itemView.findViewById(R.id.class_tittle);
            broadcapasity=itemView.findViewById(R.id.broadgaugecapasity);
            metercapasity=itemView.findViewById(R.id.metercapasity);
            coachview=itemView.findViewById(R.id.coach_image);
        }

    }
}
