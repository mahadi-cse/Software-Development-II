package com.example.bangladeshrailway;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterUpTrainFrag extends RecyclerView.Adapter<AdapterUpTrainFrag.Myviewholder>{
    ArrayList<ModelUpTrainFrag> datalist;

    public AdapterUpTrainFrag(ArrayList<ModelUpTrainFrag> datalist) {
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public AdapterUpTrainFrag.Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_train_up,parent,false);
        return new AdapterUpTrainFrag.Myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterUpTrainFrag.Myviewholder holder, int position) {
        holder.name.setText(datalist.get(position).getStation());
        holder.in.setText(datalist.get(position).getIn());
        holder.out.setText(datalist.get(position).getOut());
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    class Myviewholder extends RecyclerView.ViewHolder{

        TextView name,in,out;
        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.stationname);
            in=itemView.findViewById(R.id.in_time);
            out=itemView.findViewById(R.id.out_time);
        }
    }
}
