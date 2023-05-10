package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LabTestBookActivity extends AppCompatActivity {

    EditText edtName, edtAddress, edtContact, edtPinCode;
    Button btnBook;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_book);

        edtName = (EditText) findViewById(R.id.edtLTBFullName);
        edtAddress = (EditText) findViewById(R.id.edtLTBAddress);
        edtContact = (EditText) findViewById(R.id.edtLTBContactNb);
        edtPinCode = (EditText) findViewById(R.id.edtLTBPinCode);
        btnBook = (Button) findViewById(R.id.buttonLTBBook);

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

                db.addOrder(username, edtName.getText().toString(),edtAddress.getText().toString(),edtContact.getText().toString(),Integer.parseInt(edtPinCode.getText().toString()),date.toString(),time.toString(),Float.parseFloat(price[1].toString()),"lab");
                db.removeCart(username,"lab");
                Toast.makeText(getApplicationContext(),"Your booking is successful!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LabTestBookActivity.this,HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);

            }
        });

    }
}