package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BuyMedicineBookActivity extends AppCompatActivity {

    EditText edtName, edtAddress, edtContact, edtPinCode;
    Button btnBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_book);

        edtName = (EditText) findViewById(R.id.edtBMBFullName);
        edtAddress = (EditText) findViewById(R.id.edtBMBAddress);
        edtContact = (EditText) findViewById(R.id.edtBMBContactNb);
        edtPinCode = (EditText) findViewById(R.id.edtBMBPinCode);
        btnBook = (Button) findViewById(R.id.buttonBMBBook);

        Intent it = getIntent();
        String[] price = it.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String date = it.getStringExtra("date");
        String time = it.getStringExtra("time");

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs",MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();

                Database db = new Database(getApplicationContext(),"healthcare", null, 1);

                db.addOrder(username, edtName.getText().toString(),edtAddress.getText().toString(),edtContact.getText().toString(),Integer.parseInt(edtPinCode.getText().toString()),date.toString(),time.toString(),Float.parseFloat(price[1].toString()),"medicine");
                db.removeCart(username,"medicine");
                Toast.makeText(getApplicationContext(),"Your booking is successful!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(BuyMedicineBookActivity.this,HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });

    }
}