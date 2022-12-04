package com.example.firebasecrud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText mNameEditText, mAddressEditText;
    EditText mUpdatedNameEditText, mUpdatedAddressEditText;

    DatabaseReference mDatabaseReference1, mDatabaseReference2;
    Student mStudent;
    Teacher mTeacher;
    String key1, key2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDatabaseReference1 = FirebaseDatabase.getInstance().getReference(Student.class.getSimpleName());
        mDatabaseReference2 = FirebaseDatabase.getInstance().getReference(Teacher.class.getSimpleName());

        mNameEditText = findViewById(R.id.name_edittext);
        mAddressEditText = findViewById(R.id.address_edittext);
        mUpdatedNameEditText = findViewById(R.id.update_name_edittext);
        mUpdatedAddressEditText = findViewById(R.id.update_address_edittext);

        //Button Students data action

        findViewById(R.id.insert_student_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertStudentsData();
            }
        });

        findViewById(R.id.read_student_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readStudentsData();
            }
        });
        findViewById(R.id.update_student_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateStudentsData();
            }
        });
        findViewById(R.id.delete_student_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletedStudentsData();
            }
        });

        //Button Teachers data action
        findViewById(R.id.insert_teacher_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertTeachersData();
            }
        });

        findViewById(R.id.read_teacher_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readTeachersData();
            }
        });

        findViewById(R.id.update_teacher_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateTeachersData();
            }
        });

        findViewById(R.id.delete_teacher_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteTeachersData();
            }
        });
    }

    //Function for Students button
    private void insertStudentsData() {
        Student newStudent = new Student();
        String name = mNameEditText.getText().toString();
        String address = mAddressEditText.getText().toString();
        if (name !="" && address != ""){
            newStudent.setNameStudents(name);
            newStudent.setAddressStudents(address);


            mDatabaseReference1.push().setValue(newStudent);
            Toast.makeText(this, "Successfully insert students data!", Toast.LENGTH_SHORT).show();
        }
    }

    private void readStudentsData() {

        mStudent = new Student();
        mDatabaseReference1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {
                    for (DataSnapshot currentData : snapshot.getChildren()) {
                        key1 = currentData.getKey();
                        mStudent.setNameStudents(currentData.child("nameStudents").getValue().toString());
                        mStudent.setAddressStudents(currentData.child("addressStudents").getValue().toString());
                    }
                }

                mUpdatedNameEditText.setText(mStudent.getNameStudents());
                mUpdatedAddressEditText.setText(mStudent.getAddressStudents());
                Toast.makeText(MainActivity.this, "Students Data has been shown!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void updateStudentsData() {
        Student updatedStudentsData = new Student();
        updatedStudentsData.setNameStudents(mUpdatedNameEditText.getText().toString());
        updatedStudentsData.setAddressStudents(mUpdatedAddressEditText.getText().toString());

        mDatabaseReference1.child(key1).setValue(updatedStudentsData);
        Toast.makeText(MainActivity.this, "Students Data has been updated!", Toast.LENGTH_SHORT).show();
    }

    private void deletedStudentsData() {
        Student updatedStudentsData = new Student();
        updatedStudentsData.setNameStudents(mUpdatedNameEditText.getText().toString());
        updatedStudentsData.setAddressStudents(mUpdatedAddressEditText.getText().toString());

        mDatabaseReference1.child(key1).removeValue();
        Toast.makeText(MainActivity.this, "Students Data has been deleted!", Toast.LENGTH_SHORT).show();
    }

    //Function for teachers button
     private void insertTeachersData() {
         Teacher newTeacher = new Teacher();
         String name = mNameEditText.getText().toString();
         String address = mAddressEditText.getText().toString();
         if (name !="" && address != ""){
             newTeacher.setNameTeachers(name);
             newTeacher.setAddressTeachers(address);


             mDatabaseReference2.push().setValue(newTeacher);
             Toast.makeText(this, "Successfully insert teachers data!", Toast.LENGTH_SHORT).show();
         }
     }

     private void readTeachersData(){
        mTeacher = new Teacher();
        mDatabaseReference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChildren()) {
                    for (DataSnapshot currentData : snapshot.getChildren()) {
                        key2 = currentData.getKey();
                        mTeacher.setNameTeachers(currentData.child("nameTeachers").getValue().toString());
                        mTeacher.setAddressTeachers(currentData.child("addressTeachers").getValue().toString());
                    }
                }

                mUpdatedNameEditText.setText(mTeacher.getNameTeachers());
                mUpdatedAddressEditText.setText(mTeacher.getAddressTeachers());
                Toast.makeText(MainActivity.this, "Teachers Data has been shown!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
     }

     private void updateTeachersData(){
         Teacher updatedTeachersData = new Teacher();
         updatedTeachersData.setNameTeachers(mUpdatedNameEditText.getText().toString());
         updatedTeachersData.setAddressTeachers(mUpdatedAddressEditText.getText().toString());

         mDatabaseReference2.child(key2).setValue(updatedTeachersData);
         Toast.makeText(MainActivity.this, "Teachers Data has been updated!", Toast.LENGTH_SHORT).show();
     }

     private void deleteTeachersData(){
         Teacher updatedTeachersData = new Teacher();
         updatedTeachersData.setNameTeachers(mUpdatedNameEditText.getText().toString());
         updatedTeachersData.setAddressTeachers(mUpdatedAddressEditText.getText().toString());

         mDatabaseReference2.child(key2).removeValue();
         Toast.makeText(MainActivity.this, "Teachers Data has been deleted!", Toast.LENGTH_SHORT).show();
     }
}