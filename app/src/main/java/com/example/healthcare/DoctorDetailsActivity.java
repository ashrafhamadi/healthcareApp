package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {

    private String[][] doctor_details1 =
            {
                    {"Doctor Name: Ashraf Hamadi", "Hospital Address : Beirut", "Exp: 5yrs", "Mobile No: 76553215", "600"},
                    {"Doctor Name: Ali Kobaissi", "Hospital Address : Sour", "Exp: 10yrs", "Mobile No: 03272723", "700"},
                    {"Doctor Name: Ali Harissy", "Hospital Address : Saida", "Exp: 15yrs", "Mobile No: 76128474", "400"},
                    {"Doctor Name: Ahmad Wehbeh", "Hospital Address : Baalbek", "Exp : 7yrs", "Mobile No: 03747478", "300"},
                    {"Doctor Name: Hussein Mahdi", "Hospital Address : Jbeil", "Exp: 9yrs", "Mobile No: 71744473", "900"},
            };

    private String[][] doctor_details2 =
            {
                    {"Doctor Name: Hassan Rahmeh", "Hospital Address : Beirut", "Exp: 5yrs", "Mobile No: 76553215", "600"},
                    {"Doctor Name: Hossam Hijazi", "Hospital Address : Saida", "Exp: 15yrs", "Mobile No: 76128474", "400"},
                    {"Doctor Name: Karim Benzema", "Hospital Address : Sour", "Exp: 10yrs", "Mobile No: 03272723", "700"},
                    {"Doctor Name: Jawad Hakimi", "Hospital Address : Baalbek", "Exp: 7yrs", "Mobile No: 03747478", "300"},
                    {"Doctor Name: Alaa Hassoun", "Hospital Address : Jbeil", "Exp: 9yrs", "Mobile No: 71744473", "900"},
            };

    private String[][] doctor_details3 =
            {
                    {"Doctor Name: Salam Hodroj", "Hospital Address : Beirut", "Exp: 5yrs", "Mobile No: 76553215", "600"},
                    {"Doctor Name: Lama Mohammad", "Hospital Address : Saida", "Exp: 15yrs", "Mobile No: 76128474", "400"},
                    {"Doctor Name: Mohammad Amin", "Hospital Address : Sour", "Exp: 10yrs", "Mobile No: 03272723", "700"},
                    {"Doctor Name: Ahmad Tawbeh", "Hospital Address : Baalbek", "Exp: 7yrs", "Mobile No: 03747478", "300"},
                    {"Doctor Name: Ali Hussein", "Hospital Address : Jbeil", "Exp: 9yrs", "Mobile No: 71744473", "900"},
            };

    private String[][] doctor_details4 =
            {
                    {"Doctor Name: Hussein Hamadi", "Hospital Address : Beirut", "Exp: 5yrs", "Mobile No: 76553215", "600"},
                    {"Doctor Name: Alaa Harissy", "Hospital Address : Saida", "Exp: 15yrs", "Mobile No: 76128474", "400"},
                    {"Doctor Name: Mohammad Kobaissi", "Hospital Address : Sour", "Exp: 10yrs", "Mobile No: 03272723", "700"},
                    {"Doctor Name: Saleem Wehbeh", "Hospital Address : Baalbek", "Exp: 7yrs", "Mobile No: 03747478", "300"},
                    {"Doctor Name: Fatima Mahdi", "Hospital Address : Jbeil", "Exp: 9yrs", "Mobile No: 71744473", "900"},
            };

    private String[][] doctor_details5 =
            {
                    {"Doctor Name: Cristiano Ronaldo", "Hospital Address : Beirut", "Exp: 5yrs", "Mobile No: 76553215", "600"},
                    {"Doctor Name: Luka Modric", "Hospital Address : Saida", "Exp  15yrs", "Mobile No: 76128474", "400"},
                    {"Doctor Name: Mohammad Ali", "Hospital Address : Sour", "Exp: 10yrs", "Mobile No: 03272723", "700"},
                    {"Doctor Name: Mahdi Aalameh", "Hospital Address : Baalbek", "Exp: 7yrs", "Mobile No: 03747478", "300"},
                    {"Doctor Name: Hussein Mahdi", "Hospital Address : Jbeil", "Exp: 9yrs", "Mobile No: 71744473", "900"},
            };

    TextView tv;
    Button btn;
    String[][] doctor_details = {};
    ArrayList list;
    SimpleAdapter sa;
    HashMap<String,String> item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv = (TextView) findViewById(R.id.textViewDDTitle);
        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if(title.compareTo("Physician") == 0){
            doctor_details = doctor_details1;
        }
        else if(title.compareTo("Dietician") == 0){
            doctor_details = doctor_details2;
        }
        else if(title.compareTo("Dentist") == 0){
            doctor_details = doctor_details3;
        }
        else if(title.compareTo("Surgeon") == 0){
            doctor_details = doctor_details4;
        }
        else{
            doctor_details = doctor_details5;
        }

        btn = (Button) findViewById(R.id.BackDDButton);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorDetailsActivity.this,FindDoctorActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        list = new ArrayList();
        for (int i=0; i<doctor_details.length; i++){
            item = new HashMap<String,String>();
            item.put("line1",doctor_details[i][0]);
            item.put("line2",doctor_details[i][1]);
            item.put("line3",doctor_details[i][2]);
            item.put("line4",doctor_details[i][3]);
            item.put("line5","Cons Fees: " + doctor_details[i][4] + "$");
            list.add(item);
        }

        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
                );

        ListView lst = findViewById(R.id.ListViewDD);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent it = new Intent(DoctorDetailsActivity.this,BookAppointmentActivity.class);
                it.putExtra("text1",title);
                it.putExtra("text2",doctor_details[i][0]);
                it.putExtra("text3",doctor_details[i][1]);
                it.putExtra("text4",doctor_details[i][3]);
                it.putExtra("text5",doctor_details[i][4]);
                startActivity(it);
            }
        });
    }
}