package com.example.uts_ezyfoody_2201797922;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

public class CompleteOrder extends AppCompatActivity implements View.OnClickListener {

    TextView priceView,addre;
    Button home;
    ArrayList<inputArray> order = new ArrayList<>();
    int TotalPrice = 0;
    RecyclerView rv;
    LinearLayoutManager llm;
    AdapterComplete ac;
    String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_order);

        priceView = (TextView) findViewById(R.id.priceView);
        addre =(TextView) findViewById(R.id.TVAdd);
        home = (Button) findViewById(R.id.btn_ToHome);
        home.setOnClickListener(this);

        TextView textView = findViewById(R.id.AmountTextView);

        double tempBalance = BalanceHandler.getInstance().getBalance();

        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);

        String balance = kursIndonesia.format(tempBalance);
        textView.setText(balance);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            order=(ArrayList<inputArray>) bundle.getSerializable("myOrder");
            address = (String) bundle.getSerializable("address");
        }
        priceView.setText("Total Price : "+TotalPrice());
        addre.setText(address);
        rv = findViewById(R.id.recyclerView);
        createRecyler();
    }

    private void createRecyler() {
        rv.setHasFixedSize(true);

        llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        ac = new AdapterComplete(order);
        rv.setAdapter(ac);
    }

    private int TotalPrice() {
        for (inputArray arr:order) {
            TotalPrice +=(arr.getPrice()*arr.getQuantity());
        }
        return TotalPrice;
    }

    @Override
    public void onClick(View v) {
        for(int i=0 ; i<order.size();i++){
            order.remove(i);
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}