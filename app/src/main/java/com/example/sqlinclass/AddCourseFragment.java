package com.example.sqlinclass;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class AddCourseFragment extends Fragment {
    TextView number, name, hours;
    RadioGroup radioGroup;
    public AddCourseFragment() {}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Add Course");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_course, container, false);
        number = view.findViewById(R.id.courseNumber);
        name = view.findViewById(R.id.courseName);
        hours = view.findViewById(R.id.courseHours);
        radioGroup = view.findViewById(R.id.radioGroup);



        view.findViewById(R.id.submitButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (number.getText().toString().isEmpty() ||
                name.getText().toString().isEmpty() ||
                hours.getText().toString().isEmpty() ||
                radioGroup.getCheckedRadioButtonId() == -1){
                    Toast.makeText(getContext(), "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                } else if(Integer.valueOf(hours.getText().toString()) < 0){
                    Toast.makeText(getContext(), "Course credit hours cannot be negative", Toast.LENGTH_SHORT).show();
                } else {
                    Character grd = 'E';

                    if (radioGroup.getCheckedRadioButtonId() == R.id.RB_A){
                        grd = 'A';
                    } else if (radioGroup.getCheckedRadioButtonId() == R.id.RB_B){
                        grd = 'B';
                    } else if (radioGroup.getCheckedRadioButtonId() == R.id.RB_C){
                        grd = 'C';
                    } else if (radioGroup.getCheckedRadioButtonId() ==R.id.RB_D){
                        grd = 'D';
                    } else if (radioGroup.getCheckedRadioButtonId() ==R.id.RB_F){
                        grd = 'F';
                    }

                    Course myCourse = new Course(number.getText().toString(),
                            name.getText().toString(),
                            Integer.parseInt(hours.getText().toString()),
                            grd);

                    iAddCourse.addCourse(myCourse);
                }
            }
        });

        view.findViewById(R.id.cancelButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iAddCourse.cancel();
            }
        });
        return view;
    }


    IAddCourse iAddCourse;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof IAddCourse) {
            iAddCourse = (IAddCourse) context;
        } else {
            throw new RuntimeException("You forgot something important");
        }
    }
}

interface IAddCourse{
    void addCourse(Course myCourse);
    void cancel();
}