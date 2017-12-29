package com.example.csm.habitslist;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Habit {

    private String habit, streak;
    private String date;


    public String getHabit() {
        return habit;
    }

    public void setHabit(String habit) {
        this.habit = habit;
    }

    public String getStreak() {
        return streak;
    }

    public void setStreak(String streak) {
        this.streak = streak;
    }

    public void setDate() {
        this.date = new SimpleDateFormat("dd MMM yyyy").format(Calendar.getInstance().getTime());
    }

    public String getDate() {
        return date;
    }
}
