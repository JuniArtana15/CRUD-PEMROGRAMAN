package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button addStudents;
    Button modStudents;

    private ListView listView;
    private ArrayList<StudentsModel> studentsModelArrayList;
    private CustomAdapterStudent customAdapterStudent;
    private DatabaseHelperStudent databaseHelperStudent;

    public  void addStudentsActivity(){
        addStudents= (Button)findViewById(R.id.btn_add_teacher);
        addStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addTeachersr = new Intent(MainActivity.this, AddStudents.class);
                startActivity(addTeachersr);

            }
        });
    }

    public  void modStudentsActivity(){
        modStudents= (Button)findViewById(R.id.btn_teacher_modify);
        modStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent modTeachersr = new Intent(MainActivity.this, ModStudent.class);
                startActivity(modTeachersr);

            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addStudentsActivity();
        modStudentsActivity();

        listView = (ListView) findViewById(R.id.teachers_lv);

        databaseHelperStudent = new DatabaseHelperStudent(this);

        studentsModelArrayList = databaseHelperStudent.getAllStudents();

        customAdapterStudent = new CustomAdapterStudent(this,studentsModelArrayList);
        listView.setAdapter(customAdapterStudent);
    }
}
