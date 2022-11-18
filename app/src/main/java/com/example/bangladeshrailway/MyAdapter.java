package com.example.bangladeshrailway;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.Myviewholder>
{
    int temp;
    ArrayList<Model> datalist;
    private itemClickListener itemClickListener;

    public MyAdapter(ArrayList<Model> datalist, itemClickListener itemClickListener) {
        this.datalist = datalist;
        this.itemClickListener=itemClickListener;
    }

    @NonNull
    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_train,parent,false);
        return new Myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Myviewholder holder, @SuppressLint("RecyclerView") int position) {

        holder.counterac_b.setText(datalist.get(position).getCounterAC_B());
        holder.countersnigdha.setText(datalist.get(position).getCounterSNIGDHA());
        holder.counters_chair.setText(datalist.get(position).getCounterS_CHAIR());
        holder.countershovon.setText(datalist.get(position).getCounterSHOVON());


        holder.onlineac_b.setText(datalist.get(position).getOnlineAC_B());
        holder.onlinesnigdha.setText(datalist.get(position).getOnlineSNIGDHA());
        holder.onlines_chair.setText(datalist.get(position).getOnlineS_CHAIR());
        holder.onlineshovon.setText(datalist.get(position).getOnlineSHOVON());


        holder.priceac_b.setText(datalist.get(position).getPriceAC_B());
        holder.pricesnigdha.setText(datalist.get(position).getPriceSNIGDHA());
        holder.prices_chair.setText(datalist.get(position).getPriceS_CHAIR());
        holder.priceshovon.setText(datalist.get(position).getPriceSHOVON());

        holder.name.setText(datalist.get(position).getName());
        holder.from.setText(datalist.get(position).getFrom());
        holder.to.setText(datalist.get(position).getTo());
        holder.arraivaltime.setText(datalist.get(position).getArraivalTime());
        holder.departuretime.setText(datalist.get(position).getDepartureTime());

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
        void onItemClick(Model model);
    }


    class Myviewholder extends RecyclerView.ViewHolder
    {
        TextView counterac_b,countersnigdha,counters_chair,countershovon,
                onlineac_b,onlinesnigdha,onlines_chair,onlineshovon,
                priceac_b,pricesnigdha,prices_chair,priceshovon , from,to,arraivaltime,departuretime,name;
        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            counterac_b=itemView.findViewById(R.id.counter_ac_b);
            countersnigdha=itemView.findViewById(R.id.counter_snigsha);
            counters_chair=itemView.findViewById(R.id.counters_chair);
            countershovon=itemView.findViewById(R.id.counter_shovon);


            onlineac_b=itemView.findViewById(R.id.online_ac_b);
            onlinesnigdha=itemView.findViewById(R.id.online_snigsha);
            onlines_chair=itemView.findViewById(R.id.onlines_chair);
            onlineshovon=itemView.findViewById(R.id .online_shovon);


            priceac_b=itemView.findViewById(R.id.price_ac_b);
            pricesnigdha=itemView.findViewById(R.id.price_snigsha);
            prices_chair=itemView.findViewById(R.id.prices_chair);
            priceshovon=itemView.findViewById(R.id .price_shovon);

            name=itemView.findViewById(R.id .trainName);
            from=itemView.findViewById(R.id .departureName);
            to=itemView.findViewById(R.id .arraivalName);
            arraivaltime=itemView.findViewById(R.id .arraivaltime);
            departuretime=itemView.findViewById(R.id .deparaturetime);
        }
    }
}

