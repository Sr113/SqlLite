package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ShowDataActivity extends AppCompatActivity {
        ArrayList<StudentModal> studentModalArrayList;
        DbHelper dbHelper;
        DataAdapter dataAdapter;
        RecyclerView rv;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_show_data);

            studentModalArrayList = new ArrayList<>();
            dbHelper = new DbHelper(ShowDataActivity.this);
            studentModalArrayList = dbHelper.readData();
            dataAdapter = new DataAdapter(studentModalArrayList, ShowDataActivity.this);
            rv = findViewById(R.id.rView);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ShowDataActivity.this, RecyclerView.VERTICAL, false);
            rv.setLayoutManager(linearLayoutManager);
            rv.setAdapter(dataAdapter);
        }
}