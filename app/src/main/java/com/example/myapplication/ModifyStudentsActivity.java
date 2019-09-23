package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ModifyStudentsActivity extends AppCompatActivity {
    StudentsModel studentsModel;
    private EditText etnim, etname, etemail, etphone;
    private Button btnupdate, btndelete;
    private DatabaseHelperStudent databaseHelperStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_student);

        Intent intent = getIntent();
        studentsModel = (StudentsModel) intent.getSerializableExtra("students");

        databaseHelperStudent = new DatabaseHelperStudent(this);

        etnim = (EditText) findViewById(R.id.etnim);
        etname = (EditText) findViewById(R.id.etname);
        etemail = (EditText) findViewById(R.id.etemail);
        etphone = (EditText) findViewById(R.id.etphone);
        btndelete = (Button) findViewById(R.id.btndelete);
        btnupdate = (Button) findViewById(R.id.btnupdate);

        etnim.setText(studentsModel.getNim());
        etname.setText(studentsModel.getName());
        etemail.setText(studentsModel.getEmail());
        etphone.setText(studentsModel.getPhone());

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelperStudent.updateStudents(studentsModel.getId(),etnim    .getText().toString(),etname.getText().toString(),etemail.getText().toString(), etphone.getText().toString());
                Toast.makeText(ModifyStudentsActivity.this, "Updated Successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ModifyStudentsActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelperStudent.deleteUSer(studentsModel.getId());
                Toast.makeText(ModifyStudentsActivity.this, "Deleted Successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ModifyStudentsActivity.this,MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

    }
}
