package com.example.boss.myapplication;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    int quantity;
    TextView tv_quantity, price_view, price_text_view, qualityTextView;
    ArrayList spinnerArrayList;
    ArrayAdapter spinnerAdapter;

    HashMap goodsMap;
    String goodsName;
    double price;
    EditText userNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameEditText = findViewById(R.id.nameEditText);
        createMap();
        creatwSpinner();
    }

    private void createMap() {
        goodsMap = new HashMap();
        goodsMap.put("guitar", 500.0);
        goodsMap.put("brums", 1500.0);
        goodsMap.put("keyboard", 1000.0);
    }

    private void creatwSpinner() {
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        spinnerArrayList = new ArrayList();
        spinnerArrayList.add("guitar");
        spinnerArrayList.add("brums");
        spinnerArrayList.add("keyboard");

        spinnerAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        tv_quantity = findViewById(R.id.quantity_text_view);
        price_view = findViewById(R.id.price_text_view);

    }

    public void IncreaseQuantity(View view) {
        quantity = quantity +1;
        TextView tv_quantity = findViewById(R.id.quantity_text_view);
        tv_quantity.setText(" " + quantity);
        TextView price_view = findViewById(R.id.price_text_view);
        price_view.setText("" + quantity * price);
     }

    public void decreadeQuantity(View view) {
        quantity = quantity - 1;
        if(quantity < 0) {
            quantity = 0;
        }
            TextView tv_quantity = findViewById(R.id.quantity_text_view);
            tv_quantity.setText("" + quantity);
        TextView price_view = findViewById(R.id.price_text_view);
        price_view.setText("" + quantity * price);
        }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        goodsName = spinner.getSelectedItem().toString();
        price = (double)goodsMap.get(goodsName);
        TextView price_view = findViewById(R.id.price_text_view);
        price_view.setText("" + quantity * price);

        ImageView goodsImageView = findViewById(R.id.goodsImageView);

        switch (goodsName){
            case "guitar":
                goodsImageView.setImageResource(R.drawable.guitar);
                break;
            case "brums":
                goodsImageView.setImageResource(R.drawable.brams);
                break;
            case "keyboard":
                goodsImageView.setImageResource(R.drawable.keyboard);
                break;
                default:
                    goodsImageView.setImageResource(R.drawable.guitar);
                    break;

        }
      //  if(goodsName.equals("guitar")){
        //    goodsImageView.setImageResource(R.drawable.gibson225);
        //}else if(goodsName.equals("brums")){
          //  goodsImageView.setImageResource(R.drawable.brams);
        //}else if(goodsName.equals("keyboard")){
          //  goodsImageView.setImageResource(R.drawable.keyboard);
     //   }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void addToCart(View view) {
        Order order = new Order();
        order.userName = userNameEditText.getText().toString();
       // Log.d("userName", order.userName);
        order.gootsName = goodsName;
       // Log.d("gootsName", order.gootsName);
        order.quantity = quantity;
       // Log.d("quantity", " " + order.quantity);
        order.price = price;
        order.orderPrice = quantity * price;
       // Log.d("orderPrice", " " + order.orderPrice);


        Intent orderIntent = new Intent(MainActivity.this, OrderActivity.class);
        orderIntent.putExtra("userNameForIntent", order.userName);
        orderIntent.putExtra("gootsName", order.gootsName);
        orderIntent.putExtra("quantity", order.quantity);
        orderIntent.putExtra("price", order.price);
        orderIntent.putExtra("orderPrice", order.orderPrice);


        startActivity(orderIntent);



    }
}
