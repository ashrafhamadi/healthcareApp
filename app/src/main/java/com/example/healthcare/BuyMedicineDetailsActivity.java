package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BuyMedicineDetailsActivity extends AppCompatActivity {

    TextView tvPackageName,tvTotalCost;
    EditText edDetails;
    Button btnBack, btnGoToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_details);

        tvPackageName = (TextView) findViewById(R.id.textViewABMCart);
        tvTotalCost = (TextView) findViewById(R.id.textViewTotalCostABM);
        edDetails = (EditText) findViewById(R.id.editABMMultiLine);
        btnBack = (Button) findViewById(R.id.backABMButton);
        btnGoToCart = (Button) findViewById(R.id.buttonABMAddToCart);

        edDetails.setKeyListener(null);

        Intent it = getIntent();
        tvPackageName.setText(it.getStringExtra("text1"));
        edDetails.setText(it.getStringExtra("text2"));
        tvTotalCost.setText(it.getStringExtra("text3")+"$");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuyMedicineDetailsActivity.this,BuyMedicineActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs",MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                String product = tvPackageName.getText().toString();
                float price = Float.parseFloat(it.getStringExtra("text3").toString());

                Database db = new Database(getApplicationContext(),"healthcare",null,1);

                if(db.checkCart(username,product) == 1){
                    Toast.makeText(getApplicationContext(),"Product has already been added!",Toast.LENGTH_SHORT).show();
                }
                else{
                    db.addCart(username,product,price,"medicine");
                    Toast.makeText(getApplicationContext(),"Product added to cart!",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BuyMedicineDetailsActivity.this,BuyMedicineActivity.class));
                }
            }
        });

    }
}