package com.example.uts_ezyfoody_2201797922;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    ArrayList<History> historyList = HistoryHandler.getInstance().history;

    AdapterHistory adpHistory;
    ListView historyListView;
    Intent intent;
    public int TotalPrice;
    String list_Choice;
    public ArrayList<inputArray> order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        historyListView = (ListView)findViewById(R.id.listViewHistory);

        if(historyList.isEmpty()){
            Toast.makeText(this, "You haven't made any transaction yet!", Toast.LENGTH_SHORT).show();
        }else{
            adpHistory = new AdapterHistory(this, historyList);
            historyListView.setAdapter(adpHistory);
        }

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            order =(ArrayList<inputArray>) bundle.getSerializable("myOrder");
            for (inputArray arr : order) {
                TotalPrice += (arr.getPrice() * arr.getQuantity());
                list_Choice += (arr.getName());
            }
        }else{
            //Toast.makeText(getBaseContext(),"No Order",Toast.LENGTH_SHORT).show();
        }
    }
}