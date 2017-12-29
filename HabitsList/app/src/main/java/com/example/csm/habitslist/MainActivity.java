package com.example.csm.habitslist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDB;
    private ListView mHabitsList;
    private Button mNewHabitActivity;
    private FirebaseHelper mHelper;
    private CustomAdapter mAdapter;
    private List<Habit> habits = new ArrayList<Habit>();
    private ArrayAdapter<Habit> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHabitsList = (ListView) findViewById(R.id.habitsList);
        //INITIALIZE FIREBASE DB
        mDB = FirebaseDatabase.getInstance().getReference();
        mHelper = new FirebaseHelper(mDB);
        //ADAPTER
        mAdapter = new CustomAdapter(this, mHelper.retrieve());
        mHabitsList.setAdapter(mAdapter);

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
