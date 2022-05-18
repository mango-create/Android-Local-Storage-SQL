/*
Matthew Mango
In Class Assignment 10
Group#20_InClass10.zip
 */


package com.example.sqlinclass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IAddCourse, IGrades, IRecycle{
    List<Course> myCourses= new ArrayList<>();
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(this, AppDatabase.class, "course.db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .addMigrations()
                .build();
        myCourses = db.courseDAO().getAll();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, new GradesFragment(myCourses))
                .commit();
    }

    @Override
    public void addCourse(Course myCourse) {
        db.courseDAO().insertAll(myCourse);
        Room.databaseBuilder(this, AppDatabase.class, "course.db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .addMigrations()
                .build();
        myCourses = db.courseDAO().getAll();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, new GradesFragment(myCourses))
                .commit();
    }

    @Override
    public void cancel() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, new GradesFragment(myCourses))
                .commit();
    }

    @Override
    public void onMenuClick() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, new AddCourseFragment())
                .commit();
    }

    @Override
    public void onTrashCanClick(int position) {
        db.courseDAO().delete(myCourses.get(position));
        myCourses = db.courseDAO().getAll();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, new GradesFragment(myCourses))
                .commit();
    }
}