package com.example.lab3mobile;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StudentListActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private TextView studentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        dbHelper = new DatabaseHelper(this);
        studentList = findViewById(R.id.studentList);

        displayStudentData();
    }

    private void displayStudentData() {
        Cursor cursor = dbHelper.getAllStudents();
        if (cursor != null && cursor.moveToFirst()) {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%-5s %-20s %-20s\n", "ID", "ФИО", "Время добавления"));
            sb.append("-----------------------------------------------------\n");

            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String dateAdded = DatabaseHelper.formatTimestamp(cursor.getLong(2));

                sb.append(String.format("%-5d %-20s %-20s\n", id, name, dateAdded));
            } while (cursor.moveToNext());

            studentList.setText(sb.toString());
            cursor.close();
        } else {
            studentList.setText("Нет данных для отображения.");
        }
    }
}
