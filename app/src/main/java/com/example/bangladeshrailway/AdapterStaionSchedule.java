package com.example.bangladeshrailway;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterStaionSchedule extends RecyclerView.Adapter<AdapterStaionSchedule.Myviewholder>{

    ArrayList<ModelStaionSchedule> datalist;
    private itemClickListener itemClickListener;

    public AdapterStaionSchedule(ArrayList<ModelStaionSchedule> datalist,itemClickListener itemClickListener) {
        this.datalist = datalist;
        this.itemClickListener=itemClickListener;
    }

    @NonNull
    @Override
    public AdapterStaionSchedule.Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_station,parent,false);
        return new AdapterStaionSchedule.Myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterStaionSchedule.Myviewholder holder, @SuppressLint("RecyclerView") int position) {
        holder.tittle.setText(datalist.get(position).getTittle());
        holder.trains.setText(datalist.get(position).getTrains());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(datalist.get(position));
            }
        });
    }

    public interface itemClickListener{
        void onItemClick(ModelStaionSchedule modelStaionSchedule);
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }
    class Myviewholder extends RecyclerView.ViewHolder{

        TextView tittle,trains;
        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            tittle=itemView.findViewById(R.id.stationname);
            trains=itemView.findViewById(R.id.trains);
        }

    }
}
