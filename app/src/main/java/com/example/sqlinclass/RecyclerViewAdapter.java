package com.example.sqlinclass;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.SortViewHolder> {
    List<Course> courses;
    IRecycle iRecycle;

    public RecyclerViewAdapter(List<Course> data, IRecycle iRecycle){
        this.courses = data;
        this.iRecycle = iRecycle;
    }

    @NonNull
    @Override
    public SortViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grades_custom_layout, parent, false);
        SortViewHolder sortViewHolder = new SortViewHolder(view);
        return sortViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SortViewHolder holder, int position) {
        holder.courseNumber.setText(courses.get(position).getCourseNumber());
        holder.letterGrade.setText(courses.get(position).getCourseGrade()+"");
        if (courses.get(position).getCourseHours() ==1){
            holder.numCredits.setText("1 credit hour");
        } else{
            holder.numCredits.setText(courses.get(position).getCourseHours()+" credit hours");
        }

        holder.courseName.setText(courses.get(position).getCourseName());
        holder.position = position;
        holder.trashCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iRecycle.onTrashCanClick(position);
            }
        });

    }


    @Override
    public int getItemCount() { return this.courses.size(); }

    public static class SortViewHolder extends RecyclerView.ViewHolder {
        TextView courseNumber;
        TextView courseName;
        TextView numCredits;
        TextView letterGrade;
        ImageView trashCan;
        int position;
        public SortViewHolder(@NonNull View itemView) {
            super(itemView);
            courseName = itemView.findViewById(R.id.name);
            courseNumber = itemView.findViewById(R.id.number);
            numCredits = itemView.findViewById(R.id.credithours);
            letterGrade = itemView.findViewById(R.id.letterGrade);
            trashCan = itemView.findViewById(R.id.imageTrash);
        }
    }
}

interface IRecycle{
    void onTrashCanClick(int position);
}