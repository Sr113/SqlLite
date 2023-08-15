package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    ArrayList<StudentModal> studentModalArrayList;
    DbHelper dbHelper;
    DataAdapter dataAdapter;
    RecyclerView rv;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        name = getIntent().getStringExtra("name");
        studentModalArrayList = new ArrayList<>();
        dbHelper = new DbHelper(SearchActivity.this);
        studentModalArrayList = dbHelper.getData(name);
        dataAdapter = new DataAdapter(studentModalArrayList, SearchActivity.this);
        rv = findViewById(R.id.rView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchActivity.this, RecyclerView.VERTICAL, false);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(dataAdapter);
    }
}