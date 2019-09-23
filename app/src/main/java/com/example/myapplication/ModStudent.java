package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ModStudent extends AppCompatActivity {
    private ListView listView;
    private ArrayList<StudentsModel> studentsModelArrayList;
    private CustomMod customMod;
    private DatabaseHelperStudent databaseHelperStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mod_student);

        listView = (ListView) findViewById(R.id.teachers_lvi);

        databaseHelperStudent = new DatabaseHelperStudent(this);

        studentsModelArrayList = databaseHelperStudent.getAllStudents();

        customMod = new CustomMod(this,studentsModelArrayList);
        listView.setAdapter(customMod);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ModStudent.this,ModifyStudentsActivity.class);
                intent.putExtra("students",studentsModelArrayList.get(position));
                startActivity(intent);
            }
        });
    }
}
