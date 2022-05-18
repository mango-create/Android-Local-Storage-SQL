package com.example.sqlinclass;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "courses")
public class Course {
    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo
    String courseNumber;

    @ColumnInfo
    String courseName;

    @ColumnInfo
    int courseHours;

    @ColumnInfo
    Character courseGrade;

    public Course(String courseNumber, String courseName, int courseHours, Character courseGrade) {
        this.courseNumber = courseNumber;
        this.courseName = courseName;
        this.courseHours = courseHours;
        this.courseGrade = courseGrade;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseNumber='" + courseNumber + '\'' +
                ", courseName='" + courseName + '\'' +
                ", courseHours=" + courseHours +
                ", courseGrade=" + courseGrade +
                '}';
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseHours() {
        return courseHours;
    }

    public void setCourseHours(int courseHours) {
        this.courseHours = courseHours;
    }

    public Character getCourseGrade() {
        return courseGrade;
    }

    public void setCourseGrade(Character courseGrade) {
        this.courseGrade = courseGrade;
    }
}
