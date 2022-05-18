package com.example.sqlinclass;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GradesFragment extends Fragment {
    ArrayList<Course> myCourses = new ArrayList<>();
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    RecyclerViewAdapter adapter;
    TextView gpa, hours;


    public GradesFragment(List<Course> courses) {
        this.myCourses = (ArrayList<Course>) courses;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Grades");
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grades, container, false);
        double totalCredits = 0;
        int gradePoints = 0;
        double myGPA;
        for (Course i: myCourses){
            totalCredits = totalCredits + i.getCourseHours();
            if(i.getCourseGrade() =='A'){
                gradePoints = gradePoints + i.getCourseHours()*4;
            } else if(i.getCourseGrade() =='B'){
                gradePoints = gradePoints + i.getCourseHours()*3;
            } else if(i.getCourseGrade() =='C'){
                gradePoints = gradePoints + i.getCourseHours()*2;
            } else if(i.getCourseGrade() =='D'){
                gradePoints = gradePoints + i.getCourseHours()*1;
            } else if(i.getCourseGrade() =='F') {
                gradePoints = gradePoints + i.getCourseHours()*0;
            }
        }
        myGPA = (double) gradePoints/totalCredits;
        myGPA = Math.round(myGPA*100.00)/100.00;


        gpa = view.findViewById(R.id.gpa);

        hours = view.findViewById(R.id.hoursTotal);
        if (myCourses.size() ==0){
            gpa.setText("GPA: 4.0");
        } else{
            gpa.setText("GPA: "+ myGPA);
        }

        totalCredits = Math.round(totalCredits*10.0)/10.0;
        hours.setText("Hours: " +totalCredits);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerViewAdapter(myCourses, iRecycle);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Add_Course:
                iGrades.onMenuClick();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    IGrades iGrades;
    IRecycle iRecycle;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof IGrades) {
            iGrades = (IGrades) context;
        } else {
            throw new RuntimeException("You forgot something important");
        }

        if (context instanceof IRecycle) {
            iRecycle = (IRecycle) context;
        } else {
            throw new RuntimeException("You forgot something important");
        }
    }
}

interface IGrades{
    void onMenuClick();
}