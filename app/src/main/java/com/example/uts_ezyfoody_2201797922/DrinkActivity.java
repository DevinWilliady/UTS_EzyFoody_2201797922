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

public class DrinkActivity extends AppCompatActivity implements View.OnClickListener {

    public Button button;
    public CardView mineralWater, juice, smoothie, vodka;
    public int TotalPrice;
    String list_Choice;
    public ArrayList<inputArray> order = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink);

        mineralWater =(CardView) findViewById(R.id.btn_MineralWater);
        juice =(CardView) findViewById(R.id.btn_OrangeJuice);
        smoothie =(CardView) findViewById(R.id.btn_Smoothie);
        vodka =(CardView) findViewById(R.id.btn_Vodka);
        button =(Button) findViewById(R.id.btn_MyOrder);

        mineralWater.setOnClickListener(this);
        juice.setOnClickListener(this);
        smoothie.setOnClickListener(this);
        vodka.setOnClickListener(this);
        button.setOnClickListener(this);

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
    public void onClick(View v) {
        Intent intent;
        Bundle c = new Bundle();
        switch(v.getId()){
            case R.id.btn_MineralWater :
                intent= new Intent(this,OrderActivity.class);
//                intent.putExtra("name","Mineral Water");
//                intent.putExtra("price",15000);
                c.putString("name","Mineral Water");
                c.putInt("price",15000);
                c.putSerializable("myOrder",order);
                intent.putExtras(c);
                startActivity(intent);
                break;
            case R.id.btn_OrangeJuice :
                intent = new Intent(this,OrderActivity.class);
//                intent.putExtra("name","Orange Juice");
//                intent.putExtra("price",25000);
                c.putString("name","Orange Juice");
                c.putInt("price",25000);
                c.putSerializable("myOrder",order);
                intent.putExtras(c);
                startActivity(intent);
                break;
            case R.id.btn_Smoothie :
                intent = new Intent(this,OrderActivity.class);
//                  intent.putExtra("name","Smoothie");
//                  intent.putExtra("price",35000);
                c.putString("name","Smoothie");
                c.putInt("price",35000);
                c.putSerializable("myOrder",order);
                intent.putExtras(c);
                startActivity(intent);
                break;
            case R.id.btn_Vodka :
                intent = new Intent(this,OrderActivity.class);
//                intent.putExtra("name","Vodka");
//                intent.putExtra("price",250000);
                c.putString("name","Vodka");
                c.putInt("price",250000);
                c.putSerializable("myOrder",order);
                intent.putExtras(c);
                startActivity(intent);
                break;
            case R.id.btn_MyOrder :
                Bundle inputArr = new Bundle();
                intent = new Intent(DrinkActivity.this,MyOrderActivity.class);
                inputArr.putSerializable("myOrder", order);

                intent.putExtras(inputArr);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        Bundle inputArr = new Bundle();
        Intent intent = new Intent(DrinkActivity.this,MainActivity.class);
        inputArr.putSerializable("myOrder", order);

        intent.putExtras(inputArr);
        startActivity(intent);
    }
}