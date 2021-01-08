package com.example.uts_ezyfoody_2201797922;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

public class MyOrderActivity extends AppCompatActivity implements View.OnClickListener {

    TextView priceView;
    ArrayList<inputArray> order = new ArrayList<>();
    int TotalPrice = 0;
    double SisaUang = 0;
    RecyclerView rv;
    LinearLayoutManager llm;
    AdapterRecycler ar;
    Button payNow;
    BalanceHandler balance = BalanceHandler.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        
        priceView=(TextView) findViewById(R.id.priceView);
        payNow=(Button) findViewById(R.id.btn_PayNow);
        payNow.setOnClickListener(this);

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
            order =(ArrayList<inputArray>) bundle.getSerializable("myOrder");
            priceView.setText("Total Price : "+TotalPrice());
            
            rv = findViewById(R.id.recyclerView);
            createRecycler();
        }
    }

    private void createRecycler() {
        rv.setHasFixedSize(true);

        llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        ar = new AdapterRecycler(order);
        rv.setAdapter(ar);
    }

    private int TotalPrice() {
        for (inputArray arr: order) {
            TotalPrice +=(arr.getPrice()*arr.getQuantity());
        }
        return TotalPrice;
    }


    @Override
    public void onClick(View v) {
        if(TotalPrice > balance.getBalance()){
            Toast.makeText(getBaseContext(),"Insufficient Money !",Toast.LENGTH_SHORT).show();
        }else{
            SisaUang = balance.getBalance() - TotalPrice;
            balance.setBalance(SisaUang);

            Bundle inputArr = new Bundle();
            Intent intent = new Intent(MyOrderActivity.this,MapsActivity2.class);
            inputArr.putSerializable("myOrder", order);

            intent.putExtras(inputArr);
            startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        Bundle inputArr = new Bundle();
        Intent intent = new Intent(MyOrderActivity.this,MainActivity.class);
        inputArr.putSerializable("myOrder", order);

        intent.putExtras(inputArr);
        startActivity(intent);
    }
}