package com.example.carshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    int quantity=0;
    Spinner spinner;
    ArrayList spinnerArrayList;
    ArrayAdapter spinnerAdapter;
    HashMap goodsMap;
    Double value;
    String goods;
    EditText userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner=findViewById(R.id.spinner);
        userName=findViewById(R.id.editText);
        spinnerArrayList=new ArrayList();
        goodsMap= new HashMap();
        goodsMap.put("Toyota Corolla",1500000.0);
        goodsMap.put("Mercedes Benz",2500000.0);
        goodsMap.put("Ferrari",1000000.0);
        goodsMap.put("BMW",1600000.0);
        spinnerArrayList.add("Toyota Corolla");
        spinnerArrayList.add("Mercedes Benz");
        spinnerArrayList.add("Ferrari");
        spinnerArrayList.add("BMW");
        spinnerAdapter= new ArrayAdapter(this,android.R.layout.simple_spinner_item,spinnerArrayList);
        spinner.setOnItemSelectedListener(this);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

    }
    //this method is created for onclick button
    public void addOneQuantity(View view) {
        quantity=quantity+1;
        TextView quantityView=findViewById(R.id.amountOfQuantityTextView);
        quantityView.setText(""+quantity);
        TextView priceTextView=findViewById(R.id.priceTextView);
        priceTextView.setText(""+quantity*value);
    }
    public void decreaseQuantity(View view){
        if (quantity>0){
        quantity=quantity-1;
        TextView priceTextView=findViewById(R.id.priceTextView);
        priceTextView.setText(""+quantity*value);
        }
        else{
            quantity=0;
        }
        TextView quantityView=findViewById(R.id.amountOfQuantityTextView);
        quantityView.setText(""+quantity);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

       value=(double)goodsMap.get(spinner.getSelectedItem().toString());
       TextView priceTextView=findViewById(R.id.priceTextView);
       priceTextView.setText(""+value);
       TextView amountOfQuantityTextView=findViewById(R.id.amountOfQuantityTextView);
       ImageView imageOnSpinner=findViewById(R.id.itemImage);
       goods=spinner.getSelectedItem().toString();
       switch (goods){
           case("Toyota Corolla"):
               imageOnSpinner.setImageResource(R.drawable.car);
               return;
           case("Mercedes Benz"):
               imageOnSpinner.setImageResource(R.drawable.bmww);
               return;
           case("Ferrari"):
               imageOnSpinner.setImageResource(R.drawable.ferarri);
               return;
           case("BMW"):
               imageOnSpinner.setImageResource(R.drawable.toyota);
               default:
                   return;
       }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public void addToCart(View view) {
        Order order = new Order();
        order.goodsName=goods;
        order.orderPrice=value*quantity;
        order.quantity=quantity;
        order.userName= userName.getText().toString();
        Intent orderIntent= new Intent(MainActivity.this, OrderDetails.class);
        orderIntent.putExtra("goodsNameIntent",order.goodsName);
        orderIntent.putExtra("quantityIntent", order.quantity);
        orderIntent.putExtra("orderPriceIntent",order.orderPrice);
        orderIntent.putExtra("orderNameIntent", order.userName);
        startActivity(orderIntent);



    }
}
