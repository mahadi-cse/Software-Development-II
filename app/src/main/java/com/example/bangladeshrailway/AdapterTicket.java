package com.example.bangladeshrailway;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterTicket extends RecyclerView.Adapter<AdapterTicket.Myviewholder>{

    private ArrayList<ModelTicket> datalist;
    private itemClickListener itemClickListener;

    public AdapterTicket(ArrayList<ModelTicket> datalist, AdapterTicket.itemClickListener itemClickListener) {
        this.datalist = datalist;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public AdapterTicket.Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ticket,parent,false);
        return new AdapterTicket.Myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTicket.Myviewholder holder, @SuppressLint("RecyclerView") int position) {

        holder.train.setText(datalist.get(position).getTrainname());
        holder.route.setText(datalist.get(position).getRoute());
        holder.date.setText(datalist.get(position).getDate()+" - "+datalist.get(position).getDeptTime());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClick(datalist.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public interface itemClickListener{
        void onItemClick(ModelTicket modelTicket);
    }

    class Myviewholder extends RecyclerView.ViewHolder{

        TextView date,train,route;
        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            train=itemView.findViewById(R.id.trainname);
            route=itemView.findViewById(R.id.route);
            date=itemView.findViewById(R.id.date);
        }

    }
}
