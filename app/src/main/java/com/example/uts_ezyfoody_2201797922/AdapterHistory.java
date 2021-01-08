package com.example.uts_ezyfoody_2201797922;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterHistory extends BaseAdapter {

    Context context;
    ArrayList<History> history = new ArrayList<>();
    public static final String ID = "com.example.binusezyfood.ID";

    public AdapterHistory(Context context, ArrayList<History> history) {
        this.context = context;
        this.history = history;
    }

    @Override
    public int getCount() {
        return history.size();
    }

    @Override
    public Object getItem(int position) {
        return history.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.list_history, parent,false);
        }

        History tempHistory = (History) getItem(position);

        TextView date = (TextView)convertView.findViewById(R.id.textViewTransactionDate);
        TextView address = (TextView)convertView.findViewById(R.id.textViewTransactionAddress);
//        Button buttonDetails = (Button)convertView.findViewById(R.id.buttonDetails);
//
//        buttonDetails.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, HistoryDetailActivity.class);
//
//                int id = (int) getItemId(position);
//                intent.putExtra(ID, id);
//
//                context.startActivity(intent);
//            }
//        });

        date.setText(tempHistory.getDate());
        address.setText(tempHistory.getAddress());

        return convertView;
    }
}
