package com.example.uts_ezyfoody_2201797922;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterComplete extends RecyclerView.Adapter<AdapterComplete.ViewHolder> {

    public ArrayList<inputArray> order;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;

        public ViewHolder(CardView v){
            super(v);
            cardView = v;
        }
    }

    public AdapterComplete(ArrayList<inputArray> myOrder){
        this.order = myOrder;
    }

    @NonNull
    @Override
    public AdapterComplete.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cardView = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_cardviewcomplete,parent,false);
        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterComplete.ViewHolder holder, int position) {
        final CardView cardView = holder.cardView;

        TextView textView_Name = cardView.findViewById(R.id.textView_Name);
        TextView textView_Price = cardView.findViewById(R.id.textView_Price);
        TextView textView_Qty = cardView.findViewById(R.id.textView_qty);

        inputArray arr = order.get(position);
        textView_Name.setText("Name\t\t: " +arr.getName());
        textView_Price.setText("Price\t: "+ arr.getPrice());
        textView_Qty.setText("Quantity\t: "+arr.getQuantity());

    }

    @Override
    public int getItemCount() {
        return order.size();
    }
}
