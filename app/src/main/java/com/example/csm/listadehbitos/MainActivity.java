package com.example.csm.listadehbitos;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private Button mNewHabitActivity;
    private ListView mListViewHabits;
    private List<String> habits = new ArrayList<>();
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListViewHabits = (ListView) findViewById(R.id.listViewHabits);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Habit");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                habits.clear();
                for (DataSnapshot objSnapshot:dataSnapshot.getChildren()) {
                    Habit h = objSnapshot.getValue(Habit.class);
                    String nameHabit = h.getHabit();
                    habits.add(nameHabit);
                }

                arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, habits);
                mListViewHabits.setAdapter(arrayAdapter);
                mListViewHabits.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent in = new Intent(MainActivity.this, CalendarActivity.class);
                        startActivity(in);
                    }
                });
            }



            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        //Botão Novo Hábito
        mNewHabitActivity = (Button) findViewById(R.id.newHabitActivity);
        mNewHabitActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, NewHabitActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
    }
}
