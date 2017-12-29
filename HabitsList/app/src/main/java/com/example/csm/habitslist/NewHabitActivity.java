package com.example.csm.habitslist;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class NewHabitActivity extends AppCompatActivity {

    private Button mAddHabit;
    private DatabaseReference mDatabase;
    private EditText mNewHabit;
    private int mStreak = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_habit);

        mAddHabit = (Button) findViewById(R.id.addHabit);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Habit");
        mNewHabit = (EditText) findViewById(R.id.newHabit);

        mAddHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Novo Hábito
                String habit = mNewHabit.getText().toString();
                String streak = String.valueOf(mStreak);

                HashMap<String, String> dataMap = new HashMap<String, String>();
                dataMap.put("Habit", habit);
                dataMap.put("Streak", streak);

                //Insere Novo Hábito
                mDatabase.push().setValue(dataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(NewHabitActivity.this, "Hábito adicionado com sucesso", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(NewHabitActivity.this, "Erro ao adicionar.", Toast.LENGTH_LONG).show();
                        }
                    }
                });

                Intent myIntent = new Intent(NewHabitActivity.this, MainActivity.class);
                NewHabitActivity.this.startActivity(myIntent);
            }
        });
    }
}
