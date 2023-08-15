package com.example.sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText txtName, txtReg, txtEmail,txtSearch;
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtReg = findViewById(R.id.txtReg);
        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);
        txtSearch = findViewById(R.id.txtSearch);
        Button btn = findViewById(R.id.btn);
        Button show = findViewById(R.id.showBtn);
        Button search = findViewById(R.id.searchBtn);

        dbHelper = new DbHelper(MainActivity.this);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String regNo = txtReg.getText().toString();
                String name = txtName.getText().toString();
                String email = txtEmail.getText().toString();

                if (regNo.isEmpty() && name.isEmpty() && email.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }
                dbHelper.addNewCourse(regNo, name, email);

                Toast.makeText(MainActivity.this, "data has been added.", Toast.LENGTH_LONG).show();
                txtReg.setText("");
                txtName.setText("");
                txtEmail.setText("");
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ShowDataActivity.class));
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,SearchActivity.class);
                String name = txtSearch.getText().toString();
                i.putExtra("name",name);
                startActivity(i);
            }
        });
    }
}