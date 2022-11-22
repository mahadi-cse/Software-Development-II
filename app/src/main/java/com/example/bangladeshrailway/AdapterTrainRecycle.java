package com.example.bangladeshrailway;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class AdapterTrainRecycle extends RecyclerView.Adapter<AdapterTrainRecycle.Myviewholder>{

    ArrayList<ModelTrainRecycle> datalist;

    public AdapterTrainRecycle(ArrayList<ModelTrainRecycle> datalist) {
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public AdapterTrainRecycle.Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trainrecycle,parent,false);
        return new AdapterTrainRecycle.Myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTrainRecycle.Myviewholder holder, int position) {

       ModelTrainRecycle modelTrainRecycle = datalist.get(position);
        holder.tittle.setText(datalist.get(position).getTittle());
        holder.routes.setText(datalist.get(position).getRoute());

        Glide.with(holder.trainimageRecycle.getContext()).load(modelTrainRecycle.getUrl()).into(holder.trainimageRecycle);
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    class Myviewholder extends RecyclerView.ViewHolder{

        TextView tittle,routes;
        ImageView trainimageRecycle;
        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            tittle=itemView.findViewById(R.id.trainnamerecycle);
            routes=itemView.findViewById(R.id.trainroute);
            trainimageRecycle=itemView.findViewById(R.id.imageTrainrecycle);
        }

    }
}
