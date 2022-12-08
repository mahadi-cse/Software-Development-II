package com.example.bangladeshrailway;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterUpTrainTrack extends  RecyclerView.Adapter<AdapterUpTrainTrack.Myviewholder> {

    ArrayList<ModelTrack> datalist;
    private itemClickListener itemClickListener;

    public AdapterUpTrainTrack(ArrayList<ModelTrack> datalist,itemClickListener itemClickListener ) {
        this.itemClickListener=itemClickListener;
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public AdapterUpTrainTrack.Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_up_train_track,parent,false);
        return new AdapterUpTrainTrack.Myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterUpTrainTrack.Myviewholder holder, @SuppressLint("RecyclerView") int position) {
        holder.tittle.setText(datalist.get(position).getTittle());
        holder.route.setText(datalist.get(position).getRoute());

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
        void onItemClick(ModelTrack modelTrack);
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
