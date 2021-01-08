package com.example.uts_ezyfoody_2201797922;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView imageView;
    TextView textViewName;
    TextView textViewPrice;
    Button orderMore;
    Button myOrder;
    EditText quantity;
    ArrayList<inputArray> order = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        imageView =(ImageView) findViewById(R.id.productImage);
        textViewName=(TextView) findViewById(R.id.productName);
        textViewPrice=(TextView) findViewById(R.id.productPrice);
        quantity=(EditText) findViewById(R.id.quantity);

        orderMore=(Button) findViewById(R.id.btn_AddMore);
        myOrder=(Button) findViewById(R.id.btn_MyOrder);
        orderMore.setOnClickListener(this);
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
            String name = bundle.getString("name");
            int price = bundle.getInt("price");
            order =(ArrayList<inputArray>) bundle.getSerializable("myOrder");
            setUp(name,price);
        }
    }

    private void setUp(String name, int price) {
        if(name.equals("Mineral Water")){
            imageView.setImageResource(R.drawable.water);
            textViewName.setText(name);
            textViewPrice.setText("Rp. "+price);
        }else if(name.equals("Orange Juice")){
            imageView.setImageResource(R.drawable.juice);
            textViewName.setText(name);
            textViewPrice.setText("Rp. "+price);
        }else if(name.equals("Smoothie")){
            imageView.setImageResource(R.drawable.smoothie);
            textViewName.setText(name);
            textViewPrice.setText("Rp. "+price);
        }else if(name.equals("Vodka")){
            imageView.setImageResource(R.drawable.vodka);
            textViewName.setText(name);
            textViewPrice.setText("Rp. "+price);
        }else if(name.equals("French Fries")){
            imageView.setImageResource(R.drawable.ff);
            textViewName.setText(name);
            textViewPrice.setText("Rp. "+price);
        }else if(name.equals("Croissant")){
            imageView.setImageResource(R.drawable.croisant);
            textViewName.setText(name);
            textViewPrice.setText("Rp. "+price);
        }else if(name.equals("Popcorn")){
            imageView.setImageResource(R.drawable.popcorn);
            textViewName.setText(name);
            textViewPrice.setText("Rp. "+price);
        }else if(name.equals("Chips")){
            imageView.setImageResource(R.drawable.chips);
            textViewName.setText(name);
            textViewPrice.setText("Rp. "+price);
        }else if(name.equals("Nasi Goreng")){
            imageView.setImageResource(R.drawable.nasgor);
            textViewName.setText(name);
            textViewPrice.setText("Rp. "+price);
        }else if(name.equals("Ayam Goreng")){
            imageView.setImageResource(R.drawable.ayam);
            textViewName.setText(name);
            textViewPrice.setText("Rp. "+price);
        }else if(name.equals("Noodle")){
            imageView.setImageResource(R.drawable.noodle);
            textViewName.setText(name);
            textViewPrice.setText("Rp. "+price);
        }else if(name.equals("Satay")){
            imageView.setImageResource(R.drawable.sate);
            textViewName.setText(name);
            textViewPrice.setText("Rp. "+price);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.btn_AddMore:
                addToList();
                break;
            case R.id.btn_MyOrder:
                toMyOrder();
                break;
        }
    }

    private void toMyOrder() {
        //Ke MyOrderActivity
        Bundle inputArr = new Bundle();
        Intent intent = new Intent(OrderActivity.this,MyOrderActivity.class);
        inputArr.putSerializable("myOrder", order);

        intent.putExtras(inputArr);
        startActivity(intent);
    }

    private void addToList() {
            Bundle takeItem = getIntent().getExtras();
            String itemName = takeItem.getString("name");
            int itemPrice = takeItem.getInt("price");
            int qty = Integer.parseInt(quantity.getText().toString());
            if(qty > 0){
                order.add(new inputArray(itemName,itemPrice,qty));

                Bundle inputArr = new Bundle();
                Intent intent = new Intent(OrderActivity.this,MainActivity.class);
                inputArr.putSerializable("myOrder", order);

                intent.putExtras(inputArr);
                startActivity(intent);
                Toast.makeText(getBaseContext(),itemName+" "+itemPrice+" "+qty,Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getBaseContext(),"Input Quantity more than 0",Toast.LENGTH_SHORT).show();
            }
    }


}