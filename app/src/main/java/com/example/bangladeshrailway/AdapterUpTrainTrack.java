package com.example.bangladeshrailway;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterUpTrainTrack extends  RecyclerView.Adapter<AdapterUpTrainTrack.Myviewholder> {

    ArrayList<ModelTrack> datalist;

    public AdapterUpTrainTrack(ArrayList<ModelTrack> datalist) {
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public AdapterUpTrainTrack.Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_up_train_track,parent,false);
        return new AdapterUpTrainTrack.Myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterUpTrainTrack.Myviewholder holder, int position) {
        holder.tittle.setText(datalist.get(position).getTittle());
        holder.route.setText(datalist.get(position).getRoute());
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    class Myviewholder extends RecyclerView.ViewHolder{

        TextView tittle,route;
        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            tittle=itemView.findViewById(R.id.trainname);
            route=itemView.findViewById(R.id.routes);

        }

    }
}
