package com.example.uts_ezyfoody_2201797922;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public CardView drink,food,snack,topUp;
    public Button myOrder,history;
    public int TotalPrice;
    String list_Choice;
    public ArrayList<inputArray> order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drink =(CardView) findViewById(R.id.btn_drink);
        food =(CardView) findViewById(R.id.btn_food);
        snack =(CardView) findViewById(R.id.btn_snack);
        topUp =(CardView) findViewById(R.id.btn_topUp);
        myOrder =(Button) findViewById(R.id.btn_MyOrder);
        history = (Button) findViewById(R.id.btn_history);

        drink.setOnClickListener(this);
        food.setOnClickListener(this);
        snack.setOnClickListener(this);
        topUp.setOnClickListener(this);
        myOrder.setOnClickListener(this);
        history.setOnClickListener(this);

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
            for (inputArray arr : order) {
                TotalPrice += (arr.getPrice() * arr.getQuantity());
                list_Choice += (arr.getName());
            }
        }else{
            //Toast.makeText(getBaseContext(),"No Order",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        Bundle inputArr = new Bundle();
        Bundle check = getIntent().getExtras();
        Intent intent;
        switch(v.getId()){
            case R.id.btn_drink :
                intent= new Intent(this,DrinkActivity.class);
                if(check != null) {
                    inputArr.putSerializable("myOrder", order);

                    intent.putExtras(inputArr);
                }
                startActivity(intent);
                break;
            case R.id.btn_food :
                intent = new Intent(this,FoodActivity.class);
                if(check != null) {
                    inputArr.putSerializable("myOrder", order);

                    intent.putExtras(inputArr);
                }
                startActivity(intent);
                break;
            case R.id.btn_snack :
                intent = new Intent(this,SnackActivity.class);
                if(check != null) {
                    inputArr.putSerializable("myOrder", order);

                    intent.putExtras(inputArr);
                }
                startActivity(intent);
                break;
            case R.id.btn_topUp :
                intent = new Intent(this,TopUpActivity.class);
                if(check != null) {
                    inputArr.putSerializable("myOrder", order);

                    intent.putExtras(inputArr);
                }
                startActivity(intent);
                break;
            case R.id.btn_MyOrder :
                if(check != null) {
                    intent = new Intent(MainActivity.this, MyOrderActivity.class);
                    inputArr.putSerializable("myOrder", order);

                    intent.putExtras(inputArr);
                    startActivity(intent);
                }else{
                    Toast.makeText(getBaseContext(),"No Order",Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btn_history:
                intent = new Intent(this,HistoryActivity.class);
                if(check != null) {
                    inputArr.putSerializable("myOrder", order);

                    intent.putExtras(inputArr);
                }
                startActivity(intent);
                break;
        }

    }
}