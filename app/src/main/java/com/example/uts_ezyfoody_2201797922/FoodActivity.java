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

public class FoodActivity extends AppCompatActivity implements View.OnClickListener{

    public Button myOrder;
    public CardView nasgor,ayam,noodle,satay;
    public int TotalPrice;
    String list_Choice;
    public ArrayList<inputArray> order = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        nasgor =(CardView) findViewById(R.id.btn_NasiGoreng);
        ayam =(CardView) findViewById(R.id.btn_AyamGoreng);
        noodle =(CardView) findViewById(R.id.btn_Noodle);
        satay =(CardView) findViewById(R.id.btn_Sate);
        myOrder =(Button) findViewById(R.id.btn_MyOrder);

        nasgor.setOnClickListener(this);
        ayam.setOnClickListener(this);
        noodle.setOnClickListener(this);
        satay.setOnClickListener(this);
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
        Intent intent = new Intent(FoodActivity.this,MainActivity.class);
        inputArr.putSerializable("myOrder", order);

        intent.putExtras(inputArr);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        Bundle c = new Bundle();
        switch(v.getId()){
            case R.id.btn_NasiGoreng :
                intent= new Intent(this,OrderActivity.class);
//                intent.putExtra("name","Mineral Water");
//                intent.putExtra("price",15000);
                c.putString("name","Nasi Gopeng");
                c.putInt("price",35000);
                c.putSerializable("myOrder",order);
                intent.putExtras(c);
                startActivity(intent);
                break;
            case R.id.btn_AyamGoreng :
                intent = new Intent(this,OrderActivity.class);
//                intent.putExtra("name","Orange Juice");
//                intent.putExtra("price",25000);
                c.putString("name","Ayam Goreng");
                c.putInt("price",13000);
                c.putSerializable("myOrder",order);
                intent.putExtras(c);
                startActivity(intent);
                break;
            case R.id.btn_Noodle :
                intent = new Intent(this,OrderActivity.class);
//                  intent.putExtra("name","Smoothie");
//                  intent.putExtra("price",35000);
                c.putString("name","Noodle");
                c.putInt("price",25000);
                c.putSerializable("myOrder",order);
                intent.putExtras(c);
                startActivity(intent);
                break;
            case R.id.btn_Sate :
                intent = new Intent(this,OrderActivity.class);
//                intent.putExtra("name","Vodka");
//                intent.putExtra("price",250000);
                c.putString("name","Satay");
                c.putInt("price",50000);
                c.putSerializable("myOrder",order);
                intent.putExtras(c);
                startActivity(intent);
                break;
            case R.id.btn_MyOrder :
                Bundle inputArr = new Bundle();
                intent = new Intent(FoodActivity.this,MyOrderActivity.class);
                inputArr.putSerializable("myOrder", order);

                intent.putExtras(inputArr);
                startActivity(intent);
                break;
        }
    }
}