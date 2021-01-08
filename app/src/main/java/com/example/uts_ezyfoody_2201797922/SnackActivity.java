package com.example.uts_ezyfoody_2201797922;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

public class SnackActivity extends AppCompatActivity implements View.OnClickListener{

    public Button myOrder;
    public CardView kentang,croisant,popcorn,chips;
    public int TotalPrice;
    String list_Choice;
    public ArrayList<inputArray> order = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snack);

        kentang = (CardView) findViewById(R.id.btn_FrenchFries);
        croisant = (CardView) findViewById(R.id.btn_Croisant);
        popcorn = (CardView) findViewById(R.id.btn_Popcorn);
        chips = (CardView) findViewById(R.id.btn_Chips);
        myOrder = (Button) findViewById(R.id.btn_MyOrder);

        kentang.setOnClickListener(this);
        croisant.setOnClickListener(this);
        popcorn.setOnClickListener(this);
        chips.setOnClickListener(this);
        myOrder.setOnClickListener(this);

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
                order = (ArrayList<inputArray>) bundle.getSerializable("myOrder");
                for (inputArray arr : order) {
                    TotalPrice += (arr.getPrice() * arr.getQuantity());
                    list_Choice += (arr.getName());
                }
        }
    }

    @Override
    public void onBackPressed() {
        Bundle inputArr = new Bundle();
        Intent intent = new Intent(SnackActivity.this,MainActivity.class);
        inputArr.putSerializable("myOrder", order);

        intent.putExtras(inputArr);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        Bundle c = new Bundle();
        switch(v.getId()){
            case R.id.btn_FrenchFries :
                intent= new Intent(this,OrderActivity.class);
                c.putString("name","French Fries");
                c.putInt("price",17000);
                c.putSerializable("myOrder",order);
                intent.putExtras(c);
                startActivity(intent);
                break;
            case R.id.btn_Croisant :
                intent = new Intent(this,OrderActivity.class);
                c.putString("name","Croissant");
                c.putInt("price",15000);
                c.putSerializable("myOrder",order);
                intent.putExtras(c);
                startActivity(intent);
                break;
            case R.id.btn_Popcorn :
                intent = new Intent(this,OrderActivity.class);
                c.putString("name","Popcorn");
                c.putInt("price",35000);
                c.putSerializable("myOrder",order);
                intent.putExtras(c);
                startActivity(intent);
                break;
            case R.id.btn_Chips :
                intent = new Intent(this,OrderActivity.class);
                c.putString("name","Chips");
                c.putInt("price",5000);
                c.putSerializable("myOrder",order);
                intent.putExtras(c);
                startActivity(intent);
                break;
            case R.id.btn_MyOrder :
                Bundle inputArr = new Bundle();
                intent = new Intent(SnackActivity.this,MyOrderActivity.class);
                inputArr.putSerializable("myOrder", order);

                intent.putExtras(inputArr);
                startActivity(intent);
                break;
        }
    }
}