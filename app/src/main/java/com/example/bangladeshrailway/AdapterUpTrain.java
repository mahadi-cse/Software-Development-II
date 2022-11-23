package com.example.bangladeshrailway;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterUpTrain extends RecyclerView.Adapter<AdapterUpTrain.Myviewholder>{

    ArrayList<ModelUpTrain> datalist;

    public AdapterUpTrain(ArrayList<ModelUpTrain> datalist) {
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public AdapterUpTrain.Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_specific_station_up,parent,false);
        return new AdapterUpTrain.Myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterUpTrain.Myviewholder holder, int position) {
        holder.name.setText(datalist.get(position).getName());
        holder.in.setText(datalist.get(position).getIn());
        holder.out.setText(datalist.get(position).getOut());
        holder.start.setText(datalist.get(position).getStart());
        holder.des.setText(datalist.get(position).getDestination());
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    class Myviewholder extends RecyclerView.ViewHolder{

        TextView name,in,out,start,des;
        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.trainnamespacific);
            in=itemView.findViewById(R.id.inTime);
            out=itemView.findViewById(R.id.outTime);
            start=itemView.findViewById(R.id.startstation);
            des=itemView.findViewById(R.id.finaldes);
        }

    }
}
