package com.example.uts_ezyfoody_2201797922;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;

public class TopUpActivity extends AppCompatActivity implements View.OnClickListener {

    Button addBalance, home;
    public int TotalPrice;
    String list_Choice;
    public ArrayList<inputArray> order;
    BalanceHandler balance = BalanceHandler.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up);

        home = (Button) findViewById(R.id.btn_homee);
        home.setOnClickListener(this);

        addBalance = (Button) findViewById(R.id.btn_addBalance);
        addBalance.setOnClickListener(this);

        TextView balanceTV = findViewById(R.id.BalanceTextView);
        double tempBalance = BalanceHandler.getInstance().getBalance();

        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

        formatRp.setCurrencySymbol("Rp");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');

        kursIndonesia.setDecimalFormatSymbols(formatRp);

        String balance = kursIndonesia.format(tempBalance);
        balanceTV.setText(balance);

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
    public void onBackPressed() {
        Bundle inputArr = new Bundle();
        Intent intent = new Intent(TopUpActivity.this,MainActivity.class);
        inputArr.putSerializable("myOrder", order);

        intent.putExtras(inputArr);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        Bundle inputArr = new Bundle();
        Bundle check = getIntent().getExtras();
        Intent intent;
        switch (v.getId()){
            case R.id.btn_addBalance:
                EditText amount = findViewById(R.id.amount);
                if(amount.getText().toString().isEmpty()){
                    Toast.makeText(getBaseContext(), "Please Entered Amount", Toast.LENGTH_SHORT).show();
                }else{
                    double amounts = Double.parseDouble(amount.getText().toString());
                    if(amounts > 2000000){
                        Toast.makeText(getBaseContext(), "Can't add more than 2 million", Toast.LENGTH_SHORT).show();
                    }else if(amounts < 1){

                    }else{
                        amounts += balance.getBalance();
                        balance.setBalance(amounts);
                        Toast.makeText(this, "Money successfully added to your balance!", Toast.LENGTH_SHORT).show();
                        intent = new Intent(this, TopUpActivity.class);
                        if(check != null) {
                            inputArr.putSerializable("myOrder", order);

                            intent.putExtras(inputArr);
                        }
                        startActivity(intent);
                    }
                } 
                break;

            case R.id.btn_homee:
                intent = new Intent(this, MainActivity.class);
                if(check != null) {
                    inputArr.putSerializable("myOrder", order);

                    intent.putExtras(inputArr);
                }
                startActivity(intent);
                break;
        }
    }
}