package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import model.ClientOrder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    TextView tvAmount;
    EditText edClNumber, edNbSlices;
    RadioGroup rgPizza;
    Button btnSave, btnShowAll, btnQuit;
    ArrayList<ClientOrder> ListOfOrders;
    RadioButton rbCheese, rbVegetarian, rbMexican;

    String pizzaType = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();

    }

    private void initialize() {
        tvAmount = (TextView) findViewById(R.id.tvAmount);
        edClNumber = findViewById(R.id.edClNumber);
        edNbSlices = findViewById(R.id.edNbSlices);
        rgPizza = findViewById(R.id.rgPizza);

        btnQuit = findViewById(R.id.btnQuit);
        btnSave = findViewById(R.id.btnSave);
        btnShowAll = findViewById(R.id.btnShowAll);

        btnSave.setOnClickListener(this);
        btnShowAll.setOnClickListener(this);

        ListOfOrders = new ArrayList<ClientOrder>();
        rbCheese = findViewById(R.id.rbCheese);
        rbVegetarian = findViewById(R.id.rbVegetarian);
        rbMexican = findViewById(R.id.rbMexican);

        rbCheese.setOnClickListener(this);
        rbVegetarian.setOnClickListener(this);
        rbMexican.setOnClickListener(this);

        edNbSlices.addTextChangedListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnSave:
                saveOrder(view);
                break;
            case R.id.btnShowAll:
                ShowallOrders();
                 break;
            case R.id.btnQuit:
                quitApp();

                break;

            case R.id.rbCheese:
                pizzaType = rbCheese.getText().toString();

                ShowAmont();
                break;
            case R.id.rbMexican:
                pizzaType = rbMexican.getText().toString();
                ShowAmont();
                break;
            case R.id.rbVegetarian:
                pizzaType = rbVegetarian.getText().toString();
                ShowAmont();
                break;
        }

    }

    private void ShowallOrders() {
        Intent intent = new Intent(this,SecondActivity.class);
        intent.putExtra("order",ListOfOrders);
        startActivity(intent);
    }

    private void quitApp() {
        System.exit(0);
    }

    private void ShowAmont() {
        try {
            float price = getPrice(pizzaType);
            int nbslices = Integer.valueOf(edNbSlices.getText().toString());
            float amount = price*nbslices;
            tvAmount.setText(amount+"");
        }
        catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }

    }

    private float getPrice(String pizzaType) throws Exception {
        float price;
        if(pizzaType.equals("Vegetarian"))
            price=2.5f;
        else
            if(pizzaType.equals("Mexican"))
                price=2.4f;
            else
                if(pizzaType.equals("Cheese"))
                    price=2.0f;
                else
                    throw new Exception("Please select a pizza type");
                return price;

    }

    private void saveOrder(View view) {

      try {

          int ClNumber = Integer.valueOf(edClNumber.getText().toString());
          int nbslices = Integer.valueOf(edNbSlices.getText().toString());
          ClientOrder oneOrder = new ClientOrder(ClNumber, pizzaType, nbslices);
          ListOfOrders.add(oneOrder);
          Snackbar.make(view, "The order of the Client number:" + ClNumber + "\nhas been saved successfully", Snackbar.LENGTH_LONG).show();

          clearWidgets();
      }catch (Exception e){
          Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
      }
    }

    private void clearWidgets() {
        edClNumber.setText(null);
        edNbSlices.setText(null);
        rgPizza.clearCheck();
        tvAmount.setText(null);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
     ShowAmont();
    }
}