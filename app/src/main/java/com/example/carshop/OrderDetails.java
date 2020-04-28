package com.example.carshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OrderDetails extends AppCompatActivity {
    String[] addresses={"afolabinasifudeen@gmail.com"};
    String subject="Order from Car Shop";
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        setTitle("Order Details");
        Intent receivedOrderIntent= getIntent();
        String userName=receivedOrderIntent.getStringExtra("orderNameIntent");
        String orderName=receivedOrderIntent.getStringExtra("goodsNameIntent");
        int quantity=receivedOrderIntent.getIntExtra("quantityIntent",0);
        double orderPrice=receivedOrderIntent.getDoubleExtra("orderPriceIntent",0.0d);
        TextView orderDetailsView=findViewById(R.id.textView);
        message="Name of Client: "+userName+"\nName of Order: "+orderName+ "\nQuantity of Order: "+quantity+"\n" +
                "Price of Order: "+orderPrice;
        orderDetailsView.setText(message);

    }

    public void sendEmail(View view) {
        Intent intent= new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL,addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT,subject);
        intent.putExtra(Intent.EXTRA_TEXT,message);
        if (intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }

    }
}
