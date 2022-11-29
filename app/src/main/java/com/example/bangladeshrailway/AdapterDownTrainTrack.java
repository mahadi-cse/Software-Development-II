package com.example.bangladeshrailway;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterDownTrainTrack extends  RecyclerView.Adapter<AdapterDownTrainTrack.Myviewholder> {

    ArrayList<ModelTrack> datalist;

    public AdapterDownTrainTrack(ArrayList<ModelTrack> datalist) {
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public AdapterDownTrainTrack.Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_down_train_track,parent,false);
        return new AdapterDownTrainTrack.Myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDownTrainTrack.Myviewholder holder, int position) {
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
