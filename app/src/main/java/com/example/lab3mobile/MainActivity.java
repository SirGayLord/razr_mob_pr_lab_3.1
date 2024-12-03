package com.example.lab3mobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);

        Button viewButton = findViewById(R.id.viewButton);
        Button addButton = findViewById(R.id.addButton);
        Button updateButton = findViewById(R.id.updateButton);

        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StudentListActivity.class);
                startActivity(intent);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.insertStudent("Новый Студент");
                Toast.makeText(MainActivity.this, "Запись добавлена", Toast.LENGTH_SHORT).show();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.updateLastStudent();
                Toast.makeText(MainActivity.this, "Последняя запись обновлена", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
