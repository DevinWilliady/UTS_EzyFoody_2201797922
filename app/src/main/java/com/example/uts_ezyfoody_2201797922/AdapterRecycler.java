package com.example.uts_ezyfoody_2201797922;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterRecycler extends RecyclerView.Adapter<AdapterRecycler.ViewHolder> {

    public ArrayList<inputArray> order;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;

        public ViewHolder(CardView v){
            super(v);
            cardView = v;
        }
    }

    public AdapterRecycler(ArrayList<inputArray> myOrder){
        this.order = myOrder;
    }

    @NonNull
    @Override
    public AdapterRecycler.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView =(CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cardview,parent,false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecycler.ViewHolder holder, final int position) {
        final CardView cardView = holder.cardView;

        TextView textView_Name = cardView.findViewById(R.id.textView_Name);
        TextView textView_Price = cardView.findViewById(R.id.textView_Price);
        TextView textView_Qty = cardView.findViewById(R.id.textView_qty);

        inputArray arr = order.get(position);
        textView_Name.setText("Name\t\t: " +arr.getName());
        textView_Price.setText("Price\t: "+ arr.getPrice());
        textView_Qty.setText("Quantity\t: "+arr.getQuantity());

        //Remove From ArrayList
        Button rmv = cardView.findViewById(R.id.remove);
        rmv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                inputArray rmv = order.get(position);
                order.remove(rmv);

                //Toast.makeText(v.getContext(),""+rmv,Toast.LENGTH_SHORT).show();
                Bundle bundle = new Bundle();
                bundle.putSerializable("myOrder", order);

                Intent intent = new Intent(cardView.getContext(),MyOrderActivity.class);
                intent.putExtras(bundle);
                cardView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return order.size();
    }
}

