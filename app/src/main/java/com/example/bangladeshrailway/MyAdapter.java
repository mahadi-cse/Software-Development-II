package com.example.bangladeshrailway;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<trainsearchinfo> trainlistinfo;

    public MyAdapter(Context context, ArrayList<trainsearchinfo> trainlistinfo) {
        this.context = context;
        this.trainlistinfo = trainlistinfo;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item_train,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {

        trainsearchinfo trains = trainlistinfo.get(position);

        holder.arraiveTime.setText(trains.time[0]);
        holder.DepartureTime.setText(trains.time[1]);

        holder.counterAC_B.setText(trains.counter[0]);
        holder.counterSNIGDHA.setText(trains.counter[1]);
        holder.counterS_CHAIR.setText(trains.counter[2]);
        holder.counterSHOVON.setText(trains.counter[3]);

        holder.onlineAC_B.setText(trains.online[0]);
        holder.onlineSNIGDHA.setText(trains.online[1]);
        holder.onlineS_CHAIR.setText(trains.online[2]);
        holder.onlineSHOVON.setText(trains.online[3]);

        holder.priceAC_B.setText(trains.price[0]);
        holder.priceSNIGDHA.setText(trains.price[1]);
        holder.priceS_CHAIR.setText(trains.price[2]);
        holder.priceSHOVON.setText(trains.price[3]);


    }

    @Override
    public int getItemCount() {
        return trainlistinfo.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView arraiveTime,DepartureTime,counterAC_B,counterSNIGDHA,counterS_CHAIR,counterSHOVON,onlineAC_B,onlineSNIGDHA,onlineS_CHAIR,onlineSHOVON,priceAC_B,priceSNIGDHA,priceS_CHAIR,priceSHOVON;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            arraiveTime=itemView.findViewById(R.id.arraivaltime);
            DepartureTime=itemView.findViewById(R.id.deparaturetime);

            counterAC_B=itemView.findViewById(R.id.counter_ac_b);
            counterSNIGDHA=itemView.findViewById(R.id.counter_snigsha);
            counterS_CHAIR=itemView.findViewById(R.id.counters_chair);
            counterSHOVON=itemView.findViewById(R.id.counter_shovon);

            onlineAC_B=itemView.findViewById(R.id.online_ac_b);
            onlineSNIGDHA=itemView.findViewById(R.id.online_snigsha);
            onlineS_CHAIR=itemView.findViewById(R.id.onlines_chair);
            onlineSHOVON=itemView.findViewById(R.id.online_shovon);

            priceAC_B=itemView.findViewById(R.id.price_ac_b);
            priceSNIGDHA=itemView.findViewById(R.id.price_snigsha);
            priceS_CHAIR=itemView.findViewById(R.id.prices_chair);
            priceSHOVON=itemView.findViewById(R.id.price_shovon);
        }
    }
}