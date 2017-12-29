package com.example.csm.habitslist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by EneidaDoralina on 28/12/2017.
 */

public class CustomAdapter extends BaseAdapter{

    Context c;
    ArrayList<Habit> habits;

    public CustomAdapter(Context c, ArrayList<Habit> habits) {
        this.c = c;
        this.habits = habits;
    }
    @Override
    public int getCount() {
        return habits.size();
    }
    @Override
    public Object getItem(int position) {
        return habits.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
        {
            convertView = LayoutInflater.from(c).inflate(R.layout.habit_item, parent,false);
        }

        final TextView habitTxt = (TextView) convertView.findViewById(R.id.showHabit);
        TextView streakTxt = (TextView) convertView.findViewById(R.id.showStreak);
        TextView dateTxt = (TextView) convertView.findViewById(R.id.showDate);

        final Habit h = (Habit) this.getItem(position);
        habitTxt.setText(h.getHabit());
        streakTxt.setText(h.getStreak());
        dateTxt.setText(h.getDate());

        final String habit = h.getHabit();

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c, habit, Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }
}
