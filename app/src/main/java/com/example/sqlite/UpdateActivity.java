package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText txtRegNo, txtName, txtEmail;
    Button update,delete;
    DbHelper dbHelper;
    String regNo, name, email,r,n,e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        txtRegNo = findViewById(R.id.idRegNo);
        txtName = findViewById(R.id.idName);
        txtEmail = findViewById(R.id.idEmail);
        update = findViewById(R.id.idUpdate);
        delete = findViewById(R.id.idDelete);

        dbHelper = new DbHelper(UpdateActivity.this);

        regNo = getIntent().getStringExtra("reg");
        name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");

        txtRegNo.setText(regNo);
        txtName.setText(name);
        txtEmail.setText(email);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                r = txtRegNo.getText().toString();
                n = txtName.getText().toString();
                e = txtEmail.getText().toString();
                dbHelper.updateData(r, n, e);
                Toast.makeText(UpdateActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                n = txtName.getText().toString();
                dbHelper.deleteData(n);
                Toast.makeText(UpdateActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}